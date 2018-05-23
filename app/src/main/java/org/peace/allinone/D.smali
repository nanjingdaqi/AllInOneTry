.class public Lorg/peace/allinone/D;
.super Ljava/lang/Object;
.source "D.java"


# instance fields
.field public var:Z


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 5
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public foo()V
    .registers 2

    .prologue
    .line 10
    new-instance v0, Lorg/peace/allinone/D$1;

    invoke-direct {v0, p0}, Lorg/peace/allinone/D$1;-><init>(Lorg/peace/allinone/D;)V

    .line 15
    invoke-virtual {v0}, Lorg/peace/allinone/D$1;->start()V

    .line 16
    return-void
.end method
