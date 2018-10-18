package com.jinke.basecommon.utils;

/**
 * new in java 1.8
 * @param <T>
 */
public interface Supplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
