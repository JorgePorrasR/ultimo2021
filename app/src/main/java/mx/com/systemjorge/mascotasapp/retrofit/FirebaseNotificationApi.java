package mx.com.systemjorge.mascotasapp.retrofit;

import mx.com.systemjorge.mascotasapp.models.FCMBody;
import mx.com.systemjorge.mascotasapp.models.FCMResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FirebaseNotificationApi {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key="
    })
    @POST("fcm/send")
    Call<FCMResponse> send(@Body FCMBody body);

}
