package com.example.newsbookapp

import android.webkit.WebView
import android.webkit.WebViewClient


class WebViewController : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {

        // loadurl function will load the
        // url we will provide to our webview
        view.loadUrl(url!!)
        return true
    }
}