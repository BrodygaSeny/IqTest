package com.sentrin.iqtest.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.sentrin.iqtest.MyApplication.Companion.mainData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sentrin.iqtest.MainActivity
import com.sentrin.iqtest.MyApplication
import com.sentrin.iqtest.R
import com.sentrin.iqtest.data.getData
import com.sentrin.iqtest.data.updateDarkTheme
import com.sentrin.iqtest.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = HomeFragmentArgs.fromBundle(requireArguments())


        val viewModelFactory = HomeViewModelFactory(this.requireContext(), args.sentData)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        binding.viewmodel = viewModel


        viewModel.goToQuest.observe(this, Observer {
            if (it) {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavHomeToNavQuestion(
                        viewModel.data.value!!
                    )
                )
                viewModel.goToQuest.value = false
            }
        })

        //switch theme
        switchTheme.setOnCheckedChangeListener(
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                viewModel.darkTheme.value = isChecked
                scope.launch {
                    updateDarkTheme(isChecked)
                }

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

        })



        // if first entry
        viewModel.data.observe(this, Observer {
            // if first entry:
            if (it == null) {
                findNavController().navigate(HomeFragmentDirections.actionNavHomeToFirstStartFragment2())
            } else {
                binding.homeTitleText.text =
                    String.format(resources.getString(R.string.fragment_title), it.name);
                binding.homeUserIq.text = resources.getString(R.string.scoreIq, it.ScoreIq)
            }
        })
    }






    override fun onDestroy() {
        Log.d("debug1", "onDestroy home")
        super.onDestroy()
    }

    override fun onStop() {
        Log.d("debug1", "onStop home")
        super.onStop()
    }

    override fun onPause() {
        Log.d("debug1", "onPause home")
        super.onPause()
    }
}