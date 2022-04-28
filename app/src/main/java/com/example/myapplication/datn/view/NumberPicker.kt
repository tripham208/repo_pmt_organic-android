package com.example.myapplication.datn.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.myapplication.datn.databinding.ViewNumberpickerHorizontalBinding

class NumberPicker : LinearLayout {
    private var _binding: ViewNumberpickerHorizontalBinding? = null

    var changeNumber: ((Int) -> Unit)? = null


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        _binding = ViewNumberpickerHorizontalBinding.inflate(LayoutInflater.from(context))
        addView(
            _binding?.root ?: throw NullPointerException("Expression '_binding' must not be null")
        )
        initView()
        initAction()
    }

    private fun initAction() {
        _binding?.apply {
            btnLessNumberPicker.setOnClickListener {
                val number = getNumber()
                if (number != MIN) {
                    edValueNumberPicker.setText((number - 1).toString())
                }
                changeNumber?.invoke(getNumber())
            }
            btnMoreNumberPicker.setOnClickListener {
                val number = getNumber()
                if (number != MAX) {
                    edValueNumberPicker.setText((number + 1).toString())
                }
                changeNumber?.invoke(getNumber())
            }
        }
    }

    fun getNumber() = _binding?.edValueNumberPicker?.text.toString().toInt()
    fun setNumber(number: Int) {
        _binding?.edValueNumberPicker?.setText(number.toString())
    }

    private fun initView() {
        _binding?.apply {
            edValueNumberPicker.inputType = InputType.TYPE_NULL;
            edValueNumberPicker.setText(DEFAULT.toString())
        }

    }

    companion object {
        const val MAX = 99
        const val MIN = 1
        const val DEFAULT = 1
    }
}