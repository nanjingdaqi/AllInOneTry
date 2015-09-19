.class public Lcom/afollestad/materialdialogs/internal/MDTintHelper_CF;
.super Ljava/lang/Object;
.source "MDTintHelper.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 23
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static createEditTextColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    .locals 6
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "color"    # I
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.afollestad.materialdialogs.internal.MDTintHelper"
        method = "createEditTextColorStateList"
    .end annotation

    .prologue
    const/4 v3, 0x3

    const/4 v5, 0x0

    .line 87
    new-array v2, v3, [[I

    .line 88
    .local v2, "states":[[I
    new-array v0, v3, [I

    .line 89
    .local v0, "colors":[I
    const/4 v1, 0x0

    .line 90
    .local v1, "i":I
    const/4 v3, 0x1

    new-array v3, v3, [I

    const v4, -0x101009e

    aput v4, v3, v5

    aput-object v3, v2, v1

    .line 91
    sget v3, Lcom/afollestad/materialdialogs/R$attr;->colorControlNormal:I

    invoke-static {p0, v3}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 92
    add-int/lit8 v1, v1, 0x1

    .line 93
    const/4 v3, 0x2

    new-array v3, v3, [I

    fill-array-data v3, :array_0

    aput-object v3, v2, v1

    .line 94
    sget v3, Lcom/afollestad/materialdialogs/R$attr;->colorControlNormal:I

    invoke-static {p0, v3}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;I)I

    move-result v3

    aput v3, v0, v1

    .line 95
    add-int/lit8 v1, v1, 0x1

    .line 96
    new-array v3, v5, [I

    aput-object v3, v2, v1

    .line 97
    aput p1, v0, v1

    .line 98
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v3

    .line 93
    nop

    :array_0
    .array-data 4
        -0x10100a7
        -0x101009c
    .end array-data
.end method

.method public static setTint(Landroid/widget/CheckBox;I)V
    .locals 8
    .param p0, "box"    # Landroid/widget/CheckBox;
    .param p1, "color"    # I

    .prologue
    const/4 v5, 0x2

    const/4 v7, 0x1

    const/4 v6, 0x0

    .line 111
    new-instance v1, Landroid/content/res/ColorStateList;

    new-array v2, v5, [[I

    new-array v3, v7, [I

    const v4, -0x10100a0

    aput v4, v3, v6

    aput-object v3, v2, v6

    new-array v3, v7, [I

    const v4, 0x10100a0

    aput v4, v3, v6

    aput-object v3, v2, v7

    new-array v3, v5, [I

    invoke-virtual {p0}, Landroid/widget/CheckBox;->getContext()Landroid/content/Context;

    move-result-object v4

    sget v5, Lcom/afollestad/materialdialogs/R$attr;->colorControlNormal:I

    invoke-static {v4, v5}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;I)I

    move-result v4

    aput v4, v3, v6

    aput p1, v3, v7

    invoke-direct {v1, v2, v3}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    .line 118
    .local v1, "sl":Landroid/content/res/ColorStateList;
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x15

    if-lt v2, v3, :cond_0

    .line 119
    invoke-virtual {p0, v1}, Landroid/widget/CheckBox;->setButtonTintList(Landroid/content/res/ColorStateList;)V

    .line 125
    :goto_0
    return-void

    .line 121
    :cond_0
    invoke-virtual {p0}, Landroid/widget/CheckBox;->getContext()Landroid/content/Context;

    move-result-object v2

    sget v3, Lcom/afollestad/materialdialogs/R$drawable;->abc_btn_check_material:I

    invoke-static {v2, v3}, Landroid/support/v4/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-static {v2}, Landroid/support/v4/graphics/drawable/DrawableCompat;->wrap(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 122
    .local v0, "drawable":Landroid/graphics/drawable/Drawable;
    invoke-static {v0, v1}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintList(Landroid/graphics/drawable/Drawable;Landroid/content/res/ColorStateList;)V

    .line 123
    invoke-virtual {p0, v0}, Landroid/widget/CheckBox;->setButtonDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0
.end method

.method public static setTint(Landroid/widget/EditText;I)V
    .locals 3
    .param p0, "editText"    # Landroid/widget/EditText;
    .param p1, "color"    # I

    .prologue
    .line 102
    invoke-virtual {p0}, Landroid/widget/EditText;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1, p1}, Lcom/afollestad/materialdialogs/internal/MDTintHelper_CF;->createEditTextColorStateList(Landroid/content/Context;I)Landroid/content/res/ColorStateList;

    move-result-object v0

    .line 103
    .local v0, "editTextColorStateList":Landroid/content/res/ColorStateList;
    instance-of v1, p0, Landroid/support/v7/widget/AppCompatEditText;

    if-eqz v1, :cond_1

    .line 104
    check-cast p0, Landroid/support/v7/widget/AppCompatEditText;

    .end local p0    # "editText":Landroid/widget/EditText;
    invoke-virtual {p0, v0}, Landroid/support/v7/widget/AppCompatEditText;->setSupportBackgroundTintList(Landroid/content/res/ColorStateList;)V

    .line 108
    :cond_0
    :goto_0
    return-void

    .line 105
    .restart local p0    # "editText":Landroid/widget/EditText;
    :cond_1
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x15

    if-lt v1, v2, :cond_0

    .line 106
    invoke-virtual {p0, v0}, Landroid/widget/EditText;->setBackgroundTintList(Landroid/content/res/ColorStateList;)V

    goto :goto_0
.end method

.method public static setTint(Landroid/widget/ProgressBar;I)V
    .locals 4
    .param p0, "progressBar"    # Landroid/widget/ProgressBar;
    .param p1, "color"    # I

    .prologue
    .line 69
    invoke-static {p1}, Landroid/content/res/ColorStateList;->valueOf(I)Landroid/content/res/ColorStateList;

    move-result-object v1

    .line 70
    .local v1, "sl":Landroid/content/res/ColorStateList;
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x15

    if-lt v2, v3, :cond_1

    .line 71
    invoke-virtual {p0, v1}, Landroid/widget/ProgressBar;->setProgressTintList(Landroid/content/res/ColorStateList;)V

    .line 72
    invoke-virtual {p0, v1}, Landroid/widget/ProgressBar;->setSecondaryProgressTintList(Landroid/content/res/ColorStateList;)V

    .line 73
    invoke-virtual {p0, v1}, Landroid/widget/ProgressBar;->setIndeterminateTintList(Landroid/content/res/ColorStateList;)V

    .line 84
    :cond_0
    :goto_0
    return-void

    .line 75
    :cond_1
    sget-object v0, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    .line 76
    .local v0, "mode":Landroid/graphics/PorterDuff$Mode;
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0xa

    if-gt v2, v3, :cond_2

    .line 77
    sget-object v0, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    .line 79
    :cond_2
    invoke-virtual {p0}, Landroid/widget/ProgressBar;->getIndeterminateDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    if-eqz v2, :cond_3

    .line 80
    invoke-virtual {p0}, Landroid/widget/ProgressBar;->getIndeterminateDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v2, p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 81
    :cond_3
    invoke-virtual {p0}, Landroid/widget/ProgressBar;->getProgressDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 82
    invoke-virtual {p0}, Landroid/widget/ProgressBar;->getProgressDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v2, p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    goto :goto_0
.end method

.method public static setTint(Landroid/widget/RadioButton;I)V
    .locals 8
    .param p0, "radioButton"    # Landroid/widget/RadioButton;
    .param p1, "color"    # I

    .prologue
    const/4 v5, 0x2

    const/4 v7, 0x1

    const/4 v6, 0x0

    .line 26
    new-instance v1, Landroid/content/res/ColorStateList;

    new-array v2, v5, [[I

    new-array v3, v7, [I

    const v4, -0x10100a0

    aput v4, v3, v6

    aput-object v3, v2, v6

    new-array v3, v7, [I

    const v4, 0x10100a0

    aput v4, v3, v6

    aput-object v3, v2, v7

    new-array v3, v5, [I

    invoke-virtual {p0}, Landroid/widget/RadioButton;->getContext()Landroid/content/Context;

    move-result-object v4

    sget v5, Lcom/afollestad/materialdialogs/R$attr;->colorControlNormal:I

    invoke-static {v4, v5}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;I)I

    move-result v4

    aput v4, v3, v6

    aput p1, v3, v7

    invoke-direct {v1, v2, v3}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    .line 33
    .local v1, "sl":Landroid/content/res/ColorStateList;
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x15

    if-lt v2, v3, :cond_0

    .line 34
    invoke-virtual {p0, v1}, Landroid/widget/RadioButton;->setButtonTintList(Landroid/content/res/ColorStateList;)V

    .line 40
    :goto_0
    return-void

    .line 36
    :cond_0
    invoke-virtual {p0}, Landroid/widget/RadioButton;->getContext()Landroid/content/Context;

    move-result-object v2

    sget v3, Lcom/afollestad/materialdialogs/R$drawable;->abc_btn_radio_material:I

    invoke-static {v2, v3}, Landroid/support/v4/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-static {v2}, Landroid/support/v4/graphics/drawable/DrawableCompat;->wrap(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 37
    .local v0, "d":Landroid/graphics/drawable/Drawable;
    invoke-static {v0, v1}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintList(Landroid/graphics/drawable/Drawable;Landroid/content/res/ColorStateList;)V

    .line 38
    invoke-virtual {p0, v0}, Landroid/widget/RadioButton;->setButtonDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0
.end method

.method public static setTint(Landroid/widget/SeekBar;I)V
    .locals 7
    .param p0, "seekBar"    # Landroid/widget/SeekBar;
    .param p1, "color"    # I

    .prologue
    const/16 v6, 0xa

    .line 43
    invoke-static {p1}, Landroid/content/res/ColorStateList;->valueOf(I)Landroid/content/res/ColorStateList;

    move-result-object v2

    .line 44
    .local v2, "s1":Landroid/content/res/ColorStateList;
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v5, 0x15

    if-lt v4, v5, :cond_1

    .line 45
    invoke-virtual {p0, v2}, Landroid/widget/SeekBar;->setThumbTintList(Landroid/content/res/ColorStateList;)V

    .line 46
    invoke-virtual {p0, v2}, Landroid/widget/SeekBar;->setProgressTintList(Landroid/content/res/ColorStateList;)V

    .line 66
    :cond_0
    :goto_0
    return-void

    .line 47
    :cond_1
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    if-le v4, v6, :cond_2

    .line 48
    invoke-virtual {p0}, Landroid/widget/SeekBar;->getProgressDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-static {v4}, Landroid/support/v4/graphics/drawable/DrawableCompat;->wrap(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 49
    .local v1, "progressDrawable":Landroid/graphics/drawable/Drawable;
    invoke-virtual {p0, v1}, Landroid/widget/SeekBar;->setProgressDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 50
    invoke-static {v1, v2}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintList(Landroid/graphics/drawable/Drawable;Landroid/content/res/ColorStateList;)V

    .line 51
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v5, 0x10

    if-lt v4, v5, :cond_0

    .line 52
    invoke-virtual {p0}, Landroid/widget/SeekBar;->getThumb()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-static {v4}, Landroid/support/v4/graphics/drawable/DrawableCompat;->wrap(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    .line 53
    .local v3, "thumbDrawable":Landroid/graphics/drawable/Drawable;
    invoke-static {v3, v2}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintList(Landroid/graphics/drawable/Drawable;Landroid/content/res/ColorStateList;)V

    .line 54
    invoke-virtual {p0, v3}, Landroid/widget/SeekBar;->setThumb(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 57
    .end local v1    # "progressDrawable":Landroid/graphics/drawable/Drawable;
    .end local v3    # "thumbDrawable":Landroid/graphics/drawable/Drawable;
    :cond_2
    sget-object v0, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    .line 58
    .local v0, "mode":Landroid/graphics/PorterDuff$Mode;
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    if-gt v4, v6, :cond_3

    .line 59
    sget-object v0, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    .line 61
    :cond_3
    invoke-virtual {p0}, Landroid/widget/SeekBar;->getIndeterminateDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    if-eqz v4, :cond_4

    .line 62
    invoke-virtual {p0}, Landroid/widget/SeekBar;->getIndeterminateDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-virtual {v4, p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 63
    :cond_4
    invoke-virtual {p0}, Landroid/widget/SeekBar;->getProgressDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    if-eqz v4, :cond_0

    .line 64
    invoke-virtual {p0}, Landroid/widget/SeekBar;->getProgressDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    invoke-virtual {v4, p1, v0}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    goto :goto_0
.end method
