package org.peace.allinone;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.peace.allinone.ui.MainActivity;

/**
 * Created by daqi on 17-3-10.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SimpleTest {

  @Rule
  public ActivityTestRule<MainActivity> simpleRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void testFoo() {
    MainActivity activity = simpleRule.getActivity();
    Assert.assertSame("hello", activity.foo("hello"));
  }
}
