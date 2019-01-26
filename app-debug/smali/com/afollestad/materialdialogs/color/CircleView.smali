.class public Lcom/afollestad/materialdialogs/color/CircleView;
.super Landroid/widget/FrameLayout;
.source "CircleView.java"


# instance fields
.field private final borderWidthLarge:I

.field private final borderWidthSmall:I

.field private final innerPaint:Landroid/graphics/Paint;

.field private final outerPaint:Landroid/graphics/Paint;

.field private selected:Z

.field private final whitePaint:Landroid/graphics/Paint;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;

    .line 40
    const/4 v0, 0x0

    const/4 v1, 0x0

    invoke-direct {p0, p1, v0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 41
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .line 44
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/afollestad/materialdialogs/color/CircleView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 45
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 4
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I

    .line 48
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 49
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 50
    .local v0, "r":Landroid/content/res/Resources;
    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    const/4 v2, 0x1

    const/high16 v3, 0x40400000    # 3.0f

    invoke-static {v2, v3, v1}, Landroid/util/TypedValue;->applyDimension(IFLandroid/util/DisplayMetrics;)F

    move-result v1

    float-to-int v1, v1

    iput v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->borderWidthSmall:I

    .line 51
    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v1

    const/high16 v3, 0x40a00000    # 5.0f

    invoke-static {v2, v3, v1}, Landroid/util/TypedValue;->applyDimension(IFLandroid/util/DisplayMetrics;)F

    move-result v1

    float-to-int v1, v1

    iput v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->borderWidthLarge:I

    .line 53
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->whitePaint:Landroid/graphics/Paint;

    .line 54
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->whitePaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 55
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->whitePaint:Landroid/graphics/Paint;

    const/4 v3, -0x1

    invoke-virtual {v1, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 56
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->innerPaint:Landroid/graphics/Paint;

    .line 57
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->innerPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 58
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->outerPaint:Landroid/graphics/Paint;

    .line 59
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->outerPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 61
    const v1, -0xbbbbbc

    invoke-direct {p0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;->update(I)V

    .line 62
    const/4 v1, 0x0

    invoke-virtual {p0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;->setWillNotDraw(Z)V

    .line 63
    return-void
.end method

.method private createSelector(I)Landroid/graphics/drawable/Drawable;
    .locals 5
    .param p1, "color"    # I

    .line 168
    new-instance v0, Landroid/graphics/drawable/ShapeDrawable;

    new-instance v1, Landroid/graphics/drawable/shapes/OvalShape;

    invoke-direct {v1}, Landroid/graphics/drawable/shapes/OvalShape;-><init>()V

    invoke-direct {v0, v1}, Landroid/graphics/drawable/ShapeDrawable;-><init>(Landroid/graphics/drawable/shapes/Shape;)V

    .line 169
    .local v0, "darkerCircle":Landroid/graphics/drawable/ShapeDrawable;
    invoke-virtual {v0}, Landroid/graphics/drawable/ShapeDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object v1

    invoke-static {p1}, Lcom/afollestad/materialdialogs/color/CircleView;->shiftColorUp(I)I

    move-result v2

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/CircleView;->translucentColor(I)I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 170
    new-instance v1, Landroid/graphics/drawable/StateListDrawable;

    invoke-direct {v1}, Landroid/graphics/drawable/StateListDrawable;-><init>()V

    .line 171
    .local v1, "stateListDrawable":Landroid/graphics/drawable/StateListDrawable;
    const/4 v2, 0x1

    new-array v2, v2, [I

    const/4 v3, 0x0

    const v4, 0x10100a7

    aput v4, v2, v3

    invoke-virtual {v1, v2, v0}, Landroid/graphics/drawable/StateListDrawable;->addState([ILandroid/graphics/drawable/Drawable;)V

    .line 172
    return-object v1
.end method

.method public static shiftColor(IF)I
    .locals 3
    .param p0, "color"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param
    .param p1, "by"    # F
        .annotation build Landroid/support/annotation/FloatRange;
            from = 0.0
            to = 2.0
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 177
    const/high16 v0, 0x3f800000    # 1.0f

    cmpl-float v0, p1, v0

    if-nez v0, :cond_0

    return p0

    .line 178
    :cond_0
    const/4 v0, 0x3

    new-array v0, v0, [F

    .line 179
    .local v0, "hsv":[F
    invoke-static {p0, v0}, Landroid/graphics/Color;->colorToHSV(I[F)V

    .line 180
    const/4 v1, 0x2

    aget v2, v0, v1

    mul-float v2, v2, p1

    aput v2, v0, v1

    .line 181
    invoke-static {v0}, Landroid/graphics/Color;->HSVToColor([F)I

    move-result v1

    return v1
.end method

.method public static shiftColorDown(I)I
    .locals 1
    .param p0, "color"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 185
    const v0, 0x3f666666    # 0.9f

    invoke-static {p0, v0}, Lcom/afollestad/materialdialogs/color/CircleView;->shiftColor(IF)I

    move-result v0

    return v0
.end method

.method public static shiftColorUp(I)I
    .locals 1
    .param p0, "color"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 189
    const v0, 0x3f8ccccd    # 1.1f

    invoke-static {p0, v0}, Lcom/afollestad/materialdialogs/color/CircleView;->shiftColor(IF)I

    move-result v0

    return v0
.end method

.method private static translucentColor(I)I
    .locals 6
    .param p0, "color"    # I
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 159
    const v0, 0x3f333333    # 0.7f

    .line 160
    .local v0, "factor":F
    invoke-static {p0}, Landroid/graphics/Color;->alpha(I)I

    move-result v1

    int-to-float v1, v1

    const v2, 0x3f333333    # 0.7f

    mul-float v1, v1, v2

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    .line 161
    .local v1, "alpha":I
    invoke-static {p0}, Landroid/graphics/Color;->red(I)I

    move-result v2

    .line 162
    .local v2, "red":I
    invoke-static {p0}, Landroid/graphics/Color;->green(I)I

    move-result v3

    .line 163
    .local v3, "green":I
    invoke-static {p0}, Landroid/graphics/Color;->blue(I)I

    move-result v4

    .line 164
    .local v4, "blue":I
    invoke-static {v1, v2, v3, v4}, Landroid/graphics/Color;->argb(IIII)I

    move-result v5

    return v5
.end method

.method private update(I)V
    .locals 6
    .param p1, "color"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param

    .line 66
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/CircleView;->innerPaint:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setColor(I)V

    .line 67
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/CircleView;->outerPaint:Landroid/graphics/Paint;

    invoke-static {p1}, Lcom/afollestad/materialdialogs/color/CircleView;->shiftColorDown(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 69
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/color/CircleView;->createSelector(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 70
    .local v0, "selector":Landroid/graphics/drawable/Drawable;
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x15

    if-lt v1, v2, :cond_0

    .line 71
    const/4 v1, 0x1

    new-array v2, v1, [[I

    new-array v3, v1, [I

    const v4, 0x10100a7

    const/4 v5, 0x0

    aput v4, v3, v5

    aput-object v3, v2, v5

    .line 74
    .local v2, "states":[[I
    new-array v1, v1, [I

    invoke-static {p1}, Lcom/afollestad/materialdialogs/color/CircleView;->shiftColorUp(I)I

    move-result v3

    aput v3, v1, v5

    .line 75
    .local v1, "colors":[I
    new-instance v3, Landroid/content/res/ColorStateList;

    invoke-direct {v3, v2, v1}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    .line 76
    .local v3, "rippleColors":Landroid/content/res/ColorStateList;
    new-instance v4, Landroid/graphics/drawable/RippleDrawable;

    const/4 v5, 0x0

    invoke-direct {v4, v3, v0, v5}, Landroid/graphics/drawable/RippleDrawable;-><init>(Landroid/content/res/ColorStateList;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    invoke-virtual {p0, v4}, Lcom/afollestad/materialdialogs/color/CircleView;->setForeground(Landroid/graphics/drawable/Drawable;)V

    .line 77
    .end local v1    # "colors":[I
    .end local v2    # "states":[[I
    .end local v3    # "rippleColors":Landroid/content/res/ColorStateList;
    goto :goto_0

    .line 78
    :cond_0
    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/color/CircleView;->setForeground(Landroid/graphics/drawable/Drawable;)V

    .line 80
    :goto_0
    return-void
.end method


# virtual methods
.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 7
    .param p1, "canvas"    # Landroid/graphics/Canvas;

    .line 133
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onDraw(Landroid/graphics/Canvas;)V

    .line 134
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    .line 135
    .local v0, "outerRadius":I
    iget-boolean v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->selected:Z

    if-eqz v1, :cond_0

    .line 136
    iget v1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->borderWidthLarge:I

    sub-int v1, v0, v1

    .line 137
    .local v1, "whiteRadius":I
    iget v2, p0, Lcom/afollestad/materialdialogs/color/CircleView;->borderWidthSmall:I

    sub-int v2, v1, v2

    .line 138
    .local v2, "innerRadius":I
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    .line 139
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredHeight()I

    move-result v4

    div-int/lit8 v4, v4, 0x2

    int-to-float v4, v4

    int-to-float v5, v0

    iget-object v6, p0, Lcom/afollestad/materialdialogs/color/CircleView;->outerPaint:Landroid/graphics/Paint;

    .line 138
    invoke-virtual {p1, v3, v4, v5, v6}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 142
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    .line 143
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredHeight()I

    move-result v4

    div-int/lit8 v4, v4, 0x2

    int-to-float v4, v4

    int-to-float v5, v1

    iget-object v6, p0, Lcom/afollestad/materialdialogs/color/CircleView;->whitePaint:Landroid/graphics/Paint;

    .line 142
    invoke-virtual {p1, v3, v4, v5, v6}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 146
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    .line 147
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredHeight()I

    move-result v4

    div-int/lit8 v4, v4, 0x2

    int-to-float v4, v4

    int-to-float v5, v2

    iget-object v6, p0, Lcom/afollestad/materialdialogs/color/CircleView;->innerPaint:Landroid/graphics/Paint;

    .line 146
    invoke-virtual {p1, v3, v4, v5, v6}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 150
    .end local v1    # "whiteRadius":I
    .end local v2    # "innerRadius":I
    goto :goto_0

    .line 151
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    .line 152
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredHeight()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    int-to-float v2, v2

    int-to-float v3, v0

    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/CircleView;->innerPaint:Landroid/graphics/Paint;

    .line 151
    invoke-virtual {p1, v1, v2, v3, v4}, Landroid/graphics/Canvas;->drawCircle(FFFLandroid/graphics/Paint;)V

    .line 156
    :goto_0
    return-void
.end method

.method protected onMeasure(II)V
    .locals 2
    .param p1, "widthMeasureSpec"    # I
    .param p2, "heightMeasureSpec"    # I

    .line 128
    invoke-super {p0, p1, p1}, Landroid/widget/FrameLayout;->onMeasure(II)V

    .line 129
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getMeasuredWidth()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;->setMeasuredDimension(II)V

    .line 130
    return-void
.end method

.method public setActivated(Z)V
    .locals 2
    .param p1, "activated"    # Z
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 118
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot use setActivated() on CircleView."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setBackground(Landroid/graphics/drawable/Drawable;)V
    .locals 2
    .param p1, "background"    # Landroid/graphics/drawable/Drawable;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 98
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot use setBackground() on CircleView."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setBackgroundColor(I)V
    .locals 0
    .param p1, "color"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param

    .line 83
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/color/CircleView;->update(I)V

    .line 84
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->requestLayout()V

    .line 85
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->invalidate()V

    .line 86
    return-void
.end method

.method public setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 2
    .param p1, "background"    # Landroid/graphics/drawable/Drawable;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 108
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot use setBackgroundDrawable() on CircleView."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setBackgroundResource(I)V
    .locals 1
    .param p1, "color"    # I
        .annotation build Landroid/support/annotation/ColorRes;
        .end annotation
    .end param

    .line 89
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0, p1}, Lcom/afollestad/materialdialogs/util/DialogUtils;->getColor(Landroid/content/Context;I)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/color/CircleView;->setBackgroundColor(I)V

    .line 90
    return-void
.end method

.method public setSelected(Z)V
    .locals 0
    .param p1, "selected"    # Z

    .line 122
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/color/CircleView;->selected:Z

    .line 123
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->requestLayout()V

    .line 124
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->invalidate()V

    .line 125
    return-void
.end method

.method public showHint(I)V
    .locals 12
    .param p1, "color"    # I

    .line 193
    const/4 v0, 0x2

    new-array v0, v0, [I

    .line 194
    .local v0, "screenPos":[I
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    .line 195
    .local v1, "displayFrame":Landroid/graphics/Rect;
    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/color/CircleView;->getLocationOnScreen([I)V

    .line 196
    invoke-virtual {p0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;->getWindowVisibleDisplayFrame(Landroid/graphics/Rect;)V

    .line 197
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getContext()Landroid/content/Context;

    move-result-object v2

    .line 198
    .local v2, "context":Landroid/content/Context;
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getWidth()I

    move-result v3

    .line 199
    .local v3, "width":I
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/CircleView;->getHeight()I

    move-result v4

    .line 200
    .local v4, "height":I
    const/4 v5, 0x1

    aget v6, v0, v5

    div-int/lit8 v7, v4, 0x2

    add-int/2addr v6, v7

    .line 201
    .local v6, "midy":I
    const/4 v7, 0x0

    aget v8, v0, v7

    div-int/lit8 v9, v3, 0x2

    add-int/2addr v8, v9

    .line 202
    .local v8, "referenceX":I
    invoke-static {p0}, Landroid/support/v4/view/ViewCompat;->getLayoutDirection(Landroid/view/View;)I

    move-result v9

    if-nez v9, :cond_0

    .line 203
    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v9

    invoke-virtual {v9}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v9

    iget v9, v9, Landroid/util/DisplayMetrics;->widthPixels:I

    .line 204
    .local v9, "screenWidth":I
    sub-int v8, v9, v8

    .line 206
    .end local v9    # "screenWidth":I
    :cond_0
    const-string v9, "#%06X"

    new-array v10, v5, [Ljava/lang/Object;

    const v11, 0xffffff

    and-int/2addr v11, p1

    invoke-static {v11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v11

    aput-object v11, v10, v7

    invoke-static {v9, v10}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v9

    invoke-static {v2, v9, v7}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v9

    .line 207
    .local v9, "cheatSheet":Landroid/widget/Toast;
    invoke-virtual {v1}, Landroid/graphics/Rect;->height()I

    move-result v10

    if-ge v6, v10, :cond_1

    .line 209
    const v7, 0x800035

    aget v5, v0, v5

    add-int/2addr v5, v4

    iget v10, v1, Landroid/graphics/Rect;->top:I

    sub-int/2addr v5, v10

    invoke-virtual {v9, v7, v8, v5}, Landroid/widget/Toast;->setGravity(III)V

    goto :goto_0

    .line 213
    :cond_1
    const/16 v5, 0x51

    invoke-virtual {v9, v5, v7, v4}, Landroid/widget/Toast;->setGravity(III)V

    .line 215
    :goto_0
    invoke-virtual {v9}, Landroid/widget/Toast;->show()V

    .line 216
    return-void
.end method
