package com.techdev.myjournal

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.onegravity.rteditor.RTEditText
import com.onegravity.rteditor.RTManager
import com.onegravity.rteditor.RTToolbar
import com.onegravity.rteditor.api.RTApi
import com.onegravity.rteditor.api.RTMediaFactoryImpl
import com.onegravity.rteditor.api.RTProxyImpl
import com.techdev.myjournal.utils.Utils.PARAM_MESSAGE

class MainActivity : AppCompatActivity() {

    private lateinit var rtManager: RTManager
    private lateinit var rtEditText: RTEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // create RTManager
        val rtApi = RTApi(this, RTProxyImpl(this), RTMediaFactoryImpl(this, true))
        rtManager = RTManager(rtApi, savedInstanceState)

        setTheme(R.style.Theme_MyJournal)

        setContentView(R.layout.activity_main)

        // register toolbar
        val toolbarContainer = findViewById<View>(R.id.rte_toolbar_container) as ViewGroup
        val rtToolbar = findViewById<View>(R.id.rte_toolbar) as RTToolbar
        rtManager.registerToolbar(toolbarContainer, rtToolbar)

        // register editor & set text
        rtEditText = findViewById<View>(R.id.rteMain) as RTEditText
        rtManager.registerEditor(rtEditText, true)
        rtEditText.setRichTextEditing(true, "")

        findViewById<Button>(R.id.btnNextActivity).setOnClickListener {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        rtManager.onDestroy(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        rtManager.onSaveInstanceState(outState)
        val subject: String = rtEditText.text.toString()
        outState.putString(PARAM_MESSAGE, subject)
    }
}
