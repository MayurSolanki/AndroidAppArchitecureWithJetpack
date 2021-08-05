package com.beepnbuy.seller.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentHomeBinding
import com.beepnbuy.seller.view.activity.HomeActivity


class HomeFragment : BaseFragment(R.layout.fragment_home){

    private lateinit var homeBinding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)




        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val navController = findNavController()



        homeBinding.btnViewBalance.setOnClickListener {
            //navController.navigate(R.id.viewBalanceFragment) // by id
           // navController.navigate(R.id.action_homeFragment_to_viewBalanceFragment) // by action

            // by action
            val action = HomeFragmentDirections.actionHomeFragmentToViewBalanceFragment()
            navController.navigate(action)
        }

        homeBinding.btnTransactions.setOnClickListener {
           // navController.navigate(R.id.viewTransactionsFragment)// by id
           // navController.navigate(R.id.action_homeFragment_to_viewTransactionsFragment) // by action

            // by action
            val action = HomeFragmentDirections.actionHomeFragmentToViewTransactionsFragment()
            navController.navigate(action)
        }

        homeBinding.btnSendMoney.setOnClickListener {
           // navController.navigate(R.id.chooseReceiverFragment)// by id
           // navController.navigate(R.id.action_homeFragment_to_chooseReceiverFragment) // by action

            // by action
            val action = HomeFragmentDirections.actionHomeFragmentToChooseReceiverFragment()
            navController.navigate(action)
        }




    }
}
