package org.peace.allinone;

import dagger.Component;
import org.peace.allinone.ui.MainActivity;

/**
 * Created by daqi on 17-3-22.
 */

@Component(modules = {FooModule.class})
public interface MyAppComponent {

  void inject(MainActivity activity);
}
