package com.onemorebit.pimtha.extension

/**
 * Created by Euro on 2/20/16 AD.
 */

fun String.getPimthaIndex() : Int{
   return this.substringAfterLast("pimtha", "1").substringBeforeLast(".jpg", "1").toInt()
}
