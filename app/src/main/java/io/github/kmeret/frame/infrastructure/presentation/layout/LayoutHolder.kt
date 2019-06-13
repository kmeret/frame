package io.github.kmeret.frame.infrastructure.presentation.layout

import android.os.Bundle

interface LayoutHolder {
    val layoutResId: Int
    fun initLayout(savedInstanceState: Bundle?)
}