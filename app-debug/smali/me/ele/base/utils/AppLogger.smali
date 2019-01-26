.class public Lme/ele/base/utils/AppLogger;
.super Ljava/lang/Object;
.source "AppLogger.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lme/ele/base/utils/AppLogger$CustomLogger;
    }
.end annotation

.annotation runtime Ljava/lang/Deprecated;
.end annotation


# static fields
.field public static allowD:Z

.field public static allowE:Z

.field public static allowI:Z

.field public static allowV:Z

.field public static allowW:Z

.field public static allowWtf:Z

.field public static customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

.field public static customTagPrefix:Ljava/lang/String;

.field public static debug:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 32
    const-string v0, "App"

    sput-object v0, Lme/ele/base/utils/AppLogger;->customTagPrefix:Ljava/lang/String;

    .line 38
    const/4 v0, 0x1

    sput-boolean v0, Lme/ele/base/utils/AppLogger;->allowD:Z

    .line 39
    sput-boolean v0, Lme/ele/base/utils/AppLogger;->allowE:Z

    .line 40
    sput-boolean v0, Lme/ele/base/utils/AppLogger;->allowI:Z

    .line 41
    sput-boolean v0, Lme/ele/base/utils/AppLogger;->allowV:Z

    .line 42
    sput-boolean v0, Lme/ele/base/utils/AppLogger;->allowW:Z

    .line 43
    sput-boolean v0, Lme/ele/base/utils/AppLogger;->allowWtf:Z

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 34
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 35
    return-void
.end method

