package com.nyu.youtoo.youtoo.models;

/**
 * Created by Pooh on 09-04-2015.
 */

import android.os.Parcel;
import android.os.Parcelable;


public class Person implements Parcelable {

    public String username;


    public String current_location="";


    /**
     * Parcelable creator. Do not modify this function.
     */
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel p) {
            return new Person(p);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    /**
     * Create a Person model object from a Parcel. This
     * function is called via the Parcelable creator.
     *
     * @param p The Parcel used to populate the
     * Model fields.
     */
    public Person(Parcel p) {

        // TODO - fill in here
        this.username=p.readString();

        this.current_location=p.readString();


    }

    /**
     * Create a Person model object from arguments
     *
     * @param name Add arbitrary number of arguments to
     * instantiate Person class based on member variables.
     */
    public Person(String name) {

        // TODO - fill in here, please note you must have more arguments here
        this.username=name;
        this.current_location="Location in map";


    }

    /**
     * Serialize Person object by using writeToParcel.
     * This function is automatically called by the
     * system when the object is serialized.
     *
     * @param dest Parcel object that gets written on
     * serialization. Use functions to write out the
     * object stored via your member variables.
     *
     * @param flags Additional flags about how the object
     * should be written. May be 0 or PARCELABLE_WRITE_RETURN_VALUE.
     * In our case, you should be just passing 0.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        // TODO - fill in here
        dest.writeString(username);


        dest.writeString(current_location);

    }


    @Override
    public int describeContents() {
        // Do not implement!
        return 0;
    }
}
