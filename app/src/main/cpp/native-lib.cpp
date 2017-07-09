#include <jni.h>
#include <string>

#include <android/log.h>

#define T "peace"
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,T,__VA_ARGS__)

extern "C"

//jstring callJava(JNIEnv *env) {
//    jclass cls = env->FindClass("org/peace/allinone/ui/MainActivity");
//    jmethodID mid = env->GetStaticMethodID(cls, "forNative", "()Ljava/lang/String;");
//    return (jstring) env->CallStaticObjectMethod(cls, mid);
//}

JNIEXPORT jstring JNICALL
Java_org_peace_allinone_ui_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    LOGE("Java_org_peace_allinone_ui_MainActivity_stringFromJNI");
    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//    return callJava(env);
    jclass cls = env->FindClass("org/peace/allinone/ui/MainActivity");
    jmethodID mid = env->GetStaticMethodID(cls, "forNative", "()Ljava/lang/String;");
    return (jstring) env->CallStaticObjectMethod(cls, mid);
}

