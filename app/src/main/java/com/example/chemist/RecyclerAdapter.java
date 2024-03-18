package com.example.chemist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder>{

    Context context;
    ArrayList<RecyclerModel> arrayList;

    public RecyclerAdapter(Context context, ArrayList<RecyclerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    //inflating cardlayout item of recyclerview
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent, false);
        return new myViewHolder(view);
    }

   //setting data and methods related to clicks of items in the recyclerview
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        RecyclerModel recyclerModel = arrayList.get(position);
        holder.name.setText(recyclerModel.getEmployee_name());
        holder.email.setText(recyclerModel.getEmployee_email());
        holder.branch.setText(recyclerModel.getEmployee_branch());


        Glide.with(holder.img.getContext())
                .load(recyclerModel.getEmployee_url())
                .placeholder(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.appcheck.interop.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



    }

    //returning the length of the recyclerview
    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name, email, branch;

        //viewholder class for the cardlayout item of the recyclerview
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img  = (CircleImageView) itemView.findViewById(R.id.recycler_img);
            name = (TextView) itemView.findViewById(R.id.recyclername);
            email = (TextView) itemView.findViewById(R.id.recycleremail);
            branch = (TextView) itemView.findViewById(R.id.recyclerbranch);

        }
    }
}
