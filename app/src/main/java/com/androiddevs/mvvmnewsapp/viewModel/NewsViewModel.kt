package com.androiddevs.mvvmnewsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.model.NewsResponce
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepositiry:NewsRepository):ViewModel() {


    val breakingNews:MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
     val breakingNewsPage = 1
    val searchNews:MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
    val searchNewsPage = 1
    init {
        getBreakingNews("us")
    }


    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val responce = newsRepositiry.getBreakingNews(countryCode,breakingNewsPage)

        breakingNews.postValue(handleBreakingNewsResponse(responce))
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val responce = newsRepositiry.searchNews(searchQuery,searchNewsPage)

        searchNews.postValue(handleBreakingNewsResponse(responce))
    }
    private fun handleBreakingNewsResponse(responce: Response<NewsResponce>):Resource<NewsResponce>{
        if (responce.isSuccessful){
            responce.body()?.let { resultResponce->

                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }
    private fun handleSearchNewsResponse(responce: Response<NewsResponce>):Resource<NewsResponce>{
        if (responce.isSuccessful){
            responce.body()?.let { resultResponce->

                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }
}