package com.example.reunion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Reunion;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Reunion> mMeetings;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowEmails;
        TextView rowDescription;
        private RecyclerViewAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowEmails=itemView.findViewById(R.id.emails);
            rowDescription=itemView.findViewById(R.id.description);

        }
        public ViewHolder linkAdapter(RecyclerViewAdapter adapter){
            this.adapter=adapter;
            return this;

        }

    }

    public RecyclerViewAdapter(Context context, ArrayList<Reunion> mMeetings){
        this.context=context;
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

        holder.rowEmails.setText("Perfect");
    }

    public void addItem(int position, Reunion stud)
    {
        mMeetings.add(position, stud);
        notifyItemInserted(position);
    }
    public void updateList(List<Reunion>mMeetings){
        mMeetings=mMeetings;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       return  mMeetings.size();
    }
}
