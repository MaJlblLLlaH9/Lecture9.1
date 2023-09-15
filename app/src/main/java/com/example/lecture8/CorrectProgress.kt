package com.example.lecture8

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

class CorrectProgress : ProgressBar, BaseViewModel.ShowView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attrs,
        defStyleAttrs
    )

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
}