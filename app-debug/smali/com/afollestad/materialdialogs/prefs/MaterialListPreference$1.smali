.class Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$1;
.super Ljava/lang/Object;
.source "MaterialListPreference.java"

# interfaces
.implements Lcom/afollestad/materialdialogs/MaterialDialog$ListCallbackSingleChoice;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;->showDialog(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;)V
    .locals 0
    .param p1, "this$0"    # Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;

    .line 104
    iput-object p1, p0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$1;->this$0:Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onSelection(Lcom/afollestad/materialdialogs/MaterialDialog;Landroid/view/View;ILjava/lang/CharSequence;)Z
    .locals 4
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;
    .param p2, "itemView"    # Landroid/view/View;
    .param p3, "which"    # I
    .param p4, "text"    # Ljava/lang/CharSequence;

    .line 107
    iget-object v0, p0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$1;->this$0:Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;

    const/4 v1, 0x0

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;->onClick(Landroid/content/DialogInterface;I)V

    .line 108
    const/4 v0, 0x1

    if-ltz p3, :cond_0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$1;->this$0:Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;->getEntryValues()[Ljava/lang/CharSequence;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 110
    :try_start_0
    const-class v1, Landroid/preference/ListPreference;

    const-string v2, "mClickedDialogEntryIndex"

    invoke-virtual {v1, v2}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    .line 111
    .local v1, "clickedIndex":Ljava/lang/reflect/Field;
    invoke-virtual {v1, v0}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 112
    iget-object v2, p0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$1;->this$0:Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 115
    .end local v1    # "clickedIndex":Ljava/lang/reflect/Field;
    goto :goto_0

    .line 113
    :catch_0
    move-exception v1

    .line 114
    .local v1, "e":Ljava/lang/Exception;
    invoke-virtual {v1}, Ljava/lang/Exception;->printStackTrace()V

    .line 117
    .end local v1    # "e":Ljava/lang/Exception;
    :cond_0
    :goto_0
    return v0
.end method
