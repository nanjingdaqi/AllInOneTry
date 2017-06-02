# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:/adt-bundle-windows-x86-20140321/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontshrink
-optimizationpasses 5
-dontusemixedcaseclassnames#混淆时不会大小写混合类名
-dontskipnonpubliclibraryclasses #指定不去忽略非公共的库类
-dontpreverify #不预校验
-dontwarn #不警告
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #优化配置
-dontoptimize #不优化
-ignorewarnings #忽略警告
-repackageclasses me.ele

# 保留签名，解决泛型、类型转换的问题
-keepattributes Signature
-keepattributes Exceptions
# 不混淆带有 annotation 的变量 和 函数
-keepattributes *Annotation*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepclassmembers,allowoptimization enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 除了me.ele 其他都不混淆
-keep class !me.ele.**, !retrofit2.**{*;}
-keep class me.ele.**
# just for hotfix
#-applymapping mapping.txt

# butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepclasseswithmembernames class * {@butterknife.* <fields>; }
-keepclasseswithmembernames class * {@butterknife.* <methods>;}

-keep class me.ele.webview.EWebView$$Database
-keep class me.ele.webview.EWebView
-keep class **$$KeyDatabase
-keep class com.orhanobut.hawk.ClassFinder

-keep class * extends android.support.v4.view.ActionProvider

# eventbus
-keepclassmembers class * {
    public void onEvent*(**);
}

#weixin
-keep class com.tencent.mm.sdk.** {
   *;
}

#tengxun location
-keepclassmembers class ** {
    public void on*Event(...);
}
-dontnote ct.**

# LeakCanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }

# SchemeWebView
-keepclassmembers class * extends android.webkit.WebChromeClient {
     public void openFileChooser(...);
}

# Debug Mode
-keep class me.ele.DebugUtil { *; }

# NetDoctor
-keep class me.ele.netdoctor.TracePath { *; }

# Prism
-keep class **$$Batch { *; }
-keep class * extends retrofit2.Batch

# AMap
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
# App Link
-keep class com.taobao.applink.param.** { *; }
