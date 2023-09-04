package com.myapp.millionaire.ui.game.quiz


import android.graphics.Color
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.google.android.material.card.MaterialCardView
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentQuizBinding
import com.myapp.millionaire.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizFragment : BaseFragment<QuizViewModel, FragmentQuizBinding>() {
    var correctAnswer = -1
    var nextAnswer = 0
    override var useSharedViewModel: Boolean = true
    override fun getViewModelClass(): Class<QuizViewModel> = QuizViewModel::class.java

    override fun getDataBinding(): Class<FragmentQuizBinding> = FragmentQuizBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_quiz

    override fun setUpViews() {
        super.setUpViews()
        //answer 1
        binding.answersTopLeftButton.setOnClickListener {
            if (correctAnswer == 0) {
                //continue
                corretAnswer(binding.answersTopLeftButton)
            } else {
                when(correctAnswer){
                    1->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersTopRightButton
                        )
                    }
                    2->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersBottomLeftButton
                        )
                    }
                    3->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersBottomRightButton
                        )
                    }

                }
            }
        }
        //answer 2
        binding.answersTopRightButton.setOnClickListener {
            if (correctAnswer == 1) {
                //continue
                corretAnswer(binding.answersTopRightButton)
            }else{
                when(correctAnswer){
                    0->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersTopLeftButton
                        )
                    }
                    2->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersBottomLeftButton
                        )
                    }
                    3->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersBottomRightButton
                        )
                    }

                }
            }
        }
        //answer 3
        binding.answersBottomLeftButton.setOnClickListener {
            if (correctAnswer == 2) {
                //continue
                corretAnswer(binding.answersBottomLeftButton)
            }else{
                when(correctAnswer){
                    1->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersTopRightButton
                        )
                    }
                    0->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersTopLeftButton
                        )
                    }
                    3->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersBottomRightButton
                        )
                    }

                }
            }
        }
        //answer 4
        binding.answersBottomRightButton.setOnClickListener {
            if (correctAnswer == 3) {
                //continue

                corretAnswer(binding.answersBottomRightButton)
            }else{
                when(correctAnswer){
                    1->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersTopRightButton
                        )
                    }
                    2->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersBottomLeftButton
                        )
                    }
                    0->{
                        wrongAnswer(
                            it as MaterialCardView, binding.answersTopLeftButton
                        )
                    }

                }
            }
        }
    }

    override fun observeData() {
        super.observeData()
        if (viewModel.currentAnswer.value != null) {
            nextAnswer = viewModel.currentAnswer.value!!
        } else {
            nextAnswer = 0
        }
        viewModel.setCurrentGame()

        viewModel.currentGame.observe(viewLifecycleOwner) { currentGame ->
            viewModel.getGames(requireContext(), currentGame)
            viewModel.games.observe(viewLifecycleOwner) {
                binding.questionTextView.text = it.questions?.get(nextAnswer)?.question
                binding.answers1.text = it.questions?.get(nextAnswer)?.content?.get(0)
                binding.answers2.text = it.questions?.get(nextAnswer)?.content?.get(1)
                binding.answers3.text = it.questions?.get(nextAnswer)?.content?.get(2)
                binding.answers4.text = it.questions?.get(nextAnswer)?.content?.get(3)
                correctAnswer = it.questions?.get(nextAnswer)?.correct!!
                binding.gameIndexTextView.text = currentGame.toString()

            }
        }

    }

    private fun wrongAnswer(view: MaterialCardView, correctView: MaterialCardView) {
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)

        anim.duration = 1000 //You can manage the blinking time with this parameter
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 2
        view.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                view.setCardBackgroundColor(Color.RED)

            }

            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                val anim: Animation = AlphaAnimation(0.0f, 1.0f)
                anim.duration = 3000 //You can manage the blinking time with this parameter
                anim.startOffset = 2
                anim.repeatMode = Animation.REVERSE
                anim.repeatCount = 2
                correctView.startAnimation(anim)
                anim.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        correctView.setCardBackgroundColor(Color.GREEN)
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                        viewModel.endGame(false)
                    }

                })

//                nextAnswer++
//                viewModel.setAnser(nextAnswer)
//                val currentGame = viewModel.currentGame.value
//                viewModel.getGames(requireContext(), currentGame!!)
//                viewModel.showPage("WinnerPage")
            }
        })
    }


    private fun corretAnswer(view: MaterialCardView) {
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)

        anim.duration = 1000 //You can manage the blinking time with this parameter
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 2
        view.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                view.setCardBackgroundColor(Color.GREEN)
            }

            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                nextAnswer++
                viewModel.setAnser(nextAnswer)
                val currentGame = viewModel.currentGame.value
                viewModel.getGames(requireContext(), currentGame!!)
                viewModel.showPage("WinnerPage")
            }
        })
    }
}