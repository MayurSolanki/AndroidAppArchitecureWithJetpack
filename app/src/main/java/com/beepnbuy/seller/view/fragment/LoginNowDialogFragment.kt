package com.beepnbuy.seller.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentLoginNowDialogBinding


class LoginNowDialogFragment : DialogFragment(){

    private lateinit var loginNowDialogBinding: FragmentLoginNowDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginNowDialogBinding = FragmentLoginNowDialogBinding.inflate(inflater,container,false)

        return loginNowDialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginNowDialogBinding.btnLoginNow.setOnClickListener {
            val action = LoginNowDialogFragmentDirections.actionLoginNowDialogFragmentToLoginFragment()
           findNavController().navigate(action)
        }


    }
}
