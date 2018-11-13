package io.github.kmeret.frame.lifecycle

import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.android.base.BaseActivity

abstract class ViewModelActivity<VM : ViewModel> : BaseActivity(), ViewModelHolder<VM> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

}
