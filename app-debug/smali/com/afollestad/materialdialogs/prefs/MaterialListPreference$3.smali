.class synthetic Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$3;
.super Ljava/lang/Object;
.source "MaterialListPreference.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/prefs/MaterialListPreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1008
    name = null
.end annotation


# static fields
.field static final synthetic $SwitchMap$com$afollestad$materialdialogs$DialogAction:[I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 88
    invoke-static {}, Lcom/afollestad/materialdialogs/DialogAction;->values()[Lcom/afollestad/materialdialogs/DialogAction;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [I

    sput-object v0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$3;->$SwitchMap$com$afollestad$materialdialogs$DialogAction:[I

    :try_start_0
    sget-object v0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$3;->$SwitchMap$com$afollestad$materialdialogs$DialogAction:[I

    sget-object v1, Lcom/afollestad/materialdialogs/DialogAction;->NEUTRAL:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/DialogAction;->ordinal()I

    move-result v1

    const/4 v2, 0x1

    aput v2, v0, v1
    :try_end_0
    .catch Ljava/lang/NoSuchFieldError; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    :goto_0
    :try_start_1
    sget-object v0, Lcom/afollestad/materialdialogs/prefs/MaterialListPreference$3;->$SwitchMap$com$afollestad$materialdialogs$DialogAction:[I

    sget-object v1, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/DialogAction;->ordinal()I

    move-result v1

    const/4 v2, 0x2

    aput v2, v0, v1
    :try_end_1
    .catch Ljava/lang/NoSuchFieldError; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_1
    move-exception v0

    :goto_1
    return-void
.end method