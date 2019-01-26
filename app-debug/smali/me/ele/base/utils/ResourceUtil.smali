.class public Lme/ele/base/utils/ResourceUtil;
.super Ljava/lang/Object;
.source "ResourceUtil.java"


# static fields
.field private static CONTEXT:Landroid/content/Context;

.field private static RES:Landroid/content/res/Resources;


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 26
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 27
    return-void
.end method

.method public static getColor(I)I
    .locals 1
    .param p0, "colorId"    # I
        .annotation build Landroid/support/annotation/ColorRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 34
    const/4 v0, 0x0

    invoke-static {p0, v0}, Lme/ele/base/utils/ResourceUtil;->getColor(ILandroid/content/res/Resources$Theme;)I

    move-result v0

    return v0
.end method

.method public static getColor(ILandroid/content/res/Resources$Theme;)I
    .locals 1
    .param p0, "colorId"    # I
        .annotation build Landroid/support/annotation/ColorRes;
        .end annotation
    .end param
    .param p1, "theme"    # Landroid/content/res/Resources$Theme;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 38
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-static {v0, p0, p1}, Landroid/support/v4/content/res/ResourcesCompat;->getColor(Landroid/content/res/Resources;ILandroid/content/res/Resources$Theme;)I

    move-result v0

    return v0
.end method

.method public static getColorStateList(I)Landroid/content/res/ColorStateList;
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/ColorRes;
        .end annotation
    .end param

    .line 62
    const/4 v0, 0x0

    invoke-static {p0, v0}, Lme/ele/base/utils/ResourceUtil;->getColorStateList(ILandroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;

    move-result-object v0

    return-object v0
.end method

.method public static getColorStateList(ILandroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/ColorRes;
        .end annotation
    .end param
    .param p1, "theme"    # Landroid/content/res/Resources$Theme;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .line 67
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-static {v0, p0, p1}, Landroid/support/v4/content/res/ResourcesCompat;->getColorStateList(Landroid/content/res/Resources;ILandroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;

    move-result-object v0

    return-object v0
.end method

.method public static getDimension(I)I
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/DimenRes;
        .end annotation
    .end param

    .line 71
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v0

    float-to-int v0, v0

    return v0
.end method

.method public static getDrawable(I)Landroid/graphics/drawable/Drawable;
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/DrawableRes;
        .end annotation
    .end param

    .line 50
    const/4 v0, 0x0

    invoke-static {p0, v0}, Lme/ele/base/utils/ResourceUtil;->getDrawable(ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    return-object v0
.end method

.method public static getDrawable(ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/DrawableRes;
        .end annotation
    .end param
    .param p1, "theme"    # Landroid/content/res/Resources$Theme;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .line 54
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-static {v0, p0, p1}, Landroid/support/v4/content/res/ResourcesCompat;->getDrawable(Landroid/content/res/Resources;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    return-object v0
.end method

.method public static getIntArray(I)[I
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/ArrayRes;
        .end annotation
    .end param

    .line 58
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getIntArray(I)[I

    move-result-object v0

    return-object v0
.end method

.method public static getRes()Landroid/content/res/Resources;
    .locals 1

    .line 30
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    return-object v0
.end method

.method public static getString(I)Ljava/lang/String;
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param

    .line 42
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-virtual {v0, p0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static varargs getString(I[Ljava/lang/Object;)Ljava/lang/String;
    .locals 1
    .param p0, "id"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .param p1, "formatArgs"    # [Ljava/lang/Object;

    .line 46
    sget-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    invoke-virtual {v0, p0, p1}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static init(Landroid/content/Context;)V
    .locals 1
    .param p0, "context"    # Landroid/content/Context;

    .line 22
    sput-object p0, Lme/ele/base/utils/ResourceUtil;->CONTEXT:Landroid/content/Context;

    .line 23
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sput-object v0, Lme/ele/base/utils/ResourceUtil;->RES:Landroid/content/res/Resources;

    .line 24
    return-void
.end method
