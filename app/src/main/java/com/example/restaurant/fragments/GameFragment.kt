package com.example.restaurant.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.restaurant.R
import com.example.restaurant.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    data class Question(
        val text: String,
        val answers: List<String>)

    // the fist answer is the correct one
    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "What dish is from cochabamba?",
            answers = listOf("Majao", "Saice", "Silpancho", "Kawi")),
        Question(text = "Chicharron is made of ",
            answers = listOf("Cow", "Pig", "Cat", "Dog")),
        Question(text = "Which of these words is used in Cochabamba",
            answers = listOf("LLajua", "Chile", "Sauce", "BBQ Sauce"))
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)

        randomizeQuestions()
        binding.game = this
        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            if (checkedId != -1) {
                var anserIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> anserIndex = 1
                    R.id.thirdAnswerRadioButton -> anserIndex = 2
                    R.id.fourthAnswerRadioButton -> anserIndex = 3
                }

                if (answers[anserIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numQuestions) {
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }
                } else {
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment2)
                }
            }
        }
        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
    }

}