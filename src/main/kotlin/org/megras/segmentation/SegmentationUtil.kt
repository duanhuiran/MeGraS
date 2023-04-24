package org.megras.segmentation

import org.megras.segmentation.type.*
import java.util.*

object SegmentationUtil {

    fun shouldSwap(first: SegmentationType?, second: SegmentationType?): Boolean {
        return (first != SegmentationType.TIME && second == SegmentationType.TIME) ||
            (first == SegmentationType.CHANNEL && second != SegmentationType.CHANNEL)
    }

    fun parseSegmentationType(name: String): SegmentationType? =
        try {
            SegmentationType.valueOf(name.uppercase())
        } catch (e: IllegalArgumentException) {
            null //not found
        }

    fun parseSegmentation(segmentType: String, segmentDefinition: String): Segmentation? {

        val type = parseSegmentationType(segmentType)
        return when (type) {
            SegmentationType.RECT -> {
                val coords = segmentDefinition.split(",").mapNotNull {
                    it.trim().toDoubleOrNull()
                }

                if (coords.size == 4) {
                    Rect(coords[0], coords[1], coords[2], coords[3])
                } else {
                    null
                }
            }

            /**
             * (x,y),(x,y),...,(x,y)
             */
            SegmentationType.POLYGON -> {
                val points = parsePointPairs(segmentDefinition)
                val finalPoints = points.filterNotNull()

                if (finalPoints.size == points.size) {
                    Polygon(finalPoints)
                } else {
                    null
                }
            }
            SegmentationType.BEZIER -> {
                val points = parsePointPairs(segmentDefinition)
                val finalPoints = points.filterNotNull()

                if (finalPoints.size == points.size) {
                    BezierSpline(finalPoints)
                } else {
                    null
                }
            }
            SegmentationType.BSPLINE -> {
                val points = parsePointPairs(segmentDefinition)
                val finalPoints = points.filterNotNull()

                if (finalPoints.size == points.size) {
                    BSpline(finalPoints)
                } else {
                    null
                }
            }

            SegmentationType.PATH -> {
                SVGPath(segmentDefinition)
            }

            SegmentationType.MASK -> {
                var binaryString = ""
                if (segmentDefinition.matches(Regex("^[01]+$"))) {
                    binaryString = segmentDefinition
                } else {
                    /**
                    try {
                    val decoded = Base64.getDecoder().decode(definition)
                    binaryString = BigInteger(1, decoded).toString(2)
                    } catch (_: Exception) {}
                     **/
                }

                val mask = BitSet(binaryString.length)
                binaryString.forEachIndexed { i, b ->
                    if (b == '1') mask.set(i)
                }

                Mask(mask)
            }

            SegmentationType.HILBERT -> {
                val elements = segmentDefinition.split(",")
                val dimensions = elements[0].toIntOrNull()
                val order = elements[1].toIntOrNull()

                val ranges = mutableListOf<Interval<Long>>()
                elements.forEach { el ->
                    val range = el.split("-").map { it.toLongOrNull() ?: return null }
                    when (range.size) {
                        1 -> ranges.add(Interval(range[0], range[0]))
                        2 -> ranges.add(Interval(range[0], range[1]))
                        else -> return null
                    }
                }

                ranges.removeAt(1) // order
                ranges.removeAt(0) // dimension

                if (dimensions != null && order != null) {
                    Hilbert(dimensions, order, ranges)
                } else {
                    null
                }
            }

            SegmentationType.CHANNEL -> {
                val channels = segmentDefinition.split(",")
                Channel(channels)
            }

            SegmentationType.FREQUENCY -> {
                val bounds = segmentDefinition.split(",").map { it.toIntOrNull() ?: return null }

                if (bounds.size == 2) {
                    Frequency(bounds[0], bounds[1])
                } else {
                    null
                }
            }

            SegmentationType.TIME -> {
                val elements = segmentDefinition.split(",")

                val intervals = mutableListOf<Interval<Double>>()
                elements.forEach { el ->
                    val range = el.split("-").map { it.trim().toDoubleOrNull() ?: return null }
                    when (range.size) {
                        2 -> intervals.add(Interval(range[0], range[1]))
                        else -> return null
                    }
                }
                Time(intervals)
            }

            SegmentationType.PLANE -> {
                val params = segmentDefinition.split(",").map {
                    it.trim().toDoubleOrNull() ?: return null
                }

                if (params.size == 5) {
                    Plane(params[0], params[1], params[2], params[3], params[4] == 1.0)
                } else {
                    null
                }
            }

            /**
             * t0,type,description;t1,type,description;t2,type,description
             * type and description follow the other guidelines, e.g. rect,0,1,0,1
             */
            SegmentationType.ROTOSCOPE -> {
                val rotoscopeList = mutableListOf<RotoscopePair>()

                segmentDefinition.split(";").forEach { part ->
                    val p = part.split(",")
                    val time = p[0].toDoubleOrNull()
                    val segmentationType = p[1]
                    val segmentationDescription = part.substringAfter("$segmentationType,")

                    val segmentation = parseSegmentation(segmentationType, segmentationDescription)
                    if (time != null && segmentation is TwoDimensionalSegmentation) {
                        rotoscopeList.add(RotoscopePair(time, segmentation))
                    } else {
                        return null
                    }
                }
                return Rotoscope(rotoscopeList)
            }

            else -> null
        }
    }

    private fun parsePointPairs(input: String) : List<Pair<Double, Double>?> {
        return input.split("),").map { chunk ->
            val coords = chunk.replaceFirst("(", "").replace(")", "").split(",").map { it.toDoubleOrNull() }
            if (coords.any { it == null }) {
                null
            } else if (coords.size < 2) {
                null
            } else {
                coords[0]!! to coords[1]!!
            }
        }
    }

}