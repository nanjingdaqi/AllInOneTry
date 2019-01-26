.class Lme/zhanghai/android/materialprogressbar/internal/ObjectAnimatorCompatBase;
.super Ljava/lang/Object;
.source "ObjectAnimatorCompatBase.java"


# static fields
.field private static final NUM_POINTS:I = 0xc9


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 21
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static calculateXYValues(Landroid/graphics/Path;[F[F)V
    .locals 7
    .param p0, "path"    # Landroid/graphics/Path;
    .param p1, "xValues"    # [F
        .annotation build Landroid/support/annotation/Size;
            value = 0xc9L
        .end annotation
    .end param
    .param p2, "yValues"    # [F
        .annotation build Landroid/support/annotation/Size;
            value = 0xc9L
        .end annotation
    .end param

    .line 91
    new-instance v0, Landroid/graphics/PathMeasure;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Landroid/graphics/PathMeasure;-><init>(Landroid/graphics/Path;Z)V

    .line 92
    .local v0, "pathMeasure":Landroid/graphics/PathMeasure;
    invoke-virtual {v0}, Landroid/graphics/PathMeasure;->getLength()F

    move-result v2

    .line 94
    .local v2, "pathLength":F
    const/4 v3, 0x2

    new-array v3, v3, [F

    .line 95
    .local v3, "position":[F
    const/4 v4, 0x0

    .local v4, "i":I
    :goto_0
    const/16 v5, 0xc9

    if-ge v4, v5, :cond_0

    .line 96
    int-to-float v5, v4

    mul-float v5, v5, v2

    const/high16 v6, 0x43480000    # 200.0f

    div-float/2addr v5, v6

    .line 97
    .local v5, "distance":F
    const/4 v6, 0x0

    invoke-virtual {v0, v5, v3, v6}, Landroid/graphics/PathMeasure;->getPosTan(F[F[F)Z

    .line 98
    aget v6, v3, v1

    aput v6, p1, v4

    .line 99
    const/4 v6, 0x1

    aget v6, v3, v6

    aput v6, p2, v4

    .line 95
    .end local v5    # "distance":F
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 101
    .end local v4    # "i":I
    :cond_0
    return-void
.end method

.method private static calculateXYValues(Landroid/graphics/Path;[I[I)V
    .locals 7
    .param p0, "path"    # Landroid/graphics/Path;
    .param p1, "xValues"    # [I
        .annotation build Landroid/support/annotation/Size;
            value = 0xc9L
        .end annotation
    .end param
    .param p2, "yValues"    # [I
        .annotation build Landroid/support/annotation/Size;
            value = 0xc9L
        .end annotation
    .end param

    .line 106
    new-instance v0, Landroid/graphics/PathMeasure;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Landroid/graphics/PathMeasure;-><init>(Landroid/graphics/Path;Z)V

    .line 107
    .local v0, "pathMeasure":Landroid/graphics/PathMeasure;
    invoke-virtual {v0}, Landroid/graphics/PathMeasure;->getLength()F

    move-result v2

    .line 109
    .local v2, "pathLength":F
    const/4 v3, 0x2

    new-array v3, v3, [F

    .line 110
    .local v3, "position":[F
    const/4 v4, 0x0

    .local v4, "i":I
    :goto_0
    const/16 v5, 0xc9

    if-ge v4, v5, :cond_0

    .line 111
    int-to-float v5, v4

    mul-float v5, v5, v2

    const/high16 v6, 0x43480000    # 200.0f

    div-float/2addr v5, v6

    .line 112
    .local v5, "distance":F
    const/4 v6, 0x0

    invoke-virtual {v0, v5, v3, v6}, Landroid/graphics/PathMeasure;->getPosTan(F[F[F)Z

    .line 113
    aget v6, v3, v1

    invoke-static {v6}, Ljava/lang/Math;->round(F)I

    move-result v6

    aput v6, p1, v4

    .line 114
    const/4 v6, 0x1

    aget v6, v3, v6

    invoke-static {v6}, Ljava/lang/Math;->round(F)I

    move-result v6

    aput v6, p2, v4

    .line 110
    .end local v5    # "distance":F
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 116
    .end local v4    # "i":I
    :cond_0
    return-void
.end method

.method public static varargs ofArgb(Ljava/lang/Object;Landroid/util/Property;[I)Landroid/animation/ObjectAnimator;
    .locals 2
    .param p2, "values"    # [I
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;",
            "Landroid/util/Property<",
            "TT;",
            "Ljava/lang/Integer;",
            ">;[I)",
            "Landroid/animation/ObjectAnimator;"
        }
    .end annotation

    .line 31
    .local p0, "target":Ljava/lang/Object;, "TT;"
    .local p1, "property":Landroid/util/Property;, "Landroid/util/Property<TT;Ljava/lang/Integer;>;"
    invoke-static {p0, p1, p2}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Landroid/util/Property;[I)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 32
    .local v0, "animator":Landroid/animation/ObjectAnimator;
    new-instance v1, Landroid/animation/ArgbEvaluator;

    invoke-direct {v1}, Landroid/animation/ArgbEvaluator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->setEvaluator(Landroid/animation/TypeEvaluator;)V

    .line 33
    return-object v0
.end method

.method public static varargs ofArgb(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;
    .locals 2
    .param p0, "target"    # Ljava/lang/Object;
    .param p1, "propertyName"    # Ljava/lang/String;
    .param p2, "values"    # [I

    .line 24
    invoke-static {p0, p1, p2}, Landroid/animation/ObjectAnimator;->ofInt(Ljava/lang/Object;Ljava/lang/String;[I)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 25
    .local v0, "animator":Landroid/animation/ObjectAnimator;
    new-instance v1, Landroid/animation/ArgbEvaluator;

    invoke-direct {v1}, Landroid/animation/ArgbEvaluator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->setEvaluator(Landroid/animation/TypeEvaluator;)V

    .line 26
    return-object v0
.end method

.method public static ofFloat(Ljava/lang/Object;Landroid/util/Property;Landroid/util/Property;Landroid/graphics/Path;)Landroid/animation/ObjectAnimator;
    .locals 6
    .param p3, "path"    # Landroid/graphics/Path;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;",
            "Landroid/util/Property<",
            "TT;",
            "Ljava/lang/Float;",
            ">;",
            "Landroid/util/Property<",
            "TT;",
            "Ljava/lang/Float;",
            ">;",
            "Landroid/graphics/Path;",
            ")",
            "Landroid/animation/ObjectAnimator;"
        }
    .end annotation

    .line 52
    .local p0, "target":Ljava/lang/Object;, "TT;"
    .local p1, "xProperty":Landroid/util/Property;, "Landroid/util/Property<TT;Ljava/lang/Float;>;"
    .local p2, "yProperty":Landroid/util/Property;, "Landroid/util/Property<TT;Ljava/lang/Float;>;"
    const/16 v0, 0xc9

    new-array v1, v0, [F

    .line 53
    .local v1, "xValues":[F
    new-array v0, v0, [F

    .line 54
    .local v0, "yValues":[F
    invoke-static {p3, v1, v0}, Lme/zhanghai/android/materialprogressbar/internal/ObjectAnimatorCompatBase;->calculateXYValues(Landroid/graphics/Path;[F[F)V

    .line 56
    invoke-static {p1, v1}, Landroid/animation/PropertyValuesHolder;->ofFloat(Landroid/util/Property;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v2

    .line 57
    .local v2, "xPvh":Landroid/animation/PropertyValuesHolder;
    invoke-static {p2, v0}, Landroid/animation/PropertyValuesHolder;->ofFloat(Landroid/util/Property;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v3

    .line 59
    .local v3, "yPvh":Landroid/animation/PropertyValuesHolder;
    const/4 v4, 0x2

    new-array v4, v4, [Landroid/animation/PropertyValuesHolder;

    const/4 v5, 0x0

    aput-object v2, v4, v5

    const/4 v5, 0x1

    aput-object v3, v4, v5

    invoke-static {p0, v4}, Landroid/animation/ObjectAnimator;->ofPropertyValuesHolder(Ljava/lang/Object;[Landroid/animation/PropertyValuesHolder;)Landroid/animation/ObjectAnimator;

    move-result-object v4

    return-object v4
.end method

.method public static ofFloat(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Path;)Landroid/animation/ObjectAnimator;
    .locals 6
    .param p0, "target"    # Ljava/lang/Object;
    .param p1, "xPropertyName"    # Ljava/lang/String;
    .param p2, "yPropertyName"    # Ljava/lang/String;
    .param p3, "path"    # Landroid/graphics/Path;

    .line 39
    const/16 v0, 0xc9

    new-array v1, v0, [F

    .line 40
    .local v1, "xValues":[F
    new-array v0, v0, [F

    .line 41
    .local v0, "yValues":[F
    invoke-static {p3, v1, v0}, Lme/zhanghai/android/materialprogressbar/internal/ObjectAnimatorCompatBase;->calculateXYValues(Landroid/graphics/Path;[F[F)V

    .line 43
    invoke-static {p1, v1}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v2

    .line 44
    .local v2, "xPvh":Landroid/animation/PropertyValuesHolder;
    invoke-static {p2, v0}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v3

    .line 46
    .local v3, "yPvh":Landroid/animation/PropertyValuesHolder;
    const/4 v4, 0x2

    new-array v4, v4, [Landroid/animation/PropertyValuesHolder;

    const/4 v5, 0x0

    aput-object v2, v4, v5

    const/4 v5, 0x1

    aput-object v3, v4, v5

    invoke-static {p0, v4}, Landroid/animation/ObjectAnimator;->ofPropertyValuesHolder(Ljava/lang/Object;[Landroid/animation/PropertyValuesHolder;)Landroid/animation/ObjectAnimator;

    move-result-object v4

    return-object v4
.end method

.method public static ofInt(Ljava/lang/Object;Landroid/util/Property;Landroid/util/Property;Landroid/graphics/Path;)Landroid/animation/ObjectAnimator;
    .locals 6
    .param p3, "path"    # Landroid/graphics/Path;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;",
            "Landroid/util/Property<",
            "TT;",
            "Ljava/lang/Integer;",
            ">;",
            "Landroid/util/Property<",
            "TT;",
            "Ljava/lang/Integer;",
            ">;",
            "Landroid/graphics/Path;",
            ")",
            "Landroid/animation/ObjectAnimator;"
        }
    .end annotation

    .line 78
    .local p0, "target":Ljava/lang/Object;, "TT;"
    .local p1, "xProperty":Landroid/util/Property;, "Landroid/util/Property<TT;Ljava/lang/Integer;>;"
    .local p2, "yProperty":Landroid/util/Property;, "Landroid/util/Property<TT;Ljava/lang/Integer;>;"
    const/16 v0, 0xc9

    new-array v1, v0, [I

    .line 79
    .local v1, "xValues":[I
    new-array v0, v0, [I

    .line 80
    .local v0, "yValues":[I
    invoke-static {p3, v1, v0}, Lme/zhanghai/android/materialprogressbar/internal/ObjectAnimatorCompatBase;->calculateXYValues(Landroid/graphics/Path;[I[I)V

    .line 82
    invoke-static {p1, v1}, Landroid/animation/PropertyValuesHolder;->ofInt(Landroid/util/Property;[I)Landroid/animation/PropertyValuesHolder;

    move-result-object v2

    .line 83
    .local v2, "xPvh":Landroid/animation/PropertyValuesHolder;
    invoke-static {p2, v0}, Landroid/animation/PropertyValuesHolder;->ofInt(Landroid/util/Property;[I)Landroid/animation/PropertyValuesHolder;

    move-result-object v3

    .line 85
    .local v3, "yPvh":Landroid/animation/PropertyValuesHolder;
    const/4 v4, 0x2

    new-array v4, v4, [Landroid/animation/PropertyValuesHolder;

    const/4 v5, 0x0

    aput-object v2, v4, v5

    const/4 v5, 0x1

    aput-object v3, v4, v5

    invoke-static {p0, v4}, Landroid/animation/ObjectAnimator;->ofPropertyValuesHolder(Ljava/lang/Object;[Landroid/animation/PropertyValuesHolder;)Landroid/animation/ObjectAnimator;

    move-result-object v4

    return-object v4
.end method

.method public static ofInt(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Path;)Landroid/animation/ObjectAnimator;
    .locals 6
    .param p0, "target"    # Ljava/lang/Object;
    .param p1, "xPropertyName"    # Ljava/lang/String;
    .param p2, "yPropertyName"    # Ljava/lang/String;
    .param p3, "path"    # Landroid/graphics/Path;

    .line 65
    const/16 v0, 0xc9

    new-array v1, v0, [I

    .line 66
    .local v1, "xValues":[I
    new-array v0, v0, [I

    .line 67
    .local v0, "yValues":[I
    invoke-static {p3, v1, v0}, Lme/zhanghai/android/materialprogressbar/internal/ObjectAnimatorCompatBase;->calculateXYValues(Landroid/graphics/Path;[I[I)V

    .line 69
    invoke-static {p1, v1}, Landroid/animation/PropertyValuesHolder;->ofInt(Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;

    move-result-object v2

    .line 70
    .local v2, "xPvh":Landroid/animation/PropertyValuesHolder;
    invoke-static {p2, v0}, Landroid/animation/PropertyValuesHolder;->ofInt(Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;

    move-result-object v3

    .line 72
    .local v3, "yPvh":Landroid/animation/PropertyValuesHolder;
    const/4 v4, 0x2

    new-array v4, v4, [Landroid/animation/PropertyValuesHolder;

    const/4 v5, 0x0

    aput-object v2, v4, v5

    const/4 v5, 0x1

    aput-object v3, v4, v5

    invoke-static {p0, v4}, Landroid/animation/ObjectAnimator;->ofPropertyValuesHolder(Ljava/lang/Object;[Landroid/animation/PropertyValuesHolder;)Landroid/animation/ObjectAnimator;

    move-result-object v4

    return-object v4
.end method
