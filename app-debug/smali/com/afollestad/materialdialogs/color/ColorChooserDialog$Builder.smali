.class public Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
.super Ljava/lang/Object;
.source "ColorChooserDialog.java"

# interfaces
.implements Ljava/io/Serializable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field mAccentMode:Z

.field mAllowUserCustom:Z

.field mAllowUserCustomAlpha:Z

.field mBackBtn:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field mCancelBtn:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field mColorsSub:[[I
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation
.end field

.field mColorsTop:[I
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation
.end field

.field final transient mContext:Landroid/support/v7/app/AppCompatActivity;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation
.end field

.field mCustomBtn:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field mDoneBtn:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field mDynamicButtonColor:Z

.field mPreselect:I
    .annotation build Landroid/support/annotation/ColorInt;
    .end annotation
.end field

.field mPresetsBtn:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field mSetPreselectionColor:Z

.field mTag:Ljava/lang/String;
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation
.end field

.field mTheme:Lcom/afollestad/materialdialogs/Theme;
    .annotation build Landroid/support/annotation/Nullable;
    .end annotation
.end field

.field final mTitle:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field mTitleSub:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/support/v7/app/AppCompatActivity;I)V
    .locals 2
    .param p1    # Landroid/support/v7/app/AppCompatActivity;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "title"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<ActivityType:",
            "Landroid/support/v7/app/AppCompatActivity;",
            ":",
            "Lcom/afollestad/materialdialogs/color/ColorChooserDialog$ColorCallback;",
            ">(TActivityType;I)V"
        }
    .end annotation

    .line 592
    .local p1, "context":Landroid/support/v7/app/AppCompatActivity;, "TActivityType;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 575
    sget v0, Lcom/afollestad/materialdialogs/commons/R$string;->md_done_label:I

    iput v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDoneBtn:I

    .line 576
    sget v0, Lcom/afollestad/materialdialogs/commons/R$string;->md_back_label:I

    iput v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mBackBtn:I

    .line 577
    sget v0, Lcom/afollestad/materialdialogs/commons/R$string;->md_cancel_label:I

    iput v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCancelBtn:I

    .line 578
    sget v0, Lcom/afollestad/materialdialogs/commons/R$string;->md_custom_label:I

    iput v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCustomBtn:I

    .line 579
    sget v0, Lcom/afollestad/materialdialogs/commons/R$string;->md_presets_label:I

    iput v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mPresetsBtn:I

    .line 585
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAccentMode:Z

    .line 586
    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDynamicButtonColor:Z

    .line 587
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustom:Z

    .line 588
    iput-boolean v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustomAlpha:Z

    .line 589
    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mSetPreselectionColor:Z

    .line 593
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mContext:Landroid/support/v7/app/AppCompatActivity;

    .line 594
    iput p2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTitle:I

    .line 595
    return-void
.end method


# virtual methods
.method public accentMode(Z)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "accentMode"    # Z
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 619
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAccentMode:Z

    .line 620
    return-object p0
.end method

.method public allowUserColorInput(Z)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "allow"    # Z
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 666
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustom:Z

    .line 667
    return-object p0
.end method

.method public allowUserColorInputAlpha(Z)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "allow"    # Z
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 671
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mAllowUserCustomAlpha:Z

    .line 672
    return-object p0
.end method

.method public backButton(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 629
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mBackBtn:I

    .line 630
    return-object p0
.end method

.method public build()Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .locals 3
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 676
    new-instance v0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    invoke-direct {v0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;-><init>()V

    .line 677
    .local v0, "dialog":Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 678
    .local v1, "args":Landroid/os/Bundle;
    const-string v2, "builder"

    invoke-virtual {v1, v2, p0}, Landroid/os/Bundle;->putSerializable(Ljava/lang/String;Ljava/io/Serializable;)V

    .line 679
    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->setArguments(Landroid/os/Bundle;)V

    .line 680
    return-object v0
.end method

.method public cancelButton(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 634
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCancelBtn:I

    .line 635
    return-object p0
.end method

.method public customButton(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 639
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mCustomBtn:I

    .line 640
    return-object p0
.end method

.method public customColors(I[[I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 1
    .param p1, "topLevel"    # I
        .annotation build Landroid/support/annotation/ArrayRes;
        .end annotation
    .end param
    .param p2, "subLevel"    # [[I
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 660
    iget-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mContext:Landroid/support/v7/app/AppCompatActivity;

    invoke-static {v0, p1}, Lcom/afollestad/materialdialogs/util/DialogUtils;->getColorArray(Landroid/content/Context;I)[I

    move-result-object v0

    iput-object v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsTop:[I

    .line 661
    iput-object p2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsSub:[[I

    .line 662
    return-object p0
.end method

.method public customColors([I[[I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "topLevel"    # [I
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .param p2, "subLevel"    # [[I
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 654
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsTop:[I

    .line 655
    iput-object p2, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mColorsSub:[[I

    .line 656
    return-object p0
.end method

.method public doneButton(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 624
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDoneBtn:I

    .line 625
    return-object p0
.end method

.method public dynamicButtonColor(Z)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "enabled"    # Z
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 649
    iput-boolean p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mDynamicButtonColor:Z

    .line 650
    return-object p0
.end method

.method public preselect(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 1
    .param p1, "preselect"    # I
        .annotation build Landroid/support/annotation/ColorInt;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 613
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mPreselect:I

    .line 614
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mSetPreselectionColor:Z

    .line 615
    return-object p0
.end method

.method public presetsButton(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "text"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 644
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mPresetsBtn:I

    .line 645
    return-object p0
.end method

.method public show()Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    .locals 2
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 684
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->build()Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    move-result-object v0

    .line 685
    .local v0, "dialog":Lcom/afollestad/materialdialogs/color/ColorChooserDialog;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mContext:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v0, v1}, Lcom/afollestad/materialdialogs/color/ColorChooserDialog;->show(Landroid/support/v7/app/AppCompatActivity;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog;

    .line 686
    return-object v0
.end method

.method public tag(Ljava/lang/String;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "tag"    # Ljava/lang/String;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 603
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTag:Ljava/lang/String;

    .line 604
    return-object p0
.end method

.method public theme(Lcom/afollestad/materialdialogs/Theme;)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "theme"    # Lcom/afollestad/materialdialogs/Theme;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 608
    iput-object p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTheme:Lcom/afollestad/materialdialogs/Theme;

    .line 609
    return-object p0
.end method

.method public titleSub(I)Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;
    .locals 0
    .param p1, "titleSub"    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 598
    iput p1, p0, Lcom/afollestad/materialdialogs/color/ColorChooserDialog$Builder;->mTitleSub:I

    .line 599
    return-object p0
.end method
