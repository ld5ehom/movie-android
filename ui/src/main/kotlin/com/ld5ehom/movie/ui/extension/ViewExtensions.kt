/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ld5ehom.movie.ui.extension

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.doOnApplyWindowInsets(f: (View, WindowInsetsCompat, ViewPaddingState) -> Unit) {
    // Create a snapshot of the view's padding state
    val paddingState = createStateForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        f(v, insets, paddingState)
        insets
    }
    requestApplyInsetsWhenAttached()
}

/**
 * Call [View.requestApplyInsets] in a safe away. If we're attached it calls it straight-away.
 * If not it sets an [View.OnAttachStateChangeListener] and waits to be attached before calling
 * [View.requestApplyInsets].
 */
fun View.requestApplyInsetsWhenAttached() = doOnAttach {
    it.requestApplyInsets()
}

fun View.doOnAttach(f: (View) -> Unit) {
    if (isAttachedToWindow) {
        f(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                f(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

private fun createStateForView(view: View) = ViewPaddingState(
    view.paddingLeft,
    view.paddingTop,
    view.paddingRight,
    view.paddingBottom,
    view.paddingStart,
    view.paddingEnd
)

data class ViewPaddingState(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val start: Int,
    val end: Int
)
