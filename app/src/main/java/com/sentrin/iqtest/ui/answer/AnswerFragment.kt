package com.sentrin.iqtest.ui.answer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.R
import com.sentrin.iqtest.data.Question
import com.sentrin.iqtest.databinding.FragmentAnswerBinding
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_answer.*
import kotlinx.android.synthetic.main.toast.*
import kotlinx.android.synthetic.main.toast.view.*
import kotlinx.coroutines.*


class AnswerFragment : Fragment() {
    lateinit var binding: FragmentAnswerBinding
    lateinit var viewModel:AnswerViewModel

    val job = Job()
    val scope =  CoroutineScope(Job() + Dispatchers.Main )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnswerBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val args = AnswerFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = AnswerViewModelFactory(this.requireContext(), args.question, args.isAnswerRight, args.sentData)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AnswerViewModel::class.java)

        binding.viewmodel = viewModel


        //view options //
        //fills strings
        binding.iqChangeText.apply {
            if (args.isAnswerRight) {
                text = "+ ${mainData.plusScore} IQ"
                setTextColor(resources.getColor(R.color.greenText))
            } else {
                text = "- ${mainData.minusScore} IQ"
                setTextColor(resources.getColor(R.color.redText))
            }
        }

        binding.answerUserIq.text = resources.getString(R.string.scoreIq, viewModel.sentData.ScoreIq)

        if (args.isAnswerRight) {
            binding.answerRightAnswer.visibility = View.GONE
            binding.answerRightAnswer.height = 0
        }
        else
            binding.answerRightAnswer.text = resources.getString(R.string.answerRightAnswer, viewModel.question.rightAnswer)

        // hide view if link of resources is null
        if (args.question.descriptionResource.trim() == "") {
            binding.descriptionResource.visibility = View.INVISIBLE
            binding.descriptionResource.height = 0
        }



        //actions//





        //navigations//
        viewModel.onBackBool.observe(this, Observer {
            if (it) {
                findNavController().navigate(AnswerFragmentDirections.actionAnswerFragmentToNavHome(viewModel.sentData))
                viewModel.onBackBool.value = false
            }
        })

        viewModel.onNextBool.observe(this, Observer {
            if (it) {
                findNavController().navigate(AnswerFragmentDirections.actionAnswerFragmentToNavQuestion(viewModel.sentData))
                viewModel.onNextBool.value = false
            }
        })

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //show prompt_stairs
        if (viewModel.sentData.listAnsweredQuestions.size == 1)
            mainData.viewModelMainActivity?.showPromptStairs?.value = true

        //animation//
        val animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.change_iq)
        iqChangeText.startAnimation(animation)

    }




}