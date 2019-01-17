package ru.project.datalayer.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.project.domainlayer.model.RemoteCurrencyData;

public interface IOneForgeApi {
    @GET("quotes")
    Single<List<RemoteCurrencyData>> getCurrenciesInfo(
            @Query("pairs") String pairs,
            @Query("api_key") String key
    );
}
