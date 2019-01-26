.class Lokhttp3/internal/DiskLruCache$3;
.super Ljava/lang/Object;
.source "DiskLruCache.java"

# interfaces
.implements Ljava/util/Iterator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp3/internal/DiskLruCache;->snapshots()Ljava/util/Iterator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Iterator<",
        "Lokhttp3/internal/DiskLruCache$Snapshot;",
        ">;"
    }
.end annotation


# instance fields
.field final delegate:Ljava/util/Iterator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Iterator<",
            "Lokhttp3/internal/DiskLruCache$Entry;",
            ">;"
        }
    .end annotation
.end field

.field nextSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

.field removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

.field final synthetic this$0:Lokhttp3/internal/DiskLruCache;


# direct methods
.method constructor <init>(Lokhttp3/internal/DiskLruCache;)V
    .locals 2
    .param p1, "this$0"    # Lokhttp3/internal/DiskLruCache;

    .line 730
    iput-object p1, p0, Lokhttp3/internal/DiskLruCache$3;->this$0:Lokhttp3/internal/DiskLruCache;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 732
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lokhttp3/internal/DiskLruCache$3;->this$0:Lokhttp3/internal/DiskLruCache;

    invoke-static {v1}, Lokhttp3/internal/DiskLruCache;->access$2300(Lokhttp3/internal/DiskLruCache;)Ljava/util/LinkedHashMap;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/LinkedHashMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    iput-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->delegate:Ljava/util/Iterator;

    return-void
.end method


# virtual methods
.method public hasNext()Z
    .locals 5

    .line 741
    iget-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->nextSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    return v1

    .line 743
    :cond_0
    iget-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->this$0:Lokhttp3/internal/DiskLruCache;

    monitor-enter v0

    .line 745
    :try_start_0
    iget-object v2, p0, Lokhttp3/internal/DiskLruCache$3;->this$0:Lokhttp3/internal/DiskLruCache;

    invoke-static {v2}, Lokhttp3/internal/DiskLruCache;->access$100(Lokhttp3/internal/DiskLruCache;)Z

    move-result v2

    const/4 v3, 0x0

    if-eqz v2, :cond_1

    monitor-exit v0

    return v3

    .line 747
    :cond_1
    :goto_0
    iget-object v2, p0, Lokhttp3/internal/DiskLruCache$3;->delegate:Ljava/util/Iterator;

    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3

    .line 748
    iget-object v2, p0, Lokhttp3/internal/DiskLruCache$3;->delegate:Ljava/util/Iterator;

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lokhttp3/internal/DiskLruCache$Entry;

    .line 749
    .local v2, "entry":Lokhttp3/internal/DiskLruCache$Entry;
    invoke-virtual {v2}, Lokhttp3/internal/DiskLruCache$Entry;->snapshot()Lokhttp3/internal/DiskLruCache$Snapshot;

    move-result-object v4

    .line 750
    .local v4, "snapshot":Lokhttp3/internal/DiskLruCache$Snapshot;
    if-nez v4, :cond_2

    .end local v2    # "entry":Lokhttp3/internal/DiskLruCache$Entry;
    .end local v4    # "snapshot":Lokhttp3/internal/DiskLruCache$Snapshot;
    goto :goto_0

    .line 751
    .restart local v2    # "entry":Lokhttp3/internal/DiskLruCache$Entry;
    .restart local v4    # "snapshot":Lokhttp3/internal/DiskLruCache$Snapshot;
    :cond_2
    iput-object v4, p0, Lokhttp3/internal/DiskLruCache$3;->nextSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    .line 752
    monitor-exit v0

    return v1

    .line 754
    .end local v2    # "entry":Lokhttp3/internal/DiskLruCache$Entry;
    .end local v4    # "snapshot":Lokhttp3/internal/DiskLruCache$Snapshot;
    :cond_3
    monitor-exit v0

    .line 756
    return v3

    .line 754
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public bridge synthetic next()Ljava/lang/Object;
    .locals 1

    .line 730
    invoke-virtual {p0}, Lokhttp3/internal/DiskLruCache$3;->next()Lokhttp3/internal/DiskLruCache$Snapshot;

    move-result-object v0

    return-object v0
.end method

.method public next()Lokhttp3/internal/DiskLruCache$Snapshot;
    .locals 1

    .line 760
    invoke-virtual {p0}, Lokhttp3/internal/DiskLruCache$3;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 761
    iget-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->nextSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    iput-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    .line 762
    const/4 v0, 0x0

    iput-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->nextSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    .line 763
    iget-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    return-object v0

    .line 760
    :cond_0
    new-instance v0, Ljava/util/NoSuchElementException;

    invoke-direct {v0}, Ljava/util/NoSuchElementException;-><init>()V

    throw v0
.end method

.method public remove()V
    .locals 3

    .line 767
    iget-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    if-eqz v0, :cond_0

    .line 769
    const/4 v0, 0x0

    :try_start_0
    iget-object v1, p0, Lokhttp3/internal/DiskLruCache$3;->this$0:Lokhttp3/internal/DiskLruCache;

    iget-object v2, p0, Lokhttp3/internal/DiskLruCache$3;->removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    invoke-static {v2}, Lokhttp3/internal/DiskLruCache$Snapshot;->access$2400(Lokhttp3/internal/DiskLruCache$Snapshot;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lokhttp3/internal/DiskLruCache;->remove(Ljava/lang/String;)Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 774
    :catchall_0
    move-exception v1

    iput-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    throw v1

    .line 770
    :catch_0
    move-exception v1

    .line 774
    :goto_0
    iput-object v0, p0, Lokhttp3/internal/DiskLruCache$3;->removeSnapshot:Lokhttp3/internal/DiskLruCache$Snapshot;

    .line 775
    nop

    .line 776
    return-void

    .line 767
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "remove() before next()"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
