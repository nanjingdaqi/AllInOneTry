.class Lokhttp3/internal/AndroidPlatform;
.super Lokhttp3/internal/Platform;
.source "AndroidPlatform.java"


# static fields
.field private static final MAX_LOG_LENGTH:I = 0xfa0


# instance fields
.field private final getAlpnSelectedProtocol:Lokhttp3/internal/OptionalMethod;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;"
        }
    .end annotation
.end field

.field private final setAlpnProtocols:Lokhttp3/internal/OptionalMethod;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;"
        }
    .end annotation
.end field

.field private final setHostname:Lokhttp3/internal/OptionalMethod;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;"
        }
    .end annotation
.end field

.field private final setUseSessionTickets:Lokhttp3/internal/OptionalMethod;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;"
        }
    .end annotation
.end field

.field private final sslParametersClass:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljava/lang/Class;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;",
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;",
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;",
            "Lokhttp3/internal/OptionalMethod<",
            "Ljava/net/Socket;",
            ">;)V"
        }
    .end annotation

    .line 44
    .local p1, "sslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    .local p2, "setUseSessionTickets":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .local p3, "setHostname":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .local p4, "getAlpnSelectedProtocol":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .local p5, "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    invoke-direct {p0}, Lokhttp3/internal/Platform;-><init>()V

    .line 45
    iput-object p1, p0, Lokhttp3/internal/AndroidPlatform;->sslParametersClass:Ljava/lang/Class;

    .line 46
    iput-object p2, p0, Lokhttp3/internal/AndroidPlatform;->setUseSessionTickets:Lokhttp3/internal/OptionalMethod;

    .line 47
    iput-object p3, p0, Lokhttp3/internal/AndroidPlatform;->setHostname:Lokhttp3/internal/OptionalMethod;

    .line 48
    iput-object p4, p0, Lokhttp3/internal/AndroidPlatform;->getAlpnSelectedProtocol:Lokhttp3/internal/OptionalMethod;

    .line 49
    iput-object p5, p0, Lokhttp3/internal/AndroidPlatform;->setAlpnProtocols:Lokhttp3/internal/OptionalMethod;

    .line 50
    return-void
.end method

