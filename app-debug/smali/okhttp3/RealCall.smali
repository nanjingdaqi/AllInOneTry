.class final Lokhttp3/RealCall;
.super Ljava/lang/Object;
.source "RealCall.java"

# interfaces
.implements Lokhttp3/Call;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lokhttp3/RealCall$ApplicationInterceptorChain;,
        Lokhttp3/RealCall$AsyncCall;
    }
.end annotation


# instance fields
.field volatile canceled:Z

.field private final client:Lokhttp3/OkHttpClient;

.field engine:Lokhttp3/internal/http/HttpEngine;

.field private executed:Z

.field originalRequest:Lokhttp3/Request;


# direct methods
.method protected constructor <init>(Lokhttp3/OkHttpClient;Lokhttp3/Request;)V
    .locals 0
    .param p1, "client"    # Lokhttp3/OkHttpClient;
    .param p2, "originalRequest"    # Lokhttp3/Request;

    .line 41
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 42
    iput-object p1, p0, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    .line 43
    iput-object p2, p0, Lokhttp3/RealCall;->originalRequest:Lokhttp3/Request;

    .line 44
    return-void
.end method

.method static synthetic access$100(Lokhttp3/RealCall;Z)Lokhttp3/Response;
    .locals 1
    .param p0, "x0"    # Lokhttp3/RealCall;
    .param p1, "x1"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 30
    invoke-direct {p0, p1}, Lokhttp3/RealCall;->getResponseWithInterceptorChain(Z)Lokhttp3/Response;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$200(Lokhttp3/RealCall;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lokhttp3/RealCall;

    .line 30
    invoke-direct {p0}, Lokhttp3/RealCall;->toLoggableString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$300(Lokhttp3/RealCall;)Lokhttp3/OkHttpClient;
    .locals 1
    .param p0, "x0"    # Lokhttp3/RealCall;

    .line 30
    iget-object v0, p0, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    return-object v0
.end method

.method private getResponseWithInterceptorChain(Z)Lokhttp3/Response;
    .locals 3
    .param p1, "forWebSocket"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 162
    new-instance v0, Lokhttp3/RealCall$ApplicationInterceptorChain;

    iget-object v1, p0, Lokhttp3/RealCall;->originalRequest:Lokhttp3/Request;

    const/4 v2, 0x0

    invoke-direct {v0, p0, v2, v1, p1}, Lokhttp3/RealCall$ApplicationInterceptorChain;-><init>(Lokhttp3/RealCall;ILokhttp3/Request;Z)V

    .line 163
    .local v0, "chain":Lokhttp3/Interceptor$Chain;
    iget-object v1, p0, Lokhttp3/RealCall;->originalRequest:Lokhttp3/Request;

    invoke-interface {v0, v1}, Lokhttp3/Interceptor$Chain;->proceed(Lokhttp3/Request;)Lokhttp3/Response;

    move-result-object v1

    return-object v1
.end method

.method private toLoggableString()Ljava/lang/String;
    .locals 3

    .line 153
    iget-boolean v0, p0, Lokhttp3/RealCall;->canceled:Z

    if-eqz v0, :cond_0

    const-string v0, "canceled call"

    goto :goto_0

    :cond_0
    const-string v0, "call"

    .line 154
    .local v0, "string":Ljava/lang/String;
    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " to "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lokhttp3/RealCall;->redactedUrl()Lokhttp3/HttpUrl;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method


# virtual methods
.method public cancel()V
    .locals 1

    .line 82
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokhttp3/RealCall;->canceled:Z

    .line 83
    iget-object v0, p0, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v0}, Lokhttp3/internal/http/HttpEngine;->cancel()V

    .line 84
    :cond_0
    return-void
.end method

.method public enqueue(Lokhttp3/Callback;)V
    .locals 1
    .param p1, "responseCallback"    # Lokhttp3/Callback;

    .line 70
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lokhttp3/RealCall;->enqueue(Lokhttp3/Callback;Z)V

    .line 71
    return-void
.end method

