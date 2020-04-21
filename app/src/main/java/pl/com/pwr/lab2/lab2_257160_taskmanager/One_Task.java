package pl.com.pwr.lab2.lab2_257160_taskmanager;


import android.os.Parcel;
import android.os.Parcelable;

public class One_Task implements Parcelable {
    private int mImageResource;
    private String mTitle;
    private String mDueDate;
    private String mStatus;
    private String mDescriptions;

    public One_Task(int ImageResource, String Title, String DueDate, String Descriptions, String Status) {
        mImageResource = ImageResource;
        mTitle = Title;
        mDueDate = DueDate;
        mDescriptions = Descriptions;
        mStatus = Status;
    }

    protected One_Task(Parcel in) {
        mImageResource = in.readInt();
        mTitle = in.readString();
        mDueDate = in.readString();
        mStatus = in.readString();
        mDescriptions = in.readString();
    }

    public static final Creator<One_Task> CREATOR = new Creator<One_Task>() {
        @Override
        public One_Task createFromParcel(Parcel in) {
            return new One_Task(in);
        }

        @Override
        public One_Task[] newArray(int size) {
            return new One_Task[size];
        }
    };

    public void changeTitle (String text) {
        mTitle = text;
    }

    public void changeStatus (String text) {
        mStatus = text;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getTitle() { return mTitle;
    }
    public String getDueDate() {
        return mDueDate;
    }
    public String getDescriptions() {
        return mDescriptions;
    }
    public String getStatus() {
        return mStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mTitle);
        dest.writeString(mDueDate);
        dest.writeString(mStatus);
        dest.writeString(mDescriptions);
    }
}

