.class Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;
.super Landroid/support/v7/widget/RecyclerView$ViewHolder;
.source "DefaultRvAdapter.java"

# interfaces
.implements Landroid/view/View$OnClickListener;
.implements Landroid/view/View$OnLongClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/afollestad/materialdialogs/DefaultRvAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "DefaultVH"
.end annotation


# instance fields
.field final adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

.field final control:Landroid/widget/CompoundButton;

.field final title:Landroid/widget/TextView;


# direct methods
.method constructor <init>(Landroid/view/View;Lcom/afollestad/materialdialogs/DefaultRvAdapter;)V
    .locals 1
    .param p1, "itemView"    # Landroid/view/View;
    .param p2, "adapter"    # Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    .line 158
    invoke-direct {p0, p1}, Landroid/support/v7/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    .line 159
    sget v0, Lcom/afollestad/materialdialogs/R$id;->md_control:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CompoundButton;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->control:Landroid/widget/CompoundButton;

    .line 160
    sget v0, Lcom/afollestad/materialdialogs/R$id;->md_title:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->title:Landroid/widget/TextView;

    .line 161
    iput-object p2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    .line 162
    invoke-virtual {p1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 163
    invoke-static {p2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v0

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v0, v0, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->listLongCallback:Lcom/afollestad/materialdialogs/MaterialDialog$ListLongCallback;

    if-eqz v0, :cond_0

    .line 164
    invoke-virtual {p1, p0}, Landroid/view/View;->setOnLongClickListener(Landroid/view/View$OnLongClickListener;)V

    .line 165
    :cond_0
    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 7
    .param p1, "view"    # Landroid/view/View;

    .line 168
    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$100(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v0

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    .line 169
    const/4 v0, 0x0

    .line 170
    .local v0, "text":Ljava/lang/CharSequence;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v1

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    if-eqz v1, :cond_0

    .line 171
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v2

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_0

    .line 172
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v1

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    move-object v0, v1

    check-cast v0, Ljava/lang/CharSequence;

    .line 174
    :cond_0
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$100(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    move-result-object v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v2

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v4

    const/4 v6, 0x0

    move-object v3, p1

    move-object v5, v0

    invoke-interface/range {v1 .. v6}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;->onItemSelected(Lcom/afollestad/materialdialogs/MaterialDialog;Landroid/view/View;ILjava/lang/CharSequence;Z)Z

    .line 176
    .end local v0    # "text":Ljava/lang/CharSequence;
    :cond_1
    return-void
.end method

.method public onLongClick(Landroid/view/View;)Z
    .locals 7
    .param p1, "view"    # Landroid/view/View;

    .line 179
    iget-object v0, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$100(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v0

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    .line 180
    const/4 v0, 0x0

    .line 181
    .local v0, "text":Ljava/lang/CharSequence;
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v1

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    if-eqz v1, :cond_0

    .line 182
    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v2

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v2, v2, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_0

    .line 183
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v1

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog;->builder:Lcom/afollestad/materialdialogs/MaterialDialog$Builder;

    iget-object v1, v1, Lcom/afollestad/materialdialogs/MaterialDialog$Builder;->items:Ljava/util/ArrayList;

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    move-object v0, v1

    check-cast v0, Ljava/lang/CharSequence;

    .line 185
    :cond_0
    iget-object v1, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$100(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;

    move-result-object v1

    iget-object v2, p0, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->adapter:Lcom/afollestad/materialdialogs/DefaultRvAdapter;

    invoke-static {v2}, Lcom/afollestad/materialdialogs/DefaultRvAdapter;->access$000(Lcom/afollestad/materialdialogs/DefaultRvAdapter;)Lcom/afollestad/materialdialogs/MaterialDialog;

    move-result-object v2

    invoke-virtual {p0}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$DefaultVH;->getAdapterPosition()I

    move-result v4

    const/4 v6, 0x1

    move-object v3, p1

    move-object v5, v0

    invoke-interface/range {v1 .. v6}, Lcom/afollestad/materialdialogs/DefaultRvAdapter$InternalListCallback;->onItemSelected(Lcom/afollestad/materialdialogs/MaterialDialog;Landroid/view/View;ILjava/lang/CharSequence;Z)Z

    move-result v1

    return v1

    .line 187
    .end local v0    # "text":Ljava/lang/CharSequence;
    :cond_1
    const/4 v0, 0x0

    return v0
.end method
