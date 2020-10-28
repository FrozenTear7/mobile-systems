package com.example.lab1

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    enum class Operation {
        ADD, SUB, MUL, DIV
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val n1EditText = findViewById<EditText>(R.id.n1EditText)
        val n2EditText = findViewById<EditText>(R.id.n2EditText)
        val resEditText = findViewById<EditText>(R.id.resEditText)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val addButton = findViewById<Button>(R.id.addButton)
        val subButton = findViewById<Button>(R.id.subButton)
        val mulButton = findViewById<Button>(R.id.mulButton)
        val divButton = findViewById<Button>(R.id.divButton)
        val piButton = findViewById<Button>(R.id.piButton)

        addButton.setOnClickListener {
            try {
                resEditText.setText(calculate(n1EditText.text.toString().toDouble(), n2EditText.text.toString().toDouble(), Operation.ADD))
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please provide correct values", Toast.LENGTH_SHORT).show()
            }
        }
        subButton.setOnClickListener {
            try {
                resEditText.setText(calculate(n1EditText.text.toString().toDouble(), n2EditText.text.toString().toDouble(), Operation.SUB))
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please provide correct values", Toast.LENGTH_SHORT).show()
            }
        }
        mulButton.setOnClickListener {
            try {
                resEditText.setText(calculate(n1EditText.text.toString().toDouble(), n2EditText.text.toString().toDouble(), Operation.MUL))
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please provide correct values", Toast.LENGTH_SHORT).show()
            }
        }
        divButton.setOnClickListener {
            try {
                resEditText.setText(calculate(n1EditText.text.toString().toDouble(), n2EditText.text.toString().toDouble(), Operation.DIV))
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please provide correct values", Toast.LENGTH_SHORT).show()
            }
        }

        class PiComputeTask : AsyncTask<Void, Int, Double>() {
            val interval = 10000000
            var circlePoints = 0
            var squarePoints = 0
            var pi = 0.0
            var progress = 0

            override fun doInBackground(vararg params: Void?): Double? {
                for (x in 0 until interval) {
                    val randX = Random.nextDouble(-1.0, 1.0)
                    val randY = Random.nextDouble(-1.0, 1.0)

                    val originDist = randX.pow(2) + randY.pow(2)

                    if (originDist <= 1)
                        circlePoints += 1

                    squarePoints += 1

                    pi = (4 * circlePoints / squarePoints).toDouble()

                    val newProgress = ((x.toDouble() / interval.toDouble()) * 100).toInt()
                    if(newProgress > progress) {
                        progress = newProgress
                        publishProgress(progress)
                    }
                }

                return pi
            }

            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate()

                Log.v(TAG, "progress = " + values[0]!!)

                progressBar.progress = values[0]!!
            }

            override fun onPostExecute(result: Double?) {
                resEditText.setText(result.toString())

                Log.v(TAG, "Done calculating pi")

                progressBar.progress = 100
            }
        }

        piButton.setOnClickListener {
            progressBar.progress = 0
            PiComputeTask().execute();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calculate(number1: Double, number2: Double, operation: Operation): String {
        return when (operation) {
            Operation.ADD -> (number1 + number2).toString()
            Operation.SUB -> (number1 - number2).toString()
            Operation.MUL -> (number1 * number2).toString()
            Operation.DIV -> (number1 / number2).toString()
        }
    }
}