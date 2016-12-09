// Callback.aidl
package org.nicehiro.servicetest2.aidl;

// Declare any non-default types here with import statements

interface Callback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    oneway void callback(int what);
}
