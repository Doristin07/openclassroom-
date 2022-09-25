package com.example.reunion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.reunion.Actions.ItemCountAssertion.withItemCount;
import static com.example.reunion.ListMeetingActivity.mMeetings;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.app.LauncherActivity;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.reunion.Actions.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class InstumentedTest {


    public ActivityScenario<ListMeetingActivity> mActivityScenario;

    @Before
    public void setUp() {
        mActivityScenario= ActivityScenario.launch(ListMeetingActivity.class);
        mActivityScenario.moveToState(Lifecycle.State.STARTED);
    }
    @Test
    public void useAppContext() {
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.example.reunion", appContext.getPackageName());
    }
    @Test
    public void mMeetingsList_shouldNotBeEmpty() {
        onView(withId(R.id.meeting_view))
                .check(matches(hasMinimumChildCount(1)));
    }
    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        int size = mMeetings.size();
        onView(withId(R.id.meeting_view)).check(withItemCount(size));
        onView(withId(R.id.meeting_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.meeting_view)).check(withItemCount(size-1));
    }



    @Test
    public void filterByRoomWithSuccess() {
        onView(withId(R.id.room_picker_actions)).perform(click());
        onView(ViewMatchers.withText("By Room")).perform(click());
        onView(withText("Rooom 2")).check(matches(isNotChecked())).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.meeting_view)).check(withItemCount(2));
    }
    @Test
    public void filterByDateWithSuccess() {
        onView(withId(com.google.android.material.R.id.date_picker_actions)).perform(click());
        onView(ViewMatchers.withText("Par Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1970,1, 19));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.meeting_view)).check(withItemCount(1));
    }


    @Test
    public void addNewMeetingWithSuccess() {
        int size = mMeetings.size();
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.topic)).perform(click()).perform(typeText("TestSubject"));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(scrollTo(), PickerActions.setDate(2020,5, 19));
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(scrollTo(), PickerActions.setTime(12, 50));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.chip_group)).check(withItemCount(size+1));
    }

}