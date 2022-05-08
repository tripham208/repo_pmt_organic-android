package com.example.myapplication.datn.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.myapplication.datn.R

class InternetDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.need_internet))
            .setIcon(R.drawable.logo)
            .setTitle(getString(R.string.notif))
            .setPositiveButton(getString(R.string.okk)) { _, _ ->
                activity?.finish()
            }
            .create()

    companion object {
        const val TAG = "InternetDialogFragment"
    }
}