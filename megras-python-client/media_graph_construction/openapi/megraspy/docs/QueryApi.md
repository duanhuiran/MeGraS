# media_graph_construction.openapi.megraspy.QueryApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_query_sparql**](QueryApi.md#get_query_sparql) | **GET** /query/sparql | Queries the Graph using SPARQL.
[**post_query_knn**](QueryApi.md#post_query_knn) | **POST** /query/knn | Queries the Graph for quads within a kNN-cluster.
[**post_query_object**](QueryApi.md#post_query_object) | **POST** /query/object | Queries the Graph for quads with a specific object.
[**post_query_path**](QueryApi.md#post_query_path) | **POST** /query/path | Queries a path along a set of predicates starting from a set of subjects.
[**post_query_predicate**](QueryApi.md#post_query_predicate) | **POST** /query/predicate | Queries the Graph for quads with a specific predicate.
[**post_query_quads**](QueryApi.md#post_query_quads) | **POST** /query/quads | Queries the Graph.
[**post_query_relevancefeedback**](QueryApi.md#post_query_relevancefeedback) | **POST** /query/relevancefeedback | Queries the Graph for quads based on positive and negative examples.
[**post_query_subject**](QueryApi.md#post_query_subject) | **POST** /query/subject | Queries the Graph for quads with a specific subject.
[**post_query_text**](QueryApi.md#post_query_text) | **POST** /query/text | Queries the Graph for quads with a specific predicate and an object string that matches a specified filter text.


# **get_query_sparql**
> ApiSparqlResult get_query_sparql()

Queries the Graph using SPARQL.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_sparql_result import ApiSparqlResult
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
    api_instance = query_api.QueryApi(api_client)
    query = "query_example" # str |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph using SPARQL.
        api_response = api_instance.get_query_sparql(query=query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->get_query_sparql: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | **str**|  | [optional]

### Return type

[**ApiSparqlResult**](ApiSparqlResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **post_query_knn**
> ApiQueryResult post_query_knn()

Queries the Graph for quads within a kNN-cluster.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_knn_query import ApiKnnQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_knn_query = ApiKnnQuery(
        predicate="predicate_example",
        object=[
            3.14,
        ],
        count=1,
        distance=Distance("COSINE"),
    ) # ApiKnnQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph for quads within a kNN-cluster.
        api_response = api_instance.post_query_knn(api_knn_query=api_knn_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_knn: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_knn_query** | [**ApiKnnQuery**](ApiKnnQuery.md)|  | [optional]

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

# **post_query_object**
> ApiQueryResult post_query_object()

Queries the Graph for quads with a specific object.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_quad_value_query import ApiQuadValueQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_quad_value_query = ApiQuadValueQuery(
        quad_value="quad_value_example",
    ) # ApiQuadValueQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph for quads with a specific object.
        api_response = api_instance.post_query_object(api_quad_value_query=api_quad_value_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_object: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_quad_value_query** | [**ApiQuadValueQuery**](ApiQuadValueQuery.md)|  | [optional]

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

# **post_query_path**
> ApiQueryResult post_query_path()

Queries a path along a set of predicates starting from a set of subjects.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_path_query import ApiPathQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_path_query = ApiPathQuery(
        seeds=[
            "seeds_example",
        ],
        predicates=[
            "predicates_example",
        ],
        max_depth=1,
        reverse=True,
    ) # ApiPathQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries a path along a set of predicates starting from a set of subjects.
        api_response = api_instance.post_query_path(api_path_query=api_path_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_path: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_path_query** | [**ApiPathQuery**](ApiPathQuery.md)|  | [optional]

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

# **post_query_predicate**
> ApiQueryResult post_query_predicate()

Queries the Graph for quads with a specific predicate.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_quad_value_query import ApiQuadValueQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_quad_value_query = ApiQuadValueQuery(
        quad_value="quad_value_example",
    ) # ApiQuadValueQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph for quads with a specific predicate.
        api_response = api_instance.post_query_predicate(api_quad_value_query=api_quad_value_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_predicate: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_quad_value_query** | [**ApiQuadValueQuery**](ApiQuadValueQuery.md)|  | [optional]

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

# **post_query_quads**
> ApiQueryResult post_query_quads()

Queries the Graph.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
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
    api_instance = query_api.QueryApi(api_client)
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
        # Queries the Graph.
        api_response = api_instance.post_query_quads(api_basic_query=api_basic_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_quads: %s\n" % e)
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

# **post_query_relevancefeedback**
> ApiQueryResult post_query_relevancefeedback()

Queries the Graph for quads based on positive and negative examples.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_relevance_feedback_query import ApiRelevanceFeedbackQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_relevance_feedback_query = ApiRelevanceFeedbackQuery(
        positives=[
            "positives_example",
        ],
        negatives=[
            "negatives_example",
        ],
        predicate="predicate_example",
        count=1,
    ) # ApiRelevanceFeedbackQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph for quads based on positive and negative examples.
        api_response = api_instance.post_query_relevancefeedback(api_relevance_feedback_query=api_relevance_feedback_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_relevancefeedback: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_relevance_feedback_query** | [**ApiRelevanceFeedbackQuery**](ApiRelevanceFeedbackQuery.md)|  | [optional]

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

# **post_query_subject**
> ApiQueryResult post_query_subject()

Queries the Graph for quads with a specific subject.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_quad_value_query import ApiQuadValueQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_quad_value_query = ApiQuadValueQuery(
        quad_value="quad_value_example",
    ) # ApiQuadValueQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph for quads with a specific subject.
        api_response = api_instance.post_query_subject(api_quad_value_query=api_quad_value_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_subject: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_quad_value_query** | [**ApiQuadValueQuery**](ApiQuadValueQuery.md)|  | [optional]

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

# **post_query_text**
> ApiQueryResult post_query_text()

Queries the Graph for quads with a specific predicate and an object string that matches a specified filter text.

### Example

```python
import time
import media_graph_construction.openapi.megraspy
from media_graph_construction.openapi.megraspy.api import query_api
from media_graph_construction.openapi.megraspy.model.api_query_result import ApiQueryResult
from media_graph_construction.openapi.megraspy.model.api_text_query import ApiTextQuery
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
    api_instance = query_api.QueryApi(api_client)
    api_text_query = ApiTextQuery(
        predicate="predicate_example",
        filter_text="filter_text_example",
    ) # ApiTextQuery |  (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # Queries the Graph for quads with a specific predicate and an object string that matches a specified filter text.
        api_response = api_instance.post_query_text(api_text_query=api_text_query)
        pprint(api_response)
    except media_graph_construction.openapi.megraspy.ApiException as e:
        print("Exception when calling QueryApi->post_query_text: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **api_text_query** | [**ApiTextQuery**](ApiTextQuery.md)|  | [optional]

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

