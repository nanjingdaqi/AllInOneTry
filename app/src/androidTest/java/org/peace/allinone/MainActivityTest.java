package org.peace.allinone;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.peace.allinone.ui.MainActivity;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by daqi on 17-3-22.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  //@Rule
  //public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

  @Test
  public void testFoo() {
    TestIdHolder.id = 0;
    ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);
    rule.launchActivity(new Intent(Intent.ACTION_MAIN));
    Espresso.onView(ViewMatchers.withId(R.id.start_btn)).perform(ViewActions.click());
    pause(3);
    rule.getActivity().finish();
  }

  @Test
  public void testFoo2() {
    TestIdHolder.id = 1;
    ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);
    rule.launchActivity(new Intent(Intent.ACTION_MAIN));
    Espresso.onView(ViewMatchers.withId(R.id.start_btn)).perform(ViewActions.click());
    pause(3);
    rule.getActivity().finish();
  }

  public void pause(int s) {
    try {
      Thread.sleep(s * 1000);
    } catch (Exception e) {

    }
  }
}
