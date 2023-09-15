package com.example.lecture8

import android.content.Context
import android.util.AttributeSet

class CorrectTextView : androidx.appcompat.widget.AppCompatTextView, BaseViewModel.ShowText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attrs,
        defStyleAttrs
    )

    override fun show(text: String) {
        setText(text)
    }

}