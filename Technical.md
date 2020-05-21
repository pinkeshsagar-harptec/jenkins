# Simple Json Parser
-
Json Parser parses the json to desired outcome. Here user passes Json output string contating JsonPath. What this parser does is simply replacing JsonPath with resulted JsonPath value which can be any object.

## Description
* This Parser is developed with the help of these two libraries, Jackson DataBind  and JsonPath by jayway.
* User has to pass json output configuration and json input string that is related to json output configuratio.
* In the beginning, user passed Json output configuration string is converted to JsonNode and this JsonNode is iterated and being validated for correct JsonPath expression which is passed as string.
* Then with the use of JsonPath library, values of JsonPath expression is extracted from json input string that is related to that Json output configuration.

## Diagram

### Use case

**Overall Usecase**


**Json output configuration validation**


**Json Gererator**


### Sequence

