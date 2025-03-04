package com.ld5ehom.movie.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Keep
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.ld5ehom.movie.ui.R

class TopLeftCutoutBackgroundView : ShapeableImageView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TopLeftCutoutBackgroundView)
        maxCutSize = a.getDimension(R.styleable.TopLeftCutoutBackgroundView_topLeftCutSize, 0f)
        a.recycle()
        syncCutSize()
    }

    var maxCutSize: Float = 0f
        set(value) {
            field = value
            syncCutSize()
        }

    @set:Keep
    var cutProgress: Float = 1f
        set(value) {
            field = value
            syncCutSize()
        }

    private var currentCutSize = 0f

    private fun syncCutSize() {
        val newCutSize = maxCutSize * cutProgress
        if (newCutSize != currentCutSize) {
            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setTopLeftCorner(CornerFamily.CUT, newCutSize)
                .build()
            currentCutSize = newCutSize
        }
    }
}
