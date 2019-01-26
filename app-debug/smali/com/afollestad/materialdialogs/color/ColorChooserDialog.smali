.class public Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
.super Landroid/support/v4/app/DialogFragment;
.source "ColorChooserDialog.java"

# interfaces
.implements Landroid/view/View$OnClickListener;
.implements Landroid/view/View$OnLongClickListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;,
        Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;,
        Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;,
        Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorChooserTag;
    }
.end annotation


# static fields
.field public static final TAG_ACCENT:Ljava/lang/String; = "[MD_COLOR_CHOOSER]"

.field public static final TAG_CUSTOM:Ljava/lang/String; = "[MD_COLOR_CHOOSER]"

.field public static final TAG_PRIMARY:Ljava/lang/String; = "[MD_COLOR_CHOOSER]"


# instance fields
.field private callback:Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;

.field private circleSize:I

.field private colorChooserCustomFrame:Landroid/view/View;

.field private customColorHex:Landroid/widget/EditText;

.field private customColorIndicator:Landroid/view/View;

.field private customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

.field private customColorTextWatcher:Landroid/text/TextWatcher;

.field private customSeekA:Landroid/widget/SeekBar;

.field private customSeekAValue:Landroid/widget/TextView;

.field private customSeekB:Landroid/widget/SeekBar;

.field private customSeekBValue:Landroid/widget/TextView;

.field private customSeekG:Landroid/widget/SeekBar;

.field private customSeekGValue:Landroid/widget/TextView;

.field private customSeekR:Landroid/widget/SeekBar;

.field private customSeekRValue:Landroid/widget/TextView;

.field private grid:Landroid/widget/GridView;

