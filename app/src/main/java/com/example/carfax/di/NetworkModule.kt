package com.example.carfax.di

import android.content.Context
import androidx.room.Room
import com.example.carfax.data.local_source.CarDao
import com.example.carfax.data.local_source.CarDatabase
import com.example.carfax.data.remote_source.Api
import com.example.carfax.data.repository.RepositoryImpl
import com.example.carfax.domain.repository.Repository
import com.example.carfax.domain.usecase.GetAllCarsUsecase
import com.example.carfax.domain.util.MyConstants.BASE_URL
import com.example.carfax.domain.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: Api, carDao: CarDao, networkHelper: NetworkHelper): Repository {
        return RepositoryImpl(api, carDao, networkHelper)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }

    @Provides
    @Singleton
    fun provideGetAllCategoryUseCase(repository: Repository): GetAllCarsUsecase {
        return GetAllCarsUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CarDatabase {
        return Room.databaseBuilder(
            context,
            CarDatabase::class.java,
            "car_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCarDao(database: CarDatabase): CarDao {
        return database.carDao()
    }

}