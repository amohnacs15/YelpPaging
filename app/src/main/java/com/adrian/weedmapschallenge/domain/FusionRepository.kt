package com.adrian.weedmapschallenge.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.adrian.weedmapschallenge.data.Business
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FusionRepository @Inject constructor(
    private val yelpFusionService: YelpFusionService
) : IFusionRepository {

    override fun getBusinessSearchResponse(
        term: String,
        latitude: Double,
        longitude: Double
    ): Flowable<PagingData<Business>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FusionPagingSource(yelpFusionService, term, latitude, longitude)
            }
        ).flowable
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}