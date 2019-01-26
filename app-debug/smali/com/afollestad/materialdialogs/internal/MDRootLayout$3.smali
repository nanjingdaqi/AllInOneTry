.class Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;
.super Ljava/lang/Object;
.source "MDRootLayout.java"

# interfaces
.implements Landroid/view/ViewTreeObserver$OnScrollChangedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/afollestad/materialdialogs/internal/MDRootLayout;->addScrollListener(Landroid/view/ViewGroup;ZZ)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/afollestad/materialdialogs/internal/MDRootLayout;

.field final synthetic val$setForBottom:Z

.field final synthetic val$setForTop:Z

.field final synthetic val$vg:Landroid/view/ViewGroup;


# direct methods
.method constructor <init>(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/ViewGroup;ZZ)V
    .locals 0
    .param p1, "this$0"    # Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    .line 468
    iput-object p1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->this$0:Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    iput-object p2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$vg:Landroid/view/ViewGroup;

    iput-boolean p3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$setForTop:Z

    iput-boolean p4, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$setForBottom:Z

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onScrollChanged()V
    .locals 7

    .line 471
    const/4 v0, 0x0

    .line 472
    .local v0, "hasButtons":Z
    iget-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->this$0:Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    invoke-static {v1}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->access$400(Lcom/afollestad/materialdialogs/internal/MDRootLayout;)[Lcom/afollestad/materialdialogs/internal/MDButton;

    move-result-object v1

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    .line 473
    .local v4, "button":Lcom/afollestad/materialdialogs/internal/MDButton;
    if-eqz v4, :cond_0

    invoke-virtual {v4}, Lcom/afollestad/materialdialogs/internal/MDButton;->getVisibility()I

    move-result v5

    const/16 v6, 0x8

    if-eq v5, v6, :cond_0

    .line 474
    const/4 v0, 0x1

    .line 475
    goto :goto_1

    .line 472
    .end local v4    # "button":Lcom/afollestad/materialdialogs/internal/MDButton;
    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 478
    :cond_1
    :goto_1
    iget-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$vg:Landroid/view/ViewGroup;

    instance-of v1, v1, Landroid/webkit/WebView;

    if-eqz v1, :cond_2

    .line 479
    iget-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->this$0:Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$vg:Landroid/view/ViewGroup;

    check-cast v2, Landroid/webkit/WebView;

    iget-boolean v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$setForTop:Z

    iget-boolean v4, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$setForBottom:Z

    invoke-static {v1, v2, v3, v4, v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->access$600(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/webkit/WebView;ZZZ)V

    goto :goto_2

    .line 481
    :cond_2
    iget-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->this$0:Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    iget-object v2, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$vg:Landroid/view/ViewGroup;

    iget-boolean v3, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$setForTop:Z

    iget-boolean v4, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->val$setForBottom:Z

    invoke-static {v1, v2, v3, v4, v0}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->access$500(Lcom/afollestad/materialdialogs/internal/MDRootLayout;Landroid/view/ViewGroup;ZZZ)V

    .line 483
    :goto_2
    iget-object v1, p0, Lcom/afollestad/materialdialogs/internal/MDRootLayout$3;->this$0:Lcom/afollestad/materialdialogs/internal/MDRootLayout;

    invoke-virtual {v1}, Lcom/afollestad/materialdialogs/internal/MDRootLayout;->invalidate()V

    .line 484
    return-void
.end method
