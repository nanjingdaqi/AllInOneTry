.class public Lme/ele/base/utils/DimenUtil;
.super Ljava/lang/Object;
.source "DimenUtil.java"


# static fields
.field private static CONTEXT:Landroid/content/Context; = null

.field private static final PADDING:F = 0.5f


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 23
    return-void
.end method

.method public static dip2px(F)I
    .locals 3
    .param p0, "dpValue"    # F

    .line 29
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 30
    .local v0, "scale":F
    mul-float v1, p0, v0

    const/high16 v2, 0x3f000000    # 0.5f

    add-float/2addr v1, v2

    float-to-int v1, v1

    return v1
.end method

.method private static getActionbarSize()I
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/ClassNotFoundException;,
            Ljava/lang/NoSuchFieldException;,
            Ljava/lang/IllegalAccessException;
        }
    .end annotation

    .line 100
    const-string v0, "android.support.v7.appcompat.R$attr"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 101
    .local v0, "appcompatAttrClazz":Ljava/lang/Class;
    const-string v1, "actionBarSize"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    .line 102
    .local v1, "actionBarSizeField":Ljava/lang/reflect/Field;
    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    return v2
.end method

.method public static getContentHeight()I
    .locals 6

    .line 75
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    .line 76
    .local v0, "context":Landroid/content/Context;
    const-string v1, "window"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/view/WindowManager;

    .line 77
    .local v1, "windowManager":Landroid/view/WindowManager;
    invoke-interface {v1}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object v2

    .line 78
    .local v2, "display":Landroid/view/Display;
    new-instance v3, Landroid/graphics/Point;

    invoke-direct {v3}, Landroid/graphics/Point;-><init>()V

    .line 79
    .local v3, "size":Landroid/graphics/Point;
    invoke-virtual {v2, v3}, Landroid/view/Display;->getSize(Landroid/graphics/Point;)V

    .line 80
    iget v4, v3, Landroid/graphics/Point;->y:I

    invoke-static {}, Lme/ele/base/utils/DimenUtil;->getStatusBarHeight()I

    move-result v5

    sub-int/2addr v4, v5

    return v4
.end method

.method public static getNavigationBarHeight()I
    .locals 4

    .line 69
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 70
    .local v0, "resources":Landroid/content/res/Resources;
    const-string v1, "navigation_bar_height"

    const-string v2, "dimen"

    const-string v3, "android"

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    .line 71
    .local v1, "resId":I
    if-lez v1, :cond_0

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    return v2
.end method

.method public static getScreenHeight()I
    .locals 1

    .line 55
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->heightPixels:I

    return v0
.end method

.method public static getScreenHeightMinusOf(I)I
    .locals 2
    .param p0, "dpValue"    # I

    .line 59
    invoke-static {}, Lme/ele/base/utils/DimenUtil;->getScreenHeight()I

    move-result v0

    int-to-float v1, p0

    invoke-static {v1}, Lme/ele/base/utils/DimenUtil;->dip2px(F)I

    move-result v1

    sub-int/2addr v0, v1

    return v0
.end method

.method public static getScreenWidth()I
    .locals 1

    .line 47
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    return v0
.end method

.method public static getScreenWidthMinusOf(I)I
    .locals 2
    .param p0, "dpValue"    # I

    .line 51
    invoke-static {}, Lme/ele/base/utils/DimenUtil;->getScreenWidth()I

    move-result v0

    int-to-float v1, p0

    invoke-static {v1}, Lme/ele/base/utils/DimenUtil;->dip2px(F)I

    move-result v1

    sub-int/2addr v0, v1

    return v0
.end method

.method public static getStatusBarHeight()I
    .locals 4

    .line 63
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 64
    .local v0, "resources":Landroid/content/res/Resources;
    const-string v1, "status_bar_height"

    const-string v2, "dimen"

    const-string v3, "android"

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    .line 65
    .local v1, "resId":I
    if-lez v1, :cond_0

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    return v2
.end method

.method public static getToolBarHeight(Landroid/app/Activity;)I
    .locals 6
    .param p0, "activity"    # Landroid/app/Activity;

    .line 85
    const/4 v0, 0x0

    .line 87
    .local v0, "styledAttributes":Landroid/content/res/TypedArray;
    const/high16 v1, 0x42600000    # 56.0f

    :try_start_0
    invoke-virtual {p0}, Landroid/app/Activity;->getTheme()Landroid/content/res/Resources$Theme;

    move-result-object v2

    const/4 v3, 0x1

    new-array v3, v3, [I

    invoke-static {}, Lme/ele/base/utils/DimenUtil;->getActionbarSize()I

    move-result v4

    const/4 v5, 0x0

    aput v4, v3, v5

    invoke-virtual {v2, v3}, Landroid/content/res/Resources$Theme;->obtainStyledAttributes([I)Landroid/content/res/TypedArray;

    move-result-object v2

    move-object v0, v2

    .line 88
    invoke-static {v1}, Lme/ele/base/utils/DimenUtil;->dip2px(F)I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v0, v5, v2}, Landroid/content/res/TypedArray;->getDimension(IF)F

    move-result v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    float-to-int v1, v2

    .line 92
    if-eqz v0, :cond_0

    .line 93
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 88
    :cond_0
    return v1

    .line 92
    :catchall_0
    move-exception v1

    goto :goto_0

    .line 89
    :catch_0
    move-exception v2

    .line 90
    .local v2, "e":Ljava/lang/Exception;
    :try_start_1
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 92
    .end local v2    # "e":Ljava/lang/Exception;
    if-eqz v0, :cond_1

    .line 93
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 96
    :cond_1
    invoke-static {v1}, Lme/ele/base/utils/DimenUtil;->dip2px(F)I

    move-result v1

    return v1

    .line 92
    :goto_0
    if-eqz v0, :cond_2

    .line 93
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 95
    :cond_2
    throw v1
.end method

.method public static init(Landroid/content/Context;)V
    .locals 0
    .param p0, "context"    # Landroid/content/Context;

    .line 19
    sput-object p0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    .line 20
    return-void
.end method

.method public static px2dip(F)I
    .locals 3
    .param p0, "pxValue"    # F

    .line 42
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 43
    .local v0, "scale":F
    div-float v1, p0, v0

    const/high16 v2, 0x3f000000    # 0.5f

    add-float/2addr v1, v2

    float-to-int v1, v1

    return v1
.end method

.method public static sp2px(F)I
    .locals 2
    .param p0, "sp"    # F

    .line 34
    sget-object v0, Lme/ele/base/utils/DimenUtil;->CONTEXT:Landroid/content/Context;

    .line 35
    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    .line 34
    const/4 v1, 0x2

    invoke-static {v1, p0, v0}, Landroid/util/TypedValue;->applyDimension(IFLandroid/util/DisplayMetrics;)F

    move-result v0

    float-to-int v0, v0

    return v0
.end method
