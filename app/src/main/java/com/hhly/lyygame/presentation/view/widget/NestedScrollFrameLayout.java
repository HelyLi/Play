package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

/**
 * Created by Simon on 16/9/7.
 */
public class NestedScrollFrameLayout extends FrameLayout implements NestedScrollingParent {

    private static final String TAG = "NestedScrollFrameLayout";

    private int mCurrentY = 0;

    private RecyclerView.LayoutManager mLayoutManager;

    public NestedScrollFrameLayout(Context context) {
        super(context);
        init();
    }

    public NestedScrollFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedScrollFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private static final Interpolator sQuinticInterpolator = new Interpolator() {
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };

    private void init () {
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {

    }

    @Override
    public void onStopNestedScroll(View child) {

    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
//        return super.onNestedFling(target, velocityX, velocityY, consumed);
        /*if (target instanceof RecyclerView) {
            int offset = ((RecyclerView) target).computeVerticalScrollOffset();
            int extent = ((RecyclerView) target).computeVerticalScrollExtent();
            int range = ((RecyclerView) target).computeVerticalScrollRange();

            Log.d(TAG, String.format("offset=%d, extend=%d, range=%d", offset, extent, range));

        }*/

        return false;
    }

    @Override
    public int getNestedScrollAxes() {
//        return super.getNestedScrollAxes();
        return 1 << 1;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
//        super.onNestedPreScroll(target, dx, dy, consumed);
        scrollTo(0, mCurrentY + (checkScrollBottom() ? 0 : dy));
    }

    private void dealWithScroll() {
        mCurrentY = Math.max(0, mCurrentY);
        if (mScrollChangeListener != null) {
            mScrollChangeListener.onScrolling(0, mCurrentY);
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (target instanceof RecyclerView) {
            mLayoutManager = ((RecyclerView) target).getLayoutManager();
        }
        mViewFlinger.fling((int)velocityX, (int)velocityY);

        return false;
    }

    public interface OnScrollChangeListener {
        void onScrolling(int dx, int dy);
    }

    private OnScrollChangeListener mScrollChangeListener;

    public void setScrollChangeListener(OnScrollChangeListener scrollChangeListener) {
        mScrollChangeListener = scrollChangeListener;
    }


    @Override
    public void scrollTo(int x, int y) {
        mCurrentY  = y;
        dealWithScroll();
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollTo(x, mCurrentY + y);
    }


    private int getScrollOffset (int last, int current) {
        return checkScrollBottom() ? 0 : current - last;
    }

    private boolean checkScrollBottom () {
        if (mLayoutManager != null) {
            int itemCount = mLayoutManager.getItemCount();
            if (mLayoutManager instanceof GridLayoutManager) {
                int lastPosition = ((GridLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition();
                if (lastPosition == itemCount - 1) {
                    //end
                    return true;
                }
            }
        }
        return false;
    }

    public void reset () {
        scrollTo(0, 0);
    }

    private static final int MAX_SCROLL_DURATION = 2000;

    private ViewFlinger mViewFlinger = new ViewFlinger();

    private class ViewFlinger implements Runnable {
        private int mLastFlingX;
        private int mLastFlingY;
        private ScrollerCompat mScroller;
        private Interpolator mInterpolator = sQuinticInterpolator;


        // When set to true, postOnAnimation callbacks are delayed until the run method completes
        private boolean mEatRunOnAnimationRequest = false;

        // Tracks if postAnimationCallback should be re-attached when it is done
        private boolean mReSchedulePostAnimationCallback = false;

        public ViewFlinger() {
            mScroller = ScrollerCompat.create(getContext(), sQuinticInterpolator);
        }

        @Override
        public void run() {
            disableRunOnAnimationRequests();
            // keep a local reference so that if it is changed during onAnimation method, it won't
            // cause unexpected behaviors
            final ScrollerCompat scroller = mScroller;
            if (scroller.computeScrollOffset()) {
                final int x = scroller.getCurrX();
                final int y = scroller.getCurrY();
                mCurrentY += getScrollOffset(mLastFlingY, y);
                mLastFlingX = x;
                mLastFlingY = y;

                scrollTo(0, mCurrentY);

                if (!scroller.isFinished()) {
                    postOnAnimation();

                }
            }
            // call this after the onAnimation is complete not to have inconsistent callbacks etc.
            enableRunOnAnimationRequests();
        }

        private void disableRunOnAnimationRequests() {
            mReSchedulePostAnimationCallback = false;
            mEatRunOnAnimationRequest = true;
        }

        private void enableRunOnAnimationRequests() {
            mEatRunOnAnimationRequest = false;
            if (mReSchedulePostAnimationCallback) {
                postOnAnimation();
            }
        }

        void postOnAnimation() {
            if (mEatRunOnAnimationRequest) {
                mReSchedulePostAnimationCallback = true;
            } else {
                removeCallbacks(this);
                ViewCompat.postOnAnimation(NestedScrollFrameLayout.this, this);
            }
        }

        public void fling(int velocityX, int velocityY) {
            mLastFlingX = mLastFlingY = 0;
            mScroller.fling(0, 0, velocityX, velocityY,
                    Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }

        public void smoothScrollBy(int dx, int dy) {
            smoothScrollBy(dx, dy, 0, 0);
        }

        public void smoothScrollBy(int dx, int dy, int vx, int vy) {
            smoothScrollBy(dx, dy, computeScrollDuration(dx, dy, vx, vy));
        }

        private float distanceInfluenceForSnapDuration(float f) {
            f -= 0.5f; // center the values about 0.
            f *= 0.3f * Math.PI / 2.0f;
            return (float) Math.sin(f);
        }

        private int computeScrollDuration(int dx, int dy, int vx, int vy) {
            final int absDx = Math.abs(dx);
            final int absDy = Math.abs(dy);
            final boolean horizontal = absDx > absDy;
            final int velocity = (int) Math.sqrt(vx * vx + vy * vy);
            final int delta = (int) Math.sqrt(dx * dx + dy * dy);
            final int containerSize = horizontal ? getWidth() : getHeight();
            final int halfContainerSize = containerSize / 2;
            final float distanceRatio = Math.min(1.f, 1.f * delta / containerSize);
            final float distance = halfContainerSize + halfContainerSize *
                    distanceInfluenceForSnapDuration(distanceRatio);

            final int duration;
            if (velocity > 0) {
                duration = 4 * Math.round(1000 * Math.abs(distance / velocity));
            } else {
                float absDelta = (float) (horizontal ? absDx : absDy);
                duration = (int) (((absDelta / containerSize) + 1) * 300);
            }
            return Math.min(duration, MAX_SCROLL_DURATION);
        }

        public void smoothScrollBy(int dx, int dy, int duration) {
            smoothScrollBy(dx, dy, duration, sQuinticInterpolator);
        }

        public void smoothScrollBy(int dx, int dy, int duration, Interpolator interpolator) {
            if (mInterpolator != interpolator) {
                mInterpolator = interpolator;
                mScroller = ScrollerCompat.create(getContext(), interpolator);
            }
            mLastFlingX = mLastFlingY = 0;
            mScroller.startScroll(0, 0, dx, dy, duration);
            postOnAnimation();
        }

        public void stop() {
            mLastFlingY = 0;
            removeCallbacks(this);
            mScroller.abortAnimation();
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mViewFlinger != null) {
            mViewFlinger.stop();
        }
    }

    private RecyclerView mRecyclerView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            if (child instanceof RecyclerView) {
                mRecyclerView = (RecyclerView) getChildAt(0);
                mRecyclerView.addOnScrollListener(mScrollListener);
            }
        }
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            scrollBy(dx, dy);
        }
    };
}
