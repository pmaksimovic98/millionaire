package com.myapp.millionaire.ui.game.settings

import androidx.navigation.fragment.findNavController
import com.myapp.millionaire.R
import com.myapp.millionaire.databinding.FragmentSettingsBinding
import com.myapp.millionaire.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {
    override fun getViewModelClass(): Class<SettingsViewModel> = SettingsViewModel::class.java

    override fun getDataBinding(): Class<FragmentSettingsBinding> =
        FragmentSettingsBinding::class.java

    override fun getContentView(): Int = R.layout.fragment_settings
    override fun setUpViews() {
        super.setUpViews()
        binding.settingsLevelBar.setLabelFormatter {
            when (it.toInt()) {
                0 ->
                    return@setLabelFormatter "Beginner"
                30 ->
                    return@setLabelFormatter "Intermediate"
                60 ->
                    return@setLabelFormatter "Advanced"
                else ->
                    return@setLabelFormatter "null"

            }
        }

        binding.settingsLevelBar.addOnChangeListener { slider, value, fromUser ->
            when (value.toInt()) {
                0 -> {                    //beginner
                }
                30 -> {                    //intermediate
                }
                60 -> {                    //Advanced
                }
                else -> {                    //something wrong set on begginer
                }
            }
        }
    }

    override fun observeData() {
        super.observeData()
    }

    override fun onBackKeyPress() {
        super.onBackKeyPress()
        findNavController().navigate(R.id.action_fragmentSettings_to_fragmentGameMenu)
    }
}