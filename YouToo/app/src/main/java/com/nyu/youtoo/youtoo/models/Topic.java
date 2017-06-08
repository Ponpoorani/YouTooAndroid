package com.nyu.youtoo.youtoo.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.String;
/**
 * Created by Rakshith on 3/28/2015
 */

public class Topic implements Parcelable {

    public String topicName;
    public String topicDescription;

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public String getTopicName() {
        return topicName;
    }


    /**
     * Parcelable creator. Do not modify this function.
     */
    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {
        public Topic createFromParcel(Parcel p) {
            return new Topic(p);
        }
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    /**
     * Create a Person model object from a Parcel. This
     * function is called via the Parcelable creator.
     *
     * @param p The Parcel used to populate the
     * Model fields.
     */
    public Topic(Parcel p) {

        // TODO - fill in here
        this.topicName=p.readString();

        this.topicDescription=p.readString();


    }

    public Topic()
    {
        this.topicName = "";
        this.topicDescription = "";
    }

    /**
     * Create a Person model object from arguments
     *
     * @param topicName Add arbitrary number of arguments to
     * instantiate Person class based on member variables.
     */
    public Topic(String topicName,String topicDescription) {

        // TODO - fill in here, please note you must have more arguments here
        this.topicName=topicName;
        this.topicDescription=topicDescription;


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
        dest.writeString(topicName);


        dest.writeString(topicDescription);

    }


    @Override
    public int describeContents() {
        // Do not implement!
        return 0;
    }
}
