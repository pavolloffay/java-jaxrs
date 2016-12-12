package io.opentracing.contrib.jaxrs.itest.cxf;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.opentracing.contrib.jarxrs.itest.common.AbstractJettyTest;
import io.opentracing.contrib.jarxrs.itest.common.rest.InstrumentedRestApplication;

/**
 * @author Pavol Loffay
 */
public class ApacheCXFITest extends AbstractJettyTest {

    @Override
    protected void initServletContext(ServletContextHandler context) {
        ServletHolder jerseyServlet = context.addServlet(
                org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "javax.ws.rs.Application", InstrumentedRestApplication.class.getCanonicalName());
    }
}
