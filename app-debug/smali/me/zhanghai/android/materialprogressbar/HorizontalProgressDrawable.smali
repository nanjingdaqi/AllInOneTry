.class public Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;
.super Landroid/graphics/drawable/LayerDrawable;
.source "HorizontalProgressDrawable.java"

# interfaces
.implements Lme/zhanghai/android/materialprogressbar/IntrinsicPaddingDrawable;
.implements Lme/zhanghai/android/materialprogressbar/ShowBackgroundDrawable;
.implements Lme/zhanghai/android/materialprogressbar/TintableDrawable;


# static fields
.field private static final TAG:Ljava/lang/String;


# instance fields
.field private mBackgroundAlpha:F

.field private mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

.field private mHasSecondaryProgressTint:Z

.field private mHasSecondaryProgressTintColor:Z

.field private mProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

.field private mSecondaryProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

.field private mSecondaryProgressTint:Landroid/content/res/ColorStateList;

.field private mSecondaryProgressTintColor:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 29
    const-class v0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->TAG:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 5
    .param p1, "context"    # Landroid/content/Context;

    .line 48
    const/4 v0, 0x3

    new-array v0, v0, [Landroid/graphics/drawable/Drawable;

    new-instance v1, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-direct {v1, p1}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;-><init>(Landroid/content/Context;)V

    const/4 v2, 0x0

    aput-object v1, v0, v2

    new-instance v1, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-direct {v1, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;-><init>(Landroid/content/Context;)V

    const/4 v3, 0x1

    aput-object v1, v0, v3

    new-instance v1, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-direct {v1, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;-><init>(Landroid/content/Context;)V

    const/4 v4, 0x2

    aput-object v1, v0, v4

    invoke-direct {p0, v0}, Landroid/graphics/drawable/LayerDrawable;-><init>([Landroid/graphics/drawable/Drawable;)V

    .line 54
    const v0, 0x1010033

    invoke-static {v0, p1}, Lme/zhanghai/android/materialprogressbar/internal/ThemeUtils;->getFloatFromAttrRes(ILandroid/content/Context;)F

    move-result v0

    iput v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundAlpha:F

    .line 56
    const/high16 v0, 0x1020000

    invoke-virtual {p0, v2, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->setId(II)V

    .line 57
    invoke-virtual {p0, v2}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    check-cast v0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    iput-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    .line 58
    const v0, 0x102000f

    invoke-virtual {p0, v3, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->setId(II)V

    .line 59
    invoke-virtual {p0, v3}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    check-cast v0, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    iput-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    .line 60
    const v0, 0x102000d

    invoke-virtual {p0, v4, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->setId(II)V

    .line 61
    invoke-virtual {p0, v4}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    check-cast v0, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    iput-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    .line 63
    sget v0, Lme/zhanghai/android/materialprogressbar/R$attr;->colorControlActivated:I

    invoke-static {v0, p1}, Lme/zhanghai/android/materialprogressbar/internal/ThemeUtils;->getColorFromAttrRes(ILandroid/content/Context;)I

    move-result v0

    .line 65
    .local v0, "controlActivatedColor":I
    invoke-virtual {p0, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->setTint(I)V

    .line 66
    return-void
.end method

.method private compositeAlpha(FF)F
    .locals 1
    .param p1, "alpha1"    # F
    .param p2, "alpha2"    # F

    .line 188
    const/high16 v0, 0x3f800000    # 1.0f

    sub-float/2addr v0, p1

    mul-float v0, v0, p2

    add-float/2addr v0, p1

    return v0
.end method

.method private setSecondaryProgressTint(I)V
    .locals 1
    .param p1, "tintColor"    # I

    .line 150
    const/4 v0, 0x1

    iput-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mHasSecondaryProgressTintColor:Z

    .line 151
    iput p1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressTintColor:I

    .line 152
    const/4 v0, 0x0

    iput-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mHasSecondaryProgressTint:Z

    .line 153
    invoke-direct {p0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->updateSecondaryProgressTint()V

    .line 154
    return-void
.end method

.method private setSecondaryProgressTintList(Landroid/content/res/ColorStateList;)V
    .locals 1
    .param p1, "tint"    # Landroid/content/res/ColorStateList;

    .line 157
    const/4 v0, 0x0

    iput-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mHasSecondaryProgressTintColor:Z

    .line 158
    const/4 v0, 0x1

    iput-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mHasSecondaryProgressTint:Z

    .line 159
    iput-object p1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressTint:Landroid/content/res/ColorStateList;

    .line 160
    invoke-direct {p0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->updateSecondaryProgressTint()V

    .line 161
    return-void
.end method

.method private updateSecondaryProgressTint()V
    .locals 4
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    .line 165
    iget-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mHasSecondaryProgressTintColor:Z

    const/high16 v1, 0x437f0000    # 255.0f

    if-eqz v0, :cond_1

    .line 166
    iget v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressTintColor:I

    .line 167
    .local v0, "tintColor":I
    invoke-virtual {p0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->getShowBackground()Z

    move-result v2

    if-nez v2, :cond_0

    .line 170
    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v2, v1

    .line 171
    .local v2, "backgroundAlpha":F
    nop

    .line 172
    invoke-direct {p0, v2, v2}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->compositeAlpha(FF)F

    move-result v3

    mul-float v3, v3, v1

    .line 171
    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v1

    invoke-static {v0, v1}, Landroid/support/v4/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v0

    .line 174
    .end local v2    # "backgroundAlpha":F
    :cond_0
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v1, v0}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setTint(I)V

    .line 175
    .end local v0    # "tintColor":I
    goto :goto_0

    :cond_1
    iget-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mHasSecondaryProgressTint:Z

    if-eqz v0, :cond_3

    .line 176
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressTint:Landroid/content/res/ColorStateList;

    .line 177
    .local v0, "tint":Landroid/content/res/ColorStateList;
    invoke-virtual {p0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->getShowBackground()Z

    move-result v2

    if-nez v2, :cond_2

    .line 179
    iget v2, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundAlpha:F

    iget v3, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundAlpha:F

    invoke-direct {p0, v2, v3}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->compositeAlpha(FF)F

    move-result v2

    mul-float v2, v2, v1

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/content/res/ColorStateList;->withAlpha(I)Landroid/content/res/ColorStateList;

    move-result-object v0

    .line 182
    :cond_2
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v1, v0}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setTintList(Landroid/content/res/ColorStateList;)V

    .line 184
    .end local v0    # "tint":Landroid/content/res/ColorStateList;
    :cond_3
    :goto_0
    return-void
.end method


# virtual methods
.method public getShowBackground()Z
    .locals 1

    .line 72
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->getShowBackground()Z

    move-result v0

    return v0
.end method

.method public getUseIntrinsicPadding()Z
    .locals 1

    .line 91
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->getUseIntrinsicPadding()Z

    move-result v0

    return v0
.end method

.method public setShowBackground(Z)V
    .locals 1
    .param p1, "show"    # Z

    .line 80
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->getShowBackground()Z

    move-result v0

    if-eq v0, p1, :cond_0

    .line 81
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->setShowBackground(Z)V

    .line 82
    invoke-direct {p0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->updateSecondaryProgressTint()V

    .line 84
    :cond_0
    return-void
.end method

.method public setTint(I)V
    .locals 2
    .param p1, "tintColor"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    .line 111
    nop

    .line 112
    invoke-static {p1}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    int-to-float v0, v0

    iget v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundAlpha:F

    mul-float v0, v0, v1

    .line 111
    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    invoke-static {p1, v0}, Landroid/support/v4/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v0

    .line 113
    .local v0, "backgroundTintColor":I
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v1, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->setTint(I)V

    .line 114
    invoke-direct {p0, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->setSecondaryProgressTint(I)V

    .line 115
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v1, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setTint(I)V

    .line 116
    return-void
.end method

.method public setTintList(Landroid/content/res/ColorStateList;)V
    .locals 2
    .param p1, "tint"    # Landroid/content/res/ColorStateList;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    .line 125
    if-eqz p1, :cond_1

    .line 126
    invoke-virtual {p1}, Landroid/content/res/ColorStateList;->isOpaque()Z

    move-result v0

    if-nez v0, :cond_0

    .line 127
    sget-object v0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->TAG:Ljava/lang/String;

    const-string v1, "setTintList() called with a non-opaque ColorStateList, its original alpha will be discarded"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 129
    :cond_0
    const/high16 v0, 0x437f0000    # 255.0f

    iget v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundAlpha:F

    mul-float v1, v1, v0

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/content/res/ColorStateList;->withAlpha(I)Landroid/content/res/ColorStateList;

    move-result-object v0

    goto :goto_0

    .line 131
    :cond_1
    const/4 v0, 0x0

    .line 133
    .local v0, "backgroundTint":Landroid/content/res/ColorStateList;
    :goto_0
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v1, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->setTintList(Landroid/content/res/ColorStateList;)V

    .line 134
    invoke-direct {p0, v0}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->setSecondaryProgressTintList(Landroid/content/res/ColorStateList;)V

    .line 135
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v1, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setTintList(Landroid/content/res/ColorStateList;)V

    .line 136
    return-void
.end method

.method public setTintMode(Landroid/graphics/PorterDuff$Mode;)V
    .locals 1
    .param p1, "tintMode"    # Landroid/graphics/PorterDuff$Mode;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    .line 144
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->setTintMode(Landroid/graphics/PorterDuff$Mode;)V

    .line 145
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setTintMode(Landroid/graphics/PorterDuff$Mode;)V

    .line 146
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setTintMode(Landroid/graphics/PorterDuff$Mode;)V

    .line 147
    return-void
.end method

.method public setUseIntrinsicPadding(Z)V
    .locals 1
    .param p1, "useIntrinsicPadding"    # Z

    .line 99
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mBackgroundDrawable:Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/HorizontalProgressBackgroundDrawable;->setUseIntrinsicPadding(Z)V

    .line 100
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mSecondaryProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setUseIntrinsicPadding(Z)V

    .line 101
    iget-object v0, p0, Lme/zhanghai/android/materialprogressbar/HorizontalProgressDrawable;->mProgressDrawable:Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;

    invoke-virtual {v0, p1}, Lme/zhanghai/android/materialprogressbar/SingleHorizontalProgressDrawable;->setUseIntrinsicPadding(Z)V

    .line 102
    return-void
.end method
