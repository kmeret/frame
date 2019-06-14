package io.github.kmeret.frame.infrastructure.presentation.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.kmeret.frame.infrastructure.application.BaseApp
import io.github.kmeret.frame.infrastructure.data.ConvertException
import io.github.kmeret.frame.infrastructure.presentation.extensions.hideKeyboard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class LayoutFragment : Fragment(), LayoutHolder {

    val layoutActivity: LayoutActivity?
        get() = activity as? LayoutActivity

    inline fun <reified T> getBaseActivity(): T {
        return activity as? T ?: throw ConvertException()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard(activity)
    }

    protected fun delay(duration: Long = BaseApp.DEFAULT_DELAY_MS, action: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            kotlinx.coroutines.delay(duration)
            action.invoke()
        }
    }
}