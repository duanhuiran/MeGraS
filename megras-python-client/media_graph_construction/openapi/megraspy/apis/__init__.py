
# flake8: noqa

# Import all APIs into this package.
# If you have many APIs here with many many models used in each API this may
# raise a `RecursionError`.
# In order to avoid this, import only the API that you directly need like:
#
#   from .api.addition_api import AdditionApi
#
# or import this package, but before doing it, use:
#
#   import sys
#   sys.setrecursionlimit(n)

# Import APIs into API package:
from media_graph_construction.openapi.megraspy.api.addition_api import AdditionApi
from media_graph_construction.openapi.megraspy.api.deletion_api import DeletionApi
from media_graph_construction.openapi.megraspy.api.query_api import QueryApi
