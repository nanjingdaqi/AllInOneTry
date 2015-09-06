package org.peace.allinone.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.start_btn)
    Button mStartBtn;
    Button mStartBtn2;

    @InjectView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        String content = "( % 额配送会员 )";
        SpannableString ss = new SpannableString(content);
        Drawable vip = getResources().getDrawable(R.drawable.order_confirm_vip_tag);
        vip.setBounds(0, 0, vip.getIntrinsicWidth(), vip.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(vip, ImageSpan.ALIGN_BASELINE);
        int index = content.indexOf('%');
        ss.setSpan(span, index, index + 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
    }

    @OnClick({R.id.start_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_btn) {

        }
    }
}
