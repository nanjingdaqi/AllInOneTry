.class public Landroid/support/v4/app/NotificationCompatKitKat$Builder;
.super Ljava/lang/Object;
.source "NotificationCompatKitKat.java"

# interfaces
.implements Landroid/support/v4/app/NotificationBuilderWithBuilderAccessor;
.implements Landroid/support/v4/app/NotificationBuilderWithActions;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/app/NotificationCompatKitKat;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private b:Landroid/app/Notification$Builder;

.field private mActionExtrasList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroid/os/Bundle;",
            ">;"
        }
    .end annotation
.end field

.field private mBigContentView:Landroid/widget/RemoteViews;

.field private mContentView:Landroid/widget/RemoteViews;

.field private mExtras:Landroid/os/Bundle;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/app/Notification;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/widget/RemoteViews;ILandroid/app/PendingIntent;Landroid/app/PendingIntent;Landroid/graphics/Bitmap;IIZZZILjava/lang/CharSequence;ZLjava/util/ArrayList;Landroid/os/Bundle;Ljava/lang/String;ZLjava/lang/String;Landroid/widget/RemoteViews;Landroid/widget/RemoteViews;)V
    .locals 16
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "n"    # Landroid/app/Notification;
    .param p3, "contentTitle"    # Ljava/lang/CharSequence;
    .param p4, "contentText"    # Ljava/lang/CharSequence;
    .param p5, "contentInfo"    # Ljava/lang/CharSequence;
    .param p6, "tickerView"    # Landroid/widget/RemoteViews;
    .param p7, "number"    # I
    .param p8, "contentIntent"    # Landroid/app/PendingIntent;
    .param p9, "fullScreenIntent"    # Landroid/app/PendingIntent;
    .param p10, "largeIcon"    # Landroid/graphics/Bitmap;
    .param p11, "progressMax"    # I
    .param p12, "progress"    # I
    .param p13, "progressIndeterminate"    # Z
    .param p14, "showWhen"    # Z
    .param p15, "useChronometer"    # Z
    .param p16, "priority"    # I
    .param p17, "subText"    # Ljava/lang/CharSequence;
    .param p18, "localOnly"    # Z
    .param p20, "extras"    # Landroid/os/Bundle;
    .param p21, "groupKey"    # Ljava/lang/String;
    .param p22, "groupSummary"    # Z
    .param p23, "sortKey"    # Ljava/lang/String;
    .param p24, "contentView"    # Landroid/widget/RemoteViews;
    .param p25, "bigContentView"    # Landroid/widget/RemoteViews;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Landroid/app/Notification;",
            "Ljava/lang/CharSequence;",
            "Ljava/lang/CharSequence;",
            "Ljava/lang/CharSequence;",
            "Landroid/widget/RemoteViews;",
            "I",
            "Landroid/app/PendingIntent;",
            "Landroid/app/PendingIntent;",
            "Landroid/graphics/Bitmap;",
            "IIZZZI",
            "Ljava/lang/CharSequence;",
            "Z",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;",
            "Landroid/os/Bundle;",
            "Ljava/lang/String;",
            "Z",
            "Ljava/lang/String;",
            "Landroid/widget/RemoteViews;",
            "Landroid/widget/RemoteViews;",
            ")V"
        }
    .end annotation

    .line 50
    .local p19, "people":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Ljava/lang/String;>;"
    move-object/from16 v0, p0

    move-object/from16 v1, p2

    move-object/from16 v2, p19

    move-object/from16 v3, p20

    move-object/from16 v4, p21

    move-object/from16 v5, p23

    invoke-direct/range {p0 .. p0}, Ljava/lang/Object;-><init>()V

    .line 39
    new-instance v6, Ljava/util/ArrayList;

    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    iput-object v6, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mActionExtrasList:Ljava/util/List;

    .line 51
    new-instance v6, Landroid/app/Notification$Builder;

    move-object/from16 v7, p1

    invoke-direct {v6, v7}, Landroid/app/Notification$Builder;-><init>(Landroid/content/Context;)V

    iget-wide v8, v1, Landroid/app/Notification;->when:J

    .line 52
    invoke-virtual {v6, v8, v9}, Landroid/app/Notification$Builder;->setWhen(J)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 53
    move/from16 v8, p14

    invoke-virtual {v6, v8}, Landroid/app/Notification$Builder;->setShowWhen(Z)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v9, v1, Landroid/app/Notification;->icon:I

    iget v10, v1, Landroid/app/Notification;->iconLevel:I

    .line 54
    invoke-virtual {v6, v9, v10}, Landroid/app/Notification$Builder;->setSmallIcon(II)Landroid/app/Notification$Builder;

    move-result-object v6

    iget-object v9, v1, Landroid/app/Notification;->contentView:Landroid/widget/RemoteViews;

    .line 55
    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setContent(Landroid/widget/RemoteViews;)Landroid/app/Notification$Builder;

    move-result-object v6

    iget-object v9, v1, Landroid/app/Notification;->tickerText:Ljava/lang/CharSequence;

    .line 56
    move-object/from16 v10, p6

    invoke-virtual {v6, v9, v10}, Landroid/app/Notification$Builder;->setTicker(Ljava/lang/CharSequence;Landroid/widget/RemoteViews;)Landroid/app/Notification$Builder;

    move-result-object v6

    iget-object v9, v1, Landroid/app/Notification;->sound:Landroid/net/Uri;

    iget v11, v1, Landroid/app/Notification;->audioStreamType:I

    .line 57
    invoke-virtual {v6, v9, v11}, Landroid/app/Notification$Builder;->setSound(Landroid/net/Uri;I)Landroid/app/Notification$Builder;

    move-result-object v6

    iget-object v9, v1, Landroid/app/Notification;->vibrate:[J

    .line 58
    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setVibrate([J)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v9, v1, Landroid/app/Notification;->ledARGB:I

    iget v11, v1, Landroid/app/Notification;->ledOnMS:I

    iget v12, v1, Landroid/app/Notification;->ledOffMS:I

    .line 59
    invoke-virtual {v6, v9, v11, v12}, Landroid/app/Notification$Builder;->setLights(III)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v9, v1, Landroid/app/Notification;->flags:I

    and-int/lit8 v9, v9, 0x2

    if-eqz v9, :cond_0

    .line 60
    const/4 v9, 0x1

    goto :goto_0

    .line 59
    :cond_0
    nop

    .line 60
    const/4 v9, 0x0

    :goto_0
    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setOngoing(Z)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v9, v1, Landroid/app/Notification;->flags:I

    and-int/lit8 v9, v9, 0x8

    if-eqz v9, :cond_1

    .line 61
    const/4 v9, 0x1

    goto :goto_1

    .line 60
    :cond_1
    nop

    .line 61
    const/4 v9, 0x0

    :goto_1
    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setOnlyAlertOnce(Z)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v9, v1, Landroid/app/Notification;->flags:I

    and-int/lit8 v9, v9, 0x10

    if-eqz v9, :cond_2

    .line 62
    const/4 v9, 0x1

    goto :goto_2

    .line 61
    :cond_2
    nop

    .line 62
    const/4 v9, 0x0

    :goto_2
    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setAutoCancel(Z)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v9, v1, Landroid/app/Notification;->defaults:I

    .line 63
    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setDefaults(I)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 64
    move-object/from16 v9, p3

    invoke-virtual {v6, v9}, Landroid/app/Notification$Builder;->setContentTitle(Ljava/lang/CharSequence;)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 65
    move-object/from16 v13, p4

    invoke-virtual {v6, v13}, Landroid/app/Notification$Builder;->setContentText(Ljava/lang/CharSequence;)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 66
    move-object/from16 v14, p17

    invoke-virtual {v6, v14}, Landroid/app/Notification$Builder;->setSubText(Ljava/lang/CharSequence;)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 67
    move-object/from16 v15, p5

    invoke-virtual {v6, v15}, Landroid/app/Notification$Builder;->setContentInfo(Ljava/lang/CharSequence;)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 68
    move-object/from16 v11, p8

    invoke-virtual {v6, v11}, Landroid/app/Notification$Builder;->setContentIntent(Landroid/app/PendingIntent;)Landroid/app/Notification$Builder;

    move-result-object v6

    iget-object v12, v1, Landroid/app/Notification;->deleteIntent:Landroid/app/PendingIntent;

    .line 69
    invoke-virtual {v6, v12}, Landroid/app/Notification$Builder;->setDeleteIntent(Landroid/app/PendingIntent;)Landroid/app/Notification$Builder;

    move-result-object v6

    iget v12, v1, Landroid/app/Notification;->flags:I

    and-int/lit16 v12, v12, 0x80

    if-eqz v12, :cond_3

    .line 70
    const/4 v12, 0x1

    goto :goto_3

    .line 69
    :cond_3
    nop

    .line 70
    const/4 v12, 0x0

    :goto_3
    move-object/from16 v1, p9

    invoke-virtual {v6, v1, v12}, Landroid/app/Notification$Builder;->setFullScreenIntent(Landroid/app/PendingIntent;Z)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 72
    move-object/from16 v12, p10

    invoke-virtual {v6, v12}, Landroid/app/Notification$Builder;->setLargeIcon(Landroid/graphics/Bitmap;)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 73
    move/from16 v1, p7

    invoke-virtual {v6, v1}, Landroid/app/Notification$Builder;->setNumber(I)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 74
    move/from16 v1, p15

    invoke-virtual {v6, v1}, Landroid/app/Notification$Builder;->setUsesChronometer(Z)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 75
    move/from16 v1, p16

    invoke-virtual {v6, v1}, Landroid/app/Notification$Builder;->setPriority(I)Landroid/app/Notification$Builder;

    move-result-object v6

    .line 76
    move/from16 v1, p11

    move/from16 v7, p12

    move/from16 v8, p13

    invoke-virtual {v6, v1, v7, v8}, Landroid/app/Notification$Builder;->setProgress(IIZ)Landroid/app/Notification$Builder;

    move-result-object v6

    iput-object v6, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->b:Landroid/app/Notification$Builder;

    .line 77
    new-instance v6, Landroid/os/Bundle;

    invoke-direct {v6}, Landroid/os/Bundle;-><init>()V

    iput-object v6, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    .line 78
    if-eqz v3, :cond_4

    .line 79
    iget-object v6, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    invoke-virtual {v6, v3}, Landroid/os/Bundle;->putAll(Landroid/os/Bundle;)V

    .line 81
    :cond_4
    if-eqz v2, :cond_5

    invoke-virtual/range {p19 .. p19}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v6

    if-nez v6, :cond_5

    .line 82
    iget-object v6, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v1, "android.people"

    .line 83
    invoke-virtual/range {p19 .. p19}, Ljava/util/ArrayList;->size()I

    move-result v3

    new-array v3, v3, [Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v3

    check-cast v3, [Ljava/lang/String;

    .line 82
    invoke-virtual {v6, v1, v3}, Landroid/os/Bundle;->putStringArray(Ljava/lang/String;[Ljava/lang/String;)V

    .line 85
    :cond_5
    if-eqz p18, :cond_6

    .line 86
    iget-object v3, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v6, "android.support.localOnly"

    const/4 v1, 0x1

    invoke-virtual {v3, v6, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    .line 88
    :cond_6
    if-eqz v4, :cond_8

    .line 89
    iget-object v1, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v3, "android.support.groupKey"

    invoke-virtual {v1, v3, v4}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 90
    if-eqz p22, :cond_7

    .line 91
    iget-object v3, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v6, "android.support.isGroupSummary"

    const/4 v1, 0x1

    invoke-virtual {v3, v6, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    goto :goto_4

    .line 93
    :cond_7
    const/4 v1, 0x1

    iget-object v3, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v6, "android.support.useSideChannel"

    invoke-virtual {v3, v6, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    .line 96
    :cond_8
    :goto_4
    if-eqz v5, :cond_9

    .line 97
    iget-object v1, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v3, "android.support.sortKey"

    invoke-virtual {v1, v3, v5}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 99
    :cond_9
    move-object/from16 v1, p24

    iput-object v1, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mContentView:Landroid/widget/RemoteViews;

    .line 100
    move-object/from16 v3, p25

    iput-object v3, v0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mBigContentView:Landroid/widget/RemoteViews;

    .line 101
    return-void
.end method


# virtual methods
.method public addAction(Landroid/support/v4/app/NotificationCompatBase$Action;)V
    .locals 2
    .param p1, "action"    # Landroid/support/v4/app/NotificationCompatBase$Action;

    .line 105
    iget-object v0, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mActionExtrasList:Ljava/util/List;

    iget-object v1, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->b:Landroid/app/Notification$Builder;

    invoke-static {v1, p1}, Landroid/support/v4/app/NotificationCompatJellybean;->writeActionAndGetExtras(Landroid/app/Notification$Builder;Landroid/support/v4/app/NotificationCompatBase$Action;)Landroid/os/Bundle;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 106
    return-void
.end method

.method public build()Landroid/app/Notification;
    .locals 3

    .line 115
    iget-object v0, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mActionExtrasList:Ljava/util/List;

    invoke-static {v0}, Landroid/support/v4/app/NotificationCompatJellybean;->buildActionExtrasMap(Ljava/util/List;)Landroid/util/SparseArray;

    move-result-object v0

    .line 117
    .local v0, "actionExtrasMap":Landroid/util/SparseArray;, "Landroid/util/SparseArray<Landroid/os/Bundle;>;"
    if-eqz v0, :cond_0

    .line 119
    iget-object v1, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    const-string v2, "android.support.actionExtras"

    invoke-virtual {v1, v2, v0}, Landroid/os/Bundle;->putSparseParcelableArray(Ljava/lang/String;Landroid/util/SparseArray;)V

    .line 122
    :cond_0
    iget-object v1, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->b:Landroid/app/Notification$Builder;

    iget-object v2, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mExtras:Landroid/os/Bundle;

    invoke-virtual {v1, v2}, Landroid/app/Notification$Builder;->setExtras(Landroid/os/Bundle;)Landroid/app/Notification$Builder;

    .line 123
    iget-object v1, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->b:Landroid/app/Notification$Builder;

    invoke-virtual {v1}, Landroid/app/Notification$Builder;->build()Landroid/app/Notification;

    move-result-object v1

    .line 124
    .local v1, "notification":Landroid/app/Notification;
    iget-object v2, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mContentView:Landroid/widget/RemoteViews;

    if-eqz v2, :cond_1

    .line 125
    iget-object v2, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mContentView:Landroid/widget/RemoteViews;

    iput-object v2, v1, Landroid/app/Notification;->contentView:Landroid/widget/RemoteViews;

    .line 127
    :cond_1
    iget-object v2, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mBigContentView:Landroid/widget/RemoteViews;

    if-eqz v2, :cond_2

    .line 128
    iget-object v2, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->mBigContentView:Landroid/widget/RemoteViews;

    iput-object v2, v1, Landroid/app/Notification;->bigContentView:Landroid/widget/RemoteViews;

    .line 130
    :cond_2
    return-object v1
.end method

.method public getBuilder()Landroid/app/Notification$Builder;
    .locals 1

    .line 110
    iget-object v0, p0, Landroid/support/v4/app/NotificationCompatKitKat$Builder;->b:Landroid/app/Notification$Builder;

    return-object v0
.end method
