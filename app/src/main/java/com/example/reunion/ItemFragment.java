package com.example.reunion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import model.Reunion;
import static com.example.reunion.ListMeetingActivity.filteredlist;
import static com.example.reunion.ListMeetingFragment.mMeetings;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ItemFragment extends Fragment {

    public static ArrayList<Reunion> mFilteredMeetings;
    public List<String> ty;
    public RecyclerView mRecyclerView;
    public ItemAdapter mAdapter;
    private FloatingActionButton mAddButton2;


    public ItemFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFilteredMeetings=new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mFilteredMeetings=filteredlist;

        mAddButton2=view.findViewById(R.id.add_button2);
        mRecyclerView=view.findViewById(R.id.meeting_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mAdapter=new ItemAdapter(mFilteredMeetings);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        mAddButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFilteredMeetings=mMeetings;
                Context context= getContext();
                View view2=getLayoutInflater().inflate(R.layout.dialog_design,null

                );
                String[] roomNumbers=getResources().getStringArray(R.array.room_numbers);
                AddMeetingDialog.showDialog(view2,context,roomNumbers);


            }

        });

        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}