.class Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;
.super Ljava/lang/Object;
.source "ColorChooserDialog.java"

# interfaces
.implements Lcom/afollestad/materialdialogs/MaterialDialog$SingleButtonCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
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

    .line 344
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Lcom/afollestad/materialdialogs/MaterialDialog;Lcom/afollestad/materialdialogs/DialogAction;)V
    .locals 2
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "which"    # Lcom/afollestad/materialdialogs/DialogAction;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .line 347
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 348
    sget-object v0, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v1

    iget v1, v1, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCancelBtn:I

    invoke-virtual {p1, v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    .line 349
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;Z)V

    .line 350
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    const/4 v1, -0x1

    invoke-static {v0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)V

    .line 351
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$600(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    goto :goto_0

    .line 353
    :cond_0
    invoke-virtual {p1}, Lcom/afollestad/materialdialogs/MaterialDialog;->cancel()V

    .line 355
    :goto_0
    return-void
.end method
