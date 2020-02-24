package com.example.testapp.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.R
import com.example.testapp.hide
import com.example.testapp.repository.AddPostRepository
import com.example.testapp.show
import com.example.testapp.showToast
import kotlinx.android.synthetic.main.activity_add_post.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.http.Body
import java.util.*

class AddPostActivity : AppCompatActivity() {
    private val addPostViewModel:AddPostViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        addButton.setOnClickListener {
            addPost(title_et.text.toString(),description_et.text.toString())
        }
    }

    fun addPost(title:String?,body:String?) = when(formIsValidated(title, body)){
        ValidatingStatus.ERROR_IN_TITLE -> {
            title_et.error = "Пожалуйста введите название"
        }
        ValidatingStatus.ERROR_IN_DESCRIPTION -> {
            description_et.error = "Пожалуйста введите описание"
        }
        ValidatingStatus.VALIDATED -> {
            showProgressBar()
            val userId = Random().nextInt(49)+1
            addPostViewModel.addPost(title!!,body!!,userId).observe(this, androidx.lifecycle.Observer {
                if (it != null){
                    observingAddingStatus(it)
                }else{
                    showErrorMessage()
                    hideProgressBar()
                }
            })
        }
    }

    private fun observingAddingStatus(it: AddPostRepository.AddingStatus) {
        when (it) {
            AddPostRepository.AddingStatus.AddedSuccesfully -> {
                showSuccesMessage()
                hideProgressBar()
            }
            AddPostRepository.AddingStatus.AddingError -> {
                showErrorMessage()
                hideProgressBar()
            }
        }
    }

    private fun showSuccesMessage() {
        showToast("Успешно добавлено")
        finish()
    }

    private fun showErrorMessage() {
        showToast("Произашло какая та ошибка повторите пожалуйста")
    }

    private fun formIsValidated(title:String?,body:String?): ValidatingStatus {
        if (title.isNullOrEmpty()) return ValidatingStatus.ERROR_IN_TITLE
        if (body.isNullOrEmpty()) return ValidatingStatus.ERROR_IN_DESCRIPTION
        return ValidatingStatus.VALIDATED
    }

    private enum class ValidatingStatus{
        VALIDATED,ERROR_IN_TITLE,ERROR_IN_DESCRIPTION
    }

    private fun showProgressBar(){
        add_post_progess_bar.show()
    }

    private fun hideProgressBar(){
        add_post_progess_bar.hide()
    }
}
