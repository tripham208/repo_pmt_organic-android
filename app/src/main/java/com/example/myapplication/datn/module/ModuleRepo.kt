package com.example.myapplication.datn.module

import com.example.myapplication.datn.repository.address.AddressRepository
import com.example.myapplication.datn.repository.address.IAddressRepository
import com.example.myapplication.datn.repository.order.IOrderRepository
import com.example.myapplication.datn.repository.order.OrderRepository
import com.example.myapplication.datn.repository.product.IProductRepository
import com.example.myapplication.datn.repository.product.ProductRepository
import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface ModuleRepo {
    @Binds
    fun provideIUser(useRepository: UserRepository): IUserRepository

    @Binds
    fun provideIProduct(productRepository: ProductRepository): IProductRepository

    @Binds
    fun provideIOrder(orderRepository: OrderRepository): IOrderRepository

    @Binds
    fun provideIAddress(orderRepository: AddressRepository): IAddressRepository
}