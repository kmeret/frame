package io.github.kmeret.frame.infrastructure.presentation.widgets

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatSpinner

class NormalSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatSpinner(context, attrs, defStyleAttr) {

    private var onItemClick: OnItemClickListener? = null

    override fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
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
