.class Landroid/support/v4/widget/SwipeRefreshLayout$6;
.super Landroid/view/animation/Animation;
.source "SwipeRefreshLayout.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/widget/SwipeRefreshLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroid/support/v4/widget/SwipeRefreshLayout;


# direct methods
.method constructor <init>(Landroid/support/v4/widget/SwipeRefreshLayout;)V
    .locals 0
    .param p1, "this$0"    # Landroid/support/v4/widget/SwipeRefreshLayout;

    .line 1135
    iput-object p1, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    invoke-direct {p0}, Landroid/view/animation/Animation;-><init>()V

    return-void
.end method


# virtual methods
.method public applyTransformation(FLandroid/view/animation/Transformation;)V
    .locals 5
    .param p1, "interpolatedTime"    # F
    .param p2, "t"    # Landroid/view/animation/Transformation;

    .line 1138
    const/4 v0, 0x0

    .line 1139
    .local v0, "targetTop":I
    const/4 v1, 0x0

    .line 1140
    .local v1, "endTarget":I
    iget-object v2, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget-boolean v2, v2, Landroid/support/v4/widget/SwipeRefreshLayout;->mUsingCustomStart:Z

    if-nez v2, :cond_0

    .line 1141
    iget-object v2, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget v2, v2, Landroid/support/v4/widget/SwipeRefreshLayout;->mSpinnerOffsetEnd:I

    iget-object v3, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget v3, v3, Landroid/support/v4/widget/SwipeRefreshLayout;->mOriginalOffsetTop:I

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v3

    sub-int/2addr v2, v3

    goto :goto_0

    .line 1143
    :cond_0
    iget-object v2, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget v2, v2, Landroid/support/v4/widget/SwipeRefreshLayout;->mSpinnerOffsetEnd:I

    .line 1145
    .end local v1    # "endTarget":I
    .local v2, "endTarget":I
    :goto_0
    iget-object v1, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget v1, v1, Landroid/support/v4/widget/SwipeRefreshLayout;->mFrom:I

    iget-object v3, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget v3, v3, Landroid/support/v4/widget/SwipeRefreshLayout;->mFrom:I

    sub-int v3, v2, v3

    int-to-float v3, v3

    mul-float v3, v3, p1

    float-to-int v3, v3

    add-int/2addr v1, v3

    .line 1146
    .end local v0    # "targetTop":I
    .local v1, "targetTop":I
    iget-object v0, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget-object v0, v0, Landroid/support/v4/widget/SwipeRefreshLayout;->mCircleView:Landroid/support/v4/widget/CircleImageView;

    invoke-virtual {v0}, Landroid/support/v4/widget/CircleImageView;->getTop()I

    move-result v0

    sub-int v0, v1, v0

    .line 1147
    .local v0, "offset":I
    iget-object v3, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    const/4 v4, 0x0

    invoke-virtual {v3, v0, v4}, Landroid/support/v4/widget/SwipeRefreshLayout;->setTargetOffsetTopAndBottom(IZ)V

    .line 1148
    iget-object v3, p0, Landroid/support/v4/widget/SwipeRefreshLayout$6;->this$0:Landroid/support/v4/widget/SwipeRefreshLayout;

    iget-object v3, v3, Landroid/support/v4/widget/SwipeRefreshLayout;->mProgress:Landroid/support/v4/widget/MaterialProgressDrawable;

    const/high16 v4, 0x3f800000    # 1.0f

    sub-float/2addr v4, p1

    invoke-virtual {v3, v4}, Landroid/support/v4/widget/MaterialProgressDrawable;->setArrowScale(F)V

    .line 1149
    return-void
.end method
