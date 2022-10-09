package com.example.reunion;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Reunion;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {


    ArrayList<Reunion> mMeetings;

    public RecyclerViewAdapter(ArrayList<Reunion> mMeetings){
        this.mMeetings=mMeetings;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowEmails;
        TextView rowDescription;
        ImageButton deleteButton;
        public final ImageView mCircleView;
        public RecyclerViewAdapter adapter;
        public int position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ListMeetingActivity myActivity=new ListMeetingActivity();
            rowEmails=itemView.findViewById(R.id.emails);
            mCircleView=itemView.findViewById(R.id.circle_item);
            rowDescription=itemView.findViewById(R.id.description);
            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
                myActivity.deleteMeeting(adapter,getAdapterPosition());
            });


        }
        public ViewHolder linkAdapter(RecyclerViewAdapter adapter){
            this.adapter=adapter;
            return this;

        }

    }




    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_item,parent,false);
        return new ViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Reunion meeting = mMeetings.get(position);

        @SuppressLint("SimpleDateFormat")
        String description = TextUtils.join(" - ", Arrays.asList(
                meeting.getMeetingRoom(),
                meeting.getMeetingTime(),
                meeting.getMeetingSubject()));
        holder.rowDescription.setText(description);

        holder.rowEmails.setText(TextUtils.join(", ",
                meeting.getParticipants()));
        ((GradientDrawable)holder.mCircleView.getBackground()).setColor(meeting.getColor());
    }


    @Override
    public int getItemCount() {return  mMeetings.size();
    }




}