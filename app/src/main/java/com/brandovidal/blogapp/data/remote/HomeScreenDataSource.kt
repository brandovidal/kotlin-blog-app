package com.brandovidal.blogapp.data.remote

import com.brandovidal.blogapp.core.Resource
import com.brandovidal.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestPosts(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()

        for(post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let{ postList.add(it) }
        }

        return Resource.Success(postList)
    }
}