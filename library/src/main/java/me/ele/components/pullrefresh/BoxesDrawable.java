package me.ele.components.pullrefresh;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import me.ele.recyclerview.R;

class BoxesDrawable extends Drawable implements Animatable {
  private static final boolean DEBUG = false;

  @DrawableRes private static final int[] GOODS_DRAWABLES =
      {
          R.drawable.good_1,
          R.drawable.good_2,
          R.drawable.good_3,
          R.drawable.good_4,
          R.drawable.good_5,
          R.drawable.good_6
      };

  private static final float SHADOW_INSET_PERCENT = .33f;

  public static final float LID_FINAL_ROTATION = .625f;
  public static final float LID_MAXIMUM_ROTATION = .678f;

  private static final float LID_HORIZONTAL_INSET = 4.6f;
  private static final float LID_VERTICAL_INSET = 4.6f;

  private static final int ANIMATE_UNIT_DURATION = 2000;
  private static final int ANIMATE_SCALE_BOXES_DURATION = 300;
  private static final int ANIMATE_THROW_GOODS_DURATION = 600;
  private static final int ANIMATE_THROW_GOODS_DURATION_OFFSET = 150;

  private Bitmap mBoxesBitmap;
  private Bitmap mShadowBitmap;
  private Bitmap[] mLidBitmaps;
  private Bitmap[] mGoodsBitmaps;
  @DrawableRes private int[][] mGoodsPermutation;

  private RectF[] mParabolasBounds;

  private ThrowMeasure[][] mThrowMeasures;

  private Paint mPaint = new Paint();
  private Matrix mMatrix = new Matrix();

  private float mBoxesScale;
  private RectF mBoxesBounds;
  private RectF mOriginalBoxesBounds;

  private float mLidRotation;
  private RectF[] mLidBounds;
  private RectF[] mOriginalLidBounds;

  private float mLidHorizontalInset;
  private float mLidVerticalInset;

  private RectF mShadowBounds;

  private int mGoodsAmount;

  private View mParent;

  private Animation mAnimation;
  private Animation mBoxesAnimation;

  private Goods mGoods;

  private float mWidth;
  private float mHeight;

  public Bitmap getBitmapFromRes(Resources resources, int resId, Resources.Theme theme) {
    return ((BitmapDrawable) ResourcesCompat.getDrawable(resources, resId, theme)).getBitmap();
  }

  public BoxesDrawable(View parent) {
    Context context = parent.getContext();
    Resources resources = context.getResources();
    Resources.Theme theme = context.getTheme();
    mGoodsAmount = GOODS_DRAWABLES.length;
    mParent = parent;

    mBoxesBitmap = getBitmapFromRes(resources, R.drawable.boxes, theme);
    mShadowBitmap = getBitmapFromRes(resources, R.drawable.shadow, theme);

    mLidBitmaps = new Bitmap[2];
    mLidBitmaps[0] = getBitmapFromRes(resources, R.drawable.lid_l, theme);
    mLidBitmaps[1] = getBitmapFromRes(resources, R.drawable.lid_r, theme);

    Arrays.sort(GOODS_DRAWABLES);
    mGoodsPermutation = PermutateUtil.arrange(GOODS_DRAWABLES);
    mGoodsBitmaps = new Bitmap[mGoodsAmount];
    for (int i = 0; i < mGoodsAmount; i++) {
      mGoodsBitmaps[i] = getBitmapFromRes(resources, GOODS_DRAWABLES[i], theme);
    }

    mWidth = mLidBitmaps[0].getWidth() + mBoxesBitmap.getWidth() + mLidBitmaps[1].getWidth()
        + mGoodsBitmaps[0].getWidth() * 4;
    mHeight = mBoxesBitmap.getHeight() + mShadowBitmap.getHeight()
        + mGoodsBitmaps[0].getHeight() * 1.3f;

    mBoxesScale = 1.0f;

    mLidRotation = 0;

    final float density = context.getResources().getDisplayMetrics().density;
    mLidHorizontalInset = LID_HORIZONTAL_INSET * density;
    mLidVerticalInset = LID_VERTICAL_INSET * density;

    setupAnimators();
  }

