.class Lorg/peace/allinone/D$1;
.super Ljava/lang/Thread;
.source "D.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/peace/allinone/D;->foo()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/peace/allinone/D;


# direct methods
.method constructor <init>(Lorg/peace/allinone/D;)V
    .registers 2
    .param p1, "this$0"    # Lorg/peace/allinone/D;

    .prologue
    .line 10
    iput-object p1, p0, Lorg/peace/allinone/D$1;->this$0:Lorg/peace/allinone/D;

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .prologue
    .line 13
    iget-object v0, p0, Lorg/peace/allinone/D$1;->this$0:Lorg/peace/allinone/D;

    const/4 v1, 0x0

    iput-boolean v1, v0, Lorg/peace/allinone/D;->var:Z

    .line 14
    return-void
.end method
