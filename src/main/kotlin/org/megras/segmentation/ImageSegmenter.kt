package org.megras.segmentation

import org.tinyspline.BSpline
import java.awt.Color
import java.awt.Shape
import java.awt.geom.Path2D
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import kotlin.math.roundToInt


object ImageSegmenter {

    fun toBinary(image: BufferedImage, segmentation: Segmentation): ByteArray? = try {
        when (segmentation.type) {
            SegmentationType.RECT -> rectToBinary(image, segmentation as Rect)
            SegmentationType.POLYGON -> polygonToBinary(image, segmentation as Polygon)
            SegmentationType.PATH -> pathToBinary(image, segmentation as Path)
            SegmentationType.SPLINE -> splineToBinary(image, segmentation as Spline)
            SegmentationType.MASK -> (segmentation as Mask).mask
            else -> null
        }
    } catch (e: Exception) {
        null
    }

    fun segment(image: BufferedImage, mask: ByteArray): BufferedImage? {
        try {
            if (image.width * image.height != mask.size) {
                return null
            }

            var top = image.height
            var bottom = 0
            var left = image.width
            var right = 0

            for (y in 0 until image.height) {
                for (x in 0 until image.width) {

                    if (mask[y * image.width + x].compareTo(1) == 0) {
                        top = Math.min(top, y)
                        bottom = Math.max(bottom, y)
                        left = Math.min(left, x)
                        right = Math.max(right, x)
                    } else {
                        image.setRGB(x, y, 0)
                    }
                }
            }

            val out = BufferedImage(right - left, bottom - top, BufferedImage.TYPE_INT_ARGB)
            val g = out.createGraphics()
            g.drawImage(image, -left, -top, null)
            g.dispose()

            return out
        } catch (e: Exception) {
            //TODO log
            return null
        }
    }

    private fun rectToBinary(image: BufferedImage, r: Rect) : ByteArray {
        val rect = Rectangle2D.Double(r.xmin, r.ymin, r.width, r.height)
        return generateMask(image, rect)
    }

    private fun polygonToBinary(image: BufferedImage, segmentation: Polygon) : ByteArray {
        val polygon = java.awt.Polygon(segmentation.vertices.map { it.first.roundToInt() }.toIntArray(), segmentation.vertices.map { it.second.roundToInt() }.toIntArray(), segmentation.vertices.size)
        return generateMask(image, polygon)
    }

    private fun pathToBinary(image: BufferedImage, path: Path) : ByteArray {
        return generateMask(image, path.path)
    }

    private fun splineToBinary(image: BufferedImage, polygon: Spline) : ByteArray {
        var spline = BSpline(polygon.vertices.size.toLong(), 2, 3, BSpline.Type.Opened)
        spline.controlPoints = polygon.vertices.flatMap { listOf(it.first, it.second) }
        spline = spline.toBeziers()

//        val spline = BSpline.interpolateCubicNatural(polygon.vertices.flatMap { listOf(it.first, it.second) }, 2).toBeziers()

        val ctrlp = spline.controlPoints
        val order = spline.order.toInt()
        val dim = spline.dimension.toInt()
        val nBeziers = (ctrlp.size / dim) / order
        val path = Path2D.Double()
        path.moveTo(ctrlp[0], ctrlp[1])
        for (i in 0 until nBeziers) {
            path.curveTo(
                ctrlp[i * dim * order + 2], ctrlp[i * dim * order + 3],
                ctrlp[i * dim * order + 4], ctrlp[i * dim * order + 5],
                ctrlp[i * dim * order + 6], ctrlp[i * dim * order + 7]
            )
        }

        return generateMask(image, path)
    }

    private fun generateMask(inputImage: BufferedImage, clippingShape: Shape) : ByteArray {
        val segmentedImage = BufferedImage(inputImage.width, inputImage.height, BufferedImage.TYPE_INT_ARGB)
        val g = segmentedImage.createGraphics() //TODO replace clipping with mask alpha blending to get smooth edges
        g.clip = clippingShape
        g.drawImage(inputImage, 0, 0, null)
        g.dispose()

        val alpha = segmentedImage.alphaRaster
        val mask = ByteArray(segmentedImage.width * segmentedImage.height)

        for (y in 0 until segmentedImage.height) {
            for (x in 0 until segmentedImage.width) {
                if (alpha.getSample(x, y, 0) == 0) {
                    mask[y * segmentedImage.width + x] = 0
                }
                else {
                    mask[y * segmentedImage.width + x] = 1
                }
            }
        }
        val x = mask.groupBy { it }.map { "${it.key} : ${it.value.size}" }
        return mask
    }

    fun segmentChannel(image: BufferedImage, channel: Channel): BufferedImage {

        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                val rgb = image.getRGB(x, y)
                var red = rgb and 0x00ff0000 shr 16
                var green = rgb and 0x0000ff00 shr 8
                var blue = rgb and 0x000000ff

                if (!channel.selection.contains("red")) red = 0
                if (!channel.selection.contains("green")) green = 0
                if (!channel.selection.contains("blue")) blue = 0

                image.setRGB(x, y, Color(red, green, blue).rgb)
            }
        }
        return image
    }
}