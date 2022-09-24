package com.example.reunion;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import model.Reunion;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {

    ItemAdapter context;
    ArrayList<Reunion> mfilteredMeetings;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowEmails;
        TextView rowDescription;
        ImageButton deleteButton;
        ItemAdapter adapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowEmails=itemView.findViewById(R.id.emails);
            rowDescription=itemView.findViewById(R.id.description);
            deleteButton=itemView.findViewById(R.id.delete);
            deleteButton.setOnClickListener(view -> {
                adapter.mfilteredMeetings.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });


        }
        public ViewHolder linkAdapter(ItemAdapter adapter){
            this.adapter=adapter;
            return this;

        }

    }

    public ItemAdapter(ArrayList<Reunion> mMeetings){
        this.mfilteredMeetings=mMeetings;

    }


    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_item,parent,false);
        return new ViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Reunion meeting = mfilteredMeetings.get(position);

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
    public int getItemCount() {return  mfilteredMeetings.size();
    }




}
