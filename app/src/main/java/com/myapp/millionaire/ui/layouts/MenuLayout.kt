package com.myapp.millionaire.ui.layouts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.children
import com.google.android.material.card.MaterialCardView
import com.myapp.millionaire.R

class MenuLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {


    init {

        LayoutInflater.from(context)
            .inflate(R.layout.layout_game_menu, this, true)

    }


}

