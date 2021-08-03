package com.beepnbuy.seller.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentSendCashBinding

/**
 * Created by Mayur Solanki on 29/07/21, 4:51 pm.
 */
class SendCashFragment : Fragment(R.layout.fragment_send_cash){

    private val args : SendCashFragmentArgs by navArgs()
    lateinit var fragmentSendCashBinding: FragmentSendCashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSendCashBinding = FragmentSendCashBinding.inflate(inflater,container,false)

        return fragmentSendCashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  val receiverName = arguments?.getString("name")
        val receiverName = args.receiverName



        fragmentSendCashBinding.tvReceiver.text = "Send cash to $receiverName"
//        fragmentSendCashBinding.etAmount.setText(amount.toString())

        fragmentSendCashBinding.btnSend.setOnClickListener {
            val amountEntered = fragmentSendCashBinding.etAmount.text.toString().trim()
            val action =  SendCashFragmentDirections.actionSendCashFragmentToConfirmDialogFragment(receiverName = receiverName,amount = amountEntered.toLong())
            findNavController().navigate(action)
        }

        fragmentSendCashBinding.btnDone.setOnClickListener {
            val action = SendCashFragmentDirections.actionSendCashFragmentToHomeFragment()
            findNavController().navigate(action)

        }

    }

}