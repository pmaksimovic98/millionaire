package com.myapp.millionaire.ui.game.quiz

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.myapp.millionaire.model.Game
import com.myapp.millionaire.model.Root
import com.myapp.millionaire.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel
@Inject constructor() : BaseViewModel() {
    val currentGame = MutableLiveData<Int>()
    val currentAnswer = MutableLiveData<Int>()
    val games = MutableLiveData<Game>()
    val score = MutableLiveData<Int>()
    val showPage = MutableLiveData<String>()
    fun showPage(page: String){
        showPage.value = page
    }
    fun setCurrentGame(){
        val randomNumber = (0..4).random()
        currentGame.value = randomNumber;
//        score.value=0
    }
    fun setAnser(answer: Int){
        currentAnswer.value =answer
        println("Answer = "+ currentAnswer.value)
    }

    fun getGames(context: Context, gameId: Int){

        viewModelScope.launch {
            val jsonData =
                context.applicationContext.resources.openRawResource(
                context.applicationContext.resources.getIdentifier("questions", "raw", context.applicationContext.packageName)
                ).bufferedReader().use {
                    it.readText()
                }
            val gson = Gson()
            var root = gson.fromJson<Root>(jsonData, Root::class.java)
            if(gameId >= root.games?.size!!) return@launch endGame(true)
            games.value = root.games!!.get(gameId)
        }
    }
    fun setScore(value: Int){
        viewModelScope.launch {
            score.value = value
            println("Score value: $value")
        }


    }
    fun endGame(winner: Boolean){
        if(winner){
            showPage("Winner")
        }else{
            showPage("Looser")
        }
    }

}