.class Landroid/support/design/widget/FloatingActionButtonLollipop;
.super Landroid/support/design/widget/FloatingActionButtonIcs;
.source "FloatingActionButtonLollipop.java"


# annotations
.annotation build Landroid/annotation/TargetApi;
    value = 0x15
.end annotation


# instance fields
.field private mInsetDrawable:Landroid/graphics/drawable/InsetDrawable;

.field private final mInterpolator:Landroid/view/animation/Interpolator;


# direct methods
.method constructor <init>(Landroid/support/design/widget/VisibilityAwareImageButton;Landroid/support/design/widget/ShadowViewDelegate;)V
    .locals 2
    .param p1, "view"    # Landroid/support/design/widget/VisibilityAwareImageButton;
    .param p2, "shadowViewDelegate"    # Landroid/support/design/widget/ShadowViewDelegate;

    .line 44
    invoke-direct {p0, p1, p2}, Landroid/support/design/widget/FloatingActionButtonIcs;-><init>(Landroid/support/design/widget/VisibilityAwareImageButton;Landroid/support/design/widget/ShadowViewDelegate;)V

    .line 46
    invoke-virtual {p1}, Landroid/support/design/widget/VisibilityAwareImageButton;->isInEditMode()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    invoke-virtual {v0}, Landroid/support/design/widget/VisibilityAwareImageButton;->getContext()Landroid/content/Context;

    move-result-object v0

    const v1, 0x10c000d

    invoke-static {v0, v1}, Landroid/view/animation/AnimationUtils;->loadInterpolator(Landroid/content/Context;I)Landroid/view/animation/Interpolator;

    move-result-object v0

    :goto_0
    iput-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mInterpolator:Landroid/view/animation/Interpolator;

    .line 49
    return-void
.end method

.method private setupAnimator(Landroid/animation/Animator;)Landroid/animation/Animator;
    .locals 1
    .param p1, "animator"    # Landroid/animation/Animator;

    .line 150
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mInterpolator:Landroid/view/animation/Interpolator;

    invoke-virtual {p1, v0}, Landroid/animation/Animator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 151
    return-object p1
.end method


# virtual methods
.method public getElevation()F
    .locals 1

    .line 115
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    invoke-virtual {v0}, Landroid/support/design/widget/VisibilityAwareImageButton;->getElevation()F

    move-result v0

    return v0
.end method

.method getPadding(Landroid/graphics/Rect;)V
    .locals 6
    .param p1, "rect"    # Landroid/graphics/Rect;

    .line 160
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    invoke-interface {v0}, Landroid/support/design/widget/ShadowViewDelegate;->isCompatPaddingEnabled()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 161
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    invoke-interface {v0}, Landroid/support/design/widget/ShadowViewDelegate;->getRadius()F

    move-result v0

    .line 162
    .local v0, "radius":F
    invoke-virtual {p0}, Landroid/support/design/widget/FloatingActionButtonLollipop;->getElevation()F

    move-result v2

    iget v3, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mPressedTranslationZ:F

    add-float/2addr v2, v3

    .line 163
    .local v2, "maxShadowSize":F
    invoke-static {v2, v0, v1}, Landroid/support/design/widget/ShadowDrawableWrapper;->calculateHorizontalPadding(FFZ)F

    move-result v3

    float-to-double v3, v3

    invoke-static {v3, v4}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v3

    double-to-int v3, v3

    .line 165
    .local v3, "hPadding":I
    invoke-static {v2, v0, v1}, Landroid/support/design/widget/ShadowDrawableWrapper;->calculateVerticalPadding(FFZ)F

    move-result v1

    float-to-double v4, v1

    invoke-static {v4, v5}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v4

    double-to-int v1, v4

    .line 167
    .local v1, "vPadding":I
    invoke-virtual {p1, v3, v1, v3, v1}, Landroid/graphics/Rect;->set(IIII)V

    .line 168
    .end local v0    # "radius":F
    .end local v1    # "vPadding":I
    .end local v2    # "maxShadowSize":F
    .end local v3    # "hPadding":I
    goto :goto_0

    .line 169
    :cond_0
    invoke-virtual {p1, v1, v1, v1, v1}, Landroid/graphics/Rect;->set(IIII)V

    .line 171
    :goto_0
    return-void
.end method

.method jumpDrawableToCurrentState()V
    .locals 0

    .line 142
    return-void
.end method

.method newCircularDrawable()Landroid/support/design/widget/CircularBorderDrawable;
    .locals 1

    .line 156
    new-instance v0, Landroid/support/design/widget/CircularBorderDrawableLollipop;

    invoke-direct {v0}, Landroid/support/design/widget/CircularBorderDrawableLollipop;-><init>()V

    return-object v0
.end method

.method onCompatShadowChanged()V
    .locals 0

    .line 120
    invoke-virtual {p0}, Landroid/support/design/widget/FloatingActionButtonLollipop;->updatePadding()V

    .line 121
    return-void
