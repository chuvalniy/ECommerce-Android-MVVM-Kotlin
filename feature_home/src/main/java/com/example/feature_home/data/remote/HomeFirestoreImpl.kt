package com.example.feature_home.data.remote

import com.example.feature_home.data.remote.dto.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeFirestoreImpl : HomeFirestore {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchCloudDataSource(category: Int): List<CloudDataSource> {
        return firestore
            .collection(FIRESTORE_PRODUCTS_TABLE)
            .whereEqualTo("category", category)
            .whereArrayContainsAny("subcategories", listOf("best_seller", "hot_sales"))
            .get()
            .await()
            .toObjects(CloudDataSource::class.java)
    }

    private companion object {
        const val FIRESTORE_PRODUCTS_TABLE = "products"
    }
}