package com.ld5ehom.movie.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import com.ld5ehom.movie.presentation.base.BaseViewModel
import com.ld5ehom.movie.presentation.base.ViewEvent
import com.ld5ehom.movie.presentation.extension.observe
import com.ld5ehom.movie.ui.BR
import com.ld5ehom.movie.ui.extension.repeatOnStarted

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel<VE>, VE : ViewEvent>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
    protected abstract val viewModel: VM

    abstract fun handleEvent(event: VE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId) ?: return
        binding {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, viewModel)
            setVariable(BR.view, this@BaseActivity)
        }
        observeEvent()
    }

    private fun observeEvent() = repeatOnStarted {
        viewModel.eventFlow
            .collect { handleEvent(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

    protected infix fun <T> StateFlow<T?>.observe(action: (T) -> Unit) {
        observe(this@BaseActivity, action)
    }

}
