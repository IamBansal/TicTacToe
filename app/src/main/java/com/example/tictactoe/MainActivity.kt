package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tictactoe.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: LinkedList<String>
    private var isFirst = true
    private lateinit var board: Array<Array<Char>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.btnPlay.setOnClickListener {
            binding.btnPlay.visibility = View.GONE
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
        binding.textView.text = getString(R.string.player_one_s_turn)
        binding.linearLayout.alpha = 1.0f
    }

    private fun onClick(it: View?, i: Int, j: Int) {

        binding.btnPlay.visibility = View.VISIBLE
        binding.btnPlay.text = getString(R.string.start_over)

        it?.let {
            if (!list.contains(it.tag.toString())) {
                if (isFirst) {
                    it.background = ContextCompat.getDrawable(this, R.drawable.no)
                    board[i][j] = 'x'
                    binding.textView.text = getString(R.string.player_two_turn)
                } else {
                    it.background = ContextCompat.getDrawable(this, R.drawable.circle)
                    board[i][j] = 'o'
                    binding.textView.text = getString(R.string.player_one_s_turn)
                }
                isFirst = !isFirst
                list.add(it.tag as String)
                if (gameOver()) {
                    binding.linearLayout.alpha = 0.4f
                    binding.btnPlay.text = getString(R.string.play)
                }
            }
        }
    }

    private fun gameOver(): Boolean {
        return if (winSituations('x')) {
            Toast.makeText(this, "Player 1 won", Toast.LENGTH_SHORT).show()
            true
        } else if (winSituations('o')) {
            Toast.makeText(this, "Player 2 won", Toast.LENGTH_SHORT).show()
            true
        } else if (list.size == 9) {
            Toast.makeText(this, "No one won\nPlay Again.", Toast.LENGTH_SHORT).show()
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