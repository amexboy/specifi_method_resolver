package com.amanu.classresolver;

/**
 * @author by Amanu on November 12, 2016.
 */
public interface Resolver<T, R> {

    R resolve(T t);


}
