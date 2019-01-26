.class final Lbutterknife/internal/ImmutableList;
.super Ljava/util/AbstractList;
.source "ImmutableList.java"

# interfaces
.implements Ljava/util/RandomAccess;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/util/AbstractList<",
        "TT;>;",
        "Ljava/util/RandomAccess;"
    }
.end annotation


# instance fields
.field private final views:[Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "[TT;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>([Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([TT;)V"
        }
    .end annotation

    .line 13
    .local p0, "this":Lbutterknife/internal/ImmutableList;, "Lbutterknife/internal/ImmutableList<TT;>;"
    .local p1, "views":[Ljava/lang/Object;, "[TT;"
    invoke-direct {p0}, Ljava/util/AbstractList;-><init>()V

    .line 14
    iput-object p1, p0, Lbutterknife/internal/ImmutableList;->views:[Ljava/lang/Object;

    .line 15
    return-void
.end method


# virtual methods
.method public contains(Ljava/lang/Object;)Z
    .locals 5
    .param p1, "o"    # Ljava/lang/Object;

    .line 26
    .local p0, "this":Lbutterknife/internal/ImmutableList;, "Lbutterknife/internal/ImmutableList<TT;>;"
    iget-object v0, p0, Lbutterknife/internal/ImmutableList;->views:[Ljava/lang/Object;

    array-length v1, v0

    const/4 v2, 0x0

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v1, :cond_1

    aget-object v4, v0, v3

    .line 27
    .local v4, "view":Ljava/lang/Object;, "TT;"
    if-ne v4, p1, :cond_0

    .line 28
    const/4 v0, 0x1

    return v0

    .line 26
    .end local v4    # "view":Ljava/lang/Object;, "TT;"
    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 31
    :cond_1
    return v2
.end method

.method public get(I)Ljava/lang/Object;
    .locals 1
    .param p1, "index"    # I
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TT;"
        }
    .end annotation

    .line 18
    .local p0, "this":Lbutterknife/internal/ImmutableList;, "Lbutterknife/internal/ImmutableList<TT;>;"
    iget-object v0, p0, Lbutterknife/internal/ImmutableList;->views:[Ljava/lang/Object;

    aget-object v0, v0, p1

    return-object v0
.end method

.method public size()I
    .locals 1

    .line 22
    .local p0, "this":Lbutterknife/internal/ImmutableList;, "Lbutterknife/internal/ImmutableList<TT;>;"
    iget-object v0, p0, Lbutterknife/internal/ImmutableList;->views:[Ljava/lang/Object;

    array-length v0, v0

    return v0
.end method
