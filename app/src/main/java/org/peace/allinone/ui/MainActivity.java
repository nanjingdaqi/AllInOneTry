package org.peace.allinone.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;
import android.util.Log;
import org.peace.allinone.WeakRefAsyncTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyTask(new MyCallback()).execute();
    }

    private void updateUI() {}

    // static inner class
    static class MyTask extends WeakRefAsyncTask<Void, Void, Void> {
        MyTask(WeakRefAsyncTask.Callback<Void, Void, Void> cb) {
            super(cb);
        }
    }

    // non-static inner class, but would be referenced by WeakRef in WeakRefAsyncTask
    class MyCallback implements WeakRefAsyncTask.Callback<Void, Void, Void> {
            @Override
            public void onPreExecute() {}

            @Override
            public Void doInBackground(Void[] voids) {
                Log.d("daqi", "doInBackground");
                while (System.currentTimeMillis() > 0) {

                }
                return null;
            }

            @Override
            public void onProgressUpdate(Void[] values) {}

            @Override
            public void onPostExecute(Void aVoid) {
                updateUI();
            }
    }
}
