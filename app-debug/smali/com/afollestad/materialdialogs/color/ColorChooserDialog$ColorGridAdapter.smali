.class Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;
.super Landroid/widget/BaseAdapter;
.source "ColorChooserDialog.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "ColorGridAdapter"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V
    .locals 0

    .line 531
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-direct {p0}, Landroid/widget/BaseAdapter;-><init>()V

    .line 532
    return-void
.end method


# virtual methods
.method public getCount()I
    .locals 2

    .line 535
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[[I

    move-result-object v0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v1

    aget-object v0, v0, v1

    array-length v0, v0

    return v0

    .line 536
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[I

    move-result-object v0

    array-length v0, v0

    return v0
.end method

.method public getItem(I)Ljava/lang/Object;
    .locals 2
    .param p1, "position"    # I

    .line 540
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[[I

    move-result-object v0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v1

    aget-object v0, v0, v1

    aget v0, v0, p1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    return-object v0

    .line 541
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[I

    move-result-object v0

    aget v0, v0, p1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    return-object v0
.end method

.method public getItemId(I)J
    .locals 2
    .param p1, "position"    # I

    .line 545
    int-to-long v0, p1

    return-wide v0
.end method

.method public getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 7
    .param p1, "position"    # I
    .param p2, "convertView"    # Landroid/view/View;
    .param p3, "parent"    # Landroid/view/ViewGroup;
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "DefaultLocale"
        }
    .end annotation

    .line 551
    if-nez p2, :cond_0

    .line 552
    new-instance v0, Lcom/afollestad/materialdialogs/color/CircleView;

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;-><init>(Landroid/content/Context;)V

    move-object p2, v0

    .line 553
    new-instance v0, Landroid/widget/AbsListView$LayoutParams;

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v2

    invoke-direct {v0, v1, v2}, Landroid/widget/AbsListView$LayoutParams;-><init>(II)V

    invoke-virtual {p2, v0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 555
    :cond_0
    move-object v0, p2

    check-cast v0, Lcom/afollestad/materialdialogs/color/CircleView;

    .line 557
    .local v0, "child":Lcom/afollestad/materialdialogs/color/CircleView;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[[I

    move-result-object v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v2

    aget-object v1, v1, v2

    aget v1, v1, p1

    goto :goto_0

    :cond_1
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[I

    move-result-object v1

    aget v1, v1, p1

    .line 558
    .local v1, "color":I
    :goto_0
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/color/CircleView;->setBackgroundColor(I)V

    .line 559
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Z

    move-result v2

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eqz v2, :cond_3

    .line 560
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v2

    if-ne v2, p1, :cond_2

    const/4 v2, 0x1

    goto :goto_1

    :cond_2
    const/4 v2, 0x0

    :goto_1
    invoke-virtual {v0, v2}, Lcom/afollestad/materialdialogs/color/CircleView;->setSelected(Z)V

    goto :goto_3

    .line 561
    :cond_3
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v2

    if-ne v2, p1, :cond_4

    const/4 v2, 0x1

    goto :goto_2

    :cond_4
    const/4 v2, 0x0

    :goto_2
    invoke-virtual {v0, v2}, Lcom/afollestad/materialdialogs/color/CircleView;->setSelected(Z)V

    .line 562
    :goto_3
    const-string v2, "%d:%d"

    const/4 v5, 0x2

    new-array v5, v5, [Ljava/lang/Object;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v3

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v5, v4

    invoke-static {v2, v5}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/afollestad/materialdialogs/color/CircleView;->setTag(Ljava/lang/Object;)V

    .line 563
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-virtual {v0, v2}, Lcom/afollestad/materialdialogs/color/CircleView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 564
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-virtual {v0, v2}, Lcom/afollestad/materialdialogs/color/CircleView;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 565
    return-object p2
.end method
