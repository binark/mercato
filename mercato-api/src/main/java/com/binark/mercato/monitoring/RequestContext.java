package com.binark.mercato.monitoring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * The request context used for logging tracing
 */
@Component
@RequestScope
@Getter
@Setter
public class RequestContext {
    private String requestId;
}
