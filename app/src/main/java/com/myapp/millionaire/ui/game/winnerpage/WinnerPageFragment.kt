package com.myapp.millionaire.ui.game.winnerpage

import android.transition.TransitionInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentWinnerScoreBinding
import com.myapp.millionaire.model.WinnAmmount
import com.myapp.millionaire.ui.base.BaseFragment
import com.myapp.millionaire.ui.game.quiz.QuizViewModel
import com.myapp.millionaire.ui.recycle_view.WinnerScoreAdapter

class WinnerPageFragment: BaseFragment<QuizViewModel, FragmentWinnerScoreBinding>() {
    private val wins = listOf<WinnAmmount>(
        WinnAmmount(14, 1000000),
        WinnAmmount(13, 500000),
        WinnAmmount(12, 250000),
        WinnAmmount(11, 125000),
        WinnAmmount(10, 64000),
        WinnAmmount(9, 32000),
        WinnAmmount(8, 16000),
        WinnAmmount(7, 8000),
        WinnAmmount(6, 4000),
        WinnAmmount(5, 2000),
        WinnAmmount(4, 1000),
        WinnAmmount(3, 500),
        WinnAmmount(2, 300),
        WinnAmmount(1, 200),
        WinnAmmount(0, 0),
    )
    override var useSharedViewModel: Boolean = true

    override fun getViewModelClass(): Class<QuizViewModel> = QuizViewModel::class.java

    override fun getDataBinding(): Class<FragmentWinnerScoreBinding> = FragmentWinnerScoreBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_winner_score

    override fun setUpViews() {
        super.setUpViews()
    }

    override fun observeData() {
        super.observeData()
        viewModel.currentAnswer.observe(viewLifecycleOwner){
            binding.mainRecyclerView.adapter = WinnerScoreAdapter(wins, it, viewModel)
            binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            val inflater = TransitionInflater.from(requireContext())
            enterTransition = inflater.inflateTransition(R.transition.slide_in)
        }
    }
}