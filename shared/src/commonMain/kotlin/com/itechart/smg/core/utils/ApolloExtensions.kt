package com.itechart.smg.core.utils

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation

val <T: Operation.Data> ApolloResponse<T>.errorMessage: String
    get() {
        this.errors?.let {
            if(it.isNotEmpty()) {
                return it[0].message
            }
        }

        return "Unknown error"
    }