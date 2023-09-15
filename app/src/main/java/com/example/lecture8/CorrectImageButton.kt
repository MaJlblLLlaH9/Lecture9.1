package com.example.lecture8

import android.content.Context
import android.util.AttributeSet

class CorrectImageButton : androidx.appcompat.widget.AppCompatImageButton, BaseViewModel.ShowImage {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attrs,
        defStyleAttrs
    )

    override fun show(id: Int) {
        setImageResource(id)
    }
}