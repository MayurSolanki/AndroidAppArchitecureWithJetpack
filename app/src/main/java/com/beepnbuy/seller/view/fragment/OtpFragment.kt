package com.beepnbuy.seller.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentHomeBinding
import com.beepnbuy.seller.databinding.FragmentLoginBinding
import com.beepnbuy.seller.databinding.FragmentOtpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OtpFragment : BaseFragment(R.layout.fragment_otp){

    private lateinit var otpBinding: FragmentOtpBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        otpBinding = FragmentOtpBinding.inflate(inflater,container,false)
        return otpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val navController = findNavController()

        otpBinding.btnOtpVerified.setOnClickListener {
            val  action = OtpFragmentDirections.actionOtpFragmentToLoginNowDialogFragment()
            findNavController().navigate(action)
        }




    }
}
