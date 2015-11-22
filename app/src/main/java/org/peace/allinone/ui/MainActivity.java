package org.peace.allinone.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.image_switcher) ImageSwitcher imageSwitcher;

  int[] drawables;
  // 左右滑动时手指按下的X坐标
  private float touchDownX;
  // 左右滑动时手指松开的X坐标
  private float touchUpX;
  int pictureIndex = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    drawables = new int[] {
        R.drawable.__leak_canary_icon, R.drawable.__leak_canary_notification,
        R.drawable.ic_launcher,
    };

    imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
      @Override public View makeView() {
        ImageView view = new ImageView(MainActivity.this);
        //view.setImageDrawable(drawables[pictureIndex]);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return view;
      }
    });
    imageSwitcher.setImageResource(drawables[pictureIndex]);
    imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          // 取得左右滑动时手指按下的X坐标
          touchDownX = event.getX();
          return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
          // 取得左右滑动时手指松开的X坐标
          touchUpX = event.getX();
          // 从左往右，看前一张
          if (touchUpX - touchDownX > 100) {
            // 取得当前要看的图片的index
            pictureIndex = pictureIndex == 0 ? drawables.length - 1 : pictureIndex - 1;
            // 设置图片切换的动画
            imageSwitcher.setInAnimation(
                AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
            imageSwitcher.setOutAnimation(
                AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
            // 设置当前要看的图片
            imageSwitcher.setImageResource(drawables[pictureIndex]);
            // 从右往左，看下一张
          } else if (touchDownX - touchUpX > 100) {
            // 取得当前要看的图片的index
            pictureIndex = pictureIndex == drawables.length - 1 ? 0 : pictureIndex + 1;
            // 设置图片切换的动画
            // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
            imageSwitcher.setInAnimation(
                AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
            imageSwitcher.setOutAnimation(
                AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
            // 设置当前要看的图片
            imageSwitcher.setImageResource(drawables[pictureIndex]);
          }
          return true;
        }
        return false;
      }
    });
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