.method enqueue(Lokhttp3/Callback;Z)V
    .locals 3
    .param p1, "responseCallback"    # Lokhttp3/Callback;
    .param p2, "forWebSocket"    # Z

    .line 74
    monitor-enter p0

    .line 75
    :try_start_0
    iget-boolean v0, p0, Lokhttp3/RealCall;->executed:Z

    if-nez v0, :cond_0

    .line 76
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokhttp3/RealCall;->executed:Z

    .line 77
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 78
    iget-object v0, p0, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v0}, Lokhttp3/OkHttpClient;->dispatcher()Lokhttp3/Dispatcher;

    move-result-object v0

    new-instance v1, Lokhttp3/RealCall$AsyncCall;

    const/4 v2, 0x0

    invoke-direct {v1, p0, p1, p2, v2}, Lokhttp3/RealCall$AsyncCall;-><init>(Lokhttp3/RealCall;Lokhttp3/Callback;ZLokhttp3/RealCall$1;)V

    invoke-virtual {v0, v1}, Lokhttp3/Dispatcher;->enqueue(Lokhttp3/RealCall$AsyncCall;)V

    .line 79
    return-void

    .line 75
    :cond_0
    :try_start_1
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Already Executed"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 77
    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public execute()Lokhttp3/Response;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 51
    monitor-enter p0

    .line 52
    :try_start_0
    iget-boolean v0, p0, Lokhttp3/RealCall;->executed:Z

    if-nez v0, :cond_1

    .line 53
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokhttp3/RealCall;->executed:Z

    .line 54
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 56
    :try_start_1
    iget-object v0, p0, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v0}, Lokhttp3/OkHttpClient;->dispatcher()Lokhttp3/Dispatcher;

    move-result-object v0

    invoke-virtual {v0, p0}, Lokhttp3/Dispatcher;->executed(Lokhttp3/RealCall;)V

    .line 57
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lokhttp3/RealCall;->getResponseWithInterceptorChain(Z)Lokhttp3/Response;

    move-result-object v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 58
    .local v0, "result":Lokhttp3/Response;
    if-eqz v0, :cond_0

    .line 59
    nop

    .line 61
    iget-object v1, p0, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v1}, Lokhttp3/OkHttpClient;->dispatcher()Lokhttp3/Dispatcher;

    move-result-object v1

    invoke-virtual {v1, p0}, Lokhttp3/Dispatcher;->finished(Lokhttp3/Call;)V

    .line 59
    return-object v0

    .line 58
    :cond_0
    :try_start_2
    new-instance v1, Ljava/io/IOException;

    const-string v2, "Canceled"

    invoke-direct {v1, v2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 61
    .end local v0    # "result":Lokhttp3/Response;
    :catchall_0
    move-exception v0

    iget-object v1, p0, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v1}, Lokhttp3/OkHttpClient;->dispatcher()Lokhttp3/Dispatcher;

    move-result-object v1

    invoke-virtual {v1, p0}, Lokhttp3/Dispatcher;->finished(Lokhttp3/Call;)V

    throw v0

    .line 52
    :cond_1
    :try_start_3
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Already Executed"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 54
    :catchall_1
    move-exception v0

    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw v0
.end method

