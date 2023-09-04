package com.myapp.millionaire.ui.recycle_view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.millionaire.R
import com.myapp.millionaire.model.WinnAmmount
import com.myapp.millionaire.ui.game.quiz.QuizViewModel


class WinnerScoreAdapter(
    val winns: List<WinnAmmount>,
    val animOnId: Int,
    val viewModel: QuizViewModel
) : RecyclerView.Adapter<WinnerScoreAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.recycle_view_winner_text_view_win)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_winner_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = winns[position].ammount.toString()
        run lit@{

            winns.forEach {
                if (it.id == animOnId) {
                    viewModel.setScore(it.ammount)

                    val anim: Animation = AlphaAnimation(0.0f, 1.0f)

                    anim.duration = 1000 //You can manage the blinking time with this parameter

                    anim.startOffset = 20
                    anim.repeatMode = Animation.REVERSE
                    anim.repeatCount = 6
                    if (holder.textView.text.contentEquals(it.ammount.toString(), true)) {
                        holder.textView.startAnimation(anim)
                        holder.textView.setBackgroundColor(Color.GREEN)
                    }
                    anim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(arg0: Animation) {}
                        override fun onAnimationRepeat(arg0: Animation) {}
                        override fun onAnimationEnd(arg0: Animation) {
                            if(it.id == 14) {
                                //winner
//
                                viewModel.endGame(true)
                            }else{
                                viewModel.showPage("Quiz")

                            }
                        }
                    })
                    return@lit
                }


            }
        }
        holder.textView.text = "$" + winns[position].ammount.toString()

    }

    override fun getItemCount(): Int = winns.size
}