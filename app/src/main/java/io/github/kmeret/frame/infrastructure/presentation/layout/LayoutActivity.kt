package io.github.kmeret.frame.infrastructure.presentation.layout

import android.os.Bundle
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.google.android.material.snackbar.Snackbar
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.application.BaseApp

abstract class LayoutActivity : AppCompatActivity(), LayoutHolder {

    private val snackBar by lazy { createErrorSnackBar() }

    val baseApp: BaseApp?
        get() = application as? BaseApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        initLayout(savedInstanceState)
    }

    abstract fun getCoordinatorLayout(): CoordinatorLayout

    fun showError(throwable: Throwable) = baseApp?.let { showError(it.mapError(throwable)) }

    fun showError(@StringRes textResId: Int) = snackBar.setText(textResId).show()

    fun showError(text: String) = snackBar.setText(text).show()

    private fun createErrorSnackBar(): Snackbar {
        val errorSnackBar = Snackbar.make(getCoordinatorLayout(), "", 3000)
        val snackBarView = errorSnackBar.view.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        }
        val textView = snackBarView.findViewById(R.id.snackbar_text) as TextView
        TextViewCompat.setTextAppearance(textView, R.style.AppSnackBarText)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        return errorSnackBar
    }
}
