.class public Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;
.super Landroid/support/v4/app/DialogFragment;
.source "MainActivity.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/peace/allinone/ui/MainActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "MyDlgFrag"
.end annotation


# instance fields
.field final synthetic this$0:Lorg/peace/allinone/ui/MainActivity;


# direct methods
.method public constructor <init>(Lorg/peace/allinone/ui/MainActivity;)V
    .locals 0
    .param p1, "this$0"    # Lorg/peace/allinone/ui/MainActivity;

    .line 65
    iput-object p1, p0, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;->this$0:Lorg/peace/allinone/ui/MainActivity;

    invoke-direct {p0}, Landroid/support/v4/app/DialogFragment;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .line 70
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;->getActivity()Landroid/support/v4/app/FragmentActivity;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    new-instance v1, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$2;

    invoke-direct {v1, p0}, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$2;-><init>(Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;)V

    .line 71
    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const-string v1, "Yes"

    new-instance v2, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$1;

    invoke-direct {v2, p0}, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag$1;-><init>(Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;)V

    .line 77
    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 83
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    .line 70
    return-object v0
.end method
