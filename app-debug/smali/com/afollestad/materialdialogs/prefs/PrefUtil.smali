.class Lcom/afollestad/materialdialogs/prefs/PrefUtil;
.super Ljava/lang/Object;
.source "PrefUtil.java"


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 21
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 22
    return-void
.end method

.method static registerOnActivityDestroyListener(Landroid/preference/Preference;Landroid/preference/PreferenceManager$OnActivityDestroyListener;)V
    .locals 7
    .param p0, "preference"    # Landroid/preference/Preference;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p1, "listener"    # Landroid/preference/PreferenceManager$OnActivityDestroyListener;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .line 53
    :try_start_0
    invoke-virtual {p0}, Landroid/preference/Preference;->getPreferenceManager()Landroid/preference/PreferenceManager;

    move-result-object v0

    .line 54
    .local v0, "pm":Landroid/preference/PreferenceManager;
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const-string v2, "registerOnActivityDestroyListener"

    const/4 v3, 0x1

    new-array v4, v3, [Ljava/lang/Class;

    const-class v5, Landroid/preference/PreferenceManager$OnActivityDestroyListener;

    const/4 v6, 0x0

    aput-object v5, v4, v6

    invoke-virtual {v1, v2, v4}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 57
    .local v1, "method":Ljava/lang/reflect/Method;
    invoke-virtual {v1, v3}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    .line 58
    new-array v2, v3, [Ljava/lang/Object;

    aput-object p1, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 60
    .end local v0    # "pm":Landroid/preference/PreferenceManager;
    .end local v1    # "method":Ljava/lang/reflect/Method;
    goto :goto_0

    .line 59
    :catch_0
    move-exception v0

    .line 61
    :goto_0
    return-void
.end method

.method static setLayoutResource(Landroid/content/Context;Landroid/preference/Preference;Landroid/util/AttributeSet;)V
    .locals 6
    .param p0, "context"    # Landroid/content/Context;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p1, "preference"    # Landroid/preference/Preference;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "attrs"    # Landroid/util/AttributeSet;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .line 25
    const/4 v0, 0x0

    .line 26
    .local v0, "foundLayout":Z
    const/4 v1, 0x0

    if-eqz p2, :cond_1

    .line 27
    const/4 v2, 0x0

    .local v2, "i":I
    :goto_0
    invoke-interface {p2}, Landroid/util/AttributeSet;->getAttributeCount()I

    move-result v3

    if-ge v2, v3, :cond_1

    .line 28
    move-object v3, p2

    check-cast v3, Landroid/content/res/XmlResourceParser;

    invoke-interface {v3, v1}, Landroid/content/res/XmlResourceParser;->getAttributeNamespace(I)Ljava/lang/String;

    move-result-object v3

    .line 29
    .local v3, "namespace":Ljava/lang/String;
    const-string v4, "http://schemas.android.com/apk/res/android"

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 30
    invoke-interface {p2, v2}, Landroid/util/AttributeSet;->getAttributeName(I)Ljava/lang/String;

    move-result-object v4

    const-string v5, "layout"

    invoke-virtual {v4, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 31
    const/4 v0, 0x1

    .line 32
    goto :goto_1

    .line 27
    .end local v3    # "namespace":Ljava/lang/String;
    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 37
    .end local v2    # "i":I
    :cond_1
    :goto_1
    const/4 v2, 0x0

    .line 38
    .local v2, "useStockLayout":Z
    if-eqz p2, :cond_2

    .line 39
    invoke-virtual {p0}, Landroid/content/Context;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object v3

    sget-object v4, Lcom/afollestad/materialdialogs/commons/R$styleable;->Preference:[I

    invoke-virtual {v3, p2, v4, v1, v1}, Landroid/content/res/Resources$Theme;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v3

    .line 41
    .local v3, "a":Landroid/content/res/TypedArray;
    :try_start_0
    sget v4, Lcom/afollestad/materialdialogs/commons/R$styleable;->Preference_useStockLayout:I

    invoke-virtual {v3, v4, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move v2, v1

    .line 43
    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    .line 44
    goto :goto_2

    .line 43
    :catchall_0
    move-exception v1

    invoke-virtual {v3}, Landroid/content/res/TypedArray;->recycle()V

    throw v1

    .line 47
    .end local v3    # "a":Landroid/content/res/TypedArray;
    :cond_2
    :goto_2
    if-nez v0, :cond_3

    if-nez v2, :cond_3

    .line 48
    sget v1, Lcom/afollestad/materialdialogs/commons/R$layout;->md_preference_custom:I

    invoke-virtual {p1, v1}, Landroid/preference/Preference;->setLayoutResource(I)V

    .line 49
    :cond_3
    return-void
.end method

.method static unregisterOnActivityDestroyListener(Landroid/preference/Preference;Landroid/preference/PreferenceManager$OnActivityDestroyListener;)V
    .locals 7
    .param p0, "preference"    # Landroid/preference/Preference;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p1, "listener"    # Landroid/preference/PreferenceManager$OnActivityDestroyListener;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .line 65
    :try_start_0
    invoke-virtual {p0}, Landroid/preference/Preference;->getPreferenceManager()Landroid/preference/PreferenceManager;

    move-result-object v0

    .line 66
    .local v0, "pm":Landroid/preference/PreferenceManager;
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const-string v2, "unregisterOnActivityDestroyListener"

    const/4 v3, 0x1

    new-array v4, v3, [Ljava/lang/Class;

    const-class v5, Landroid/preference/PreferenceManager$OnActivityDestroyListener;

    const/4 v6, 0x0

    aput-object v5, v4, v6

    invoke-virtual {v1, v2, v4}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 69
    .local v1, "method":Ljava/lang/reflect/Method;
    invoke-virtual {v1, v3}, Ljava/lang/reflect/Method;->setAccessible(Z)V

    .line 70
    new-array v2, v3, [Ljava/lang/Object;

    aput-object p1, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 72
    .end local v0    # "pm":Landroid/preference/PreferenceManager;
    .end local v1    # "method":Ljava/lang/reflect/Method;
    goto :goto_0

    .line 71
    :catch_0
    move-exception v0

    .line 73
    :goto_0
    return-void
.end method
