/*
 * Copyright 2015-present wequick.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package net.wequick.small;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.wequick.small.util.BundleParser;
import net.wequick.small.util.FileUtils;

public final class Small {

    public static final String LOG_TAG = Small.class.getSimpleName();

    public static final String BUNDLES_KEY = "bundles";
    public static final String KEY_ACTIVITY_URI = "key_activity_uri";

    public static final String KEY_QUERY = "small-query";
    public static final String EXTRAS_KEY_RET = "small-ret";
    public static final int REQUEST_CODE_DEFAULT = 10000;

    private static Application hostApplication;
    private static ApkBundleLauncher apkBundleLauncher;
    private static List<Bundle> loadedBundles = null;
    private static boolean isNewHostApp; // first launched or upgraded

    public static Application hostApplication() {
        return hostApplication;
    }

    public static boolean isNewHostApp() {
        return isNewHostApp;
    }

    public static void setup(Application application) throws SmallSetupException {
        hostApplication = application;
        SharedPreferenceManager.init(hostApplication);
        handleVersionChange();
        // todo handle bundle update here
        try {
            setupBundleLaunchers();
            loadBundles();
        } catch (Exception e) {
            throw new SmallSetupException(e);
        }
    }

    private static void handleVersionChange() {
        // Check if host app is first-installed or upgraded
        PackageManager pm = hostApplication.getPackageManager();
        String packageName = hostApplication.getPackageName();
        int backupHostVersion = SharedPreferenceManager.getHostVersionCode();
        int currHostVersion = 0;
        try {
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            currHostVersion = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (backupHostVersion != currHostVersion) {
            isNewHostApp = true;
            SharedPreferenceManager.setHostVersionCode(currHostVersion);
            clearSmallCache();
        } else {
            isNewHostApp = false;
        }
    }

    private static void clearSmallCache() {
        File file = new File(FileManager.smallDir());
        FileUtils.deleteFile(file);
    }

    private static void setupBundleLaunchers() throws ApkBundleLauncher.LauncherSetupException {
        apkBundleLauncher = new ApkBundleLauncher();
        // todo consider remove this step
        apkBundleLauncher.setup(hostApplication);
    }

    private static void loadBundles()
        throws BundleParser.BundleParseException, Bundle.BundleLoadException {
        BundleManifest bundleManifest = parseBundleManifest();
        doLoadBundles(bundleManifest);
    }

    // todo handle bundle.json processing
    // todo handling bundle.json upgrade
    private static BundleManifest parseBundleManifest() {
        String jsonStr = readBundlesInfo();
        // Parse manifest file
        Gson gson = new Gson();
        BundleManifest bundleManifest = gson.fromJson(jsonStr, BundleManifest.class);
        return bundleManifest;
    }

    private static String readBundlesInfo() {
        File manifestFile = new File(hostApplication.getFilesDir(), FileManager.BUNDLE_MANIFEST_NAME);
        manifestFile.delete();
        String manifestJson = null;
        // Copy asset to files
        try {
            InputStream is = hostApplication.getAssets().open(FileManager.BUNDLE_MANIFEST_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            manifestFile.createNewFile();
            FileOutputStream os = new FileOutputStream(manifestFile);
            os.write(buffer);
            os.close();

            manifestJson = new String(buffer, 0, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return manifestJson;
    }

    private static void doLoadBundles(BundleManifest bundleManifest)
        throws BundleParser.BundleParseException, Bundle.BundleLoadException {
        List<BundleManifest.BundleInfo> bundleInfoList = bundleManifest.bundleInfoList();
        List<Bundle> bundles = new ArrayList<>(bundleInfoList.size());
        for (BundleManifest.BundleInfo bundleInfo : bundleInfoList) {
            Bundle bundle = new Bundle(bundleInfo);
            bundle.setup(apkBundleLauncher);
            bundles.add(bundle);

            if (BuildConfig.DEBUG) {
                Log.d(LOG_TAG, "bundle loaded: " + bundleInfo);
            }
        }
        loadedBundles = bundles;
    }

    // todo integrate with scheme
    public static void launchBundleActivity(Intent intent, Context context) {
        String uriStr = intent.getStringExtra(KEY_ACTIVITY_URI);
        if (TextUtils.isEmpty(uriStr)) {
            throw new IllegalArgumentException("intent must contain a valid key value of Small#KEY_ACTIVITY_URI");
        }
        Bundle bundle = findTargetBundle(uriStr);
        if (bundle == null) {
            Log.w(LOG_TAG, "fail to find target bundle for uri: " + uriStr);
            return;
        }
        try {
            Class target = bundle.getTargetClass(uriStr);
            apkBundleLauncher.launchActivity(target, intent, context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // todo check the
    private static Bundle findTargetBundle(String uriStr) {
        for (Bundle bundle : loadedBundles) {
            if (bundle.isTarget(uriStr)) {
                return bundle;
            }
        }
        return null;
    }

    public static class SmallSetupException extends Exception {
        public SmallSetupException(Throwable throwable) {
            super(throwable);
        }
    }
}
