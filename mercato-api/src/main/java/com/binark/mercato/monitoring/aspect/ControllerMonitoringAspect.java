package com.binark.mercato.monitoring.aspect;

import com.binark.mercato.domain.dto.output.RequestError;
import com.binark.mercato.monitoring.RequestContext;
import com.binark.mercato.monitoring.object.BaseLoggingObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Slf4j
@RequiredArgsConstructor
@Component
public class ControllerMonitoringAspect {

    private final ObjectMapper mapper;
    private final RequestContext context;

    /**
     * Write log before any controller execution.
     * Log method name and parameters if they exist
     *
     * @param joinPoint The join point
     */
    @Before("controllerPointCut()")
    public void beforeControllerExecution(JoinPoint joinPoint) {
        setContextId();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String packageName = methodSignature.getDeclaringTypeName();
        String[] splitPackage = packageName.split("\\.");
        String className = splitPackage[splitPackage.length - 1];
        BaseLoggingObject loggingMethodNameObject = new BaseLoggingObject("REQUEST", context.getRequestId(), "METHOD " +
                "NAME", className.concat("." + methodSignature.getName()));
        try {
            log.info(mapper.writeValueAsString(loggingMethodNameObject));
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                BaseLoggingObject loggingParametersObject = new BaseLoggingObject("REQUEST", context.getRequestId(),
                                                                                  "PARAMETERS", args);
                log.info(mapper.writeValueAsString(loggingParametersObject));
            }
        } catch (JsonProcessingException e) {
            log.error("unexpected logging error:  ", e);
        }
    }

    /**
     * Write log when controller returns value if the returned value is not null
     *
     * @param joinPoint     The joint point
     * @param returnedValue The returned value
     */
    @AfterReturning(value = "controllerPointCut()", returning = "returnedValue")
    public void afterControllerExecution(JoinPoint joinPoint, Object returnedValue) {
        if (returnedValue != null) {
            try {
                BaseLoggingObject loggingResponseObject = new BaseLoggingObject("RESPONSE", context.getRequestId(),
                                                                                "RESPONSE", returnedValue);
                log.info(mapper.writeValueAsString(loggingResponseObject));
            } catch (JsonProcessingException e) {
                log.info("unexpected logging error", e);
            }
        }
    }

    /**
     * Write log after exception handler execution
     *
     * @param joinPoint The join point
     * @param errorData The error data
     */
    @AfterReturning(value = "exceptionHandlerPointCut()", returning = "errorData")
    public void afterHandlingExecution(JoinPoint joinPoint, RequestError errorData) {
        if (context.getRequestId() == null) {
            setContextId();
        }
        if (errorData != null) {
            try {
                BaseLoggingObject loggingResponseObject = new BaseLoggingObject("ERROR", context.getRequestId(),
                                                                                "DATA", errorData);
                log.info(mapper.writeValueAsString(loggingResponseObject));
            } catch (JsonProcessingException e) {
                log.info("unexpected logging error", e);
            }
        }
    }

    private void setContextId() {
        context.setRequestId(UUID.randomUUID().toString());
    }

    @Pointcut(" execution(* com.binark.mercato.infrastructure.controller.*.*(..))")
    public void controllerPointCut() {
    }

    @Pointcut(" execution(* com.binark.mercato.infrastructure.controller.handler.*.*(..))")
    public void exceptionHandlerPointCut() {
    }
}
