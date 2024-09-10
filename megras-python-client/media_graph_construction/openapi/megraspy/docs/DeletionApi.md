# media_graph_construction.openapi.megraspy.DeletionApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**post_delete_quads**](DeletionApi.md#post_delete_quads) | **POST** /delete/quads | Deletes matching quads from the graph.


# **post_delete_quads**
> ApiQueryResult post_delete_quads()

Deletes matching quads from the graph.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import deletion_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_basic_query import ApiBasicQuery
from media_graph_construction.openapi.megraspy.model.rest_error_status import RestErrorStatus
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = media_graph_construction.openapi.megraspy.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with media_graph_construction.openapi.megraspy.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = deletion_api.DeletionApi(api_client)
    api_basic_query = ApiBasicQuery(
        s=[
            "s_example",
        ],
        p=[
            "p_example",
        ],
        o=[
            "o_example",
        ],
    ) # ApiBasicQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Deletes matching quads from the graph.
        api_response = api_instance.post_delete_quads(api_basic_query=api_basic_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling DeletionApi->post_delete_quads: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_basic_query** | [**ApiBasicQuery**](ApiBasicQuery.md)|  | [optional]

### Return type

[**ApiQueryResult**](ApiQueryResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

