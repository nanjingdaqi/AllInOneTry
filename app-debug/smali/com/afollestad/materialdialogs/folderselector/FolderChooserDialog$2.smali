.class Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$2;
.super Ljava/lang/Object;
.source "FolderChooserDialog.java"

# interfaces
.implements Lcom/afollestad/materialdialogs/MaterialDialog$SingleButtonCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;)V
    .locals 0
    .param p1, "this$0"    # Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    .line 110
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$2;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

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

    .line 113
    invoke-virtual {p1}, Lcom/afollestad/materialdialogs/MaterialDialog;->dismiss()V

    .line 114
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$2;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->access$200(Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$FolderCallback;

    move-result-object v0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$2;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$2;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->access$100(Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;)Ljava/io/File;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$FolderCallback;->onFolderSelection(Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;Ljava/io/File;)V

    .line 115
    return-void
.end method
