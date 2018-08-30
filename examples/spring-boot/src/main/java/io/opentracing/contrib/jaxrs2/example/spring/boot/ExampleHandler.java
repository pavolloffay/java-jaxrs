package io.opentracing.contrib.jaxrs2.example.spring.boot;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Pavol Loffay
 */

@Path(value = "foo/bar")
public class ExampleHandler {

  @GET
  @Path(value = "")
  public String myMethod() {
    return "foo";
  }

}
