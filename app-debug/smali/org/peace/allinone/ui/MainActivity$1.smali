.class Lorg/peace/allinone/ui/MainActivity$1;
.super Ljava/lang/Object;
.source "MainActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


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

    .line 32
    iput-object p1, p0, Lorg/peace/allinone/ui/MainActivity$1;->this$0:Lorg/peace/allinone/ui/MainActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1
    .param p1, "view"    # Landroid/view/View;

    .line 35
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity$1;->this$0:Lorg/peace/allinone/ui/MainActivity;

    invoke-virtual {v0}, Lorg/peace/allinone/ui/MainActivity;->leak()V

    .line 36
    return-void
.end method
