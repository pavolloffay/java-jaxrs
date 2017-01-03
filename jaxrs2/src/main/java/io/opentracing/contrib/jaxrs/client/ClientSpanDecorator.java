package io.opentracing.contrib.jaxrs.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;

import io.opentracing.Span;
import io.opentracing.contrib.jaxrs.SpanDecorator;
import io.opentracing.contrib.jaxrs.internal.URIUtils;
import io.opentracing.tag.Tags;

/**
 * @author Pavol Loffay
 */
public interface ClientSpanDecorator extends SpanDecorator<ClientRequestContext, ClientResponseContext> {

    /**
     * Adds standard tags: {@link io.opentracing.tag.Tags#SPAN_KIND},
     * {@link io.opentracing.tag.Tags#PEER_HOSTNAME}, {@link io.opentracing.tag.Tags#PEER_PORT},
     * {@link io.opentracing.tag.Tags#HTTP_METHOD}, {@link io.opentracing.tag.Tags#HTTP_URL} and
     * {@link io.opentracing.tag.Tags#HTTP_STATUS}
     */
    ClientSpanDecorator STANDARD_TAGS = new ClientSpanDecorator() {
        @Override
        public void decorateRequest(ClientRequestContext requestContext, Span span) {
            Tags.SPAN_KIND.set(span, Tags.SPAN_KIND_CLIENT);
            Tags.PEER_HOSTNAME.set(span, requestContext.getUri().getHost());
            Tags.PEER_PORT.set(span, (short)requestContext.getUri().getPort());

            Tags.HTTP_METHOD.set(span, requestContext.getMethod());

            String url = URIUtils.url(requestContext.getUri());
            if (url != null) {
                Tags.HTTP_URL.set(span, url);
            }
        }

        @Override
        public void decorateResponse(ClientResponseContext responseContext, Span span) {
            Tags.HTTP_STATUS.set(span, responseContext.getStatus());
        }
    };

    /**
     * As operation name provides HTTP method e.g. GET, POST..
     */
    ClientSpanDecorator HTTP_METHOD_OPERATION_NAME = new ClientSpanDecorator() {
        @Override
        public void decorateRequest(ClientRequestContext clientRequestContext, Span span) {
            span.setOperationName(clientRequestContext.getMethod());
        }

        @Override
        public void decorateResponse(ClientResponseContext response, Span span) {
        }
    };

    /**
     * As operation name provides HTTP path. If there are path parameters used in URL then
     * spans for the same requests would have different operation names, therefore use carefully.
     */
    ClientSpanDecorator HTTP_PATH_OPERATION_NAME = new ClientSpanDecorator() {
        @Override
        public void decorateRequest(ClientRequestContext clientRequestContext, Span span) {
            span.setOperationName(URIUtils.path(clientRequestContext.getUri()));
        }

        @Override
        public void decorateResponse(ClientResponseContext response, Span span) {
        }
    };
}
