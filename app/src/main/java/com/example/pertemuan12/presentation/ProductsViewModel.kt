package com.example.pertemuan12.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pertemuan12.data.Hasil
import com.example.pertemuan12.data.ProdukRepository
import com.example.pertemuan12.data.model.Product
import com.example.pertemuan12.data.model.Products
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val produkRepository: ProdukRepository
): ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            produkRepository.getProductsList().collectLatest { result ->
                when(result) {
                    is Hasil.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Hasil.Success -> {
                        result.data?.let { products ->
                            _products.update { products }

                        }
                    }
                }
            }

        }
    }

}


























