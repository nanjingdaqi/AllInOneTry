.class Lcom/afollestad/materialdialogs/DefaultRvAdapter;
.super Landroid/support/v7/widget/RecyclerView$Adapter;
.source "DefaultRvAdapter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;,
        Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/support/v7/widget/RecyclerView$Adapter<",
        "Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;",
        ">;"
    }
.end annotation


# instance fields
.field private callback:Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

.field private final dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

.field private final itemGravity:Lcom/afollestad/materialdialogs/GravityEnum;

.field private final layout:I
    .annotation build Landroid/support/annotation/LayoutRes;
    .end annotation
.end field


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/MaterialDialog;I)V
    .locals 1
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;
    .param p2, "layout"    # I
        .annotation build Landroid/support/annotation/LayoutRes;
        .end annotation
    .end param

    .line 37
    invoke-direct {p0}, Landroid/support/v7/widget/RecyclerView$Adapter;-><init>()V

    .line 38
    iput-object p1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 39
    iput p2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->layout:I

    .line 40
    iget-object v0, p1, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->itemsGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->itemGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    .line 41
    return-void
.end method

.method static synthetic access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    .line 25
    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    return-object v0
.end method

.method static synthetic access$100(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    .line 25
    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->callback:Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    return-object v0
.end method

.method private isRTL()Z
    .locals 4
    .annotation build Landroid/annotation/TargetApi;
        value = 0x11
    .end annotation

    .line 145
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v1, 0x0

    const/16 v2, 0x11

    if-ge v0, v2, :cond_0

    .line 146
    return v1

    .line 147
    :cond_0
    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/MaterialDialog;->getBuilder()Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    .line 148
    .local v0, "config":Landroid/content/res/Configuration;
    invoke-virtual {v0}, Landroid/content/res/Configuration;->getLayoutDirection()I

    move-result v2

    const/4 v3, 0x1

    if-ne v2, v3, :cond_1

    const/4 v1, 0x1

    nop

    :cond_1
    return v1
.end method

.method private setupGravity(Landroid/view/ViewGroup;)V
    .locals 8
    .param p1, "view"    # Landroid/view/ViewGroup;
    .annotation build Landroid/annotation/TargetApi;
        value = 0x11
    .end annotation

    .line 113
    move-object v0, p1

    check-cast v0, Landroid/widget/LinearLayout;

    .line 114
    .local v0, "itemRoot":Landroid/widget/LinearLayout;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->itemGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/GravityEnum;->getGravityInt()I

    move-result v1

    .line 115
    .local v1, "gravityInt":I
    or-int/lit8 v2, v1, 0x10

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 117
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    const/4 v3, 0x2

    if-ne v2, v3, :cond_1

    .line 118
    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->itemGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v3, Lcom/afollestad/materialdialogs/GravityEnum;->END:Lcom/afollestad/materialdialogs/GravityEnum;

    const/4 v4, 0x0

    if-ne v2, v3, :cond_0

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->isRTL()Z

    move-result v2

    if-nez v2, :cond_0

    invoke-virtual {p1, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    instance-of v2, v2, Landroid/widget/CompoundButton;

    if-eqz v2, :cond_0

    .line 119
    invoke-virtual {p1, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/CompoundButton;

    .line 120
    .local v2, "first":Landroid/widget/CompoundButton;
    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 122
    invoke-virtual {p1, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/TextView;

    .line 123
    .local v3, "second":Landroid/widget/TextView;
    invoke-virtual {p1, v3}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 124
    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingRight()I

    move-result v4

    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingTop()I

    move-result v5

    .line 125
    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingLeft()I

    move-result v6

    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingBottom()I

    move-result v7

    .line 124
    invoke-virtual {v3, v4, v5, v6, v7}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 127
    invoke-virtual {p1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 128
    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 129
    .end local v2    # "first":Landroid/widget/CompoundButton;
    .end local v3    # "second":Landroid/widget/TextView;
    goto :goto_0

    :cond_0
    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->itemGravity:Lcom/afollestad/materialdialogs/GravityEnum;

    sget-object v3, Lcom/afollestad/materialdialogs/GravityEnum;->START:Lcom/afollestad/materialdialogs/GravityEnum;

    if-ne v2, v3, :cond_1

    invoke-direct {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->isRTL()Z

    move-result v2

    if-eqz v2, :cond_1

    const/4 v2, 0x1

    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    instance-of v3, v3, Landroid/widget/CompoundButton;

    if-eqz v3, :cond_1

    .line 130
    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/CompoundButton;

    .line 131
    .restart local v2    # "first":Landroid/widget/CompoundButton;
    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 133
    invoke-virtual {p1, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/TextView;

    .line 134
    .restart local v3    # "second":Landroid/widget/TextView;
    invoke-virtual {p1, v3}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 135
    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingRight()I

    move-result v4

    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingTop()I

    move-result v5

    .line 136
    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingRight()I

    move-result v6

    invoke-virtual {v3}, Landroid/widget/TextView;->getPaddingBottom()I

    move-result v7

    .line 135
    invoke-virtual {v3, v4, v5, v6, v7}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 138
    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 139
    invoke-virtual {p1, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 142
    .end local v2    # "first":Landroid/widget/CompoundButton;
    .end local v3    # "second":Landroid/widget/TextView;
    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
.method public getItemCount()I
    .locals 1

    .line 109
    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public bridge synthetic onBindViewHolder(Landroid/support/v7/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    .line 25
    check-cast p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;

    invoke-virtual {p0, p1, p2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->onBindViewHolder(Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;I)V

    return-void
.end method

.method public onBindViewHolder(Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;I)V
    .locals 7
    .param p1, "holder"    # Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;
    .param p2, "index"    # I

    .line 55
    iget-object v0, p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->itemView:Landroid/view/View;

    .line 56
    .local v0, "view":Landroid/view/View;
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->disabledIndices:[Ljava/lang/Integer;

    invoke-static {v1, v2}, Lcom/afollestad/materialdialogs/util/DialogUtils;->isIn(Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result v1

    .line 57
    .local v1, "disabled":Z
    sget-object v2, Lcom/afollestad/materialdialogs/DefaultRvAdapter$1;->$SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType:[I

    iget-object v3, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v3, v3, Lcom/afollestad/materialdialogs/MaterialDialog;->listType:Lcom/afollestad/materialdialogs/MaterialDialog$ListType;

    invoke-virtual {v3}, Lcom/afollestad/materialdialogs/MaterialDialog$ListType;->ordinal()I

    move-result v3

    aget v2, v2, v3

    const/4 v3, 0x0

    const/4 v4, 0x1

    packed-switch v2, :pswitch_data_0

    goto :goto_3

    .line 71
    :pswitch_0
    iget-object v2, p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->control:Landroid/widget/CompoundButton;

    check-cast v2, Landroid/widget/CheckBox;

    .line 72
    .local v2, "checkbox":Landroid/widget/CheckBox;
    iget-object v5, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog;->selectedIndicesList:Ljava/util/List;

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v5, v6}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v5

    .line 73
    .local v5, "selected":Z
    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->choiceWidgetColor:Landroid/content/res/ColorStateList;

    if-eqz v6, :cond_0

    .line 74
    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->choiceWidgetColor:Landroid/content/res/ColorStateList;

    invoke-static {v2, v6}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/CheckBox;Landroid/content/res/ColorStateList;)V

    goto :goto_0

    .line 76
    :cond_0
    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->widgetColor:I

    invoke-static {v2, v6}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/CheckBox;I)V

    .line 78
    :goto_0
    invoke-virtual {v2, v5}, Landroid/widget/CheckBox;->setChecked(Z)V

    .line 79
    xor-int/lit8 v6, v1, 0x1

    invoke-virtual {v2, v6}, Landroid/widget/CheckBox;->setEnabled(Z)V

    .line 80
    goto :goto_3

    .line 59
    .end local v2    # "checkbox":Landroid/widget/CheckBox;
    .end local v5    # "selected":Z
    :pswitch_1
    iget-object v2, p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->control:Landroid/widget/CompoundButton;

    check-cast v2, Landroid/widget/RadioButton;

    .line 60
    .local v2, "radio":Landroid/widget/RadioButton;
    iget-object v5, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->selectedIndex:I

    if-ne v5, p2, :cond_1

    const/4 v5, 0x1

    goto :goto_1

    :cond_1
    const/4 v5, 0x0

    .line 61
    .restart local v5    # "selected":Z
    :goto_1
    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->choiceWidgetColor:Landroid/content/res/ColorStateList;

    if-eqz v6, :cond_2

    .line 62
    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->choiceWidgetColor:Landroid/content/res/ColorStateList;

    invoke-static {v2, v6}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/RadioButton;Landroid/content/res/ColorStateList;)V

    goto :goto_2

    .line 64
    :cond_2
    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->widgetColor:I

    invoke-static {v2, v6}, Lcom/afollestad/materialdialogs/internal/MDTintHelper;->setTint(Landroid/widget/RadioButton;I)V

    .line 66
    :goto_2
    invoke-virtual {v2, v5}, Landroid/widget/RadioButton;->setChecked(Z)V

    .line 67
    xor-int/lit8 v6, v1, 0x1

    invoke-virtual {v2, v6}, Landroid/widget/RadioButton;->setEnabled(Z)V

    .line 68
    nop

    .line 84
    .end local v2    # "radio":Landroid/widget/RadioButton;
    .end local v5    # "selected":Z
    :goto_3
    iget-object v2, p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->title:Landroid/widget/TextView;

    iget-object v5, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    invoke-virtual {v5, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/CharSequence;

    invoke-virtual {v2, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 85
    iget-object v2, p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->title:Landroid/widget/TextView;

    iget-object v5, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget v5, v5, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->itemColor:I

    invoke-virtual {v2, v5}, Landroid/widget/TextView;->setTextColor(I)V

    .line 86
    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v5, p1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->title:Landroid/widget/TextView;

    iget-object v6, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v6, v6, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->regularFont:Landroid/graphics/Typeface;

    invoke-virtual {v2, v5, v6}, Lcom/afollestad/materialdialogs/MaterialDialog;->setTypeface(Landroid/widget/TextView;Landroid/graphics/Typeface;)V

    .line 88
    move-object v2, v0

    check-cast v2, Landroid/view/ViewGroup;

    invoke-direct {p0, v2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->setupGravity(Landroid/view/ViewGroup;)V

    .line 90
    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->itemIds:[I

    if-eqz v2, :cond_4

    .line 91
    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->itemIds:[I

    array-length v2, v2

    if-ge p2, v2, :cond_3

    .line 92
    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->itemIds:[I

    aget v2, v2, p2

    invoke-virtual {v0, v2}, Landroid/view/View;->setId(I)V

    goto :goto_4

    .line 93
    :cond_3
    const/4 v2, -0x1

    invoke-virtual {v0, v2}, Landroid/view/View;->setId(I)V

    .line 96
    :cond_4
    :goto_4
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v5, 0x15

    if-lt v2, v5, :cond_6

    .line 97
    move-object v2, v0

    check-cast v2, Landroid/view/ViewGroup;

    .line 98
    .local v2, "group":Landroid/view/ViewGroup;
    invoke-virtual {v2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v5

    const/4 v6, 0x2

    if-ne v5, v6, :cond_6

    .line 100
    invoke-virtual {v2, v3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    instance-of v5, v5, Landroid/widget/CompoundButton;

    const/4 v6, 0x0

    if-eqz v5, :cond_5

    .line 101
    invoke-virtual {v2, v3}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3, v6}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    goto :goto_5

    .line 102
    :cond_5
    invoke-virtual {v2, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    instance-of v3, v3, Landroid/widget/CompoundButton;

    if-eqz v3, :cond_6

    .line 103
    invoke-virtual {v2, v4}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3, v6}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 106
    .end local v2    # "group":Landroid/view/ViewGroup;
    :cond_6
    :goto_5
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
    .locals 0

    .line 25
    invoke-virtual {p0, p1, p2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;

    move-result-object p1

    return-object p1
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;
    .locals 3
    .param p1, "parent"    # Landroid/view/ViewGroup;
    .param p2, "viewType"    # I

    .line 48
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    iget v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->layout:I

    .line 49
    const/4 v2, 0x0

    invoke-virtual {v0, v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    .line 50
    .local v0, "view":Landroid/view/View;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/MaterialDialog;->getListSelector()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/afollestad/materialdialogs/util/DialogUtils;->setBackgroundCompat(Landroid/view/View;Landroid/graphics/drawable/Drawable;)V

    .line 51
    new-instance v1, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;

    invoke-direct {v1, v0, p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;-><init>(Landroid/view/View;Lcom/afollestad/materialdialogs/DefaultRvAdapter;)V

    return-object v1
.end method

.method setCallback(Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;)V
    .locals 0
    .param p1, "callback"    # Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    .line 44
    iput-object p1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->callback:Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    .line 45
    return-void
.end method
