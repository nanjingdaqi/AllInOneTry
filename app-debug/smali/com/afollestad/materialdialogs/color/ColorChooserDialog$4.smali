.class Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;
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

    .line 337
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Lcom/afollestad/materialdialogs/MaterialDialog;Lcom/afollestad/materialdialogs/DialogAction;)V
    .locals 3
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "which"    # Lcom/afollestad/materialdialogs/DialogAction;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param

    .line 340
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$800(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;

    move-result-object v0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->access$700(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I

    move-result v2

    invoke-interface {v0, v1, v2}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;->onColorSelection(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)V

    .line 341
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->dismiss()V

    .line 342
    return-void
.end method
