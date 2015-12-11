package org.peace.allinone.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;

  @BindView(R.id.scroll_view) ScrollView scrollView;
  @BindView(R.id.ll) LinearLayout ll;
  @BindView(R.id.root) LinearLayout root;

  ArrayAdapter<String> adapter2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    TextView head = new TextView(this);
    head.setText("Head");
    LinearLayout.LayoutParams lp =
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            DimenUtil.dip2px(this, 48));
    head.setLayoutParams(lp);
    //root.addView(head, 1);

    //root.setChildrenView(head, scrollView);

    for (int i = 0; i < 10; ++i) {
      TextView tv = new TextView(this);
      tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160));
      tv.setText("Item " + i);
      ll.addView(tv);
      tv.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          ValueAnimator va = ValueAnimator.ofInt(tv.getHeight(), 0);
          LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
          float y = tv.getY();
          va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
              int h = (int) animation.getAnimatedValue();
              lp.height = h;
              tv.setLayoutParams(lp);
            }
          });
          va.setDuration(1000).start();
        }
      });
    }
  }
}
