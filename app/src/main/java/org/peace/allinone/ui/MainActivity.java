package org.peace.allinone.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;


public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    account = CreateSyncAccount(this);
    ContentResolver.addPeriodicSync(account, "peace", new Bundle(), 10 * 1000);
  }

  Account account;

  @OnClick(R.id.start_btn) public void onClick(View v) {
    ContentResolver.requestSync(account, "peace", new Bundle());
  }

  /**
   * Create a new dummy account for the sync adapter
   *
   * @param context The application context
   */
  public static Account CreateSyncAccount(Context context) {
    // Create the account type and default account
    Account newAccount = new Account("peace", "peace");
    // Get an instance of the Android account manager
    AccountManager accountManager = (AccountManager) context.getSystemService(
                    ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
    if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
    } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
    }

    return newAccount;
  }
}
