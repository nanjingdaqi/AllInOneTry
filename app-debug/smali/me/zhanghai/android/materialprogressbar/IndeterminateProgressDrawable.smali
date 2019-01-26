.class public Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;
.super Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;
.source "IndeterminateProgressDrawable.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;,
        Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;
    }
.end annotation


# static fields
.field private static final PADDED_INTRINSIC_SIZE_DP:I = 0x30

.field private static final PROGRESS_INTRINSIC_SIZE_DP:I = 0x2a

.field private static final RECT_BOUND:Landroid/graphics/RectF;

.field private static final RECT_PADDED_BOUND:Landroid/graphics/RectF;

.field private static final RECT_PROGRESS:Landroid/graphics/RectF;


# instance fields
.field private mPaddedIntrinsicSize:I

.field private mProgressIntrinsicSize:I

.field private mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

.field private mRingRotation:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 22
    new-instance v0, Landroid/graphics/RectF;

    const/high16 v1, 0x41a80000    # 21.0f

    const/high16 v2, -0x3e580000    # -21.0f

    invoke-direct {v0, v2, v2, v1, v1}, Landroid/graphics/RectF;-><init>(FFFF)V

    sput-object v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_BOUND:Landroid/graphics/RectF;

    .line 23
    new-instance v0, Landroid/graphics/RectF;

    const/high16 v1, 0x41c00000    # 24.0f

    const/high16 v2, -0x3e400000    # -24.0f

    invoke-direct {v0, v2, v2, v1, v1}, Landroid/graphics/RectF;-><init>(FFFF)V

    sput-object v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PADDED_BOUND:Landroid/graphics/RectF;

    .line 24
    new-instance v0, Landroid/graphics/RectF;

    const/high16 v1, 0x41980000    # 19.0f

    const/high16 v2, -0x3e680000    # -19.0f

    invoke-direct {v0, v2, v2, v1, v1}, Landroid/graphics/RectF;-><init>(FFFF)V

    sput-object v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PROGRESS:Landroid/graphics/RectF;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 4
    .param p1, "context"    # Landroid/content/Context;

    .line 38
    invoke-direct {p0, p1}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;-><init>(Landroid/content/Context;)V

    .line 29
    new-instance v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;-><init>(Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$1;)V

    iput-object v0, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    .line 30
    new-instance v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;

    invoke-direct {v0, v1}, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;-><init>(Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$1;)V

    iput-object v0, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingRotation:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;

    .line 40
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 41
    .local v0, "density":F
    const/high16 v1, 0x42280000    # 42.0f

    mul-float v1, v1, v0

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    iput v1, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mProgressIntrinsicSize:I

    .line 42
    const/high16 v1, 0x42400000    # 48.0f

    mul-float v1, v1, v0

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    iput v1, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mPaddedIntrinsicSize:I

    .line 44
    const/4 v1, 0x2

    new-array v1, v1, [Landroid/animation/Animator;

    iget-object v2, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    .line 45
    invoke-static {v2}, Lme/zhanghai/android/materialprogressbar/Animators;->createIndeterminate(Ljava/lang/Object;)Landroid/animation/Animator;

    move-result-object v2

    const/4 v3, 0x0

    aput-object v2, v1, v3

    iget-object v2, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingRotation:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;

    .line 46
    invoke-static {v2}, Lme/zhanghai/android/materialprogressbar/Animators;->createIndeterminateRotation(Ljava/lang/Object;)Landroid/animation/Animator;

    move-result-object v2

    const/4 v3, 0x1

    aput-object v2, v1, v3

    iput-object v1, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mAnimators:[Landroid/animation/Animator;

    .line 48
    return-void
.end method

.method private drawRing(Landroid/graphics/Canvas;Landroid/graphics/Paint;)V
    .locals 10
    .param p1, "canvas"    # Landroid/graphics/Canvas;
    .param p2, "paint"    # Landroid/graphics/Paint;

    .line 94
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v0

    .line 95
    .local v0, "saveCount":I
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingRotation:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;

    invoke-static {v1}, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;->access$200(Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingRotation;)F

    move-result v1

    invoke-virtual {p1, v1}, Landroid/graphics/Canvas;->rotate(F)V

    .line 98
    iget-object v1, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    iget v1, v1, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;->mTrimPathOffset:F

    iget-object v2, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    iget v2, v2, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;->mTrimPathStart:F

    add-float/2addr v1, v2

    const/high16 v2, 0x43b40000    # 360.0f

    mul-float v1, v1, v2

    const/high16 v3, -0x3d4c0000    # -90.0f

    add-float/2addr v1, v3

    .line 100
    .local v1, "startAngle":F
    iget-object v3, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    iget v3, v3, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;->mTrimPathEnd:F

    iget-object v4, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mRingPathTransform:Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;

    iget v4, v4, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable$RingPathTransform;->mTrimPathStart:F

    sub-float/2addr v3, v4

    mul-float v2, v2, v3

    .line 102
    .local v2, "sweepAngle":F
    sget-object v5, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PROGRESS:Landroid/graphics/RectF;

    const/4 v8, 0x0

    move-object v4, p1

    move v6, v1

    move v7, v2

    move-object v9, p2

    invoke-virtual/range {v4 .. v9}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 104
    invoke-virtual {p1, v0}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 105
    return-void
.end method

.method private getIntrinsicSize()I
    .locals 1

    .line 51
    iget-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mUseIntrinsicPadding:Z

    if-eqz v0, :cond_0

    iget v0, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mPaddedIntrinsicSize:I

    goto :goto_0

    :cond_0
    iget v0, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mProgressIntrinsicSize:I

    :goto_0
    return v0
.end method


# virtual methods
.method public bridge synthetic draw(Landroid/graphics/Canvas;)V
    .locals 0

    .line 18
    invoke-super {p0, p1}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;->draw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method public getIntrinsicHeight()I
    .locals 1

    .line 67
    invoke-direct {p0}, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->getIntrinsicSize()I

    move-result v0

    return v0
.end method

.method public getIntrinsicWidth()I
    .locals 1

    .line 59
    invoke-direct {p0}, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->getIntrinsicSize()I

    move-result v0

    return v0
.end method

.method public bridge synthetic getUseIntrinsicPadding()Z
    .locals 1

    .line 18
    invoke-super {p0}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;->getUseIntrinsicPadding()Z

    move-result v0

    return v0
.end method

.method public bridge synthetic isRunning()Z
    .locals 1

    .line 18
    invoke-super {p0}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;->isRunning()Z

    move-result v0

    return v0
.end method

.method protected onDraw(Landroid/graphics/Canvas;IILandroid/graphics/Paint;)V
    .locals 4
    .param p1, "canvas"    # Landroid/graphics/Canvas;
    .param p2, "width"    # I
    .param p3, "height"    # I
    .param p4, "paint"    # Landroid/graphics/Paint;

    .line 81
    iget-boolean v0, p0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->mUseIntrinsicPadding:Z

    const/high16 v1, 0x40000000    # 2.0f

    if-eqz v0, :cond_0

    .line 82
    int-to-float v0, p2

    sget-object v2, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PADDED_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->width()F

    move-result v2

    div-float/2addr v0, v2

    int-to-float v2, p3

    sget-object v3, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PADDED_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v3}, Landroid/graphics/RectF;->height()F

    move-result v3

    div-float/2addr v2, v3

    invoke-virtual {p1, v0, v2}, Landroid/graphics/Canvas;->scale(FF)V

    .line 83
    sget-object v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PADDED_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v0

    div-float/2addr v0, v1

    sget-object v2, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_PADDED_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->height()F

    move-result v2

    div-float/2addr v2, v1

    invoke-virtual {p1, v0, v2}, Landroid/graphics/Canvas;->translate(FF)V

    goto :goto_0

    .line 85
    :cond_0
    int-to-float v0, p2

    sget-object v2, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->width()F

    move-result v2

    div-float/2addr v0, v2

    int-to-float v2, p3

    sget-object v3, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v3}, Landroid/graphics/RectF;->height()F

    move-result v3

    div-float/2addr v2, v3

    invoke-virtual {p1, v0, v2}, Landroid/graphics/Canvas;->scale(FF)V

    .line 86
    sget-object v0, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v0

    div-float/2addr v0, v1

    sget-object v2, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->RECT_BOUND:Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->height()F

    move-result v2

    div-float/2addr v2, v1

    invoke-virtual {p1, v0, v2}, Landroid/graphics/Canvas;->translate(FF)V

    .line 89
    :goto_0
    invoke-direct {p0, p1, p4}, Lme/zhanghai/android/materialprogressbar/IndeterminateProgressDrawable;->drawRing(Landroid/graphics/Canvas;Landroid/graphics/Paint;)V

    .line 90
    return-void
.end method

.method protected onPreparePaint(Landroid/graphics/Paint;)V
    .locals 1
    .param p1, "paint"    # Landroid/graphics/Paint;

    .line 72
    sget-object v0, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 73
    const/high16 v0, 0x40800000    # 4.0f

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 74
    sget-object v0, Landroid/graphics/Paint$Cap;->SQUARE:Landroid/graphics/Paint$Cap;

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    .line 75
    sget-object v0, Landroid/graphics/Paint$Join;->MITER:Landroid/graphics/Paint$Join;

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setStrokeJoin(Landroid/graphics/Paint$Join;)V

    .line 76
    return-void
.end method

.method public bridge synthetic setUseIntrinsicPadding(Z)V
    .locals 0

    .line 18
    invoke-super {p0, p1}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;->setUseIntrinsicPadding(Z)V

    return-void
.end method

.method public bridge synthetic start()V
    .locals 0

    .line 18
    invoke-super {p0}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;->start()V

    return-void
.end method

.method public bridge synthetic stop()V
    .locals 0

    .line 18
    invoke-super {p0}, Lme/zhanghai/android/materialprogressbar/BaseIndeterminateProgressDrawable;->stop()V

    return-void
.end method
