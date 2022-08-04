package com.example.feature_user_profile.presentation.profile_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.feature_user_profile.R
import com.example.feature_user_profile.databinding.FragmentUserProfileBinding


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToMyOrders.setOnClickListener {
            findNavController().navigate(R.id.action_user_profile_to_orders)
        }

        binding.btnGoToSettings.setOnClickListener {
            findNavController().navigate(R.id.action_user_profile_to_settings)
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserProfileBinding.inflate(inflater, container, false)
}