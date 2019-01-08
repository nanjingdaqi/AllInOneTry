#include <jni.h>
#include <string>

#include <android/log.h>

#define T "daqi"
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

int throw_ex(JNIEnv* env) {
    jclass exp_cls = env->FindClass("java/lang/RuntimeException");
    jobject exp = env->AllocObject(exp_cls);
//    env->Throw((jthrowable) exp);
    return env->ThrowNew(exp_cls, "foo");
}

jstring native_hello(JNIEnv* env) {
    throw_ex(env);
    return env->NewStringUTF("native hello");
}

jobject obj;
jobject obj2;

void loop() {
    int count = 0;
    while (count >= 0) {
        if (count % 100 == 0) count = 0;
        count++;
    }
}

void op_obj(JNIEnv* env) {
    LOGE("op_obj get called");
    jclass cls = env->FindClass("org/peace/allinone/Daqi");
    jfieldID mid = (jfieldID) env->GetFieldID(cls, "msg", "Ljava/lang/String;");
//    LOGE("obj msg: %s", env->GetStringUTFChars((jstring) env->GetObjectField(obj2, mid), NULL));
    loop();
}

void alloc(JNIEnv* env) {
    jclass cls = env->FindClass("org/peace/allinone/Daqi");
    obj = env->AllocObject(cls);
    obj = env->NewObject(cls, env->GetMethodID(cls, "<init>", "()V"));
    LOGE("obj ref: %d", env->GetObjectRefType(obj));
    obj2 = env->NewGlobalRef(obj);
    LOGE("obj2 ref: %d", env->GetObjectRefType(obj2));
}

void fromJava(JNIEnv* env, jobject obj) {
    LOGE("obj ref: %d", env->GetObjectRefType(obj));
}

JNINativeMethod methods[] = {
        {"native_hello", "()Ljava/lang/String;", (void*)native_hello},
        {"alloc", "()V", (void*)alloc},
        {"op_obj", "()V", (void*)op_obj},
        {"fromJava", "(Ljava/lang/String;)V", (void*)fromJava}
};

void registerNativeMethods(JNIEnv* env) {
    jclass cls = env->FindClass("org/peace/allinone/ui/MainActivity");
    LOGE("RegisterNatives begin");
    env->RegisterNatives(cls, methods, 4);
    LOGE("RegisterNatives done");
}

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    printf("daqi JNI_OnLoad");
    JNIEnv* env;
    LOGE("JNI_OnLoad");
    vm->GetEnv((void **) &env, JNI_VERSION_1_4);
    LOGE("get env done");
    registerNativeMethods(env);
    return JNI_VERSION_1_4;
}

