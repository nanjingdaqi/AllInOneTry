.class Landroid/support/design/widget/BottomSheetBehavior$1;
.super Landroid/support/v4/widget/ViewDragHelper$Callback;
.source "BottomSheetBehavior.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/design/widget/BottomSheetBehavior;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroid/support/design/widget/BottomSheetBehavior;


# direct methods
.method constructor <init>(Landroid/support/design/widget/BottomSheetBehavior;)V
    .locals 0

    .line 520
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    iput-object p1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-direct {p0}, Landroid/support/v4/widget/ViewDragHelper$Callback;-><init>()V

    return-void
.end method


# virtual methods
.method public clampViewPositionHorizontal(Landroid/view/View;II)I
    .locals 1
    .param p1, "child"    # Landroid/view/View;
    .param p2, "left"    # I
    .param p3, "dx"    # I

    .line 591
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v0

    return v0
.end method

.method public clampViewPositionVertical(Landroid/view/View;II)I
    .locals 2
    .param p1, "child"    # Landroid/view/View;
    .param p2, "top"    # I
    .param p3, "dy"    # I

    .line 586
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$700(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1}, Landroid/support/design/widget/BottomSheetBehavior;->access$800(Landroid/support/design/widget/BottomSheetBehavior;)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1}, Landroid/support/design/widget/BottomSheetBehavior;->access$1000(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v1

    goto :goto_0

    :cond_0
    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1}, Landroid/support/design/widget/BottomSheetBehavior;->access$1100(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v1

    :goto_0
    invoke-static {p2, v0, v1}, Landroid/support/design/widget/MathUtils;->constrain(III)I

    move-result v0

    return v0
.end method

.method public getViewVerticalDragRange(Landroid/view/View;)I
    .locals 2
    .param p1, "child"    # Landroid/view/View;

    .line 596
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$800(Landroid/support/design/widget/BottomSheetBehavior;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 597
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$1000(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1}, Landroid/support/design/widget/BottomSheetBehavior;->access$700(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v1

    sub-int/2addr v0, v1

    return v0

    .line 599
    :cond_0
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$1100(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1}, Landroid/support/design/widget/BottomSheetBehavior;->access$700(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v1

    sub-int/2addr v0, v1

    return v0
.end method

.method public onViewDragStateChanged(I)V
    .locals 2
    .param p1, "state"    # I

    .line 547
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    .line 548
    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1, v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$600(Landroid/support/design/widget/BottomSheetBehavior;I)V

    .line 550
    :cond_0
    return-void
.end method

.method public onViewPositionChanged(Landroid/view/View;IIII)V
    .locals 1
    .param p1, "changedView"    # Landroid/view/View;
    .param p2, "left"    # I
    .param p3, "top"    # I
    .param p4, "dx"    # I
    .param p5, "dy"    # I

    .line 542
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0, p3}, Landroid/support/design/widget/BottomSheetBehavior;->access$500(Landroid/support/design/widget/BottomSheetBehavior;I)V

    .line 543
    return-void
.end method

.method public onViewReleased(Landroid/view/View;FF)V
    .locals 5
    .param p1, "releasedChild"    # Landroid/view/View;
    .param p2, "xvel"    # F
    .param p3, "yvel"    # F

    .line 556
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    const/4 v0, 0x4

    const/4 v1, 0x0

    cmpg-float v2, p3, v1

    if-gez v2, :cond_0

    .line 557
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$700(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    .line 558
    .local v0, "top":I
    const/4 v1, 0x3

    .line 573
    .local v0, "targetState":I
    .local v1, "top":I
    :goto_0
    move v4, v1

    move v1, v0

    move v0, v4

    goto :goto_2

    .line 559
    .end local v0    # "targetState":I
    .end local v1    # "top":I
    :cond_0
    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v2}, Landroid/support/design/widget/BottomSheetBehavior;->access$800(Landroid/support/design/widget/BottomSheetBehavior;)Z

    move-result v2

    if-eqz v2, :cond_1

    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v2, p1, p3}, Landroid/support/design/widget/BottomSheetBehavior;->access$900(Landroid/support/design/widget/BottomSheetBehavior;Landroid/view/View;F)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 560
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$1000(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    .line 561
    .local v0, "top":I
    const/4 v1, 0x5

    goto :goto_0

    .line 562
    .end local v0    # "top":I
    :cond_1
    cmpl-float v1, p3, v1

    if-nez v1, :cond_3

    .line 563
    invoke-virtual {p1}, Landroid/view/View;->getTop()I

    move-result v1

    .line 564
    .local v1, "currentTop":I
    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v2}, Landroid/support/design/widget/BottomSheetBehavior;->access$700(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v2

    sub-int v2, v1, v2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    iget-object v3, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v3}, Landroid/support/design/widget/BottomSheetBehavior;->access$1100(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v3

    sub-int v3, v1, v3

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v3

    if-ge v2, v3, :cond_2

    .line 565
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$700(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    .line 566
    .restart local v0    # "top":I
    const/4 v2, 0x3

    .line 569
    move v4, v2

    move v2, v0

    move v0, v4

    goto :goto_1

    .line 568
    .end local v0    # "top":I
    :cond_2
    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v2}, Landroid/support/design/widget/BottomSheetBehavior;->access$1100(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v2

    .line 569
    .local v2, "top":I
    nop

    .line 571
    .end local v1    # "currentTop":I
    .local v0, "targetState":I
    :goto_1
    nop

    .line 573
    move v1, v2

    goto :goto_2

    .line 572
    .end local v0    # "targetState":I
    .end local v2    # "top":I
    :cond_3
    iget-object v1, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v1}, Landroid/support/design/widget/BottomSheetBehavior;->access$1100(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v1

    .line 573
    .local v1, "top":I
    nop

    .line 575
    .restart local v0    # "targetState":I
    :goto_2
    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v2}, Landroid/support/design/widget/BottomSheetBehavior;->access$1200(Landroid/support/design/widget/BottomSheetBehavior;)Landroid/support/v4/widget/ViewDragHelper;

    move-result-object v2

    invoke-virtual {p1}, Landroid/view/View;->getLeft()I

    move-result v3

    invoke-virtual {v2, v3, v1}, Landroid/support/v4/widget/ViewDragHelper;->settleCapturedViewAt(II)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 576
    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    const/4 v3, 0x2

    invoke-static {v2, v3}, Landroid/support/design/widget/BottomSheetBehavior;->access$600(Landroid/support/design/widget/BottomSheetBehavior;I)V

    .line 577
    new-instance v2, Landroid/support/design/widget/BottomSheetBehavior$SettleRunnable;

    iget-object v3, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-direct {v2, v3, p1, v0}, Landroid/support/design/widget/BottomSheetBehavior$SettleRunnable;-><init>(Landroid/support/design/widget/BottomSheetBehavior;Landroid/view/View;I)V

    invoke-static {p1, v2}, Landroid/support/v4/view/ViewCompat;->postOnAnimation(Landroid/view/View;Ljava/lang/Runnable;)V

    goto :goto_3

    .line 580
    :cond_4
    iget-object v2, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v2, v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$600(Landroid/support/design/widget/BottomSheetBehavior;I)V

    .line 582
    :goto_3
    return-void
.end method

.method public tryCaptureView(Landroid/view/View;I)Z
    .locals 4
    .param p1, "child"    # Landroid/view/View;
    .param p2, "pointerId"    # I

    .line 524
    .local p0, "this":Landroid/support/design/widget/BottomSheetBehavior$1;, "Landroid/support/design/widget/BottomSheetBehavior.1;"
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$000(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-ne v0, v1, :cond_0

    .line 525
    return v2

    .line 527
    :cond_0
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$100(Landroid/support/design/widget/BottomSheetBehavior;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 528
    return v2

    .line 530
    :cond_1
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$000(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    const/4 v3, 0x3

    if-ne v0, v3, :cond_2

    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$200(Landroid/support/design/widget/BottomSheetBehavior;)I

    move-result v0

    if-ne v0, p2, :cond_2

    .line 531
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$300(Landroid/support/design/widget/BottomSheetBehavior;)Ljava/lang/ref/WeakReference;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 532
    .local v0, "scroll":Landroid/view/View;
    if-eqz v0, :cond_2

    const/4 v3, -0x1

    invoke-static {v0, v3}, Landroid/support/v4/view/ViewCompat;->canScrollVertically(Landroid/view/View;I)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 534
    return v2

    .line 537
    .end local v0    # "scroll":Landroid/view/View;
    :cond_2
    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$400(Landroid/support/design/widget/BottomSheetBehavior;)Ljava/lang/ref/WeakReference;

    move-result-object v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Landroid/support/design/widget/BottomSheetBehavior$1;->this$0:Landroid/support/design/widget/BottomSheetBehavior;

    invoke-static {v0}, Landroid/support/design/widget/BottomSheetBehavior;->access$400(Landroid/support/design/widget/BottomSheetBehavior;)Ljava/lang/ref/WeakReference;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    if-ne v0, p1, :cond_3

    goto :goto_0

    :cond_3
    const/4 v1, 0x0

    :goto_0
    return v1
.end method
