.class final Lretrofit2/ParameterHandler$QueryMap;
.super Lretrofit2/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit2/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "QueryMap"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lretrofit2/ParameterHandler<",
        "Ljava/util/Map<",
        "Ljava/lang/String;",
        "TT;>;>;"
    }
.end annotation


# instance fields
.field private final encoded:Z

.field private final valueConverter:Lretrofit2/Converter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit2/Converter<",
            "TT;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lretrofit2/Converter;Z)V
    .locals 0
    .param p2, "encoded"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit2/Converter<",
            "TT;",
            "Ljava/lang/String;",
            ">;Z)V"
        }
    .end annotation

    .line 117
    .local p0, "this":Lretrofit2/ParameterHandler$QueryMap;, "Lretrofit2/ParameterHandler$QueryMap<TT;>;"
    .local p1, "valueConverter":Lretrofit2/Converter;, "Lretrofit2/Converter<TT;Ljava/lang/String;>;"
    invoke-direct {p0}, Lretrofit2/ParameterHandler;-><init>()V

    .line 118
    iput-object p1, p0, Lretrofit2/ParameterHandler$QueryMap;->valueConverter:Lretrofit2/Converter;

    .line 119
    iput-boolean p2, p0, Lretrofit2/ParameterHandler$QueryMap;->encoded:Z

    .line 120
    return-void
.end method


# virtual methods
.method bridge synthetic apply(Lretrofit2/RequestBuilder;Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 113
    .local p0, "this":Lretrofit2/ParameterHandler$QueryMap;, "Lretrofit2/ParameterHandler$QueryMap<TT;>;"
    check-cast p2, Ljava/util/Map;

    invoke-virtual {p0, p1, p2}, Lretrofit2/ParameterHandler$QueryMap;->apply(Lretrofit2/RequestBuilder;Ljava/util/Map;)V

    return-void
.end method

.method apply(Lretrofit2/RequestBuilder;Ljava/util/Map;)V
    .locals 6
    .param p1, "builder"    # Lretrofit2/RequestBuilder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit2/RequestBuilder;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "TT;>;)V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 123
    .local p0, "this":Lretrofit2/ParameterHandler$QueryMap;, "Lretrofit2/ParameterHandler$QueryMap<TT;>;"
    .local p2, "value":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;TT;>;"
    if-eqz p2, :cond_3

    .line 127
    invoke-interface {p2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 128
    .local v1, "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;TT;>;"
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 129
    .local v2, "entryKey":Ljava/lang/String;
    if-eqz v2, :cond_1

    .line 132
    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    .line 133
    .local v3, "entryValue":Ljava/lang/Object;, "TT;"
    if-eqz v3, :cond_0

    .line 137
    iget-object v4, p0, Lretrofit2/ParameterHandler$QueryMap;->valueConverter:Lretrofit2/Converter;

    invoke-interface {v4, v3}, Lretrofit2/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    iget-boolean v5, p0, Lretrofit2/ParameterHandler$QueryMap;->encoded:Z

    invoke-virtual {p1, v2, v4, v5}, Lretrofit2/RequestBuilder;->addQueryParam(Ljava/lang/String;Ljava/lang/String;Z)V

    .line 138
    .end local v1    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;TT;>;"
    .end local v2    # "entryKey":Ljava/lang/String;
    .end local v3    # "entryValue":Ljava/lang/Object;, "TT;"
    goto :goto_0

    .line 134
    .restart local v1    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;TT;>;"
    .restart local v2    # "entryKey":Ljava/lang/String;
    .restart local v3    # "entryValue":Ljava/lang/Object;, "TT;"
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Query map contained null value for key \'"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "\'."

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v0, v4}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 130
    .end local v3    # "entryValue":Ljava/lang/Object;, "TT;"
    :cond_1
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v3, "Query map contained null key."

    invoke-direct {v0, v3}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 139
    .end local v1    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;TT;>;"
    .end local v2    # "entryKey":Ljava/lang/String;
    :cond_2
    return-void

    .line 124
    :cond_3
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "Query map was null."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method