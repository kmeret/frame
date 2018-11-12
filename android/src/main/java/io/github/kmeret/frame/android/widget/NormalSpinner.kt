package io.github.kmeret.frame.android.widget

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.AdapterView

import androidx.appcompat.widget.AppCompatSpinner

class NormalSpinner : AppCompatSpinner {

    private var onItemClick: AdapterView.OnItemClickListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, mode: Int) : super(context, mode)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, mode: Int) : super(context, attrs, defStyleAttr, mode)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, mode: Int, popupTheme: Resources.Theme) : super(context, attrs, defStyleAttr, mode, popupTheme)

    override fun setOnItemClickListener(onItemClickListener: AdapterView.OnItemClickListener) {
        this.onItemClick = onItemClickListener
    }

    override fun setSelection(position: Int) {
        super.setSelection(position)
        performItemClick()
    }

    override fun setSelection(position: Int, animate: Boolean) {
        super.setSelection(position, animate)
        performItemClick()
    }

    fun setSelection(position: Int, animate: Boolean, fromUser: Boolean) {
        super.setSelection(position, animate)
        if (fromUser) performItemClick()
    }

    private fun performItemClick() {
        onItemClick?.onItemClick(this, selectedView, selectedItemPosition, selectedItemId)
    }
}
