package udemy.andriod.newproject.modules

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import udemy.andriod.newproject.repository.service.roomdatabase.APIRequestUser

const val BASE_URL = "https://api.github.com/"

val networkModule = module {
     single {
          Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()

     }
             single { get<Retrofit>().create(APIRequestUser::class.java)}

}