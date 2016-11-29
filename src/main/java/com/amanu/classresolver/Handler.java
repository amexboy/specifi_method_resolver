package com.amanu.classresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation shows that the annotated methid is a handler for specific class method resolution
 * This is to allow the handler class have other methods
 *
 * @author by Amanu on November 12, 2016.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
}
