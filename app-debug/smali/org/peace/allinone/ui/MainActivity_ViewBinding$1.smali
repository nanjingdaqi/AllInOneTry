.class Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;
.super Lbutterknife/internal/DebouncingOnClickListener;
.source "MainActivity_ViewBinding.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/peace/allinone/ui/MainActivity_ViewBinding;-><init>(Lorg/peace/allinone/ui/MainActivity;Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/peace/allinone/ui/MainActivity_ViewBinding;

.field final synthetic val$target:Lorg/peace/allinone/ui/MainActivity;


# direct methods
.method constructor <init>(Lorg/peace/allinone/ui/MainActivity_ViewBinding;Lorg/peace/allinone/ui/MainActivity;)V
    .locals 0
    .param p1, "this$0"    # Lorg/peace/allinone/ui/MainActivity_ViewBinding;

    .line 24
    .local p0, "this":Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;, "Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;"
    iput-object p1, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;->this$0:Lorg/peace/allinone/ui/MainActivity_ViewBinding;

    iput-object p2, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;->val$target:Lorg/peace/allinone/ui/MainActivity;

    invoke-direct {p0}, Lbutterknife/internal/DebouncingOnClickListener;-><init>()V

    return-void
.end method


# virtual methods
.method public doClick(Landroid/view/View;)V
    .locals 1
    .param p1, "p0"    # Landroid/view/View;

    .line 27
    .local p0, "this":Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;, "Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;"
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity_ViewBinding$1;->val$target:Lorg/peace/allinone/ui/MainActivity;

    invoke-virtual {v0, p1}, Lorg/peace/allinone/ui/MainActivity;->onClick(Landroid/view/View;)V

    .line 28
    return-void
.end method
