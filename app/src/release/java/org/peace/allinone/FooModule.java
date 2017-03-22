package org.peace.allinone;

import dagger.Module;
import dagger.Provides;
import org.peace.allinone.ui.FooClass;

/**
 * Created by daqi on 17-3-22.
 */

@Module
public class FooModule {

  @Provides
  public FooClass provideFooClass() {
    return new FooClass();
  }
}