.method public static d(Ljava/lang/String;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;

    .line 88
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 89
    return-void

    .line 91
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowD:Z

    if-nez v0, :cond_1

    return-void

    .line 92
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 93
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 94
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 96
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 97
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 99
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 101
    :goto_0
    return-void
.end method

.method public static d(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;
    .param p1, "tr"    # Ljava/lang/Throwable;

    .line 111
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 112
    return-void

    .line 114
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowD:Z

    if-nez v0, :cond_1

    return-void

    .line 115
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 116
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 117
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 119
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 120
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0, p1}, Lme/ele/base/utils/AppLogger$CustomLogger;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 122
    :cond_2
    invoke-static {v1, p0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 124
    :goto_0
    return-void
.end method

.method public static e(Ljava/lang/String;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;

    .line 127
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 128
    return-void

    .line 130
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowE:Z

    if-nez v0, :cond_1

    return-void

    .line 131
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 132
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 133
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 135
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 136
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->e(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 138
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 140
    :goto_0
    return-void
.end method

.method public static e(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;
    .param p1, "tr"    # Ljava/lang/Throwable;

    .line 143
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 144
    return-void

    .line 146
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowE:Z

    if-nez v0, :cond_1

    return-void

    .line 147
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 148
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 149
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 151
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 152
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0, p1}, Lme/ele/base/utils/AppLogger$CustomLogger;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 154
    :cond_2
    invoke-static {v1, p0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 156
    :goto_0
    return-void
.end method

.method public static e(Ljava/lang/Throwable;)V
    .locals 4
    .param p0, "tr"    # Ljava/lang/Throwable;

    .line 159
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 160
    return-void

    .line 162
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowE:Z

    if-nez v0, :cond_1

    return-void

    .line 163
    :cond_1
    if-nez p0, :cond_2

    .line 164
    return-void

    .line 166
    :cond_2
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 167
    .local v0, "content":Ljava/lang/String;
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v1

    .line 168
    .local v1, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v1}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v2

    .line 170
    .local v2, "tag":Ljava/lang/String;
    sget-object v3, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v3, :cond_3

    .line 171
    sget-object v3, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v3, v2, v0, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 173
    :cond_3
    invoke-static {v2, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 175
    :goto_0
    return-void
.end method

.method private static generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;
    .locals 5
    .param p0, "caller"    # Ljava/lang/StackTraceElement;

    .line 46
    const-string v0, "%s.%s(L:%d)"

    .line 47
    .local v0, "tag":Ljava/lang/String;
    invoke-virtual {p0}, Ljava/lang/StackTraceElement;->getClassName()Ljava/lang/String;

    move-result-object v1

    .line 48
    .local v1, "callerClazzName":Ljava/lang/String;
    const-string v2, "build/intermediates/exploded-aar/com.android.support/appcompat-v7/21.0.2/res"

    invoke-virtual {v1, v2}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v2

    const/4 v3, 0x1

    add-int/2addr v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v1

    .line 50
    const/4 v2, 0x3

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object v1, v2, v4

    invoke-virtual {p0}, Ljava/lang/StackTraceElement;->getMethodName()Ljava/lang/String;

    move-result-object v4

    aput-object v4, v2, v3

    invoke-virtual {p0}, Ljava/lang/StackTraceElement;->getLineNumber()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const/4 v4, 0x2

    aput-object v3, v2, v4

    invoke-static {v0, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 51
    sget-object v2, Lme/ele/base/utils/AppLogger;->customTagPrefix:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    move-object v2, v0

    goto :goto_0

    :cond_0
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v3, Lme/ele/base/utils/AppLogger;->customTagPrefix:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ":"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    :goto_0
    move-object v0, v2

    .line 52
    return-object v0
.end method

.method public static getCallerStackTraceElement()Ljava/lang/StackTraceElement;
    .locals 2

    .line 336
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Thread;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object v0

    const/4 v1, 0x4

    aget-object v0, v0, v1

    return-object v0
.end method

.method public static i(Ljava/lang/String;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;

    .line 178
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 179
    return-void

    .line 181
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowI:Z

    if-nez v0, :cond_1

    return-void

    .line 182
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 183
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 184
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 186
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 187
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->i(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 189
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 191
    :goto_0
    return-void
.end method

.method public static i(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;
    .param p1, "tr"    # Ljava/lang/Throwable;

    .line 194
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 195
    return-void

    .line 197
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowI:Z

    if-nez v0, :cond_1

    return-void

    .line 198
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 199
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 200
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 202
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 203
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0, p1}, Lme/ele/base/utils/AppLogger$CustomLogger;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 205
    :cond_2
    invoke-static {v1, p0, p1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 207
    :goto_0
    return-void
.end method

.method public static v(Ljava/lang/String;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;

    .line 210
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 211
    return-void

    .line 213
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowV:Z

    if-nez v0, :cond_1

    return-void

    .line 214
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 215
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 216
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 218
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 219
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->v(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 221
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 223
    :goto_0
    return-void
.end method

.method public static v(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;
    .param p1, "tr"    # Ljava/lang/Throwable;

    .line 226
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 227
    return-void

    .line 229
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowV:Z

    if-nez v0, :cond_1

    return-void

    .line 230
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 231
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 232
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 234
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 235
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0, p1}, Lme/ele/base/utils/AppLogger$CustomLogger;->v(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 237
    :cond_2
    invoke-static {v1, p0, p1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 239
    :goto_0
    return-void
.end method

.method public static w(Ljava/lang/String;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;

    .line 242
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 243
    return-void

    .line 245
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowW:Z

    if-nez v0, :cond_1

    return-void

    .line 246
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 247
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 248
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 250
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 251
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->w(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 253
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 255
    :goto_0
    return-void
.end method

.method public static w(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;
    .param p1, "tr"    # Ljava/lang/Throwable;

    .line 258
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 259
    return-void

    .line 261
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowW:Z

    if-nez v0, :cond_1

    return-void

    .line 262
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 263
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 264
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 266
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 267
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0, p1}, Lme/ele/base/utils/AppLogger$CustomLogger;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 269
    :cond_2
    invoke-static {v1, p0, p1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 271
    :goto_0
    return-void
.end method

.method public static w(Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "tr"    # Ljava/lang/Throwable;

    .line 274
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 275
    return-void

    .line 277
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowW:Z

    if-nez v0, :cond_1

    return-void

    .line 278
    :cond_1
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 279
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 281
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 282
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 284
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 286
    :goto_0
    return-void
.end method

.method private static wrapContent(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p0, "content"    # Ljava/lang/String;

    .line 104
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 105
    const-string p0, ""

    .line 107
    :cond_0
    return-object p0
.end method

.method public static wtf(Ljava/lang/String;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;

    .line 289
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 290
    return-void

    .line 292
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowWtf:Z

    if-nez v0, :cond_1

    return-void

    .line 293
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 294
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 295
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 297
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 298
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->wtf(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 300
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->wtf(Ljava/lang/String;Ljava/lang/String;)I

    .line 302
    :goto_0
    return-void
.end method

.method public static wtf(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "content"    # Ljava/lang/String;
    .param p1, "tr"    # Ljava/lang/Throwable;

    .line 305
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 306
    return-void

    .line 308
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowWtf:Z

    if-nez v0, :cond_1

    return-void

    .line 309
    :cond_1
    invoke-static {p0}, Lme/ele/base/utils/AppLogger;->wrapContent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 310
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 311
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 313
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 314
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0, p1}, Lme/ele/base/utils/AppLogger$CustomLogger;->wtf(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 316
    :cond_2
    invoke-static {v1, p0, p1}, Landroid/util/Log;->wtf(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 318
    :goto_0
    return-void
.end method

.method public static wtf(Ljava/lang/Throwable;)V
    .locals 3
    .param p0, "tr"    # Ljava/lang/Throwable;

    .line 321
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->debug:Z

    if-nez v0, :cond_0

    .line 322
    return-void

    .line 324
    :cond_0
    sget-boolean v0, Lme/ele/base/utils/AppLogger;->allowWtf:Z

    if-nez v0, :cond_1

    return-void

    .line 325
    :cond_1
    invoke-static {}, Lme/ele/base/utils/AppLogger;->getCallerStackTraceElement()Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 326
    .local v0, "caller":Ljava/lang/StackTraceElement;
    invoke-static {v0}, Lme/ele/base/utils/AppLogger;->generateTag(Ljava/lang/StackTraceElement;)Ljava/lang/String;

    move-result-object v1

    .line 328
    .local v1, "tag":Ljava/lang/String;
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    if-eqz v2, :cond_2

    .line 329
    sget-object v2, Lme/ele/base/utils/AppLogger;->customLogger:Lme/ele/base/utils/AppLogger$CustomLogger;

    invoke-interface {v2, v1, p0}, Lme/ele/base/utils/AppLogger$CustomLogger;->wtf(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 331
    :cond_2
    invoke-static {v1, p0}, Landroid/util/Log;->wtf(Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 333
    :goto_0
    return-void
.end method
