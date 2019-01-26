.class Lcom/afollestad/materialdialogs/MaterialDialog$DialogException;
.super Landroid/view/WindowManager$BadTokenException;
.source "MaterialDialog.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/MaterialDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "DialogException"
.end annotation


# direct methods
.method constructor <init>(Ljava/lang/String;)V
    .locals 0
    .param p1, "message"    # Ljava/lang/String;

    .line 261
    invoke-direct {p0, p1}, Landroid/view/WindowManager$BadTokenException;-><init>(Ljava/lang/String;)V

    .line 262
    return-void
.end method
