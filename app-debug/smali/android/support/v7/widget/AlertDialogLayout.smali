.class public Landroid/support/v7/widget/AlertDialogLayout;
.super Landroid/support/v7/widget/LinearLayoutCompat;
.source "AlertDialogLayout.java"


# annotations
.annotation build Landroid/support/annotation/RestrictTo;
    value = {
        .enum Landroid/support/annotation/RestrictTo$Scope;->LIBRARY_GROUP:Landroid/support/annotation/RestrictTo$Scope;
    }
.end annotation


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0
    .param p1, "context"    # Landroid/content/Context;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .line 54
    invoke-direct {p0, p1}, Landroid/support/v7/widget/LinearLayoutCompat;-><init>(Landroid/content/Context;)V

    .line 55
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0
    .param p1, "context"    # Landroid/content/Context;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "attrs"    # Landroid/util/AttributeSet;
        .annotation build Landroid/support/annotation/Nullable;
        .end annotation
    .end param

    .line 58
    invoke-direct {p0, p1, p2}, Landroid/support/v7/widget/LinearLayoutCompat;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 59
    return-void
.end method

.method private forceUniformWidth(II)V
    .locals 11
    .param p1, "count"    # I
    .param p2, "heightMeasureSpec"    # I

    .line 221
    nop

    .line 222
    invoke-virtual {p0}, Landroid/support/v7/widget/AlertDialogLayout;->getMeasuredWidth()I

    move-result v0

    .line 221
    const/high16 v1, 0x40000000    # 2.0f

    invoke-static {v0, v1}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 224
    .local v0, "uniformMeasureSpec":I
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_0
    if-ge v1, p1, :cond_1

    .line 225
    invoke-virtual {p0, v1}, Landroid/support/v7/widget/AlertDialogLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v8

    .line 226
    .local v8, "child":Landroid/view/View;
    invoke-virtual {v8}, Landroid/view/View;->getVisibility()I

    move-result v2

    const/16 v3, 0x8

    if-eq v2, v3, :cond_0

    .line 227
    invoke-virtual {v8}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    move-object v9, v2

    check-cast v9, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;

    .line 228
    .local v9, "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    iget v2, v9, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->width:I

    const/4 v3, -0x1

    if-ne v2, v3, :cond_0

    .line 231
    iget v10, v9, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->height:I

    .line 232
    .local v10, "oldHeight":I
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    iput v2, v9, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->height:I

    .line 235
    const/4 v5, 0x0

    const/4 v7, 0x0

    move-object v2, p0

    move-object v3, v8

    move v4, v0

    move v6, p2

    invoke-virtual/range {v2 .. v7}, Landroid/support/v7/widget/AlertDialogLayout;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 236
    iput v10, v9, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->height:I

    .line 224
    .end local v8    # "child":Landroid/view/View;
    .end local v9    # "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    .end local v10    # "oldHeight":I
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 240
    .end local v1    # "i":I
    :cond_1
    return-void
.end method

