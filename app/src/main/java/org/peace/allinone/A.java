package org.peace.allinone;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by daqi on 17-6-2.
 */

public class A extends JobService {
    public static String TAG = "Peace";

    @Override
    public boolean onStartJob(final JobParameters params) {

        Log.e(TAG, cn() + " onstart");
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, cn() + " async on post");
                jobFinished(params, false);
            }
        }.execute();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, cn() + " onstop");
        return false;
    }

    public String cn() {
        return getClass().getSimpleName();
    }
}
