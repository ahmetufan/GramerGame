package com.ahmet.gramer.view

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmet.gramer.R
import com.ahmet.gramer.databinding.FragmentQuestionBinding
import com.ahmet.gramer.models.Question
import com.ahmet.gramer.utils.LoginPref
import com.ahmet.gramer.viewmodel.QuestionViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuestionViewModel by viewModels()
    private val args by navArgs<QuestionFragmentArgs>()
    lateinit var question: ArrayList<Question>
    var score = 0
    var i = 0
    lateinit var session: LoginPref

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        question = ArrayList<Question>()

        initViewModel()

        pref()


    }

    private fun initViewModel() {

        viewModel.questionLiveData.observe(viewLifecycleOwner, Observer {
            question = ArrayList(it)

            binding.questionText.text = question[i].question


            setData()

            binding.questionNumber.text = "${i + 1}/${question.size}"
        })
        viewModel.getQuestionData(args.testUID)
    }

    fun setData() {

        binding.opt1.text = question[i].option_one
        binding.opt2.text = question[i].option_two
        binding.opt3.text = question[i].option_three
        binding.opt4.text = question[i].option_four

        Glide.with(requireContext())
            .load(question[i].url)
            .into(binding.urlImage)


    }

    fun changeQuestion() {

        binding.devamButton.setOnClickListener {

            if (i == question.size) {
                i = 0
                scoreScreen()

            } else {

                binding.opt1.isClickable = true
                binding.opt2.isClickable = true
                binding.opt3.isClickable = true
                binding.opt4.isClickable = true

                binding.opt1.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mainblue
                    )
                )
                binding.opt2.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mainblue
                    )
                )
                binding.opt3.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mainblue
                    )
                )
                binding.opt4.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mainblue
                    )
                )

                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_option)
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_option)
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_option)
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_option)

                binding.questionText.setText(question[i].question)


                setData()

                binding.questionNumber.text = "${i + 1}/${question.size}"
            }
        }

    }

    fun scoreScreen() {

        val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(score)
        findNavController().navigate(action)
    }

    private fun speak() {

        binding.speakButton.setOnClickListener {

            tts= TextToSpeech(requireContext(), TextToSpeech.OnInitListener {
                if (it == TextToSpeech.SUCCESS) {

                    val result = tts!!.setLanguage(Locale.ENGLISH)
                    tts!!.setSpeechRate(0.7f)
                    tts!!.speak(binding.questionText.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Belirtilen dil desteklenmiyor")
                    }

                } else {
                    Log.e("TTS", "İnitilation Başarısız")
                }
            })
        }

    }

    override fun onResume() {
        super.onResume()

        changeQuestion()
        control()
        speak()
    }

    fun control() {

        opt1()

        opt2()

        opt3()

        opt4()

    }

    fun opt1() {


        binding.opt1.setOnClickListener {

            if (question[i].correct_ans == binding.opt1.text) {
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt1.setTextColor(requireContext().getColor(R.color.white))
                score += 10
                val animObj =
                    TranslateAnimation(-400.0F, binding.questionScore.width.toFloat(), 0.0F, 0.0F)
                animObj.duration = 500
                binding.questionScore.startAnimation(animObj)
                binding.questionScore.text = "Score: ${score.toString()}"

            } else if (question[i].correct_ans == binding.opt2.text) {
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt2.setTextColor(requireContext().getColor(R.color.white))
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt3.text) {
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt3.setTextColor(requireContext().getColor(R.color.white))
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt4.text) {
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt4.setTextColor(requireContext().getColor(R.color.white))
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.opt1.isClickable = false
            binding.opt2.isClickable = false
            binding.opt3.isClickable = false
            binding.opt4.isClickable = false
        }
    }

    fun opt2() {

        binding.opt2.setOnClickListener {

            if (question[i].correct_ans == binding.opt2.text) {
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt2.setTextColor(requireContext().getColor(R.color.white))
                score += 10
                val animObj =
                    TranslateAnimation(-400.0F, binding.questionScore.width.toFloat(), 0.0F, 0.0F)
                animObj.duration = 500
                binding.questionScore.startAnimation(animObj)
                binding.questionScore.text = "Score: ${score.toString()}"

            } else if (question[i].correct_ans == binding.opt1.text) {
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt1.setTextColor(requireContext().getColor(R.color.white))
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt3.text) {
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt3.setTextColor(requireContext().getColor(R.color.white))
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt4.text) {
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt4.setTextColor(requireContext().getColor(R.color.white))
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.opt1.isClickable = false
            binding.opt2.isClickable = false
            binding.opt3.isClickable = false
            binding.opt4.isClickable = false
        }
    }

    fun opt3() {

        binding.opt3.setOnClickListener {

            if (question[i].correct_ans == binding.opt3.text) {
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt3.setTextColor(requireContext().getColor(R.color.white))
                score += 10
                val animObj =
                    TranslateAnimation(-400.0F, binding.questionScore.width.toFloat(), 0.0F, 0.0F)
                animObj.duration = 500
                binding.questionScore.startAnimation(animObj)
                binding.questionScore.text = "Score: ${score.toString()}"

            } else if (question[i].correct_ans == binding.opt2.text) {
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt2.setTextColor(requireContext().getColor(R.color.white))
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt1.text) {
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt1.setTextColor(requireContext().getColor(R.color.white))
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt4.text) {
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt4.setTextColor(requireContext().getColor(R.color.white))
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.opt1.isClickable = false
            binding.opt2.isClickable = false
            binding.opt3.isClickable = false
            binding.opt4.isClickable = false
        }
    }

    fun opt4() {

        binding.opt4.setOnClickListener {

            if (question[i].correct_ans == binding.opt4.text) {
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt4.setTextColor(requireContext().getColor(R.color.white))
                score += 10
                val animObj =
                    TranslateAnimation(-400.0F, binding.questionScore.width.toFloat(), 0.0F, 0.0F)
                animObj.duration = 500
                binding.questionScore.startAnimation(animObj)
                binding.questionScore.text = "Score: ${score.toString()}"

            } else if (question[i].correct_ans == binding.opt2.text) {
                binding.opt2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt2.setTextColor(requireContext().getColor(R.color.white))
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt3.text) {
                binding.opt3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt3.setTextColor(requireContext().getColor(R.color.white))
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_false)

            } else if (question[i].correct_ans == binding.opt1.text) {
                binding.opt1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.opt1.setTextColor(requireContext().getColor(R.color.white))
                binding.opt4.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.opt1.isClickable = false
            binding.opt2.isClickable = false
            binding.opt3.isClickable = false
            binding.opt4.isClickable = false
        }
    }

    private fun pref() {

        session = LoginPref(requireContext())

        session.checkLogin()

        val user: HashMap<String, String> = session.getUserDetails()

        val username = user.get(LoginPref.key_username)

        binding.persontextquestion.text = "Merhaba $username"
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }


}