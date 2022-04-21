package com.lzc.bfer.ui.animation

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Animatable
import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import com.lzc.bfer.ui.animation.FAnimation

/**
- @author:  LZC
- @time:  2021/7/26
- @desc:
 */

@SuppressLint("NewApi")
class TextLoadingDrawable(animator: FAnimation) : Drawable(), Animatable {
    private var animation : FAnimation = animator

    init {
        animation.setDrawable(this)
        animator.setCallback(object : Callback{
            override fun invalidateDrawable(who: Drawable) {
                //实现来重绘这个 Drawable
                invalidateSelf()
            }

            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
                //实现来安排这个 Drawable
                scheduleSelf(what, `when`)
            }

            override fun unscheduleDrawable(who: Drawable, what: Runnable) {
                //实现来取消调度此 Drawable
                unscheduleSelf(what)
            }

        })
        animation.initAnimators()
    }

    override fun draw(canvas: Canvas) {
        if (!bounds.isEmpty){
            animation.onAnimationDraw(canvas)
        }
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }


    override fun start() {
        animation.start()

    }

    override fun stop() {
        animation.stop()
    }

    override fun isRunning(): Boolean {
        return animation.isRunning()
    }

}