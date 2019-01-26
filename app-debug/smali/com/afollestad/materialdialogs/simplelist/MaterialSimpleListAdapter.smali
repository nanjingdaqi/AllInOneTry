.class public Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;
.super Landroid/support/v7/widget/RecyclerView$Adapter;
.source "MaterialSimpleListAdapter.java"

# interfaces
.implements Lcom/afollestad/materialdialogs/internal/MDAdapter;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;,
        Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/support/v7/widget/RecyclerView$Adapter<",
        "Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;",
        ">;",
        "Lcom/afollestad/materialdialogs/internal/MDAdapter;"
    }
.end annotation


# instance fields
.field private callback:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;

.field private dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

.field private items:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;)V
    .locals 2
    .param p1, "callback"    # Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;

    .line 34
    invoke-direct {p0}, Landroid/support/v7/widget/RecyclerView$Adapter;-><init>()V

    .line 35
    new-instance v0, Ljava/util/ArrayList;

    const/4 v1, 0x4

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    iput-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    .line 36
    iput-object p1, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->callback:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;

    .line 37
    return-void
.end method

.method static synthetic access$000(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    .line 24
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->callback:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;

    return-object v0
.end method

.method static synthetic access$100(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;
    .locals 1
    .param p0, "x0"    # Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    .line 24
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    return-object v0
.end method


# virtual methods
.method public add(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;)V
    .locals 1
    .param p1, "item"    # Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;

    .line 40
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 41
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    invoke-virtual {p0, v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->notifyItemInserted(I)V

    .line 42
    return-void
.end method

.method public clear()V
    .locals 1

    .line 45
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 46
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->notifyDataSetChanged()V

    .line 47
    return-void
.end method

.method public getItem(I)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;
    .locals 1
    .param p1, "index"    # I

    .line 50
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;

    return-object v0
.end method

.method public getItemCount()I
    .locals 1

    .line 82
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public bridge synthetic onBindViewHolder(Landroid/support/v7/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    .line 24
    check-cast p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;

    invoke-virtual {p0, p1, p2}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->onBindViewHolder(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;I)V

    return-void
.end method

.method public onBindViewHolder(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;I)V
    .locals 6
    .param p1, "holder"    # Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;
    .param p2, "position"    # I

    .line 64
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    if-eqz v0, :cond_1

    .line 65
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->items:Ljava/util/List;

    invoke-interface {v0, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;

    .line 66
    .local v0, "item":Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;
    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 67
    iget-object v1, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->icon:Landroid/widget/ImageView;

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getIcon()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 68
    iget-object v1, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->icon:Landroid/widget/ImageView;

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getIconPadding()I

    move-result v2

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getIconPadding()I

    move-result v3

    .line 69
    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getIconPadding()I

    move-result v4

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getIconPadding()I

    move-result v5

    .line 68
    invoke-virtual {v1, v2, v3, v4, v5}, Landroid/widget/ImageView;->setPadding(IIII)V

    .line 70
    iget-object v1, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->icon:Landroid/widget/ImageView;

    invoke-virtual {v1}, Landroid/widget/ImageView;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getBackgroundColor()I

    move-result v2

    sget-object v3, Landroid/graphics/PorterDuff$Mode;->SRC_ATOP:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v1, v2, v3}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    goto :goto_0

    .line 73
    :cond_0
    iget-object v1, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->icon:Landroid/widget/ImageView;

    const/16 v2, 0x8

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 75
    :goto_0
    iget-object v1, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->title:Landroid/widget/TextView;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-virtual {v2}, Lcom/afollestad/materialdialogs/MaterialDialog;->getBuilder()Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v2

    invoke-virtual {v2}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->getItemColor()I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setTextColor(I)V

    .line 76
    iget-object v1, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->title:Landroid/widget/TextView;

    invoke-virtual {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;->getContent()Ljava/lang/CharSequence;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 77
    iget-object v1, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    iget-object v2, p1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->title:Landroid/widget/TextView;

    iget-object v3, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    invoke-virtual {v3}, Lcom/afollestad/materialdialogs/MaterialDialog;->getBuilder()Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    move-result-object v3

    invoke-virtual {v3}, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->getRegularFont()Landroid/graphics/Typeface;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Lcom/afollestad/materialdialogs/MaterialDialog;->setTypeface(Landroid/widget/TextView;Landroid/graphics/Typeface;)V

    .line 79
    .end local v0    # "item":Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;
    :cond_1
    return-void
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
    .locals 0

    .line 24
    invoke-virtual {p0, p1, p2}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;

    move-result-object p1

    return-object p1
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;
    .locals 3
    .param p1, "parent"    # Landroid/view/ViewGroup;
    .param p2, "viewType"    # I

    .line 58
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    sget v1, Lcom/afollestad/materialdialogs/commons/R$layout;->md_simplelist_item:I

    .line 59
    const/4 v2, 0x0

    invoke-virtual {v0, v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    .line 60
    .local v0, "view":Landroid/view/View;
    new-instance v1, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;

    invoke-direct {v1, v0, p0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;-><init>(Landroid/view/View;Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)V

    return-object v1
.end method

.method public setDialog(Lcom/afollestad/materialdialogs/MaterialDialog;)V
    .locals 0
    .param p1, "dialog"    # Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 54
    iput-object p1, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->dialog:Lcom/afollestad/materialdialogs/MaterialDialog;

    .line 55
    return-void
.end method
