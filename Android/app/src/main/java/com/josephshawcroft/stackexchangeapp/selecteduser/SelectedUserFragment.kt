package com.josephshawcroft.stackexchangeapp.selecteduser

import android.os.Bundle
import com.josephshawcroft.stackexchangeapp.BaseFragment
import com.josephshawcroft.stackexchangeapp.databinding.FragmentSelectedUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedUserFragment: BaseFragment<FragmentSelectedUserBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo grab user by safe args
    }
}