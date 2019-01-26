.class Lcom/afollestad/materialdialogs/color/ColorPalette;
.super Ljava/lang/Object;
.source "ColorPalette.java"


# static fields
.field static final ACCENT_COLORS:[I

.field static final ACCENT_COLORS_SUB:[[I

.field static final PRIMARY_COLORS:[I

.field static final PRIMARY_COLORS_SUB:[[I


# direct methods
.method static constructor <clinit>()V
    .locals 18

    .line 10
    const/16 v0, 0x13

    new-array v0, v0, [I

    const-string v1, "#F44336"

    .line 11
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v2, 0x0

    aput v1, v0, v2

    const-string v1, "#E91E63"

    .line 12
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v3, 0x1

    aput v1, v0, v3

    const-string v1, "#9C27B0"

    .line 13
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v4, 0x2

    aput v1, v0, v4

    const-string v1, "#673AB7"

    .line 14
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v5, 0x3

    aput v1, v0, v5

    const-string v1, "#3F51B5"

    .line 15
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v6, 0x4

    aput v1, v0, v6

    const-string v1, "#2196F3"

    .line 16
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v7, 0x5

    aput v1, v0, v7

    const-string v1, "#03A9F4"

    .line 17
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v8, 0x6

    aput v1, v0, v8

    const-string v1, "#00BCD4"

    .line 18
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/4 v9, 0x7

    aput v1, v0, v9

    const-string v1, "#009688"

    .line 19
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v10, 0x8

    aput v1, v0, v10

    const-string v1, "#4CAF50"

    .line 20
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v11, 0x9

    aput v1, v0, v11

    const-string v1, "#8BC34A"

    .line 21
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v12, 0xa

    aput v1, v0, v12

    const-string v1, "#CDDC39"

    .line 22
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v13, 0xb

    aput v1, v0, v13

    const-string v1, "#FFEB3B"

    .line 23
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v14, 0xc

    aput v1, v0, v14

    const-string v1, "#FFC107"

    .line 24
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v15, 0xd

    aput v1, v0, v15

    const-string v1, "#FF9800"

    .line 25
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v16, 0xe

    aput v1, v0, v16

    const-string v1, "#FF5722"

    .line 26
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v17, 0xf

    aput v1, v0, v17

    const-string v1, "#795548"

    .line 27
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v17, 0x10

    aput v1, v0, v17

    const-string v1, "#9E9E9E"

    .line 28
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v17, 0x11

    aput v1, v0, v17

    const-string v1, "#607D8B"

    .line 29
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v17, 0x12

    aput v1, v0, v17

    sput-object v0, Lcom/afollestad/materialdialogs/color/ColorPalette;->PRIMARY_COLORS:[I

    .line 32
    const/16 v0, 0x13

    new-array v0, v0, [[I

    new-array v1, v12, [I

    const-string v17, "#FFEBEE"

    .line 34
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FFCDD2"

    .line 35
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#EF9A9A"

    .line 36
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#E57373"

    .line 37
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#EF5350"

    .line 38
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#F44336"

    .line 39
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#E53935"

    .line 40
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#D32F2F"

    .line 41
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#C62828"

    .line 42
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#B71C1C"

    .line 43
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v2

    new-array v1, v12, [I

    const-string v17, "#FCE4EC"

    .line 46
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#F8BBD0"

    .line 47
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#F48FB1"

    .line 48
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#F06292"

    .line 49
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#EC407A"

    .line 50
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#E91E63"

    .line 51
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#D81B60"

    .line 52
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#C2185B"

    .line 53
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#AD1457"

    .line 54
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#880E4F"

    .line 55
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v3

    new-array v1, v12, [I

    const-string v17, "#F3E5F5"

    .line 58
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#E1BEE7"

    .line 59
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#CE93D8"

    .line 60
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#BA68C8"

    .line 61
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#AB47BC"

    .line 62
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#9C27B0"

    .line 63
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#8E24AA"

    .line 64
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#7B1FA2"

    .line 65
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#6A1B9A"

    .line 66
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#4A148C"

    .line 67
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v4

    new-array v1, v12, [I

    const-string v17, "#EDE7F6"

    .line 70
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#D1C4E9"

    .line 71
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#B39DDB"

    .line 72
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#9575CD"

    .line 73
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#7E57C2"

    .line 74
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#673AB7"

    .line 75
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#5E35B1"

    .line 76
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#512DA8"

    .line 77
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#4527A0"

    .line 78
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#311B92"

    .line 79
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v5

    new-array v1, v12, [I

    const-string v17, "#E8EAF6"

    .line 82
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#C5CAE9"

    .line 83
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#9FA8DA"

    .line 84
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#7986CB"

    .line 85
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#5C6BC0"

    .line 86
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#3F51B5"

    .line 87
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#3949AB"

    .line 88
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#303F9F"

    .line 89
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#283593"

    .line 90
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#1A237E"

    .line 91
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v6

    new-array v1, v12, [I

    const-string v17, "#E3F2FD"

    .line 94
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#BBDEFB"

    .line 95
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#90CAF9"

    .line 96
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#64B5F6"

    .line 97
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#42A5F5"

    .line 98
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#2196F3"

    .line 99
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#1E88E5"

    .line 100
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#1976D2"

    .line 101
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#1565C0"

    .line 102
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#0D47A1"

    .line 103
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v7

    new-array v1, v12, [I

    const-string v17, "#E1F5FE"

    .line 106
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#B3E5FC"

    .line 107
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#81D4FA"

    .line 108
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#4FC3F7"

    .line 109
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#29B6F6"

    .line 110
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#03A9F4"

    .line 111
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#039BE5"

    .line 112
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#0288D1"

    .line 113
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#0277BD"

    .line 114
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#01579B"

    .line 115
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v8

    new-array v1, v12, [I

    const-string v17, "#E0F7FA"

    .line 118
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#B2EBF2"

    .line 119
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#80DEEA"

    .line 120
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#4DD0E1"

    .line 121
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#26C6DA"

    .line 122
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#00BCD4"

    .line 123
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#00ACC1"

    .line 124
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#0097A7"

    .line 125
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#00838F"

    .line 126
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#006064"

    .line 127
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v9

    new-array v1, v12, [I

    const-string v17, "#E0F2F1"

    .line 130
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#B2DFDB"

    .line 131
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#80CBC4"

    .line 132
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#4DB6AC"

    .line 133
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#26A69A"

    .line 134
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#009688"

    .line 135
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#00897B"

    .line 136
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#00796B"

    .line 137
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#00695C"

    .line 138
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#004D40"

    .line 139
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v10

    new-array v1, v12, [I

    const-string v17, "#E8F5E9"

    .line 142
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#C8E6C9"

    .line 143
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#A5D6A7"

    .line 144
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#81C784"

    .line 145
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#66BB6A"

    .line 146
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#4CAF50"

    .line 147
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#43A047"

    .line 148
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#388E3C"

    .line 149
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#2E7D32"

    .line 150
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#1B5E20"

    .line 151
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v11

    new-array v1, v12, [I

    const-string v17, "#F1F8E9"

    .line 154
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#DCEDC8"

    .line 155
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#C5E1A5"

    .line 156
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#AED581"

    .line 157
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#9CCC65"

    .line 158
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#8BC34A"

    .line 159
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#7CB342"

    .line 160
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#689F38"

    .line 161
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#558B2F"

    .line 162
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#33691E"

    .line 163
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v12

    new-array v1, v12, [I

    const-string v17, "#F9FBE7"

    .line 166
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#F0F4C3"

    .line 167
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#E6EE9C"

    .line 168
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#DCE775"

    .line 169
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#D4E157"

    .line 170
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#CDDC39"

    .line 171
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#C0CA33"

    .line 172
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#AFB42B"

    .line 173
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#9E9D24"

    .line 174
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#827717"

    .line 175
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v13

    new-array v1, v12, [I

    const-string v17, "#FFFDE7"

    .line 178
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FFF9C4"

    .line 179
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#FFF59D"

    .line 180
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#FFF176"

    .line 181
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#FFEE58"

    .line 182
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#FFEB3B"

    .line 183
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#FDD835"

    .line 184
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#FBC02D"

    .line 185
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#F9A825"

    .line 186
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#F57F17"

    .line 187
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v14

    new-array v1, v12, [I

    const-string v17, "#FFF8E1"

    .line 190
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FFECB3"

    .line 191
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#FFE082"

    .line 192
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#FFD54F"

    .line 193
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#FFCA28"

    .line 194
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#FFC107"

    .line 195
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#FFB300"

    .line 196
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#FFA000"

    .line 197
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#FF8F00"

    .line 198
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#FF6F00"

    .line 199
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v15

    new-array v1, v12, [I

    const-string v17, "#FFF3E0"

    .line 202
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FFE0B2"

    .line 203
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#FFCC80"

    .line 204
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#FFB74D"

    .line 205
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#FFA726"

    .line 206
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#FF9800"

    .line 207
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#FB8C00"

    .line 208
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#F57C00"

    .line 209
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#EF6C00"

    .line 210
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#E65100"

    .line 211
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    aput-object v1, v0, v16

    new-array v1, v12, [I

    const-string v17, "#FBE9E7"

    .line 214
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FFCCBC"

    .line 215
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#FFAB91"

    .line 216
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#FF8A65"

    .line 217
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#FF7043"

    .line 218
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#FF5722"

    .line 219
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#F4511E"

    .line 220
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#E64A19"

    .line 221
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#D84315"

    .line 222
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#BF360C"

    .line 223
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    const/16 v17, 0xf

    aput-object v1, v0, v17

    new-array v1, v12, [I

    const-string v17, "#EFEBE9"

    .line 226
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#D7CCC8"

    .line 227
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#BCAAA4"

    .line 228
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#A1887F"

    .line 229
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#8D6E63"

    .line 230
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#795548"

    .line 231
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#6D4C41"

    .line 232
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#5D4037"

    .line 233
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#4E342E"

    .line 234
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#3E2723"

    .line 235
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    const/16 v17, 0x10

    aput-object v1, v0, v17

    new-array v1, v12, [I

    const-string v17, "#FAFAFA"

    .line 238
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#F5F5F5"

    .line 239
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#EEEEEE"

    .line 240
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#E0E0E0"

    .line 241
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#BDBDBD"

    .line 242
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#9E9E9E"

    .line 243
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#757575"

    .line 244
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#616161"

    .line 245
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#424242"

    .line 246
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#212121"

    .line 247
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    const/16 v17, 0x11

    aput-object v1, v0, v17

    new-array v1, v12, [I

    const-string v17, "#ECEFF1"

    .line 250
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#CFD8DC"

    .line 251
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#B0BEC5"

    .line 252
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#90A4AE"

    .line 253
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    const-string v17, "#78909C"

    .line 254
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v6

    const-string v17, "#607D8B"

    .line 255
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v7

    const-string v17, "#546E7A"

    .line 256
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v8

    const-string v17, "#455A64"

    .line 257
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v9

    const-string v17, "#37474F"

    .line 258
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v10

    const-string v17, "#263238"

    .line 259
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v11

    const/16 v17, 0x12

    aput-object v1, v0, v17

    sput-object v0, Lcom/afollestad/materialdialogs/color/ColorPalette;->PRIMARY_COLORS_SUB:[[I

    .line 263
    const/16 v0, 0x10

    new-array v0, v0, [I

    const-string v1, "#FF1744"

    .line 264
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v2

    const-string v1, "#F50057"

    .line 265
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v3

    const-string v1, "#D500F9"

    .line 266
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v4

    const-string v1, "#651FFF"

    .line 267
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v5

    const-string v1, "#3D5AFE"

    .line 268
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v6

    const-string v1, "#2979FF"

    .line 269
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v7

    const-string v1, "#00B0FF"

    .line 270
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v8

    const-string v1, "#00E5FF"

    .line 271
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v9

    const-string v1, "#1DE9B6"

    .line 272
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v10

    const-string v1, "#00E676"

    .line 273
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v11

    const-string v1, "#76FF03"

    .line 274
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v12

    const-string v1, "#C6FF00"

    .line 275
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v13

    const-string v1, "#FFEA00"

    .line 276
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v14

    const-string v1, "#FFC400"

    .line 277
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v15

    const-string v1, "#FF9100"

    .line 278
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    aput v1, v0, v16

    const-string v1, "#FF3D00"

    .line 279
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    const/16 v17, 0xf

    aput v1, v0, v17

    sput-object v0, Lcom/afollestad/materialdialogs/color/ColorPalette;->ACCENT_COLORS:[I

    .line 282
    const/16 v0, 0x10

    new-array v0, v0, [[I

    new-array v1, v6, [I

    const-string v17, "#FF8A80"

    .line 284
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FF5252"

    .line 285
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#FF1744"

    .line 286
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#D50000"

    .line 287
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    aput-object v1, v0, v2

    new-array v1, v6, [I

    const-string v17, "#FF80AB"

    .line 290
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#FF4081"

    .line 291
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#F50057"

    .line 292
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#C51162"

    .line 293
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    aput-object v1, v0, v3

    new-array v1, v6, [I

    const-string v17, "#EA80FC"

    .line 296
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#E040FB"

    .line 297
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#D500F9"

    .line 298
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#AA00FF"

    .line 299
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    aput-object v1, v0, v4

    new-array v1, v6, [I

    const-string v17, "#B388FF"

    .line 302
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#7C4DFF"

    .line 303
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#651FFF"

    .line 304
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#6200EA"

    .line 305
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    aput-object v1, v0, v5

    new-array v1, v6, [I

    const-string v17, "#8C9EFF"

    .line 308
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#536DFE"

    .line 309
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#3D5AFE"

    .line 310
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#304FFE"

    .line 311
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    aput-object v1, v0, v6

    new-array v1, v6, [I

    const-string v17, "#82B1FF"

    .line 314
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v2

    const-string v17, "#448AFF"

    .line 315
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v3

    const-string v17, "#2979FF"

    .line 316
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v4

    const-string v17, "#2962FF"

    .line 317
    invoke-static/range {v17 .. v17}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v17

    aput v17, v1, v5

    aput-object v1, v0, v7

    new-array v1, v6, [I

    const-string v7, "#80D8FF"

    .line 320
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#40C4FF"

    .line 321
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#00B0FF"

    .line 322
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#0091EA"

    .line 323
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v8

    new-array v1, v6, [I

    const-string v7, "#84FFFF"

    .line 326
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#18FFFF"

    .line 327
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#00E5FF"

    .line 328
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#00B8D4"

    .line 329
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v9

    new-array v1, v6, [I

    const-string v7, "#A7FFEB"

    .line 332
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#64FFDA"

    .line 333
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#1DE9B6"

    .line 334
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#00BFA5"

    .line 335
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v10

    new-array v1, v6, [I

    const-string v7, "#B9F6CA"

    .line 338
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#69F0AE"

    .line 339
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#00E676"

    .line 340
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#00C853"

    .line 341
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v11

    new-array v1, v6, [I

    const-string v7, "#CCFF90"

    .line 344
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#B2FF59"

    .line 345
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#76FF03"

    .line 346
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#64DD17"

    .line 347
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v12

    new-array v1, v6, [I

    const-string v7, "#F4FF81"

    .line 350
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#EEFF41"

    .line 351
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#C6FF00"

    .line 352
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#AEEA00"

    .line 353
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v13

    new-array v1, v6, [I

    const-string v7, "#FFFF8D"

    .line 356
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#FFFF00"

    .line 357
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#FFEA00"

    .line 358
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#FFD600"

    .line 359
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v14

    new-array v1, v6, [I

    const-string v7, "#FFE57F"

    .line 362
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#FFD740"

    .line 363
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#FFC400"

    .line 364
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#FFAB00"

    .line 365
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v15

    new-array v1, v6, [I

    const-string v7, "#FFD180"

    .line 368
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v2

    const-string v7, "#FFAB40"

    .line 369
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v3

    const-string v7, "#FF9100"

    .line 370
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v4

    const-string v7, "#FF6D00"

    .line 371
    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    aput v7, v1, v5

    aput-object v1, v0, v16

    new-array v1, v6, [I

    const-string v6, "#FF9E80"

    .line 374
    invoke-static {v6}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v6

    aput v6, v1, v2

    const-string v2, "#FF6E40"

    .line 375
    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    aput v2, v1, v3

    const-string v2, "#FF3D00"

    .line 376
    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    aput v2, v1, v4

    const-string v2, "#DD2C00"

    .line 377
    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    aput v2, v1, v5

    const/16 v2, 0xf

    aput-object v1, v0, v2

    sput-object v0, Lcom/afollestad/materialdialogs/color/ColorPalette;->ACCENT_COLORS_SUB:[[I

    .line 282
    return-void
.end method

.method constructor <init>()V
    .locals 0

    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
