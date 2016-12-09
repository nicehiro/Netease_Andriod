package org.nicehiro.servicetest2.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hiro on 16-12-9.
 */

public class ParcelableObject implements Parcelable {
    private int mA;
    private String mB;
    private User mUser;

    public static final Parcelable.Creator<ParcelableObject> CREATOR = new Parcelable.Creator<ParcelableObject>() {
        @Override
        public ParcelableObject createFromParcel(Parcel parcel) {
            return new ParcelableObject(parcel);
        }

        @Override
        public ParcelableObject[] newArray(int i) {
            return new ParcelableObject[i];
        }
    };

    public ParcelableObject(Parcel in) {
        mA = in.readInt();
        mB = in.readString();
        mUser = (User) in.readSerializable();
    }

    public ParcelableObject(int a, String b, User user) {
        this.mA = a;
        this.mB = b;
        this.mUser = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mA);
        parcel.writeString(mB);
        parcel.writeSerializable(mUser);
    }

    public int getmA() {
        return mA;
    }

    public String getmB() {
        return mB;
    }

    public User getmUser() {
        return mUser;
    }
}
