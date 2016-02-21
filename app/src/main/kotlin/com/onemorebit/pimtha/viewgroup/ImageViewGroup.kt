package com.onemorebit.pimtha.viewgroup

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.onemorebit.pimtha.R
import com.onemorebit.pimtha.http.RetroAdapter
import com.onemorebit.pimtha.model.ImagesArrayDao
import kotlinx.android.synthetic.main.viewgroup_image.view.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Euro on 2/19/16 AD.
 */
class ImageViewGroup : FrameLayout {
    private var view: View? = null

    constructor(context: Context) : super(context) {
        initInflate()
        initInstance(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initInflate()
        initInstance(attrs, 0)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
        initInstance(attrs, defStyleAttr)
    }

    private fun initInstance(attrs: AttributeSet?, defStyleAttr: Int) {
        Glide.with(context).load(R.drawable.pimtha1).into(ivImage)
    }

    private fun initInflate() {
        view = LayoutInflater.from(context).inflate(R.layout.viewgroup_image, this)

    }

    private fun initAttr() {

    }

    fun setImageUrl(url: String) {
        Glide.with(context).load(url).into(ivImage)
    }

    fun setImageArrayUrl(urls: ImagesArrayDao) {
        var count: Int = 0

        var url = RetroAdapter.BASE_URL + "pimtha/" + urls.path + urls.images[count].fileName

        var playAllImages: Observable<ImagesArrayDao.ImagesEntity> = Observable.from(urls.images)

        playAllImages.subscribeOn(Schedulers.io())
                .flatMap { Observable.create<Int> { subscribe ->
                    val s = RetroAdapter.BASE_URL + "pimtha/" + urls.path + urls.images[count].fileName
                    var file: File? = Glide.with(context).load(s).downloadOnly(ivImage.measuredWidth, ivImage.measuredHeight).get()
                        count++
                        subscribe.onNext(count)
                } }
                .flatMap { Observable.just(count) }
                .filter { it < urls.images.size }
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe {
                    val s = RetroAdapter.BASE_URL + "pimtha/" + urls.path + urls.images[it].fileName
                    Glide.with(context)
                            .load(s)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ivImage);
                    Timber.d(s)
                }

    }
}
