package com.example.feature_search.data.remote

import com.example.feature_search.data.remote.dto.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SearchFirestoreImpl : SearchFirestore {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchData(): List<CloudDataSource> {
        return firestore
            .collection(FIRESTORE_PRODUCTS_TABLE)
            .get()
            .await()
            .toObjects(CloudDataSource::class.java)
    }

    private companion object {
        const val FIRESTORE_PRODUCTS_TABLE = "products"
    }
}