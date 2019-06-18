package io.github.kmeret.frame.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.presentation.extensions.inflateViewAsRoot
import io.github.kmeret.frame.infrastructure.presentation.extensions.obtainAttrs
import kotlinx.android.synthetic.main.widget_app_text_field.view.*


class AppTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val layout: TextInputLayout
        get() = text_field_layout

    val input: TextInputEditText
        get() = text_field_input

    init {
        inflateViewAsRoot(R.layout.widget_app_text_field)
        obtainAttrs(attrs, R.styleable.AppTextField) { array ->
            array.getString(R.styleable.AppTextField_hint)?.let { setHint(it) }
        }
    }

    private fun setHint(text: String) {
        layout.hint = text
    }

}