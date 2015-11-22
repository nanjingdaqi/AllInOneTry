package org.peace.allinone.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.LinkedList;
import java.util.List;
import me.ele.base.utils.DimenUtil;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

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
            MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
            MaterialDialog dlg = builder.title("商品价格有调整, 总价变化")
                    .titleGravity(GravityEnum.CENTER)
                    .customView(R.layout.layout_dlg, false)
                    .positiveText("继续下单")
                    .negativeText("取消")
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .positiveColor(Color.parseColor("#3190e8"))
                    .negativeColor(Color.parseColor("#3190e8"))
                    .build();
            ListView lv = (ListView) dlg.findViewById(R.id.list);
            lv.setAdapter(new MyAdapter(this));
            int heightSpec = View.MeasureSpec.makeMeasureSpec(DimenUtil.dip2px(MainActivity.this, 130),
                    View.MeasureSpec.AT_MOST);
            lv.measure(0, heightSpec);
            ViewGroup.LayoutParams layoutParams = lv.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = lv.getMeasuredHeight();
            }
          })
          .positiveColor(Color.parseColor("#3190e8"))
          .negativeColor(Color.parseColor("#3190e8"))
          .build();
      ListView lv = (ListView) dlg.findViewById(R.id.list);
      lv.setAdapter(new MyAdapter(this));
      int heightSpec = View.MeasureSpec.makeMeasureSpec(DimenUtil.dip2px(MainActivity.this, 130),
          View.MeasureSpec.AT_MOST);
      lv.measure(0, heightSpec);
      ViewGroup.LayoutParams layoutParams = lv.getLayoutParams();
      if (layoutParams != null) {
        layoutParams.height = lv.getMeasuredHeight();
      }

      TextView title = (TextView) dlg.findViewById(R.id.price_change);
      SpannableString ss =
          new SpannableString(StringUtil.RMB + String.valueOf(Math.abs(17.5)) + " ");
      AbsoluteSizeSpan textSizeSpan = new AbsoluteSizeSpan(DimenUtil.sp2px(this, 24));
      ss.setSpan(textSizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      // todo replace by a smaller image
      Drawable drawable = this.getResources().getDrawable(R.drawable.down);
      int pt = 4;
      drawable.setBounds(DimenUtil.dip2px(this, pt), 0,
          drawable.getIntrinsicWidth() + DimenUtil.dip2px(this, pt), drawable.getIntrinsicHeight());
      ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
      ss.setSpan(imageSpan, ss.length() - 1, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      title.setText(ss);

      dlg.show();
    }

    public static class MyVH {

        @InjectView(R.id.food_name)
        TextView content;
        @InjectView(R.id.price)
        TextView price;

        public MyVH(View itemView) {
            ButterKnife.inject(this, itemView);
        }

        public static class MyAdapter extends BaseAdapter {

            Activity activity;
            LayoutInflater inflater;
            List<String> data;

            MyAdapter(Activity activity) {
                this.activity = activity;
                inflater = LayoutInflater.from(activity);
                data = new LinkedList<>();
                for (int i = 0; i < 10; ++i) {
                    data.add("番茄炒鸡蛋" + i);
                }
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MyVH vh;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_dlg, parent, false);
                    vh = new MyVH(convertView);
                    convertView.setTag(vh);
                }
                vh = (MyVH) convertView.getTag();
                vh.content.setText((String) getItem(position));
                vh.price.setText("-¥" + position);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        }
    }
}

