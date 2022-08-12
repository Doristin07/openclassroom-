package com.example.reunion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reunion.R;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.Collections;
import java.util.List;

import model.Reunion;

public class AddMeetingActivity extends AppCompatActivity {
    public static final String BUNDLE_EXTRA_SUBJECT = "BUNDLE_EXTRA_SUBJECT";
    public static final String BUNDLE_EXTRA_PLACE = "BUNDLE_EXTRA_PLACE";
    public static final String BUNDLE_EXTRA_PARTICIPANTS = "BUNDLE_EXTRA_PARTICIPANTS";

    private EditText msubjectEditText;
    private EditText  mplaceEditText;
    private EditText mparticipantsEditText;
    private Button msaveButton;
    private static final int REQUEST_CODE_ADDMEETING=50;
    public String mSubject;
    public String mplace;
    public String mparticipants;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        msubjectEditText=findViewById(R.id.Meeting_Subject);
        mplaceEditText=findViewById(R.id.Meeting_Place);
        mparticipantsEditText=findViewById(R.id.Meeting_Participant);
        msaveButton=findViewById(R.id.Save_Button);

        msaveButton.setEnabled(false);
        msubjectEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                msaveButton.setEnabled(!s.toString().isEmpty());
            }
        });


        mparticipantsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                msaveButton.setEnabled(!s.toString().isEmpty());
            }
        });


        mplaceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                msaveButton.setEnabled(!s.toString().isEmpty());
            }
        });

        msaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mplace=mplaceEditText.getText().toString();
                mparticipants= mparticipantsEditText.getText().toString();

                Intent intent=new Intent();
                intent.putExtra(BUNDLE_EXTRA_SUBJECT, mSubject);
                intent.putExtra(BUNDLE_EXTRA_PLACE, mplace);
                intent.putExtra(BUNDLE_EXTRA_PARTICIPANTS, String.valueOf(mparticipants));
                setResult(Activity.RESULT_OK, intent);



                finish();


            }
        });





        }

}









