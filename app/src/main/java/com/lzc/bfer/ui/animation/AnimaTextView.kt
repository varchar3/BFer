package com.lzc.bfer.ui.animation

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
- @author:  LZC
- @time:  2021/7/26
- @desc:
 */
@SuppressLint("AppCompatCustomView")
class AnimaTextView : ImageView {
    private var fAnima : FAnimation = FAnimation(context!!)
    private lateinit var drawable: TextLoadingDrawable
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    init {
        initDrawable()
    }

    private fun initDrawable(){
        //多加一
        drawable = TextLoadingDrawable(fAnima)
        setImageDrawable(drawable)

    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        val visible = visibility == VISIBLE && getVisibility() == VISIBLE
        if (visible) {
            drawable.start()
        } else {
            drawable.stop()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        drawable.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        drawable.stop()
    }


}