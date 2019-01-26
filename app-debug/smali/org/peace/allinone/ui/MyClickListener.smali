.class public Lorg/peace/allinone/ui/MyClickListener;
.super Ljava/lang/Object;
.source "MyClickListener.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field public activity:Lorg/peace/allinone/ui/MainActivity;


# direct methods
.method public constructor <init>(Lorg/peace/allinone/ui/MainActivity;)V
    .locals 0
    .param p1, "activity"    # Lorg/peace/allinone/ui/MainActivity;

    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 10
    iput-object p1, p0, Lorg/peace/allinone/ui/MyClickListener;->activity:Lorg/peace/allinone/ui/MainActivity;

    .line 11
    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1
    .param p1, "view"    # Landroid/view/View;

    .line 15
    iget-object v0, p0, Lorg/peace/allinone/ui/MyClickListener;->activity:Lorg/peace/allinone/ui/MainActivity;

    invoke-virtual {v0}, Lorg/peace/allinone/ui/MainActivity;->leak()V

    .line 16
    return-void
.end method