.method public static buildIfSupported()Lokhttp3/internal/Platform;
    .locals 12

    .line 152
    const/4 v0, 0x0

    :try_start_0
    const-string v1, "com.android.org.conscrypt.SSLParametersImpl"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 157
    .local v1, "sslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    nop

    .line 155
    move-object v4, v1

    goto :goto_0

    .line 153
    .end local v1    # "sslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :catch_0
    move-exception v1

    .line 155
    .local v1, "e":Ljava/lang/ClassNotFoundException;
    :try_start_1
    const-string v2, "org.apache.harmony.xnet.provider.jsse.SSLParametersImpl"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    move-object v4, v2

    .line 159
    .end local v1    # "e":Ljava/lang/ClassNotFoundException;
    .local v4, "sslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :goto_0
    new-instance v5, Lokhttp3/internal/OptionalMethod;

    const-string v1, "setUseSessionTickets"

    const/4 v2, 0x1

    new-array v3, v2, [Ljava/lang/Class;

    sget-object v6, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    const/4 v7, 0x0

    aput-object v6, v3, v7

    invoke-direct {v5, v0, v1, v3}, Lokhttp3/internal/OptionalMethod;-><init>(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V

    .line 161
    .local v5, "setUseSessionTickets":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    new-instance v6, Lokhttp3/internal/OptionalMethod;

    const-string v1, "setHostname"

    new-array v3, v2, [Ljava/lang/Class;

    const-class v8, Ljava/lang/String;

    aput-object v8, v3, v7

    invoke-direct {v6, v0, v1, v3}, Lokhttp3/internal/OptionalMethod;-><init>(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
    :try_end_1
    .catch Ljava/lang/ClassNotFoundException; {:try_start_1 .. :try_end_1} :catch_2

    .line 163
    .local v6, "setHostname":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    const/4 v1, 0x0

    .line 164
    .local v1, "getAlpnSelectedProtocol":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    move-object v3, v0

    .line 168
    .local v3, "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    :try_start_2
    const-string v8, "android.net.Network"

    invoke-static {v8}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    .line 169
    new-instance v8, Lokhttp3/internal/OptionalMethod;

    const-class v9, [B

    const-string v10, "getAlpnSelectedProtocol"

    new-array v11, v7, [Ljava/lang/Class;

    invoke-direct {v8, v9, v10, v11}, Lokhttp3/internal/OptionalMethod;-><init>(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V

    move-object v1, v8

    .line 170
    new-instance v8, Lokhttp3/internal/OptionalMethod;

    const-string v9, "setAlpnProtocols"

    new-array v2, v2, [Ljava/lang/Class;

    const-class v10, [B

    aput-object v10, v2, v7

    invoke-direct {v8, v0, v9, v2}, Lokhttp3/internal/OptionalMethod;-><init>(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
    :try_end_2
    .catch Ljava/lang/ClassNotFoundException; {:try_start_2 .. :try_end_2} :catch_1

    move-object v2, v8

    .line 172
    .end local v3    # "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .local v2, "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    goto :goto_1

    .line 171
    .end local v2    # "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .restart local v3    # "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    :catch_1
    move-exception v2

    .line 174
    move-object v2, v3

    .end local v3    # "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .restart local v2    # "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    :goto_1
    :try_start_3
    new-instance v9, Lokhttp3/internal/AndroidPlatform;

    move-object v3, v9

    move-object v7, v1

    move-object v8, v2

    invoke-direct/range {v3 .. v8}, Lokhttp3/internal/AndroidPlatform;-><init>(Ljava/lang/Class;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;Lokhttp3/internal/OptionalMethod;)V
    :try_end_3
    .catch Ljava/lang/ClassNotFoundException; {:try_start_3 .. :try_end_3} :catch_2

    return-object v9

    .line 176
    .end local v1    # "getAlpnSelectedProtocol":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .end local v2    # "setAlpnProtocols":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .end local v4    # "sslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    .end local v5    # "setUseSessionTickets":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    .end local v6    # "setHostname":Lokhttp3/internal/OptionalMethod;, "Lokhttp3/internal/OptionalMethod<Ljava/net/Socket;>;"
    :catch_2
    move-exception v1

    .line 180
    return-object v0
.end method


# virtual methods
.method public configureTlsExtensions(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
    .locals 5
    .param p1, "sslSocket"    # Ljavax/net/ssl/SSLSocket;
    .param p2, "hostname"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljavax/net/ssl/SSLSocket;",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lokhttp3/Protocol;",
            ">;)V"
        }
    .end annotation

    .line 93
    .local p3, "protocols":Ljava/util/List;, "Ljava/util/List<Lokhttp3/Protocol;>;"
    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p2, :cond_0

    .line 94
    iget-object v2, p0, Lokhttp3/internal/AndroidPlatform;->setUseSessionTickets:Lokhttp3/internal/OptionalMethod;

    new-array v3, v1, [Ljava/lang/Object;

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    aput-object v4, v3, v0

    invoke-virtual {v2, p1, v3}, Lokhttp3/internal/OptionalMethod;->invokeOptionalWithoutCheckedException(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    .line 95
    iget-object v2, p0, Lokhttp3/internal/AndroidPlatform;->setHostname:Lokhttp3/internal/OptionalMethod;

    new-array v3, v1, [Ljava/lang/Object;

    aput-object p2, v3, v0

    invoke-virtual {v2, p1, v3}, Lokhttp3/internal/OptionalMethod;->invokeOptionalWithoutCheckedException(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    .line 99
    :cond_0
    iget-object v2, p0, Lokhttp3/internal/AndroidPlatform;->setAlpnProtocols:Lokhttp3/internal/OptionalMethod;

    if-eqz v2, :cond_1

    iget-object v2, p0, Lokhttp3/internal/AndroidPlatform;->setAlpnProtocols:Lokhttp3/internal/OptionalMethod;

    invoke-virtual {v2, p1}, Lokhttp3/internal/OptionalMethod;->isSupported(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 100
    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p3}, Lokhttp3/internal/AndroidPlatform;->concatLengthPrefixed(Ljava/util/List;)[B

    move-result-object v2

    aput-object v2, v1, v0

    move-object v0, v1

    .line 101
    .local v0, "parameters":[Ljava/lang/Object;
    iget-object v1, p0, Lokhttp3/internal/AndroidPlatform;->setAlpnProtocols:Lokhttp3/internal/OptionalMethod;

    invoke-virtual {v1, p1, v0}, Lokhttp3/internal/OptionalMethod;->invokeWithoutCheckedException(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    .line 103
    .end local v0    # "parameters":[Ljava/lang/Object;
    :cond_1
    return-void
.end method

.method public connectSocket(Ljava/net/Socket;Ljava/net/InetSocketAddress;I)V
    .locals 3
    .param p1, "socket"    # Ljava/net/Socket;
    .param p2, "address"    # Ljava/net/InetSocketAddress;
    .param p3, "connectTimeout"    # I
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 55
    :try_start_0
    invoke-virtual {p1, p2, p3}, Ljava/net/Socket;->connect(Ljava/net/SocketAddress;I)V
    :try_end_0
    .catch Ljava/lang/AssertionError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    .line 65
    nop

    .line 66
    return-void

    .line 59
    :catch_0
    move-exception v0

    .line 62
    .local v0, "e":Ljava/lang/SecurityException;
    new-instance v1, Ljava/io/IOException;

    const-string v2, "Exception in connect"

    invoke-direct {v1, v2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    .line 63
    .local v1, "ioException":Ljava/io/IOException;
    invoke-virtual {v1, v0}, Ljava/io/IOException;->initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;

    .line 64
    throw v1

    .line 56
    .end local v0    # "e":Ljava/lang/SecurityException;
    .end local v1    # "ioException":Ljava/io/IOException;
    :catch_1
    move-exception v0

    .line 57
    .local v0, "e":Ljava/lang/AssertionError;
    invoke-static {v0}, Lokhttp3/internal/Util;->isAndroidGetsocknameError(Ljava/lang/AssertionError;)Z

    move-result v1

    if-eqz v1, :cond_0

    new-instance v1, Ljava/io/IOException;

    invoke-direct {v1, v0}, Ljava/io/IOException;-><init>(Ljava/lang/Throwable;)V

    throw v1

    .line 58
    :cond_0
    throw v0
.end method

.method public getSelectedProtocol(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
    .locals 3
    .param p1, "socket"    # Ljavax/net/ssl/SSLSocket;

    .line 106
    iget-object v0, p0, Lokhttp3/internal/AndroidPlatform;->getAlpnSelectedProtocol:Lokhttp3/internal/OptionalMethod;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    .line 107
    :cond_0
    iget-object v0, p0, Lokhttp3/internal/AndroidPlatform;->getAlpnSelectedProtocol:Lokhttp3/internal/OptionalMethod;

    invoke-virtual {v0, p1}, Lokhttp3/internal/OptionalMethod;->isSupported(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    return-object v1

    .line 109
    :cond_1
    iget-object v0, p0, Lokhttp3/internal/AndroidPlatform;->getAlpnSelectedProtocol:Lokhttp3/internal/OptionalMethod;

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v0, p1, v2}, Lokhttp3/internal/OptionalMethod;->invokeWithoutCheckedException(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [B

    .line 110
    .local v0, "alpnResult":[B
    if-eqz v0, :cond_2

    new-instance v1, Ljava/lang/String;

    sget-object v2, Lokhttp3/internal/Util;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, v0, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    nop

    :cond_2
    return-object v1
.end method

.method public isCleartextTrafficPermitted()Z
    .locals 6

    .line 131
    :try_start_0
    const-string v0, "android.security.NetworkSecurityPolicy"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 132
    .local v0, "networkPolicyClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    const-string v1, "getInstance"

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 133
    .local v1, "getInstanceMethod":Ljava/lang/reflect/Method;
    const/4 v3, 0x0

    new-array v4, v2, [Ljava/lang/Object;

    invoke-virtual {v1, v3, v4}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    .line 134
    .local v3, "networkSecurityPolicy":Ljava/lang/Object;
    const-string v4, "isCleartextTrafficPermitted"

    new-array v5, v2, [Ljava/lang/Class;

    .line 135
    invoke-virtual {v0, v4, v5}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v4

    .line 136
    .local v4, "isCleartextTrafficPermittedMethod":Ljava/lang/reflect/Method;
    new-array v2, v2, [Ljava/lang/Object;

    .line 137
    invoke-virtual {v4, v3, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Boolean;

    .line 136
    invoke-virtual {v2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v2
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_0 .. :try_end_0} :catch_0

    .line 138
    .local v2, "cleartextPermitted":Z
    return v2

    .line 141
    .end local v0    # "networkPolicyClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    .end local v1    # "getInstanceMethod":Ljava/lang/reflect/Method;
    .end local v2    # "cleartextPermitted":Z
    .end local v3    # "networkSecurityPolicy":Ljava/lang/Object;
    .end local v4    # "isCleartextTrafficPermittedMethod":Ljava/lang/reflect/Method;
    :catch_0
    move-exception v0

    .line 143
    .local v0, "e":Ljava/lang/Exception;
    new-instance v1, Ljava/lang/AssertionError;

    invoke-direct {v1}, Ljava/lang/AssertionError;-><init>()V

    throw v1

    .line 139
    .end local v0    # "e":Ljava/lang/Exception;
    :catch_1
    move-exception v0

    .line 140
    .local v0, "e":Ljava/lang/ClassNotFoundException;
    invoke-super {p0}, Lokhttp3/internal/Platform;->isCleartextTrafficPermitted()Z

    move-result v1

    return v1
.end method

.method public log(ILjava/lang/String;Ljava/lang/Throwable;)V
    .locals 8
    .param p1, "level"    # I
    .param p2, "message"    # Ljava/lang/String;
    .param p3, "t"    # Ljava/lang/Throwable;

    .line 114
    const/4 v0, 0x5

    if-ne p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x3

    .line 115
    .local v0, "logLevel":I
    :goto_0
    const/16 v1, 0xa

    if-eqz p3, :cond_1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-static {p3}, Landroid/util/Log;->getStackTraceString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 118
    :cond_1
    const/4 v2, 0x0

    .local v2, "i":I
    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v3

    .local v3, "length":I
    :goto_1
    if-ge v2, v3, :cond_4

    .line 119
    invoke-virtual {p2, v1, v2}, Ljava/lang/String;->indexOf(II)I

    move-result v4

    .line 120
    .local v4, "newline":I
    const/4 v5, -0x1

    if-eq v4, v5, :cond_2

    move v5, v4

    goto :goto_2

    :cond_2
    move v5, v3

    :goto_2
    move v4, v5

    .line 122
    :cond_3
    add-int/lit16 v5, v2, 0xfa0

    invoke-static {v4, v5}, Ljava/lang/Math;->min(II)I

    move-result v5

    .line 123
    .local v5, "end":I
    const-string v6, "OkHttp"

    invoke-virtual {p2, v2, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v7

    invoke-static {v0, v6, v7}, Landroid/util/Log;->println(ILjava/lang/String;Ljava/lang/String;)I

    .line 124
    move v2, v5

    .line 125
    .end local v5    # "end":I
    if-lt v2, v4, :cond_3

    .line 118
    .end local v4    # "newline":I
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 127
    .end local v2    # "i":I
    .end local v3    # "length":I
    :cond_4
    return-void
.end method

.method public trustManager(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;
    .locals 4
    .param p1, "sslSocketFactory"    # Ljavax/net/ssl/SSLSocketFactory;

    .line 69
    iget-object v0, p0, Lokhttp3/internal/AndroidPlatform;->sslParametersClass:Ljava/lang/Class;

    const-string v1, "sslParameters"

    invoke-static {p1, v0, v1}, Lokhttp3/internal/AndroidPlatform;->readFieldOrNull(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    .line 70
    .local v0, "context":Ljava/lang/Object;
    if-nez v0, :cond_0

    .line 74
    :try_start_0
    const-string v1, "com.google.android.gms.org.conscrypt.SSLParametersImpl"

    const/4 v2, 0x0

    .line 76
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v3

    .line 74
    invoke-static {v1, v2, v3}, Ljava/lang/Class;->forName(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;

    move-result-object v1

    .line 77
    .local v1, "gmsSslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    const-string v2, "sslParameters"

    invoke-static {p1, v1, v2}, Lokhttp3/internal/AndroidPlatform;->readFieldOrNull(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v0, v2

    .line 80
    .end local v1    # "gmsSslParametersClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    goto :goto_0

    .line 78
    :catch_0
    move-exception v1

    .line 79
    .local v1, "e":Ljava/lang/ClassNotFoundException;
    invoke-super {p0, p1}, Lokhttp3/internal/Platform;->trustManager(Ljavax/net/ssl/SSLSocketFactory;)Ljavax/net/ssl/X509TrustManager;

    move-result-object v2

    return-object v2

    .line 83
    .end local v1    # "e":Ljava/lang/ClassNotFoundException;
    :cond_0
    :goto_0
    const-class v1, Ljavax/net/ssl/X509TrustManager;

    const-string v2, "x509TrustManager"

    invoke-static {v0, v1, v2}, Lokhttp3/internal/AndroidPlatform;->readFieldOrNull(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljavax/net/ssl/X509TrustManager;

    .line 85
    .local v1, "x509TrustManager":Ljavax/net/ssl/X509TrustManager;
    if-eqz v1, :cond_1

    return-object v1

    .line 87
    :cond_1
    const-class v2, Ljavax/net/ssl/X509TrustManager;

    const-string v3, "trustManager"

    invoke-static {v0, v2, v3}, Lokhttp3/internal/AndroidPlatform;->readFieldOrNull(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljavax/net/ssl/X509TrustManager;

    return-object v2
.end method
