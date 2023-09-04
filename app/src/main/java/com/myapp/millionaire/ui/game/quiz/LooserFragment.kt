package com.myapp.millionaire.ui.game.quiz

import androidx.navigation.fragment.findNavController
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentLooserBinding
import com.myapp.millionaire.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LooserFragment: BaseFragment<QuizViewModel, FragmentLooserBinding>() {
    override var useSharedViewModel: Boolean = true
    override fun getViewModelClass(): Class<QuizViewModel> = QuizViewModel::class.java

    override fun getDataBinding(): Class<FragmentLooserBinding> = FragmentLooserBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_looser
    override fun setUpViews() {
        super.setUpViews()
        binding.looserTextViewPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentQuizSwicher_to_fragmentGameMenu)

        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.score.observe(viewLifecycleOwner){
            val textRegex = resources.getString(R.string.game_finished_with_n)
            binding.looserTextViewEndGame.text = textRegex.replace("***", "$$it")


        }

    }
}