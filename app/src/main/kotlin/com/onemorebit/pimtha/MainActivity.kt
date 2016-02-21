package com.onemorebit.pimtha

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.onemorebit.pimtha.extension.get
import com.onemorebit.pimtha.extension.log
import com.onemorebit.pimtha.extension.toast
import com.onemorebit.pimtha.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var mainFragment = MainFragment.getInstance("Hello", "World")

        /* Inflate this fragment */
        supportFragmentManager.beginTransaction().replace(R.id.contentContainer, mainFragment).commit()

        setSupportActionBar(toolbar)

        // Set the padding to match the Status Bar height
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        supportActionBar!!.title =""


        toast("${get("id", 0L) as Long}")
    }

    // A method to find height of the status bar
    fun getStatusBarHeight() : Int{
        var result = 0;
        var resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        log("$result")
        return result;
    }

}
