package com.example.andvk

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.andvk.EditPostActivity.Companion.POST_KEY
import com.example.andvk.dto.Post

class EditPostResultContract : ActivityResultContract<Post, Post?>() {

    override fun createIntent(context: Context, input: Post): Intent =
        Intent(context, EditPostActivity::class.java).putExtra(POST_KEY,input)

    override fun parseResult(resultCode: Int, intent: Intent?): Post? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getParcelableExtra(POST_KEY)
        } else {
            null
        }
}