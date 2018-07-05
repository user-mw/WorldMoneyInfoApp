package ru.project.datalayer.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.project.domainlayer.model.RemoteCurrencyPair;

public interface IOneForgeApi {
    @GET("quotes")
    Single<List<RemoteCurrencyPair>> getCurrenciesInfo(
            @Query("pairs") String pairs,
            @Query("api_key") String key
    );
}
