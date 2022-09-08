package com.example.reunion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import model.Reunion;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Reunion> mMeetings;
    OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);

    }
    public void setOnItemClickListener (OnItemClickListener listener){mlistener=listener;}


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowEmails;
        TextView rowDescription;
        ImageView deleteImage;
        RecyclerViewAdapter adapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowEmails=itemView.findViewById(R.id.emails);
            rowDescription=itemView.findViewById(R.id.description);
            deleteImage=itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mlistener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.onDeleteClick(position);
                        }
                    }

                }
            });
        }
        public ViewHolder linkAdapter(RecyclerViewAdapter adapter){
            this.adapter=adapter;
            return this;

        }

    }

    public RecyclerViewAdapter(ArrayList<Reunion> mMeetings){

        this.mMeetings=mMeetings;

    }



    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_item,parent,false);
        return new ViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Reunion meeting = mMeetings.get(position);

        @SuppressLint("SimpleDateFormat")
        String description = TextUtils.join(" - ", Arrays.asList(
                meeting.getMeetingRoom(),
                meeting.getMeetingTime(),
                meeting.getMeetingSubject()));
        holder.rowDescription.setText(description);

        holder.rowEmails.setText(TextUtils.join(", ",
                meeting.getParticipants()));
    }



    @Override
    public int getItemCount() {
       return  mMeetings.size();
    }
}
