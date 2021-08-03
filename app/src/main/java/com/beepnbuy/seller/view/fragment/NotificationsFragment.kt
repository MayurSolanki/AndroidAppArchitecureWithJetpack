package com.beepnbuy.seller.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.beepnbuy.seller.R
import com.beepnbuy.seller.databinding.FragmentHomeBinding
import com.beepnbuy.seller.databinding.FragmentNotificationsBinding


class NotificationsFragment : BaseFragment(R.layout.fragment_notifications){

    private lateinit var notificationsBinding: FragmentNotificationsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsBinding = FragmentNotificationsBinding.inflate(inflater,container,false)




        return notificationsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        notificationsBinding.lvNotifications.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,getNotifications())


    }

    private fun getNotifications():List<String>{
        val notifications = mutableListOf<String>()

        for(i in 1..20){
            notifications.add("Notification # $i")
        }
        return notifications
    }
}
