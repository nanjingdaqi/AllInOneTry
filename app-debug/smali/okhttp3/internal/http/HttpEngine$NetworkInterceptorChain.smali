.class Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;
.super Ljava/lang/Object;
.source "HttpEngine.java"

# interfaces
.implements Lokhttp3/Interceptor$Chain;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp3/internal/http/HttpEngine;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "NetworkInterceptorChain"
.end annotation


# instance fields
.field private calls:I

.field private final connection:Lokhttp3/Connection;

.field private final index:I

.field private final request:Lokhttp3/Request;

.field final synthetic this$0:Lokhttp3/internal/http/HttpEngine;


# direct methods
.method constructor <init>(Lokhttp3/internal/http/HttpEngine;ILokhttp3/Request;Lokhttp3/Connection;)V
    .locals 0
    .param p1, "this$0"    # Lokhttp3/internal/http/HttpEngine;
    .param p2, "index"    # I
    .param p3, "request"    # Lokhttp3/Request;
    .param p4, "connection"    # Lokhttp3/Connection;

    .line 694
    iput-object p1, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 695
    iput p2, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->index:I

    .line 696
    iput-object p3, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->request:Lokhttp3/Request;

    .line 697
    iput-object p4, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->connection:Lokhttp3/Connection;

    .line 698
    return-void
.end method


# virtual methods
.method public connection()Lokhttp3/Connection;
    .locals 1

    .line 701
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->connection:Lokhttp3/Connection;

    return-object v0
.end method

