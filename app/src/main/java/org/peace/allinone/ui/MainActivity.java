package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.text_view) TextView textView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    ClickableSpan cs = new ClickableSpan() {
      @Override public void onClick(View widget) {
        Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
      }
    };

    SpannableString ss = new SpannableString(textView.getText());
    ss.setSpan(cs, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    textView.setText(ss);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
