package com.example.reunion;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.reunion.Actions.ItemCountAssertion.withItemCount;
import static com.example.reunion.ListMeetingActivity.sMeetings;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.reunion.Actions.DeleteViewAction;


public class IntegrationTests {


    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class);
    @Before
    public void setUp() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());}

    @Test
    public void useAppContext() {
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.example.reunion", appContext.getPackageName());
    }

    @Test
    public void mMeetingsList_shouldNotBeEmpty() {
        onView(withId(R.id.meeting_view))
                .check(matches(hasMinimumChildCount(0)));
    }

    @Test
    public void addNewMeeting() {
        int size = sMeetings.size();
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.room)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Room 7")))
                .inRoot(isPlatformPopup())
                .perform(click());
        onView(withId(R.id.topic)).perform(click()).perform(typeText("TestSubject"));
        onView(withId(R.id.time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform( PickerActions.setTime(12, 50));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022,12, 7));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.emails)).perform(click()).perform(typeText("sam@gmail.com"), pressImeActionButton(),closeSoftKeyboard());
        onView(withText("Save")).perform(click());
        onView(withId(R.id.meeting_view)).check(withItemCount(size+1));
    }

    @Test
    public void RemoveMeeting() {
        int size = sMeetings.size();
        addNewMeeting();
        onView(withId(R.id.meeting_view)).check(withItemCount(size+1));
        onView(withId(R.id.meeting_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.meeting_view)).check(withItemCount(0));
    }



    @Test
    public void filterByRoom() {
        addNewMeeting();
        onView(ViewMatchers.withId(R.id.filter))
                .perform(click());
        onView(ViewMatchers.withText("Room")).perform(click());
        onView(ViewMatchers.withText("Room 7")).perform(click());
        onView(withId(R.id.meeting_view)).check(withItemCount(1));
    }
    @Test
    public void filterByDate() {
        addNewMeeting();
        onView(ViewMatchers.withId(R.id.filter))
                .perform(click());
        onView(ViewMatchers.withText("Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022,12, 7));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.meeting_view)).check(withItemCount(1));
    }




}