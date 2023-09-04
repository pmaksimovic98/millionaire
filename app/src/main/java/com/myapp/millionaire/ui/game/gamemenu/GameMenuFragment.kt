package com.myapp.millionaire.ui.game.gamemenu

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentGameMenuBinding
import com.myapp.millionaire.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameMenuFragment : BaseFragment<GameMenuViewModel, FragmentGameMenuBinding>() {
    override fun getViewModelClass(): Class<GameMenuViewModel> = GameMenuViewModel::class.java

    override fun getDataBinding(): Class<FragmentGameMenuBinding> =
        FragmentGameMenuBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_game_menu

    override fun setUpViews() {
        super.setUpViews()
        //start game
        binding.mainMenu.findViewById<MaterialCardView>(R.id.top_left_button).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentGameMenu_to_fragmentQuizSwicher)

        }
        //settingsw
        binding.mainMenu.findViewById<MaterialCardView>(R.id.top_right_button).setOnClickListener{
            findNavController().navigate(R.id.action_fragmentGameMenu_to_fragmentSettings)
        }
        //multy player
        binding.mainMenu.findViewById<MaterialCardView>(R.id.bottom_left_button).setOnClickListener {
            Toast.makeText(requireContext(), "In progress", Toast.LENGTH_LONG).show()
        }
        //quit
        binding.mainMenu.findViewById<MaterialCardView>(R.id.bottom_right_button).setOnClickListener{
            requireActivity().finishAndRemoveTask();
        }

    }

    override fun observeData() {
        super.observeData()
    }

}