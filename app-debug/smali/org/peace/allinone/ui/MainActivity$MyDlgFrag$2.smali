.class Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$2;
.super Ljava/lang/Object;
.source "MainActivity.java"

# interfaces
.implements Landroid/content/DialogInterface$OnCancelListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;


# direct methods
.method constructor <init>(Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;)V
    .locals 0
    .param p1, "this$1"    # Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;

    .line 71
    iput-object p1, p0, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$2;->this$1:Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCancel(Landroid/content/DialogInterface;)V
    .locals 1
    .param p1, "dialogInterface"    # Landroid/content/DialogInterface;

    .line 74
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$2;->this$1:Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;

    iget-object v0, v0, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;->this$0:Lorg/peace/allinone/ui/MainActivity;

    invoke-virtual {v0}, Lorg/peace/allinone/ui/MainActivity;->leak()V

    .line 75
    return-void
.end method
