
package com.myapp.millionaire.ui.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.library.baseAdapters.BR

import com.myapp.millionaire.ui.base.BaseViewModel


abstract class BaseFragment<ViewModel: BaseViewModel, DataBinding: ViewDataBinding>: Fragment() {
    open var useSharedViewModel: Boolean = false

    protected lateinit var viewModel: ViewModel
    protected lateinit var binding: DataBinding
    @NonNull
    protected abstract fun getViewModelClass(): Class<ViewModel>

    @NonNull
    protected abstract fun getDataBinding(): Class<DataBinding>

    @NonNull
    protected abstract fun getContentView(): Int

    open fun setUpViews(){}
    open fun observeData(){
        viewModel.backendError.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
    open fun onBackKeyPress(){}



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        init(inflater, container)
        setLifecycle(binding, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
        view.setFocusableInTouchMode(true)
        view.requestFocus()
        view.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_BACK && event!!.getAction() == KeyEvent.ACTION_UP )
                {
                    onBackKeyPress()
                    return true
                }
                return true
            }

        })
    }

    private fun setLifecycle(binding: DataBinding, viewModel: ViewModel) {
        binding.run {
            setVariable(BR._all, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun init(
        inflater: LayoutInflater,
        container: ViewGroup?
    ){
        binding = DataBindingUtil.inflate(
            inflater,
            getContentView(),
            container,
            false
        )
        viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity()).get(getViewModelClass())
        } else {
            ViewModelProvider(this).get(getViewModelClass())
        }

    }

}