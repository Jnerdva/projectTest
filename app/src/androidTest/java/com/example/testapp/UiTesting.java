package com.example.testapp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.testapp.view.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.core.util.Preconditions.checkNotNull;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UiTesting {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(MainActivity.class);
    @Test
    public void checkUiWithOuTData(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (itemCount> 0){
            onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }
    @Test
    public void UiWithFilledData(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv);
        int itemCount = recyclerView.getAdapter().getItemCount();
        for(int position=0;position<itemCount;position++){
            onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        }

    }


}
