.class public Lcom/afollestad/materialdialogs/internal/MDButton;
.super Landroid/widget/TextView;
.source "MDButton.java"


# instance fields
.field private defaultBackground:Landroid/graphics/drawable/Drawable;

.field private stacked:Z

.field private stackedBackground:Landroid/graphics/drawable/Drawable;

.field private stackedEndPadding:I

.field private stackedGravity:Lcom/afollestad/materialdialogs/GravityEnum;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .line 28
    invoke-direct {p0, p1, p2}, Landroid/widget/TextView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 20
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stacked:Z

    .line 29
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/internal/MDButton;->init(Landroid/content/Context;)V

    .line 30
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I

    .line 33
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/TextView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 20
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stacked:Z

    .line 34
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/internal/MDButton;->init(Landroid/content/Context;)V

    .line 35
    return-void
.end method

.method private init(Landroid/content/Context;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;

    .line 38
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/afollestad/materialdialogs/R$dimen;->md_dialog_frame_margin:I

    .line 39
    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedEndPadding:I

    .line 40
    sget-object v0, Lcom/afollestad/materialdialogs/GravityEnum;->END:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 41
    return-void
.end method


# virtual methods
.method public setAllCapsCompat(Z)V
    .locals 2
    .param p1, "allCaps"    # Z

    .line 83
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xe

    if-lt v0, v1, :cond_0

    .line 84
    invoke-virtual {p0, p1}, Lcom/afollestad/materialdialogs/internal/MDButton;->setAllCaps(Z)V

    goto :goto_0

    .line 86
    :cond_0
    if-eqz p1, :cond_1

    .line 87
    new-instance v0, Landroid/support/v7/text/AllCapsTransformationMethod;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDButton;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/support/v7/text/AllCapsTransformationMethod;-><init>(Landroid/content/Context;)V

    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/internal/MDButton;->setTransformationMethod(Landroid/text/method/TransformationMethod;)V

    goto :goto_0

    .line 89
    :cond_1
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/internal/MDButton;->setTransformationMethod(Landroid/text/method/TransformationMethod;)V

    .line 91
    :goto_0
    return-void
.end method

.method public setDefaultSelector(Landroid/graphics/drawable/Drawable;)V
    .locals 2
    .param p1, "d"    # Landroid/graphics/drawable/Drawable;

    .line 77
    iput-object p1, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->defaultBackground:Landroid/graphics/drawable/Drawable;

    .line 78
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stacked:Z

    if-nez v0, :cond_0

    .line 79
    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lcom/afollestad/materialdialogs/internal/MDButton;->setStacked(ZZ)V

    .line 80
    :cond_0
    return-void
.end method

.method setStacked(ZZ)V
    .locals 4
    .param p1, "stacked"    # Z
    .param p2, "force"    # Z

    .line 49
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stacked:Z

    if-ne v0, p1, :cond_0

    if-eqz p2, :cond_6

    .line 51
    :cond_0
    const/16 v0, 0x11

    if-eqz p1, :cond_1

    iget-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/GravityEnum;->getGravityInt()I

    move-result v1

    or-int/lit8 v1, v1, 0x10

    goto :goto_0

    :cond_1
    const/16 v1, 0x11

    :goto_0
    invoke-virtual {p0, v1}, Lcom/afollestad/materialdialogs/internal/MDButton;->setGravity(I)V

    .line 52
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v1, v0, :cond_3

    .line 54
    if-eqz p1, :cond_2

    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/GravityEnum;->getTextAlignment()I

    move-result v0

    goto :goto_1

    :cond_2
    const/4 v0, 0x4

    :goto_1
    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/internal/MDButton;->setTextAlignment(I)V

    .line 57
    :cond_3
    if-eqz p1, :cond_4

    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedBackground:Landroid/graphics/drawable/Drawable;

    goto :goto_2

    :cond_4
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->defaultBackground:Landroid/graphics/drawable/Drawable;

    :goto_2
    invoke-static {p0, v0}, Lcom/afollestad/materialdialogs/util/DialogUtils;->setBackgroundCompat(Landroid/view/View;Landroid/graphics/drawable/Drawable;)V

    .line 58
    if-eqz p1, :cond_5

    .line 59
    iget v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedEndPadding:I

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDButton;->getPaddingTop()I

    move-result v1

    iget v2, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedEndPadding:I

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDButton;->getPaddingBottom()I

    move-result v3

    invoke-virtual {p0, v0, v1, v2, v3}, Lcom/afollestad/materialdialogs/internal/MDButton;->setPadding(IIII)V

    .line 62
    :cond_5
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stacked:Z

    .line 64
    :cond_6
    return-void
.end method

.method public setStackedGravity(Lcom/afollestad/materialdialogs/GravityEnum;)V
    .locals 0
    .param p1, "gravity"    # Lcom/afollestad/materialdialogs/GravityEnum;

    .line 67
    iput-object p1, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 68
    return-void
.end method

.method public setStackedSelector(Landroid/graphics/drawable/Drawable;)V
    .locals 1
    .param p1, "d"    # Landroid/graphics/drawable/Drawable;

    .line 71
    iput-object p1, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stackedBackground:Landroid/graphics/drawable/Drawable;

    .line 72
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDButton;->stacked:Z

    if-eqz v0, :cond_0

    .line 73
    const/4 v0, 0x1

    invoke-virtual {p0, v0, v0}, Lcom/afollestad/materialdialogs/internal/MDButton;->setStacked(ZZ)V

    .line 74
    :cond_0
    return-void
.end method
