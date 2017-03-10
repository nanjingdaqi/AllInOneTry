package org.peace.allinone;

import android.content.Context;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.peace.allinone.ui.FooClass;

/**
 * Created by daqi on 17-3-10.
 */

@RunWith(MockitoJUnitRunner.class)
public class UnitSimpleTest {

  @Mock
  Context context1;

  @Mock
  Context context2;

  @Before
  public void setup() {
    Mockito.when(context1.toString()).thenReturn("hello");
    Mockito.when(context2.toString()).thenReturn("world");
  }

  @Test
  public void testFoo() {
    FooClass obj = new FooClass();
    Assert.assertTrue(obj.foo2(context1));
    Assert.assertFalse(obj.foo2(context2));
  }
}
