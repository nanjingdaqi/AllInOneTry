.class final Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState$1;
.super Ljava/lang/Object;
.source "MaterialEditTextPreference.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator<",
        "Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 239
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;)Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;
    .locals 1
    .param p1, "in"    # Landroid/os/Parcel;

    .line 241
    new-instance v0, Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;

    invoke-direct {v0, p1}, Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    .line 239
    invoke-virtual {p0, p1}, Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState$1;->createFromParcel(Landroid/os/Parcel;)Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;

    move-result-object p1

    return-object p1
.end method

.method public newArray(I)[Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;
    .locals 1
    .param p1, "size"    # I

    .line 245
    new-array v0, p1, [Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    .line 239
    invoke-virtual {p0, p1}, Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState$1;->newArray(I)[Lcom/afollestad/materialdialogs/prefs/MaterialEditTextPreference$SavedState;

    move-result-object p1

    return-object p1
.end method
