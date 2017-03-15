package org.peace.allinone;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.peace.allinone.ui.FooClass;
import org.peace.allinone.ui.MainActivity;

/**
 * Created by daqi on 17-3-10.
 */

@RunWith(AndroidJUnit4.class)
public class SimpleTest {

  @Rule
  public ActivityTestRule<MainActivity> simpleRule = new ActivityTestRule<>(MainActivity.class);

  Context context = InstrumentationRegistry.getTargetContext();

  @Mock
  FooClass fooObj1;

  @Mock
  FooClass fooObj2;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(fooObj1.foo()).thenReturn(true);
    Mockito.when(fooObj2.foo()).thenReturn(false);
  }

  @Test
  public void testFoo() {
    MainActivity activity = simpleRule.getActivity();
    activity.setMyFoo(fooObj1);
    Assert.assertTrue(activity.foo());

    activity.setMyFoo(fooObj2);
    Assert.assertFalse(activity.foo());
  }
}
