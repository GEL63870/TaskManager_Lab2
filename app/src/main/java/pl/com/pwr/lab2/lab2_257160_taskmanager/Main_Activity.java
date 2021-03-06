package pl.com.pwr.lab2.lab2_257160_taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Main_Activity extends AppCompatActivity implements Adapter.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private ArrayList<One_Task> one_task;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final String[] TASK = new String[] {
            "Todo","Email", "Meeting", "Phone"};
    private Button addButton;
    private Spinner select_task;
    private ArrayAdapter<CharSequence> adapter;
    private EditText new_due_date;
    private EditText new_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        createOne_Task();
        buildRecyclerView();

        select_task = findViewById(R.id.type_task);
        adapter = ArrayAdapter.createFromResource(this, R.array.task_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_task.setAdapter(adapter);
        select_task.setOnItemSelectedListener(this);

        new_due_date = findViewById(R.id.enter_due_date);
        new_description = findViewById(R.id.enter_description);

        addButton = findViewById(R.id.new_task_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = select_task.getItemAtPosition(select_task.getSelectedItemPosition()).toString();
                String due_date = new_due_date.getText().toString();
                String description = new_description.getText().toString();
                int position = 0;

                // We select the correct logo for the task after getting the type of task the user wanted to create
                if (title.equals("Todo")){
                    add_task_todo(position, title, due_date, description);
                }
                else if (title.equals("Email")) {
                    add_task_email(position, title, due_date, description);
                }
                else if (title.equals("Phone")) {
                    add_task_phone(position, title, due_date, description);
                }
                else if (title.equals("Meeting")) {
                    add_task_meeting(position, title, due_date, description);
                }
            }
        });
    }


    public void createOne_Task() {
        one_task = new ArrayList<>();
        one_task.add(new One_Task(R.drawable.icon_todo, "Todo Example", "23/04/2020", "You read the description of a todo task", "Not Done"));
        one_task.add(new One_Task(R.drawable.icon_email, "Email Example", "22/03/2020", "You read the description of a email task", "Not Done"));
        one_task.add(new One_Task(R.drawable.icon_phone, "Phone Example", "21/03/2020", "You read the description of a phone task", "Not Done"));
        one_task.add(new One_Task(R.drawable.icon_meeting, "Meeting Example", "20/03/2020", "You read the description of a meeting task", "Not Done"));
    }

    public void add_task_todo(int position, String title, String dueDate, String description) {
        one_task.add(position, new One_Task(R.drawable.icon_todo, title, dueDate, description, "Not Done"));
        mAdapter.notifyItemInserted(position);
    }

    public void add_task_email(int position, String title, String dueDate, String description) {
        one_task.add(position, new One_Task(R.drawable.icon_email, title, dueDate, description, "Not Done"));
        mAdapter.notifyItemInserted(position);
    }

    public void add_task_phone(int position, String title, String dueDate, String description) {
        one_task.add(position, new One_Task(R.drawable.icon_phone, title, dueDate, description, "Not Done"));
        mAdapter.notifyItemInserted(position);
    }

    public void add_task_meeting(int position, String title, String dueDate, String description) {
        one_task.add(position, new One_Task(R.drawable.icon_meeting, title, dueDate, description, "Not Done"));
        mAdapter.notifyItemInserted(position);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(one_task);
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(mRecyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Main_Activity.this, Fragment_Task.class);
                intent.putExtra("task", one_task.get(position));

                startActivity(intent);
            }

            @Override
            public void onDetailClick(int position) {
                Intent intent = new Intent(Main_Activity.this, Fragment_Task.class);
                intent.putExtra("task", one_task.get(position));

                startActivity(intent);
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
            if (one_task.get(viewHolder.getAdapterPosition()).getStatus().equals("Not Done")){
                one_task.get(viewHolder.getAdapterPosition()).changeStatus("Done");
                mAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
            else {
                one_task.get(viewHolder.getAdapterPosition()).changeStatus("Not Done");
                mAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        }
    };

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Main_Activity.this, Fragment_Task.class);
        intent.putExtra("task", one_task.get(position));

        startActivity(intent);
    }

    @Override
    public void onDetailClick(int position) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Type" + choice + "selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
