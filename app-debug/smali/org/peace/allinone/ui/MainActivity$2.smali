.class Lorg/peace/allinone/ui/MainActivity$2;
.super Lbutterknife/internal/DebouncingOnClickListener;
.source "MainActivity.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/peace/allinone/ui/MainActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/peace/allinone/ui/MainActivity;


# direct methods
.method constructor <init>(Lorg/peace/allinone/ui/MainActivity;)V
    .locals 0
    .param p1, "this$0"    # Lorg/peace/allinone/ui/MainActivity;

    .line 41
    iput-object p1, p0, Lorg/peace/allinone/ui/MainActivity$2;->this$0:Lorg/peace/allinone/ui/MainActivity;

    invoke-direct {p0}, Lbutterknife/internal/DebouncingOnClickListener;-><init>()V

    return-void
.end method


# virtual methods
.method public doClick(Landroid/view/View;)V
    .locals 1
    .param p1, "view"    # Landroid/view/View;

    .line 44
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity$2;->this$0:Lorg/peace/allinone/ui/MainActivity;

    invoke-virtual {v0}, Lorg/peace/allinone/ui/MainActivity;->leak()V

    .line 45
    return-void
.end method
