package com.ivlieva.calculator.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ivlieva.calculator.R
import com.ivlieva.calculator.util.ReversePolishCalculator
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*

class CalculatorFragment : Fragment() {

    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        retainInstance = true
        val numberTextView = view.findViewById<TextView>(R.id.numberTextView)
        if (savedInstanceState != null) {
            val get = savedInstanceState.get("numberText")
            if (get != null) {
                numberTextView.text = get.toString()
            }
        }

        //числа
        view.num0Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num1Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num2Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num3Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num4Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num5Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num6Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num7Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num8Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.num9Button.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }
        view.dotButton.setOnClickListener {
            onClickNumButton((it as Button).text.toString(), numberTextView)
        }


        // арифметические операции
        view.plusButton.setOnClickListener {
            onClickSymbolButton((it as Button).text.toString(), numberTextView)
        }
        view.minusButton.setOnClickListener {
            onClickSymbolButton((it as Button).text.toString(), numberTextView)
        }
        view.multiplicationButton.setOnClickListener {
            onClickSymbolButton((it as Button).text.toString(), numberTextView)
        }
        view.divButton.setOnClickListener {
            onClickSymbolButton((it as Button).text.toString(), numberTextView)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            view.bracketRButton.setOnClickListener {
                onClickSymbolButton((it as Button).text.toString(), numberTextView)
            }
            view.bracketLButton.setOnClickListener {
                onClickSymbolButton((it as Button).text.toString(), numberTextView)
            }
        }

        view.acButton.setOnClickListener {
            onClickDeleteButton(numberTextView)
        }

        view.signChangeButton.setOnClickListener {
            onClickSignChangeButton(numberTextView)
        }

        view.calculateButton.setOnClickListener {
            onClickCalculateButton(numberTextView)
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("numberText", numberTextView.text.toString())
    }


    private fun onClickNumButton(num: String, numberTextView: TextView) {
        numberTextView.text = numberTextView.text.toString().plus(num)
    }

    private fun onClickSymbolButton(num: String, numberTextView: TextView) {
        numberTextView.text = numberTextView.text.toString().plus(" ")
        onClickNumButton(num, numberTextView)
        numberTextView.text = numberTextView.text.toString().plus(" ")
    }

    private fun onClickDeleteButton(numberTextView: TextView) {
        if (flag) {
            numberTextView.text = ""
        } else {
            val text = numberTextView.text.toString()
            if (text.last() == ' ') {
                numberTextView.text = numberTextView.text.dropLast(1)
            }
            numberTextView.text = numberTextView.text.dropLast(1)
            flag = true
        }
    }

    private fun onClickCalculateButton(numberTextView: TextView) {
        val reversePolishCalculator = ReversePolishCalculator()
        try {
            numberTextView.text = reversePolishCalculator.reversePolishNotation(
                reversePolishCalculator.getArr(numberTextView.text.toString())
            ).toString()
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка в примере", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickSignChangeButton(numberTextView: TextView) {
        if (numberTextView.text.first() == '-') {
            numberTextView.text = numberTextView.text.drop(1)
        } else if (!numberTextView.text.isBlank()) {
            numberTextView.text = "-".plus(numberTextView.text)
        }
    }
}
