# media_graph_construction.openapi.megraspy.AdditionApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**post_add_quads**](AdditionApi.md#post_add_quads) | **POST** /add/quads | Adds new quads to the graph.


# **post_add_quads**
> ApiQueryResult post_add_quads()

Adds new quads to the graph.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import addition_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.rest_error_status import RestErrorStatus
from media_graph_construction.openapi.megraspy.model.api_add_quad import ApiAddQuad
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = media_graph_construction.openapi.megraspy.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with media_graph_construction.openapi.megraspy.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = addition_api.AdditionApi(api_client)
    api_add_quad = ApiAddQuad(
        quads=[
            ApiQuad(
                id="id_example",
                s="s_example",
                p="p_example",
                o="o_example",
            ),
        ],
    ) # ApiAddQuad |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Adds new quads to the graph.
        api_response = api_instance.post_add_quads(api_add_quad=api_add_quad)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling AdditionApi->post_add_quads: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_add_quad** | [**ApiAddQuad**](ApiAddQuad.md)|  | [optional]

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

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

