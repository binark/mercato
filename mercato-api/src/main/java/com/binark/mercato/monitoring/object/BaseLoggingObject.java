package com.binark.mercato.monitoring.object;

/**
 * Base Logging object. This is the base model of a logging object
 *
 * @param step      The execution step
 * @param requestId The request id
 * @param key       The data key
 * @param data      The data value
 */
public record BaseLoggingObject(String step, String requestId, String key, Object data) {
}
