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
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.wequick.small.util.BundleParser;
import net.wequick.small.util.ReflectAccelerator;

public class ApkBundleLauncher {

    private Map<String, LoadedApk> loadedApks = new HashMap<>();
    private Map<String, ActivityInfo> loadedActivities = new HashMap<>();
    private Map<String, List<IntentFilter>> loadedIntentFilters = new HashMap<>();

    private Instrumentation hostInstrumentation;
    private InstrumentationWrapper instrumentationWrapper;

    public void setup(Context context) throws LauncherSetupException {
        try {
            hostInstrumentation = ReflectAccelerator.hostInstrumentation();
            instrumentationWrapper = new InstrumentationWrapper(this);
            ReflectAccelerator.hookInstrumentation(instrumentationWrapper, context);
        } catch (Exception e) {
          throw new LauncherSetupException("fail to setup launcher", e);
        }
    }

    public void initBundle(BundleParser parser, LoadedApk loadedApk)
        throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        storeActivities(parser.getPackageInfo());
        storeIntentFilters(parser);
        storeApk(parser.getPackageInfo().packageName, loadedApk);

        String bundleApplicationName = parser.getPackageInfo().applicationInfo.className;
        if (!TextUtils.isEmpty(bundleApplicationName)) {
            createApplication(bundleApplicationName);
        }
    }

    private void storeActivities(PackageInfo pluginInfo) {
        // Record activities for intent redirection
        for (ActivityInfo ai : pluginInfo.activities) {
            loadedActivities.put(ai.name, ai);
        }
    }

    private void storeApk(String packageName, LoadedApk loadedApk) {
        loadedApks.put(packageName, loadedApk);
    }

    private void storeIntentFilters(BundleParser parser) {
        // Record intent-filters for implicit action
        ConcurrentHashMap<String, List<IntentFilter>> filters = parser.getIntentFilters();
        if (filters != null) {
            loadedIntentFilters.putAll(filters);
        }
    }

    private void createApplication(String bundleApplicationName)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // Call bundle application onCreate
        Class applicationClass = Class.forName(bundleApplicationName);
        Application bundleApplication = Instrumentation.newApplication(
            applicationClass, Small.hostApplication());
        hostInstrumentation.callApplicationOnCreate(bundleApplication);
    }

    public Instrumentation hostInstrumentation() {
        return hostInstrumentation;
    }

    public Map<String, ActivityInfo> loadedActivities() {
        return loadedActivities;
    }

    public Map<String, LoadedApk> loadedApks() {
        return loadedApks;
    }

    public Map<String, List<IntentFilter>> loadedIntentFilters() {
        return loadedIntentFilters;
    }

    //
    ///** Incubating */
    //private void unloadBundle(String packageName) {
    //    if (loadedApks == null) return;
    //    LoadedApk apk = loadedApks.get(packageName);
    //    if (apk == null) return;
    //
    //    if (loadedActivities != null && apk.activities != null) {
    //        for (ActivityInfo ai : apk.activities) {
    //            loadedActivities.remove(ai.name);
    //        }
    //    }
    //    loadedApks.remove(packageName);
    //
    //    // Remove asset path from application (Reset resources merger)
    //    Context appContext = ((ContextWrapper) Small.hostApplication()).getBaseContext();
    //    ResourcesMerger rm = ResourcesMerger.merge(appContext);
    //    ReflectAccelerator.setResources(appContext, rm);
    //    // TODO: Remove asset path from launching activities
    //
    //    // Remove dexElement from ClassLoader?
    //    for (LoadedApk a : loadedApks.values()) {
    //        if (a.dexElementIndex > apk.dexElementIndex) a.dexElementIndex--;
    //    }
    //    ReflectAccelerator.removeDexPathList(
    //            appContext.getClassLoader(), apk.dexElementIndex);
    //    if (apk.dexFile.exists()) apk.dexFile.delete();
    //}

    public void launchActivity(Class target, Intent intent, Context context) {
        intent.setComponent(new ComponentName(context, target));
        context.startActivity(intent);
    }


    public static class LauncherSetupException extends Exception {
        public LauncherSetupException() {
        }

        public LauncherSetupException(String detailMessage) {
            super(detailMessage);
        }

        public LauncherSetupException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public LauncherSetupException(Throwable throwable) {
            super(throwable);
        }
    }
}
