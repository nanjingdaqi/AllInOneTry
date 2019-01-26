.class public Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
.super Ljava/lang/Object;
.source "FolderChooserDialog.java"

# interfaces
.implements Ljava/io/Serializable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field allowNewFolder:Z

.field cancelButton:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field chooseButton:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field final transient context:Landroid/support/v7/app/AppCompatActivity;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation
.end field

.field goUpLabel:Ljava/lang/String;

.field initialPath:Ljava/lang/String;

.field newFolderButton:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field tag:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/support/v7/app/AppCompatActivity;)V
    .locals 1
    .param p1    # Landroid/support/v7/app/AppCompatActivity;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<ActivityType:",
            "Landroid/support/v7/app/AppCompatActivity;",
            ":",
            "Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$FolderCallback;",
            ">(TActivityType;)V"
        }
    .end annotation

    .line 226
    .local p1, "context":Landroid/support/v7/app/AppCompatActivity;, "TActivityType;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 227
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->context:Landroid/support/v7/app/AppCompatActivity;

    .line 228
    sget v0, Lcom/afollestad/materialdialogs/commons/R$string;->md_choose_label:I

    iput v0, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->chooseButton:I

    .line 229
    const/high16 v0, 0x1040000

    iput v0, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->cancelButton:I

    .line 230
    const-string v0, "..."

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->goUpLabel:Ljava/lang/String;

    .line 231
    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->initialPath:Ljava/lang/String;

    .line 232
    return-void
.end method


# virtual methods
.method public allowNewFolder(ZI)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
    .locals 0
    .param p1, "allow"    # Z
    .param p2, "buttonLabel"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 250
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->allowNewFolder:Z

    .line 251
    if-nez p2, :cond_0

    .line 252
    sget p2, Lcom/afollestad/materialdialogs/commons/R$string;->new_folder:I

    .line 253
    :cond_0
    iput p2, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->newFolderButton:I

    .line 254
    return-object p0
.end method

.method public build()Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;
    .locals 3
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 272
    new-instance v0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    invoke-direct {v0}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;-><init>()V

    .line 273
    .local v0, "dialog":Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 274
    .local v1, "args":Landroid/os/Bundle;
    const-string v2, "builder"

    invoke-virtual {v1, v2, p0}, Landroid/os/Bundle;->putSerializable(Ljava/lang/String;Ljava/io/Serializable;)V

    .line 275
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->setArguments(Landroid/os/Bundle;)V

    .line 276
    return-object v0
.end method

.method public cancelButton(I)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 240
    iput p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->cancelButton:I

    .line 241
    return-object p0
.end method

.method public chooseButton(I)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 235
    iput p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->chooseButton:I

    .line 236
    return-object p0
.end method

.method public goUpLabel(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # Ljava/lang/String;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 245
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->goUpLabel:Ljava/lang/String;

    .line 246
    return-object p0
.end method

.method public initialPath(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
    .locals 0
    .param p1, "initialPath"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 258
    if-nez p1, :cond_0

    .line 259
    sget-object p1, Ljava/io/File;->separator:Ljava/lang/String;

    .line 260
    :cond_0
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->initialPath:Ljava/lang/String;

    .line 261
    return-object p0
.end method

.method public show()Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;
    .locals 2
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 280
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->build()Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;

    move-result-object v0

    .line 281
    .local v0, "dialog":Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->context:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog;->show(Landroid/support/v4/app/FragmentActivity;)V

    .line 282
    return-object v0
.end method

.method public tag(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;
    .locals 0
    .param p1, "tag"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 265
    if-nez p1, :cond_0

    .line 266
    const-string p1, "[MD_FOLDER_SELECTOR]"

    .line 267
    :cond_0
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FolderChooserDialog$Builder;->tag:Ljava/lang/String;

    .line 268
    return-object p0
.end method
