.class Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;
.super Ljava/lang/Object;
.source "ColorChooserDialog.java"

# interfaces
.implements Landroid/text/TextWatcher;


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

    .line 422
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public afterTextChanged(Landroid/text/Editable;)V
    .locals 0
    .param p1, "s"    # Landroid/text/Editable;

    .line 458
    return-void
.end method

.method public beforeTextChanged(Ljava/lang/CharSequence;III)V
    .locals 0
    .param p1, "s"    # Ljava/lang/CharSequence;
    .param p2, "start"    # I
    .param p3, "count"    # I
    .param p4, "after"    # I

    .line 425
    return-void
.end method

.method public onTextChanged(Ljava/lang/CharSequence;III)V
    .locals 7
    .param p1, "s"    # Ljava/lang/CharSequence;
    .param p2, "start"    # I
    .param p3, "before"    # I
    .param p4, "count"    # I

    .line 430
    :try_start_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "#"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {p1}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    invoke-static {v0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$902(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)I
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    .line 433
    goto :goto_0

    .line 431
    :catch_0
    move-exception v0

    .line 432
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    const/high16 v2, -0x1000000

    invoke-static {v1, v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$902(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)I

    .line 434
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :goto_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1000(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackgroundColor(I)V

    .line 435
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/SeekBar;->getVisibility()I

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 436
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v0

    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    .line 437
    .local v0, "alpha":I
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v2

    invoke-virtual {v2, v0}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 438
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;

    move-result-object v2

    sget-object v3, Ljava/util/Locale;->US:Ljava/util/Locale;

    const-string v4, "%d"

    const/4 v5, 0x1

    new-array v5, v5, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v1

    invoke-static {v3, v4, v5}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 440
    .end local v0    # "alpha":I
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/SeekBar;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    .line 441
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v0

    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    .line 442
    .restart local v0    # "alpha":I
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v2

    invoke-virtual {v2, v0}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 444
    .end local v0    # "alpha":I
    :cond_1
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v0

    invoke-static {v0}, Landroid/graphics/Color;->red(I)I

    move-result v0

    .line 445
    .local v0, "red":I
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v2

    invoke-virtual {v2, v0}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 446
    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v2

    invoke-static {v2}, Landroid/graphics/Color;->green(I)I

    move-result v2

    .line 447
    .local v2, "green":I
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v3

    invoke-virtual {v3, v2}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 448
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v3

    invoke-static {v3}, Landroid/graphics/Color;->blue(I)I

    move-result v3

    .line 449
    .local v3, "blue":I
    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;

    move-result-object v4

    invoke-virtual {v4, v3}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 450
    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v4, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;Z)V

    .line 451
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    const/4 v4, -0x1

    invoke-static {v1, v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$1600(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)V

    .line 452
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1, v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)V

    .line 453
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$000(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    .line 454
    return-void
.end method