.end method

.method onDrawableStateChanged([I)V
    .locals 0
    .param p1, "state"    # [I

    .line 137
    return-void
.end method

.method public onElevationChanged(F)V
    .locals 1
    .param p1, "elevation"    # F

    .line 89
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    invoke-virtual {v0, p1}, Landroid/support/design/widget/VisibilityAwareImageButton;->setElevation(F)V

    .line 90
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    invoke-interface {v0}, Landroid/support/design/widget/ShadowViewDelegate;->isCompatPaddingEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 91
    invoke-virtual {p0}, Landroid/support/design/widget/FloatingActionButtonLollipop;->updatePadding()V

    .line 93
    :cond_0
    return-void
.end method

.method onPaddingUpdated(Landroid/graphics/Rect;)V
    .locals 7
    .param p1, "padding"    # Landroid/graphics/Rect;

    .line 125
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    invoke-interface {v0}, Landroid/support/design/widget/ShadowViewDelegate;->isCompatPaddingEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 126
    new-instance v0, Landroid/graphics/drawable/InsetDrawable;

    iget-object v2, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    iget v3, p1, Landroid/graphics/Rect;->left:I

    iget v4, p1, Landroid/graphics/Rect;->top:I

    iget v5, p1, Landroid/graphics/Rect;->right:I

    iget v6, p1, Landroid/graphics/Rect;->bottom:I

    move-object v1, v0

    invoke-direct/range {v1 .. v6}, Landroid/graphics/drawable/InsetDrawable;-><init>(Landroid/graphics/drawable/Drawable;IIII)V

    iput-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mInsetDrawable:Landroid/graphics/drawable/InsetDrawable;

    .line 128
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    iget-object v1, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mInsetDrawable:Landroid/graphics/drawable/InsetDrawable;

    invoke-interface {v0, v1}, Landroid/support/design/widget/ShadowViewDelegate;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 130
    :cond_0
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    iget-object v1, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    invoke-interface {v0, v1}, Landroid/support/design/widget/ShadowViewDelegate;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 132
    :goto_0
    return-void
.end method

.method onTranslationZChanged(F)V
    .locals 7
    .param p1, "translationZ"    # F

    .line 97
    new-instance v0, Landroid/animation/StateListAnimator;

    invoke-direct {v0}, Landroid/animation/StateListAnimator;-><init>()V

    .line 99
    .local v0, "stateListAnimator":Landroid/animation/StateListAnimator;
    sget-object v1, Landroid/support/design/widget/FloatingActionButtonLollipop;->PRESSED_ENABLED_STATE_SET:[I

    iget-object v2, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    const-string v3, "translationZ"

    const/4 v4, 0x1

    new-array v5, v4, [F

    const/4 v6, 0x0

    aput p1, v5, v6

    invoke-static {v2, v3, v5}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    invoke-direct {p0, v2}, Landroid/support/design/widget/FloatingActionButtonLollipop;->setupAnimator(Landroid/animation/Animator;)Landroid/animation/Animator;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/animation/StateListAnimator;->addState([ILandroid/animation/Animator;)V

    .line 101
    sget-object v1, Landroid/support/design/widget/FloatingActionButtonLollipop;->FOCUSED_ENABLED_STATE_SET:[I

    iget-object v2, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    const-string v3, "translationZ"

    new-array v5, v4, [F

    aput p1, v5, v6

    invoke-static {v2, v3, v5}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    invoke-direct {p0, v2}, Landroid/support/design/widget/FloatingActionButtonLollipop;->setupAnimator(Landroid/animation/Animator;)Landroid/animation/Animator;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/animation/StateListAnimator;->addState([ILandroid/animation/Animator;)V

    .line 104
    sget-object v1, Landroid/support/design/widget/FloatingActionButtonLollipop;->EMPTY_STATE_SET:[I

    iget-object v2, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    const-string v3, "translationZ"

    new-array v4, v4, [F

    const/4 v5, 0x0

    aput v5, v4, v6

    invoke-static {v2, v3, v4}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v2

    invoke-direct {p0, v2}, Landroid/support/design/widget/FloatingActionButtonLollipop;->setupAnimator(Landroid/animation/Animator;)Landroid/animation/Animator;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/animation/StateListAnimator;->addState([ILandroid/animation/Animator;)V

    .line 106
    iget-object v1, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mView:Landroid/support/design/widget/VisibilityAwareImageButton;

    invoke-virtual {v1, v0}, Landroid/support/design/widget/VisibilityAwareImageButton;->setStateListAnimator(Landroid/animation/StateListAnimator;)V

    .line 108
    iget-object v1, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    invoke-interface {v1}, Landroid/support/design/widget/ShadowViewDelegate;->isCompatPaddingEnabled()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 109
    invoke-virtual {p0}, Landroid/support/design/widget/FloatingActionButtonLollipop;->updatePadding()V

    .line 111
    :cond_0
    return-void
.end method

.method requirePreDrawListener()Z
    .locals 1

    .line 146
    const/4 v0, 0x0

    return v0
.end method

.method setBackgroundDrawable(Landroid/content/res/ColorStateList;Landroid/graphics/PorterDuff$Mode;II)V
    .locals 5
    .param p1, "backgroundTint"    # Landroid/content/res/ColorStateList;
    .param p2, "backgroundTintMode"    # Landroid/graphics/PorterDuff$Mode;
    .param p3, "rippleColor"    # I
    .param p4, "borderWidth"    # I

    .line 55
    invoke-virtual {p0}, Landroid/support/design/widget/FloatingActionButtonLollipop;->createShapeDrawable()Landroid/graphics/drawable/GradientDrawable;

    move-result-object v0

    invoke-static {v0}, Landroid/support/v4/graphics/drawable/DrawableCompat;->wrap(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShapeDrawable:Landroid/graphics/drawable/Drawable;

    .line 56
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShapeDrawable:Landroid/graphics/drawable/Drawable;

    invoke-static {v0, p1}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintList(Landroid/graphics/drawable/Drawable;Landroid/content/res/ColorStateList;)V

    .line 57
    if-eqz p2, :cond_0

    .line 58
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShapeDrawable:Landroid/graphics/drawable/Drawable;

    invoke-static {v0, p2}, Landroid/support/v4/graphics/drawable/DrawableCompat;->setTintMode(Landroid/graphics/drawable/Drawable;Landroid/graphics/PorterDuff$Mode;)V

    .line 62
    :cond_0
    const/4 v0, 0x0

    if-lez p4, :cond_1

    .line 63
    invoke-virtual {p0, p4, p1}, Landroid/support/design/widget/FloatingActionButtonLollipop;->createBorderDrawable(ILandroid/content/res/ColorStateList;)Landroid/support/design/widget/CircularBorderDrawable;

    move-result-object v1

    iput-object v1, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mBorderDrawable:Landroid/support/design/widget/CircularBorderDrawable;

    .line 64
    new-instance v1, Landroid/graphics/drawable/LayerDrawable;

    const/4 v2, 0x2

    new-array v2, v2, [Landroid/graphics/drawable/Drawable;

    const/4 v3, 0x0

    iget-object v4, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mBorderDrawable:Landroid/support/design/widget/CircularBorderDrawable;

    aput-object v4, v2, v3

    const/4 v3, 0x1

    iget-object v4, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShapeDrawable:Landroid/graphics/drawable/Drawable;

    aput-object v4, v2, v3

    invoke-direct {v1, v2}, Landroid/graphics/drawable/LayerDrawable;-><init>([Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 66
    :cond_1
    iput-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mBorderDrawable:Landroid/support/design/widget/CircularBorderDrawable;

    .line 67
    iget-object v1, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShapeDrawable:Landroid/graphics/drawable/Drawable;

    .line 70
    .local v1, "rippleContent":Landroid/graphics/drawable/Drawable;
    :goto_0
    new-instance v2, Landroid/graphics/drawable/RippleDrawable;

    invoke-static {p3}, Landroid/content/res/ColorStateList;->valueOf(I)Landroid/content/res/ColorStateList;

    move-result-object v3

    invoke-direct {v2, v3, v1, v0}, Landroid/graphics/drawable/RippleDrawable;-><init>(Landroid/content/res/ColorStateList;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    iput-object v2, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    .line 73
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    iput-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mContentBackground:Landroid/graphics/drawable/Drawable;

    .line 75
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mShadowViewDelegate:Landroid/support/design/widget/ShadowViewDelegate;

    iget-object v2, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    invoke-interface {v0, v2}, Landroid/support/design/widget/ShadowViewDelegate;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 76
    return-void
.end method

.method setRippleColor(I)V
    .locals 2
    .param p1, "rippleColor"    # I

    .line 80
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    instance-of v0, v0, Landroid/graphics/drawable/RippleDrawable;

    if-eqz v0, :cond_0

    .line 81
    iget-object v0, p0, Landroid/support/design/widget/FloatingActionButtonLollipop;->mRippleDrawable:Landroid/graphics/drawable/Drawable;

    check-cast v0, Landroid/graphics/drawable/RippleDrawable;

    invoke-static {p1}, Landroid/content/res/ColorStateList;->valueOf(I)Landroid/content/res/ColorStateList;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/RippleDrawable;->setColor(Landroid/content/res/ColorStateList;)V

    goto :goto_0

    .line 83
    :cond_0
    invoke-super {p0, p1}, Landroid/support/design/widget/FloatingActionButtonIcs;->setRippleColor(I)V

    .line 85
    :goto_0
    return-void
.end method