.method public proceed(Lokhttp3/Request;)Lokhttp3/Response;
    .locals 7
    .param p1, "request"    # Lokhttp3/Request;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 709
    iget v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->calls:I

    const/4 v1, 0x1

    add-int/2addr v0, v1

    iput v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->calls:I

    .line 711
    iget v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->index:I

    if-lez v0, :cond_2

    .line 712
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    iget-object v0, v0, Lokhttp3/internal/http/HttpEngine;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v0}, Lokhttp3/OkHttpClient;->networkInterceptors()Ljava/util/List;

    move-result-object v0

    iget v2, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->index:I

    sub-int/2addr v2, v1

    invoke-interface {v0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lokhttp3/Interceptor;

    .line 713
    .local v0, "caller":Lokhttp3/Interceptor;
    invoke-virtual {p0}, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->connection()Lokhttp3/Connection;

    move-result-object v2

    invoke-interface {v2}, Lokhttp3/Connection;->route()Lokhttp3/Route;

    move-result-object v2

    invoke-virtual {v2}, Lokhttp3/Route;->address()Lokhttp3/Address;

    move-result-object v2

    .line 716
    .local v2, "address":Lokhttp3/Address;
    invoke-virtual {p1}, Lokhttp3/Request;->url()Lokhttp3/HttpUrl;

    move-result-object v3

    invoke-virtual {v3}, Lokhttp3/HttpUrl;->host()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2}, Lokhttp3/Address;->url()Lokhttp3/HttpUrl;

    move-result-object v4

    invoke-virtual {v4}, Lokhttp3/HttpUrl;->host()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 717
    invoke-virtual {p1}, Lokhttp3/Request;->url()Lokhttp3/HttpUrl;

    move-result-object v3

    invoke-virtual {v3}, Lokhttp3/HttpUrl;->port()I

    move-result v3

    invoke-virtual {v2}, Lokhttp3/Address;->url()Lokhttp3/HttpUrl;

    move-result-object v4

    invoke-virtual {v4}, Lokhttp3/HttpUrl;->port()I

    move-result v4

    if-ne v3, v4, :cond_1

    .line 723
    iget v3, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->calls:I

    if-gt v3, v1, :cond_0

    .end local v0    # "caller":Lokhttp3/Interceptor;
    .end local v2    # "address":Lokhttp3/Address;
    goto :goto_0

    .line 724
    .restart local v0    # "caller":Lokhttp3/Interceptor;
    .restart local v2    # "address":Lokhttp3/Address;
    :cond_0
    new-instance v1, Ljava/lang/IllegalStateException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "network interceptor "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v4, " must call proceed() exactly once"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 718
    :cond_1
    new-instance v1, Ljava/lang/IllegalStateException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "network interceptor "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v4, " must retain the same host and port"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 729
    .end local v0    # "caller":Lokhttp3/Interceptor;
    .end local v2    # "address":Lokhttp3/Address;
    :cond_2
    :goto_0
    iget v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->index:I

    iget-object v2, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    iget-object v2, v2, Lokhttp3/internal/http/HttpEngine;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v2}, Lokhttp3/OkHttpClient;->networkInterceptors()Ljava/util/List;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    if-ge v0, v2, :cond_5

    .line 731
    new-instance v0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;

    iget-object v2, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    iget v3, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->index:I

    add-int/2addr v3, v1

    iget-object v4, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->connection:Lokhttp3/Connection;

    invoke-direct {v0, v2, v3, p1, v4}, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;-><init>(Lokhttp3/internal/http/HttpEngine;ILokhttp3/Request;Lokhttp3/Connection;)V

    .line 732
    .local v0, "chain":Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;
    iget-object v2, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    iget-object v2, v2, Lokhttp3/internal/http/HttpEngine;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v2}, Lokhttp3/OkHttpClient;->networkInterceptors()Ljava/util/List;

    move-result-object v2

    iget v3, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->index:I

    invoke-interface {v2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lokhttp3/Interceptor;

    .line 733
    .local v2, "interceptor":Lokhttp3/Interceptor;
    invoke-interface {v2, v0}, Lokhttp3/Interceptor;->intercept(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;

    move-result-object v3

    .line 736
    .local v3, "interceptedResponse":Lokhttp3/Response;
    iget v4, v0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->calls:I

    if-ne v4, v1, :cond_4

    .line 740
    if-eqz v3, :cond_3

    .line 745
    return-object v3

    .line 741
    :cond_3
    new-instance v1, Ljava/lang/NullPointerException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "network interceptor "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v5, " returned null"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v1, v4}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 737
    :cond_4
    new-instance v1, Ljava/lang/IllegalStateException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "network interceptor "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v5, " must call proceed() exactly once"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v1, v4}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 748
    .end local v0    # "chain":Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;
    .end local v2    # "interceptor":Lokhttp3/Interceptor;
    .end local v3    # "interceptedResponse":Lokhttp3/Response;
    :cond_5
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    invoke-static {v0}, Lokhttp3/internal/http/HttpEngine;->access$000(Lokhttp3/internal/http/HttpEngine;)Lokhttp3/internal/http/HttpStream;

    move-result-object v0

    invoke-interface {v0, p1}, Lokhttp3/internal/http/HttpStream;->writeRequestHeaders(Lokhttp3/Request;)V

    .line 751
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    invoke-static {v0, p1}, Lokhttp3/internal/http/HttpEngine;->access$102(Lokhttp3/internal/http/HttpEngine;Lokhttp3/Request;)Lokhttp3/Request;

    .line 753
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v0, p1}, Lokhttp3/internal/http/HttpEngine;->permitsRequestBody(Lokhttp3/Request;)Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-virtual {p1}, Lokhttp3/Request;->body()Lokhttp3/RequestBody;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 754
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    invoke-static {v0}, Lokhttp3/internal/http/HttpEngine;->access$000(Lokhttp3/internal/http/HttpEngine;)Lokhttp3/internal/http/HttpStream;

    move-result-object v0

    invoke-virtual {p1}, Lokhttp3/Request;->body()Lokhttp3/RequestBody;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp3/RequestBody;->contentLength()J

    move-result-wide v1

    invoke-interface {v0, p1, v1, v2}, Lokhttp3/internal/http/HttpStream;->createRequestBody(Lokhttp3/Request;J)Lokio/Sink;

    move-result-object v0

    .line 755
    .local v0, "requestBodyOut":Lokio/Sink;
    invoke-static {v0}, Lokio/Okio;->buffer(Lokio/Sink;)Lokio/BufferedSink;

    move-result-object v1

    .line 756
    .local v1, "bufferedRequestBody":Lokio/BufferedSink;
    invoke-virtual {p1}, Lokhttp3/Request;->body()Lokhttp3/RequestBody;

    move-result-object v2

    invoke-virtual {v2, v1}, Lokhttp3/RequestBody;->writeTo(Lokio/BufferedSink;)V

    .line 757
    invoke-interface {v1}, Lokio/BufferedSink;->close()V

    .line 760
    .end local v0    # "requestBodyOut":Lokio/Sink;
    .end local v1    # "bufferedRequestBody":Lokio/BufferedSink;
    :cond_6
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->this$0:Lokhttp3/internal/http/HttpEngine;

    invoke-static {v0}, Lokhttp3/internal/http/HttpEngine;->access$200(Lokhttp3/internal/http/HttpEngine;)Lokhttp3/Response;

    move-result-object v0

    .line 762
    .local v0, "response":Lokhttp3/Response;
    invoke-virtual {v0}, Lokhttp3/Response;->code()I

    move-result v1

    .line 763
    .local v1, "code":I
    const/16 v2, 0xcc

    if-eq v1, v2, :cond_7

    const/16 v2, 0xcd

    if-ne v1, v2, :cond_8

    :cond_7
    invoke-virtual {v0}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v2

    invoke-virtual {v2}, Lokhttp3/ResponseBody;->contentLength()J

    move-result-wide v2

    const-wide/16 v4, 0x0

    cmp-long v6, v2, v4

    if-gtz v6, :cond_9

    .line 768
    :cond_8
    return-object v0

    .line 764
    :cond_9
    new-instance v2, Ljava/net/ProtocolException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "HTTP "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, " had non-zero Content-Length: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 765
    invoke-virtual {v0}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v4

    invoke-virtual {v4}, Lokhttp3/ResponseBody;->contentLength()J

    move-result-wide v4

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/net/ProtocolException;-><init>(Ljava/lang/String;)V

    throw v2
.end method

.method public request()Lokhttp3/Request;
    .locals 1

    .line 705
    iget-object v0, p0, Lokhttp3/internal/http/HttpEngine$NetworkInterceptorChain;->request:Lokhttp3/Request;

    return-object v0
.end method
