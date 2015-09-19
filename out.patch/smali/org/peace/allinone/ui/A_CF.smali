.class public Lorg/peace/allinone/ui/A_CF;
.super Ljava/lang/Object;
.source "A.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public foo()V
    .locals 1
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        method = "foo"
        clazz = "org.peace.allinone.ui.A"
    .end annotation

    .prologue
    .line 12
    new-instance v0, Lorg/peace/allinone/ui/Fix;

    invoke-direct {v0}, Lorg/peace/allinone/ui/Fix;-><init>()V

    invoke-virtual {v0}, Lorg/peace/allinone/ui/Fix;->foo()V

    .line 13
    return-void
.end method
