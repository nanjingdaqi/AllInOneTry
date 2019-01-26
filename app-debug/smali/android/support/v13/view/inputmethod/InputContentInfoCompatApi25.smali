.class final Landroid/support/v13/view/inputmethod/InputContentInfoCompatApi25;
.super Ljava/lang/Object;
.source "InputContentInfoCompatApi25.java"


# annotations
.annotation build Landroid/annotation/TargetApi;
    value = 0x19
.end annotation

.annotation build Landroid/support/annotation/RequiresApi;
    value = 0x19
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static create(Landroid/net/Uri;Landroid/content/ClipDescription;Landroid/net/Uri;)Ljava/lang/Object;
    .locals 1
    .param p0, "contentUri"    # Landroid/net/Uri;
    .param p1, "description"    # Landroid/content/ClipDescription;
    .param p2, "linkUri"    # Landroid/net/Uri;

    .line 30
    new-instance v0, Landroid/view/inputmethod/InputContentInfo;

    invoke-direct {v0, p0, p1, p2}, Landroid/view/inputmethod/InputContentInfo;-><init>(Landroid/net/Uri;Landroid/content/ClipDescription;Landroid/net/Uri;)V

    return-object v0
.end method

.method public static getContentUri(Ljava/lang/Object;)Landroid/net/Uri;
    .locals 1
    .param p0, "inputContentInfo"    # Ljava/lang/Object;

    .line 34
    move-object v0, p0

    check-cast v0, Landroid/view/inputmethod/InputContentInfo;

    invoke-virtual {v0}, Landroid/view/inputmethod/InputContentInfo;->getContentUri()Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public static getDescription(Ljava/lang/Object;)Landroid/content/ClipDescription;
    .locals 1
    .param p0, "inputContentInfo"    # Ljava/lang/Object;

    .line 38
    move-object v0, p0

    check-cast v0, Landroid/view/inputmethod/InputContentInfo;

    invoke-virtual {v0}, Landroid/view/inputmethod/InputContentInfo;->getDescription()Landroid/content/ClipDescription;

    move-result-object v0

    return-object v0
.end method

.method public static getLinkUri(Ljava/lang/Object;)Landroid/net/Uri;
    .locals 1
    .param p0, "inputContentInfo"    # Ljava/lang/Object;

    .line 42
    move-object v0, p0

    check-cast v0, Landroid/view/inputmethod/InputContentInfo;

    invoke-virtual {v0}, Landroid/view/inputmethod/InputContentInfo;->getLinkUri()Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public static releasePermission(Ljava/lang/Object;)V
    .locals 1
    .param p0, "inputContentInfo"    # Ljava/lang/Object;

    .line 50
    move-object v0, p0

    check-cast v0, Landroid/view/inputmethod/InputContentInfo;

    invoke-virtual {v0}, Landroid/view/inputmethod/InputContentInfo;->releasePermission()V

    .line 51
    return-void
.end method

.method public static requestPermission(Ljava/lang/Object;)V
    .locals 1
    .param p0, "inputContentInfo"    # Ljava/lang/Object;

    .line 46
    move-object v0, p0

    check-cast v0, Landroid/view/inputmethod/InputContentInfo;

    invoke-virtual {v0}, Landroid/view/inputmethod/InputContentInfo;->requestPermission()V

    .line 47
    return-void
.end method