.method getResponse(Lokhttp3/Request;Z)Lokhttp3/Response;
    .locals 18
    .param p1, "request"    # Lokhttp3/Request;
    .param p2, "forWebSocket"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 210
    move-object/from16 v1, p0

    invoke-virtual/range {p1 .. p1}, Lokhttp3/Request;->body()Lokhttp3/RequestBody;

    move-result-object v2

    .line 211
    .local v2, "body":Lokhttp3/RequestBody;
    if-eqz v2, :cond_2

    .line 212
    invoke-virtual/range {p1 .. p1}, Lokhttp3/Request;->newBuilder()Lokhttp3/Request$Builder;

    move-result-object v0

    .line 214
    .local v0, "requestBuilder":Lokhttp3/Request$Builder;
    invoke-virtual {v2}, Lokhttp3/RequestBody;->contentType()Lokhttp3/MediaType;

    move-result-object v3

    .line 215
    .local v3, "contentType":Lokhttp3/MediaType;
    if-eqz v3, :cond_0

    .line 216
    const-string v4, "Content-Type"

    invoke-virtual {v3}, Lokhttp3/MediaType;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v4, v5}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    .line 219
    :cond_0
    invoke-virtual {v2}, Lokhttp3/RequestBody;->contentLength()J

    move-result-wide v4

    .line 220
    .local v4, "contentLength":J
    const-wide/16 v6, -0x1

    cmp-long v8, v4, v6

    if-eqz v8, :cond_1

    .line 221
    const-string v6, "Content-Length"

    invoke-static {v4, v5}, Ljava/lang/Long;->toString(J)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v0, v6, v7}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    .line 222
    const-string v6, "Transfer-Encoding"

    invoke-virtual {v0, v6}, Lokhttp3/Request$Builder;->removeHeader(Ljava/lang/String;)Lokhttp3/Request$Builder;

    goto :goto_0

    .line 224
    :cond_1
    const-string v6, "Transfer-Encoding"

    const-string v7, "chunked"

    invoke-virtual {v0, v6, v7}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    .line 225
    const-string v6, "Content-Length"

    invoke-virtual {v0, v6}, Lokhttp3/Request$Builder;->removeHeader(Ljava/lang/String;)Lokhttp3/Request$Builder;

    .line 228
    :goto_0
    invoke-virtual {v0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v0

    goto :goto_1

    .line 232
    .end local v0    # "requestBuilder":Lokhttp3/Request$Builder;
    .end local v3    # "contentType":Lokhttp3/MediaType;
    .end local v4    # "contentLength":J
    :cond_2
    move-object/from16 v0, p1

    .end local p1    # "request":Lokhttp3/Request;
    .local v0, "request":Lokhttp3/Request;
    :goto_1
    new-instance v12, Lokhttp3/internal/http/HttpEngine;

    iget-object v4, v1, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object v3, v12

    move-object v5, v0

    move/from16 v8, p2

    invoke-direct/range {v3 .. v11}, Lokhttp3/internal/http/HttpEngine;-><init>(Lokhttp3/OkHttpClient;Lokhttp3/Request;ZZZLokhttp3/internal/http/StreamAllocation;Lokhttp3/internal/http/RetryableSink;Lokhttp3/Response;)V

    iput-object v12, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    .line 234
    const/4 v3, 0x0

    move-object v4, v0

    const/4 v0, 0x0

    .local v0, "followUpCount":I
    .local v4, "request":Lokhttp3/Request;
    :goto_2
    move v5, v0

    .line 236
    .end local v0    # "followUpCount":I
    .local v5, "followUpCount":I
    iget-boolean v0, v1, Lokhttp3/RealCall;->canceled:Z

    if-nez v0, :cond_d

    .line 241
    const/4 v6, 0x1

    move v7, v6

    .line 243
    .local v7, "releaseConnection":Z
    const/4 v8, 0x0

    :try_start_0
    iget-object v0, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v0}, Lokhttp3/internal/http/HttpEngine;->sendRequest()V

    .line 244
    iget-object v0, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v0}, Lokhttp3/internal/http/HttpEngine;->readResponse()V
    :try_end_0
    .catch Lokhttp3/internal/http/RequestException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lokhttp3/internal/http/RouteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 245
    const/4 v0, 0x0

    .line 272
    .end local v7    # "releaseConnection":Z
    .local v0, "releaseConnection":Z
    if-eqz v0, :cond_3

    .line 273
    iget-object v6, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v6}, Lokhttp3/internal/http/HttpEngine;->close()Lokhttp3/internal/http/StreamAllocation;

    move-result-object v6

    .line 274
    .local v6, "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    invoke-virtual {v6}, Lokhttp3/internal/http/StreamAllocation;->release()V

    .line 275
    .end local v6    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    nop

    .line 278
    :cond_3
    iget-object v6, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v6}, Lokhttp3/internal/http/HttpEngine;->getResponse()Lokhttp3/Response;

    move-result-object v6

    .line 279
    .local v6, "response":Lokhttp3/Response;
    iget-object v7, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v7}, Lokhttp3/internal/http/HttpEngine;->followUpRequest()Lokhttp3/Request;

    move-result-object v16

    .line 281
    .local v16, "followUp":Lokhttp3/Request;
    if-nez v16, :cond_5

    .line 282
    if-nez p2, :cond_4

    .line 283
    iget-object v3, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v3}, Lokhttp3/internal/http/HttpEngine;->releaseStreamAllocation()V

    .line 285
    :cond_4
    return-object v6

    .line 288
    :cond_5
    iget-object v7, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v7}, Lokhttp3/internal/http/HttpEngine;->close()Lokhttp3/internal/http/StreamAllocation;

    move-result-object v7

    .line 290
    .local v7, "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    add-int/lit8 v5, v5, 0x1

    const/16 v8, 0x14

    if-gt v5, v8, :cond_8

    .line 295
    iget-object v8, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual/range {v16 .. v16}, Lokhttp3/Request;->url()Lokhttp3/HttpUrl;

    move-result-object v9

    invoke-virtual {v8, v9}, Lokhttp3/internal/http/HttpEngine;->sameConnection(Lokhttp3/HttpUrl;)Z

    move-result v8

    if-nez v8, :cond_6

    .line 296
    invoke-virtual {v7}, Lokhttp3/internal/http/StreamAllocation;->release()V

    .line 297
    const/4 v7, 0x0

    .line 303
    .end local v7    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    .local v17, "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    :goto_3
    move-object/from16 v17, v7

    goto :goto_4

    .line 298
    .end local v17    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    .restart local v7    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    :cond_6
    invoke-virtual {v7}, Lokhttp3/internal/http/StreamAllocation;->stream()Lokhttp3/internal/http/HttpStream;

    move-result-object v8

    if-nez v8, :cond_7

    goto :goto_3

    .line 303
    .end local v7    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    .restart local v17    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    :goto_4
    move-object/from16 v9, v16

    .line 304
    .end local v4    # "request":Lokhttp3/Request;
    .local v9, "request":Lokhttp3/Request;
    new-instance v4, Lokhttp3/internal/http/HttpEngine;

    iget-object v8, v1, Lokhttp3/RealCall;->client:Lokhttp3/OkHttpClient;

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v14, 0x0

    move-object v7, v4

    move/from16 v12, p2

    move-object/from16 v13, v17

    move-object v15, v6

    invoke-direct/range {v7 .. v15}, Lokhttp3/internal/http/HttpEngine;-><init>(Lokhttp3/OkHttpClient;Lokhttp3/Request;ZZZLokhttp3/internal/http/StreamAllocation;Lokhttp3/internal/http/RetryableSink;Lokhttp3/Response;)V

    iput-object v4, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    .line 306
    .end local v0    # "releaseConnection":Z
    .end local v6    # "response":Lokhttp3/Response;
    .end local v16    # "followUp":Lokhttp3/Request;
    .end local v17    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    nop

    .line 234
    move v0, v5

    move-object v4, v9

    goto :goto_2

    .line 299
    .end local v9    # "request":Lokhttp3/Request;
    .restart local v0    # "releaseConnection":Z
    .restart local v4    # "request":Lokhttp3/Request;
    .restart local v6    # "response":Lokhttp3/Response;
    .restart local v7    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    .restart local v16    # "followUp":Lokhttp3/Request;
    :cond_7
    new-instance v3, Ljava/lang/IllegalStateException;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "Closing the body of "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v9, " didn\'t close its backing stream. Bad interceptor?"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-direct {v3, v8}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v3

    .line 291
    :cond_8
    invoke-virtual {v7}, Lokhttp3/internal/http/StreamAllocation;->release()V

    .line 292
    new-instance v3, Ljava/net/ProtocolException;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "Too many follow-up requests: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-direct {v3, v8}, Ljava/net/ProtocolException;-><init>(Ljava/lang/String;)V

    throw v3

    .line 272
    .end local v0    # "releaseConnection":Z
    .end local v6    # "response":Lokhttp3/Response;
    .end local v16    # "followUp":Lokhttp3/Request;
    .local v7, "releaseConnection":Z
    :catchall_0
    move-exception v0

    goto :goto_7

    .line 259
    :catch_0
    move-exception v0

    .line 261
    .local v0, "e":Ljava/io/IOException;
    :try_start_1
    iget-object v6, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v6, v0, v3, v8}, Lokhttp3/internal/http/HttpEngine;->recover(Ljava/io/IOException;ZLokio/Sink;)Lokhttp3/internal/http/HttpEngine;

    move-result-object v6

    .line 262
    .local v6, "retryEngine":Lokhttp3/internal/http/HttpEngine;
    if-eqz v6, :cond_9

    .line 263
    const/4 v7, 0x0

    .line 264
    iput-object v6, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 272
    if-eqz v7, :cond_a

    .line 273
    :goto_5
    iget-object v8, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v8}, Lokhttp3/internal/http/HttpEngine;->close()Lokhttp3/internal/http/StreamAllocation;

    move-result-object v8

    .line 274
    .local v8, "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    invoke-virtual {v8}, Lokhttp3/internal/http/StreamAllocation;->release()V

    .line 275
    .end local v8    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    goto :goto_6

    .line 269
    :cond_9
    :try_start_2
    throw v0

    .line 249
    .end local v0    # "e":Ljava/io/IOException;
    .end local v6    # "retryEngine":Lokhttp3/internal/http/HttpEngine;
    :catch_1
    move-exception v0

    .line 251
    .local v0, "e":Lokhttp3/internal/http/RouteException;
    iget-object v9, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v0}, Lokhttp3/internal/http/RouteException;->getLastConnectException()Ljava/io/IOException;

    move-result-object v10

    invoke-virtual {v9, v10, v6, v8}, Lokhttp3/internal/http/HttpEngine;->recover(Ljava/io/IOException;ZLokio/Sink;)Lokhttp3/internal/http/HttpEngine;

    move-result-object v6

    .line 252
    .restart local v6    # "retryEngine":Lokhttp3/internal/http/HttpEngine;
    if-eqz v6, :cond_b

    .line 253
    const/4 v7, 0x0

    .line 254
    iput-object v6, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    .line 272
    if-eqz v7, :cond_a

    goto :goto_5

    .line 234
    .end local v0    # "e":Lokhttp3/internal/http/RouteException;
    .end local v6    # "retryEngine":Lokhttp3/internal/http/HttpEngine;
    .end local v7    # "releaseConnection":Z
    :cond_a
    :goto_6
    move v0, v5

    goto/16 :goto_2

    .line 258
    .restart local v0    # "e":Lokhttp3/internal/http/RouteException;
    .restart local v6    # "retryEngine":Lokhttp3/internal/http/HttpEngine;
    .restart local v7    # "releaseConnection":Z
    :cond_b
    invoke-virtual {v0}, Lokhttp3/internal/http/RouteException;->getLastConnectException()Ljava/io/IOException;

    move-result-object v3

    throw v3

    .line 246
    .end local v0    # "e":Lokhttp3/internal/http/RouteException;
    .end local v6    # "retryEngine":Lokhttp3/internal/http/HttpEngine;
    :catch_2
    move-exception v0

    .line 248
    .local v0, "e":Lokhttp3/internal/http/RequestException;
    invoke-virtual {v0}, Lokhttp3/internal/http/RequestException;->getCause()Ljava/io/IOException;

    move-result-object v3

    throw v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 272
    .end local v0    # "e":Lokhttp3/internal/http/RequestException;
    :goto_7
    if-eqz v7, :cond_c

    .line 273
    iget-object v3, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v3}, Lokhttp3/internal/http/HttpEngine;->close()Lokhttp3/internal/http/StreamAllocation;

    move-result-object v3

    .line 274
    .local v3, "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    invoke-virtual {v3}, Lokhttp3/internal/http/StreamAllocation;->release()V

    .line 275
    .end local v3    # "streamAllocation":Lokhttp3/internal/http/StreamAllocation;
    :cond_c
    throw v0

    .line 237
    .end local v7    # "releaseConnection":Z
    :cond_d
    iget-object v0, v1, Lokhttp3/RealCall;->engine:Lokhttp3/internal/http/HttpEngine;

    invoke-virtual {v0}, Lokhttp3/internal/http/HttpEngine;->releaseStreamAllocation()V

    .line 238
    new-instance v0, Ljava/io/IOException;

    const-string v3, "Canceled"

    invoke-direct {v0, v3}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public isCanceled()Z
    .locals 1

    .line 91
    iget-boolean v0, p0, Lokhttp3/RealCall;->canceled:Z

    return v0
.end method

.method public declared-synchronized isExecuted()Z
    .locals 1

    monitor-enter p0

    .line 87
    :try_start_0
    iget-boolean v0, p0, Lokhttp3/RealCall;->executed:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return v0

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method redactedUrl()Lokhttp3/HttpUrl;
    .locals 2

    .line 158
    iget-object v0, p0, Lokhttp3/RealCall;->originalRequest:Lokhttp3/Request;

    invoke-virtual {v0}, Lokhttp3/Request;->url()Lokhttp3/HttpUrl;

    move-result-object v0

    const-string v1, "/..."

    invoke-virtual {v0, v1}, Lokhttp3/HttpUrl;->resolve(Ljava/lang/String;)Lokhttp3/HttpUrl;

    move-result-object v0

    return-object v0
.end method

.method public request()Lokhttp3/Request;
    .locals 1

    .line 47
    iget-object v0, p0, Lokhttp3/RealCall;->originalRequest:Lokhttp3/Request;

    return-object v0
.end method

.method tag()Ljava/lang/Object;
    .locals 1

    .line 66
    iget-object v0, p0, Lokhttp3/RealCall;->originalRequest:Lokhttp3/Request;

    invoke-virtual {v0}, Lokhttp3/Request;->tag()Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method
