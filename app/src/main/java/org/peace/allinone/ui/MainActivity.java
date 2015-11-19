package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
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

        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_btn)
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
                    .build();
            RecyclerView rv = (RecyclerView) dlg.findViewById(R.id.list);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new MyAdapter(this));
            rv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    rv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int maxH = DimenUtil.dip2px(MainActivity.this, 80);
                    if (maxH < rv.getMeasuredHeight()) {
                        ViewGroup.LayoutParams layoutParams = rv.getLayoutParams();
                        if (layoutParams != null) {
                            layoutParams.height = maxH;
                        }
                    }
                }
            });
            dlg.show();
        }
    }

    public static class MyVH extends RecyclerView.ViewHolder {

        @InjectView(R.id.content)
        TextView content;

        public MyVH(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyVH> {

        Activity activity;
        LayoutInflater inflater;
        List<String> data;

        MyAdapter(Activity activity) {
            this.activity = activity;
            inflater = LayoutInflater.from(activity);
            data = new LinkedList<>();
            for (int i = 0; i < 1; ++i) {
                data.add("Item " + i);
            }
        }

        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.item_dlg, parent, false);
            return new MyVH(itemView);
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position) {
            holder.content.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
