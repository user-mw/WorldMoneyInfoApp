package ru.project.worldmoneyinfo.dependency;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.project.datalayer.BuildConfig;
import ru.project.datalayer.api.IOneForgeApi;

@Module
public class ApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
        clientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        return clientBuilder.build();
    }

    @Provides
    @Singleton
    public Gson provideGsonInstance() {
        return new Gson();
    }

    @Provides
    public Retrofit getRetrofitInstance(Gson gsonInstance, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MAIN_URL)
                // for interceptors
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonInstance))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public IOneForgeApi provideApi(Retrofit retrofitInstance) {
        return retrofitInstance.create(IOneForgeApi.class);
    }
}
