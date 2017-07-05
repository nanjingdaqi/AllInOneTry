package org.peace.allinone;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daqi on 17-7-5.
 */
public class TestWrapper implements Parcelable {

    public Test t;

    public TestWrapper(Test t) {
        this.t = t;
    }

    public TestWrapper(IBinder binder) {
        this.t = Test.Stub.asInterface(binder);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStrongBinder(t.asBinder());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TestWrapper> CREATOR = new Creator<TestWrapper>() {
        @Override
        public TestWrapper createFromParcel(Parcel in) {
            return new TestWrapper(in.readStrongBinder());
        }

        @Override
        public TestWrapper[] newArray(int size) {
            return new TestWrapper[size];
        }
    };
}