.field private mColorsSub:[[I
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation
.end field

.field private mColorsTop:[I
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation
.end field

.field private selectedCustomColor:I


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 84
    invoke-direct {p0}, Landroid/support/v4/app/DialogFragment;-><init>()V

    .line 85
    return-void
.end method

.method static synthetic access$000(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->invalidateDynamicButtonColors()V

    return-void
.end method

.method static synthetic access$100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;Lcom/afollestad/materialdialogs/MaterialDialog;)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .param p1, "x1"    # Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 47
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->toggleCustom(Lcom/afollestad/materialdialogs/MaterialDialog;)V

    return-void
.end method

.method static synthetic access$1000(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/view/View;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorIndicator:Landroid/view/View;

    return-object v0
.end method

.method static synthetic access$1100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    return-object v0
.end method

.method static synthetic access$1200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekAValue:Landroid/widget/TextView;

    return-object v0
.end method

.method static synthetic access$1300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekR:Landroid/widget/SeekBar;

    return-object v0
.end method

.method static synthetic access$1400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekG:Landroid/widget/SeekBar;

    return-object v0
.end method

.method static synthetic access$1500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/SeekBar;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekB:Landroid/widget/SeekBar;

    return-object v0
.end method

.method static synthetic access$1600(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .param p1, "x1"    # I

    .line 47
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex(I)V

    return-void
.end method

.method static synthetic access$1700(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/EditText;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    return-object v0
.end method

.method static synthetic access$1800(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekRValue:Landroid/widget/TextView;

    return-object v0
.end method

.method static synthetic access$1900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekGValue:Landroid/widget/TextView;

    return-object v0
.end method

.method static synthetic access$200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Z
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub()Z

    move-result v0

    return v0
.end method

.method static synthetic access$2000(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Landroid/widget/TextView;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekBValue:Landroid/widget/TextView;

    return-object v0
.end method

.method static synthetic access$2100(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[[I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    return-object v0
.end method

.method static synthetic access$2200(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex()I

    move-result v0

    return v0
.end method

.method static synthetic access$2300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)[I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    return-object v0
.end method

.method static synthetic access$2400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->circleSize:I

    return v0
.end method

.method static synthetic access$2500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex()I

    move-result v0

    return v0
.end method

.method static synthetic access$300(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$400(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;Z)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .param p1, "x1"    # Z

    .line 47
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub(Z)V

    return-void
.end method

.method static synthetic access$500(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .param p1, "x1"    # I

    .line 47
    invoke-direct {p0, p1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex(I)V

    return-void
.end method

.method static synthetic access$600(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->invalidate()V

    return-void
.end method

.method static synthetic access$700(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getSelectedColor()I

    move-result v0

    return v0
.end method

.method static synthetic access$800(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->callback:Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;

    return-object v0
.end method

.method static synthetic access$900(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)I
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 47
    iget v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    return v0
.end method

.method static synthetic access$902(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;I)I
    .locals 0
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .param p1, "x1"    # I

    .line 47
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    return p1
.end method

.method private dismissIfNecessary(Landroid/support/v7/app/AppCompatActivity;Ljava/lang/String;)V
    .locals 2
    .param p1, "context"    # Landroid/support/v7/app/AppCompatActivity;
    .param p2, "tag"    # Ljava/lang/String;

    .line 696
    invoke-virtual {p1}, Landroid/support/v7/app/AppCompatActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0, p2}, Landroid/support/v4/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/support/v4/app/Fragment;

    move-result-object v0

    .line 697
    .local v0, "frag":Landroid/support/v4/app/Fragment;
    if-eqz v0, :cond_0

    .line 698
    move-object v1, v0

    check-cast v1, Landroid/support/v4/app/DialogFragment;

    invoke-virtual {v1}, Landroid/support/v4/app/DialogFragment;->dismiss()V

    .line 699
    invoke-virtual {p1}, Landroid/support/v7/app/AppCompatActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v1

    invoke-virtual {v1}, Landroid/support/v4/app/FragmentManager;->beginTransaction()Landroid/support/v4/app/FragmentTransaction;

    move-result-object v1

    .line 700
    invoke-virtual {v1, v0}, Landroid/support/v4/app/FragmentTransaction;->remove(Landroid/support/v4/app/Fragment;)Landroid/support/v4/app/FragmentTransaction;

    move-result-object v1

    invoke-virtual {v1}, Landroid/support/v4/app/FragmentTransaction;->commit()I

    .line 702
    :cond_0
    return-void
.end method

.method private findSubIndexForColor(II)V
    .locals 3
    .param p1, "topIndex"    # I
    .param p2, "color"    # I

    .line 266
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    array-length v0, v0

    add-int/lit8 v0, v0, -0x1

    if-ge v0, p1, :cond_0

    goto :goto_2

    .line 268
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    aget-object v0, v0, p1

    .line 269
    .local v0, "subColors":[I
    const/4 v1, 0x0

    .local v1, "subIndex":I
    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_2

    .line 270
    aget v2, v0, v1

    if-ne v2, p2, :cond_1

    .line 271
    invoke-direct {p0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex(I)V

    .line 272
    goto :goto_1

    .line 269
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 275
    .end local v1    # "subIndex":I
    :cond_2
    :goto_1
    return-void

    .line 267
    .end local v0    # "subColors":[I
    :cond_3
    :goto_2
    return-void
.end method

.method public static findVisible(Landroid/support/v7/app/AppCompatActivity;Ljava/lang/String;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .locals 2
    .param p0, "context"    # Landroid/support/v7/app/AppCompatActivity;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p1, "tag"    # Ljava/lang/String;
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation

    .line 706
    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/support/v4/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/support/v4/app/Fragment;

    move-result-object v0

    .line 707
    .local v0, "frag":Landroid/support/v4/app/Fragment;
    if-eqz v0, :cond_0

    instance-of v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    if-eqz v1, :cond_0

    .line 708
    move-object v1, v0

    check-cast v1, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    return-object v1

    .line 709
    :cond_0
    const/4 v1, 0x0

    return-object v1
.end method

.method private generateColors()V
    .locals 2

    .line 68
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    .line 69
    .local v0, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    iget-object v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsTop:[I

    if-eqz v1, :cond_0

    .line 70
    iget-object v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsTop:[I

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    .line 71
    iget-object v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsSub:[[I

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    .line 72
    return-void

    .line 75
    :cond_0
    iget-boolean v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAccentMode:Z

    if-eqz v1, :cond_1

    .line 76
    sget-object v1, Lcom/afollestad/materialdialogs/color/ColorPalette;->ACCENT_COLORS:[I

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    .line 77
    sget-object v1, Lcom/afollestad/materialdialogs/color/ColorPalette;->ACCENT_COLORS_SUB:[[I

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    goto :goto_0

    .line 79
    :cond_1
    sget-object v1, Lcom/afollestad/materialdialogs/color/ColorPalette;->PRIMARY_COLORS:[I

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    .line 80
    sget-object v1, Lcom/afollestad/materialdialogs/color/ColorPalette;->PRIMARY_COLORS_SUB:[[I

    iput-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    .line 82
    :goto_0
    return-void
.end method

.method private getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 2

    .line 691
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "builder"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 692
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "builder"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v0

    check-cast v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    return-object v0

    .line 691
    :cond_1
    :goto_0
    const/4 v0, 0x0

    return-object v0
.end method

.method private getSelectedColor()I
    .locals 4
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation

    .line 239
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    .line 240
    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 241
    iget v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    return v0

    .line 244
    :cond_0
    const/4 v0, 0x0

    .line 245
    .local v0, "color":I
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex()I

    move-result v1

    const/4 v2, -0x1

    if-le v1, v2, :cond_1

    .line 246
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex()I

    move-result v2

    aget-object v1, v1, v2

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex()I

    move-result v2

    aget v0, v1, v2

    goto :goto_0

    .line 247
    :cond_1
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex()I

    move-result v1

    if-le v1, v2, :cond_2

    .line 248
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex()I

    move-result v2

    aget v0, v1, v2

    .line 249
    :cond_2
    :goto_0
    if-nez v0, :cond_4

    .line 250
    const/4 v1, 0x0

    .line 251
    .local v1, "fallback":I
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x15

    if-lt v2, v3, :cond_3

    .line 252
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v2

    const v3, 0x1010435

    invoke-static {v2, v3}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;I)I

    move-result v1

    .line 253
    :cond_3
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v2

    sget v3, Lcom/afollestad/materialdialogs/commons/R$attr;->colorAccent:I

    invoke-static {v2, v3, v1}, Lcom/afollestad/materialdialogs/util/DialogUtils;->resolveColor(Landroid/content/Context;II)I

    move-result v0

    .line 255
    .end local v1    # "fallback":I
    :cond_4
    return v0
.end method

.method private invalidate()V
    .locals 4

    .line 521
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    invoke-virtual {v0}, Landroid/widget/GridView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    if-nez v0, :cond_0

    .line 522
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    new-instance v1, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;

    invoke-direct {v1, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorGridAdapter;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    invoke-virtual {v0, v1}, Landroid/widget/GridView;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 523
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/afollestad/materialdialogs/commons/R$drawable;->md_transparent:I

    const/4 v3, 0x0

    invoke-static {v1, v2, v3}, Landroid/support/v4/content/res/ResourcesCompat;->getDrawable(Landroid/content/res/Resources;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/GridView;->setSelector(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 524
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    invoke-virtual {v0}, Landroid/widget/GridView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    check-cast v0, Landroid/widget/BaseAdapter;

    invoke-virtual {v0}, Landroid/widget/BaseAdapter;->notifyDataSetChanged()V

    .line 525
    :goto_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 526
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getTitle()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/app/Dialog;->setTitle(I)V

    .line 527
    :cond_1
    return-void
.end method

.method private invalidateDynamicButtonColors()V
    .locals 5

    .line 209
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    check-cast v0, Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 210
    .local v0, "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    if-nez v0, :cond_0

    return-void

    .line 211
    :cond_0
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v1

    .line 212
    .local v1, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    iget-boolean v2, v1, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDynamicButtonColor:Z

    if-eqz v2, :cond_5

    .line 213
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getSelectedColor()I

    move-result v2

    .line 214
    .local v2, "selectedColor":I
    invoke-static {v2}, Landroid/graphics/Color;->alpha(I)I

    move-result v3

    const/16 v4, 0x40

    if-lt v3, v4, :cond_1

    .line 215
    invoke-static {v2}, Landroid/graphics/Color;->red(I)I

    move-result v3

    const/16 v4, 0xf7

    if-le v3, v4, :cond_2

    .line 216
    invoke-static {v2}, Landroid/graphics/Color;->green(I)I

    move-result v3

    if-le v3, v4, :cond_2

    .line 217
    invoke-static {v2}, Landroid/graphics/Color;->blue(I)I

    move-result v3

    if-le v3, v4, :cond_2

    .line 219
    :cond_1
    const-string v3, "#DEDEDE"

    invoke-static {v3}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    .line 222
    :cond_2
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v3

    iget-boolean v3, v3, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDynamicButtonColor:Z

    if-eqz v3, :cond_3

    .line 223
    sget-object v3, Lcom/afollestad/materialdialogs/DialogAction;->POSITIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-virtual {v0, v3}, Lcom/afollestad/materialdialogs/MaterialDialog;->getActionButton(Lcom/afollestad/materialdialogs/DialogAction;)Lcom/afollestad/materialdialogs/internal/MDButton;

    move-result-object v3

    invoke-virtual {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDButton;->setTextColor(I)V

    .line 224
    sget-object v3, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-virtual {v0, v3}, Lcom/afollestad/materialdialogs/MaterialDialog;->getActionButton(Lcom/afollestad/materialdialogs/DialogAction;)Lcom/afollestad/materialdialogs/internal/MDButton;

    move-result-object v3

    invoke-virtual {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDButton;->setTextColor(I)V

    .line 225
    sget-object v3, Lcom/afollestad/materialdialogs/DialogAction;->NEUTRAL:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-virtual {v0, v3}, Lcom/afollestad/materialdialogs/MaterialDialog;->getActionButton(Lcom/afollestad/materialdialogs/DialogAction;)Lcom/afollestad/materialdialogs/internal/MDButton;

    move-result-object v3

    invoke-virtual {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDButton;->setTextColor(I)V

    .line 228
    :cond_3
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekR:Landroid/widget/SeekBar;

    if-eqz v3, :cond_5

    .line 229
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    invoke-virtual {v3}, Landroid/widget/SeekBar;->getVisibility()I

    move-result v3

    if-nez v3, :cond_4

    .line 230
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    invoke-static {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/SeekBar;I)V

    .line 231
    :cond_4
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekR:Landroid/widget/SeekBar;

    invoke-static {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/SeekBar;I)V

    .line 232
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekG:Landroid/widget/SeekBar;

    invoke-static {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/SeekBar;I)V

    .line 233
    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekB:Landroid/widget/SeekBar;

    invoke-static {v3, v2}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/SeekBar;I)V

    .line 236
    .end local v2    # "selectedColor":I
    :cond_5
    return-void
.end method

.method private isInSub(Z)V
    .locals 2
    .param p1, "value"    # Z

    .line 129
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "in_sub"

    invoke-virtual {v0, v1, p1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    .line 130
    return-void
.end method

.method private isInSub()Z
    .locals 3

    .line 125
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "in_sub"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method private subIndex()I
    .locals 3

    .line 143
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    const/4 v1, -0x1

    if-nez v0, :cond_0

    return v1

    .line 144
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v2, "sub_index"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method private subIndex(I)V
    .locals 2
    .param p1, "value"    # I

    .line 148
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    if-nez v0, :cond_0

    return-void

    .line 149
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "sub_index"

    invoke-virtual {v0, v1, p1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 150
    return-void
.end method

.method private toggleCustom(Lcom/afollestad/materialdialogs/MaterialDialog;)V
    .locals 6
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 413
    if-nez p1, :cond_0

    .line 414
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getDialog()Landroid/app/Dialog;

    move-result-object v0

    move-object p1, v0

    check-cast p1, Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 415
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    invoke-virtual {v0}, Landroid/widget/GridView;->getVisibility()I

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_2

    .line 416
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    iget v0, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCustomBtn:I

    invoke-virtual {p1, v0}, Lcom/afollestad/materialdialogs/MaterialDialog;->setTitle(I)V

    .line 417
    sget-object v0, Lcom/afollestad/materialdialogs/DialogAction;->NEUTRAL:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v2

    iget v2, v2, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mPresetsBtn:I

    invoke-virtual {p1, v0, v2}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    .line 418
    sget-object v0, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v2

    iget v2, v2, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCancelBtn:I

    invoke-virtual {p1, v0, v2}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    .line 419
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    const/4 v2, 0x4

    invoke-virtual {v0, v2}, Landroid/widget/GridView;->setVisibility(I)V

    .line 420
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 422
    new-instance v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;

    invoke-direct {v0, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$5;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    iput-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorTextWatcher:Landroid/text/TextWatcher;

    .line 461
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorTextWatcher:Landroid/text/TextWatcher;

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->addTextChangedListener(Landroid/text/TextWatcher;)V

    .line 462
    new-instance v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;

    invoke-direct {v0, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$6;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    iput-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    .line 494
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekR:Landroid/widget/SeekBar;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    invoke-virtual {v0, v2}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 495
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekG:Landroid/widget/SeekBar;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    invoke-virtual {v0, v2}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 496
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekB:Landroid/widget/SeekBar;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    invoke-virtual {v0, v2}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 497
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    invoke-virtual {v0}, Landroid/widget/SeekBar;->getVisibility()I

    move-result v0

    const/4 v2, 0x1

    if-nez v0, :cond_1

    .line 498
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    iget-object v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    invoke-virtual {v0, v3}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 499
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    const-string v3, "%08X"

    new-array v2, v2, [Ljava/lang/Object;

    iget v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v2, v1

    invoke-static {v3, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    goto :goto_1

    .line 501
    :cond_1
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    const-string v3, "%06X"

    new-array v2, v2, [Ljava/lang/Object;

    const v4, 0xffffff

    iget v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    and-int/2addr v4, v5

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v2, v1

    invoke-static {v3, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    goto :goto_1

    .line 504
    :cond_2
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    iget v0, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTitle:I

    invoke-virtual {p1, v0}, Lcom/afollestad/materialdialogs/MaterialDialog;->setTitle(I)V

    .line 505
    sget-object v0, Lcom/afollestad/materialdialogs/DialogAction;->NEUTRAL:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v2

    iget v2, v2, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCustomBtn:I

    invoke-virtual {p1, v0, v2}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    .line 506
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 507
    sget-object v0, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v2

    iget v2, v2, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mBackBtn:I

    invoke-virtual {p1, v0, v2}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    goto :goto_0

    .line 508
    :cond_3
    sget-object v0, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v2

    iget v2, v2, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCancelBtn:I

    invoke-virtual {p1, v0, v2}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    .line 509
    :goto_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    invoke-virtual {v0, v1}, Landroid/widget/GridView;->setVisibility(I)V

    .line 510
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 511
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorTextWatcher:Landroid/text/TextWatcher;

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->removeTextChangedListener(Landroid/text/TextWatcher;)V

    .line 512
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorTextWatcher:Landroid/text/TextWatcher;

    .line 513
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekR:Landroid/widget/SeekBar;

    invoke-virtual {v1, v0}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 514
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekG:Landroid/widget/SeekBar;

    invoke-virtual {v1, v0}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 515
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekB:Landroid/widget/SeekBar;

    invoke-virtual {v1, v0}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 516
    iput-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorRgbListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    .line 518
    :goto_1
    return-void
.end method

.method private topIndex()I
    .locals 3

    .line 133
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "top_index"

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method private topIndex(I)V
    .locals 2
    .param p1, "value"    # I

    .line 137
    const/4 v0, -0x1

    if-le p1, v0, :cond_0

    .line 138
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    aget v0, v0, p1

    invoke-direct {p0, p1, v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->findSubIndexForColor(II)V

    .line 139
    :cond_0
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "top_index"

    invoke-virtual {v0, v1, p1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 140
    return-void
.end method


# virtual methods
.method public getTitle()I
    .locals 2
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation

    .line 154
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    .line 156
    .local v0, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub()Z

    move-result v1

    if-eqz v1, :cond_0

    iget v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTitleSub:I

    goto :goto_0

    .line 157
    :cond_0
    iget v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTitle:I

    .line 158
    .local v1, "title":I
    :goto_0
    if-nez v1, :cond_1

    iget v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTitle:I

    .line 159
    :cond_1
    return v1
.end method

.method public isAccentMode()Z
    .locals 1

    .line 170
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    iget-boolean v0, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAccentMode:Z

    return v0
.end method

.method public onAttach(Landroid/app/Activity;)V
    .locals 2
    .param p1, "activity"    # Landroid/app/Activity;

    .line 118
    invoke-super {p0, p1}, Landroid/support/v4/app/DialogFragment;->onAttach(Landroid/app/Activity;)V

    .line 119
    instance-of v0, p1, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;

    if-eqz v0, :cond_0

    .line 121
    move-object v0, p1

    check-cast v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->callback:Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;

    .line 122
    return-void

    .line 120
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "ColorChooserDialog needs to be shown from an Activity implementing ColorCallback."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public onClick(Landroid/view/View;)V
    .locals 6
    .param p1, "v"    # Landroid/view/View;

    .line 175
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_3

    .line 176
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .line 177
    .local v0, "tag":[Ljava/lang/String;
    const/4 v1, 0x0

    aget-object v1, v0, v1

    invoke-static {v1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v1

    .line 178
    .local v1, "index":I
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getDialog()Landroid/app/Dialog;

    move-result-object v2

    check-cast v2, Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 179
    .local v2, "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v3

    .line 181
    .local v3, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub()Z

    move-result v4

    if-eqz v4, :cond_0

    .line 182
    invoke-direct {p0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex(I)V

    goto :goto_0

    .line 184
    :cond_0
    invoke-direct {p0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex(I)V

    .line 185
    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    if-eqz v4, :cond_1

    iget-object v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    array-length v4, v4

    if-ge v1, v4, :cond_1

    .line 186
    sget-object v4, Lcom/afollestad/materialdialogs/DialogAction;->NEGATIVE:Lcom/afollestad/materialdialogs/DialogAction;

    iget v5, v3, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mBackBtn:I

    invoke-virtual {v2, v4, v5}, Lcom/afollestad/materialdialogs/MaterialDialog;->setActionButton(Lcom/afollestad/materialdialogs/DialogAction;I)V

    .line 187
    const/4 v4, 0x1

    invoke-direct {p0, v4}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub(Z)V

    .line 191
    :cond_1
    :goto_0
    iget-boolean v4, v3, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustom:Z

    if-eqz v4, :cond_2

    .line 192
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getSelectedColor()I

    move-result v4

    iput v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    .line 193
    :cond_2
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->invalidateDynamicButtonColors()V

    .line 194
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->invalidate()V

    .line 196
    .end local v0    # "tag":[Ljava/lang/String;
    .end local v1    # "index":I
    .end local v2    # "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    .end local v3    # "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    :cond_3
    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 11
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 280
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    if-eqz v0, :cond_e

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "builder"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_e

    .line 282
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->generateColors()V

    .line 285
    const/4 v0, 0x0

    .line 287
    .local v0, "foundPreselectColor":Z
    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz p1, :cond_0

    .line 288
    const-string v3, "in_custom"

    invoke-virtual {p1, v3, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v3

    xor-int/2addr v3, v1

    move v0, v3

    .line 289
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getSelectedColor()I

    move-result v3

    goto/16 :goto_4

    .line 291
    :cond_0
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v3

    iget-boolean v3, v3, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mSetPreselectionColor:Z

    if-eqz v3, :cond_8

    .line 292
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v3

    iget v3, v3, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mPreselect:I

    .line 293
    .local v3, "preselectColor":I
    if-eqz v3, :cond_9

    .line 294
    move v4, v0

    const/4 v0, 0x0

    .local v0, "topIndex":I
    .local v4, "foundPreselectColor":Z
    :goto_0
    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    array-length v5, v5

    if-ge v0, v5, :cond_7

    .line 295
    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsTop:[I

    aget v5, v5, v0

    if-ne v5, v3, :cond_3

    .line 296
    const/4 v4, 0x1

    .line 297
    invoke-direct {p0, v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex(I)V

    .line 298
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v5

    iget-boolean v5, v5, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAccentMode:Z

    if-eqz v5, :cond_1

    .line 299
    const/4 v5, 0x2

    invoke-direct {p0, v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex(I)V

    goto :goto_3

    .line 300
    :cond_1
    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    if-eqz v5, :cond_2

    .line 301
    invoke-direct {p0, v0, v3}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->findSubIndexForColor(II)V

    goto :goto_3

    .line 303
    :cond_2
    const/4 v5, 0x5

    invoke-direct {p0, v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex(I)V

    .line 305
    goto :goto_3

    .line 308
    :cond_3
    iget-object v5, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    if-eqz v5, :cond_6

    .line 309
    const/4 v5, 0x0

    .local v5, "subIndex":I
    :goto_1
    iget-object v6, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    aget-object v6, v6, v0

    array-length v6, v6

    if-ge v5, v6, :cond_5

    .line 310
    iget-object v6, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->mColorsSub:[[I

    aget-object v6, v6, v0

    aget v6, v6, v5

    if-ne v6, v3, :cond_4

    .line 311
    const/4 v4, 0x1

    .line 312
    invoke-direct {p0, v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex(I)V

    .line 313
    invoke-direct {p0, v5}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex(I)V

    .line 314
    goto :goto_2

    .line 309
    :cond_4
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    .line 317
    .end local v5    # "subIndex":I
    :cond_5
    :goto_2
    if-eqz v4, :cond_6

    .end local v0    # "topIndex":I
    goto :goto_3

    .line 294
    .restart local v0    # "topIndex":I
    :cond_6
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 327
    .end local v0    # "topIndex":I
    :cond_7
    :goto_3
    move v0, v4

    goto :goto_4

    .line 322
    .end local v3    # "preselectColor":I
    .end local v4    # "foundPreselectColor":Z
    .local v0, "foundPreselectColor":Z
    :cond_8
    const/high16 v3, -0x1000000

    .line 323
    .restart local v3    # "preselectColor":I
    const/4 v0, 0x1

    .line 327
    :cond_9
    :goto_4
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/afollestad/materialdialogs/commons/R$dimen;->md_colorchooser_circlesize:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    iput v4, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->circleSize:I

    .line 328
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v4

    .line 330
    .local v4, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    new-instance v5, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v6

    invoke-direct {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 331
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getTitle()I

    move-result v6

    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->title(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    .line 332
    invoke-virtual {v5, v2}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->autoDismiss(Z)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    sget v6, Lcom/afollestad/materialdialogs/commons/R$layout;->md_dialog_colorchooser:I

    .line 333
    invoke-virtual {v5, v6, v2}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->customView(IZ)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    iget v6, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCancelBtn:I

    .line 334
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->negativeText(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    iget v6, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDoneBtn:I

    .line 335
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->positiveText(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    iget-boolean v6, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustom:Z

    if-eqz v6, :cond_a

    iget v6, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCustomBtn:I

    goto :goto_5

    .line 336
    :cond_a
    const/4 v6, 0x0

    :goto_5
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->neutralText(I)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    new-instance v6, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;

    invoke-direct {v6, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$4;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    .line 337
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->onPositive(Lcom/afollestad/materialdialogs/MaterialDialog$SingleButtonCallback;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    new-instance v6, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;

    invoke-direct {v6, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$3;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    .line 344
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->onNegative(Lcom/afollestad/materialdialogs/MaterialDialog$SingleButtonCallback;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    new-instance v6, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$2;

    invoke-direct {v6, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$2;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    .line 357
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->onNeutral(Lcom/afollestad/materialdialogs/MaterialDialog$SingleButtonCallback;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    new-instance v6, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$1;

    invoke-direct {v6, p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$1;-><init>(Lcom/afollestad/materialdialogs/color/ColorChooserDialog;)V

    .line 363
    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->showListener(Landroid/content/DialogInterface$OnShowListener;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v5

    .line 370
    .local v5, "bd":Lcom/afollestad/materialdialogs/MaterialDialog$Builder;
    iget-object v6, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTheme:Lcom/afollestad/materialdialogs/Theme;

    if-eqz v6, :cond_b

    .line 371
    iget-object v6, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTheme:Lcom/afollestad/materialdialogs/Theme;

    invoke-virtual {v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->theme(Lcom/afollestad/materialdialogs/Theme;)Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    .line 373
    :cond_b
    invoke-virtual {v5}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->build()Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v6

    .line 374
    .local v6, "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    invoke-virtual {v6}, Lcom/afollestad/materialdialogs/MaterialDialog;->getCustomView()Landroid/view/View;

    move-result-object v7

    .line 375
    .local v7, "v":Landroid/view/View;
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_grid:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/GridView;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->grid:Landroid/widget/GridView;

    .line 377
    iget-boolean v8, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustom:Z

    if-eqz v8, :cond_d

    .line 378
    iput v3, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->selectedCustomColor:I

    .line 379
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorChooserCustomFrame:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    .line 380
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_hexInput:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/EditText;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    .line 381
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorIndicator:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorIndicator:Landroid/view/View;

    .line 382
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorA:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/SeekBar;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    .line 383
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorAValue:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/TextView;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekAValue:Landroid/widget/TextView;

    .line 384
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorR:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/SeekBar;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekR:Landroid/widget/SeekBar;

    .line 385
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorRValue:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/TextView;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekRValue:Landroid/widget/TextView;

    .line 386
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorG:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/SeekBar;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekG:Landroid/widget/SeekBar;

    .line 387
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorGValue:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/TextView;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekGValue:Landroid/widget/TextView;

    .line 388
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorB:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/SeekBar;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekB:Landroid/widget/SeekBar;

    .line 389
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorBValue:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/widget/TextView;

    iput-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekBValue:Landroid/widget/TextView;

    .line 391
    iget-boolean v8, v4, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustomAlpha:Z

    const/16 v9, 0x8

    if-nez v8, :cond_c

    .line 392
    sget v8, Lcom/afollestad/materialdialogs/commons/R$id;->md_colorALabel:I

    invoke-virtual {v7, v8}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v8

    invoke-virtual {v8, v9}, Landroid/view/View;->setVisibility(I)V

    .line 393
    iget-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekA:Landroid/widget/SeekBar;

    invoke-virtual {v8, v9}, Landroid/widget/SeekBar;->setVisibility(I)V

    .line 394
    iget-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customSeekAValue:Landroid/widget/TextView;

    invoke-virtual {v8, v9}, Landroid/widget/TextView;->setVisibility(I)V

    .line 395
    iget-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    const-string v9, "2196F3"

    invoke-virtual {v8, v9}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 396
    iget-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    new-array v1, v1, [Landroid/text/InputFilter;

    new-instance v9, Landroid/text/InputFilter$LengthFilter;

    const/4 v10, 0x6

    invoke-direct {v9, v10}, Landroid/text/InputFilter$LengthFilter;-><init>(I)V

    aput-object v9, v1, v2

    invoke-virtual {v8, v1}, Landroid/widget/EditText;->setFilters([Landroid/text/InputFilter;)V

    goto :goto_6

    .line 398
    :cond_c
    iget-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    const-string v10, "FF2196F3"

    invoke-virtual {v8, v10}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 399
    iget-object v8, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->customColorHex:Landroid/widget/EditText;

    new-array v1, v1, [Landroid/text/InputFilter;

    new-instance v10, Landroid/text/InputFilter$LengthFilter;

    invoke-direct {v10, v9}, Landroid/text/InputFilter$LengthFilter;-><init>(I)V

    aput-object v10, v1, v2

    invoke-virtual {v8, v1}, Landroid/widget/EditText;->setFilters([Landroid/text/InputFilter;)V

    .line 402
    :goto_6
    if-nez v0, :cond_d

    .line 404
    invoke-direct {p0, v6}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->toggleCustom(Lcom/afollestad/materialdialogs/MaterialDialog;)V

    .line 408
    :cond_d
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->invalidate()V

    .line 409
    return-object v6

    .line 281
    .end local v0    # "foundPreselectColor":Z
    .end local v3    # "preselectColor":I
    .end local v4    # "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .end local v5    # "bd":Lcom/afollestad/materialdialogs/MaterialDialog$Builder;
    .end local v6    # "dialog":Lcom/afollestad/materialdialogs/MaterialDialog;
    .end local v7    # "v":Landroid/view/View;
    :cond_e
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "ColorChooserDialog should be created using its Builder interface."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public onLongClick(Landroid/view/View;)Z
    .locals 4
    .param p1, "v"    # Landroid/view/View;

    .line 199
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 200
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .line 201
    .local v0, "tag":[Ljava/lang/String;
    const/4 v1, 0x1

    aget-object v2, v0, v1

    invoke-static {v2}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v2

    .line 202
    .local v2, "color":I
    move-object v3, p1

    check-cast v3, Lcom/afollestad/materialdialogs/color/CircleView;

    invoke-virtual {v3, v2}, Lcom/afollestad/materialdialogs/color/CircleView;->showHint(I)V

    .line 203
    return v1

    .line 205
    .end local v0    # "tag":[Ljava/lang/String;
    .end local v2    # "color":I
    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2
    .param p1, "outState"    # Landroid/os/Bundle;

    .line 108
    invoke-super {p0, p1}, Landroid/support/v4/app/DialogFragment;->onSaveInstanceState(Landroid/os/Bundle;)V

    .line 109
    const-string v0, "top_index"

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->topIndex()I

    move-result v1

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 110
    const-string v0, "in_sub"

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->isInSub()Z

    move-result v1

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    .line 111
    const-string v0, "sub_index"

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->subIndex()I

    move-result v1

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 112
    const-string v0, "in_custom"

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->colorChooserCustomFrame:Landroid/view/View;

    .line 113
    invoke-virtual {v1}, Landroid/view/View;->getVisibility()I

    move-result v1

    if-nez v1, :cond_0

    const/4 v1, 0x1

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    .line 112
    :goto_0
    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    .line 114
    return-void
.end method

.method public show(Landroid/support/v7/app/AppCompatActivity;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .locals 3
    .param p1, "context"    # Landroid/support/v7/app/AppCompatActivity;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 714
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    .line 715
    .local v0, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    iget-object v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsTop:[I

    if-eqz v1, :cond_0

    .line 716
    const-string v1, "[MD_COLOR_CHOOSER]"

    goto :goto_0

    .line 717
    :cond_0
    iget-boolean v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAccentMode:Z

    if-eqz v1, :cond_1

    .line 718
    const-string v1, "[MD_COLOR_CHOOSER]"

    goto :goto_0

    .line 719
    :cond_1
    const-string v1, "[MD_COLOR_CHOOSER]"

    .line 720
    .local v1, "tag":Ljava/lang/String;
    :goto_0
    invoke-direct {p0, p1, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->dismissIfNecessary(Landroid/support/v7/app/AppCompatActivity;Ljava/lang/String;)V

    .line 721
    invoke-virtual {p1}, Landroid/support/v7/app/AppCompatActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v2

    invoke-virtual {p0, v2, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->show(Landroid/support/v4/app/FragmentManager;Ljava/lang/String;)V

    .line 722
    return-object p0
.end method

.method public tag()Ljava/lang/String;
    .locals 2

    .line 163
    invoke-direct {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->getBuilder()Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;

    move-result-object v0

    .line 164
    .local v0, "builder":Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    iget-object v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTag:Ljava/lang/String;

    if-eqz v1, :cond_0

    .line 165
    iget-object v1, v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTag:Ljava/lang/String;

    return-object v1

    .line 166
    :cond_0
    invoke-super {p0}, Landroid/support/v4/app/DialogFragment;->getTag()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method
