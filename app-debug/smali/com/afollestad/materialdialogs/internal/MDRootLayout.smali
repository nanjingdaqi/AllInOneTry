.class public Lcom/afollestad/materialdialogs/internal/MDRootLayout;
.super Landroid/view/ViewGroup;
.source "MDRootLayout.java"


# static fields
.field private static final INDEX_NEGATIVE:I = 0x1

.field private static final INDEX_NEUTRAL:I = 0x0

.field private static final INDEX_POSITIVE:I = 0x2


# instance fields
.field private bottomOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

.field private buttonBarHeight:I

.field private buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

.field private buttonHorizontalEdgeMargin:I

.field private buttonPaddingFull:I

.field private final buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

.field private content:Landroid/view/View;

.field private dividerPaint:Landroid/graphics/Paint;

.field private dividerWidth:I

.field private drawBottomDivider:Z

.field private drawTopDivider:Z

.field private isStacked:Z

.field private maxHeight:I

.field private noTitleNoPadding:Z

.field private noTitlePaddingFull:I

.field private reducePaddingNoTitleNoButtons:Z

.field private stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

.field private titleBar:Landroid/view/View;

.field private topOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

.field private useFullPadding:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;

    .line 66
    invoke-direct {p0, p1}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;)V

    .line 41
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 42
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 43
    const/4 v1, 0x3

    new-array v1, v1, [Lcom/afollestad/materialdialogs/internal/MDButton;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    .line 44
    sget-object v1, Lcom/afollestad/materialdialogs/StackingBehavior;->ADAPTIVE:Lcom/afollestad/materialdialogs/StackingBehavior;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    .line 45
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    .line 46
    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 54
    sget-object v1, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 67
    const/4 v1, 0x0

    invoke-direct {p0, p1, v1, v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->init(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 68
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .line 71
    invoke-direct {p0, p1, p2}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 41
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 42
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 43
    const/4 v1, 0x3

    new-array v1, v1, [Lcom/afollestad/materialdialogs/internal/MDButton;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    .line 44
    sget-object v1, Lcom/afollestad/materialdialogs/StackingBehavior;->ADAPTIVE:Lcom/afollestad/materialdialogs/StackingBehavior;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    .line 45
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    .line 46
    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 54
    sget-object v1, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 72
    invoke-direct {p0, p1, p2, v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->init(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 73
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I
    .annotation build Landroid/annotation/TargetApi;
        value = 0xb
    .end annotation

    .line 77
    invoke-direct {p0, p1, p2, p3}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 41
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 42
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 43
    const/4 v1, 0x3

    new-array v1, v1, [Lcom/afollestad/materialdialogs/internal/MDButton;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    .line 44
    sget-object v1, Lcom/afollestad/materialdialogs/StackingBehavior;->ADAPTIVE:Lcom/afollestad/materialdialogs/StackingBehavior;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    .line 45
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    .line 46
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 54
    sget-object v0, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 78
    invoke-direct {p0, p1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->init(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 79
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I
    .param p4, "defStyleRes"    # I
    .annotation build Landroid/annotation/TargetApi;
        value = 0x15
    .end annotation

    .line 83
    invoke-direct {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 41
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 42
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 43
    const/4 v1, 0x3

    new-array v1, v1, [Lcom/afollestad/materialdialogs/internal/MDButton;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    .line 44
    sget-object v1, Lcom/afollestad/materialdialogs/StackingBehavior;->ADAPTIVE:Lcom/afollestad/materialdialogs/StackingBehavior;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    .line 45
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    .line 46
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 54
    sget-object v0, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 84
    invoke-direct {p0, p1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->init(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 85
    return-void
.end method

.method static synthetic access$000(Landroid/webkit/WebView;)Z
    .locals 1
    .param p0, "x0"    # Landroid/webkit/WebView;

    .line 31
    invoke-static {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->canWebViewScroll(Landroid/webkit/WebView;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$102(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Z)Z
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;
    .param p1, "x1"    # Z

    .line 31
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    return p1
.end method

.method static synthetic access$202(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Z)Z
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;
    .param p1, "x1"    # Z

    .line 31
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    return p1
.end method

.method static synthetic access$300(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/ViewGroup;ZZ)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;
    .param p1, "x1"    # Landroid/view/ViewGroup;
    .param p2, "x2"    # Z
    .param p3, "x3"    # Z

    .line 31
    invoke-direct {p0, p1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->addScrollListener(Landroid/view/ViewGroup;ZZ)V

    return-void
.end method

.method static synthetic access$400(Lcom/afollestad/materialdialogs/internal/MDRootLayout;)[Lcom/afollestad/materialdialogs/internal/MDButton;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    .line 31
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    return-object v0
.end method

.method static synthetic access$500(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/ViewGroup;ZZZ)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;
    .param p1, "x1"    # Landroid/view/ViewGroup;
    .param p2, "x2"    # Z
    .param p3, "x3"    # Z
    .param p4, "x4"    # Z

    .line 31
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->invalidateDividersForScrollingView(Landroid/view/ViewGroup;ZZZ)V

    return-void
.end method

.method static synthetic access$600(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/webkit/WebView;ZZZ)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;
    .param p1, "x1"    # Landroid/webkit/WebView;
    .param p2, "x2"    # Z
    .param p3, "x3"    # Z
    .param p4, "x4"    # Z

    .line 31
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->invalidateDividersForWebView(Landroid/webkit/WebView;ZZZ)V

    return-void
.end method

.method private addScrollListener(Landroid/view/ViewGroup;ZZ)V
    .locals 3
    .param p1, "vg"    # Landroid/view/ViewGroup;
    .param p2, "setForTop"    # Z
    .param p3, "setForBottom"    # Z

    .line 447
    if-nez p3, :cond_0

    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->topOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

    if-eqz v0, :cond_1

    :cond_0
    if-eqz p3, :cond_4

    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->bottomOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

    if-nez v0, :cond_4

    .line 449
    :cond_1
    instance-of v0, p1, Landroid/support/v7/widget/RecyclerView;

    if-eqz v0, :cond_2

    .line 450
    new-instance v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$2;

    invoke-direct {v0, p0, p1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout$2;-><init>(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/ViewGroup;ZZ)V

    .line 465
    .local v0, "scrollListener":Landroid/support/v7/widget/RecyclerView$OnScrollListener;
    move-object v1, p1

    check-cast v1, Landroid/support/v7/widget/RecyclerView;

    invoke-virtual {v1, v0}, Landroid/support/v7/widget/RecyclerView;->addOnScrollListener(Landroid/support/v7/widget/RecyclerView$OnScrollListener;)V

    .line 466
    move-object v1, p1

    check-cast v1, Landroid/support/v7/widget/RecyclerView;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2, v2}, Landroid/support/v7/widget/RecyclerView$OnScrollListener;->onScrolled(Landroid/support/v7/widget/RecyclerView;II)V

    .line 467
    .end local v0    # "scrollListener":Landroid/support/v7/widget/RecyclerView$OnScrollListener;
    goto :goto_1

    .line 468
    :cond_2
    new-instance v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;

    invoke-direct {v0, p0, p1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;-><init>(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/ViewGroup;ZZ)V

    .line 486
    .local v0, "onScrollChangedListener":Landroid/view/ViewTreeObserver$OnScrollChangedListener;
    if-nez p3, :cond_3

    .line 487
    iput-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->topOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

    .line 488
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->topOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

    invoke-virtual {v1, v2}, Landroid/view/ViewTreeObserver;->addOnScrollChangedListener(Landroid/view/ViewTreeObserver$OnScrollChangedListener;)V

    goto :goto_0

    .line 490
    :cond_3
    iput-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->bottomOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

    .line 491
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->bottomOnScrollChangedListener:Landroid/view/ViewTreeObserver$OnScrollChangedListener;

    invoke-virtual {v1, v2}, Landroid/view/ViewTreeObserver;->addOnScrollChangedListener(Landroid/view/ViewTreeObserver$OnScrollChangedListener;)V

    .line 493
    :goto_0
    invoke-interface {v0}, Landroid/view/ViewTreeObserver$OnScrollChangedListener;->onScrollChanged()V

    .line 496
    .end local v0    # "onScrollChangedListener":Landroid/view/ViewTreeObserver$OnScrollChangedListener;
    :cond_4
    :goto_1
    return-void
.end method

.method private static canAdapterViewScroll(Landroid/widget/AdapterView;)Z
    .locals 7
    .param p0, "lv"    # Landroid/widget/AdapterView;

    .line 544
    invoke-virtual {p0}, Landroid/widget/AdapterView;->getLastVisiblePosition()I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, -0x1

    if-ne v0, v2, :cond_0

    .line 545
    return v1

    .line 548
    :cond_0
    invoke-virtual {p0}, Landroid/widget/AdapterView;->getFirstVisiblePosition()I

    move-result v0

    const/4 v2, 0x1

    if-nez v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 549
    .local v0, "firstItemVisible":Z
    :goto_0
    invoke-virtual {p0}, Landroid/widget/AdapterView;->getLastVisiblePosition()I

    move-result v3

    invoke-virtual {p0}, Landroid/widget/AdapterView;->getCount()I

    move-result v4

    sub-int/2addr v4, v2

    if-ne v3, v4, :cond_2

    const/4 v3, 0x1

    goto :goto_1

    :cond_2
    const/4 v3, 0x0

    .line 551
    .local v3, "lastItemVisible":Z
    :goto_1
    if-eqz v0, :cond_5

    if-eqz v3, :cond_5

    invoke-virtual {p0}, Landroid/widget/AdapterView;->getChildCount()I

    move-result v4

    if-lez v4, :cond_5

    .line 553
    invoke-virtual {p0, v1}, Landroid/widget/AdapterView;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    invoke-virtual {v4}, Landroid/view/View;->getTop()I

    move-result v4

    invoke-virtual {p0}, Landroid/widget/AdapterView;->getPaddingTop()I

    move-result v5

    if-ge v4, v5, :cond_3

    .line 554
    return v2

    .line 556
    :cond_3
    invoke-virtual {p0}, Landroid/widget/AdapterView;->getChildCount()I

    move-result v4

    sub-int/2addr v4, v2

    invoke-virtual {p0, v4}, Landroid/widget/AdapterView;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    invoke-virtual {v4}, Landroid/view/View;->getBottom()I

    move-result v4

    .line 557
    invoke-virtual {p0}, Landroid/widget/AdapterView;->getHeight()I

    move-result v5

    invoke-virtual {p0}, Landroid/widget/AdapterView;->getPaddingBottom()I

    move-result v6

    sub-int/2addr v5, v6

    if-le v4, v5, :cond_4

    .line 556
    const/4 v1, 0x1

    goto :goto_2

    .line 557
    :cond_4
    nop

    .line 556
    :goto_2
    return v1

    .line 560
    :cond_5
    return v2
.end method

.method public static canRecyclerViewScroll(Landroid/support/v7/widget/RecyclerView;)Z
    .locals 1
    .param p0, "view"    # Landroid/support/v7/widget/RecyclerView;

    .line 527
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/support/v7/widget/RecyclerView;->getLayoutManager()Landroid/support/v7/widget/RecyclerView$LayoutManager;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Landroid/support/v7/widget/RecyclerView;->getLayoutManager()Landroid/support/v7/widget/RecyclerView$LayoutManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/support/v7/widget/RecyclerView$LayoutManager;->canScrollVertically()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private static canScrollViewScroll(Landroid/widget/ScrollView;)Z
    .locals 4
    .param p0, "sv"    # Landroid/widget/ScrollView;

    .line 531
    invoke-virtual {p0}, Landroid/widget/ScrollView;->getChildCount()I

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 532
    return v1

    .line 533
    :cond_0
    invoke-virtual {p0, v1}, Landroid/widget/ScrollView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    .line 534
    .local v0, "childHeight":I
    invoke-virtual {p0}, Landroid/widget/ScrollView;->getMeasuredHeight()I

    move-result v2

    invoke-virtual {p0}, Landroid/widget/ScrollView;->getPaddingTop()I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual {p0}, Landroid/widget/ScrollView;->getPaddingBottom()I

    move-result v3

    sub-int/2addr v2, v3

    if-ge v2, v0, :cond_1

    const/4 v1, 0x1

    nop

    :cond_1
    return v1
.end method

.method private static canWebViewScroll(Landroid/webkit/WebView;)Z
    .locals 3
    .param p0, "view"    # Landroid/webkit/WebView;

    .line 539
    invoke-virtual {p0}, Landroid/webkit/WebView;->getMeasuredHeight()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p0}, Landroid/webkit/WebView;->getContentHeight()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p0}, Landroid/webkit/WebView;->getScale()F

    move-result v2

    mul-float v1, v1, v2

    cmpg-float v0, v0, v1

    if-gez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private static getBottomView(Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 5
    .param p0, "viewGroup"    # Landroid/view/ViewGroup;
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation

    .line 571
    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 573
    :cond_0
    const/4 v0, 0x0

    .line 574
    .local v0, "bottomView":Landroid/view/View;
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    .local v1, "i":I
    :goto_0
    if-ltz v1, :cond_2

    .line 575
    invoke-virtual {p0, v1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 576
    .local v2, "child":Landroid/view/View;
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v3

    if-nez v3, :cond_1

    invoke-virtual {v2}, Landroid/view/View;->getBottom()I

    move-result v3

    invoke-virtual {p0}, Landroid/view/ViewGroup;->getMeasuredHeight()I

    move-result v4

    if-ne v3, v4, :cond_1

    .line 577
    move-object v0, v2

    .line 578
    goto :goto_1

    .line 574
    .end local v2    # "child":Landroid/view/View;
    :cond_1
    add-int/lit8 v1, v1, -0x1

    goto :goto_0

    .line 581
    .end local v1    # "i":I
    :cond_2
    :goto_1
    return-object v0

    .line 572
    .end local v0    # "bottomView":Landroid/view/View;
    :cond_3
    :goto_2
    const/4 v0, 0x0

    return-object v0
.end method

.method private static getTopView(Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 4
    .param p0, "viewGroup"    # Landroid/view/ViewGroup;
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation

    .line 585
    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 587
    :cond_0
    const/4 v0, 0x0

    .line 588
    .local v0, "topView":Landroid/view/View;
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    .local v1, "i":I
    :goto_0
    if-ltz v1, :cond_2

    .line 589
    invoke-virtual {p0, v1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 590
    .local v2, "child":Landroid/view/View;
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v3

    if-nez v3, :cond_1

    invoke-virtual {v2}, Landroid/view/View;->getTop()I

    move-result v3

    if-nez v3, :cond_1

    .line 591
    move-object v0, v2

    .line 592
    goto :goto_1

    .line 588
    .end local v2    # "child":Landroid/view/View;
    :cond_1
    add-int/lit8 v1, v1, -0x1

    goto :goto_0

    .line 595
    .end local v1    # "i":I
    :cond_2
    :goto_1
    return-object v0

    .line 586
    .end local v0    # "topView":Landroid/view/View;
    :cond_3
    :goto_2
    const/4 v0, 0x0

    return-object v0
.end method

.method private init(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 5
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I

    .line 88
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 90
    .local v0, "r":Landroid/content/res/Resources;
    sget-object v1, Lcom/afollestad/materialdialogs/R$styleable;->MDRootLayout:[I

    const/4 v2, 0x0

    invoke-virtual {p1, p2, v1, p3, v2}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v1

    .line 91
    .local v1, "a":Landroid/content/res/TypedArray;
    sget v3, Lcom/afollestad/materialdialogs/R$styleable;->MDRootLayout_md_reduce_padding_no_title_no_buttons:I

    const/4 v4, 0x1

    invoke-virtual {v1, v3, v4}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v3

    iput-boolean v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->reducePaddingNoTitleNoButtons:Z

    .line 92
    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    .line 94
    sget v3, Lcom/afollestad/materialdialogs/R$dimen;->md_notitle_vertical_padding:I

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    iput v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->noTitlePaddingFull:I

    .line 95
    sget v3, Lcom/afollestad/materialdialogs/R$dimen;->md_button_frame_vertical_padding:I

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    iput v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    .line 97
    sget v3, Lcom/afollestad/materialdialogs/R$dimen;->md_button_padding_frame_side:I

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    iput v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonHorizontalEdgeMargin:I

    .line 98
    sget v3, Lcom/afollestad/materialdialogs/R$dimen;->md_button_height:I

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    iput v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonBarHeight:I

    .line 100
    new-instance v3, Landroid/graphics/Paint;

    invoke-direct {v3}, Landroid/graphics/Paint;-><init>()V

    iput-object v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerPaint:Landroid/graphics/Paint;

    .line 101
    sget v3, Lcom/afollestad/materialdialogs/R$dimen;->md_divider_height:I

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    iput v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerWidth:I

    .line 102
    iget-object v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerPaint:Landroid/graphics/Paint;

    sget v4, Lcom/afollestad/materialdialogs/R$attr;->md_divider_color:I

    invoke-static {p1, v4}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;I)I

    move-result v4

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setColor(I)V

    .line 103
    invoke-virtual {p0, v2}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->setWillNotDraw(Z)V

    .line 104
    return-void
.end method

.method private invalidateDividersForScrollingView(Landroid/view/ViewGroup;ZZZ)V
    .locals 4
    .param p1, "view"    # Landroid/view/ViewGroup;
    .param p2, "setForTop"    # Z
    .param p3, "setForBottom"    # Z
    .param p4, "hasButtons"    # Z

    .line 499
    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p2, :cond_1

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    if-lez v2, :cond_1

    .line 500
    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    if-eqz v2, :cond_0

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    .line 501
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_0

    .line 503
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getScrollY()I

    move-result v2

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingTop()I

    move-result v3

    add-int/2addr v2, v3

    invoke-virtual {p1, v1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getTop()I

    move-result v3

    if-le v2, v3, :cond_0

    const/4 v2, 0x1

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    iput-boolean v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 506
    :cond_1
    if-eqz p3, :cond_3

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    if-lez v2, :cond_3

    .line 507
    if-eqz p4, :cond_2

    .line 508
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getScrollY()I

    move-result v2

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getHeight()I

    move-result v3

    add-int/2addr v2, v3

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingBottom()I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v3

    sub-int/2addr v3, v0

    invoke-virtual {p1, v3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getBottom()I

    move-result v3

    if-ge v2, v3, :cond_2

    goto :goto_1

    :cond_2
    const/4 v0, 0x0

    :goto_1
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 510
    :cond_3
    return-void
.end method

.method private invalidateDividersForWebView(Landroid/webkit/WebView;ZZZ)V
    .locals 5
    .param p1, "view"    # Landroid/webkit/WebView;
    .param p2, "setForTop"    # Z
    .param p3, "setForBottom"    # Z
    .param p4, "hasButtons"    # Z

    .line 513
    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p2, :cond_1

    .line 514
    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    if-eqz v2, :cond_0

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    .line 515
    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_0

    .line 517
    invoke-virtual {p1}, Landroid/webkit/WebView;->getScrollY()I

    move-result v2

    invoke-virtual {p1}, Landroid/webkit/WebView;->getPaddingTop()I

    move-result v3

    add-int/2addr v2, v3

    if-lez v2, :cond_0

    const/4 v2, 0x1

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    iput-boolean v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 519
    :cond_1
    if-eqz p3, :cond_3

    .line 521
    if-eqz p4, :cond_2

    .line 522
    invoke-virtual {p1}, Landroid/webkit/WebView;->getScrollY()I

    move-result v2

    invoke-virtual {p1}, Landroid/webkit/WebView;->getMeasuredHeight()I

    move-result v3

    add-int/2addr v2, v3

    invoke-virtual {p1}, Landroid/webkit/WebView;->getPaddingBottom()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    invoke-virtual {p1}, Landroid/webkit/WebView;->getContentHeight()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p1}, Landroid/webkit/WebView;->getScale()F

    move-result v4

    mul-float v3, v3, v4

    cmpg-float v2, v2, v3

    if-gez v2, :cond_2

    goto :goto_1

    :cond_2
    const/4 v0, 0x0

    :goto_1
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 524
    :cond_3
    return-void
.end method

.method private invertGravityIfNecessary()V
    .locals 3

    .line 366
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x11

    if-ge v0, v1, :cond_0

    return-void

    .line 367
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    .line 368
    .local v0, "config":Landroid/content/res/Configuration;
    invoke-virtual {v0}, Landroid/content/res/Configuration;->getLayoutDirection()I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_1

    .line 369
    sget-object v1, Lcom/afollestad/materialdialogs/internal/MDRootLayout$4;->$SwitchMap$com$afollestad$materialdialogs$GravityEnum:[I

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    invoke-virtual {v2}, Lcom/afollestad/materialdialogs/GravityEnum;->ordinal()I

    move-result v2

    aget v1, v1, v2

    packed-switch v1, :pswitch_data_0

    goto :goto_0

    .line 374
    :pswitch_0
    sget-object v1, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    goto :goto_0

    .line 371
    :pswitch_1
    sget-object v1, Lcom/afollestad/materialdialogs/GravityEnum;->END:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 372
    nop

    .line 378
    :cond_1
    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private static isVisible(Landroid/view/View;)Z
    .locals 4
    .param p0, "v"    # Landroid/view/View;

    .line 227
    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_0

    const/4 v2, 0x1

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    .line 228
    .local v2, "visible":Z
    :goto_0
    if-eqz v2, :cond_2

    instance-of v3, p0, Lcom/afollestad/materialdialogs/internal/MDButton;

    if-eqz v3, :cond_2

    .line 229
    move-object v3, p0

    check-cast v3, Lcom/afollestad/materialdialogs/internal/MDButton;

    invoke-virtual {v3}, Lcom/afollestad/materialdialogs/internal/MDButton;->getText()Ljava/lang/CharSequence;

    move-result-object v3

    invoke-interface {v3}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    if-lez v3, :cond_1

    goto :goto_1

    :cond_1
    const/4 v0, 0x0

    :goto_1
    move v2, v0

    .line 230
    :cond_2
    return v2
.end method

.method private setUpDividersVisibility(Landroid/view/View;ZZ)V
    .locals 4
    .param p1, "view"    # Landroid/view/View;
    .param p2, "setForTop"    # Z
    .param p3, "setForBottom"    # Z

    .line 388
    if-nez p1, :cond_0

    .line 389
    return-void

    .line 390
    :cond_0
    instance-of v0, p1, Landroid/widget/ScrollView;

    const/4 v1, 0x0

    if-eqz v0, :cond_4

    .line 391
    move-object v0, p1

    check-cast v0, Landroid/widget/ScrollView;

    .line 392
    .local v0, "sv":Landroid/widget/ScrollView;
    invoke-static {v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->canScrollViewScroll(Landroid/widget/ScrollView;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 393
    invoke-direct {p0, v0, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->addScrollListener(Landroid/view/ViewGroup;ZZ)V

    goto :goto_0

    .line 395
    :cond_1
    if-eqz p2, :cond_2

    .line 396
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 397
    :cond_2
    if-eqz p3, :cond_3

    .line 398
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 400
    .end local v0    # "sv":Landroid/widget/ScrollView;
    :cond_3
    :goto_0
    goto :goto_2

    :cond_4
    instance-of v0, p1, Landroid/widget/AdapterView;

    if-eqz v0, :cond_8

    .line 401
    move-object v0, p1

    check-cast v0, Landroid/widget/AdapterView;

    .line 402
    .local v0, "sv":Landroid/widget/AdapterView;
    invoke-static {v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->canAdapterViewScroll(Landroid/widget/AdapterView;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 403
    invoke-direct {p0, v0, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->addScrollListener(Landroid/view/ViewGroup;ZZ)V

    goto :goto_1

    .line 405
    :cond_5
    if-eqz p2, :cond_6

    .line 406
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 407
    :cond_6
    if-eqz p3, :cond_7

    .line 408
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 410
    .end local v0    # "sv":Landroid/widget/AdapterView;
    :cond_7
    :goto_1
    goto :goto_2

    :cond_8
    instance-of v0, p1, Landroid/webkit/WebView;

    if-eqz v0, :cond_9

    .line 411
    invoke-virtual {p1}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    new-instance v1, Lcom/afollestad/materialdialogs/internal/MDRootLayout$1;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout$1;-><init>(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/View;ZZ)V

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->addOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    goto :goto_2

    .line 428
    :cond_9
    instance-of v0, p1, Landroid/support/v7/widget/RecyclerView;

    if-eqz v0, :cond_d

    .line 429
    move-object v0, p1

    check-cast v0, Landroid/support/v7/widget/RecyclerView;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->canRecyclerViewScroll(Landroid/support/v7/widget/RecyclerView;)Z

    move-result v0

    .line 430
    .local v0, "canScroll":Z
    if-eqz p2, :cond_a

    .line 431
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    .line 432
    :cond_a
    if-eqz p3, :cond_b

    .line 433
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    .line 434
    :cond_b
    if-eqz v0, :cond_c

    .line 435
    move-object v1, p1

    check-cast v1, Landroid/view/ViewGroup;

    invoke-direct {p0, v1, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->addScrollListener(Landroid/view/ViewGroup;ZZ)V

    .line 436
    .end local v0    # "canScroll":Z
    :cond_c
    goto :goto_2

    :cond_d
    instance-of v0, p1, Landroid/view/ViewGroup;

    if-eqz v0, :cond_e

    .line 437
    move-object v0, p1

    check-cast v0, Landroid/view/ViewGroup;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getTopView(Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    .line 438
    .local v0, "topView":Landroid/view/View;
    invoke-direct {p0, v0, p2, p3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->setUpDividersVisibility(Landroid/view/View;ZZ)V

    .line 439
    move-object v2, p1

    check-cast v2, Landroid/view/ViewGroup;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getBottomView(Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v2

    .line 440
    .local v2, "bottomView":Landroid/view/View;
    if-eq v2, v0, :cond_e

    .line 441
    const/4 v3, 0x1

    invoke-direct {p0, v2, v1, v3}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->setUpDividersVisibility(Landroid/view/View;ZZ)V

    .line 444
    .end local v0    # "topView":Landroid/view/View;
    .end local v2    # "bottomView":Landroid/view/View;
    :cond_e
    :goto_2
    return-void
.end method


# virtual methods
.method public noTitleNoPadding()V
    .locals 1

    .line 111
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->noTitleNoPadding:Z

    .line 112
    return-void
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 7
    .param p1, "canvas"    # Landroid/graphics/Canvas;

    .line 234
    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onDraw(Landroid/graphics/Canvas;)V

    .line 236
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    if-eqz v0, :cond_1

    .line 237
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawTopDivider:Z

    if-eqz v0, :cond_0

    .line 238
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v0

    .line 239
    .local v0, "y":I
    const/4 v2, 0x0

    iget v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerWidth:I

    sub-int v1, v0, v1

    int-to-float v3, v1

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getMeasuredWidth()I

    move-result v1

    int-to-float v4, v1

    int-to-float v5, v0

    iget-object v6, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerPaint:Landroid/graphics/Paint;

    move-object v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 242
    .end local v0    # "y":I
    :cond_0
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->drawBottomDivider:Z

    if-eqz v0, :cond_1

    .line 243
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getBottom()I

    move-result v0

    .line 244
    .restart local v0    # "y":I
    const/4 v2, 0x0

    int-to-float v3, v0

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getMeasuredWidth()I

    move-result v1

    int-to-float v4, v1

    iget v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerWidth:I

    add-int/2addr v1, v0

    int-to-float v5, v1

    iget-object v6, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerPaint:Landroid/graphics/Paint;

    move-object v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 247
    .end local v0    # "y":I
    :cond_1
    return-void
.end method

.method public onFinishInflate()V
    .locals 6

    .line 115
    invoke-super {p0}, Landroid/view/ViewGroup;->onFinishInflate()V

    .line 116
    const/4 v0, 0x0

    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getChildCount()I

    move-result v2

    if-ge v1, v2, :cond_4

    .line 117
    invoke-virtual {p0, v1}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 118
    .local v2, "v":Landroid/view/View;
    invoke-virtual {v2}, Landroid/view/View;->getId()I

    move-result v3

    sget v4, Lcom/afollestad/materialdialogs/R$id;->md_titleFrame:I

    if-ne v3, v4, :cond_0

    .line 119
    iput-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    goto :goto_1

    .line 120
    :cond_0
    invoke-virtual {v2}, Landroid/view/View;->getId()I

    move-result v3

    sget v4, Lcom/afollestad/materialdialogs/R$id;->md_buttonDefaultNeutral:I

    if-ne v3, v4, :cond_1

    .line 121
    iget-object v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    move-object v4, v2

    check-cast v4, Lcom/afollestad/materialdialogs/internal/MDButton;

    aput-object v4, v3, v0

    goto :goto_1

    .line 122
    :cond_1
    invoke-virtual {v2}, Landroid/view/View;->getId()I

    move-result v3

    sget v4, Lcom/afollestad/materialdialogs/R$id;->md_buttonDefaultNegative:I

    if-ne v3, v4, :cond_2

    .line 123
    iget-object v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    move-object v4, v2

    check-cast v4, Lcom/afollestad/materialdialogs/internal/MDButton;

    const/4 v5, 0x1

    aput-object v4, v3, v5

    goto :goto_1

    .line 124
    :cond_2
    invoke-virtual {v2}, Landroid/view/View;->getId()I

    move-result v3

    sget v4, Lcom/afollestad/materialdialogs/R$id;->md_buttonDefaultPositive:I

    if-ne v3, v4, :cond_3

    .line 125
    iget-object v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    const/4 v4, 0x2

    move-object v5, v2

    check-cast v5, Lcom/afollestad/materialdialogs/internal/MDButton;

    aput-object v5, v3, v4

    goto :goto_1

    .line 127
    :cond_3
    iput-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    .line 116
    .end local v2    # "v":Landroid/view/View;
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 130
    .end local v1    # "i":I
    :cond_4
    return-void
.end method

.method protected onLayout(ZIIII)V
    .locals 16
    .param p1, "changed"    # Z
    .param p2, "l"    # I
    .param p3, "t"    # I
    .param p4, "r"    # I
    .param p5, "b"    # I

    .line 250
    move-object/from16 v0, p0

    move/from16 v1, p2

    move/from16 v2, p3

    move/from16 v3, p4

    iget-object v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    invoke-static {v4}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 251
    iget-object v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    .line 252
    .local v4, "height":I
    iget-object v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    add-int v6, v2, v4

    invoke-virtual {v5, v1, v2, v3, v6}, Landroid/view/View;->layout(IIII)V

    .line 253
    add-int/2addr v2, v4

    .line 254
    .end local v4    # "height":I
    .end local p3    # "t":I
    .local v2, "t":I
    goto :goto_0

    .end local v2    # "t":I
    .restart local p3    # "t":I
    :cond_0
    iget-boolean v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->noTitleNoPadding:Z

    if-nez v4, :cond_1

    iget-boolean v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    if-eqz v4, :cond_1

    .line 255
    iget v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->noTitlePaddingFull:I

    add-int/2addr v2, v4

    .line 258
    .end local p3    # "t":I
    .restart local v2    # "t":I
    :cond_1
    :goto_0
    iget-object v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-static {v4}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 259
    iget-object v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    iget-object v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    add-int/2addr v5, v2

    invoke-virtual {v4, v1, v2, v3, v5}, Landroid/view/View;->layout(IIII)V

    .line 261
    :cond_2
    iget-boolean v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-eqz v4, :cond_4

    .line 262
    iget v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    sub-int v4, p5, v4

    .line 263
    .end local p5    # "b":I
    .local v4, "b":I
    iget-object v7, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    array-length v8, v7

    :goto_1
    if-ge v6, v8, :cond_11

    aget-object v9, v7, v6

    .line 264
    .local v9, "mButton":Lcom/afollestad/materialdialogs/internal/MDButton;
    invoke-static {v9}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v10

    if-eqz v10, :cond_3

    .line 265
    invoke-virtual {v9}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredHeight()I

    move-result v10

    sub-int v10, v4, v10

    invoke-virtual {v9, v1, v10, v3, v4}, Lcom/afollestad/materialdialogs/internal/MDButton;->layout(IIII)V

    .line 266
    invoke-virtual {v9}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredHeight()I

    move-result v10

    sub-int/2addr v4, v10

    .line 263
    .end local v9    # "mButton":Lcom/afollestad/materialdialogs/internal/MDButton;
    :cond_3
    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    .line 271
    .end local v4    # "b":I
    .restart local p5    # "b":I
    :cond_4
    move/from16 v4, p5

    .line 272
    .local v4, "barBottom":I
    iget-boolean v8, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    if-eqz v8, :cond_5

    .line 273
    iget v8, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    sub-int/2addr v4, v8

    .line 274
    :cond_5
    iget v8, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonBarHeight:I

    sub-int v8, v4, v8

    .line 286
    .local v8, "barTop":I
    iget v9, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonHorizontalEdgeMargin:I

    .line 289
    .local v9, "offset":I
    const/4 v10, -0x1

    .line 290
    .local v10, "neutralLeft":I
    const/4 v11, -0x1

    .line 292
    .local v11, "neutralRight":I
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    const/4 v13, 0x2

    aget-object v12, v12, v13

    invoke-static {v12}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v12

    if-eqz v12, :cond_7

    .line 294
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v14, Lcom/afollestad/materialdialogs/GravityEnum;->END:Lcom/afollestad/materialdialogs/GravityEnum;

    if-ne v12, v14, :cond_6

    .line 295
    add-int v12, v1, v9

    .line 296
    .local v12, "bl":I
    iget-object v14, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v14, v14, v13

    invoke-virtual {v14}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v14

    add-int/2addr v14, v12

    goto :goto_2

    .line 298
    .end local v12    # "bl":I
    :cond_6
    sub-int v14, v3, v9

    .line 299
    .local v14, "br":I
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v13

    invoke-virtual {v12}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v12

    sub-int v12, v14, v12

    .line 300
    .restart local v12    # "bl":I
    move v11, v12

    .line 302
    :goto_2
    iget-object v15, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v15, v15, v13

    invoke-virtual {v15, v12, v8, v14, v4}, Lcom/afollestad/materialdialogs/internal/MDButton;->layout(IIII)V

    .line 303
    iget-object v15, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v15, v15, v13

    invoke-virtual {v15}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v15

    add-int/2addr v9, v15

    .line 306
    .end local v12    # "bl":I
    .end local v14    # "br":I
    :cond_7
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v5

    invoke-static {v12}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v12

    if-eqz v12, :cond_a

    .line 308
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v14, Lcom/afollestad/materialdialogs/GravityEnum;->END:Lcom/afollestad/materialdialogs/GravityEnum;

    if-ne v12, v14, :cond_8

    .line 309
    add-int v12, v1, v9

    .line 310
    .restart local v12    # "bl":I
    iget-object v14, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v14, v14, v5

    invoke-virtual {v14}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v14

    add-int/2addr v14, v12

    goto :goto_3

    .line 311
    .end local v12    # "bl":I
    :cond_8
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v14, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    if-ne v12, v14, :cond_9

    .line 312
    sub-int v14, v3, v9

    .line 313
    .restart local v14    # "br":I
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v5

    invoke-virtual {v12}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v12

    sub-int v12, v14, v12

    goto :goto_3

    .line 315
    .end local v14    # "br":I
    :cond_9
    iget v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonHorizontalEdgeMargin:I

    add-int/2addr v12, v1

    .line 316
    .restart local v12    # "bl":I
    iget-object v14, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v14, v14, v5

    invoke-virtual {v14}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v14

    add-int/2addr v14, v12

    .line 317
    .restart local v14    # "br":I
    move v10, v14

    .line 319
    :goto_3
    iget-object v15, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v15, v15, v5

    invoke-virtual {v15, v12, v8, v14, v4}, Lcom/afollestad/materialdialogs/internal/MDButton;->layout(IIII)V

    .line 322
    .end local v12    # "bl":I
    .end local v14    # "br":I
    :cond_a
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v6

    invoke-static {v12}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v12

    if-eqz v12, :cond_10

    .line 324
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v14, Lcom/afollestad/materialdialogs/GravityEnum;->END:Lcom/afollestad/materialdialogs/GravityEnum;

    if-ne v12, v14, :cond_b

    .line 325
    iget v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonHorizontalEdgeMargin:I

    sub-int v12, v3, v12

    .line 326
    .local v12, "br":I
    iget-object v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v13, v13, v6

    invoke-virtual {v13}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v13

    sub-int v13, v12, v13

    goto :goto_5

    .line 327
    .end local v12    # "br":I
    :cond_b
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v14, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    if-ne v12, v14, :cond_c

    .line 328
    iget v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonHorizontalEdgeMargin:I

    add-int v13, v1, v12

    .line 329
    .local v13, "bl":I
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v6

    invoke-virtual {v12}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v12

    add-int/2addr v12, v13

    goto :goto_5

    .line 331
    .end local v13    # "bl":I
    :cond_c
    const/4 v12, -0x1

    if-ne v10, v12, :cond_d

    if-eq v11, v12, :cond_d

    .line 332
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v6

    invoke-virtual {v12}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v12

    sub-int v10, v11, v12

    goto :goto_4

    .line 333
    :cond_d
    if-ne v11, v12, :cond_e

    if-eq v10, v12, :cond_e

    .line 334
    iget-object v12, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v12, v12, v6

    invoke-virtual {v12}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v12

    add-int/2addr v12, v10

    .line 339
    move v11, v12

    goto :goto_4

    .line 335
    :cond_e
    if-ne v11, v12, :cond_f

    .line 336
    sub-int v12, v3, v1

    div-int/2addr v12, v13

    iget-object v14, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v14, v14, v6

    invoke-virtual {v14}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v14

    div-int/2addr v14, v13

    sub-int/2addr v12, v14

    .line 337
    .end local v10    # "neutralLeft":I
    .local v12, "neutralLeft":I
    iget-object v10, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v10, v10, v6

    invoke-virtual {v10}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v10

    add-int/2addr v10, v12

    .line 339
    move v11, v10

    move v10, v12

    .end local v12    # "neutralLeft":I
    .restart local v10    # "neutralLeft":I
    :cond_f
    :goto_4
    move v13, v10

    .line 340
    .restart local v13    # "bl":I
    move v12, v11

    .line 343
    .local v12, "br":I
    :goto_5
    iget-object v14, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    aget-object v6, v14, v6

    invoke-virtual {v6, v13, v8, v12, v4}, Lcom/afollestad/materialdialogs/internal/MDButton;->layout(IIII)V

    .line 347
    .end local v4    # "barBottom":I
    .end local v8    # "barTop":I
    .end local v9    # "offset":I
    .end local v10    # "neutralLeft":I
    .end local v11    # "neutralRight":I
    .end local v12    # "br":I
    .end local v13    # "bl":I
    :cond_10
    move/from16 v4, p5

    .end local p5    # "b":I
    .local v4, "b":I
    :cond_11
    iget-object v6, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-direct {v0, v6, v5, v5}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->setUpDividersVisibility(Landroid/view/View;ZZ)V

    .line 348
    return-void
.end method

.method public onMeasure(II)V
    .locals 16
    .param p1, "widthMeasureSpec"    # I
    .param p2, "heightMeasureSpec"    # I

    .line 133
    move-object/from16 v0, p0

    move/from16 v1, p1

    move/from16 v2, p2

    invoke-static/range {p1 .. p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v3

    .line 134
    .local v3, "width":I
    invoke-static/range {p2 .. p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v4

    .line 136
    .local v4, "height":I
    iget v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->maxHeight:I

    if-le v4, v5, :cond_0

    .line 137
    iget v4, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->maxHeight:I

    .line 140
    :cond_0
    const/4 v5, 0x1

    iput-boolean v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 141
    const/4 v6, 0x0

    .line 144
    .local v6, "hasButtons":Z
    iget-object v7, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    sget-object v8, Lcom/afollestad/materialdialogs/StackingBehavior;->ALWAYS:Lcom/afollestad/materialdialogs/StackingBehavior;

    const/4 v9, 0x0

    if-ne v7, v8, :cond_1

    .line 145
    const/4 v7, 0x1

    goto :goto_2

    .line 146
    :cond_1
    iget-object v7, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    sget-object v8, Lcom/afollestad/materialdialogs/StackingBehavior;->NEVER:Lcom/afollestad/materialdialogs/StackingBehavior;

    if-ne v7, v8, :cond_2

    .line 147
    const/4 v7, 0x0

    goto :goto_2

    .line 149
    :cond_2
    const/4 v7, 0x0

    .line 150
    .local v7, "buttonsWidth":I
    iget-object v8, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    array-length v10, v8

    move v11, v6

    const/4 v6, 0x0

    .end local v6    # "hasButtons":Z
    .local v11, "hasButtons":Z
    :goto_0
    if-ge v6, v10, :cond_4

    aget-object v12, v8, v6

    .line 151
    .local v12, "button":Lcom/afollestad/materialdialogs/internal/MDButton;
    if-eqz v12, :cond_3

    invoke-static {v12}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v13

    if-eqz v13, :cond_3

    .line 152
    invoke-virtual {v12, v9, v9}, Lcom/afollestad/materialdialogs/internal/MDButton;->setStacked(ZZ)V

    .line 153
    invoke-virtual {v0, v12, v1, v2}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->measureChild(Landroid/view/View;II)V

    .line 154
    invoke-virtual {v12}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredWidth()I

    move-result v13

    add-int/2addr v7, v13

    .line 155
    const/4 v11, 0x1

    .line 150
    .end local v12    # "button":Lcom/afollestad/materialdialogs/internal/MDButton;
    :cond_3
    add-int/lit8 v6, v6, 0x1

    goto :goto_0

    .line 159
    :cond_4
    invoke-virtual/range {p0 .. p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->getContext()Landroid/content/Context;

    move-result-object v6

    invoke-virtual {v6}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    sget v8, Lcom/afollestad/materialdialogs/R$dimen;->md_neutral_button_margin:I

    .line 160
    invoke-virtual {v6, v8}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v6

    .line 161
    .local v6, "buttonBarPadding":I
    mul-int/lit8 v8, v6, 0x2

    sub-int v8, v3, v8

    .line 162
    .local v8, "buttonFrameWidth":I
    if-le v7, v8, :cond_5

    const/4 v10, 0x1

    goto :goto_1

    :cond_5
    const/4 v10, 0x0

    :goto_1
    move v7, v10

    move v6, v11

    .line 165
    .end local v8    # "buttonFrameWidth":I
    .end local v11    # "hasButtons":Z
    .local v6, "hasButtons":Z
    .local v7, "stacked":Z
    :goto_2
    const/4 v8, 0x0

    .line 166
    .local v8, "stackedHeight":I
    iput-boolean v7, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    .line 167
    if-eqz v7, :cond_8

    .line 168
    iget-object v10, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    array-length v11, v10

    move v12, v8

    move v8, v6

    const/4 v6, 0x0

    .end local v6    # "hasButtons":Z
    .local v8, "hasButtons":Z
    .local v12, "stackedHeight":I
    :goto_3
    if-ge v6, v11, :cond_7

    aget-object v13, v10, v6

    .line 169
    .local v13, "button":Lcom/afollestad/materialdialogs/internal/MDButton;
    if-eqz v13, :cond_6

    invoke-static {v13}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v14

    if-eqz v14, :cond_6

    .line 170
    invoke-virtual {v13, v5, v9}, Lcom/afollestad/materialdialogs/internal/MDButton;->setStacked(ZZ)V

    .line 171
    invoke-virtual {v0, v13, v1, v2}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->measureChild(Landroid/view/View;II)V

    .line 172
    invoke-virtual {v13}, Lcom/afollestad/materialdialogs/internal/MDButton;->getMeasuredHeight()I

    move-result v14

    add-int/2addr v12, v14

    .line 173
    const/4 v8, 0x1

    .line 168
    .end local v13    # "button":Lcom/afollestad/materialdialogs/internal/MDButton;
    :cond_6
    add-int/lit8 v6, v6, 0x1

    goto :goto_3

    .line 178
    :cond_7
    move v6, v8

    move v8, v12

    .end local v12    # "stackedHeight":I
    .restart local v6    # "hasButtons":Z
    .local v8, "stackedHeight":I
    :cond_8
    move v10, v4

    .line 179
    .local v10, "availableHeight":I
    const/4 v11, 0x0

    .line 180
    .local v11, "fullPadding":I
    const/4 v12, 0x0

    .line 181
    .local v12, "minPadding":I
    if-eqz v6, :cond_a

    .line 182
    iget-boolean v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isStacked:Z

    if-eqz v13, :cond_9

    .line 183
    sub-int/2addr v10, v8

    .line 184
    iget v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    mul-int/lit8 v13, v13, 0x2

    add-int/2addr v11, v13

    .line 185
    iget v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    mul-int/lit8 v13, v13, 0x2

    add-int/2addr v12, v13

    goto :goto_4

    .line 187
    :cond_9
    iget v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonBarHeight:I

    sub-int/2addr v10, v13

    .line 188
    iget v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    mul-int/lit8 v13, v13, 0x2

    add-int/2addr v11, v13

    goto :goto_4

    .line 193
    :cond_a
    iget v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonPaddingFull:I

    mul-int/lit8 v13, v13, 0x2

    add-int/2addr v11, v13

    .line 196
    :goto_4
    iget-object v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    invoke-static {v13}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v13

    const/high16 v14, 0x40000000    # 2.0f

    if-eqz v13, :cond_b

    .line 197
    iget-object v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    invoke-static {v3, v14}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v15

    invoke-virtual {v13, v15, v9}, Landroid/view/View;->measure(II)V

    .line 199
    iget-object v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    invoke-virtual {v13}, Landroid/view/View;->getMeasuredHeight()I

    move-result v13

    sub-int/2addr v10, v13

    goto :goto_5

    .line 200
    :cond_b
    iget-boolean v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->noTitleNoPadding:Z

    if-nez v13, :cond_c

    .line 201
    iget v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->noTitlePaddingFull:I

    add-int/2addr v11, v13

    .line 204
    :cond_c
    :goto_5
    iget-object v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-static {v13}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v13

    if-eqz v13, :cond_10

    .line 205
    iget-object v13, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-static {v3, v14}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v14

    sub-int v15, v10, v12

    const/high16 v5, -0x80000000

    .line 206
    invoke-static {v15, v5}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v5

    .line 205
    invoke-virtual {v13, v14, v5}, Landroid/view/View;->measure(II)V

    .line 208
    iget-object v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    sub-int v13, v10, v11

    if-gt v5, v13, :cond_f

    .line 209
    iget-boolean v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->reducePaddingNoTitleNoButtons:Z

    if-eqz v5, :cond_e

    iget-object v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->titleBar:Landroid/view/View;

    invoke-static {v5}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->isVisible(Landroid/view/View;)Z

    move-result v5

    if-nez v5, :cond_e

    if-eqz v6, :cond_d

    goto :goto_6

    .line 213
    :cond_d
    iput-boolean v9, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 214
    iget-object v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    add-int/2addr v5, v12

    sub-int/2addr v10, v5

    goto :goto_7

    .line 210
    :cond_e
    :goto_6
    const/4 v5, 0x1

    iput-boolean v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 211
    iget-object v5, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->content:Landroid/view/View;

    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    add-int/2addr v5, v11

    sub-int/2addr v10, v5

    goto :goto_7

    .line 217
    :cond_f
    iput-boolean v9, v0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->useFullPadding:Z

    .line 218
    const/4 v10, 0x0

    .line 223
    :cond_10
    :goto_7
    sub-int v5, v4, v10

    invoke-virtual {v0, v3, v5}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->setMeasuredDimension(II)V

    .line 224
    return-void
.end method

.method public setButtonGravity(Lcom/afollestad/materialdialogs/GravityEnum;)V
    .locals 0
    .param p1, "gravity"    # Lcom/afollestad/materialdialogs/GravityEnum;

    .line 361
    iput-object p1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttonGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 362
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->invertGravityIfNecessary()V

    .line 363
    return-void
.end method

.method public setButtonStackedGravity(Lcom/afollestad/materialdialogs/GravityEnum;)V
    .locals 4
    .param p1, "gravity"    # Lcom/afollestad/materialdialogs/GravityEnum;

    .line 381
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->buttons:[Lcom/afollestad/materialdialogs/internal/MDButton;

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, v0, v2

    .line 382
    .local v3, "mButton":Lcom/afollestad/materialdialogs/internal/MDButton;
    if-eqz v3, :cond_0

    .line 383
    invoke-virtual {v3, p1}, Lcom/afollestad/materialdialogs/internal/MDButton;->setStackedGravity(Lcom/afollestad/materialdialogs/GravityEnum;)V

    .line 381
    .end local v3    # "mButton":Lcom/afollestad/materialdialogs/internal/MDButton;
    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 385
    :cond_1
    return-void
.end method

.method public setDividerColor(I)V
    .locals 1
    .param p1, "color"    # I

    .line 356
    iget-object v0, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->dividerPaint:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setColor(I)V

    .line 357
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->invalidate()V

    .line 358
    return-void
.end method

.method public setMaxHeight(I)V
    .locals 0
    .param p1, "maxHeight"    # I

    .line 107
    iput p1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->maxHeight:I

    .line 108
    return-void
.end method

.method public setStackingBehavior(Lcom/afollestad/materialdialogs/StackingBehavior;)V
    .locals 0
    .param p1, "behavior"    # Lcom/afollestad/materialdialogs/StackingBehavior;

    .line 351
    iput-object p1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->stackBehavior:Lcom/afollestad/materialdialogs/StackingBehavior;

    .line 352
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->invalidate()V

    .line 353
    return-void
.end method
