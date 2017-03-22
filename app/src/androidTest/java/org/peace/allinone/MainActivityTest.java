package org.peace.allinone;

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

  @Rule
  public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

  @Test
  public void testFoo() {
    MainActivity activity = rule.getActivity();
    MainActivity activity1 = Mockito.spy(activity);
    when(activity1.foo()).thenReturn(true);
    Espresso.onView(ViewMatchers.withId(R.id.start_btn)).perform(ViewActions.click());
  }

}
