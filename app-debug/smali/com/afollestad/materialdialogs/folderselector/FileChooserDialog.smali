.class public Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;
.super Landroid/support/v4/app/DialogFragment;
.source "FileChooserDialog.java"

# interfaces
.implements Lcom/afollestad/materialdialogs/MaterialDialog$ListCallback;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileSorter;,
        Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;,
        Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;
    }
.end annotation


# static fields
.field private static final DEFAULT_TAG:Ljava/lang/String; = "[MD_FILE_SELECTOR]"


# instance fields
.field private callback:Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;

.field private canGoUp:Z

.field private parentContents:[Ljava/io/File;

.field private parentFolder:Ljava/io/File;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 48
    invoke-direct {p0}, Landroid/support/v4/app/DialogFragment;-><init>()V

    .line 39
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    .line 49
    return-void
.end method

.method private checkIfCanGoUp()V
    .locals 3

    .line 209
    const/4 v0, 0x0

    :try_start_0
    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v1

    const-string v2, "/"

    invoke-virtual {v1, v2}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    array-length v1, v1

    const/4 v2, 0x1

    if-le v1, v2, :cond_0

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    iput-boolean v2, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z
    :try_end_0
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    .line 212
    goto :goto_1

    .line 210
    :catch_0
    move-exception v1

    .line 211
    .local v1, "e":Ljava/lang/IndexOutOfBoundsException;
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    .line 213
    .end local v1    # "e":Ljava/lang/IndexOutOfBoundsException;
    :goto_1
    return-void
.end method

.method private getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;
    .locals 2
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 305
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "builder"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v0

    check-cast v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    return-object v0
.end method


# virtual methods
.method fileIsMimeType(Ljava/io/File;Ljava/lang/String;Landroid/webkit/MimeTypeMap;)Z
    .locals 12
    .param p1, "file"    # Ljava/io/File;
    .param p2, "mimeType"    # Ljava/lang/String;
    .param p3, "mimeTypeMap"    # Landroid/webkit/MimeTypeMap;

    .line 96
    const/4 v0, 0x1

    if-eqz p2, :cond_9

    const-string v1, "*/*"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 100
    :cond_0
    invoke-virtual {p1}, Ljava/io/File;->toURI()Ljava/net/URI;

    move-result-object v1

    invoke-virtual {v1}, Ljava/net/URI;->toString()Ljava/lang/String;

    move-result-object v1

    .line 101
    .local v1, "filename":Ljava/lang/String;
    const/16 v2, 0x2e

    invoke-virtual {v1, v2}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v2

    .line 102
    .local v2, "dotPos":I
    const/4 v3, -0x1

    const/4 v4, 0x0

    if-ne v2, v3, :cond_1

    .line 103
    return v4

    .line 105
    :cond_1
    add-int/lit8 v5, v2, 0x1

    invoke-virtual {v1, v5}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v5

    .line 106
    .local v5, "fileExtension":Ljava/lang/String;
    const-string v6, "json"

    invoke-virtual {v5, v6}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_2

    .line 107
    const-string v0, "application/json"

    invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    return v0

    .line 108
    :cond_2
    invoke-virtual {p3, v5}, Landroid/webkit/MimeTypeMap;->getMimeTypeFromExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 109
    .local v6, "fileType":Ljava/lang/String;
    if-nez v6, :cond_3

    .line 110
    return v4

    .line 113
    :cond_3
    invoke-virtual {v6, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_4

    .line 114
    return v0

    .line 117
    :cond_4
    const/16 v7, 0x2f

    invoke-virtual {p2, v7}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v8

    .line 118
    .local v8, "mimeTypeDelimiter":I
    if-ne v8, v3, :cond_5

    .line 119
    return v4

    .line 121
    :cond_5
    invoke-virtual {p2, v4, v8}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v9

    .line 122
    .local v9, "mimeTypeMainType":Ljava/lang/String;
    add-int/lit8 v10, v8, 0x1

    invoke-virtual {p2, v10}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v10

    .line 123
    .local v10, "mimeTypeSubtype":Ljava/lang/String;
    const-string v11, "*"

    invoke-virtual {v10, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-nez v11, :cond_6

    .line 124
    return v4

    .line 126
    :cond_6
    invoke-virtual {v6, v7}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v7

    .line 127
    .local v7, "fileTypeDelimiter":I
    if-ne v7, v3, :cond_7

    .line 128
    return v4

    .line 130
    :cond_7
    invoke-virtual {v6, v4, v7}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    .line 131
    .local v3, "fileTypeMainType":Ljava/lang/String;
    invoke-virtual {v3, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_8

    .line 132
    return v0

    .line 135
    .end local v1    # "filename":Ljava/lang/String;
    .end local v2    # "dotPos":I
    .end local v3    # "fileTypeMainType":Ljava/lang/String;
    .end local v5    # "fileExtension":Ljava/lang/String;
    .end local v6    # "fileType":Ljava/lang/String;
    .end local v7    # "fileTypeDelimiter":I
    .end local v8    # "mimeTypeDelimiter":I
    .end local v9    # "mimeTypeMainType":Ljava/lang/String;
    .end local v10    # "mimeTypeSubtype":Ljava/lang/String;
    :cond_8
    return v4

    .line 97
    :cond_9
    :goto_0
    return v0
.end method

.method getContentsArray()[Ljava/lang/CharSequence;
    .locals 4

    .line 52
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    const/4 v1, 0x0

    if-nez v0, :cond_1

    .line 53
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    if-eqz v0, :cond_0

    .line 54
    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/String;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v2

    iget-object v2, v2, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->goUpLabel:Ljava/lang/String;

    aput-object v2, v0, v1

    return-object v0

    .line 55
    :cond_0
    new-array v0, v1, [Ljava/lang/String;

    return-object v0

    .line 57
    :cond_1
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    array-length v0, v0

    iget-boolean v2, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    add-int/2addr v0, v2

    new-array v0, v0, [Ljava/lang/String;

    .line 58
    .local v0, "results":[Ljava/lang/String;
    iget-boolean v2, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    if-eqz v2, :cond_2

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v2

    iget-object v2, v2, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->goUpLabel:Ljava/lang/String;

    aput-object v2, v0, v1

    .line 59
    :cond_2
    nop

    .local v1, "i":I
    :goto_0
    iget-object v2, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    array-length v2, v2

    if-ge v1, v2, :cond_4

    .line 60
    iget-boolean v2, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    if-eqz v2, :cond_3

    add-int/lit8 v2, v1, 0x1

    goto :goto_1

    :cond_3
    move v2, v1

    :goto_1
    iget-object v3, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    aget-object v3, v3, v1

    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v3

    aput-object v3, v0, v2

    .line 59
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 61
    .end local v1    # "i":I
    :cond_4
    return-object v0
.end method

.method public getInitialPath()Ljava/lang/String;
    .locals 1
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 299
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v0

    iget-object v0, v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->initialPath:Ljava/lang/String;

    return-object v0
.end method

.method listFiles(Ljava/lang/String;[Ljava/lang/String;)[Ljava/io/File;
    .locals 16
    .param p1, "mimeType"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "extensions"    # [Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .line 65
    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    iget-object v3, v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v3}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v3

    .line 66
    .local v3, "contents":[Ljava/io/File;
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 67
    .local v4, "results":Ljava/util/List;, "Ljava/util/List<Ljava/io/File;>;"
    const/4 v5, 0x0

    if-eqz v3, :cond_7

    .line 68
    invoke-static {}, Landroid/webkit/MimeTypeMap;->getSingleton()Landroid/webkit/MimeTypeMap;

    move-result-object v6

    .line 69
    .local v6, "mimeTypeMap":Landroid/webkit/MimeTypeMap;
    array-length v7, v3

    const/4 v9, 0x0

    :goto_0
    if-ge v9, v7, :cond_6

    aget-object v10, v3, v9

    .line 70
    .local v10, "fi":Ljava/io/File;
    invoke-virtual {v10}, Ljava/io/File;->isDirectory()Z

    move-result v11

    if-eqz v11, :cond_0

    .line 71
    invoke-interface {v4, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 73
    :cond_0
    if-eqz v2, :cond_4

    .line 74
    const/4 v11, 0x0

    .line 75
    .local v11, "found":Z
    array-length v12, v2

    const/4 v13, 0x0

    :goto_1
    if-ge v13, v12, :cond_2

    aget-object v14, v2, v13

    .line 76
    .local v14, "ext":Ljava/lang/String;
    invoke-virtual {v10}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v14}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v15, v8}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_1

    .line 77
    const/4 v11, 0x1

    .line 78
    goto :goto_2

    .line 75
    .end local v14    # "ext":Ljava/lang/String;
    :cond_1
    add-int/lit8 v13, v13, 0x1

    goto :goto_1

    .line 81
    :cond_2
    :goto_2
    if-eqz v11, :cond_3

    invoke-interface {v4, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 82
    .end local v11    # "found":Z
    :cond_3
    goto :goto_3

    :cond_4
    if-eqz v1, :cond_5

    .line 83
    invoke-virtual {v0, v10, v1, v6}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->fileIsMimeType(Ljava/io/File;Ljava/lang/String;Landroid/webkit/MimeTypeMap;)Z

    move-result v8

    if-eqz v8, :cond_5

    .line 84
    invoke-interface {v4, v10}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 69
    .end local v10    # "fi":Ljava/io/File;
    :cond_5
    :goto_3
    add-int/lit8 v9, v9, 0x1

    goto :goto_0

    .line 89
    :cond_6
    new-instance v7, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileSorter;

    invoke-direct {v7, v5}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileSorter;-><init>(Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$1;)V

    invoke-static {v4, v7}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    .line 90
    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v5

    new-array v5, v5, [Ljava/io/File;

    invoke-interface {v4, v5}, Ljava/util/List;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v5

    check-cast v5, [Ljava/io/File;

    return-object v5

    .line 92
    .end local v6    # "mimeTypeMap":Landroid/webkit/MimeTypeMap;
    :cond_7
    return-object v5
.end method

.method public onAttach(Landroid/app/Activity;)V
    .locals 1
    .param p1, "activity"    # Landroid/app/Activity;

    .line 216
    invoke-super {p0, p1}, Landroid/support/v4/app/DialogFragment;->onAttach(Landroid/app/Activity;)V

    .line 217
    move-object v0, p1

    check-cast v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->callback:Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;

    .line 218
    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 142
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_0

    .line 143
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v0

    const-string v1, "android.permission.READ_EXTERNAL_STORAGE"

    invoke-static {v0, v1}, Landroid/support/v13/app/ActivityCompat;->checkSelfPermission(Landroid/content/Context;Ljava/lang/String;)I

    move-result v0

    if-eqz v0, :cond_0

    .line 145
    new-instance v0, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;-><init>(Landroid/content/Context;)V

    sget v1, Lcom/afollestad/materialdialogs/commons/R$string;->md_error_label:I

    .line 146
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->title(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    sget v1, Lcom/afollestad/materialdialogs/commons/R$string;->md_storage_perm_error:I

    .line 147
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->content(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    const v1, 0x104000a

    .line 148
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->positiveText(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    .line 149
    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->build()Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v0

    .line 145
    return-object v0

    .line 152
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "builder"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 154
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "current_path"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 155
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "current_path"

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v2

    iget-object v2, v2, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->initialPath:Ljava/lang/String;

    invoke-virtual {v0, v1, v2}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 156
    :cond_1
    new-instance v0, Ljava/io/File;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v1

    const-string v2, "current_path"

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    .line 157
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->checkIfCanGoUp()V

    .line 158
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v0

    iget-object v0, v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->mimeType:Ljava/lang/String;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v1

    iget-object v1, v1, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->extensions:[Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->listFiles(Ljava/lang/String;[Ljava/lang/String;)[Ljava/io/File;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    .line 159
    new-instance v0, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;-><init>(Landroid/content/Context;)V

    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    .line 160
    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->title(Ljava/lang/CharSequence;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    .line 161
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getContentsArray()[Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items([Ljava/lang/CharSequence;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    .line 162
    invoke-virtual {v0, p0}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->itemsCallback(Lcom/afollestad/materialdialogs/MaterialDialog$ListCallback;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    new-instance v1, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$1;

    invoke-direct {v1, p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$1;-><init>(Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;)V

    .line 163
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->onNegative(Lcom/afollestad/materialdialogs/MaterialDialog$SingleButtonCallback;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    const/4 v1, 0x0

    .line 169
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->autoDismiss(Z)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    .line 170
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v1

    iget v1, v1, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->cancelButton:I

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->negativeText(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    .line 171
    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->build()Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v0

    .line 159
    return-object v0

    .line 153
    :cond_2
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "You must create a FileChooserDialog using the Builder."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public onDismiss(Landroid/content/DialogInterface;)V
    .locals 1
    .param p1, "dialog"    # Landroid/content/DialogInterface;

    .line 175
    invoke-super {p0, p1}, Landroid/support/v4/app/DialogFragment;->onDismiss(Landroid/content/DialogInterface;)V

    .line 176
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->callback:Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;

    if-eqz v0, :cond_0

    .line 177
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->callback:Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;

    invoke-interface {v0, p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;->onFileChooserDismissed(Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;)V

    .line 179
    :cond_0
    return-void
.end method

.method public onSelection(Lcom/afollestad/materialdialogs/MaterialDialog;Landroid/view/View;ILjava/lang/CharSequence;)V
    .locals 4
    .param p1, "materialDialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;
    .param p2, "view"    # Landroid/view/View;
    .param p3, "i"    # I
    .param p4, "s"    # Ljava/lang/CharSequence;

    .line 183
    iget-boolean v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    if-nez p3, :cond_2

    .line 184
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    .line 185
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const-string v2, "/storage/emulated"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 186
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    .line 187
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getParent()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    :goto_0
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    goto :goto_2

    .line 189
    :cond_2
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    iget-boolean v2, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    if-eqz v2, :cond_3

    add-int/lit8 v2, p3, -0x1

    goto :goto_1

    :cond_3
    move v2, p3

    :goto_1
    aget-object v0, v0, v2

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    .line 190
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->canGoUp:Z

    .line 191
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const-string v1, "/storage/emulated"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 192
    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    .line 194
    :cond_4
    :goto_2
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->isFile()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 195
    iget-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->callback:Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;

    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-interface {v0, p0, v1}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$FileCallback;->onFileSelection(Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;Ljava/io/File;)V

    .line 196
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->dismiss()V

    goto :goto_3

    .line 198
    :cond_5
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v0

    iget-object v0, v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->mimeType:Ljava/lang/String;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v1

    iget-object v1, v1, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->extensions:[Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->listFiles(Ljava/lang/String;[Ljava/lang/String;)[Ljava/io/File;

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentContents:[Ljava/io/File;

    .line 199
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    check-cast v0, Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 200
    .local v0, "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog;->setTitle(Ljava/lang/CharSequence;)V

    .line 201
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v1

    const-string v2, "current_path"

    iget-object v3, p0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->parentFolder:Ljava/io/File;

    invoke-virtual {v3}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 202
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getContentsArray()[Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/MaterialDialog;->setItems([Ljava/lang/CharSequence;)V

    .line 204
    .end local v0    # "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    :goto_3
    return-void
.end method

.method public show(Landroid/support/v4/app/FragmentActivity;)V
    .locals 3
    .param p1, "context"    # Landroid/support/v4/app/FragmentActivity;

    .line 221
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;

    move-result-object v0

    iget-object v0, v0, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog$Builder;->tag:Ljava/lang/String;

    .line 222
    .local v0, "tag":Ljava/lang/String;
    invoke-virtual {p1}, Landroid/support/v4/app/FragmentActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/support/v4/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/support/v4/app/Fragment;

    move-result-object v1

    .line 223
    .local v1, "frag":Landroid/support/v4/app/Fragment;
    if-eqz v1, :cond_0

    .line 224
    move-object v2, v1

    check-cast v2, Landroid/support/v4/app/DialogFragment;

    invoke-virtual {v2}, Landroid/support/v4/app/DialogFragment;->dismiss()V

    .line 225
    invoke-virtual {p1}, Landroid/support/v4/app/FragmentActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v2

    invoke-virtual {v2}, Landroid/support/v4/app/FragmentManager;->beginTransaction()Landroid/support/v4/app/FragmentTransaction;

    move-result-object v2

    .line 226
    invoke-virtual {v2, v1}, Landroid/support/v4/app/FragmentTransaction;->remove(Landroid/support/v4/app/Fragment;)Landroid/support/v4/app/FragmentTransaction;

    move-result-object v2

    invoke-virtual {v2}, Landroid/support/v4/app/FragmentTransaction;->commit()I

    .line 228
    :cond_0
    invoke-virtual {p1}, Landroid/support/v4/app/FragmentActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v2

    invoke-virtual {p0, v2, v0}, Lcom/afollestad/materialdialogs/folderselector/FileChooserDialog;->show(Landroid/support/v4/app/FragmentManager;Ljava/lang/String;)V

    .line 229
    return-void
.end method
