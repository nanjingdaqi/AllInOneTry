.class Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$4;
.super Ljava/lang/Object;
.source "FolderChooserDialog.java"

# interfaces
.implements Lcom/afollestad/materialdialogs/MaterialDialog$InputCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->createNewFolder()V
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

    .line 151
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onInput(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/lang/CharSequence;)V
    .locals 4
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "input"    # Ljava/lang/CharSequence;

    .line 155
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->access$100(Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;)Ljava/io/File;

    move-result-object v1

    invoke-interface {p2}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 156
    .local v0, "newFi":Ljava/io/File;
    invoke-virtual {v0}, Ljava/io/File;->mkdir()Z

    move-result v1

    if-nez v1, :cond_0

    .line 157
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unable to create folder "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", make sure you have the WRITE_EXTERNAL_STORAGE permission or root permissions."

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 159
    .local v1, "msg":Ljava/lang/String;
    iget-object v2, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-virtual {v2}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v2

    const/4 v3, 0x1

    invoke-static {v2, v1, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/Toast;->show()V

    .line 160
    .end local v1    # "msg":Ljava/lang/String;
    goto :goto_0

    .line 161
    :cond_0
    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$4;->this$0:Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->access$400(Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;)V

    .line 163
    :goto_0
    return-void
.end method
