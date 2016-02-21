package com.onemorebit.pimtha.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType
import com.onemorebit.pimtha.R
import com.onemorebit.pimtha.extension.log
import com.onemorebit.pimtha.extension.toast
import com.onemorebit.pimtha.http.RetroAdapter
import com.onemorebit.pimtha.model.ImagesArrayDao
import kotlinx.android.synthetic.main.fragment_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.util.*

/**
 * Created by Euro on 2/20/16 AD.
 */


class MainFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var pimthaArray: ArrayList<String> = ArrayList()

    companion object {
        private val ARG_1 = "ARG_1"
        private val ARG_2 = "ARG_2"

        fun getInstance(param1: String = "Hello", param2: String = "World"): MainFragment {
            var mainFragment: MainFragment = MainFragment()
            var args: Bundle = Bundle()
            args.putString(ARG_1, param1)
            args.putString(ARG_2, param2)
            mainFragment.arguments = args
            return mainFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        log(arguments.getString(ARG_1))

        param1 = arguments.getString(ARG_1)
        param2 = arguments.getString(ARG_2)

        if (savedInstanceState != null) {
            param1 = savedInstanceState.getString(ARG_1)
            param2 = savedInstanceState.getString(ARG_2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_main, container, false)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initInstance()

    }

    fun fetchImagesUrl() {
        tvHello.text = "Loading images..."
        var call: Observable<ImagesArrayDao> = RetroAdapter.createApiService().getImage()
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe({
                    Collections.sort(it.images)
                    for (item in it.images) {
                        pimthaArray.add(RetroAdapter.BASE_URL + "pimtha/" + it.path + item.fileName)
                    }

                    cacheImages(pimthaArray)
                    tvHello.text = getString(R.string.welcome_to_pimtha_gallery)

                }, { error("Oops" + it.printStackTrace()) })
    }

    fun cacheImages(pimthaArray: ArrayList<String>) {
        var imagesUrlObservable: Observable<String> = Observable.from(pimthaArray)
        var count: Int = 0

        imagesUrlObservable.subscribeOn(Schedulers.io())
                .flatMap {
                    Observable.create<Int> { subscribe ->
                        var file: File? = Glide.with(context).load(pimthaArray[count]).downloadOnly(ivPimtha.measuredWidth, ivPimtha.measuredHeight).get()
                        count++
                        subscribe.onNext(count)
                    }
                }
                .flatMap { Observable.just(count) }
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe { /* update view loading progress */
                    if (count < pimthaArray.size) tvHello.text = "Loading image $count/${pimthaArray.size}"
                    else tvHello.text = "Complete ${pimthaArray.size}/${pimthaArray.size}"
                }
    }

    fun initInstance() {
        fetchImagesUrl()

        Glide.with(this@MainFragment).load(R.drawable.pimtha1).into(ivCircle)
        Glide.with(this@MainFragment).load(R.drawable.pimtha1).into(kbvImage)

        ivCircle.setOnClickListener { setPimthaRandomImg() }
        tvHello.setOnClickListener { activity.toast("This is a toast msg [${param1.plus(param2)}]") }
        btnGallery.setOnClickListener {
            var intent: Intent = Intent(activity, ImageGalleryActivity::class.java)

            intent.putStringArrayListExtra("images", pimthaArray);
            // optionally set background color using Palette
            intent.putExtra("palette_color_type", PaletteColorType.DARK_MUTED);

            startActivity(intent);

        }

    }

    private fun setPimthaRandomImg() {
        if (pimthaArray.isNotEmpty()) {
            var random: Random = Random()
            val index = random.nextInt(pimthaArray.size - 1)
            Glide.with(this@MainFragment).load(pimthaArray[index]).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(kbvImage)
        } else {
            activity.toast("Image is loading..")
        }
    }

}