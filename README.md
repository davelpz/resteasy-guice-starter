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
4. Create a Guice Module as usual but add a ApplicationModule annotation. The argument to @ApplicationModule
is the java package to scan for Rest resource classes.
```java
package com.davelpz.rweb;

import com.davelpz.resteasy.ApplicationModule;
import com.davelpz.rweb.impl.DBUserRepository;
import com.google.inject.AbstractModule;

@ApplicationModule("com.davelpz.rweb")
public class RestModule extends AbstractModule {
    public void configure() {
        binder().bind(UserRepository.class).to(DBUserRepository.class);
    }
}
```
5. Create your Rest Resources as usual, for example
```java
package com.davelpz.rweb;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest/user")
public class UserResource {

    private final UserRepository userRepository;

    @Inject
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("id") int id) {
        return userRepository.findUser(id);
    }
}
```
6. Build and deploy your web app.
