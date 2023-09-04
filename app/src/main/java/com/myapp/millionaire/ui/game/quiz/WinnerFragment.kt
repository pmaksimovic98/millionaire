package com.myapp.millionaire.ui.game.quiz

import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentWinnerBinding
import com.myapp.millionaire.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WinnerFragment: BaseFragment<QuizViewModel, FragmentWinnerBinding>() {
    override var useSharedViewModel: Boolean = true
    override fun getViewModelClass(): Class<QuizViewModel> = QuizViewModel::class.java
    override fun getDataBinding(): Class<FragmentWinnerBinding> = FragmentWinnerBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_winner
}