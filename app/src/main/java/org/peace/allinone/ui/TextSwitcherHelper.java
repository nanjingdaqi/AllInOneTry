package org.peace.allinone.ui;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import static android.view.animation.Animation.ABSOLUTE;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

class TextSwitcherHelper {

  private TextSwitcherHelper() {
  }

  public static Animation createOutAnim(long duration) {
    TranslateAnimation anim =
        new TranslateAnimation(ABSOLUTE, 0, 0, 0, RELATIVE_TO_SELF, 0, RELATIVE_TO_SELF, -1.0f);
    anim.setDuration(duration);
    return anim;
  }

  public static Animation createInAnim(long duration) {
    TranslateAnimation anim =
        new TranslateAnimation(ABSOLUTE, 0, 0, 0, RELATIVE_TO_SELF, 1.0f, RELATIVE_TO_SELF, 0);
    anim.setDuration(duration);
    return anim;
  }
}
