package com.myapp.millionaire.ui.game.firstpage


import androidx.navigation.fragment.findNavController
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentStartGameBinding
import com.myapp.millionaire.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartGameFragment: BaseFragment<StartGameViewModel, FragmentStartGameBinding>() {

    override fun getViewModelClass(): Class<StartGameViewModel> = StartGameViewModel::class.java

    override fun getDataBinding(): Class<FragmentStartGameBinding> = FragmentStartGameBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_start_game

    override fun observeData() {
        super.observeData()
        viewModel.startProgressBar()
        //observe progressbar mutable value
        viewModel.progress.observe(viewLifecycleOwner) {
            if(it != null){
                binding.startGameProgressBar.progress = it!!

            }
        }
        //once progressbar has loaded, move to next fragment
        viewModel.finished.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_fragmentStartGame_to_fragmentGameMenu)

        }
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.startGameProgressBar.progress = 0

    }





}