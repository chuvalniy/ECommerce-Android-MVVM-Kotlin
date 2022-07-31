package com.example.ecommercialapplication.splash

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.navigation.NavCommand
import com.example.core.navigation.NavCommands
import com.example.core.navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.ecommercialapplication.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigate(
            NavCommand(
                target = NavCommands.DeepLink(
                    url = Uri.parse("myApp://featureMain"),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)
}