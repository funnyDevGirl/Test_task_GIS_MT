## <a href="https://www.oracle.com/java/" target="_blank" rel="noreferrer"><img src="https://raw.githubusercontent.com/danielcranney/readme-generator/main/public/icons/skills/java-colored.svg" width="36" height="36" alt="Java" /></a> Task:
### It is necessary to implement a class in Java (version 11+) to work with the Fair Sign API. The class must be thread-safe and support a limit on the number of API requests. The limit is specified in the constructor in the form of the number of requests in a certain time interval.

### For example:
```public CrptApi(TimeUnit TimeUnit, int requestLimit)```
### TimeUnit – specifies the time interval – second, minute, etc,
### requestLimit is a positive value that determines the maximum number of requests in this time interval.
### If the limit is exceeded, the request must be blocked in order not to exceed the maximum number of API requests and continue execution when the limit is not exceeded.

### The only method that needs to be implemented is the creation of a document for the entry into circulation of goods produced in the Russian Federation. The document and signature must be passed to the method as a Java object and string, respectively.
### The implementation should be as convenient as possible for further expansion of the functionality.

### The solution should be designed as a single file CrptApi.java . All additional classes that are used must be internal.
