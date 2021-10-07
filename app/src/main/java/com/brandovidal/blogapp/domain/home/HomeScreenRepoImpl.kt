package com.brandovidal.blogapp.domain.home

import com.brandovidal.blogapp.core.Resource
import com.brandovidal.blogapp.data.model.Post
import com.brandovidal.blogapp.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestPosts(): Resource<List<Post>> = dataSource.getLatestPosts()
}