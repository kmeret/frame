package io.github.kmeret.frame.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.presentation.extensions.inflateViewAsRoot

class AppProgress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflateViewAsRoot(R.layout.widget_app_progress)
    }

}