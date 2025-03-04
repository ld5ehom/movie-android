package com.ld5ehom.movie.data.bound

fun <DataType, DomainType> flowDataResource(dataAction: suspend () -> DataType) =
    FlowBoundResource<DomainType, DataType>(dataAction)

fun <DataType, DomainType> flowDataResource(
    dataAction: suspend () -> DataType,
    localSourceAction: suspend () -> DataType?,
    saveCache: suspend (DataType) -> Unit,
) =
    FlowPersistableRemoteBoundResource<DomainType, DataType>(
        dataAction,
        localSourceAction,
        saveCache
    )
