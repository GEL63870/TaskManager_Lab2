package pl.com.pwr.lab2.lab2_257160_taskmanager;

public class One_Task {
    private int mImageResource;
    private String mTitle;
    private String mDueDate;
    private String mStatus;
    private String mDescriptions;

    public One_Task(int ImageResource, String Title, String DueDate, String Status) {
        mImageResource = ImageResource;
        mTitle = Title;
        mDueDate = DueDate;
        mStatus = Status;
    }
    public void changeTitle (String text) {
        mTitle = text;
    }

    public void changeStatus (String text) {
        mStatus = text;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getDueDate() {
        return mDueDate;
    }
    public String getStatus() {
        return mStatus;
    }
}

