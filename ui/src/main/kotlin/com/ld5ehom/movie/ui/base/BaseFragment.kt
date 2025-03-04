package com.ld5ehom.movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import com.ld5ehom.movie.presentation.base.BaseViewModel
import com.ld5ehom.movie.presentation.base.ViewEvent
import com.ld5ehom.movie.presentation.extension.observe
import com.ld5ehom.movie.ui.BR
import com.ld5ehom.movie.ui.extension.repeatOnStarted

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel<VE>, VE : ViewEvent>(
    @LayoutRes private val layoutResId: Int,
) : Fragment() {

    private var _binding: B? = null
    protected val binding: B
        get() = _binding ?: throw IllegalStateException("Fragment에서 Destroy 이후 binding에 접근하려고함")

    abstract val viewModel: VM

    abstract fun handleEvent(event: VE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
            setVariable(BR.view, this@BaseFragment)
        }
        observeEvent()
    }

    private fun observeEvent() = repeatOnStarted {
        viewModel.eventFlow
            .collect { handleEvent(it) }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

    protected infix fun <T> StateFlow<T?>.observe(action: (T) -> Unit) {
        observe(viewLifecycleOwner, action)
    }


}
