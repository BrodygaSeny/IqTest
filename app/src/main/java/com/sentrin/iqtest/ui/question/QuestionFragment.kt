package com.sentrin.iqtest.ui.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sentrin.iqtest.MyApplication
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.R
import com.sentrin.iqtest.databinding.FragmentQuestionBinding
import kotlinx.android.synthetic.main.fragment_question.view.*
import kotlinx.android.synthetic.main.toast.*
import kotlinx.android.synthetic.main.toast.view.*
import kotlinx.coroutines.*

class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    private var randomRightRadio = 0
    private lateinit var rightRadioButton:RadioButton
    private lateinit var othersRadioButton:Collection<RadioButton>

    private val job = Job()
    private val scope = CoroutineScope(job+Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuestionBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val args = QuestionFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = QuestionViewModelFactory(this.requireContext(),args.sentData)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuestionViewModel::class.java)

        binding.viewmodel = viewModel

        // set randomv question
        viewModel.question?.run {
            randomRightRadio = (1..4).random()

            val radioButtonMap = mapOf(
                1 to binding.radioButton1,
                2 to binding.radioButton2,
                3 to binding.radioButton3,
                4 to binding.radioButton4,
            )

            rightRadioButton = radioButtonMap[randomRightRadio]!!
            val othersRadioButtonTemporary = radioButtonMap.filter { (index, radioButton) ->
                radioButton != rightRadioButton
            }
            othersRadioButton = othersRadioButtonTemporary.values

            rightRadioButton!!.text = this.rightAnswer
            for ((index, radioButton) in othersRadioButton.withIndex()) {
                radioButton.text = this.otherAnswers[index]
            }
        }


        viewModel.navigateOnAnswer.observe(this, Observer {
            if (it) {
            when (binding.questionRadioGroup.checkedRadioButtonId){
                -1 -> Snackbar.make(binding.root, resources.getString(R.string.snackCheckAnswer),
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                rightRadioButton.id -> {
                    findNavController().navigate(
                        QuestionFragmentDirections
                            .actionNavQuestionToAnswerFragment(true, viewModel.question!!, viewModel.sentData)
                    )
                }
                else ->
                    findNavController().navigate(QuestionFragmentDirections
                    .actionNavQuestionToAnswerFragment(false, viewModel.question!!, viewModel.sentData))
            }

            viewModel.navigateOnAnswer.value = false
            }
        })

        viewModel.navigateOnEndGame.observe(this, Observer {
            if (it) {
                findNavController().navigate(QuestionFragmentDirections.actionNavQuestionToEndGameFragment(viewModel.sentData))
                viewModel.navigateOnEndGame.value = false
            }
        })


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.sentData.listAnsweredQuestions.size == 2)
            mainData.viewModelMainActivity?.showPromptThinkAbout?.value = true
    }





}