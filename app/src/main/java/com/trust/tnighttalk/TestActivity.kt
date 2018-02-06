package com.trust.tnighttalk

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.trust.tnighttalk.base.BaseActivtiy
import com.trust.tnighttalk.tool.kttest.RxKotlin
import com.trust.tnighttalk.tool.retrofit.RetrofitTest
import kotlinx.android.synthetic.main.activity_test.*
import java.io.InputStream

class TestActivity : BaseActivtiy(),View.OnClickListener {
    private var context = this
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ssssssss -> {
                Toast.makeText(context,"点击了1111111",Toast.LENGTH_LONG).show()
                var rest =  RetrofitTest(this)
//                rest.sendTest()

                rest.sendLogin("")
//                rest.sendSenDiLogin()
            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {
        ssssssss.setOnClickListener(this)
        ssssssss.text = "this is kotlin"
        RxKotlin()
    }

    override fun initDate(savedInstanceState: Bundle?) {

    }

    override fun requestSuccessCallBack(code: Int, `object`: Any?) {

    }

    override fun requestErrorCallBack(code: Int, `object`: Any?) {

    }


}
