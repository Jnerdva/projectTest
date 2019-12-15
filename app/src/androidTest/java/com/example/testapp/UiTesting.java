package com.example.testapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.testapp.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UiTesting {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(MainActivity.class);
    @Test
    /*Test to assert if No data is present in the screen.
      By performing click operation on first item.
     */
    public void checkUiWithOuTData(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv);
        int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
        if (itemCount> 0){
            onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }
    @Test
    /*
     Test to assert that screen with all data filled.
     By performing click operation on every item present on the screen.
     */
    public void UiWithFilledData(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv);
        int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
        for(int position=0;position<itemCount;position++){
            onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        }

    }


}
