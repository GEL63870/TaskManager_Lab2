package pl.com.pwr.lab2.lab2_257160_taskmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Main_Activity extends AppCompatActivity {
    private ArrayList<One_Task> one_task;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        createOne_Task();
        buildRecyclerView();

        addButton = findViewById(R.id.new_task_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void add_task(int position){
        one_task.add(position, new One_Task(R.drawable.icon_todo, "New title", "NewDueDate", "Not Done"));
        mAdapter.notifyItemInserted(position);

    };

    public void remove_task(int position) {
        mAdapter.notifyItemChanged(position);
    }

    public void changeTask(int position,  String text) {
        one_task.get(position).changeTitle(text);
        mAdapter.notifyItemChanged(position);
    }

    public void createOne_Task() {
        one_task = new ArrayList<>();
        one_task.add(new One_Task(R.drawable.icon_todo, "My Title", "MyDueDate", "Done / Not Done" ));
        one_task.add(new One_Task(R.drawable.icon_email, "My Title", "MyDueDate", "Done / Not Done" ));
        one_task.add(new One_Task(R.drawable.icon_phone, "My Title", "MyDueDate", "Done / Not Done" ));
        one_task.add(new One_Task(R.drawable.icon_meeting, "My Title", "MyDueDate", "Done / Not Done" ));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager (mLayoutManager);

        mAdapter = new Adapter(one_task);
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(mRecyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeTask(position, "Clicked");
            }
        });
    }

    // Part' in order to Swipe to Right to Delete

    ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            one_task.remove(viewHolder.getAdapterPosition());
            mAdapter.notifyDataSetChanged();
        }
    };

    // Part' in order to Swipe to LEFT to mark as Done

    ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            one_task.get(viewHolder.getAdapterPosition()).changeStatus("Done");
            mAdapter.notifyDataSetChanged();
        }
    };

}
