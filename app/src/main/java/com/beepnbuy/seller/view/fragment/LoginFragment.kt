package com.beepnbuy.seller.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentHomeBinding
import com.beepnbuy.seller.databinding.FragmentLoginBinding
import com.beepnbuy.seller.view.activity.AuthActivity
import com.beepnbuy.seller.view.activity.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment(R.layout.fragment_login){

    private lateinit var loginBinding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(inflater,container,false)



        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val navController = findNavController()

        loginBinding.btnLogin.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }

        loginBinding.btnForgotPass.setOnClickListener {
            val action =  LoginFragmentDirections.actionLoginFragmentToOtpFragment()
            findNavController().navigate(action)

        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)



        }





    }
}
