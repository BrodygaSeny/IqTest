package com.sentrin.iqtest.ui.firstStart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sentrin.iqtest.MyApplication
import com.sentrin.iqtest.R
import com.sentrin.iqtest.data.updateDarkTheme
import com.sentrin.iqtest.databinding.FragmentFirstStartBinding
import kotlinx.android.synthetic.main.fragment_first_start.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FirstStartFragment : Fragment() {
    lateinit var binding: FragmentFirstStartBinding
    lateinit var viewModel: FirstStartViewModel
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstStartBinding.inflate(inflater)
        binding.setLifecycleOwner(this)



        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = FirstStartViewModelFactory(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FirstStartViewModel::class.java)

        binding.viewmodel = viewModel

        // fill strings
        binding.firstStartUserStartIQ.text = resources.getString(R.string.scoreIq, MyApplication.mainData.startScore)

        viewModel.goToQuest.observe(this, Observer {
            if (it) {
                viewModel.saveInDatabase(binding.personNameEdit.text.toString())
                findNavController().navigate(FirstStartFragmentDirections
                    .actionFirstStartFragmentToNavQuestion(viewModel.sentData!!))
                viewModel.goToQuest.value = false
            }
        })

        //switch theme
        switchThemeStart.setOnCheckedChangeListener(
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



    }

}