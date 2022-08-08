package com.example.feature_details_screen.data.remote

import com.example.feature_details_screen.data.remote.dto.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DetailsFirestoreImpl : DetailsFirestore {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchCloudDataSource(id: String): CloudDataSource? {
        return firestore
            .collection(FIRESTORE_PRODUCTS_TABLE)
            .document(id)
            .get()
            .await()
            .toObject(CloudDataSource::class.java)
    }

    private companion object {
        const val FIRESTORE_PRODUCTS_TABLE = "products"
    }
}