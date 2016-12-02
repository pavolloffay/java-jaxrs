package sk.loffay.opentracing.jax.rs.client;

/**
 * @author Pavol Loffay
 */
public class TracingProperties {

    private TracingProperties() {}

    /**
     * Denotes a "parent" span ({@link io.opentracing.References#CHILD_OF}).
     * If it is not specified a new trace will be started.
     * Set on {@link javax.ws.rs.client.Client#property(String, Object)}
     */
    public static final String PARENT_SPAN = "parentSpan";

    /**
     * Indicates whether request should be traced or not. If it is not
     * present and client is correctly configured requests will be traced.
     * Value should be true or false (trace disabled/enabled).
     * Set on {@link javax.ws.rs.client.Client#property(String, Object)}
     */
    public static final String TRACING_DISABLED = "tracingDisabled";
}
