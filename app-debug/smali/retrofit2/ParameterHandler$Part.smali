.class final Lretrofit2/ParameterHandler$Part;
.super Lretrofit2/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit2/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Part"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lretrofit2/ParameterHandler<",
        "TT;>;"
    }
.end annotation


# instance fields
.field private final converter:Lretrofit2/Converter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit2/Converter<",
            "TT;",
            "Lokhttp3/RequestBody;",
            ">;"
        }
    .end annotation
.end field

.field private final headers:Lokhttp3/Headers;


# direct methods
.method constructor <init>(Lokhttp3/Headers;Lretrofit2/Converter;)V
    .locals 0
    .param p1, "headers"    # Lokhttp3/Headers;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp3/Headers;",
            "Lretrofit2/Converter<",
            "TT;",
            "Lokhttp3/RequestBody;",
            ">;)V"
        }
    .end annotation

    .line 219
    .local p0, "this":Lretrofit2/ParameterHandler$Part;, "Lretrofit2/ParameterHandler$Part<TT;>;"
    .local p2, "converter":Lretrofit2/Converter;, "Lretrofit2/Converter<TT;Lokhttp3/RequestBody;>;"
    invoke-direct {p0}, Lretrofit2/ParameterHandler;-><init>()V

    .line 220
    iput-object p1, p0, Lretrofit2/ParameterHandler$Part;->headers:Lokhttp3/Headers;

    .line 221
    iput-object p2, p0, Lretrofit2/ParameterHandler$Part;->converter:Lretrofit2/Converter;

    .line 222
    return-void
.end method


# virtual methods
.method apply(Lretrofit2/RequestBuilder;Ljava/lang/Object;)V
    .locals 4
    .param p1, "builder"    # Lretrofit2/RequestBuilder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit2/RequestBuilder;",
            "TT;)V"
        }
    .end annotation

    .line 225
    .local p0, "this":Lretrofit2/ParameterHandler$Part;, "Lretrofit2/ParameterHandler$Part<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    if-nez p2, :cond_0

    return-void

    .line 229
    :cond_0
    :try_start_0
    iget-object v0, p0, Lretrofit2/ParameterHandler$Part;->converter:Lretrofit2/Converter;

    invoke-interface {v0, p2}, Lretrofit2/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lokhttp3/RequestBody;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 232
    .local v0, "body":Lokhttp3/RequestBody;
    nop

    .line 231
    nop

    .line 233
    iget-object v1, p0, Lretrofit2/ParameterHandler$Part;->headers:Lokhttp3/Headers;

    invoke-virtual {p1, v1, v0}, Lretrofit2/RequestBuilder;->addPart(Lokhttp3/Headers;Lokhttp3/RequestBody;)V

    .line 234
    return-void

    .line 230
    .end local v0    # "body":Lokhttp3/RequestBody;
    :catch_0
    move-exception v0

    .line 231
    .local v0, "e":Ljava/io/IOException;
    new-instance v1, Ljava/lang/RuntimeException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unable to convert "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, " to RequestBody"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
.end method