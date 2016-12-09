// TestServiceCall.aidl
package org.nicehiro.servicetest2.aidl;

import org.nicehiro.servicetest2.aidl.Callback;
import org.nicehiro.servicetest2.aidl.ParcelableObject;
// Declare any non-default types here with import statements

interface TestServiceCall {
    int add(int a, int b);
    void testOut(inout byte[] arg1, out byte[] arg2, in byte[] arg3, in ParcelableObject obj);
    oneway void setCallback(Callback callback);
}
