package com.beepnbuy.seller.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.MainNavGraphDirections
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentHomeBinding
import com.beepnbuy.seller.databinding.FragmentSettingBinding


class SettingsFragment : BaseFragment(R.layout.fragment_setting){

    private lateinit var  fragmentSettingBinding: FragmentSettingBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSettingBinding = FragmentSettingBinding.inflate(inflater,container,false)




        return fragmentSettingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       //  val navController = findNavController()
        fragmentSettingBinding.btnAboutApp.setOnClickListener {
            val action = MainNavGraphDirections.actionGlobalAboutAppFragment()
            findNavController().navigate(action)
        }



    }
}
