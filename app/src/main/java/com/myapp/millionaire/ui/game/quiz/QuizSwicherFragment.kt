package com.myapp.millionaire.ui.game.quiz

import android.transition.TransitionInflater
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentQuizSwicherBinding
import com.myapp.millionaire.ui.base.BaseFragment
import com.myapp.millionaire.ui.game.winnerpage.WinnerPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizSwicherFragment : BaseFragment<QuizViewModel, FragmentQuizSwicherBinding>() {
    override var useSharedViewModel: Boolean = true
    override fun getViewModelClass(): Class<QuizViewModel> = QuizViewModel::class.java
    override fun getDataBinding(): Class<FragmentQuizSwicherBinding> =
        FragmentQuizSwicherBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_quiz_swicher

    override fun setUpViews() {
        super.setUpViews()
        viewModel.showPage("Quiz")
        viewModel.setScore(0)
        viewModel.setAnser(0)
    }

    override fun observeData() {
        super.observeData()
        viewModel.showPage.observe(viewLifecycleOwner) {
            if (it == "WinnerPage") {
                showWinnerNextStep()
            } else if (it.equals("Winner", true)) {
                showWinnerPage()
            } else if (it.equals("Looser", true)){
                showLooserPage()
            }
            else {
                showQuiz()
            }
        }
    }

    private fun showLooserPage() {

        val fragmentManager: FragmentManager = requireFragmentManager()

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        val fragment = LooserFragment()
        fragmentManager.commit {
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            replace(R.id.dzoystickImplementation_placeholder, fragment)
            addToBackStack(null)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade_in)
    }

    private fun showWinnerPage() {

        val fragmentManager: FragmentManager = requireFragmentManager()

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        val fragment = WinnerFragment()
        fragmentManager.commit {
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            replace(R.id.dzoystickImplementation_placeholder, fragment)
            addToBackStack(null)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade_in)
    }

    private fun showWinnerNextStep() {

        val fragmentManager: FragmentManager = requireFragmentManager()

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        val fragment = WinnerPageFragment()
        fragmentManager.commit {
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            replace(R.id.dzoystickImplementation_placeholder, fragment)
            addToBackStack(null)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade_in)
    }

    private fun showQuiz() {

        val fragmentManager: FragmentManager = requireFragmentManager()

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        val fragment = QuizFragment()
        fragmentManager.commit {
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            replace(R.id.dzoystickImplementation_placeholder, fragment)
            addToBackStack(null)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade_in)
    }
}