.method private static resolveMinimumHeight(Landroid/view/View;)I
    .locals 5
    .param p0, "v"    # Landroid/view/View;

    .line 252
    invoke-static {p0}, Landroid/support/v4/view/ViewCompat;->getMinimumHeight(Landroid/view/View;)I

    move-result v0

    .line 253
    .local v0, "minHeight":I
    if-lez v0, :cond_0

    .line 254
    return v0

    .line 257
    :cond_0
    instance-of v1, p0, Landroid/view/ViewGroup;

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    .line 258
    move-object v1, p0

    check-cast v1, Landroid/view/ViewGroup;

    .line 259
    .local v1, "vg":Landroid/view/ViewGroup;
    invoke-virtual {v1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_1

    .line 260
    invoke-virtual {v1, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    invoke-static {v2}, Landroid/support/v7/widget/AlertDialogLayout;->resolveMinimumHeight(Landroid/view/View;)I

    move-result v2

    return v2

    .line 264
    .end local v1    # "vg":Landroid/view/ViewGroup;
    :cond_1
    return v2
.end method

.method private setChildFrame(Landroid/view/View;IIII)V
    .locals 2
    .param p1, "child"    # Landroid/view/View;
    .param p2, "left"    # I
    .param p3, "top"    # I
    .param p4, "width"    # I
    .param p5, "height"    # I

    .line 352
    add-int v0, p2, p4

    add-int v1, p3, p5

    invoke-virtual {p1, p2, p3, v0, v1}, Landroid/view/View;->layout(IIII)V

    .line 353
    return-void
.end method

.method private tryOnMeasure(II)Z
    .locals 24
    .param p1, "widthMeasureSpec"    # I
    .param p2, "heightMeasureSpec"    # I

    .line 70
    move-object/from16 v0, p0

    move/from16 v1, p1

    move/from16 v2, p2

    const/4 v3, 0x0

    .line 71
    .local v3, "topPanel":Landroid/view/View;
    const/4 v4, 0x0

    .line 72
    .local v4, "buttonPanel":Landroid/view/View;
    const/4 v5, 0x0

    .line 74
    .local v5, "middlePanel":Landroid/view/View;
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getChildCount()I

    move-result v6

    .line 75
    .local v6, "count":I
    const/4 v7, 0x0

    move-object v8, v5

    move-object v5, v4

    move-object v4, v3

    const/4 v3, 0x0

    .local v3, "i":I
    .local v4, "topPanel":Landroid/view/View;
    .local v5, "buttonPanel":Landroid/view/View;
    .local v8, "middlePanel":Landroid/view/View;
    :goto_0
    const/16 v9, 0x8

    if-ge v3, v6, :cond_6

    .line 76
    invoke-virtual {v0, v3}, Landroid/support/v7/widget/AlertDialogLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v10

    .line 77
    .local v10, "child":Landroid/view/View;
    invoke-virtual {v10}, Landroid/view/View;->getVisibility()I

    move-result v11

    if-ne v11, v9, :cond_0

    .line 78
    goto :goto_2

    .line 81
    :cond_0
    invoke-virtual {v10}, Landroid/view/View;->getId()I

    move-result v9

    .line 82
    .local v9, "id":I
    sget v11, Landroid/support/v7/appcompat/R$id;->topPanel:I

    if-ne v9, v11, :cond_1

    .line 83
    move-object v4, v10

    goto :goto_2

    .line 84
    :cond_1
    sget v11, Landroid/support/v7/appcompat/R$id;->buttonPanel:I

    if-ne v9, v11, :cond_2

    .line 85
    move-object v5, v10

    goto :goto_2

    .line 86
    :cond_2
    sget v11, Landroid/support/v7/appcompat/R$id;->contentPanel:I

    if-eq v9, v11, :cond_4

    sget v11, Landroid/support/v7/appcompat/R$id;->customPanel:I

    if-ne v9, v11, :cond_3

    goto :goto_1

    .line 94
    :cond_3
    return v7

    .line 87
    :cond_4
    :goto_1
    if-eqz v8, :cond_5

    .line 89
    return v7

    .line 91
    :cond_5
    move-object v8, v10

    .line 75
    .end local v9    # "id":I
    .end local v10    # "child":Landroid/view/View;
    :goto_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 98
    .end local v3    # "i":I
    :cond_6
    invoke-static/range {p2 .. p2}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v3

    .line 99
    .local v3, "heightMode":I
    invoke-static/range {p2 .. p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v10

    .line 100
    .local v10, "heightSize":I
    invoke-static/range {p1 .. p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v11

    .line 102
    .local v11, "widthMode":I
    const/4 v12, 0x0

    .line 103
    .local v12, "childState":I
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingTop()I

    move-result v13

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingBottom()I

    move-result v14

    add-int/2addr v13, v14

    .line 105
    .local v13, "usedHeight":I
    if-eqz v4, :cond_7

    .line 106
    invoke-virtual {v4, v1, v7}, Landroid/view/View;->measure(II)V

    .line 108
    invoke-virtual {v4}, Landroid/view/View;->getMeasuredHeight()I

    move-result v14

    add-int/2addr v13, v14

    .line 109
    nop

    .line 110
    invoke-static {v4}, Landroid/support/v4/view/ViewCompat;->getMeasuredState(Landroid/view/View;)I

    move-result v14

    .line 109
    invoke-static {v12, v14}, Landroid/support/v4/view/ViewCompat;->combineMeasuredStates(II)I

    move-result v12

    .line 113
    :cond_7
    const/4 v14, 0x0

    .line 114
    .local v14, "buttonHeight":I
    const/4 v15, 0x0

    .line 115
    .local v15, "buttonWantsHeight":I
    if-eqz v5, :cond_8

    .line 116
    invoke-virtual {v5, v1, v7}, Landroid/view/View;->measure(II)V

    .line 117
    invoke-static {v5}, Landroid/support/v7/widget/AlertDialogLayout;->resolveMinimumHeight(Landroid/view/View;)I

    move-result v14

    .line 118
    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v16

    sub-int v15, v16, v14

    .line 120
    add-int/2addr v13, v14

    .line 121
    nop

    .line 122
    invoke-static {v5}, Landroid/support/v4/view/ViewCompat;->getMeasuredState(Landroid/view/View;)I

    move-result v9

    .line 121
    invoke-static {v12, v9}, Landroid/support/v4/view/ViewCompat;->combineMeasuredStates(II)I

    move-result v12

    .line 125
    :cond_8
    const/4 v9, 0x0

    .line 126
    .local v9, "middleHeight":I
    if-eqz v8, :cond_a

    .line 128
    if-nez v3, :cond_9

    .line 129
    const/16 v16, 0x0

    .line 131
    move-object/from16 v17, v4

    goto :goto_3

    :cond_9
    move-object/from16 v17, v4

    .end local v4    # "topPanel":Landroid/view/View;
    .local v17, "topPanel":Landroid/view/View;
    sub-int v4, v10, v13

    .line 132
    invoke-static {v7, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 131
    invoke-static {v4, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v16

    .local v16, "childHeightSpec":I
    :goto_3
    move/from16 v4, v16

    .line 135
    .end local v16    # "childHeightSpec":I
    .local v4, "childHeightSpec":I
    invoke-virtual {v8, v1, v4}, Landroid/view/View;->measure(II)V

    .line 136
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredHeight()I

    move-result v9

    .line 138
    add-int/2addr v13, v9

    .line 139
    nop

    .line 140
    invoke-static {v8}, Landroid/support/v4/view/ViewCompat;->getMeasuredState(Landroid/view/View;)I

    move-result v7

    .line 139
    invoke-static {v12, v7}, Landroid/support/v4/view/ViewCompat;->combineMeasuredStates(II)I

    move-result v12

    goto :goto_4

    .line 143
    .end local v17    # "topPanel":Landroid/view/View;
    .local v4, "topPanel":Landroid/view/View;
    :cond_a
    move-object/from16 v17, v4

    .end local v4    # "topPanel":Landroid/view/View;
    .restart local v17    # "topPanel":Landroid/view/View;
    :goto_4
    sub-int v4, v10, v13

    .line 148
    .local v4, "remainingHeight":I
    const/high16 v7, 0x40000000    # 2.0f

    if-eqz v5, :cond_c

    .line 149
    sub-int/2addr v13, v14

    .line 151
    invoke-static {v4, v15}, Ljava/lang/Math;->min(II)I

    move-result v16

    .line 152
    .local v16, "heightToGive":I
    if-lez v16, :cond_b

    .line 153
    sub-int v4, v4, v16

    .line 154
    add-int v14, v14, v16

    .line 157
    :cond_b
    move/from16 v18, v4

    .end local v4    # "remainingHeight":I
    .local v18, "remainingHeight":I
    invoke-static {v14, v7}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v4

    .line 159
    .local v4, "childHeightSpec":I
    invoke-virtual {v5, v1, v4}, Landroid/view/View;->measure(II)V

    .line 161
    invoke-virtual {v5}, Landroid/view/View;->getMeasuredHeight()I

    move-result v19

    add-int v13, v13, v19

    .line 162
    nop

    .line 163
    invoke-static {v5}, Landroid/support/v4/view/ViewCompat;->getMeasuredState(Landroid/view/View;)I

    move-result v7

    .line 162
    invoke-static {v12, v7}, Landroid/support/v4/view/ViewCompat;->combineMeasuredStates(II)I

    move-result v12

    .line 168
    move/from16 v4, v18

    .end local v16    # "heightToGive":I
    .end local v18    # "remainingHeight":I
    .local v4, "remainingHeight":I
    :cond_c
    if-eqz v8, :cond_d

    if-lez v4, :cond_d

    .line 169
    sub-int/2addr v13, v9

    .line 171
    move v7, v4

    .line 172
    .local v7, "heightToGive":I
    sub-int/2addr v4, v7

    .line 173
    add-int/2addr v9, v7

    .line 178
    move/from16 v20, v4

    .end local v4    # "remainingHeight":I
    .local v20, "remainingHeight":I
    invoke-static {v9, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v4

    .line 180
    .local v4, "childHeightSpec":I
    invoke-virtual {v8, v1, v4}, Landroid/view/View;->measure(II)V

    .line 182
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredHeight()I

    move-result v16

    add-int v13, v13, v16

    .line 183
    nop

    .line 184
    move/from16 v21, v3

    .end local v3    # "heightMode":I
    .local v21, "heightMode":I
    invoke-static {v8}, Landroid/support/v4/view/ViewCompat;->getMeasuredState(Landroid/view/View;)I

    move-result v3

    .line 183
    invoke-static {v12, v3}, Landroid/support/v4/view/ViewCompat;->combineMeasuredStates(II)I

    move-result v12

    .line 188
    move/from16 v4, v20

    goto :goto_5

    .end local v7    # "heightToGive":I
    .end local v20    # "remainingHeight":I
    .end local v21    # "heightMode":I
    .restart local v3    # "heightMode":I
    .local v4, "remainingHeight":I
    :cond_d
    move/from16 v21, v3

    .end local v3    # "heightMode":I
    .restart local v21    # "heightMode":I
    :goto_5
    const/4 v3, 0x0

    .line 189
    .local v3, "maxWidth":I
    move v7, v3

    const/4 v3, 0x0

    .local v3, "i":I
    .local v7, "maxWidth":I
    :goto_6
    if-ge v3, v6, :cond_f

    .line 190
    invoke-virtual {v0, v3}, Landroid/support/v7/widget/AlertDialogLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v16

    .line 191
    .local v16, "child":Landroid/view/View;
    move/from16 v22, v4

    .end local v4    # "remainingHeight":I
    .local v22, "remainingHeight":I
    invoke-virtual/range {v16 .. v16}, Landroid/view/View;->getVisibility()I

    move-result v4

    move-object/from16 v23, v5

    const/16 v5, 0x8

    .end local v5    # "buttonPanel":Landroid/view/View;
    .local v23, "buttonPanel":Landroid/view/View;
    if-eq v4, v5, :cond_e

    .line 192
    invoke-virtual/range {v16 .. v16}, Landroid/view/View;->getMeasuredWidth()I

    move-result v4

    invoke-static {v7, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 189
    move v7, v4

    .end local v16    # "child":Landroid/view/View;
    :cond_e
    add-int/lit8 v3, v3, 0x1

    move/from16 v4, v22

    move-object/from16 v5, v23

    goto :goto_6

    .line 196
    .end local v3    # "i":I
    .end local v22    # "remainingHeight":I
    .end local v23    # "buttonPanel":Landroid/view/View;
    .restart local v4    # "remainingHeight":I
    .restart local v5    # "buttonPanel":Landroid/view/View;
    :cond_f
    move/from16 v22, v4

    move-object/from16 v23, v5

    .end local v4    # "remainingHeight":I
    .end local v5    # "buttonPanel":Landroid/view/View;
    .restart local v22    # "remainingHeight":I
    .restart local v23    # "buttonPanel":Landroid/view/View;
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingLeft()I

    move-result v3

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingRight()I

    move-result v4

    add-int/2addr v3, v4

    add-int/2addr v7, v3

    .line 198
    invoke-static {v7, v1, v12}, Landroid/support/v4/view/ViewCompat;->resolveSizeAndState(III)I

    move-result v3

    .line 200
    .local v3, "widthSizeAndState":I
    const/4 v4, 0x0

    invoke-static {v13, v2, v4}, Landroid/support/v4/view/ViewCompat;->resolveSizeAndState(III)I

    move-result v4

    .line 202
    .local v4, "heightSizeAndState":I
    invoke-virtual {v0, v3, v4}, Landroid/support/v7/widget/AlertDialogLayout;->setMeasuredDimension(II)V

    .line 206
    const/high16 v5, 0x40000000    # 2.0f

    if-eq v11, v5, :cond_10

    .line 207
    invoke-direct {v0, v6, v2}, Landroid/support/v7/widget/AlertDialogLayout;->forceUniformWidth(II)V

    .line 210
    :cond_10
    const/4 v5, 0x1

    return v5
.end method


# virtual methods
.method protected onLayout(ZIIII)V
    .locals 26
    .param p1, "changed"    # Z
    .param p2, "left"    # I
    .param p3, "top"    # I
    .param p4, "right"    # I
    .param p5, "bottom"    # I

    .line 269
    move-object/from16 v6, p0

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingLeft()I

    move-result v8

    .line 272
    .local v8, "paddingLeft":I
    sub-int v9, p4, p2

    .line 273
    .local v9, "width":I
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingRight()I

    move-result v0

    sub-int v10, v9, v0

    .line 276
    .local v10, "childRight":I
    sub-int v0, v9, v8

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingRight()I

    move-result v1

    sub-int v11, v0, v1

    .line 278
    .local v11, "childSpace":I
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getMeasuredHeight()I

    move-result v12

    .line 279
    .local v12, "totalLength":I
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getChildCount()I

    move-result v5

    .line 280
    .local v5, "count":I
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getGravity()I

    move-result v13

    .line 281
    .local v13, "gravity":I
    and-int/lit8 v4, v13, 0x70

    .line 282
    .local v4, "majorGravity":I
    const v0, 0x800007

    and-int v14, v13, v0

    .line 285
    .local v14, "minorGravity":I
    const/16 v0, 0x10

    if-eq v4, v0, :cond_1

    const/16 v0, 0x50

    if-eq v4, v0, :cond_0

    .line 298
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingTop()I

    move-result v0

    goto :goto_0

    .line 288
    :cond_0
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingTop()I

    move-result v0

    add-int v0, v0, p5

    sub-int v0, v0, p3

    sub-int/2addr v0, v12

    .line 289
    .local v0, "childTop":I
    goto :goto_0

    .line 293
    .end local v0    # "childTop":I
    :cond_1
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getPaddingTop()I

    move-result v0

    sub-int v1, p5, p3

    sub-int/2addr v1, v12

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    .line 294
    .restart local v0    # "childTop":I
    nop

    .line 298
    :goto_0
    nop

    .line 302
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/widget/AlertDialogLayout;->getDividerDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v15

    .line 303
    .local v15, "dividerDrawable":Landroid/graphics/drawable/Drawable;
    const/4 v1, 0x0

    if-nez v15, :cond_2

    .line 304
    const/4 v2, 0x0

    goto :goto_1

    :cond_2
    invoke-virtual {v15}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v2

    :goto_1
    move/from16 v16, v2

    .line 306
    .local v16, "dividerHeight":I
    nop

    .local v1, "i":I
    :goto_2
    move v3, v1

    .end local v1    # "i":I
    .local v3, "i":I
    if-ge v3, v5, :cond_8

    .line 307
    invoke-virtual {v6, v3}, Landroid/support/v7/widget/AlertDialogLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v17

    .line 308
    .local v17, "child":Landroid/view/View;
    if-eqz v17, :cond_7

    invoke-virtual/range {v17 .. v17}, Landroid/view/View;->getVisibility()I

    move-result v1

    const/16 v2, 0x8

    if-eq v1, v2, :cond_7

    .line 309
    invoke-virtual/range {v17 .. v17}, Landroid/view/View;->getMeasuredWidth()I

    move-result v18

    .line 310
    .local v18, "childWidth":I
    invoke-virtual/range {v17 .. v17}, Landroid/view/View;->getMeasuredHeight()I

    move-result v19

    .line 312
    .local v19, "childHeight":I
    nop

    .line 313
    invoke-virtual/range {v17 .. v17}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;

    .line 315
    .local v1, "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    iget v2, v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->gravity:I

    .line 316
    .local v2, "layoutGravity":I
    if-gez v2, :cond_3

    .line 317
    move v2, v14

    .line 319
    :cond_3
    invoke-static/range {p0 .. p0}, Landroid/support/v4/view/ViewCompat;->getLayoutDirection(Landroid/view/View;)I

    move-result v7

    .line 320
    .local v7, "layoutDirection":I
    invoke-static {v2, v7}, Landroid/support/v4/view/GravityCompat;->getAbsoluteGravity(II)I

    move-result v20

    .line 324
    .local v20, "absoluteGravity":I
    move/from16 v21, v2

    .end local v2    # "layoutGravity":I
    .local v21, "layoutGravity":I
    and-int/lit8 v2, v20, 0x7

    move/from16 v22, v4

    .end local v4    # "majorGravity":I
    .local v22, "majorGravity":I
    const/4 v4, 0x1

    if-eq v2, v4, :cond_5

    const/4 v4, 0x5

    if-eq v2, v4, :cond_4

    .line 336
    iget v2, v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->leftMargin:I

    add-int/2addr v2, v8

    goto :goto_3

    .line 331
    :cond_4
    sub-int v2, v10, v18

    iget v4, v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->rightMargin:I

    sub-int/2addr v2, v4

    .line 332
    .local v2, "childLeft":I
    goto :goto_3

    .line 326
    .end local v2    # "childLeft":I
    :cond_5
    sub-int v2, v11, v18

    div-int/lit8 v2, v2, 0x2

    add-int/2addr v2, v8

    iget v4, v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->leftMargin:I

    add-int/2addr v2, v4

    iget v4, v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->rightMargin:I

    sub-int/2addr v2, v4

    .line 328
    .restart local v2    # "childLeft":I
    nop

    .line 336
    :goto_3
    nop

    .line 340
    invoke-virtual {v6, v3}, Landroid/support/v7/widget/AlertDialogLayout;->hasDividerBeforeChildAt(I)Z

    move-result v4

    if-eqz v4, :cond_6

    .line 341
    add-int v0, v0, v16

    .line 344
    :cond_6
    iget v4, v1, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->topMargin:I

    add-int v23, v0, v4

    .line 345
    .end local v0    # "childTop":I
    .local v23, "childTop":I
    move-object/from16 v0, p0

    move-object v4, v1

    .end local v1    # "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    .local v4, "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    move-object/from16 v1, v17

    move/from16 v24, v3

    .end local v3    # "i":I
    .local v24, "i":I
    move/from16 v3, v23

    move-object v6, v4

    .end local v4    # "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    .local v6, "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    move/from16 v4, v18

    move/from16 v25, v5

    .end local v5    # "count":I
    .local v25, "count":I
    move/from16 v5, v19

    invoke-direct/range {v0 .. v5}, Landroid/support/v7/widget/AlertDialogLayout;->setChildFrame(Landroid/view/View;IIII)V

    .line 346
    iget v0, v6, Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;->bottomMargin:I

    add-int v0, v19, v0

    add-int v23, v23, v0

    .line 306
    move/from16 v0, v23

    goto :goto_4

    .end local v2    # "childLeft":I
    .end local v6    # "lp":Landroid/support/v7/widget/LinearLayoutCompat$LayoutParams;
    .end local v7    # "layoutDirection":I
    .end local v17    # "child":Landroid/view/View;
    .end local v18    # "childWidth":I
    .end local v19    # "childHeight":I
    .end local v20    # "absoluteGravity":I
    .end local v21    # "layoutGravity":I
    .end local v22    # "majorGravity":I
    .end local v23    # "childTop":I
    .end local v24    # "i":I
    .end local v25    # "count":I
    .restart local v0    # "childTop":I
    .restart local v3    # "i":I
    .local v4, "majorGravity":I
    .restart local v5    # "count":I
    :cond_7
    move/from16 v24, v3

    move/from16 v22, v4

    move/from16 v25, v5

    .end local v3    # "i":I
    .end local v4    # "majorGravity":I
    .end local v5    # "count":I
    .restart local v22    # "majorGravity":I
    .restart local v24    # "i":I
    .restart local v25    # "count":I
    :goto_4
    add-int/lit8 v1, v24, 0x1

    move/from16 v4, v22

    move/from16 v5, v25

    move-object/from16 v6, p0

    goto/16 :goto_2

    .line 349
    .end local v22    # "majorGravity":I
    .end local v24    # "i":I
    .end local v25    # "count":I
    .restart local v4    # "majorGravity":I
    .restart local v5    # "count":I
    :cond_8
    move/from16 v22, v4

    move/from16 v25, v5

    .end local v4    # "majorGravity":I
    .end local v5    # "count":I
    .restart local v22    # "majorGravity":I
    .restart local v25    # "count":I
    return-void
.end method

.method protected onMeasure(II)V
    .locals 1
    .param p1, "widthMeasureSpec"    # I
    .param p2, "heightMeasureSpec"    # I

    .line 63
    invoke-direct {p0, p1, p2}, Landroid/support/v7/widget/AlertDialogLayout;->tryOnMeasure(II)Z

    move-result v0

    if-nez v0, :cond_0

    .line 65
    invoke-super {p0, p1, p2}, Landroid/support/v7/widget/LinearLayoutCompat;->onMeasure(II)V

    .line 67
    :cond_0
    return-void
.end method
