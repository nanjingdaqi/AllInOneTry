.class public Lorg/peace/allinone/MyApp;
.super Landroid/app/Application;
.source "MyApp.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 11
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreate()V
    .locals 1

    .line 14
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 15
    const/4 v0, 0x1

    sput-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    .line 16
    invoke-static {p0}, Lme/ele/base/utils/DimenUtil;->init(Landroid/content/Context;)V

    .line 17
    invoke-static {p0}, Lme/ele/base/utils/ResourceUtil;->init(Landroid/content/Context;)V

    .line 18
    return-void
.end method
