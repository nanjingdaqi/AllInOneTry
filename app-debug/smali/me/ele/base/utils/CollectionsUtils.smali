.class public Lme/ele/base/utils/CollectionsUtils;
.super Ljava/lang/Object;
.source "CollectionsUtils.java"


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 10
    return-void
.end method

.method public static clear(Ljava/util/Collection;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "*>;)V"
        }
    .end annotation

    .line 49
    .local p0, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    invoke-static {p0}, Lme/ele/base/utils/CollectionsUtils;->isEmpty(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 50
    return-void

    .line 52
    :cond_0
    invoke-interface {p0}, Ljava/util/Collection;->clear()V

    .line 53
    return-void
.end method

.method public static clear(Ljava/util/Map;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "**>;)V"
        }
    .end annotation

    .line 56
    .local p0, "map":Ljava/util/Map;, "Ljava/util/Map<**>;"
    invoke-static {p0}, Lme/ele/base/utils/CollectionsUtils;->isEmpty(Ljava/util/Map;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 57
    return-void

    .line 59
    :cond_0
    invoke-interface {p0}, Ljava/util/Map;->clear()V

    .line 60
    return-void
.end method

.method public static isEmpty(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "*>;)Z"
        }
    .end annotation

    .line 13
    .local p0, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    if-eqz p0, :cond_1

    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 16
    :cond_0
    const/4 v0, 0x0

    return v0

    .line 14
    :cond_1
    :goto_0
    const/4 v0, 0x1

    return v0
.end method

.method public static isEmpty(Ljava/util/Map;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "**>;)Z"
        }
    .end annotation

    .line 20
    .local p0, "map":Ljava/util/Map;, "Ljava/util/Map<**>;"
    if-eqz p0, :cond_1

    invoke-interface {p0}, Ljava/util/Map;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 23
    :cond_0
    const/4 v0, 0x0

    return v0

    .line 21
    :cond_1
    :goto_0
    const/4 v0, 0x1

    return v0
.end method

.method public static isNotEmpty(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "*>;)Z"
        }
    .end annotation

    .line 27
    .local p0, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    invoke-static {p0}, Lme/ele/base/utils/CollectionsUtils;->isEmpty(Ljava/util/Collection;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public static isNotEmpty(Ljava/util/Map;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "**>;)Z"
        }
    .end annotation

    .line 31
    .local p0, "map":Ljava/util/Map;, "Ljava/util/Map<**>;"
    invoke-static {p0}, Lme/ele/base/utils/CollectionsUtils;->isEmpty(Ljava/util/Map;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public static size(Ljava/util/Collection;)I
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "*>;)I"
        }
    .end annotation

    .line 35
    .local p0, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    invoke-static {p0}, Lme/ele/base/utils/CollectionsUtils;->isEmpty(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 36
    const/4 v0, 0x0

    return v0

    .line 38
    :cond_0
    invoke-interface {p0}, Ljava/util/Collection;->size()I

    move-result v0

    return v0
.end method

.method public static size(Ljava/util/Map;)I
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "**>;)I"
        }
    .end annotation

    .line 42
    .local p0, "map":Ljava/util/Map;, "Ljava/util/Map<**>;"
    invoke-static {p0}, Lme/ele/base/utils/CollectionsUtils;->isEmpty(Ljava/util/Map;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 43
    const/4 v0, 0x0

    return v0

    .line 45
    :cond_0
    invoke-interface {p0}, Ljava/util/Map;->size()I

    move-result v0

    return v0
.end method
