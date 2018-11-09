package com.cysdk.data.http;


import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiService {
    @POST("/api/AppZulin/EquipmentTypeList")
    Observable<String> equipmentTypeList(@Header("Authorization") String authorization);
}
