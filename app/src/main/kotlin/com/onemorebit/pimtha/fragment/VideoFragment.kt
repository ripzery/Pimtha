package com.onemorebit.pimtha.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.onemorebit.pimtha.R
import kotlinx.android.synthetic.main.fragment_video.*

/**
 * Created by Euro on 2/24/16 AD.
 */

class VideoFragment : Fragment() {

    private var param1: String = ""
    private var param2: String = ""

    companion object{
        private val ARG_1 : String = "PARAM1"
        private val ARG_2 : String = "PARAM2"

        fun getInstance(param1: String? = "Hello", param2: String? = "World"): VideoFragment{
            var bundle: Bundle = Bundle()

            bundle.putString(ARG_1, param1)
            bundle.putString(ARG_2, param2)

            var videoFragment: VideoFragment = VideoFragment()
            videoFragment.arguments = bundle

            return videoFragment

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        param1 = arguments.getString(ARG_1)
        param2 = arguments.getString(ARG_2)

        if(savedInstanceState != null){
            param1 = savedInstanceState.getString(ARG_1)
            param2 = savedInstanceState.getString(ARG_2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater!!.inflate(R.layout.fragment_video, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startVideo()

    }

    private fun startVideo() {
        videoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=Moqt1I_3oOk"))
        val mediaController = MediaController(this@VideoFragment.activity)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.setOnPreparedListener { videoView.start() }
    }
}