  private void measureBounds(Rect bounds) {
    final RectF shadowBounds = new RectF();
    shadowBounds.left = bounds.left + (bounds.width() - mShadowBitmap.getWidth()) / 2f;
    shadowBounds.top = bounds.bottom - mShadowBitmap.getHeight();
    shadowBounds.right = shadowBounds.left + mShadowBitmap.getWidth();
    shadowBounds.bottom = bounds.bottom;
    mShadowBounds = shadowBounds;

    final RectF boxesBounds = new RectF();
    final float boxesMarginBottom = shadowBounds.height() * SHADOW_INSET_PERCENT;
    boxesBounds.left = bounds.left + (bounds.width() - mBoxesBitmap.getWidth()) / 2f;
    boxesBounds.top = bounds.bottom - mBoxesBitmap.getHeight() - boxesMarginBottom;
    boxesBounds.right = boxesBounds.left + mBoxesBitmap.getWidth();
    boxesBounds.bottom = bounds.bottom - boxesMarginBottom;
    mBoxesBounds = boxesBounds;
    mOriginalBoxesBounds = new RectF(boxesBounds);

    final RectF[] lidBounds = new RectF[2];
    lidBounds[0] = new RectF();
    lidBounds[0].left = boxesBounds.left - mLidBitmaps[0].getWidth();
    lidBounds[0].top = boxesBounds.top - mLidBitmaps[0].getWidth();
    lidBounds[0].right = boxesBounds.left + mLidBitmaps[0].getWidth();
    lidBounds[0].bottom = boxesBounds.top + mLidBitmaps[0].getWidth();
    lidBounds[1] = new RectF();
    lidBounds[1].left = boxesBounds.right - mLidBitmaps[1].getWidth();
    lidBounds[1].top = boxesBounds.top - mLidBitmaps[1].getWidth();
    lidBounds[1].right = boxesBounds.right + mLidBitmaps[1].getWidth();
    lidBounds[1].bottom = boxesBounds.top + mLidBitmaps[1].getWidth();
    mLidBounds = lidBounds;
    mOriginalLidBounds = new RectF[2];
    mOriginalLidBounds[0] = new RectF(lidBounds[0]);
    mOriginalLidBounds[1] = new RectF(lidBounds[1]);

    final RectF[] parabolasBounds = new RectF[2];
    final float parabolaSpan = boxesBounds.width() / 2f + lidBounds[0].width() / 2f
        + mGoodsBitmaps[0].getWidth() * 0.75f;
    final float parabolaHeight = boxesBounds.height() + mGoodsBitmaps[0].getHeight();
    parabolasBounds[0] = new RectF();
    parabolasBounds[0].left = boxesBounds.centerX() - parabolaSpan;
    parabolasBounds[0].bottom = boxesBounds.centerY() - boxesBounds.height() / 5;
    parabolasBounds[0].top = parabolasBounds[0].bottom - parabolaHeight;
    parabolasBounds[0].right = boxesBounds.centerX();

    parabolasBounds[1] = new RectF();
    parabolasBounds[1].left = boxesBounds.centerX();
    parabolasBounds[1].bottom = boxesBounds.centerY() - boxesBounds.height() / 5;
    parabolasBounds[1].top = parabolasBounds[1].bottom - parabolaHeight;
    parabolasBounds[1].right = boxesBounds.centerX() + parabolaSpan;

    mParabolasBounds = parabolasBounds;
  }

