package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDataBase
import com.androiddevs.mvvmnewsapp.model.Article

class NewsRepository(val db:ArticleDataBase) {


    suspend fun getBreakingNews(countyCode:String,pageNumber:Int )=
        RetrofitInstance().api.getBreakingNews(countyCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber:Int )=
        RetrofitInstance().api.searchForNews(searchQuery,pageNumber)

}