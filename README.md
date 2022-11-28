# resteasy-guice-starter
Makes it easy to create a resteasy microservice with guice integration

## Steps to use
1. Clone this repo
```bash
git clone https://github.com/davelpz/resteasy-guice-starter.git
```
2. Build this repo
```
mvn install
```

3. Add this to your maven pom.xml
```xml
<dependency>
  <groupId>com.davelpz</groupId>
  <artifactId>resteasy-guice-starter</artifactId>
  <version>0.1</version>
</dependency>
```
4. Create a Guice Module with a ApplicationModule annotation
```java
import com.davelpz.resteasy.ApplicationModule;
import com.google.inject.AbstractModule;

@ApplicationModule("package.to.scan")
public class RestModule extends AbstractModule {
    public void configure() {
    //guice bindings
    }
}
```
5. Create your Rest Resources as usual, for example
```java
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest/hello")
public class HelloWorld {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World";
    }
}
```
