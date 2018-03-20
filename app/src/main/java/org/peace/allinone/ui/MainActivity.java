package org.peace.allinone.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import org.peace.allinone.Data;
import org.peace.allinone.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    File outFile;
    String ARROW = " -> ";
    String TAB = "  ";
    int MAX_LEN = 20;

    List<Context> ctxs = new ArrayList<>();
    Context settingsCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        try {
            settingsCtx = createPackageContext("com.android.settings", CONTEXT_INCLUDE_CODE);
            ctxs.add(settingsCtx);
            ctxs.add(createPackageContext("com.miui.core", CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY));
            ctxs.add(createPackageContext("com.android.phone", CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        outFile = new File(getFilesDir(), "res_str.txt");
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        try {
            InputStream is = settingsCtx.getAssets().open("index.json");
            BufferedInputStream bis = new BufferedInputStream(is);
            InputStreamReader reader = new InputStreamReader(bis);
            Gson gson = new Gson();
            Data data = gson.fromJson(reader, Data.class);
            Log.d("daqi", data.toString());
            Log.d("daqi", "son size: " + data.son.size());

            StringBuilder sb = new StringBuilder();
            print(data, sb, 0);
            Log.d("daqi", "\n" + sb.toString());

            FileOutputStream fos = new FileOutputStream(outFile);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void print(Data data, StringBuilder sb, int tabNum) {
        int num = tabNum;
        while (num-- > 0) {
            sb.append(TAB);
        }
        if (data.resource.length() > MAX_LEN) {
            sb.append(data.resource.substring(0, MAX_LEN));
        } else {
            sb.append(data.resource);
            int num2 = MAX_LEN - data.resource.length();
            while (num2-- > 0) {
                sb.append(' ');
            }
        }
        sb.append(ARROW);
        int id = 0;
        String val = "0x0";
        for (Context ctx : ctxs) {
            id = ctx.getResources().getIdentifier(data.resource, "string", TextUtils.equals(ctx.getPackageName(), "com.miui.core") ? "miui" : ctx.getPackageName());
            if (id != 0) {
                val = ctx.getResources().getString(id);
                break;
            }
        }
        sb.append(val);
        sb.append("\n");

        List<Data> son = data.son;
        if (son != null && son.size() > 0) {
            for (Data s : son) {
                print(s, sb, tabNum + 1);
            }
        }
    }
}
