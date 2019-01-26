.class public Lorg/peace/allinone/ui/MainActivity_ViewBinding;
.super Ljava/lang/Object;
.source "MainActivity_ViewBinding.java"

# interfaces
.implements Lbutterknife/Unbinder;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Lorg/peace/allinone/ui/MainActivity;",
        ">",
        "Ljava/lang/Object;",
        "Lbutterknife/Unbinder;"
    }
.end annotation


# instance fields
.field protected target:Lorg/peace/allinone/ui/MainActivity;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field

.field private view2131165339:Landroid/view/View;


# direct methods
.method public constructor <init>(Lorg/peace/allinone/ui/MainActivity;Landroid/view/View;)V
    .locals 2
    .param p2, "source"    # Landroid/view/View;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;",
            "Landroid/view/View;",
            ")V"
        }
    .end annotation

    .line 17
    .local p0, "this":Lorg/peace/allinone/ui/MainActivity_ViewBinding;, "Lorg/peace/allinone/ui/MainActivity_ViewBinding<TT;>;"
    .local p1, "target":Lorg/peace/allinone/ui/MainActivity;, "TT;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 18
    iput-object p1, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding;->target:Lorg/peace/allinone/ui/MainActivity;

    .line 21
    const-string v0, "field \'startBtn\' and method \'onClick\'"

    const v1, 0x7f07009b

    invoke-static {p2, v1, v0}, Lbutterknife/internal/Utils;->findRequiredView(Landroid/view/View;ILjava/lang/String;)Landroid/view/View;

    move-result-object v0

    .line 22
    .local v0, "view":Landroid/view/View;
    iput-object v0, p1, Lorg/peace/allinone/ui/MainActivity;->startBtn:Landroid/view/View;

    .line 23
    iput-object v0, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding;->view2131165339:Landroid/view/View;

    .line 24
    new-instance v1, Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;

    invoke-direct {v1, p0, p1}, Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;-><init>(Lorg/peace/allinone/ui/MainActivity_ViewBinding;Lorg/peace/allinone/ui/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 30
    return-void
.end method


# virtual methods
.method public unbind()V
    .locals 3

    .line 34
    .local p0, "this":Lorg/peace/allinone/ui/MainActivity_ViewBinding;, "Lorg/peace/allinone/ui/MainActivity_ViewBinding<TT;>;"
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding;->target:Lorg/peace/allinone/ui/MainActivity;

    .line 35
    .local v0, "target":Lorg/peace/allinone/ui/MainActivity;, "TT;"
    if-eqz v0, :cond_0

    .line 37
    const/4 v1, 0x0

    iput-object v1, v0, Lorg/peace/allinone/ui/MainActivity;->startBtn:Landroid/view/View;

    .line 39
    iget-object v2, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding;->view2131165339:Landroid/view/View;

    invoke-virtual {v2, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 40
    iput-object v1, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding;->view2131165339:Landroid/view/View;

    .line 42
    iput-object v1, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding;->target:Lorg/peace/allinone/ui/MainActivity;

    .line 43
    return-void

    .line 35
    :cond_0
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "Bindings already cleared."

    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1
.end method
