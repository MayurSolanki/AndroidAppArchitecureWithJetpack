package com.beepnbuy.seller.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.beepnbuy.seller.databinding.FragmentConfirmDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ConfirmDialogFragment : BottomSheetDialogFragment(){

    private val args : ConfirmDialogFragmentArgs by navArgs()
    private lateinit var fragmentConfirmDialogBinding: FragmentConfirmDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentConfirmDialogBinding = FragmentConfirmDialogBinding.inflate(inflater,container,false)

        return fragmentConfirmDialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receiverName = args.receiverName
        val amount = args.amount

        fragmentConfirmDialogBinding.tvMessage.text = "Do you want to send Rs $amount to $receiverName"

        fragmentConfirmDialogBinding.btnYes.setOnClickListener {
            Toast.makeText(requireContext(),"Sent ", Toast.LENGTH_LONG).show()
            dismiss()
        }
        fragmentConfirmDialogBinding.btnNo.setOnClickListener {
            dismiss()
        }

    }
}
