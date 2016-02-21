package com.onemorebit.pimtha.http

import com.onemorebit.pimtha.model.ImagesArrayDao
import retrofit2.http.GET
import rx.Observable

/**
 * Created by Euro on 2/19/16 AD.
 */
interface RipzeryApiService {
    @GET("pimtha") fun getImage() : Observable<ImagesArrayDao>
}