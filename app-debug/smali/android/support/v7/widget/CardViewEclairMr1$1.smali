.class Landroid/support/v7/widget/CardViewEclairMr1$1;
.super Ljava/lang/Object;
.source "CardViewEclairMr1.java"

# interfaces
.implements Landroid/support/v7/widget/RoundRectDrawableWithShadow$RoundRectHelper;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroid/support/v7/widget/CardViewEclairMr1;->initStatic()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroid/support/v7/widget/CardViewEclairMr1;


# direct methods
.method constructor <init>(Landroid/support/v7/widget/CardViewEclairMr1;)V
    .locals 0

    .line 34
    iput-object p1, p0, Landroid/support/v7/widget/CardViewEclairMr1$1;->this$0:Landroid/support/v7/widget/CardViewEclairMr1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public drawRoundRect(Landroid/graphics/Canvas;Landroid/graphics/RectF;FLandroid/graphics/Paint;)V
    .locals 15
    .param p1, "canvas"    # Landroid/graphics/Canvas;
    .param p2, "bounds"    # Landroid/graphics/RectF;
    .param p3, "cornerRadius"    # F
    .param p4, "paint"    # Landroid/graphics/Paint;

    .line 38
    move-object v0, p0

    move-object/from16 v7, p1

    move-object/from16 v8, p2

    const/high16 v1, 0x40000000    # 2.0f

    mul-float v9, p3, v1

    .line 39
    .local v9, "twoRadius":F
    invoke-virtual/range {p2 .. p2}, Landroid/graphics/RectF;->width()F

    move-result v1

    sub-float/2addr v1, v9

    const/high16 v10, 0x3f800000    # 1.0f

    sub-float v11, v1, v10

    .line 40
    .local v11, "innerWidth":F
    invoke-virtual/range {p2 .. p2}, Landroid/graphics/RectF;->height()F

    move-result v1

    sub-float/2addr v1, v9

    sub-float v12, v1, v10

    .line 41
    .local v12, "innerHeight":F
    const/4 v13, 0x0

    cmpl-float v1, p3, v10

    if-ltz v1, :cond_0

    .line 43
    const/high16 v1, 0x3f000000    # 0.5f

    add-float v14, p3, v1

    .line 44
    .local v14, "roundedCornerRadius":F
    iget-object v1, v0, Landroid/support/v7/widget/CardViewEclairMr1$1;->this$0:Landroid/support/v7/widget/CardViewEclairMr1;

    iget-object v1, v1, Landroid/support/v7/widget/CardViewEclairMr1;->sCornerRect:Landroid/graphics/RectF;

    neg-float v2, v14

    neg-float v3, v14

    invoke-virtual {v1, v2, v3, v14, v14}, Landroid/graphics/RectF;->set(FFFF)V

    .line 46
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Canvas;->save()I

    move-result v6

    .line 47
    .local v6, "saved":I
    iget v1, v8, Landroid/graphics/RectF;->left:F

    add-float/2addr v1, v14

    iget v2, v8, Landroid/graphics/RectF;->top:F

    add-float/2addr v2, v14

    invoke-virtual {v7, v1, v2}, Landroid/graphics/Canvas;->translate(FF)V

    .line 49
    iget-object v1, v0, Landroid/support/v7/widget/CardViewEclairMr1$1;->this$0:Landroid/support/v7/widget/CardViewEclairMr1;

    iget-object v2, v1, Landroid/support/v7/widget/CardViewEclairMr1;->sCornerRect:Landroid/graphics/RectF;

    const/high16 v3, 0x43340000    # 180.0f

    const/high16 v4, 0x42b40000    # 90.0f

    const/4 v5, 0x1

    move-object/from16 v1, p1

    move v10, v6

    .end local v6    # "saved":I
    .local v10, "saved":I
    move-object/from16 v6, p4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 50
    invoke-virtual {v7, v11, v13}, Landroid/graphics/Canvas;->translate(FF)V

    .line 51
    const/high16 v6, 0x42b40000    # 90.0f

    invoke-virtual {v7, v6}, Landroid/graphics/Canvas;->rotate(F)V

    .line 52
    iget-object v1, v0, Landroid/support/v7/widget/CardViewEclairMr1$1;->this$0:Landroid/support/v7/widget/CardViewEclairMr1;

    iget-object v2, v1, Landroid/support/v7/widget/CardViewEclairMr1;->sCornerRect:Landroid/graphics/RectF;

    move-object/from16 v1, p1

    move-object/from16 v6, p4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 53
    invoke-virtual {v7, v12, v13}, Landroid/graphics/Canvas;->translate(FF)V

    .line 54
    const/high16 v6, 0x42b40000    # 90.0f

    invoke-virtual {v7, v6}, Landroid/graphics/Canvas;->rotate(F)V

    .line 55
    iget-object v1, v0, Landroid/support/v7/widget/CardViewEclairMr1$1;->this$0:Landroid/support/v7/widget/CardViewEclairMr1;

    iget-object v2, v1, Landroid/support/v7/widget/CardViewEclairMr1;->sCornerRect:Landroid/graphics/RectF;

    move-object/from16 v1, p1

    move-object/from16 v6, p4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 56
    invoke-virtual {v7, v11, v13}, Landroid/graphics/Canvas;->translate(FF)V

    .line 57
    const/high16 v1, 0x42b40000    # 90.0f

    invoke-virtual {v7, v1}, Landroid/graphics/Canvas;->rotate(F)V

    .line 58
    iget-object v1, v0, Landroid/support/v7/widget/CardViewEclairMr1$1;->this$0:Landroid/support/v7/widget/CardViewEclairMr1;

    iget-object v2, v1, Landroid/support/v7/widget/CardViewEclairMr1;->sCornerRect:Landroid/graphics/RectF;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 59
    invoke-virtual {v7, v10}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 61
    iget v1, v8, Landroid/graphics/RectF;->left:F

    add-float/2addr v1, v14

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float v3, v1, v2

    iget v4, v8, Landroid/graphics/RectF;->top:F

    iget v1, v8, Landroid/graphics/RectF;->right:F

    sub-float/2addr v1, v14

    add-float v5, v1, v2

    iget v1, v8, Landroid/graphics/RectF;->top:F

    add-float v6, v1, v14

    move-object/from16 v1, p1

    move v2, v3

    move v3, v4

    move v4, v5

    move v5, v6

    move-object/from16 v6, p4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 64
    iget v1, v8, Landroid/graphics/RectF;->left:F

    add-float/2addr v1, v14

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float v3, v1, v2

    iget v1, v8, Landroid/graphics/RectF;->bottom:F

    sub-float/2addr v1, v14

    add-float v4, v1, v2

    iget v1, v8, Landroid/graphics/RectF;->right:F

    sub-float/2addr v1, v14

    add-float v5, v1, v2

    iget v6, v8, Landroid/graphics/RectF;->bottom:F

    move-object/from16 v1, p1

    move v2, v3

    move v3, v4

    move v4, v5

    move v5, v6

    move-object/from16 v6, p4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 69
    .end local v10    # "saved":I
    .end local v14    # "roundedCornerRadius":F
    :cond_0
    iget v2, v8, Landroid/graphics/RectF;->left:F

    iget v1, v8, Landroid/graphics/RectF;->top:F

    const/high16 v3, 0x3f800000    # 1.0f

    sub-float v4, p3, v3

    invoke-static {v13, v4}, Ljava/lang/Math;->max(FF)F

    move-result v4

    add-float/2addr v4, v1

    iget v5, v8, Landroid/graphics/RectF;->right:F

    iget v1, v8, Landroid/graphics/RectF;->bottom:F

    sub-float v1, v1, p3

    add-float v6, v1, v3

    move-object/from16 v1, p1

    move v3, v4

    move v4, v5

    move v5, v6

    move-object/from16 v6, p4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 71
    return-void
.end method
