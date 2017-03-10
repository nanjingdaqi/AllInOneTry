package org.peace.allinone;

import android.content.Context;
import android.view.LayoutInflater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.peace.allinone.ui.FooClass;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by daqi on 17-3-10.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({FooClass.class, LayoutInflater.class})
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
  public void testFoo2() {
    FooClass obj = new FooClass();
    Assert.assertTrue(obj.foo2(context1));
    Assert.assertFalse(obj.foo2(context2));
  }

  @Test
  public void testFoo3() {
    LayoutInflater inflater = PowerMockito.mock(LayoutInflater.class);
    Mockito.when(inflater.toString()).thenReturn("hello");

    PowerMockito.mockStatic(LayoutInflater.class);
    PowerMockito.when(LayoutInflater.from(context1)).thenReturn(inflater);

    FooClass obj = new FooClass();

    Assert.assertTrue(obj.foo3(context1));
  }
}
