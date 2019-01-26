.class public Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
.super Ljava/lang/Object;
.source "FileChooserDialog.java"

# interfaces
.implements Ljava/io/Serializable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field cancelButton:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field final transient context:Landroid/support/v7/app/AppCompatActivity;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation
.end field

.field extensions:[Ljava/lang/String;

.field goUpLabel:Ljava/lang/String;

.field initialPath:Ljava/lang/String;

.field mimeType:Ljava/lang/String;

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
            "Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;",
            ">(TActivityType;)V"
        }
    .end annotation

    .line 241
    .local p1, "context":Landroid/support/v7/app/AppCompatActivity;, "TActivityType;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 242
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->context:Landroid/support/v7/app/AppCompatActivity;

    .line 243
    const/high16 v0, 0x1040000

    iput v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->cancelButton:I

    .line 244
    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->initialPath:Ljava/lang/String;

    .line 245
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->mimeType:Ljava/lang/String;

    .line 246
    const-string v0, "..."

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->goUpLabel:Ljava/lang/String;

    .line 247
    return-void
.end method


# virtual methods
.method public build()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;
    .locals 3
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 284
    new-instance v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;

    invoke-direct {v0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;-><init>()V

    .line 285
    .local v0, "dialog":Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 286
    .local v1, "args":Landroid/os/Bundle;
    const-string v2, "builder"

    invoke-virtual {v1, v2, p0}, Landroid/os/Bundle;->putSerializable(Ljava/lang/String;Ljava/io/Serializable;)V

    .line 287
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->setArguments(Landroid/os/Bundle;)V

    .line 288
    return-object v0
.end method

.method public cancelButton(I)Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 250
    iput p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->cancelButton:I

    .line 251
    return-object p0
.end method

.method public varargs extensionsFilter([Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 0
    .param p1, "extensions"    # [Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 267
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->extensions:[Ljava/lang/String;

    .line 268
    return-object p0
.end method

.method public goUpLabel(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # Ljava/lang/String;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 279
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->goUpLabel:Ljava/lang/String;

    .line 280
    return-object p0
.end method

.method public initialPath(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 0
    .param p1, "initialPath"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 255
    if-nez p1, :cond_0

    .line 256
    sget-object p1, Ljava/io/File;->separator:Ljava/lang/String;

    .line 257
    :cond_0
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->initialPath:Ljava/lang/String;

    .line 258
    return-object p0
.end method

.method public mimeType(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 0
    .param p1, "type"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 262
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->mimeType:Ljava/lang/String;

    .line 263
    return-object p0
.end method

.method public show()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;
    .locals 2
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 292
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->build()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;

    move-result-object v0

    .line 293
    .local v0, "dialog":Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->context:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->show(Landroid/support/v4/app/FragmentActivity;)V

    .line 294
    return-object v0
.end method

.method public tag(Ljava/lang/String;)Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 0
    .param p1, "tag"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 272
    if-nez p1, :cond_0

    .line 273
    const-string p1, "[MD_FILE_SELECTOR]"

    .line 274
    :cond_0
    iput-object p1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->tag:Ljava/lang/String;

    .line 275
    return-object p0
.end method
