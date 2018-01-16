package org.peace.allinone;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public abstract class WeakRefAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    private WeakReference<Callback<Params, Progress, Result>> mOuterRef;

    public WeakRefAsyncTask(Callback<Params, Progress, Result> outer) {
        mOuterRef = new WeakReference<Callback<Params, Progress, Result>>(outer);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Callback<Params, Progress, Result> outer = mOuterRef.get();
        if (outer != null) {
            outer.onPreExecute();
        }
    }

    @Override
    protected Result doInBackground(Params[] params) {
        Callback<Params, Progress, Result> outer = mOuterRef.get();
        if (outer != null) {
            return outer.doInBackground(params);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Progress[] values) {
        super.onProgressUpdate(values);
        Callback<Params, Progress, Result> outer = mOuterRef.get();
        if (outer != null) {
            outer.onProgressUpdate(values);
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        Callback<Params, Progress, Result> outer = mOuterRef.get();
        if (outer != null) {
            outer.onPostExecute(result);
        }
    }

    public interface Callback<Params, Progress, Result> {
        void onPreExecute();

        Result doInBackground(Params[] params);

        void onProgressUpdate(Progress[] values);

        void onPostExecute(Result result);
    }
}
