package org.peace.allinone.Main;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;
import org.peace.allinone.MyApp;

/**
 * Created by daqi on 17-6-5.
 */

public class Sync extends AbstractThreadedSyncAdapter {

    public static String T = "Peace";

    public Sync(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.e(T, "on perform sync");
        MyApp.doSth(30);
        Log.e(T, "30s passed");
        MyApp.doSth(100);
        Log.e(T, "on perform sync end");
    }


}
