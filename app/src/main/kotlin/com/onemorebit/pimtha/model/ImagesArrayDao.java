package com.onemorebit.pimtha.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Euro on 2/19/16 AD.
 */
public class ImagesArrayDao {

    /**
     * path : img/
     * images : [{"fileName":"pimtha75.jpg"},{"fileName":"pimtha55.jpg"},{"fileName":"pimtha73.jpg"},{"fileName":"pimtha39.jpg"},{"fileName":"pimtha69.jpg"},{"fileName":"pimtha13.jpg"},{"fileName":"pimtha71.jpg"},{"fileName":"pimtha32.jpg"},{"fileName":"pimtha25.jpg"},{"fileName":"pimtha35.jpg"},{"fileName":"pimtha20.jpg"},{"fileName":"pimtha18.jpg"},{"fileName":"pimtha43.jpg"},{"fileName":"pimtha17.jpg"},{"fileName":"pimtha7.jpg"},{"fileName":"pimtha3.jpg"},{"fileName":"pimtha72.jpg"},{"fileName":"pimtha57.jpg"},{"fileName":"pimtha33.jpg"},{"fileName":"pimtha15.jpg"},{"fileName":"pimtha10.jpg"},{"fileName":"pimtha50.jpg"},{"fileName":"pimtha22.jpg"},{"fileName":"pimtha44.jpg"},{"fileName":"pimtha24.jpg"},{"fileName":"pimtha11.jpg"},{"fileName":"pimtha30.jpg"},{"fileName":"pimtha4.jpg"},{"fileName":"pimtha45.jpg"},{"fileName":"pimtha27.jpg"},{"fileName":"pimtha76.jpg"},{"fileName":"pimtha26.jpg"},{"fileName":"pimtha2.jpg"},{"fileName":"pimtha29.jpg"},{"fileName":"pimtha62.jpg"},{"fileName":"pimtha38.jpg"},{"fileName":"pimtha59.jpg"},{"fileName":"pimtha9.jpg"},{"fileName":"pimtha41.jpg"},{"fileName":"pimtha28.jpg"},{"fileName":"pimtha23.jpg"},{"fileName":"pimtha66.jpg"},{"fileName":"pimtha42.jpg"},{"fileName":"pimtha6.jpg"},{"fileName":"pimtha12.jpg"},{"fileName":"pimtha1.jpg"},{"fileName":"pimtha5.jpg"},{"fileName":"pimtha53.jpg"},{"fileName":"pimtha68.jpg"},{"fileName":"pimtha56.jpg"},{"fileName":"pimtha63.jpg"},{"fileName":"pimtha61.jpg"},{"fileName":"pimtha49.jpg"},{"fileName":"pimtha67.jpg"},{"fileName":"pimtha70.jpg"},{"fileName":"pimtha65.jpg"},{"fileName":"pimtha58.jpg"},{"fileName":"pimtha52.jpg"},{"fileName":"pimtha60.jpg"},{"fileName":"pimtha34.jpg"},{"fileName":"pimtha19.jpg"},{"fileName":"pimtha31.jpg"},{"fileName":"pimtha64.jpg"},{"fileName":"pimtha40.jpg"},{"fileName":"pimtha16.jpg"},{"fileName":"pimtha47.jpg"},{"fileName":"pimtha36.jpg"},{"fileName":"pimtha48.jpg"},{"fileName":"pimtha74.jpg"},{"fileName":"pimtha51.jpg"},{"fileName":"pimtha37.jpg"},{"fileName":"pimtha54.jpg"},{"fileName":"pimtha21.jpg"},{"fileName":"pimtha14.jpg"},{"fileName":"pimtha46.jpg"},{"fileName":"pimtha8.jpg"}]
     */

    @SerializedName("path") public String path;
    /**
     * fileName : pimtha75.jpg
     */

    @SerializedName("images") public List<ImagesEntity> images;

    public static class ImagesEntity implements Comparable<ImagesEntity> {
        @SerializedName("fileName") public String fileName;

        @Override public int compareTo(ImagesEntity another) {
            return fileName.compareTo(another.fileName);
        }

        @Override public String toString() {
            return "ImagesEntity{" +
                "fileName='" + fileName + '\'' +
                '}';
        }


    }

    @Override public String toString() {
        return "ImagesArrayDao{" +
            "path='" + path + '\'' +
            ", images=" + images +
            '}';
    }
}
