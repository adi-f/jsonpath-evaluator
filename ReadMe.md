# JSON Path Evaluator

Simple evaluator for JSON Path expressions (Java Swing GUI).
Intention of this application: Different JSON Path implementations have a slight different behavior.
This Java tool supports different implementation, so you can test your JSON Path expression with the implementation you use in your project.

## Features
* Compiles to an executable JAR
* Support for diffrent JSON Path implementations. Current support for:
  * [Jayway JsonPath](https://github.com/json-path/JsonPath) (wide used; e.g. by WireMock)
  * [JsonSurfer](https://github.com/wanglingsong/JsonSurfer)
* Simple to extend

## How to extend
1. Add new implementation as Maven dependency
2. Implement the _JsonPathEvaluator_-Interface
3. Add it to the _jsonPathEvaluators_-list in _Startup.main(...)_