  private void setupThrowMeasures() {
    final ThrowMeasure[][] throwMeasures = new ThrowMeasure[2][3];

    final RectF[] parabolaBounds = mParabolasBounds;
    final Path[][] parabolaPaths = new Path[2][3];
    final PathMeasure[][] parabolaPathMeasures = new PathMeasure[2][3];
    final float[][][] parabolaControlPos = new float[2][3][2];
    float originX = parabolaBounds[0].right;
    float originY = parabolaBounds[0].bottom;

    for (int i = 0, offset = 0; i < 3; i++, offset += parabolaBounds[0].width() / 8) {
      final float x1 = originX - parabolaBounds[0].width() / 2f;
      final float y1 = originY - parabolaBounds[0].height() * 1.2f + offset;
      final float x2 = originX - parabolaBounds[0].width() + offset / 2;
      final float y2 = originY - (parabolaBounds[0].width() / 5) * 2 + offset;
      Path path = new Path();
      path.moveTo(originX, originY);
      path.quadTo(x1, y1, x2, y2);
      parabolaPaths[0][i] = path;
      parabolaPathMeasures[0][i] = new PathMeasure(path, false);
      parabolaControlPos[0][i][0] = x1;
      parabolaControlPos[0][i][1] = y1;
    }

    originX = parabolaBounds[1].left;
    originY = parabolaBounds[1].bottom;
    for (int i = 0, offset = 0; i < 3; i++, offset += parabolaBounds[1].width() / 8) {
      final float x1 = originX + parabolaBounds[1].width() / 2f;
      final float y1 = originY - parabolaBounds[1].height() * 1.2f + offset;
      final float x2 = originX + parabolaBounds[1].width() - offset / 2;
      final float y2 = originY - (parabolaBounds[0].width() / 5) * 2 + offset;
      Path path = new Path();
      path.moveTo(originX, originY);
      path.quadTo(x1, y1, x2, y2);
      parabolaPaths[1][i] = path;
      parabolaPathMeasures[1][i] = new PathMeasure(path, false);
      parabolaControlPos[1][i][0] = x1;
      parabolaControlPos[1][i][1] = y1;
    }

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        final Path path = parabolaPaths[i][j];
        final PathMeasure pathMeasure = parabolaPathMeasures[i][j];
        final float[] controlPos = parabolaControlPos[i][j];
        throwMeasures[i][j] = new ThrowMeasure(path, pathMeasure, controlPos);
      }
    }

    mThrowMeasures = throwMeasures;
  }

  @Override public void start() {
    mAnimation.start();
    mAnimation.setDuration(ANIMATE_UNIT_DURATION);
    mParent.startAnimation(mAnimation);
  }

  public void postStop() {
    if (mGoods != null) {
      mGoods.setTerminated();
    }
  }

  @Override public void stop() {
    mParent.clearAnimation();
    mAnimation.reset();
  }

  @Override public boolean isRunning() {
    return mAnimation.hasStarted() && !mAnimation.hasEnded();
  }

  @Override protected void onBoundsChange(Rect bounds) {
    measureBounds(bounds);
    setupThrowMeasures();
    mGoods = new Goods(mCallback, mGoodsAmount, mGoodsBitmaps, mGoodsPermutation, mThrowMeasures);
  }

  @Override public void draw(Canvas canvas) {
    if (DEBUG) {
      mPaint.reset();
      mPaint.setStyle(Paint.Style.STROKE);
      mPaint.setStrokeWidth(1);
      mPaint.setColor(Color.BLACK);
    }
    drawShadow(canvas);
    drawLids(canvas);
    drawGoods(canvas);
    drawBoxes(canvas);

    if (DEBUG) {
      canvas.drawRect(mParabolasBounds[0], mPaint);
      canvas.drawRect(mParabolasBounds[1], mPaint);
      mPaint.setPathEffect(new DashPathEffect(new float[] { 5, 5 }, 0));
      for (ThrowMeasure[] throwMeasures : mThrowMeasures) {
        for (ThrowMeasure throwMeasure : throwMeasures) {
          canvas.drawPath(throwMeasure.getPath(), mPaint);
        }
      }
    }
  }

  private void drawShadow(Canvas canvas) {
    final RectF shadowBounds = mShadowBounds;
    final float translateX = shadowBounds.left;
    final float translateY = shadowBounds.top;
    mMatrix.reset();
    mMatrix.postTranslate(translateX, translateY);
    canvas.drawBitmap(mShadowBitmap, mMatrix, null);

    if (DEBUG) {
      canvas.drawRect(mShadowBounds, mPaint);
    }
  }

  private void drawGoods(Canvas canvas) {
    mGoods.draw(canvas);
  }

  private void drawLids(Canvas canvas) {
    final RectF boxesBounds = mBoxesBounds;
    final float[] rotateAngle = new float[2];
    final float[][] pivotPos = new float[2][2];
    final float[][] translatePos = new float[2][2];

    rotateAngle[0] = mLidRotation * -360f;
    rotateAngle[1] = -mLidRotation * -360f;
    pivotPos[0][0] = boxesBounds.left + mLidHorizontalInset * mLidRotation;
    pivotPos[0][1] = boxesBounds.top + mLidVerticalInset * mLidRotation;
    pivotPos[1][0] = boxesBounds.right - mLidHorizontalInset * mLidRotation;
    pivotPos[1][1] = boxesBounds.top + mLidVerticalInset * mLidRotation;

    translatePos[0][0] = boxesBounds.left + mLidHorizontalInset * mLidRotation;
    translatePos[0][1] = boxesBounds.top + mLidVerticalInset * mLidRotation;
    translatePos[1][0] =
        boxesBounds.right - mLidBitmaps[0].getWidth() - mLidHorizontalInset * mLidRotation;
    translatePos[1][1] = boxesBounds.top + mLidVerticalInset * mLidRotation;

    for (int i = 0; i < 2; i++) {
      mMatrix.reset();
      mMatrix.postRotate(rotateAngle[i], pivotPos[i][0], pivotPos[i][1]);
      mMatrix.preTranslate(translatePos[i][0], translatePos[i][1]);
      canvas.drawBitmap(mLidBitmaps[i], mMatrix, null);

      if (DEBUG) {
        canvas.drawRect(mLidBounds[i], mPaint);
      }
    }
  }

  private void drawBoxes(Canvas canvas) {
    final RectF boxesBounds = mBoxesBounds;
    final RectF solidBoxesBounds = mOriginalBoxesBounds;
    final float pivotX = solidBoxesBounds.centerX();
    final float pivotY = solidBoxesBounds.centerY();
    float translateX = boxesBounds.left;
    float translateY = boxesBounds.top;
    mMatrix.reset();
    mMatrix.postScale(1f, mBoxesScale, pivotX, pivotY);
    mMatrix.preTranslate(translateX, translateY);
    canvas.drawBitmap(mBoxesBitmap, mMatrix, null);

    if (DEBUG) {
      canvas.drawRect(boxesBounds, mPaint);
    }
  }

  private static boolean isAnimationRunning(Animation animation) {
    return animation != null && animation.hasStarted() && !animation.hasEnded();
  }

  public void setLidRotation(float rotation) {
    mLidRotation = rotation;
    invalidateSelf();
  }

  public float getLidRotation() {
    return mLidRotation;
  }

  private void setupBoxesAnimator() {
    final Animation animation = new Animation() {
      private final RectF mTempBounds = new RectF();

      @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
        float boxesInterpolated = interpolatedTime * 2;
        if (boxesInterpolated <= 1.0f) {
          mBoxesScale = 1 - boxesInterpolated * 0.15f;
        } else if (boxesInterpolated <= 2.0f) {
          mBoxesScale = 1 - (2.0f - boxesInterpolated) * 0.15f;
        }
        final float solidBoxesHeight = mOriginalBoxesBounds.height();
        final float offset = solidBoxesHeight - solidBoxesHeight * mBoxesScale;

        final RectF boxesBounds = mTempBounds;
        boxesBounds.set(mOriginalBoxesBounds);
        boxesBounds.top = mOriginalBoxesBounds.top + offset;
        mBoxesBounds.set(boxesBounds);

        for (int i = mLidBounds.length - 1; i >= 0; --i) {
          final RectF lidBounds = mTempBounds;
          lidBounds.set(mOriginalLidBounds[i]);
          lidBounds.top += offset;
          mLidBounds[i].set(lidBounds);
        }

        invalidateSelf();
      }

      @Override public void reset() {
        super.reset();
        if (mOriginalBoxesBounds != null) {
          mBoxesBounds = new RectF(mOriginalBoxesBounds); // may cause NPE on low-api devices
        }
        mBoxesScale = 1;
      }
    };
    animation.setDuration(ANIMATE_SCALE_BOXES_DURATION);
    animation.setRepeatMode(Animation.RESTART);
    animation.setRepeatCount(Animation.INFINITE);
    mBoxesAnimation = animation;
  }

  private void setupAnimators() {
    setupBoxesAnimator();
    final Animation animationSet = new Animation() {
      @Override
      public boolean getTransformation(long currentTime, Transformation outTransformation) {
        mBoxesAnimation.getTransformation(currentTime, null);
        mGoods.getTransformation(currentTime, null);
        return super.getTransformation(currentTime, outTransformation);
      }

      @Override public void start() {
        super.start();
        mBoxesAnimation.start();
        mGoods.start();
      }

      @Override public void reset() {
        super.reset();
        mBoxesAnimation.reset();
        if (mGoods != null) {
          mGoods.reset();
        }
      }
    };
    animationSet.setRepeatMode(Animation.RESTART);
    animationSet.setRepeatCount(Animation.INFINITE);
    mAnimation = animationSet;
  }

  @Override public int getIntrinsicWidth() {
    return (int) mWidth;
  }

  @Override public int getIntrinsicHeight() {
    return (int) mHeight;
  }

  @Override public void setAlpha(int alpha) {
  }

  @Override public void setColorFilter(ColorFilter colorFilter) {
  }

  @Override public int getOpacity() {
    return 0;
  }

  private static class GoodCache {
    private List<Good> mCachedGoods = new LinkedList<>();

    public Good[] get(final int amount) {
      final Good[] goods = new Good[amount];
      for (int i = amount - 1; i >= 0; --i) {
        final Good tempGood;
        if (mCachedGoods.size() > 0) {
          tempGood = mCachedGoods.remove(0);
        } else {
          tempGood = new Good();
        }
        goods[i] = tempGood;
      }

      return goods;
    }

    public void recycle(final Good[] goods) {
      final int amount = goods.length;
      for (int i = amount - 1; i >= 0; --i) {
        final Good good = goods[i];
        good.setBitmap(null);
        good.setDistance(0);
        good.setBounds(null);
        good.setThrowMeasure(null);
        mCachedGoods.add(good);
      }
    }
  }

  private static class Good {
    private Bitmap mBitmap;
    private boolean mInverse;
    private float mDistance;
    private RectF mBounds;
    private ThrowMeasure mThrowMeasure;

    private long mDuration;
    private long mStartOffset;

    private final Matrix mMatrix = new Matrix();
    private final Paint mPaint = new Paint();

    private int mWidth;
    private int mHeight;

    private Animation mAnimation;

    private boolean mRunning;

    public Good() {
      mPaint.setStyle(Paint.Style.STROKE);
      mPaint.setStrokeWidth(1);
      mPaint.setColor(Color.BLACK);
    }

    public void setBitmap(Bitmap bitmap) {
      mBitmap = bitmap;
      mWidth = bitmap != null ? bitmap.getWidth() : 0;
      mHeight = bitmap != null ? bitmap.getHeight() : 0;
    }

    public void setInverse(boolean inverse) {
      mInverse = inverse;
    }

    public void setDistance(float distance) {
      mDistance = distance;
    }

    public void setBounds(RectF bounds) {
      mBounds = bounds;
    }

    public void setThrowMeasure(ThrowMeasure throwMeasure) {
      mThrowMeasure = throwMeasure;
    }

    public void setStartOffset(long startOffset) {
      mStartOffset = startOffset;
    }

    public void setDuration(long durationMillis) {
      mDuration = durationMillis;
    }

    public boolean isRunning() {
      return mRunning;
    }

    public Animation getAnimation() {
      final Animation animation;
      if (mAnimation != null) {
        animation = mAnimation;
      } else {
        animation = new Animation() {
          @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
            mDistance = mThrowMeasure.getTotalDistance() * interpolatedTime;

            final float[] currPos = mThrowMeasure.getPos(mDistance);
            final float translateX = currPos[0] - mWidth / 2f;
            final float translateY = currPos[1] - mHeight / 2f;
            mBounds.set(translateX, translateY, translateX + mWidth, translateY + mHeight);

            mRunning = mDistance != 0 && mDistance != mThrowMeasure.getTotalDistance();
          }

          @Override public void reset() {
            super.reset();
            mRunning = false;
          }
        };
        animation.setStartOffset(mStartOffset);
        animation.setDuration(mDuration);
        mAnimation = animation;
      }
      return animation;
    }

    public void draw(Canvas canvas) {
      if (!isRunning()) {
        return;
      }
      final float translateX = mBounds.left;
      final float translateY = mBounds.top;
      mMatrix.reset();
      mMatrix.preTranslate(translateX, translateY);
      canvas.drawBitmap(mBitmap, mMatrix, null);

      if (DEBUG) {
        canvas.save();
        canvas.drawRect(mBounds, mPaint);
        canvas.restore();
      }
    }
  }

  private final Callback mCallback = new Callback() {
    @Override
    public void invalidateDrawable(Drawable d) {
      invalidateSelf();
    }

    @Override
    public void scheduleDrawable(Drawable d, Runnable what, long when) {
      scheduleSelf(what, when);
    }

    @Override
    public void unscheduleDrawable(Drawable d, Runnable what) {
      unscheduleSelf(what);
    }
  };

  private static class Goods {
    private Random mRandom = new Random();
    private GoodCache mGoodCache = new GoodCache();

    private int mAmount;
    private Bitmap[] mBitmaps;
    private int[][] mPermutation;
    private ThrowMeasure[][] mThrowMeasures;

    private Map<Animation, Good[]> mGoodsMap;

    private final Callback mCallback;

    private boolean mLastAnimationEnd = true;

    private boolean mTerminated;

    public Goods(Callback callback, int amount, Bitmap[] bitmaps, int[][] permutation,
        ThrowMeasure[][] throwMeasures) {
      mCallback = callback;
      mAmount = amount;
      mBitmaps = bitmaps;
      mPermutation = permutation;
      mThrowMeasures = throwMeasures;
    }

    public void setTerminated() {
      if (isRunning()) {
        final Map<Animation, Good[]> runningMap = mGoodsMap;
        List<Animation> dropAnims = new ArrayList<>();
        for (Map.Entry<Animation, Good[]> entry : runningMap.entrySet()) {
          final Animation anim = entry.getKey();
          if (!isAnimationRunning(anim)) {
            dropAnims.add(anim);
          }
        }
        for (Animation anim : dropAnims) {
          anim.cancel();
          runningMap.remove(anim);
        }
        mTerminated = true;
      }
    }

    private Bitmap[] rearrangeGoodsBitmaps() {
      final int permutationSize = mPermutation.length;
      final int randomIndex = mRandom.nextInt(permutationSize);
      final int[] randomGoodsDrawableIds = mPermutation[randomIndex];
      final Bitmap[] rearrangedBitmaps = new Bitmap[mAmount];
      for (int i = 0; i < mAmount; i++) {
        int index = Arrays.binarySearch(GOODS_DRAWABLES, randomGoodsDrawableIds[i]);
        rearrangedBitmaps[i] = mBitmaps[index];
      }
      return rearrangedBitmaps;
    }

    private ThrowMeasure[] rearrangeGoodsThrowMeasures() {
      final ThrowMeasure[] rearrangedThrowMeasures = new ThrowMeasure[mAmount];
      for (int i = 0; i < mAmount; i++) {
        final int randomPos = mRandom.nextInt(mThrowMeasures[i % 2].length);
        rearrangedThrowMeasures[i] = mThrowMeasures[i % 2][randomPos];
      }
      return rearrangedThrowMeasures;
    }

    private Good[] propagateGoods() {
      final Good[] goods = mGoodCache.get(mAmount);
      final Bitmap[] bitmaps = rearrangeGoodsBitmaps();
      final ThrowMeasure[] throwMeasures = rearrangeGoodsThrowMeasures();
      for (int i = 0; i < mAmount; i++) {
        final Good good = goods[i];
        good.setInverse(i % 2 == 0);
        good.setBounds(new RectF());
        good.setBitmap(bitmaps[i]);
        good.setThrowMeasure(throwMeasures[i]);
        good.setDuration(ANIMATE_THROW_GOODS_DURATION);
        good.setStartOffset(i * ANIMATE_THROW_GOODS_DURATION_OFFSET);
      }
      return goods;
    }

    private Animation propagateAnimation(final Good[] goods, final GoodsListener listener) {
      final Animation animation = new Animation() {
        @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
          invalidateSelf();
        }

        @Override
        public boolean getTransformation(long currentTime, Transformation outTransformation) {
          for (Good good : goods) {
            good.getAnimation().getTransformation(currentTime, outTransformation);
          }

          boolean rightTimeToStartNextAnimation = getDuration() - (currentTime - getStartTime())
              < ANIMATE_THROW_GOODS_DURATION - ANIMATE_THROW_GOODS_DURATION_OFFSET;
          if (isAnimationRunning(this) && !mTerminated && rightTimeToStartNextAnimation
              && mLastAnimationEnd) {
            if (listener != null) {
              listener.onNext();
            }
          }

          return super.getTransformation(currentTime, outTransformation);
        }

        @Override public void start() {
          super.start();
          for (Good good : goods) {
            good.getAnimation().start();
          }
        }

        @Override public void reset() {
          super.reset();
          for (Good good : goods) {
            good.getAnimation().reset();
          }
          mTerminated = false;
        }
      };
      long duration = 0;
      for (Good good : goods) {
        duration = Math.max(duration, good.getAnimation().getStartOffset()
            + good.getAnimation().getDuration());
      }
      animation.setDuration(duration);
      animation.setAnimationListener(new Animation.AnimationListener() {
        @Override public void onAnimationStart(Animation animation) {
        }

        @Override public void onAnimationRepeat(Animation animation) {
        }

        @Override public void onAnimationEnd(Animation animation) {
          if (listener != null) {
            listener.onEnd(animation);
          }
        }
      });
      return animation;
    }

    public boolean getTransformation(long currentTime, Transformation outTransformation) {
      final Map<Animation, Good[]> runningMap = mGoodsMap;
      boolean more = false;
      Set<Animation> runningAnimationSet = runningMap.keySet();
      Animation[] runningAnimationArray = runningAnimationSet
          .toArray(new Animation[runningAnimationSet.size()]);
      for (Animation a : runningAnimationArray) {
        more = a.getTransformation(currentTime, outTransformation) || more;
      }
      return more;
    }

    public boolean isRunning() {
      final Map<Animation, Good[]> runningMap = mGoodsMap;
      return runningMap != null && runningMap.size() > 0;
    }

    public void draw(Canvas canvas) {
      if (isRunning()) {
        final Map<Animation, Good[]> runningMap = mGoodsMap;
        for (Map.Entry<Animation, Good[]> entry : runningMap.entrySet()) {
          Good[] goods = entry.getValue();
          for (Good good : goods) {
            good.draw(canvas);
          }
        }
      }
    }

    public void start() {
      Map<Animation, Good[]> runningMap = mGoodsMap;
      if (isRunning()) {
        for (Map.Entry<Animation, Good[]> entry : runningMap.entrySet()) {
          Good[] goods = entry.getValue();
          mGoodCache.recycle(goods);
        }
        runningMap.clear();
      } else {
        runningMap = new LinkedHashMap<>();
      }

      final Good[] goods = propagateGoods();
      final Animation animation = propagateAnimation(goods, mPropagateAndRecycleListener);
      animation.start();
      runningMap.put(animation, goods);
      mGoodsMap = runningMap;
      mLastAnimationEnd = true;
    }

    public void reset() {
      if (isRunning()) {
        final Map<Animation, Good[]> runningMap = mGoodsMap;
        for (Animation a : runningMap.keySet()) {
          a.reset();
        }
      }
    }

    interface GoodsListener {
      void onEnd(Animation animation);

      void onNext();
    }

    private GoodsListener mPropagateAndRecycleListener = new GoodsListener() {
      @Override public void onEnd(Animation animation) {
        final Map<Animation, Good[]> runningMap = mGoodsMap;
        Good[] goods = runningMap.get(animation);
        mGoodCache.recycle(goods);
        runningMap.remove(animation);
        mLastAnimationEnd = true;
      }

      @Override public void onNext() {
        final Map<Animation, Good[]> runningMap = mGoodsMap;
        final Good[] goods = propagateGoods();
        final Animation animation = propagateAnimation(goods, this);
        animation.start();
        runningMap.put(animation, goods);
        mLastAnimationEnd = false;
      }
    };

    private void invalidateSelf() {
      mCallback.invalidateDrawable(null);
    }
  }

  private static class ThrowMeasure {
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float[] mControlPos;

    public ThrowMeasure(Path path, PathMeasure pathMeasure, float[] controlPos) {
      mPath = path;
      mPathMeasure = pathMeasure;
      mControlPos = controlPos;
    }

    public Path getPath() {
      return mPath;
    }

    public PathMeasure getPathMeasure() {
      return mPathMeasure;
    }

    public float getTotalDistance() {
      return mPathMeasure.getLength();
    }

    public float[] getControlPos() {
      return mControlPos;
    }

    public float[] getPos(float distance) {
      final float[] pos = new float[2];
      mPathMeasure.getPosTan(distance, pos, null);
      return pos;
    }

    public float[] getStartPos() {
      return getPos(0);
    }

    public float[] getEndPos() {
      return getPos(getTotalDistance());
    }
  }

  private static class FlipInterpolator implements TimeInterpolator {
    private ThrowMeasure mThrowMeasure;

    private OverController mOverController;

    private boolean mOverControlPos;

    private float mInputOffset;

    public FlipInterpolator(ThrowMeasure throwMeasure) {
      mThrowMeasure = throwMeasure;

      final float[] startPos = throwMeasure.getStartPos();
      final float[] endPos = throwMeasure.getEndPos();
      final boolean reverse = startPos[0] < endPos[0];
      if (reverse) {
        mOverController = new OverController() {
          @Override public boolean hasOverControl(float contourX, float controlX) {
            return contourX < controlX;
          }
        };
      } else {
        mOverController = new OverController() {
          @Override public boolean hasOverControl(float contourX, float controlX) {
            return contourX > controlX;
          }
        };
      }
    }

    public boolean hasInterpolated(float input, float distance) {
      if (mOverControlPos) {
        return true;
      }
      final float[] currPos = mThrowMeasure.getPos(distance);
      final float contourX = currPos[0];
      final float controlX = mThrowMeasure.getControlPos()[0];
      if (hasOverControl(contourX, controlX)) {
        mInputOffset = input;
        mOverControlPos = true;
        return true;
      }
      return false;
    }

    private boolean hasOverControl(float contourX, float controlX) {
      return mOverController.hasOverControl(contourX, controlX);
    }

    @Override public float getInterpolation(float input) {
      if (!mOverControlPos) {
        return 0;
      }
      return (input - mInputOffset) / (1.0f - mInputOffset);
    }

    interface OverController {
      boolean hasOverControl(float contourX, float controlX);
    }
  }
}
