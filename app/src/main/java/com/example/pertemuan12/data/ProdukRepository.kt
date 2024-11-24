package com.example.pertemuan12.data

import com.example.pertemuan12.data.model.Product
import kotlinx.coroutines.flow.Flow


interface ProdukRepository {
    suspend fun getProductsList(): Flow<Hasil<List<Product>>>
}