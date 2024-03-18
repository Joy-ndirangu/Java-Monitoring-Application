package com.example.chemist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.taskViewHolder> {

    Context context;
    ArrayList<TaskModel> arrayList;

    public TaskAdapter(Context context, ArrayList<TaskModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public TaskAdapter.taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_recycler_item,parent, false);
        return new TaskAdapter.taskViewHolder(view);
    }

    //setting data and methods related to clicks of items in the recyclerview


    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.taskViewHolder holder, int position) {

        TaskModel taskModel = arrayList.get(position);
        holder.name_Of_task.setText(taskModel.getTask_Name());
        holder.DueDate.setText(taskModel.getDue_Date());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class taskViewHolder extends RecyclerView.ViewHolder {

        TextView name_Of_task,DueDate;
        Button detailsButton;

        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            name_Of_task = (TextView) itemView.findViewById(R.id.recycler_taskName);
            DueDate = (TextView) itemView.findViewById(R.id.recycler_dueDate);
            detailsButton = (Button) itemView.findViewById(R.id.detailsbtn);

            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
