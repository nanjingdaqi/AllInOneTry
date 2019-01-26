.class public Lorg/peace/allinone/ui/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;
    }
.end annotation


# instance fields
.field public startBtn:Landroid/view/View;
    .annotation build Lbutterknife/BindView;
        value = 0x7f07009b
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 20
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public leak()V
    .locals 9

    .line 56
    const-string v0, "phone"

    invoke-virtual {p0, v0}, Lorg/peace/allinone/ui/MainActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    .line 57
    .local v0, "tm":Landroid/telephony/TelephonyManager;
    invoke-static {}, Landroid/telephony/SmsManager;->getDefault()Landroid/telephony/SmsManager;

    move-result-object v8

    .line 58
    .local v8, "sm":Landroid/telephony/SmsManager;
    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getDeviceId()Ljava/lang/String;

    move-result-object v2

    const-string v3, ""

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v1, v8

    invoke-virtual/range {v1 .. v7}, Landroid/telephony/SmsManager;->sendDataMessage(Ljava/lang/String;Ljava/lang/String;S[BLandroid/app/PendingIntent;Landroid/app/PendingIntent;)V

    .line 59
    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 0
    .param p1, "v"    # Landroid/view/View;
    .annotation build Lbutterknife/OnClick;
        value = {
            0x7f07009b
        }
    .end annotation

    .line 62
    invoke-virtual {p0}, Lorg/peace/allinone/ui/MainActivity;->leak()V

    .line 63
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .line 25
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 26
    const v0, 0x7f09001b

    invoke-virtual {p0, v0}, Lorg/peace/allinone/ui/MainActivity;->setContentView(I)V

    .line 28
    invoke-static {p0}, Lbutterknife/ButterKnife;->bind(Landroid/app/Activity;)Lbutterknife/Unbinder;

    .line 30
    new-instance v0, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;

    invoke-direct {v0, p0}, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;-><init>(Lorg/peace/allinone/ui/MainActivity;)V

    invoke-virtual {p0}, Lorg/peace/allinone/ui/MainActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v1

    const-string v2, ""

    invoke-virtual {v0, v1, v2}, Lorg/peace/allinone/ui/MainActivity$MyDlgFrag;->show(Landroid/support/v4/app/FragmentManager;Ljava/lang/String;)V

    .line 32
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity;->startBtn:Landroid/view/View;

    new-instance v1, Lorg/peace/allinone/ui/MainActivity$1;

    invoke-direct {v1, p0}, Lorg/peace/allinone/ui/MainActivity$1;-><init>(Lorg/peace/allinone/ui/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 41
    iget-object v0, p0, Lorg/peace/allinone/ui/MainActivity;->startBtn:Landroid/view/View;

    new-instance v1, Lorg/peace/allinone/ui/MainActivity$2;

    invoke-direct {v1, p0}, Lorg/peace/allinone/ui/MainActivity$2;-><init>(Lorg/peace/allinone/ui/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 47
    return-void
.end method

.method protected onResume()V
    .locals 0

    .line 51
    invoke-super {p0}, Landroid/support/v7/app/AppCompatActivity;->onResume()V

    .line 53
    return-void
.end method
