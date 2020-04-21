package pl.com.pwr.lab2.lab2_257160_taskmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.MyViewHolder> {
    private ArrayList<One_Task> mTodo_Task;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mDueDate;
        public TextView mStatus;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iconView);
            mTitle = itemView.findViewById(R.id.task_name_txt);
            mDueDate = itemView.findViewById(R.id.due_date);
            mStatus = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);

                        }
                    }
                }
            });
        }
    }
    public Adapter(ArrayList<One_Task> todo_tasks) {
        mTodo_Task = todo_tasks;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_task_row, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        One_Task currentTask = mTodo_Task.get(position);
        holder.mImageView.setImageResource(currentTask.getImageResource());
        holder.mTitle.setText(currentTask.getTitle());
        holder.mDueDate.setText(currentTask.getDueDate());
        holder.mStatus.setText(currentTask.getStatus());
    }

    @Override
    public int getItemCount() {
        return mTodo_Task.size();
    }
}

