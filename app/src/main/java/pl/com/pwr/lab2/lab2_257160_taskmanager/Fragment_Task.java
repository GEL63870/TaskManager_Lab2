package pl.com.pwr.lab2.lab2_257160_taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Fragment_Task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail);

        Intent intent = getIntent();
        One_Task one_task = intent.getParcelableExtra("Example Fragment");

        int imageRes = one_task.getImageResource();
        String mTitle = one_task.getTitle();
        String mDue_date = one_task.getDueDate();
        String mDescription = one_task.getDescriptions();
        String mStatus = one_task.getStatus();

        ///ImageView imageView = findViewById(R.id.iconView_detail);
        ///imageView.setImageResource(imageRes);

        TextView title = findViewById(R.id.title_detail);
        title.setText(mTitle);

        TextView due__date = findViewById(R.id.due_date_detail);
        due__date.setText(mDue_date);

        TextView description = findViewById(R.id.description_detail);
        description.setText(mDescription);

        TextView status = findViewById(R.id.status_detail);
        status.setText(mStatus);


    }
}





    //@Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.task_detail, container, false);

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.task_detail);
        //Intent intent = getIntent();

        //ImageView imageview = findViewById(R.id.iconView);
        //TextView title = findViewById(R.id.task_name_txt);
        //TextView due_date = findViewById(R.id.due_date);
        //TextView description_detail = findViewById(R.id.description_detail);
        //TextView status = findViewById(R.id.status);"""
