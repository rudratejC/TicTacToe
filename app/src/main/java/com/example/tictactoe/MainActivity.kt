package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER=true
    var count:Int=0
    var st= Array(size = 3){IntArray(3)}
    lateinit var board :Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
                arrayOf(btn1,btn2,btn3),
                arrayOf(btn4,btn5,btn6),
                arrayOf(btn7,btn8,btn9)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initStatus()

        restartBtn.setOnClickListener(){
            initStatus()
            PLAYER=true
            count=0
        }
    }

    fun initStatus(){
        for(i in 0..2){
            for(j in 0..2){
                st[i][j]=-1

            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
        msg.setText("Player X turn")
    }

    override fun onClick(v: View) {
            when(v.id){
                R.id.btn1->{
                    updateSt(row=0,col=0,player=PLAYER)
                }
                R.id.btn2->{
                    updateSt(row=0,col=1,player=PLAYER)
                }
                R.id.btn3->{
                    updateSt(row=0,col=2,player=PLAYER)
                }
                R.id.btn4->{
                    updateSt(row=1,col=0,player=PLAYER)
                }
                R.id.btn5->{
                    updateSt(row=1,col=1,player=PLAYER)
                }
                R.id.btn6->{
                    updateSt(row=1,col=2,player=PLAYER)
                }
                R.id.btn7->{
                    updateSt(row=2,col=0,player=PLAYER)
                }
                R.id.btn8->{
                    updateSt(row=2,col=1,player=PLAYER)
                }
                R.id.btn9->{
                    updateSt(row=2,col=2,player=PLAYER)
                }
            }
            count++
            PLAYER=!PLAYER
            if(PLAYER){
                updateTextV("Player X Turn")
            }
            else{
                updateTextV("Player O Turn")
            }

            if(count==9){
                updateTextV("Game Draw")
            }
            checkWin()

    }

    fun updateTextV(str:String){
        msg.setText(str)
    }

    fun updateSt(row: Int, col: Int ,player: Boolean) {

        var res=if(player) 1 else 0
        var str=if(player) "X" else "O"
        st[row][col]=res
        board[row][col].setText(str)
        board[row][col].isEnabled=false
    }

    fun checkWin(){
        //for Horizontal winner
        for(i in 0..2){
            if(st[i][0]==st[i][1] && st[i][1]==st[i][2]){
                if(st[i][0]==1){
                    updateWinner("Player X is Winner")
                    break
                }
                else if(st[i][0]==0){
                    updateWinner("Player O is Winner")
                    break
                }
            }
        }
        //for vertical winner
        for(i in 0..2){
            if(st[0][i]==st[1][i] && st[0][i]==st[2][i]){
                if(st[0][i]==1){
                    updateWinner("Player X is Winner")
                    break
                }
                else if(st[0][i]==0){
                    updateWinner("Player O is Winner")
                    break
                }
            }
        }
        //for diagonal winner
        if(st[0][0]==st[1][1] && st[0][0]==st[2][2]){
            if(st[0][0]==1){
                updateWinner("Player X is Winner")
            }
            else if(st[0][0]==0){
                updateWinner("Player O is Winner")
            }
        }

        if(st[0][2]==st[1][1] && st[1][1]==st[2][0]){
            if(st[0][2]==1){
                updateWinner("Player X is Winner")
            }
            else if(st[0][2]==0){
                updateWinner("Player O is Winner")
            }
        }
    }

    fun updateWinner(str:String){
        msg.setText(str)
        disableBtns()
    }
    fun disableBtns(){
        for(i in board){
            for(button in i){
               button.isEnabled=false
            }
        }
    }
}