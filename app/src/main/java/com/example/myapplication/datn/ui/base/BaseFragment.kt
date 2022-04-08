package com.example.myapplication.datn.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out T : ViewBinding> : Fragment() {

    private var _binding: T? = null

    protected val binding: T
        get() = _binding ?: throw  IllegalStateException(
            "binding is only valid between onCreateView and onDestroyView"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    /**
     * Call in [Fragment.onCreateView] to get the rootView of [binding]
     */
    abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T?


    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerLiveData()
        initDataSaveArgs()
        initData(arguments)
        initView()
        initAction()
    }

    open fun initView() {}

    open fun initAction() {}

    open fun initData(arguments: Bundle?) {}

    open fun observerLiveData() {}

    open fun initDataSaveArgs(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

