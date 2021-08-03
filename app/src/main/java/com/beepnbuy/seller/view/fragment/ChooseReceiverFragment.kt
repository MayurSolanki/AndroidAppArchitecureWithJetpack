package com.beepnbuy.seller.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentChooseReceiverBinding

/**
 * Created by Mayur Solanki on 29/07/21, 4:51 pm.
 */
class ChooseReceiverFragment : BaseFragment(R.layout.fragment_choose_receiver){

    private lateinit var  fragmentChooseReceiverBinding: FragmentChooseReceiverBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentChooseReceiverBinding = FragmentChooseReceiverBinding.inflate(inflater,container,false)

        return fragmentChooseReceiverBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentChooseReceiverBinding.btnNext.setOnClickListener {

          val receiverName =   fragmentChooseReceiverBinding.etReceiverName.text.toString()
            val args = Bundle()
            args.putString("name",receiverName)

           // findNavController().navigate(R.id.sendCashFragment, args)

            val action =  ChooseReceiverFragmentDirections.actionChooseReceiverFragmentToSendCashFragment(receiverName, 300)

            findNavController().navigate(action)

        }
        fragmentChooseReceiverBinding.btnCancel.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,true)
        }

    }
}