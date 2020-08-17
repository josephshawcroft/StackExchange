package com.josephshawcroft.stackexchangeapp

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.josephshawcroft.stackexchangeapp.util.BackPressHandler

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding
            ?: throw RuntimeException("Don't call getBinding() without first calling setBinding()")

    fun setBinding(binding: T) {
        _binding = binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this is BackPressHandler) {
            activity?.let { activity ->
                activity.onBackPressedDispatcher.addCallback(this) {
                    onBackPressed(activity)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}