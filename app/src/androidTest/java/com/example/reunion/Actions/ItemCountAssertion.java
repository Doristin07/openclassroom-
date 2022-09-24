package com.example.reunion.Actions;

import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

    public class ItemCountAssertion implements ViewAssertion {
        private final Matcher<Integer> matcher;

        public static ViewAssertion withItemCount(int expectedCount) {
            return withItemCount(Matchers.is(expectedCount));
        }

        public static ItemCountAssertion withItemCount(Matcher<Integer> matcher) {
            return new ItemCountAssertion(matcher);
        }

        private ItemCountAssertion(Matcher<Integer> matcher) {
            this.matcher = matcher;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            Assert.assertThat(adapter.getItemCount(), matcher);
        }
    }

