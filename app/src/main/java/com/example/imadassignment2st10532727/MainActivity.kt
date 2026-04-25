package com.example.imadassignment2st10532727

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private var iNum = 0
    private var score = 0

    private val qList = arrayOf(
        "A wooden spoon over a boiling pot prevents it from boiling over.",
        "You can use the inside of a banana peel to polish leather shoes.",
        "Putting your phone in rice will fix it after it gets wet.",
        "Using a straw to drink soda prevents tooth decay.",
        "You can charge your phone by rubbing it against your hair."
    )

    private val aList = arrayOf(true, true, false, true, false) 

    private val infoList = arrayOf(
        "The spoon breaks the surface tension of the bubbles.",
        "The potassium and oils in the peel shine the leather.",
        "Rice doesn't pull moisture out well and can leave dust inside.",
        "It keeps the sugar and acid away from most of your teeth.",
        "Static electricity isn't enough to charge a modern battery."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Pages
        val lay1 = findViewById<LinearLayout>(R.id.welcomeScreen)
        val lay2 = findViewById<LinearLayout>(R.id.questionScreen)
        val lay3 = findViewById<LinearLayout>(R.id.scoreScreen)
        val lay4 = findViewById<LinearLayout>(R.id.reviewScreen)

        //text views
        val txtQ = findViewById<TextView>(R.id.tvQuestion)
        val txtF = findViewById<TextView>(R.id.tvFeedback)
        val txtScore = findViewById<TextView>(R.id.tvTotalScore)
        val txtMsg = findViewById<TextView>(R.id.tvScoreFeedback)
        val txtReview = findViewById<TextView>(R.id.tvReviewList)

        //buttons
        val bStart = findViewById<Button>(R.id.btnStart)
        val bHack = findViewById<Button>(R.id.btnHack)
        val bMyth = findViewById<Button>(R.id.btnMyth)
        val bNext = findViewById<Button>(R.id.btnNext)
        val bReview = findViewById<Button>(R.id.btnReview)
        val bRestart = findViewById<Button>(R.id.btnRestart)
        val bBack = findViewById<Button>(R.id.btnBackToScore)

        //Start button logic
        bStart.setOnClickListener {
            lay1.visibility = View.GONE
            lay2.visibility = View.VISIBLE
            iNum = 0
            score = 0
            //show first question
            txtQ.text = qList[iNum]
            txtF.text = ""
            bNext.visibility = View.INVISIBLE
            bHack.isEnabled = true
            bMyth.isEnabled = true
        }

        //Hack button
        bHack.setOnClickListener {
            if (aList[iNum] == true) {
                score++
                txtF.text = "Correct! " + infoList[iNum]
            } else {
                txtF.text = "Wrong! That's just an urban myth.\n" + infoList[iNum]
            }
            bHack.isEnabled = false
            bMyth.isEnabled = false
            bNext.visibility = View.VISIBLE
        }

        //Myth button
        bMyth.setOnClickListener {
            if (aList[iNum] == false) {
                score++
                txtF.text = "Correct! " + infoList[iNum]
            } else {
                txtF.text = "Wrong! That's actually a real hack.\n" + infoList[iNum]
            }
            bHack.isEnabled = false
            bMyth.isEnabled = false
            bNext.visibility = View.VISIBLE
        }

        //Next button
        bNext.setOnClickListener {
            iNum++
            if (iNum < qList.size) {
                txtQ.text = qList[iNum]
                txtF.text = ""
                bNext.visibility = View.INVISIBLE
                bHack.isEnabled = true
                bMyth.isEnabled = true
            } else {
                lay2.visibility = View.GONE
                lay3.visibility = View.VISIBLE
                txtScore.text = "Final Score: " + score + " / " + qList.size
                
                if (score >= 4) {
                    txtMsg.text = "Great job! You're a Life Hack expert!"
                } else {
                    txtMsg.text = "Keep practicing! You'll learn the truth soon."
                }
            }
        }

        //Review button
        bReview.setOnClickListener {
            lay3.visibility = View.GONE
            lay4.visibility = View.VISIBLE
            
            var s = ""
            for (i in 0 until qList.size) {
                var type = ""
                if (aList[i]) type = "Hack" else type = "Myth"
                
                s = s + "Q" + (i + 1) + ": " + qList[i] + "\n"
                s = s + "Type: " + type + "\n"
                s = s + "Info: " + infoList[i] + "\n\n"
            }
            txtReview.text = s
        }

        //Restart button
        bRestart.setOnClickListener {
            lay3.visibility = View.GONE
            lay1.visibility = View.VISIBLE
        }

        //Back button
        bBack.setOnClickListener {
            lay4.visibility = View.GONE
            lay3.visibility = View.VISIBLE
        }
    }
}
