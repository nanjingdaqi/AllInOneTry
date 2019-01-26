.class public Landroid/support/graphics/drawable/PathParser$PathDataNode;
.super Ljava/lang/Object;
.source "PathParser.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/graphics/drawable/PathParser;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "PathDataNode"
.end annotation


# instance fields
.field params:[F

.field type:C


# direct methods
.method constructor <init>(C[F)V
    .locals 0
    .param p1, "type"    # C
    .param p2, "params"    # [F

    .line 300
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 301
    iput-char p1, p0, Landroid/support/graphics/drawable/PathParser$PathDataNode;->type:C

    .line 302
    iput-object p2, p0, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    .line 303
    return-void
.end method

.method constructor <init>(Landroid/support/graphics/drawable/PathParser$PathDataNode;)V
    .locals 3
    .param p1, "n"    # Landroid/support/graphics/drawable/PathParser$PathDataNode;

    .line 305
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 306
    iget-char v0, p1, Landroid/support/graphics/drawable/PathParser$PathDataNode;->type:C

    iput-char v0, p0, Landroid/support/graphics/drawable/PathParser$PathDataNode;->type:C

    .line 307
    iget-object v0, p1, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    iget-object v1, p1, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    array-length v1, v1

    const/4 v2, 0x0

    invoke-static {v0, v2, v1}, Landroid/support/graphics/drawable/PathParser;->copyOfRange([FII)[F

    move-result-object v0

    iput-object v0, p0, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    .line 308
    return-void
.end method

.method private static addCommand(Landroid/graphics/Path;[FCC[F)V
    .locals 28
    .param p0, "path"    # Landroid/graphics/Path;
    .param p1, "current"    # [F
    .param p2, "previousCmd"    # C
    .param p3, "cmd"    # C
    .param p4, "val"    # [F

    .line 345
    move-object/from16 v10, p0

    move-object/from16 v13, p4

    const/4 v0, 0x2

    .line 346
    .local v0, "incr":I
    const/4 v14, 0x0

    aget v1, p1, v14

    .line 347
    .local v1, "currentX":F
    const/4 v15, 0x1

    aget v2, p1, v15

    .line 348
    .local v2, "currentY":F
    const/16 v16, 0x2

    aget v3, p1, v16

    .line 349
    .local v3, "ctrlPointX":F
    const/16 v17, 0x3

    aget v4, p1, v17

    .line 350
    .local v4, "ctrlPointY":F
    const/16 v18, 0x4

    aget v5, p1, v18

    .line 351
    .local v5, "currentSegmentStartX":F
    const/16 v19, 0x5

    aget v6, p1, v19

    .line 355
    .local v6, "currentSegmentStartY":F
    sparse-switch p3, :sswitch_data_0

    .line 398
    .end local v0    # "incr":I
    .local v20, "incr":I
    :goto_0
    move/from16 v20, v0

    goto :goto_1

    .line 358
    .end local v20    # "incr":I
    .restart local v0    # "incr":I
    :sswitch_0
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Path;->close()V

    .line 362
    move v1, v5

    .line 363
    move v2, v6

    .line 364
    move v3, v5

    .line 365
    move v4, v6

    .line 366
    invoke-virtual {v10, v1, v2}, Landroid/graphics/Path;->moveTo(FF)V

    .line 367
    goto :goto_0

    .line 390
    :sswitch_1
    const/4 v0, 0x4

    .line 391
    goto :goto_0

    .line 374
    :sswitch_2
    const/4 v0, 0x2

    .line 375
    goto :goto_0

    .line 380
    :sswitch_3
    const/4 v0, 0x1

    .line 381
    goto :goto_0

    .line 384
    :sswitch_4
    const/4 v0, 0x6

    .line 385
    goto :goto_0

    .line 394
    :sswitch_5
    const/4 v0, 0x7

    goto :goto_0

    .line 398
    .end local v0    # "incr":I
    .restart local v20    # "incr":I
    :goto_1
    move/from16 v7, p2

    move v9, v1

    move v8, v2

    move/from16 v21, v3

    move/from16 v22, v4

    move/from16 v23, v5

    move/from16 v24, v6

    const/4 v0, 0x0

    .end local v1    # "currentX":F
    .end local v2    # "currentY":F
    .end local v3    # "ctrlPointX":F
    .end local v4    # "ctrlPointY":F
    .end local v5    # "currentSegmentStartX":F
    .end local v6    # "currentSegmentStartY":F
    .end local p2    # "previousCmd":C
    .local v0, "k":I
    .local v7, "previousCmd":C
    .local v8, "currentY":F
    .local v9, "currentX":F
    .local v21, "ctrlPointX":F
    .local v22, "ctrlPointY":F
    .local v23, "currentSegmentStartX":F
    .local v24, "currentSegmentStartY":F
    :goto_2
    move v6, v0

    .end local v0    # "k":I
    .local v6, "k":I
    array-length v0, v13

    if-ge v6, v0, :cond_e

    .line 399
    const/16 v0, 0x51

    const/16 v1, 0x43

    const/16 v2, 0x74

    const/16 v3, 0x73

    const/16 v4, 0x71

    const/16 v5, 0x63

    const/high16 v25, 0x40000000    # 2.0f

    const/4 v15, 0x0

    sparse-switch p3, :sswitch_data_1

    .line 582
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .local v11, "currentX":F
    .local v12, "currentY":F
    .local v14, "previousCmd":C
    .local v27, "k":I
    goto/16 :goto_f

    .line 447
    .end local v11    # "currentX":F
    .end local v12    # "currentY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v8    # "currentY":F
    .restart local v9    # "currentX":F
    :sswitch_6
    add-int/lit8 v0, v6, 0x0

    aget v0, v13, v0

    invoke-virtual {v10, v15, v0}, Landroid/graphics/Path;->rLineTo(FF)V

    .line 448
    add-int/lit8 v0, v6, 0x0

    aget v0, v13, v0

    add-float/2addr v8, v0

    .line 449
    nop

    .line 582
    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    :goto_3
    move/from16 v27, v6

    goto/16 :goto_7

    .line 519
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    :sswitch_7
    const/4 v1, 0x0

    .line 520
    .local v1, "reflectiveCtrlPointX":F
    const/4 v3, 0x0

    .line 521
    .local v3, "reflectiveCtrlPointY":F
    if-eq v7, v4, :cond_0

    if-eq v7, v2, :cond_0

    if-eq v7, v0, :cond_0

    const/16 v0, 0x54

    if-ne v7, v0, :cond_1

    .line 523
    :cond_0
    sub-float v1, v9, v21

    .line 524
    sub-float v3, v8, v22

    .line 526
    :cond_1
    add-int/lit8 v0, v6, 0x0

    aget v0, v13, v0

    add-int/lit8 v2, v6, 0x1

    aget v2, v13, v2

    invoke-virtual {v10, v1, v3, v0, v2}, Landroid/graphics/Path;->rQuadTo(FFFF)V

    .line 528
    add-float v0, v9, v1

    .line 529
    .end local v21    # "ctrlPointX":F
    .local v0, "ctrlPointX":F
    add-float v2, v8, v3

    .line 530
    .end local v22    # "ctrlPointY":F
    .local v2, "ctrlPointY":F
    add-int/lit8 v4, v6, 0x0

    aget v4, v13, v4

    add-float/2addr v9, v4

    .line 531
    add-int/lit8 v4, v6, 0x1

    aget v4, v13, v4

    add-float/2addr v8, v4

    .line 532
    nop

    .line 582
    move/from16 v21, v0

    move/from16 v22, v2

    goto :goto_3

    .line 473
    .end local v0    # "ctrlPointX":F
    .end local v1    # "reflectiveCtrlPointX":F
    .end local v2    # "ctrlPointY":F
    .end local v3    # "reflectiveCtrlPointY":F
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_8
    const/4 v0, 0x0

    .line 474
    .local v0, "reflectiveCtrlPointX":F
    const/4 v2, 0x0

    .line 475
    .local v2, "reflectiveCtrlPointY":F
    if-eq v7, v5, :cond_3

    if-eq v7, v3, :cond_3

    if-eq v7, v1, :cond_3

    const/16 v1, 0x53

    if-ne v7, v1, :cond_2

    goto :goto_4

    .line 480
    :cond_2
    move v15, v0

    move/from16 v25, v2

    goto :goto_5

    .line 477
    :cond_3
    :goto_4
    sub-float v0, v9, v21

    .line 478
    sub-float v1, v8, v22

    .line 480
    move v15, v0

    move/from16 v25, v1

    .end local v0    # "reflectiveCtrlPointX":F
    .end local v2    # "reflectiveCtrlPointY":F
    .local v15, "reflectiveCtrlPointX":F
    .local v25, "reflectiveCtrlPointY":F
    :goto_5
    add-int/lit8 v0, v6, 0x0

    aget v3, v13, v0

    add-int/lit8 v0, v6, 0x1

    aget v4, v13, v0

    add-int/lit8 v0, v6, 0x2

    aget v5, v13, v0

    add-int/lit8 v0, v6, 0x3

    aget v26, v13, v0

    move-object/from16 v0, p0

    move v1, v15

    move/from16 v2, v25

    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    move/from16 v6, v26

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Path;->rCubicTo(FFFFFF)V

    .line 484
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-float/2addr v0, v9

    .line 485
    .end local v21    # "ctrlPointX":F
    .local v0, "ctrlPointX":F
    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    add-float/2addr v1, v8

    .line 486
    .end local v22    # "ctrlPointY":F
    .local v1, "ctrlPointY":F
    add-int/lit8 v6, v27, 0x2

    aget v2, v13, v6

    add-float/2addr v9, v2

    .line 487
    add-int/lit8 v6, v27, 0x3

    aget v2, v13, v6

    add-float/2addr v8, v2

    .line 488
    goto/16 :goto_6

    .line 505
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .end local v15    # "reflectiveCtrlPointX":F
    .end local v25    # "reflectiveCtrlPointY":F
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_9
    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    add-int/lit8 v6, v27, 0x2

    aget v2, v13, v6

    add-int/lit8 v6, v27, 0x3

    aget v3, v13, v6

    invoke-virtual {v10, v0, v1, v2, v3}, Landroid/graphics/Path;->rQuadTo(FFFF)V

    .line 506
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-float/2addr v0, v9

    .line 507
    .end local v21    # "ctrlPointX":F
    .restart local v0    # "ctrlPointX":F
    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    add-float/2addr v1, v8

    .line 508
    .end local v22    # "ctrlPointY":F
    .restart local v1    # "ctrlPointY":F
    add-int/lit8 v6, v27, 0x2

    aget v2, v13, v6

    add-float/2addr v9, v2

    .line 509
    add-int/lit8 v6, v27, 0x3

    aget v2, v13, v6

    add-float/2addr v8, v2

    .line 510
    goto/16 :goto_6

    .line 401
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_a
    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-float/2addr v9, v0

    .line 402
    add-int/lit8 v6, v27, 0x1

    aget v0, v13, v6

    add-float/2addr v8, v0

    .line 403
    if-lez v27, :cond_4

    .line 407
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    invoke-virtual {v10, v0, v1}, Landroid/graphics/Path;->rLineTo(FF)V

    goto/16 :goto_7

    .line 409
    :cond_4
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    invoke-virtual {v10, v0, v1}, Landroid/graphics/Path;->rMoveTo(FF)V

    .line 410
    move v0, v9

    .line 411
    .end local v23    # "currentSegmentStartX":F
    .local v0, "currentSegmentStartX":F
    move v1, v8

    .line 413
    .end local v24    # "currentSegmentStartY":F
    .local v1, "currentSegmentStartY":F
    nop

    .line 582
    move/from16 v23, v0

    move/from16 v24, v1

    goto :goto_7

    .line 429
    .end local v0    # "currentSegmentStartX":F
    .end local v1    # "currentSegmentStartY":F
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v23    # "currentSegmentStartX":F
    .restart local v24    # "currentSegmentStartY":F
    :sswitch_b
    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    invoke-virtual {v10, v0, v1}, Landroid/graphics/Path;->rLineTo(FF)V

    .line 430
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-float/2addr v9, v0

    .line 431
    add-int/lit8 v6, v27, 0x1

    aget v0, v13, v6

    add-float/2addr v8, v0

    .line 432
    goto :goto_7

    .line 439
    .end local v27    # "k":I
    .restart local v6    # "k":I
    :sswitch_c
    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    invoke-virtual {v10, v0, v15}, Landroid/graphics/Path;->rLineTo(FF)V

    .line 440
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-float/2addr v9, v0

    .line 441
    goto :goto_7

    .line 455
    .end local v27    # "k":I
    .restart local v6    # "k":I
    :sswitch_d
    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v1, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v2, v13, v6

    add-int/lit8 v6, v27, 0x2

    aget v3, v13, v6

    add-int/lit8 v6, v27, 0x3

    aget v4, v13, v6

    add-int/lit8 v6, v27, 0x4

    aget v5, v13, v6

    add-int/lit8 v6, v27, 0x5

    aget v6, v13, v6

    move-object/from16 v0, p0

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Path;->rCubicTo(FFFFFF)V

    .line 458
    add-int/lit8 v6, v27, 0x2

    aget v0, v13, v6

    add-float/2addr v0, v9

    .line 459
    .end local v21    # "ctrlPointX":F
    .local v0, "ctrlPointX":F
    add-int/lit8 v6, v27, 0x3

    aget v1, v13, v6

    add-float/2addr v1, v8

    .line 460
    .end local v22    # "ctrlPointY":F
    .local v1, "ctrlPointY":F
    add-int/lit8 v6, v27, 0x4

    aget v2, v13, v6

    add-float/2addr v9, v2

    .line 461
    add-int/lit8 v6, v27, 0x5

    aget v2, v13, v6

    add-float/2addr v8, v2

    .line 463
    nop

    .line 582
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .end local v7    # "previousCmd":C
    .restart local v14    # "previousCmd":C
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :goto_6
    move/from16 v21, v0

    move/from16 v22, v1

    :goto_7
    move v14, v7

    goto/16 :goto_f

    .line 550
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    :sswitch_e
    move/from16 v27, v6

    .end local v6    # "k":I
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x5

    aget v0, v13, v6

    add-float v3, v0, v9

    add-int/lit8 v6, v27, 0x6

    aget v0, v13, v6

    add-float v4, v0, v8

    add-int/lit8 v6, v27, 0x0

    aget v5, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v6, v13, v6

    add-int/lit8 v0, v27, 0x2

    aget v25, v13, v0

    add-int/lit8 v0, v27, 0x3

    aget v0, v13, v0

    cmpl-float v0, v0, v15

    if-eqz v0, :cond_5

    const/16 v26, 0x1

    goto :goto_8

    :cond_5
    const/16 v26, 0x0

    :goto_8
    add-int/lit8 v0, v27, 0x4

    aget v0, v13, v0

    cmpl-float v0, v0, v15

    if-eqz v0, :cond_6

    const/4 v15, 0x1

    goto :goto_9

    :cond_6
    const/4 v15, 0x0

    :goto_9
    move-object/from16 v0, p0

    move v1, v9

    move v2, v8

    move v14, v7

    .end local v7    # "previousCmd":C
    .restart local v14    # "previousCmd":C
    move/from16 v7, v25

    move v12, v8

    .end local v8    # "currentY":F
    .restart local v12    # "currentY":F
    move/from16 v8, v26

    move v11, v9

    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    move v9, v15

    invoke-static/range {v0 .. v9}, Landroid/support/graphics/drawable/PathParser$PathDataNode;->drawArc(Landroid/graphics/Path;FFFFFFFZZ)V

    .line 560
    add-int/lit8 v6, v27, 0x5

    aget v0, v13, v6

    add-float v9, v11, v0

    .line 561
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x6

    aget v0, v13, v6

    add-float v8, v12, v0

    .line 562
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    move v0, v9

    .line 563
    .end local v21    # "ctrlPointX":F
    .restart local v0    # "ctrlPointX":F
    move v1, v8

    .line 564
    .end local v22    # "ctrlPointY":F
    .restart local v1    # "ctrlPointY":F
    goto/16 :goto_c

    .line 451
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_f
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    invoke-virtual {v10, v11, v0}, Landroid/graphics/Path;->lineTo(FF)V

    .line 452
    add-int/lit8 v6, v27, 0x0

    aget v8, v13, v6

    .line 453
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    goto/16 :goto_f

    .line 534
    .end local v11    # "currentX":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v9    # "currentX":F
    :sswitch_10
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    move v1, v11

    .line 535
    .local v1, "reflectiveCtrlPointX":F
    move v3, v12

    .line 536
    .restart local v3    # "reflectiveCtrlPointY":F
    if-eq v14, v4, :cond_7

    if-eq v14, v2, :cond_7

    if-eq v14, v0, :cond_7

    const/16 v0, 0x54

    if-ne v14, v0, :cond_8

    .line 538
    :cond_7
    mul-float v9, v11, v25

    sub-float v1, v9, v21

    .line 539
    mul-float v8, v12, v25

    sub-float v3, v8, v22

    .line 541
    :cond_8
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v2, v13, v6

    invoke-virtual {v10, v1, v3, v0, v2}, Landroid/graphics/Path;->quadTo(FFFF)V

    .line 543
    move v0, v1

    .line 544
    .end local v21    # "ctrlPointX":F
    .restart local v0    # "ctrlPointX":F
    move v2, v3

    .line 545
    .end local v22    # "ctrlPointY":F
    .local v2, "ctrlPointY":F
    add-int/lit8 v6, v27, 0x0

    aget v9, v13, v6

    .line 546
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x1

    aget v8, v13, v6

    .line 547
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    nop

    .line 582
    move/from16 v21, v0

    move/from16 v22, v2

    goto/16 :goto_f

    .line 490
    .end local v0    # "ctrlPointX":F
    .end local v1    # "reflectiveCtrlPointX":F
    .end local v2    # "ctrlPointY":F
    .end local v3    # "reflectiveCtrlPointY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_11
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    move v0, v11

    .line 491
    .local v0, "reflectiveCtrlPointX":F
    move v2, v12

    .line 492
    .local v2, "reflectiveCtrlPointY":F
    if-eq v14, v5, :cond_a

    if-eq v14, v3, :cond_a

    if-eq v14, v1, :cond_a

    const/16 v1, 0x53

    if-ne v14, v1, :cond_9

    goto :goto_a

    .line 497
    :cond_9
    move v9, v0

    move v8, v2

    goto :goto_b

    .line 494
    :cond_a
    :goto_a
    mul-float v9, v11, v25

    sub-float v9, v9, v21

    .line 495
    .end local v0    # "reflectiveCtrlPointX":F
    .local v9, "reflectiveCtrlPointX":F
    mul-float v8, v12, v25

    sub-float v8, v8, v22

    .line 497
    .end local v2    # "reflectiveCtrlPointY":F
    .local v8, "reflectiveCtrlPointY":F
    :goto_b
    add-int/lit8 v6, v27, 0x0

    aget v3, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v4, v13, v6

    add-int/lit8 v6, v27, 0x2

    aget v5, v13, v6

    add-int/lit8 v6, v27, 0x3

    aget v6, v13, v6

    move-object/from16 v0, p0

    move v1, v9

    move v2, v8

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Path;->cubicTo(FFFFFF)V

    .line 499
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    .line 500
    .end local v21    # "ctrlPointX":F
    .local v0, "ctrlPointX":F
    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    .line 501
    .end local v22    # "ctrlPointY":F
    .local v1, "ctrlPointY":F
    add-int/lit8 v6, v27, 0x2

    aget v2, v13, v6

    .line 502
    .end local v11    # "currentX":F
    .local v2, "currentX":F
    add-int/lit8 v6, v27, 0x3

    aget v3, v13, v6

    .line 503
    .end local v12    # "currentY":F
    .local v3, "currentY":F
    nop

    .line 582
    move/from16 v21, v0

    move/from16 v22, v1

    move v9, v2

    move v8, v3

    goto/16 :goto_f

    .line 512
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .end local v2    # "currentX":F
    .end local v3    # "currentY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .local v8, "currentY":F
    .local v9, "currentX":F
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_12
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    add-int/lit8 v6, v27, 0x2

    aget v2, v13, v6

    add-int/lit8 v6, v27, 0x3

    aget v3, v13, v6

    invoke-virtual {v10, v0, v1, v2, v3}, Landroid/graphics/Path;->quadTo(FFFF)V

    .line 513
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    .line 514
    .end local v21    # "ctrlPointX":F
    .restart local v0    # "ctrlPointX":F
    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    .line 515
    .end local v22    # "ctrlPointY":F
    .restart local v1    # "ctrlPointY":F
    add-int/lit8 v6, v27, 0x2

    aget v9, v13, v6

    .line 516
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x3

    aget v8, v13, v6

    .line 517
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    goto/16 :goto_c

    .line 415
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :sswitch_13
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v9, v13, v6

    .line 416
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x1

    aget v8, v13, v6

    .line 417
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    if-lez v27, :cond_b

    .line 421
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    invoke-virtual {v10, v0, v1}, Landroid/graphics/Path;->lineTo(FF)V

    goto/16 :goto_f

    .line 423
    :cond_b
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    invoke-virtual {v10, v0, v1}, Landroid/graphics/Path;->moveTo(FF)V

    .line 424
    move v0, v9

    .line 425
    .end local v23    # "currentSegmentStartX":F
    .local v0, "currentSegmentStartX":F
    move v1, v8

    .line 427
    .end local v24    # "currentSegmentStartY":F
    .local v1, "currentSegmentStartY":F
    nop

    .line 582
    move/from16 v23, v0

    move/from16 v24, v1

    goto/16 :goto_f

    .line 434
    .end local v0    # "currentSegmentStartX":F
    .end local v1    # "currentSegmentStartY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v23    # "currentSegmentStartX":F
    .restart local v24    # "currentSegmentStartY":F
    :sswitch_14
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v1, v13, v6

    invoke-virtual {v10, v0, v1}, Landroid/graphics/Path;->lineTo(FF)V

    .line 435
    add-int/lit8 v6, v27, 0x0

    aget v9, v13, v6

    .line 436
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x1

    aget v8, v13, v6

    .line 437
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    goto/16 :goto_f

    .line 443
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    :sswitch_15
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v0, v13, v6

    invoke-virtual {v10, v0, v12}, Landroid/graphics/Path;->lineTo(FF)V

    .line 444
    add-int/lit8 v6, v27, 0x0

    aget v9, v13, v6

    .line 445
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    goto/16 :goto_f

    .line 465
    .end local v12    # "currentY":F
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    .restart local v8    # "currentY":F
    :sswitch_16
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x0

    aget v1, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v2, v13, v6

    add-int/lit8 v6, v27, 0x2

    aget v3, v13, v6

    add-int/lit8 v6, v27, 0x3

    aget v4, v13, v6

    add-int/lit8 v6, v27, 0x4

    aget v5, v13, v6

    add-int/lit8 v6, v27, 0x5

    aget v6, v13, v6

    move-object/from16 v0, p0

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Path;->cubicTo(FFFFFF)V

    .line 467
    add-int/lit8 v6, v27, 0x4

    aget v9, v13, v6

    .line 468
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x5

    aget v8, v13, v6

    .line 469
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    add-int/lit8 v6, v27, 0x2

    aget v0, v13, v6

    .line 470
    .end local v21    # "ctrlPointX":F
    .local v0, "ctrlPointX":F
    add-int/lit8 v6, v27, 0x3

    aget v1, v13, v6

    .line 471
    .end local v22    # "ctrlPointY":F
    .local v1, "ctrlPointY":F
    nop

    .line 582
    .end local v0    # "ctrlPointX":F
    .end local v1    # "ctrlPointY":F
    .restart local v21    # "ctrlPointX":F
    .restart local v22    # "ctrlPointY":F
    :goto_c
    move/from16 v21, v0

    move/from16 v22, v1

    goto :goto_f

    .line 566
    .end local v14    # "previousCmd":C
    .end local v27    # "k":I
    .restart local v6    # "k":I
    .restart local v7    # "previousCmd":C
    :sswitch_17
    move/from16 v27, v6

    move v14, v7

    move v12, v8

    move v11, v9

    .end local v6    # "k":I
    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    .restart local v27    # "k":I
    add-int/lit8 v6, v27, 0x5

    aget v3, v13, v6

    add-int/lit8 v6, v27, 0x6

    aget v4, v13, v6

    add-int/lit8 v6, v27, 0x0

    aget v5, v13, v6

    add-int/lit8 v6, v27, 0x1

    aget v6, v13, v6

    add-int/lit8 v0, v27, 0x2

    aget v7, v13, v0

    add-int/lit8 v0, v27, 0x3

    aget v0, v13, v0

    cmpl-float v0, v0, v15

    if-eqz v0, :cond_c

    const/4 v8, 0x1

    goto :goto_d

    :cond_c
    const/4 v8, 0x0

    :goto_d
    add-int/lit8 v0, v27, 0x4

    aget v0, v13, v0

    cmpl-float v0, v0, v15

    if-eqz v0, :cond_d

    const/4 v9, 0x1

    goto :goto_e

    :cond_d
    const/4 v9, 0x0

    :goto_e
    move-object/from16 v0, p0

    move v1, v11

    move v2, v12

    invoke-static/range {v0 .. v9}, Landroid/support/graphics/drawable/PathParser$PathDataNode;->drawArc(Landroid/graphics/Path;FFFFFFFZZ)V

    .line 576
    add-int/lit8 v6, v27, 0x5

    aget v9, v13, v6

    .line 577
    .end local v11    # "currentX":F
    .restart local v9    # "currentX":F
    add-int/lit8 v6, v27, 0x6

    aget v8, v13, v6

    .line 578
    .end local v12    # "currentY":F
    .restart local v8    # "currentY":F
    move v0, v9

    .line 579
    .end local v21    # "ctrlPointX":F
    .restart local v0    # "ctrlPointX":F
    move v1, v8

    goto :goto_c

    .line 582
    .end local v0    # "ctrlPointX":F
    .restart local v21    # "ctrlPointX":F
    :goto_f
    move/from16 v7, p3

    .line 398
    .end local v14    # "previousCmd":C
    .restart local v7    # "previousCmd":C
    add-int v0, v27, v20

    const/4 v14, 0x0

    const/4 v15, 0x1

    goto/16 :goto_2

    .line 584
    .end local v27    # "k":I
    :cond_e
    move v14, v7

    move v12, v8

    move v11, v9

    .end local v7    # "previousCmd":C
    .end local v8    # "currentY":F
    .end local v9    # "currentX":F
    .restart local v11    # "currentX":F
    .restart local v12    # "currentY":F
    .restart local v14    # "previousCmd":C
    move v1, v11

    const/4 v2, 0x0

    .end local v11    # "currentX":F
    .local v1, "currentX":F
    aput v1, p1, v2

    .line 585
    const/4 v2, 0x1

    aput v12, p1, v2

    .line 586
    aput v21, p1, v16

    .line 587
    aput v22, p1, v17

    .line 588
    aput v23, p1, v18

    .line 589
    aput v24, p1, v19

    .line 590
    return-void

    :sswitch_data_0
    .sparse-switch
        0x41 -> :sswitch_5
        0x43 -> :sswitch_4
        0x48 -> :sswitch_3
        0x4c -> :sswitch_2
        0x4d -> :sswitch_2
        0x51 -> :sswitch_1
        0x53 -> :sswitch_1
        0x54 -> :sswitch_2
        0x56 -> :sswitch_3
        0x5a -> :sswitch_0
        0x61 -> :sswitch_5
        0x63 -> :sswitch_4
        0x68 -> :sswitch_3
        0x6c -> :sswitch_2
        0x6d -> :sswitch_2
        0x71 -> :sswitch_1
        0x73 -> :sswitch_1
        0x74 -> :sswitch_2
        0x76 -> :sswitch_3
        0x7a -> :sswitch_0
    .end sparse-switch

    :sswitch_data_1
    .sparse-switch
        0x41 -> :sswitch_17
        0x43 -> :sswitch_16
        0x48 -> :sswitch_15
        0x4c -> :sswitch_14
        0x4d -> :sswitch_13
        0x51 -> :sswitch_12
        0x53 -> :sswitch_11
        0x54 -> :sswitch_10
        0x56 -> :sswitch_f
        0x61 -> :sswitch_e
        0x63 -> :sswitch_d
        0x68 -> :sswitch_c
        0x6c -> :sswitch_b
        0x6d -> :sswitch_a
        0x71 -> :sswitch_9
        0x73 -> :sswitch_8
        0x74 -> :sswitch_7
        0x76 -> :sswitch_6
    .end sparse-switch
.end method

.method private static arcToBezier(Landroid/graphics/Path;DDDDDDDDD)V
    .locals 80
    .param p0, "p"    # Landroid/graphics/Path;
    .param p1, "cx"    # D
    .param p3, "cy"    # D
    .param p5, "a"    # D
    .param p7, "b"    # D
    .param p9, "e1x"    # D
    .param p11, "e1y"    # D
    .param p13, "theta"    # D
    .param p15, "start"    # D
    .param p17, "sweep"    # D

    .line 697
    move-wide/from16 v0, p5

    const-wide/high16 v6, 0x4010000000000000L    # 4.0

    mul-double v8, p17, v6

    const-wide v10, 0x400921fb54442d18L    # Math.PI

    div-double/2addr v8, v10

    invoke-static {v8, v9}, Ljava/lang/Math;->abs(D)D

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v8

    double-to-int v8, v8

    .line 699
    .local v8, "numSegments":I
    move-wide/from16 v9, p15

    .line 700
    .local v9, "eta1":D
    invoke-static/range {p13 .. p14}, Ljava/lang/Math;->cos(D)D

    move-result-wide v11

    .line 701
    .local v11, "cosTheta":D
    invoke-static/range {p13 .. p14}, Ljava/lang/Math;->sin(D)D

    move-result-wide v13

    .line 702
    .local v13, "sinTheta":D
    invoke-static {v9, v10}, Ljava/lang/Math;->cos(D)D

    move-result-wide v15

    .line 703
    .local v15, "cosEta1":D
    invoke-static {v9, v10}, Ljava/lang/Math;->sin(D)D

    move-result-wide v17

    .line 704
    .local v17, "sinEta1":D
    neg-double v6, v0

    mul-double v6, v6, v11

    mul-double v6, v6, v17

    mul-double v21, p7, v13

    mul-double v21, v21, v15

    sub-double v6, v6, v21

    .line 705
    .local v6, "ep1x":D
    move-wide/from16 v23, v6

    .end local v6    # "ep1x":D
    .local v23, "ep1x":D
    neg-double v6, v0

    mul-double v6, v6, v13

    mul-double v6, v6, v17

    mul-double v21, p7, v11

    mul-double v21, v21, v15

    add-double v6, v6, v21

    .line 707
    .local v6, "ep1y":D
    move-wide/from16 v25, v6

    .end local v6    # "ep1y":D
    .local v25, "ep1y":D
    int-to-double v6, v8

    invoke-static {v6, v7}, Ljava/lang/Double;->isNaN(D)Z

    div-double v6, p17, v6

    .line 708
    .local v6, "anglePerSegment":D
    const/16 v21, 0x0

    move-wide/from16 v4, p9

    move-wide/from16 v27, p11

    .end local p9    # "e1x":D
    .end local p11    # "e1y":D
    .local v4, "e1x":D
    .local v21, "i":I
    .local v27, "e1y":D
    :goto_0
    move/from16 v29, v21

    move-wide/from16 v30, v15

    move/from16 v15, v29

    .end local v21    # "i":I
    .local v15, "i":I
    .local v30, "cosEta1":D
    if-ge v15, v8, :cond_0

    .line 709
    add-double v21, v9, v6

    .line 710
    .local v21, "eta2":D
    invoke-static/range {v21 .. v22}, Ljava/lang/Math;->sin(D)D

    move-result-wide v32

    .line 711
    .local v32, "sinEta2":D
    invoke-static/range {v21 .. v22}, Ljava/lang/Math;->cos(D)D

    move-result-wide v34

    .line 712
    .local v34, "cosEta2":D
    mul-double v36, v0, v11

    mul-double v36, v36, v34

    add-double v36, p1, v36

    mul-double v38, p7, v13

    mul-double v38, v38, v32

    move-wide/from16 v40, v6

    .end local v6    # "anglePerSegment":D
    .local v40, "anglePerSegment":D
    sub-double v6, v36, v38

    .line 713
    .local v6, "e2x":D
    mul-double v36, v0, v13

    mul-double v36, v36, v34

    add-double v36, p3, v36

    mul-double v42, p7, v11

    mul-double v42, v42, v32

    move-wide/from16 v44, v6

    .end local v6    # "e2x":D
    .local v44, "e2x":D
    add-double v6, v36, v42

    .line 714
    .local v6, "e2y":D
    move-wide/from16 v46, v6

    .end local v6    # "e2y":D
    .local v46, "e2y":D
    neg-double v6, v0

    mul-double v6, v6, v11

    mul-double v6, v6, v32

    mul-double v36, p7, v13

    mul-double v36, v36, v34

    sub-double v6, v6, v36

    .line 715
    .local v6, "ep2x":D
    move-wide/from16 v48, v6

    .end local v6    # "ep2x":D
    .local v48, "ep2x":D
    neg-double v6, v0

    mul-double v6, v6, v13

    mul-double v6, v6, v32

    mul-double v36, p7, v11

    mul-double v36, v36, v34

    add-double v6, v6, v36

    .line 716
    .local v6, "ep2y":D
    sub-double v36, v21, v9

    const-wide/high16 v42, 0x4000000000000000L    # 2.0

    div-double v36, v36, v42

    invoke-static/range {v36 .. v37}, Ljava/lang/Math;->tan(D)D

    move-result-wide v36

    .line 717
    .local v36, "tanDiff2":D
    sub-double v42, v21, v9

    .line 718
    invoke-static/range {v42 .. v43}, Ljava/lang/Math;->sin(D)D

    move-result-wide v42

    const-wide/high16 v50, 0x4008000000000000L    # 3.0

    mul-double v52, v36, v50

    mul-double v52, v52, v36

    const-wide/high16 v19, 0x4010000000000000L    # 4.0

    add-double v52, v52, v19

    invoke-static/range {v52 .. v53}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v52

    const-wide/high16 v54, 0x3ff0000000000000L    # 1.0

    sub-double v52, v52, v54

    mul-double v42, v42, v52

    div-double v42, v42, v50

    .line 719
    .local v42, "alpha":D
    mul-double v50, v42, v23

    add-double v0, v4, v50

    .line 720
    .local v0, "q1x":D
    mul-double v50, v42, v25

    move/from16 v56, v8

    move-wide/from16 v57, v9

    move-wide/from16 v2, v27

    .end local v8    # "numSegments":I
    .end local v9    # "eta1":D
    .end local v27    # "e1y":D
    .local v2, "e1y":D
    .local v56, "numSegments":I
    .local v57, "eta1":D
    add-double v8, v2, v50

    .line 721
    .local v8, "q1y":D
    mul-double v27, v42, v48

    move-wide/from16 v59, v11

    .end local v11    # "cosTheta":D
    .local v59, "cosTheta":D
    sub-double v10, v44, v27

    .line 722
    .local v10, "q2x":D
    mul-double v27, v42, v6

    move-wide/from16 v61, v13

    .end local v13    # "sinTheta":D
    .local v61, "sinTheta":D
    sub-double v12, v46, v27

    .line 726
    .local v12, "q2y":D
    double-to-float v14, v0

    move-wide/from16 v63, v0

    .end local v0    # "q1x":D
    .local v63, "q1x":D
    double-to-float v0, v4

    sub-float v0, v14, v0

    .line 727
    .local v0, "delta_q1x":F
    double-to-float v1, v8

    double-to-float v14, v2

    sub-float/2addr v1, v14

    .line 728
    .local v1, "delta_q1y":F
    double-to-float v14, v10

    move-wide/from16 v72, v8

    .end local v8    # "q1y":D
    .local v72, "q1y":D
    double-to-float v8, v4

    sub-float v8, v14, v8

    .line 729
    .local v8, "delta_q2x":F
    double-to-float v9, v12

    double-to-float v14, v2

    sub-float/2addr v9, v14

    .line 730
    .local v9, "delta_q2y":F
    move-wide/from16 v74, v10

    move-wide/from16 v10, v44

    .end local v44    # "e2x":D
    .local v10, "e2x":D
    .local v74, "q2x":D
    double-to-float v14, v10

    move-wide/from16 v76, v12

    .end local v12    # "q2y":D
    .local v76, "q2y":D
    double-to-float v12, v4

    sub-float v12, v14, v12

    .line 731
    .local v12, "delta_e2x":F
    move-wide/from16 v78, v4

    move-wide/from16 v13, v46

    .end local v4    # "e1x":D
    .end local v46    # "e2y":D
    .local v13, "e2y":D
    .local v78, "e1x":D
    double-to-float v4, v13

    double-to-float v5, v2

    sub-float/2addr v4, v5

    .line 733
    .local v4, "delta_e2y":F
    move-object/from16 v65, p0

    move/from16 v66, v0

    move/from16 v67, v1

    move/from16 v68, v8

    move/from16 v69, v9

    move/from16 v70, v12

    move/from16 v71, v4

    invoke-virtual/range {v65 .. v71}, Landroid/graphics/Path;->rCubicTo(FFFFFF)V

    .line 735
    move-wide/from16 v27, v21

    .line 736
    .end local v57    # "eta1":D
    .local v27, "eta1":D
    nop

    .line 737
    .end local v78    # "e1x":D
    .local v44, "e1x":D
    move-wide v2, v13

    .line 738
    move-wide/from16 v23, v48

    .line 739
    move-wide/from16 v25, v6

    .line 708
    .end local v0    # "delta_q1x":F
    .end local v1    # "delta_q1y":F
    .end local v4    # "delta_e2y":F
    .end local v6    # "ep2y":D
    .end local v8    # "delta_q2x":F
    .end local v9    # "delta_q2y":F
    .end local v10    # "e2x":D
    .end local v12    # "delta_e2x":F
    .end local v13    # "e2y":D
    .end local v21    # "eta2":D
    .end local v32    # "sinEta2":D
    .end local v34    # "cosEta2":D
    .end local v36    # "tanDiff2":D
    .end local v42    # "alpha":D
    .end local v48    # "ep2x":D
    .end local v63    # "q1x":D
    .end local v72    # "q1y":D
    .end local v74    # "q2x":D
    .end local v76    # "q2y":D
    add-int/lit8 v21, v15, 0x1

    move-wide/from16 v9, v27

    move-wide/from16 v15, v30

    move-wide/from16 v6, v40

    move-wide/from16 v4, v44

    move/from16 v8, v56

    move-wide/from16 v11, v59

    move-wide/from16 v13, v61

    move-wide/from16 v0, p5

    move-wide/from16 v27, v2

    goto/16 :goto_0

    .line 741
    .end local v2    # "e1y":D
    .end local v15    # "i":I
    .end local v40    # "anglePerSegment":D
    .end local v44    # "e1x":D
    .end local v56    # "numSegments":I
    .end local v59    # "cosTheta":D
    .end local v61    # "sinTheta":D
    .local v4, "e1x":D
    .local v6, "anglePerSegment":D
    .local v8, "numSegments":I
    .local v9, "eta1":D
    .restart local v11    # "cosTheta":D
    .local v13, "sinTheta":D
    .local v27, "e1y":D
    :cond_0
    move-wide/from16 v78, v4

    move-wide/from16 v40, v6

    move/from16 v56, v8

    move-wide/from16 v57, v9

    move-wide/from16 v59, v11

    move-wide/from16 v61, v13

    move-wide/from16 v2, v27

    .end local v4    # "e1x":D
    .end local v6    # "anglePerSegment":D
    .end local v8    # "numSegments":I
    .end local v9    # "eta1":D
    .end local v11    # "cosTheta":D
    .end local v13    # "sinTheta":D
    .end local v27    # "e1y":D
    .restart local v2    # "e1y":D
    .restart local v40    # "anglePerSegment":D
    .restart local v56    # "numSegments":I
    .restart local v57    # "eta1":D
    .restart local v59    # "cosTheta":D
    .restart local v61    # "sinTheta":D
    .restart local v78    # "e1x":D
    return-void
.end method

.method private static drawArc(Landroid/graphics/Path;FFFFFFFZZ)V
    .locals 73
    .param p0, "p"    # Landroid/graphics/Path;
    .param p1, "x0"    # F
    .param p2, "y0"    # F
    .param p3, "x1"    # F
    .param p4, "y1"    # F
    .param p5, "a"    # F
    .param p6, "b"    # F
    .param p7, "theta"    # F
    .param p8, "isMoreThanHalf"    # Z
    .param p9, "isPositiveArc"    # Z

    .line 604
    move/from16 v10, p1

    move/from16 v11, p2

    move/from16 v12, p3

    move/from16 v13, p4

    move/from16 v14, p5

    move/from16 v15, p6

    move/from16 v9, p9

    move/from16 v8, p7

    float-to-double v0, v8

    invoke-static {v0, v1}, Ljava/lang/Math;->toRadians(D)D

    move-result-wide v35

    .line 606
    .local v35, "thetaD":D
    invoke-static/range {v35 .. v36}, Ljava/lang/Math;->cos(D)D

    move-result-wide v37

    .line 607
    .local v37, "cosTheta":D
    invoke-static/range {v35 .. v36}, Ljava/lang/Math;->sin(D)D

    move-result-wide v39

    .line 610
    .local v39, "sinTheta":D
    float-to-double v0, v10

    invoke-static {v0, v1}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v0, v0, v37

    float-to-double v2, v11

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v39

    add-double/2addr v0, v2

    float-to-double v2, v14

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    div-double v41, v0, v2

    .line 611
    .local v41, "x0p":D
    neg-float v0, v10

    float-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v0, v0, v39

    float-to-double v2, v11

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v37

    add-double/2addr v0, v2

    float-to-double v2, v15

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    div-double v43, v0, v2

    .line 612
    .local v43, "y0p":D
    float-to-double v0, v12

    invoke-static {v0, v1}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v0, v0, v37

    float-to-double v2, v13

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v39

    add-double/2addr v0, v2

    float-to-double v2, v14

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    div-double v45, v0, v2

    .line 613
    .local v45, "x1p":D
    neg-float v0, v12

    float-to-double v0, v0

    invoke-static {v0, v1}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v0, v0, v39

    float-to-double v2, v13

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v37

    add-double/2addr v0, v2

    float-to-double v2, v15

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    div-double v47, v0, v2

    .line 616
    .local v47, "y1p":D
    sub-double v49, v41, v45

    .line 617
    .local v49, "dx":D
    sub-double v51, v43, v47

    .line 618
    .local v51, "dy":D
    add-double v0, v41, v45

    const-wide/high16 v2, 0x4000000000000000L    # 2.0

    div-double v53, v0, v2

    .line 619
    .local v53, "xm":D
    add-double v0, v43, v47

    div-double v55, v0, v2

    .line 621
    .local v55, "ym":D
    mul-double v0, v49, v49

    mul-double v2, v51, v51

    add-double v6, v0, v2

    .line 622
    .local v6, "dsq":D
    const-wide/16 v0, 0x0

    cmpl-double v2, v6, v0

    if-nez v2, :cond_0

    .line 623
    const-string v0, "PathParser"

    const-string v1, " Points are coincident"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 624
    return-void

    .line 626
    :cond_0
    const-wide/high16 v2, 0x3ff0000000000000L    # 1.0

    div-double/2addr v2, v6

    const-wide/high16 v4, 0x3fd0000000000000L    # 0.25

    sub-double v57, v2, v4

    .line 627
    .local v57, "disc":D
    cmpg-double v2, v57, v0

    if-gez v2, :cond_1

    .line 628
    const-string v0, "PathParser"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Points are too far apart "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v6, v7}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 629
    invoke-static {v6, v7}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v0

    const-wide v2, 0x3ffffff583a53b8eL    # 1.99999

    div-double/2addr v0, v2

    double-to-float v5, v0

    .line 630
    .local v5, "adjust":F
    mul-float v16, v14, v5

    mul-float v17, v15, v5

    move-object/from16 v0, p0

    move/from16 v1, p1

    move/from16 v2, p2

    move/from16 v3, p3

    move/from16 v4, p4

    move/from16 v18, v5

    .end local v5    # "adjust":F
    .local v18, "adjust":F
    move/from16 v5, v16

    move-wide/from16 v59, v6

    .end local v6    # "dsq":D
    .local v59, "dsq":D
    move/from16 v6, v17

    move/from16 v7, p7

    move/from16 v8, p8

    move v12, v9

    move/from16 v9, p9

    invoke-static/range {v0 .. v9}, Landroid/support/graphics/drawable/PathParser$PathDataNode;->drawArc(Landroid/graphics/Path;FFFFFFFZZ)V

    .line 632
    return-void

    .line 634
    .end local v18    # "adjust":F
    .end local v59    # "dsq":D
    .restart local v6    # "dsq":D
    :cond_1
    move-wide/from16 v59, v6

    move v12, v9

    .end local v6    # "dsq":D
    .restart local v59    # "dsq":D
    invoke-static/range {v57 .. v58}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v2

    .line 635
    .local v2, "s":D
    mul-double v4, v2, v49

    .line 636
    .local v4, "sdx":D
    mul-double v6, v2, v51

    .line 639
    .local v6, "sdy":D
    move/from16 v8, p8

    if-ne v8, v12, :cond_2

    .line 640
    sub-double v16, v53, v6

    .line 641
    .local v16, "cx":D
    add-double v18, v55, v4

    goto :goto_0

    .line 643
    .end local v16    # "cx":D
    :cond_2
    const/4 v9, 0x0

    add-double v16, v53, v6

    .line 644
    .restart local v16    # "cx":D
    sub-double v18, v55, v4

    .line 647
    .local v18, "cy":D
    :goto_0
    const/4 v9, 0x0

    sub-double v0, v43, v18

    move-wide/from16 v63, v2

    .end local v2    # "s":D
    .local v63, "s":D
    sub-double v2, v41, v16

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->atan2(DD)D

    move-result-wide v0

    .line 649
    .local v0, "eta0":D
    sub-double v2, v47, v18

    move-wide/from16 v65, v4

    .end local v4    # "sdx":D
    .local v65, "sdx":D
    sub-double v4, v45, v16

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->atan2(DD)D

    move-result-wide v2

    .line 651
    .local v2, "eta1":D
    sub-double v4, v2, v0

    .line 652
    .local v4, "sweep":D
    const-wide/16 v20, 0x0

    cmpl-double v9, v4, v20

    if-ltz v9, :cond_3

    const/4 v9, 0x1

    goto :goto_1

    :cond_3
    const/4 v9, 0x0

    :goto_1
    if-eq v12, v9, :cond_5

    .line 653
    const-wide v20, 0x401921fb54442d18L    # 6.283185307179586

    const-wide/16 v22, 0x0

    cmpl-double v9, v4, v22

    if-lez v9, :cond_4

    .line 654
    sub-double v4, v4, v20

    goto :goto_2

    .line 656
    :cond_4
    const/4 v9, 0x0

    add-double v4, v4, v20

    .line 660
    :cond_5
    :goto_2
    move-wide/from16 v67, v2

    .end local v2    # "eta1":D
    .local v67, "eta1":D
    float-to-double v2, v14

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v16, v16, v2

    .line 661
    float-to-double v2, v15

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v18

    .line 662
    .end local v18    # "cy":D
    .local v2, "cy":D
    move-wide/from16 v61, v16

    .line 663
    .local v61, "tcx":D
    mul-double v18, v16, v37

    mul-double v20, v2, v39

    sub-double v69, v18, v20

    .end local v16    # "cx":D
    .local v69, "cx":D
    move-wide/from16 v17, v69

    .line 664
    mul-double v19, v61, v39

    mul-double v21, v2, v37

    add-double v2, v19, v21

    move-wide/from16 v19, v2

    .line 666
    move-wide/from16 v71, v2

    .end local v2    # "cy":D
    .local v71, "cy":D
    float-to-double v2, v14

    move-wide/from16 v21, v2

    float-to-double v2, v15

    move-wide/from16 v23, v2

    float-to-double v2, v10

    move-wide/from16 v25, v2

    float-to-double v2, v11

    move-wide/from16 v27, v2

    move-object/from16 v16, p0

    move-wide/from16 v29, v35

    move-wide/from16 v31, v0

    move-wide/from16 v33, v4

    invoke-static/range {v16 .. v34}, Landroid/support/graphics/drawable/PathParser$PathDataNode;->arcToBezier(Landroid/graphics/Path;DDDDDDDDD)V

    .line 667
    return-void
.end method

.method public static nodesToPath([Landroid/support/graphics/drawable/PathParser$PathDataNode;Landroid/graphics/Path;)V
    .locals 5
    .param p0, "node"    # [Landroid/support/graphics/drawable/PathParser$PathDataNode;
    .param p1, "path"    # Landroid/graphics/Path;

    .line 317
    const/4 v0, 0x6

    new-array v0, v0, [F

    .line 318
    .local v0, "current":[F
    const/16 v1, 0x6d

    .line 319
    .local v1, "previousCommand":C
    const/4 v2, 0x0

    .local v2, "i":I
    :goto_0
    array-length v3, p0

    if-ge v2, v3, :cond_0

    .line 320
    aget-object v3, p0, v2

    iget-char v3, v3, Landroid/support/graphics/drawable/PathParser$PathDataNode;->type:C

    aget-object v4, p0, v2

    iget-object v4, v4, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    invoke-static {p1, v0, v1, v3, v4}, Landroid/support/graphics/drawable/PathParser$PathDataNode;->addCommand(Landroid/graphics/Path;[FCC[F)V

    .line 321
    aget-object v3, p0, v2

    iget-char v1, v3, Landroid/support/graphics/drawable/PathParser$PathDataNode;->type:C

    .line 319
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 323
    .end local v2    # "i":I
    :cond_0
    return-void
.end method


# virtual methods
.method public interpolatePathDataNode(Landroid/support/graphics/drawable/PathParser$PathDataNode;Landroid/support/graphics/drawable/PathParser$PathDataNode;F)V
    .locals 4
    .param p1, "nodeFrom"    # Landroid/support/graphics/drawable/PathParser$PathDataNode;
    .param p2, "nodeTo"    # Landroid/support/graphics/drawable/PathParser$PathDataNode;
    .param p3, "fraction"    # F

    .line 336
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_0
    iget-object v1, p1, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    array-length v1, v1

    if-ge v0, v1, :cond_0

    .line 337
    iget-object v1, p0, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    iget-object v2, p1, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    aget v2, v2, v0

    const/high16 v3, 0x3f800000    # 1.0f

    sub-float/2addr v3, p3

    mul-float v2, v2, v3

    iget-object v3, p2, Landroid/support/graphics/drawable/PathParser$PathDataNode;->params:[F

    aget v3, v3, v0

    mul-float v3, v3, p3

    add-float/2addr v2, v3

    aput v2, v1, v0

    .line 336
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 340
    .end local v0    # "i":I
    :cond_0
    return-void
.end method
