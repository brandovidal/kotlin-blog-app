package com.brandovidal.blogapp.domain

import com.brandovidal.blogapp.core.Resource
import com.brandovidal.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Resource<List<Post>>
}