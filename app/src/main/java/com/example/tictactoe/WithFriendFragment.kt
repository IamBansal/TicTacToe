package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tictactoe.databinding.FragmentWithFriendBinding
import java.util.*

class WithFriendFragment : Fragment() {

    private lateinit var binding: FragmentWithFriendBinding
    private lateinit var list: LinkedList<String>
    private var isFirst = true
    private lateinit var board: Array<Array<Char>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWithFriendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAgain()

        binding.one.setOnClickListener {
            onClick(it, 0, 0)
        }
        binding.two.setOnClickListener {
            onClick(it, 1, 0)
        }
        binding.three.setOnClickListener {
            onClick(it, 2, 0)
        }
        binding.four.setOnClickListener {
            onClick(it, 0, 1)
        }
        binding.five.setOnClickListener {
            onClick(it, 1, 1)
        }
        binding.six.setOnClickListener {
            onClick(it, 2, 1)
        }
        binding.seven.setOnClickListener {
            onClick(it, 0, 2)
        }
        binding.eight.setOnClickListener {
            onClick(it, 1, 2)
        }
        binding.nine.setOnClickListener {
            onClick(it, 2, 2)
        }

        binding.fabRestart.setOnClickListener {
            startAgain()
        }

    }

    private fun startAgain() {
        list = LinkedList<String>()
        isFirst = true
        board = arrayOf(
            arrayOf('-', '-', '-'),
            arrayOf('-', '-', '-'),
            arrayOf('-', '-', '-'),
        )
        binding.one.background = null
        binding.two.background = null
        binding.three.background = null
        binding.four.background = null
        binding.five.background = null
        binding.six.background = null
        binding.seven.background = null
        binding.eight.background = null
        binding.nine.background = null
        binding.crossTurn.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.remove))
        binding.circleTurn.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.circle))
        binding.linearLayout.alpha = 1.0f
    }

    private fun onClick(it: View?, i: Int, j: Int) {

        it?.let {
            if (!list.contains(it.tag.toString())) {
                it.elevation = 100f
                if (isFirst) {
                    it.background = ContextCompat.getDrawable(requireContext(), R.drawable.cross)
                    board[i][j] = 'x'
                    binding.crossTurn.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.close_small))
                    binding.circleTurn.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.circle_circle))
                } else {
                    it.background = ContextCompat.getDrawable(requireContext(), R.drawable.circle)
                    board[i][j] = 'o'
                    binding.crossTurn.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.remove))
                    binding.circleTurn.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.circle))
                }
                isFirst = !isFirst
                list.add(it.tag as String)
                if (gameOver()) {
                    binding.linearLayout.alpha = 0.4f
                }
            }
        }
    }

    private fun gameOver(): Boolean {
        return if (winSituations('x')) {
            Toast.makeText(requireContext(), "Player 1 won", Toast.LENGTH_SHORT).show()
            val win = Integer.parseInt((binding.tvCrossWin.text as String)[0].toString())
            "${win+1} wins".also {binding.tvCrossWin.text = it}
            true
        } else if (winSituations('o')) {
            Toast.makeText(requireContext(), "Player 2 won", Toast.LENGTH_SHORT).show()
            val win = Integer.parseInt((binding.tvCircleWin.text as String)[0].toString())
            "${win+1} wins".also { binding.tvCircleWin.text = it }

            true
        } else if (list.size == 9) {
            Toast.makeText(requireContext(), "No one won\nIt's a draw", Toast.LENGTH_SHORT).show()
            val draw = Integer.parseInt((binding.tvDraws.text as String)[0].toString())
            "${draw+1} draws".also { binding.tvDraws.text = it}
            true
        } else {
            false
        }
    }

    private fun winSituations(char: Char) : Boolean{
        return (board[0][0] == char && board[1][0] == char && board[2][0] == char) ||
                (board[0][1] == char && board[1][1] == char && board[2][1] == char) ||
                (board[0][2] == char && board[1][2] == char && board[2][2] == char) ||
                (board[0][0] == char && board[0][1] == char && board[0][2] == char) ||
                (board[1][0] == char && board[1][1] == char && board[1][2] == char) ||
                (board[2][0] == char && board[2][1] == char && board[2][2] == char) ||
                (board[0][0] == char && board[1][1] == char && board[2][2] == char) ||
                (board[0][2] == char && board[1][1] == char && board[2][0] == char)

    }

}