package com.example.andvk

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.andvk.EditPostActivity.Companion.POST_KEY
import com.example.andvk.dto.Post

class EditPostResultContract : ActivityResultContract<Unit, Post?>() {

    override fun createIntent(context: Context, input: Unit?): Intent =
        Intent(context, EditPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): Post? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getParcelableExtra(intent.POST_KEY)
        } else {
            null
        }
}