package com.sentrin.iqtest.ui.endGame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sentrin.iqtest.R
import com.sentrin.iqtest.databinding.FragmentEndGameBinding
import com.sentrin.iqtest.databinding.FragmentQuestionBinding
import com.sentrin.iqtest.ui.question.QuestionFragmentArgs
import com.sentrin.iqtest.ui.question.QuestionViewModel
import com.sentrin.iqtest.ui.question.QuestionViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EndGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EndGameFragment : Fragment() {
    private lateinit var viewModel: EndGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEndGameBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val args = EndGameFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = EndGameViewModelFactory(this.requireContext(), args.sentData)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EndGameViewModel::class.java)

        binding.viewmodel = viewModel


        //string IQ
        binding.endGameUserIq.text = resources.getString(R.string.scoreIq, viewModel.sentData.ScoreIq)

        viewModel.goToQuestAgain.observe(this, Observer {
            if (it){
                findNavController().navigate(EndGameFragmentDirections.actionEndGameFragmentToNavQuestion(viewModel.sentData))
                viewModel.goToQuestAgain.value = false
            }
        })

        viewModel.goToHome.observe(this, Observer {
            if (it){
                findNavController().popBackStack()
                viewModel.goToHome.value = false
                Log.d("debug1","here back")
            }
        })


        return binding.root
    }
}