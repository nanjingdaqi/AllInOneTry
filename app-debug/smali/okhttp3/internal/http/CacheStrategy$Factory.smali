.class public Lokhttp3/internal/http/CacheStrategy$Factory;
.super Ljava/lang/Object;
.source "CacheStrategy.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp3/internal/http/CacheStrategy;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Factory"
.end annotation


# instance fields
.field private ageSeconds:I

.field final cacheResponse:Lokhttp3/Response;

.field private etag:Ljava/lang/String;

.field private expires:Ljava/util/Date;

.field private lastModified:Ljava/util/Date;

.field private lastModifiedString:Ljava/lang/String;

.field final nowMillis:J

.field private receivedResponseMillis:J

.field final request:Lokhttp3/Request;

.field private sentRequestMillis:J

.field private servedDate:Ljava/util/Date;

.field private servedDateString:Ljava/lang/String;


# direct methods
.method public constructor <init>(JLokhttp3/Request;Lokhttp3/Response;)V
    .locals 7
    .param p1, "nowMillis"    # J
    .param p3, "request"    # Lokhttp3/Request;
    .param p4, "cacheResponse"    # Lokhttp3/Response;

    .line 135
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 133
    const/4 v0, -0x1

    iput v0, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->ageSeconds:I

    .line 136
    iput-wide p1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->nowMillis:J

    .line 137
    iput-object p3, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    .line 138
    iput-object p4, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    .line 140
    if-eqz p4, :cond_5

    .line 141
    invoke-virtual {p4}, Lokhttp3/Response;->sentRequestAtMillis()J

    move-result-wide v1

    iput-wide v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->sentRequestMillis:J

    .line 142
    invoke-virtual {p4}, Lokhttp3/Response;->receivedResponseAtMillis()J

    move-result-wide v1

    iput-wide v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->receivedResponseMillis:J

    .line 143
    invoke-virtual {p4}, Lokhttp3/Response;->headers()Lokhttp3/Headers;

    move-result-object v1

    .line 144
    .local v1, "headers":Lokhttp3/Headers;
    const/4 v2, 0x0

    .local v2, "i":I
    invoke-virtual {v1}, Lokhttp3/Headers;->size()I

    move-result v3

    .local v3, "size":I
    :goto_0
    if-ge v2, v3, :cond_5

    .line 145
    invoke-virtual {v1, v2}, Lokhttp3/Headers;->name(I)Ljava/lang/String;

    move-result-object v4

    .line 146
    .local v4, "fieldName":Ljava/lang/String;
    invoke-virtual {v1, v2}, Lokhttp3/Headers;->value(I)Ljava/lang/String;

    move-result-object v5

    .line 147
    .local v5, "value":Ljava/lang/String;
    const-string v6, "Date"

    invoke-virtual {v6, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_0

    .line 148
    invoke-static {v5}, Lokhttp3/internal/http/HttpDate;->parse(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v6

    iput-object v6, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    .line 149
    iput-object v5, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDateString:Ljava/lang/String;

    goto :goto_1

    .line 150
    :cond_0
    const-string v6, "Expires"

    invoke-virtual {v6, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_1

    .line 151
    invoke-static {v5}, Lokhttp3/internal/http/HttpDate;->parse(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v6

    iput-object v6, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->expires:Ljava/util/Date;

    goto :goto_1

    .line 152
    :cond_1
    const-string v6, "Last-Modified"

    invoke-virtual {v6, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_2

    .line 153
    invoke-static {v5}, Lokhttp3/internal/http/HttpDate;->parse(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v6

    iput-object v6, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->lastModified:Ljava/util/Date;

    .line 154
    iput-object v5, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->lastModifiedString:Ljava/lang/String;

    goto :goto_1

    .line 155
    :cond_2
    const-string v6, "ETag"

    invoke-virtual {v6, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_3

    .line 156
    iput-object v5, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->etag:Ljava/lang/String;

    goto :goto_1

    .line 157
    :cond_3
    const-string v6, "Age"

    invoke-virtual {v6, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_4

    .line 158
    invoke-static {v5, v0}, Lokhttp3/internal/http/HeaderParser;->parseSeconds(Ljava/lang/String;I)I

    move-result v6

    iput v6, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->ageSeconds:I

    .line 144
    .end local v4    # "fieldName":Ljava/lang/String;
    .end local v5    # "value":Ljava/lang/String;
    :cond_4
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 162
    .end local v1    # "headers":Lokhttp3/Headers;
    .end local v2    # "i":I
    .end local v3    # "size":I
    :cond_5
    return-void
.end method

.method private cacheResponseAge()J
    .locals 10

    .line 282
    iget-object v0, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    const-wide/16 v1, 0x0

    if-eqz v0, :cond_0

    iget-wide v3, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->receivedResponseMillis:J

    iget-object v0, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    .line 283
    invoke-virtual {v0}, Ljava/util/Date;->getTime()J

    move-result-wide v5

    sub-long/2addr v3, v5

    invoke-static {v1, v2, v3, v4}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v1

    nop

    :cond_0
    move-wide v0, v1

    .line 285
    .local v0, "apparentReceivedAge":J
    iget v2, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->ageSeconds:I

    const/4 v3, -0x1

    if-eq v2, v3, :cond_1

    sget-object v2, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    iget v3, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->ageSeconds:I

    int-to-long v3, v3

    .line 286
    invoke-virtual {v2, v3, v4}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide v2

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    goto :goto_0

    :cond_1
    move-wide v2, v0

    .line 288
    .local v2, "receivedAge":J
    :goto_0
    iget-wide v4, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->receivedResponseMillis:J

    iget-wide v6, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->sentRequestMillis:J

    sub-long/2addr v4, v6

    .line 289
    .local v4, "responseDuration":J
    iget-wide v6, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->nowMillis:J

    iget-wide v8, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->receivedResponseMillis:J

    sub-long/2addr v6, v8

    .line 290
    .local v6, "residentDuration":J
    add-long v8, v2, v4

    add-long/2addr v8, v6

    return-wide v8
.end method

.method private computeFreshnessLifetime()J
    .locals 8

    .line 253
    iget-object v0, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    invoke-virtual {v0}, Lokhttp3/Response;->cacheControl()Lokhttp3/CacheControl;

    move-result-object v0

    .line 254
    .local v0, "responseCaching":Lokhttp3/CacheControl;
    invoke-virtual {v0}, Lokhttp3/CacheControl;->maxAgeSeconds()I

    move-result v1

    const/4 v2, -0x1

    if-eq v1, v2, :cond_0

    .line 255
    sget-object v1, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v0}, Lokhttp3/CacheControl;->maxAgeSeconds()I

    move-result v2

    int-to-long v2, v2

    invoke-virtual {v1, v2, v3}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide v1

    return-wide v1

    .line 256
    :cond_0
    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->expires:Ljava/util/Date;

    const-wide/16 v2, 0x0

    if-eqz v1, :cond_3

    .line 257
    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    .line 258
    invoke-virtual {v1}, Ljava/util/Date;->getTime()J

    move-result-wide v4

    goto :goto_0

    :cond_1
    iget-wide v4, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->receivedResponseMillis:J

    .line 260
    .local v4, "servedMillis":J
    :goto_0
    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->expires:Ljava/util/Date;

    invoke-virtual {v1}, Ljava/util/Date;->getTime()J

    move-result-wide v6

    sub-long/2addr v6, v4

    .line 261
    .local v6, "delta":J
    cmp-long v1, v6, v2

    if-lez v1, :cond_2

    move-wide v2, v6

    nop

    :cond_2
    return-wide v2

    .line 262
    .end local v4    # "servedMillis":J
    .end local v6    # "delta":J
    :cond_3
    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->lastModified:Ljava/util/Date;

    if-eqz v1, :cond_6

    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    .line 263
    invoke-virtual {v1}, Lokhttp3/Response;->request()Lokhttp3/Request;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp3/Request;->url()Lokhttp3/HttpUrl;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp3/HttpUrl;->query()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_6

    .line 268
    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    if-eqz v1, :cond_4

    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    .line 269
    invoke-virtual {v1}, Ljava/util/Date;->getTime()J

    move-result-wide v4

    goto :goto_1

    :cond_4
    iget-wide v4, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->sentRequestMillis:J

    .line 271
    .restart local v4    # "servedMillis":J
    :goto_1
    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->lastModified:Ljava/util/Date;

    invoke-virtual {v1}, Ljava/util/Date;->getTime()J

    move-result-wide v6

    sub-long v6, v4, v6

    .line 272
    .restart local v6    # "delta":J
    cmp-long v1, v6, v2

    if-lez v1, :cond_5

    const-wide/16 v1, 0xa

    div-long v2, v6, v1

    nop

    :cond_5
    return-wide v2

    .line 274
    .end local v4    # "servedMillis":J
    .end local v6    # "delta":J
    :cond_6
    return-wide v2
.end method

.method private getCandidate()Lokhttp3/internal/http/CacheStrategy;
    .locals 18

    .line 181
    move-object/from16 v0, p0

    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    .line 182
    new-instance v1, Lokhttp3/internal/http/CacheStrategy;

    iget-object v3, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-direct {v1, v3, v2, v2}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    return-object v1

    .line 186
    :cond_0
    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-virtual {v1}, Lokhttp3/Request;->isHttps()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    invoke-virtual {v1}, Lokhttp3/Response;->handshake()Lokhttp3/Handshake;

    move-result-object v1

    if-nez v1, :cond_1

    .line 187
    new-instance v1, Lokhttp3/internal/http/CacheStrategy;

    iget-object v3, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-direct {v1, v3, v2, v2}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    return-object v1

    .line 193
    :cond_1
    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    iget-object v3, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-static {v1, v3}, Lokhttp3/internal/http/CacheStrategy;->isCacheable(Lokhttp3/Response;Lokhttp3/Request;)Z

    move-result v1

    if-nez v1, :cond_2

    .line 194
    new-instance v1, Lokhttp3/internal/http/CacheStrategy;

    iget-object v3, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-direct {v1, v3, v2, v2}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    return-object v1

    .line 197
    :cond_2
    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-virtual {v1}, Lokhttp3/Request;->cacheControl()Lokhttp3/CacheControl;

    move-result-object v1

    .line 198
    .local v1, "requestCaching":Lokhttp3/CacheControl;
    invoke-virtual {v1}, Lokhttp3/CacheControl;->noCache()Z

    move-result v3

    if-nez v3, :cond_e

    iget-object v3, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-static {v3}, Lokhttp3/internal/http/CacheStrategy$Factory;->hasConditions(Lokhttp3/Request;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 199
    move-object/from16 v17, v1

    move-object v14, v2

    goto/16 :goto_2

    .line 202
    :cond_3
    invoke-direct/range {p0 .. p0}, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponseAge()J

    move-result-wide v3

    .line 203
    .local v3, "ageMillis":J
    invoke-direct/range {p0 .. p0}, Lokhttp3/internal/http/CacheStrategy$Factory;->computeFreshnessLifetime()J

    move-result-wide v5

    .line 205
    .local v5, "freshMillis":J
    invoke-virtual {v1}, Lokhttp3/CacheControl;->maxAgeSeconds()I

    move-result v7

    const/4 v8, -0x1

    if-eq v7, v8, :cond_4

    .line 206
    sget-object v7, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v1}, Lokhttp3/CacheControl;->maxAgeSeconds()I

    move-result v9

    int-to-long v9, v9

    invoke-virtual {v7, v9, v10}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide v9

    invoke-static {v5, v6, v9, v10}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v5

    .line 209
    :cond_4
    const-wide/16 v9, 0x0

    .line 210
    .local v9, "minFreshMillis":J
    invoke-virtual {v1}, Lokhttp3/CacheControl;->minFreshSeconds()I

    move-result v7

    if-eq v7, v8, :cond_5

    .line 211
    sget-object v7, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v1}, Lokhttp3/CacheControl;->minFreshSeconds()I

    move-result v11

    int-to-long v11, v11

    invoke-virtual {v7, v11, v12}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide v9

    .line 214
    :cond_5
    const-wide/16 v11, 0x0

    .line 215
    .local v11, "maxStaleMillis":J
    iget-object v7, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    invoke-virtual {v7}, Lokhttp3/Response;->cacheControl()Lokhttp3/CacheControl;

    move-result-object v7

    .line 216
    .local v7, "responseCaching":Lokhttp3/CacheControl;
    invoke-virtual {v7}, Lokhttp3/CacheControl;->mustRevalidate()Z

    move-result v13

    if-nez v13, :cond_6

    invoke-virtual {v1}, Lokhttp3/CacheControl;->maxStaleSeconds()I

    move-result v13

    if-eq v13, v8, :cond_6

    .line 217
    sget-object v8, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v1}, Lokhttp3/CacheControl;->maxStaleSeconds()I

    move-result v13

    int-to-long v13, v13

    invoke-virtual {v8, v13, v14}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide v11

    .line 220
    :cond_6
    invoke-virtual {v7}, Lokhttp3/CacheControl;->noCache()Z

    move-result v8

    if-nez v8, :cond_9

    add-long v13, v3, v9

    add-long v15, v5, v11

    cmp-long v8, v13, v15

    if-gez v8, :cond_9

    .line 221
    iget-object v8, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    invoke-virtual {v8}, Lokhttp3/Response;->newBuilder()Lokhttp3/Response$Builder;

    move-result-object v8

    .line 222
    .local v8, "builder":Lokhttp3/Response$Builder;
    add-long v13, v3, v9

    cmp-long v15, v13, v5

    if-ltz v15, :cond_7

    .line 223
    const-string v13, "Warning"

    const-string v14, "110 HttpURLConnection \"Response is stale\""

    invoke-virtual {v8, v13, v14}, Lokhttp3/Response$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Response$Builder;

    .line 225
    :cond_7
    const-wide/32 v13, 0x5265c00

    .line 226
    .local v13, "oneDayMillis":J
    cmp-long v15, v3, v13

    if-lez v15, :cond_8

    invoke-direct/range {p0 .. p0}, Lokhttp3/internal/http/CacheStrategy$Factory;->isFreshnessLifetimeHeuristic()Z

    move-result v15

    if-eqz v15, :cond_8

    .line 227
    const-string v15, "Warning"

    const-string v2, "113 HttpURLConnection \"Heuristic expiration\""

    invoke-virtual {v8, v15, v2}, Lokhttp3/Response$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Response$Builder;

    .line 229
    :cond_8
    new-instance v2, Lokhttp3/internal/http/CacheStrategy;

    invoke-virtual {v8}, Lokhttp3/Response$Builder;->build()Lokhttp3/Response;

    move-result-object v15

    move-object/from16 v17, v1

    const/4 v1, 0x0

    .end local v1    # "requestCaching":Lokhttp3/CacheControl;
    .local v17, "requestCaching":Lokhttp3/CacheControl;
    invoke-direct {v2, v1, v15, v1}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    return-object v2

    .line 232
    .end local v8    # "builder":Lokhttp3/Response$Builder;
    .end local v13    # "oneDayMillis":J
    .end local v17    # "requestCaching":Lokhttp3/CacheControl;
    .restart local v1    # "requestCaching":Lokhttp3/CacheControl;
    :cond_9
    move-object/from16 v17, v1

    .end local v1    # "requestCaching":Lokhttp3/CacheControl;
    .restart local v17    # "requestCaching":Lokhttp3/CacheControl;
    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-virtual {v1}, Lokhttp3/Request;->newBuilder()Lokhttp3/Request$Builder;

    move-result-object v1

    .line 234
    .local v1, "conditionalRequestBuilder":Lokhttp3/Request$Builder;
    iget-object v2, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->etag:Ljava/lang/String;

    if-eqz v2, :cond_a

    .line 235
    const-string v2, "If-None-Match"

    iget-object v8, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->etag:Ljava/lang/String;

    invoke-virtual {v1, v2, v8}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    goto :goto_0

    .line 236
    :cond_a
    iget-object v2, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->lastModified:Ljava/util/Date;

    if-eqz v2, :cond_b

    .line 237
    const-string v2, "If-Modified-Since"

    iget-object v8, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->lastModifiedString:Ljava/lang/String;

    invoke-virtual {v1, v2, v8}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    goto :goto_0

    .line 238
    :cond_b
    iget-object v2, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDate:Ljava/util/Date;

    if-eqz v2, :cond_c

    .line 239
    const-string v2, "If-Modified-Since"

    iget-object v8, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->servedDateString:Ljava/lang/String;

    invoke-virtual {v1, v2, v8}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    .line 242
    :cond_c
    :goto_0
    invoke-virtual {v1}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v2

    .line 243
    .local v2, "conditionalRequest":Lokhttp3/Request;
    invoke-static {v2}, Lokhttp3/internal/http/CacheStrategy$Factory;->hasConditions(Lokhttp3/Request;)Z

    move-result v8

    if-eqz v8, :cond_d

    new-instance v8, Lokhttp3/internal/http/CacheStrategy;

    iget-object v13, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    const/4 v14, 0x0

    invoke-direct {v8, v2, v13, v14}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    goto :goto_1

    :cond_d
    const/4 v14, 0x0

    new-instance v8, Lokhttp3/internal/http/CacheStrategy;

    invoke-direct {v8, v2, v14, v14}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    :goto_1
    return-object v8

    .line 199
    .end local v2    # "conditionalRequest":Lokhttp3/Request;
    .end local v3    # "ageMillis":J
    .end local v5    # "freshMillis":J
    .end local v7    # "responseCaching":Lokhttp3/CacheControl;
    .end local v9    # "minFreshMillis":J
    .end local v11    # "maxStaleMillis":J
    .end local v17    # "requestCaching":Lokhttp3/CacheControl;
    .local v1, "requestCaching":Lokhttp3/CacheControl;
    :cond_e
    move-object/from16 v17, v1

    move-object v14, v2

    .end local v1    # "requestCaching":Lokhttp3/CacheControl;
    .restart local v17    # "requestCaching":Lokhttp3/CacheControl;
    :goto_2
    new-instance v1, Lokhttp3/internal/http/CacheStrategy;

    iget-object v2, v0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-direct {v1, v2, v14, v14}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    return-object v1
.end method

.method private static hasConditions(Lokhttp3/Request;)Z
    .locals 1
    .param p0, "request"    # Lokhttp3/Request;

    .line 307
    const-string v0, "If-Modified-Since"

    invoke-virtual {p0, v0}, Lokhttp3/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_1

    const-string v0, "If-None-Match"

    invoke-virtual {p0, v0}, Lokhttp3/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method private isFreshnessLifetimeHeuristic()Z
    .locals 2

    .line 298
    iget-object v0, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->cacheResponse:Lokhttp3/Response;

    invoke-virtual {v0}, Lokhttp3/Response;->cacheControl()Lokhttp3/CacheControl;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp3/CacheControl;->maxAgeSeconds()I

    move-result v0

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->expires:Ljava/util/Date;

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method


# virtual methods
.method public get()Lokhttp3/internal/http/CacheStrategy;
    .locals 3

    .line 168
    invoke-direct {p0}, Lokhttp3/internal/http/CacheStrategy$Factory;->getCandidate()Lokhttp3/internal/http/CacheStrategy;

    move-result-object v0

    .line 170
    .local v0, "candidate":Lokhttp3/internal/http/CacheStrategy;
    iget-object v1, v0, Lokhttp3/internal/http/CacheStrategy;->networkRequest:Lokhttp3/Request;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lokhttp3/internal/http/CacheStrategy$Factory;->request:Lokhttp3/Request;

    invoke-virtual {v1}, Lokhttp3/Request;->cacheControl()Lokhttp3/CacheControl;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp3/CacheControl;->onlyIfCached()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 172
    new-instance v1, Lokhttp3/internal/http/CacheStrategy;

    const/4 v2, 0x0

    invoke-direct {v1, v2, v2, v2}, Lokhttp3/internal/http/CacheStrategy;-><init>(Lokhttp3/Request;Lokhttp3/Response;Lokhttp3/internal/http/CacheStrategy$1;)V

    return-object v1

    .line 175
    :cond_0
    return-object v0
.end method
