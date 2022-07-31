package com.example.core.navigation

interface NavigationProvider {
    fun launch(navCommand: NavCommand)
}