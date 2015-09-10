package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import me.ele.commons.DimenUtil;
import org.peace.allinone.R;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.start_btn)
    Button mStartBtn;
    @InjectView(R.id.edit_text)
    EditText editText;
    @InjectView(R.id.pop_up_container)
    FrameLayout popContainer;
    @InjectView(R.id.container)
    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    @OnClick({R.id.start_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_btn) {
            editText.requestFocus();
            editText.setSelection(editText.getText().length());
            showPopup();
        }
    }

    private void showPopup() {
        final int[] xy = getDetailCursorPosition();
        final int x = xy[0], y = xy[1];
        AppLogger.d("x: " + xy[0] + ", y: " + xy[1]);
        int[] edLoc = new int[2];
        editText.getLocationOnScreen(edLoc);
        AppLogger.d("ed x: " + edLoc[0] + ", ed y: " + edLoc[1]);
        View v = LayoutInflater.from(this).inflate(R.layout.triangle_pop, null);
        PopupWindow popupWindow = new PopupWindow(v, DimenUtil.dip2px(this, 160), LayoutParams.WRAP_CONTENT, false);
//        popupWindow.setWidth(DimenUtil.dip2px(this, 160));
        popupWindow.setHeight(DimenUtil.dip2px(this, 60));
        int xoff = x - DimenUtil.dip2px(this, 160) / 2;
        AppLogger.d("xoff: " + xoff);
        popupWindow.showAtLocation(container, Gravity.LEFT | Gravity.TOP, edLoc[0] + xoff,
                edLoc[1] - DimenUtil.dip2px(this, 60) - DimenUtil.dip2px(this, 8));
        AppLogger.d("pw: " + popupWindow.getWidth() + ", ph: " + popupWindow.getHeight());
    }

    private int[] getDetailCursorPosition() {
        int pos = editText.getSelectionStart();
        Layout layout = editText.getLayout();
        int line = layout.getLineForOffset(pos);
        int baseline = layout.getLineBaseline(line);
        int ascent = layout.getLineAscent(line);
        float x = layout.getPrimaryHorizontal(pos) + editText.getPaddingLeft();
        float y = baseline + ascent;
        return new int[] {(int) x, (int) y};
    }

}
