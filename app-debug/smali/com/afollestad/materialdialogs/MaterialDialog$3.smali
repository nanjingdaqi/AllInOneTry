.class Lcom/afollestad/materialdialogs/MaterialDialog$3;
.super Ljava/lang/Object;
.source "MaterialDialog.java"

# interfaces
.implements Landroid/text/TextWatcher;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/MaterialDialog;->setInternalInputCallback()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/afollestad/materialdialogs/MaterialDialog;


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/MaterialDialog;)V
    .locals 0
    .param p1, "this$0"    # Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 1911
    iput-object p1, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public afterTextChanged(Landroid/text/Editable;)V
    .locals 0
    .param p1, "s"    # Landroid/text/Editable;

    .line 1933
    return-void
.end method

.method public beforeTextChanged(Ljava/lang/CharSequence;III)V
    .locals 0
    .param p1, "s"    # Ljava/lang/CharSequence;
    .param p2, "start"    # I
    .param p3, "count"    # I
    .param p4, "after"    # I

    .line 1914
    return-void
.end method

.method public onTextChanged(Ljava/lang/CharSequence;III)V
    .locals 6
    .param p1, "s"    # Ljava/lang/CharSequence;
    .param p2, "start"    # I
    .param p3, "before"    # I
    .param p4, "count"    # I

    .line 1919
    invoke-interface {p1}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    .line 1920
    .local v0, "length":I
    const/4 v1, 0x0

    .line 1921
    .local v1, "emptyDisabled":Z
    iget-object v2, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-boolean v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->inputAllowEmpty:Z

    if-nez v2, :cond_2

    .line 1922
    const/4 v2, 0x0

    const/4 v3, 0x1

    if-nez v0, :cond_0

    const/4 v4, 0x1

    goto :goto_0

    :cond_0
    const/4 v4, 0x0

    :goto_0
    move v1, v4

    .line 1923
    iget-object v4, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    sget-object v5, Lcom/afollestad/materialdialogs/DialogAction;->POSITIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-virtual {v4, v5}, Lcom/afollestad/materialdialogs/MaterialDialog;->getActionButton(Lcom/afollestad/materialdialogs/DialogAction;)Lcom/afollestad/materialdialogs/internal/MDButton;

    move-result-object v4

    .line 1924
    .local v4, "positiveAb":Landroid/view/View;
    if-nez v1, :cond_1

    const/4 v2, 0x1

    nop

    :cond_1
    invoke-virtual {v4, v2}, Landroid/view/View;->setEnabled(Z)V

    .line 1926
    .end local v4    # "positiveAb":Landroid/view/View;
    :cond_2
    iget-object v2, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-virtual {v2, v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog;->invalidateInputMinMaxIndicator(IZ)V

    .line 1927
    iget-object v2, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-boolean v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->alwaysCallInputCallback:Z

    if-eqz v2, :cond_3

    .line 1928
    iget-object v2, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->inputCallback:Lcom/afollestad/materialdialogs/MaterialDialog$InputCallback;

    iget-object v3, p0, Lcom/afollestad/materialdialogs/MaterialDialog$3;->this$0:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-interface {v2, v3, p1}, Lcom/afollestad/materialdialogs/MaterialDialog$InputCallback;->onInput(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/lang/CharSequence;)V

    .line 1929
    :cond_3
    return-void
.end method
