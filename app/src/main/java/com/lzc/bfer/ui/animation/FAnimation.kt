package com.lzc.bfer.ui.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.Drawable
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.annotation.FloatRange
import com.lzc.bfer.R
import kotlin.math.ceil

/**
- @author:  LZC
- @time:  2021/7/23
- @desc:
 */
@SuppressLint("NewApi")
class FAnimation
constructor(context: Context) : ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    private var mDrawTextCount = 0
    private val mStartDelay: Long = 333
    private var mDurationTimePercent = 1.0
    private val mContext: Context = context
    private var defaultText = "BATTLEFIELD SCORE SEARCH"
    private val animationDuration: Long = 800
    private val mTextPaint = Paint(ANTI_ALIAS_FLAG)
    lateinit var mFloatValueAnimator: ValueAnimator
    private lateinit var mCallback: Drawable.Callback
    private lateinit var drawable: Drawable

    fun onAnimationDraw(canvas: Canvas) {
        val centerXY = dip2px(56.0f) * 0.5f
        val measureText: Float = Paint().measureText(defaultText, 0, defaultText.length)
        val pp = Paint(mTextPaint)
        pp.alpha = 100
        canvas.drawText(
            defaultText, 0, defaultText.length,
            centerXY - measureText / 2, centerXY, pp
        )
        canvas.drawText(
            defaultText, 0, mDrawTextCount,
            centerXY - measureText / 2, centerXY, mTextPaint
        )
    }

    private fun prepareStart(floatValueAnimator: ValueAnimator) {
        floatValueAnimator.duration =
            ceil((getAnimationDuration() * 0.3f).toDouble()).toLong()
        floatValueAnimator.interpolator = AccelerateInterpolator()
    }

    fun setDrawable(drawable: Drawable) {
        this.drawable = drawable
    }

    private fun computeUpdateValue(
        animation: ValueAnimator?, @FloatRange(from = 0.0, to = 1.0) animatedValue: Float
    ) {
        mTextPaint.alpha = (animatedValue * 155).toInt() +70
    }

    init {
        mTextPaint.apply {
            textSize = 60f
            color = context.getColor(R.color.greens)
            isDither = true
            isFilterBitmap = true
            style = Paint.Style.FILL
            textAlign = Paint.Align.LEFT
        }
        initAnimators()
    }

    fun initAnimators() {
        mFloatValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
            repeatCount = Animation.INFINITE
            duration = animationDuration
            startDelay = mStartDelay
            interpolator = LinearInterpolator()
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        computeUpdateValue(animation, animation!!.animatedValue as Float)
        mCallback.invalidateDrawable(drawable)

    }

    fun start() {
        mFloatValueAnimator.apply {
            if (isStarted) {
                return
            }
            addUpdateListener(this@FAnimation)
            addListener(this@FAnimation)
            repeatCount = Animation.INFINITE
            duration = getAnimationDuration()
            prepareStart(this)
        }.start()
    }

    fun stop() {
        mFloatValueAnimator.apply {
            removeAllUpdateListeners()
            removeAllListeners()
            repeatCount = 0
            duration = 0
        }.end()
    }

    fun isRunning(): Boolean {
        return mFloatValueAnimator.isRunning
    }

    fun setCallback(callback: Drawable.Callback) {
        this.mCallback = callback
    }

    private fun getAnimationDuration(): Long {
        return ceil(animationDuration * mDurationTimePercent).toLong()
    }

    override fun onAnimationStart(animation: Animator?) {

    }

    override fun onAnimationEnd(animation: Animator?) {

    }

    override fun onAnimationCancel(animation: Animator?) {

    }

    override fun onAnimationRepeat(animation: Animator?) {
        if (++mDrawTextCount > defaultText.toCharArray().size) { //还原到第一阶段
            mDrawTextCount = 0
        }
    }

    /**
     * @param density 屏幕分辨率密度 返回当前屏幕像素密度比 ，1.0,1.1,1.2,1.3....
     * */
    private fun dip2px(dpValue: Float): Float {
        return dpValue * mContext.resources.displayMetrics.density
    }

}