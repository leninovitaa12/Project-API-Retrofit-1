package com.example.pertemuan12.data

import coil.network.HttpException
import com.example.pertemuan12.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ProductRepoImpl(
    private val api: Api
): ProdukRepository {
    override suspend fun getProductsList(): Flow<Hasil<List<Product>>> {
        return flow {
            val productsFromApi = try {
                api.getProductsList()

            } catch (e: IOException){
                e.printStackTrace()
                emit(Hasil.Error(message = "Error loading products"))
                return@flow
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Hasil.Error(message = "Error loading products"))
                return@flow
            } catch (e: Exception){
                e.printStackTrace()
                emit(Hasil.Error(message = "Error loading products"))
                return@flow
            }

            emit(Hasil.Success(productsFromApi.products))
        }

    }

}









































