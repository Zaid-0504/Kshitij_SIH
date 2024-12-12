package com.example.kshitijsih

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

class CustomScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // Prevent intercepting touch events to allow ViewPager2 gestures
        return false
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        // Handle the scroll view's own touch events
        return super.onTouchEvent(ev)
    }
}