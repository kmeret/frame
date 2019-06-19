package io.github.kmeret.frame.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.button.MaterialButton
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.presentation.extensions.inflateViewAsRoot
import io.github.kmeret.frame.infrastructure.presentation.extensions.obtainAttrs
import kotlinx.android.synthetic.main.widget_app_button.view.*

class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val button: MaterialButton
        get() = app_button

    init {
        inflateViewAsRoot(R.layout.widget_app_button)
        obtainAttrs(attrs, R.styleable.AppButton) { array ->
            array.getString(R.styleable.AppButton_title)?.let { setTitle(it) }
        }
    }

    override fun setOnClickListener(onClickListener: OnClickListener?) {
        button.setOnClickListener(onClickListener)
    }

    private fun setTitle(text: String) {
        button.text = text
    }

}