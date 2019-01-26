.class Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;
.super Ljava/lang/Object;
.source "FragmentManager.java"

# interfaces
.implements Landroid/support/v4/app/Fragment$OnStartEnterTransitionListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroid/support/v4/app/FragmentManagerImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "StartEnterTransitionListener"
.end annotation


# instance fields
.field private final mIsBack:Z

.field private mNumPostponed:I

.field private final mRecord:Landroid/support/v4/app/BackStackRecord;


# direct methods
.method constructor <init>(Landroid/support/v4/app/BackStackRecord;Z)V
    .locals 0
    .param p1, "record"    # Landroid/support/v4/app/BackStackRecord;
    .param p2, "isBack"    # Z

    .line 3521
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 3522
    iput-boolean p2, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mIsBack:Z

    .line 3523
    iput-object p1, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    .line 3524
    return-void
.end method

.method static synthetic access$000(Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Z
    .locals 1
    .param p0, "x0"    # Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;

    .line 3515
    iget-boolean v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mIsBack:Z

    return v0
.end method

.method static synthetic access$100(Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/support/v4/app/BackStackRecord;
    .locals 1
    .param p0, "x0"    # Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;

    .line 3515
    iget-object v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    return-object v0
.end method


# virtual methods
.method public cancelTransaction()V
    .locals 4

    .line 3582
    iget-object v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    iget-object v0, v0, Landroid/support/v4/app/BackStackRecord;->mManager:Landroid/support/v4/app/FragmentManagerImpl;

    iget-object v1, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    iget-boolean v2, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mIsBack:Z

    const/4 v3, 0x0

    invoke-static {v0, v1, v2, v3, v3}, Landroid/support/v4/app/FragmentManagerImpl;->access$300(Landroid/support/v4/app/FragmentManagerImpl;Landroid/support/v4/app/BackStackRecord;ZZZ)V

    .line 3583
    return-void
.end method

.method public completeTransaction()V
    .locals 8

    .line 3564
    iget v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-lez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 3565
    .local v0, "canceled":Z
    :goto_0
    iget-object v3, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    iget-object v3, v3, Landroid/support/v4/app/BackStackRecord;->mManager:Landroid/support/v4/app/FragmentManagerImpl;

    .line 3566
    .local v3, "manager":Landroid/support/v4/app/FragmentManagerImpl;
    iget-object v4, v3, Landroid/support/v4/app/FragmentManagerImpl;->mAdded:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    .line 3567
    .local v4, "numAdded":I
    const/4 v5, 0x0

    .local v5, "i":I
    :goto_1
    if-ge v5, v4, :cond_2

    .line 3568
    iget-object v6, v3, Landroid/support/v4/app/FragmentManagerImpl;->mAdded:Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Landroid/support/v4/app/Fragment;

    .line 3569
    .local v6, "fragment":Landroid/support/v4/app/Fragment;
    const/4 v7, 0x0

    invoke-virtual {v6, v7}, Landroid/support/v4/app/Fragment;->setOnStartEnterTransitionListener(Landroid/support/v4/app/Fragment$OnStartEnterTransitionListener;)V

    .line 3570
    if-eqz v0, :cond_1

    invoke-virtual {v6}, Landroid/support/v4/app/Fragment;->isPostponed()Z

    move-result v7

    if-eqz v7, :cond_1

    .line 3571
    invoke-virtual {v6}, Landroid/support/v4/app/Fragment;->startPostponedEnterTransition()V

    .line 3567
    .end local v6    # "fragment":Landroid/support/v4/app/Fragment;
    :cond_1
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    .line 3574
    .end local v5    # "i":I
    :cond_2
    iget-object v5, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    iget-object v5, v5, Landroid/support/v4/app/BackStackRecord;->mManager:Landroid/support/v4/app/FragmentManagerImpl;

    iget-object v6, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    iget-boolean v7, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mIsBack:Z

    if-nez v0, :cond_3

    const/4 v1, 0x1

    nop

    :cond_3
    invoke-static {v5, v6, v7, v1, v2}, Landroid/support/v4/app/FragmentManagerImpl;->access$300(Landroid/support/v4/app/FragmentManagerImpl;Landroid/support/v4/app/BackStackRecord;ZZZ)V

    .line 3575
    return-void
.end method

.method public isReady()Z
    .locals 1

    .line 3554
    iget v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public onStartEnterTransition()V
    .locals 1

    .line 3533
    iget v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    .line 3534
    iget v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    if-eqz v0, :cond_0

    .line 3535
    return-void

    .line 3537
    :cond_0
    iget-object v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mRecord:Landroid/support/v4/app/BackStackRecord;

    iget-object v0, v0, Landroid/support/v4/app/BackStackRecord;->mManager:Landroid/support/v4/app/FragmentManagerImpl;

    invoke-static {v0}, Landroid/support/v4/app/FragmentManagerImpl;->access$200(Landroid/support/v4/app/FragmentManagerImpl;)V

    .line 3538
    return-void
.end method

.method public startListening()V
    .locals 1

    .line 3547
    iget v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Landroid/support/v4/app/FragmentManagerImpl$StartEnterTransitionListener;->mNumPostponed:I

    .line 3548
    return-void
.end method
