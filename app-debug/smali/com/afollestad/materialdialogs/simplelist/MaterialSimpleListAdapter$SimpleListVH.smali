.class Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;
.super Landroid/support/v7/widget/RecyclerView$ViewHolder;
.source "MaterialSimpleListAdapter.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "SimpleListVH"
.end annotation


# instance fields
.field final adapter:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

.field final icon:Landroid/widget/ImageView;

.field final title:Landroid/widget/TextView;


# direct methods
.method constructor <init>(Landroid/view/View;Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)V
    .locals 1
    .param p1, "itemView"    # Landroid/view/View;
    .param p2, "adapter"    # Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    .line 92
    invoke-direct {p0, p1}, Landroid/support/v7/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    .line 93
    const v0, 0x1020006

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->icon:Landroid/widget/ImageView;

    .line 94
    const v0, 0x1020016

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->title:Landroid/widget/TextView;

    .line 95
    iput-object p2, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->adapter:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    .line 96
    invoke-virtual {p1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 97
    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 5
    .param p1, "view"    # Landroid/view/View;

    .line 101
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->adapter:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->access$000(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 102
    iget-object v0, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->adapter:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->access$000(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;

    move-result-object v0

    iget-object v1, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->adapter:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->access$100(Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v1

    .line 103
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->getAdapterPosition()I

    move-result v2

    iget-object v3, p0, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->adapter:Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$SimpleListVH;->getAdapterPosition()I

    move-result v4

    invoke-virtual {v3, v4}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter;->getItem(I)Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;

    move-result-object v3

    .line 102
    invoke-interface {v0, v1, v2, v3}, Lcom/afollestad/materialdialogs/simplelist/MaterialSimpleListAdapter$Callback;->onMaterialListItemSelected(Lcom/afollestad/materialdialogs/MaterialDialog;ILcom/afollestad/materialdialogs/simplelist/MaterialSimpleListItem;)V

    .line 105
    :cond_0
    return-void
.end method
