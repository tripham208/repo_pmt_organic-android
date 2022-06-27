package com.example.myapplication.datn.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.datn.databinding.FragmentVerificationBinding
import com.example.myapplication.datn.ui.base.BaseFragment
import com.example.myapplication.datn.utils.Logger
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.example.myapplication.datn.R
import com.example.myapplication.datn.utils.Checker

import com.google.firebase.auth.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class VerificationFragment : BaseFragment<FragmentVerificationBinding>() {
    private var auth: FirebaseAuth? = null
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val args: VerificationFragmentArgs by navArgs()
    private val viewModel: UserViewModel by activityViewModels()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentVerificationBinding {
        return FragmentVerificationBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        auth?.setLanguageCode("vn")
        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Logger.d("onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Logger.w("onVerificationFailed")
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    Logger.w("Exceeded per phone number quota for sending verification codes.")
                    Toast.makeText(context, "Gioi hạn mỗi số có thể nhận", Toast.LENGTH_LONG).show()
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Logger.w("onCodeSent:$verificationId")
                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
            }
        }
        // [END phone_auth_callbacks]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = auth?.let {
            activity?.let { it1 ->
                PhoneAuthOptions.newBuilder(it)
                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(it1)                 // Activity (for callback binding)
                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
            }
        }
        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
        // [END start_phone_auth]
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }

    // [START resend_verification]
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = auth?.let {
            activity?.let { it1 ->
                PhoneAuthOptions.newBuilder(it)
                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(it1)                 // Activity (for callback binding)
                    .setCallbacks(callbacks)
            }
        }          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder?.setForceResendingToken(token) // callback's ForceResendingToken
        }
        if (optionsBuilder != null) {
            PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
        }
    }
    // [END resend_verification]

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        activity?.let {
            auth?.signInWithCredential(credential)
                ?.addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(context, "thành công", Toast.LENGTH_LONG).show()
                        //val user = task.result?.user
                        Logger.d(args.phone.toString())
                        viewModel.loginByPhone(args.phone)
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                        Toast.makeText(context, "thất bại", Toast.LENGTH_LONG).show()
                        // Update UI
                    }
                }
        }
    }


    private fun getCode(): String {

        binding.apply {
            return "${num1Very.text}${num2Very.text}${num3Very.text}${num4Very.text}${num5Very.text}${num6Very.text}"
        }
    }

    override fun initAction() {
        super.initAction()
        binding.btnVeryToMain.setOnClickListener {
            val code = getCode()
            Logger.d(code)
            verifyPhoneNumberWithCode(storedVerificationId, code)
        }
        binding.tvResend.setOnClickListener {
            resendVerificationCode("+84${args.phone}", resendToken)
        }
    }

    override fun initDataSaveArgs() {
        super.initDataSaveArgs()
        //HERE NEED
        // Toast.makeText(context, "+84${args.phone}", Toast.LENGTH_LONG).show()

        startPhoneNumberVerification("+84${args.phone}")
        viewModel.reSendTime()
        binding.tvResend.isClickable = false
        //399214349 0582639863
    }

    override fun observerLiveData() {
        super.observerLiveData()
        viewModel.loginResult.observe(viewLifecycleOwner) {
            if (it == R.string.success) {
                findNavController().navigate(R.id.action_verificationFragment_to_mainFragment2)
                Checker.HAS_USER = true
            } else if (it == R.string.space) {
                val action =
                    VerificationFragmentDirections.actionVerificationFragmentToRegisterFragment(args.phone)
                findNavController().navigate(action)
            }
        }
        viewModel.time.observe(viewLifecycleOwner) {
            if (it != 0) {
                binding.tvResend.text = resources.getString(R.string.send) + "($it)"
            } else {
                binding.tvResend.text = resources.getString(R.string.send)
                binding.tvResend.isClickable = true
            }
        }

    }

}