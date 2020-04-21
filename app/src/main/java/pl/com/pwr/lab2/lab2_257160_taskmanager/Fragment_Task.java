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
        One_Task one_task = intent.getParcelableExtra("task");

        int imageRes = (one_task != null ? one_task.getImageResource() : 0);
        String mTitle = (one_task != null) ? one_task.getTitle() : null;
        String mDue_date = one_task != null ? one_task.getDueDate() : null;
        String mDescription = (one_task != null) ? one_task.getDescriptions() : null;
        String mStatus = (one_task != null) ? one_task.getStatus() : null;

        ImageView imageView = findViewById(R.id.iconView_detail);
        imageView.setImageResource(imageRes);

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
