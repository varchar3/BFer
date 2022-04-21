package com.lzc.bfer.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.lzc.bfer.R
import com.lzc.bfer.ui.animation.bounceballview.BounceBallView

/**
- @author:  LZC
- @time:  2021/10/15
- @desc:
 */
class BallBounceDialog(context: Context) :Dialog(context, R.style.NormalDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ball_bounce_dialog)
        val bbv: BounceBallView = findViewById<View>(R.id.bbv) as BounceBallView
        bbv.start()
    }
}