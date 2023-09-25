package com.igm.igmtest.endpoint_3b;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
public @interface ExponentialBackoff {
    int maxAttempts() default 3;
    long delay() default 1000;
}
