package com.example.feature_user_profile.presentation.settings_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_user_profile.databinding.FragmentUserSettingsBinding

class UserSettingsFragment : BaseFragment<FragmentUserSettingsBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserSettingsBinding.inflate(inflater, container, false)
}