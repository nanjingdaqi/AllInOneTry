.class Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;
.super Ljava/lang/Object;
.source "ColorChooserDialog.java"

# interfaces
.implements Landroid/widget/SeekBar$OnSeekBarChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->toggleCustom(Lcom/afollestad/materialdialogs/MaterialDialog;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V
    .locals 0
    .param p1, "this$0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 462
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onProgressChanged(Landroid/widget/SeekBar;IZ)V
    .locals 7
    .param p1, "seekBar"    # Landroid/widget/SeekBar;
    .param p2, "progress"    # I
    .param p3, "fromUser"    # Z
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "DefaultLocale"
        }
    .end annotation

    .line 467
    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p3, :cond_1

    .line 468
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v2

    iget-boolean v2, v2, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustomAlpha:Z

    if-eqz v2, :cond_0

    .line 469
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/SeekBar;->getProgress()I

    move-result v2

    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 470
    invoke-static {v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/SeekBar;->getProgress()I

    move-result v3

    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 471
    invoke-static {v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v4

    invoke-virtual {v4}, Landroid/widget/SeekBar;->getProgress()I

    move-result v4

    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 472
    invoke-static {v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v5

    invoke-virtual {v5}, Landroid/widget/SeekBar;->getProgress()I

    move-result v5

    .line 469
    invoke-static {v2, v3, v4, v5}, Landroid/graphics/Color;->argb(IIII)I

    move-result v2

    .line 473
    .local v2, "color":I
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1700(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/EditText;

    move-result-object v3

    const-string v4, "%08X"

    new-array v5, v1, [Ljava/lang/Object;

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v0

    invoke-static {v4, v5}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 474
    .end local v2    # "color":I
    goto :goto_0

    .line 475
    :cond_0
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/SeekBar;->getProgress()I

    move-result v2

    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 476
    invoke-static {v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/SeekBar;->getProgress()I

    move-result v3

    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 477
    invoke-static {v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v4

    invoke-virtual {v4}, Landroid/widget/SeekBar;->getProgress()I

    move-result v4

    .line 475
    invoke-static {v2, v3, v4}, Landroid/graphics/Color;->rgb(III)I

    move-result v2

    .line 478
    .restart local v2    # "color":I
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1700(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/EditText;

    move-result-object v3

    const-string v4, "%06X"

    new-array v5, v1, [Ljava/lang/Object;

    const v6, 0xffffff

    and-int/2addr v6, v2

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v0

    invoke-static {v4, v5}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 481
    .end local v2    # "color":I
    :cond_1
    :goto_0
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;

    move-result-object v2

    const-string v3, "%d"

    new-array v4, v1, [Ljava/lang/Object;

    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v5

    invoke-virtual {v5}, Landroid/widget/SeekBar;->getProgress()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v4, v0

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 482
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1800(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;

    move-result-object v2

    const-string v3, "%d"

    new-array v4, v1, [Ljava/lang/Object;

    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v5

    invoke-virtual {v5}, Landroid/widget/SeekBar;->getProgress()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v4, v0

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 483
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;

    move-result-object v2

    const-string v3, "%d"

    new-array v4, v1, [Ljava/lang/Object;

    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v5

    invoke-virtual {v5}, Landroid/widget/SeekBar;->getProgress()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v4, v0

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 484
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$2000(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;

    move-result-object v2

    const-string v3, "%d"

    new-array v1, v1, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v4

    invoke-virtual {v4}, Landroid/widget/SeekBar;->getProgress()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v1, v0

    invoke-static {v3, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 485
    return-void
.end method

.method public onStartTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0
    .param p1, "seekBar"    # Landroid/widget/SeekBar;

    .line 488
    return-void
.end method

.method public onStopTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0
    .param p1, "seekBar"    # Landroid/widget/SeekBar;

    .line 491
    return-void
.end method
