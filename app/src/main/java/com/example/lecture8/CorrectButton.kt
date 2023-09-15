package com.example.lecture8

import android.content.Context
import android.util.AttributeSet

class CorrectButton : androidx.appcompat.widget.AppCompatButton, BaseViewModel.EnableView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attrs,
        defStyleAttrs
    )

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }

}
