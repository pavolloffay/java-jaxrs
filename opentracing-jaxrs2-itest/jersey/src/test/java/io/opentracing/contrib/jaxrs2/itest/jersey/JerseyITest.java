package io.opentracing.contrib.jaxrs2.itest.jersey;

import org.eclipse.jetty.servlet.ServletContextHandler;

import io.opentracing.contrib.jaxrs2.itest.common.AbstractServerTest;

/**
 * @author Pavol Loffay
 */
public class JerseyITest extends AbstractServerTest {

    @Override
    public void initServletContext(ServletContextHandler context) {
        JerseyHelper.initServletContext(context);
    }
}
