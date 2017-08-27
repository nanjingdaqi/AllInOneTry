.class public Lorg/peace/allinone/ui/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


# instance fields
.field public bb:I

.field public volatile bl:Z


# direct methods
.method public constructor <init>()V
    .registers 2

    .prologue
    .line 11
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    .line 13
    const/16 v0, 0xa

    iput v0, p0, Lorg/peace/allinone/ui/MainActivity;->bb:I

    return-void
.end method


# virtual methods
.method public declared-synchronized foo()V
    .registers 4

    .prologue
    .line 32
    monitor-enter p0

    :try_start_1
    monitor-enter p0
    :try_end_2
    .catchall {:try_start_1 .. :try_end_2} :catchall_2b

    .line 33
    :try_start_2
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "foo"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const/16 v1, 0xa

    const/16 v2, 0xa

    invoke-static {v1, v2}, Lorg/peace/allinone/ui/A;->foo(II)I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 34
    monitor-exit p0
    :try_end_26
    .catchall {:try_start_2 .. :try_end_26} :catchall_28

    .line 35
    monitor-exit p0

    return-void

    .line 34
    :catchall_28
    move-exception v0

    :try_start_29
    monitor-exit p0
    :try_end_2a
    .catchall {:try_start_29 .. :try_end_2a} :catchall_28

    :try_start_2a
    throw v0
    :try_end_2b
    .catchall {:try_start_2a .. :try_end_2b} :catchall_2b

    .line 32
    :catchall_2b
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public onClick(Landroid/view/View;)V
    .registers 4
    .param p1, "v"    # Landroid/view/View;
    .annotation build Lbutterknife/OnClick;
        value = {
            0x7f0b006f
        }
    .end annotation

    .prologue
    .line 27
    new-instance v0, Lorg/peace/allinone/ui/A;

    invoke-direct {v0}, Lorg/peace/allinone/ui/A;-><init>()V

    .line 28
    .local v0, "a":Lorg/peace/allinone/ui/A;
    iget v1, v0, Lorg/peace/allinone/ui/A;->aa:I

    iput v1, p0, Lorg/peace/allinone/ui/MainActivity;->bb:I

    .line 29
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .registers 3
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 18
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 19
    const v0, 0x7f03001b

    invoke-virtual {p0, v0}, Lorg/peace/allinone/ui/MainActivity;->setContentView(I)V

    .line 21
    invoke-static {p0}, Lbutterknife/ButterKnife;->bind(Landroid/app/Activity;)Lbutterknife/Unbinder;

    .line 22
    return-void
.end method
