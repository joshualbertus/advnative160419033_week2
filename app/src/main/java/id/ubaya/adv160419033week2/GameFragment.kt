package id.ubaya.adv160419033week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            textTurn.text = "$playerName's Turn"
        }

        var number1 = Random.nextInt(0,100)
        var number2 = Random.nextInt(0,100)
        textQuestion.text = "$number1 + $number2"

        var point = 0

        buttonSubmit.setOnClickListener{
            var playerAnswer = editAnswer.text.toString().toInt()
            var answer = checkAnswer(number1, number2, playerAnswer)
            if (answer) {
                point += 10
                editAnswer.setText("")
                number1 = Random.nextInt(0,100)
                number2 = Random.nextInt(0,100)
                textQuestion.text = "$number1 + $number2"
            }
            else {
                var action = GameFragmentDirections.actionResultFragment(point)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }


    fun checkAnswer(number1:Int, number2:Int, playerAnswer:Int):Boolean {
        val answer = number1 + number2
        return answer == playerAnswer
    }
}