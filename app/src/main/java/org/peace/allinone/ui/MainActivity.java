package org.peace.allinone.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ResourcesManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import org.peace.allinone.Data;
import org.peace.allinone.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    File outFile;
    String ARROW = " -> ";
    String TAB = "  ";
    int MAX_LEN = 20;

    Resources settingsRes;
    Map<String, Resources> resMap = new LinkedHashMap<>();
    File[] apks = {new File("/sdcard/Settings_l.apk"),
            new File("/sdcard/Settings_m.apk"),
            new File("/sdcard/Settings_n.apk"),
            new File("/sdcard/Settings_o.apk")};

    String[] results = {"res_l.txt", "res_m.txt", "res_n.txt", "res_o.txt"};

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    void doOp() {
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        try {
            for (int i = 0; i < apks.length; ++i) {
                resMap.clear();
                settingsRes = ResourcesManager.getInstance().getResources(null,
                        apks[i].getAbsolutePath(), null, null,
                        null, 0, null, null, getClassLoader());
                resMap.put("com.android.settings", settingsRes);
                resMap.put("miui", createPackageContext("com.miui.core", CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY).getResources());
                resMap.put("com.android.phone", createPackageContext("com.android.phone", CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY).getResources());

                outFile = new File(getFilesDir(), results[i]);

                InputStream is = settingsRes.getAssets().open("index.json");
                BufferedInputStream bis = new BufferedInputStream(is);
                InputStreamReader reader = new InputStreamReader(bis);
                Gson gson = new Gson();
                Data data = gson.fromJson(reader, Data.class);

                StringBuilder sb = new StringBuilder();
                print(data, sb, 0);

                FileOutputStream fos = new FileOutputStream(outFile);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
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
        for (Map.Entry<String, Resources> entry : resMap.entrySet()) {
            Resources res = entry.getValue();
            String pkgName = entry.getKey();
            id = res.getIdentifier(data.resource, "string", pkgName);
            if (id != 0) {
                val = res.getString(id);
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
