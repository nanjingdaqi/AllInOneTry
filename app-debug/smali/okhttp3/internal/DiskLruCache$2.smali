.class Lokhttp3/internal/DiskLruCache$2;
.super Lokhttp3/internal/FaultHidingSink;
.source "DiskLruCache.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp3/internal/DiskLruCache;->newJournalWriter()Lokio/BufferedSink;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# static fields
.field static final synthetic $assertionsDisabled:Z


# instance fields
.field final synthetic this$0:Lokhttp3/internal/DiskLruCache;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 309
    const-class v0, Lokhttp3/internal/DiskLruCache;

    return-void
.end method

.method constructor <init>(Lokhttp3/internal/DiskLruCache;Lokio/Sink;)V
    .locals 0
    .param p1, "this$0"    # Lokhttp3/internal/DiskLruCache;
    .param p2, "delegate"    # Lokio/Sink;

    .line 309
    iput-object p1, p0, Lokhttp3/internal/DiskLruCache$2;->this$0:Lokhttp3/internal/DiskLruCache;

    invoke-direct {p0, p2}, Lokhttp3/internal/FaultHidingSink;-><init>(Lokio/Sink;)V

    return-void
.end method


# virtual methods
.method protected onException(Ljava/io/IOException;)V
    .locals 2
    .param p1, "e"    # Ljava/io/IOException;

    .line 311
    nop

    .line 312
    iget-object v0, p0, Lokhttp3/internal/DiskLruCache$2;->this$0:Lokhttp3/internal/DiskLruCache;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lokhttp3/internal/DiskLruCache;->access$1002(Lokhttp3/internal/DiskLruCache;Z)Z

    .line 313
    return-void
.end method