package com.ld5ehom.movie.data.bound

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import com.ld5ehom.movie.data_resource.DataResource

class FlowBoundResource<DomainType, DataType>(dataAction: suspend () -> DataType) :
    FlowBaseBoundResource<DomainType, DataType>(dataAction) {

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<DataResource<DomainType>>) {
        collector.emit(DataResource.loading<DomainType>() as DataResource<DomainType>)
        fetchFromSource(collector)
    }

}
