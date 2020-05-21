# Simple JSON Parser
**JsonParser enables you to extract json by specifying JsonPath in json outut configuration.**


## Getting Started

### Libraries required

**To begin with, put below mentioned libraries on the class-path and you are good to go.**

JsonParser-1.0-SNAPSHOT.jar

accessors-smart-1.2.jar

asm-5.0.4.jar

assertj-core-3.14.0.jar

hamcrest-core-1.3.jar

jackson-annotations-2.10.1.jar

jackson-core-2.10.1.jar

jackson-databind-2.10.1.jar

json-path-2.4.0.jar

json-smart-2.3.jar

slf4j-api-2.0.0-alpha1.jar

slf4j-nop-2.0.0-alpha1.jar

**Code**

```
1. JsonParser jsonParser = new JsonParser("json output configuration");
2. String actual = jsonParser.lookUp("json input string");
```

1. It will initialize JsonParser object with json configuration which will be used further to extract values from json input string.
2. Extract json based on json output configuration done in first step.

**json output configuration**

```
{
  "cost": "$.expensive",
  "titles": [
    {
      "titles" : "$.store.book[*].title"
    },
    {
      "author" : "$.store.book[*].author"
    }
  ],
  "bicycle": {
    "bicycle": "$.store.bicycle"
  },
  "bookCategory": [
    {
      "bookCategory": "$.store.book[*].category"
    }
  ]
}
```

* ```$.store.book[*].category``` as shown in exmaple will be searched in input json string and returne value or object located at that path.

## JsonPath

JsonPath expressions always refer to a JSON structure in the same way as XPath expression are used in combination with an XML document. The "root member object" in JsonPath is always referred to as ```$``` regardless if it is an object or array.

JsonPath expressions can use the dot–notation
```
$.store.book[0].title
```

or the bracket–notation
```
$['store']['book'][0]['title']
```

## Operators
| **Operator** | **Description** |
|--------------------|------------------------|
|``` $```| The root element to query. All path expressions starts with this. |
|```@```|The current node being processed by a filter predicate.|
|```*```|Wildcard. Available anywhere a name or numeric are required.|
|```..```|Deep scan. Available anywhere a name is required.|
|```.<name>```|Dot-notated child|
|```['<name>' (, '<name>')]```|Bracket-notated child or children|
|```[<number> (, <number>)]```|Array index or indexes|
|```[start:end]```|Array slice operator|
|```[?(<expression>)]```|Filter expression. Expression must evaluate to a boolean value.|

### Restrictions on Json Configuration

1. While declaring json output configuration, JsonPath declaration should be valid and specified as string and should not be null or anyother datatype otherwise it will throw **PathConfigurationException**.

3. Specified jsonpath should exist in input json string else will propogate **JSONProcessingException**.
4. Json that is passed should also be valid else it will throw **JSONProcessingException**.

## When to use what?

If only one json output configuration is used to extract data for all input json string then use this.


```
JsonParser jsonParser = new JsonParser("json configuration");
String actual = jsonParser.lookUp("json input string");
```

And use below mentioned method when json configuration keeps changing.

```
JsonParser jsonParser = new JsonParser();
String actual = jsonParser.lookUp(" json output configuration1","json input string", );
String actual1 = jsonParser.lookUp( " json output configuration2","json input string"); 
```

However first elobaration can be used like this as well.

```
JsonParser jsonParser = new JsonParser("json configuration");
String actual =  jsonParser.lookUp("different json output configuration", "json input string"); 
```

## Example

**Sample JSON input string**

```
{
  "store": {
    "book": [
      {
        "category": "reference",
        "author": "Nigel Rees",
        "title": "Sayings of the Century",
        "price": 8.95
      },
      {

        "category": "fiction",
        "author": "Evelyn Waugh",
        "title": "Sword of Honour",
        "price": 12.99
      },
      {
        "category": "fiction",
        "author": "Herman Melville",
        "title": "Moby Dick",
        "isbn": "0-553-21311-3",
        "price": 8.99
      },
      {
        "category": "fiction",
        "author": "J. R. R. Tolkien",
        "title": "The Lord of the Rings",
        "isbn": "0-395-19395-8",
        "price": 22.99
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  },
  "expensive": 10
}
```

**Json output configuration (JsonPath) samples**

| **Json-path sample** | Result |
|-----------------------------|---------|
| ```$``` | Get whole json |
| ```$.expensive ``` | Get expensive field value |
| ```$.store.bicycle```| Get bicycle field value |

```
{
  "cost": "$.expensive",
  "titles": [
    {
      "titles" : "$.store.book[*].title"
    },
    {
      "author" : "$.store.book[*].author"
    }
  ],
  "bicycle": {
    "bicycle": "$.store.bicycle"
  },
  "bookCategory": [
    {
      "bookCategory": "$.store.book[*].category"
    }
  ]
}
```

By juxtaposing the output json configuration with resultant json string, all JsonPath values are replaced with objects that are represented by that JsonPath string.

**Result**

```
{
  "cost" : 10,
  "titles" : [ {
    "titles" : [ "Sayings of the Century", "Sword of Honour", "Moby Dick", "The Lord of the Rings" ]
  }, {
    "author" : [ "Nigel Rees", "Evelyn Waugh", "Herman Melville", "J. R. R. Tolkien" ]
  } ],
  "bicycle" : {
    "bicycle" : {
      "color" : "red",
      "price" : 19.95
    }
  },
  "bookCategory" : [ {
    "bookCategory" : [ "reference", "fiction", "fiction", "fiction" ]
  } ]
}
```
