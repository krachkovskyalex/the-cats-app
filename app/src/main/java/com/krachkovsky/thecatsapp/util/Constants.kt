package com.krachkovsky.thecatsapp.util

object Constants {

    const val BASE_URL = "https://api.thecatapi.com/v1/images/"

    const val API_KEY_NAME = "x-api-key"
    const val API_KEY = "94022970-c495-470f-8070-e27bac61e06e"

    const val QUERY_PARAM_LIMIT = "limit"
    const val QUERY_PARAM_PAGE = "page"
    const val QUERY_PARAM_ORDER = "order"

    const val INITIAL_PAGE_NUMBER = 0
    const val PARAM_PAGE_LIMIT = 10
    const val PARAM_PAGE_LIMIT_MAX = 100

    const val PARAM_ORDER_DESC = "DESC"
    const val PARAM_ORDER_ASC = "ASC"
    const val PARAM_ORDER_RANDOM = "RANDOM"

    const val DB_NAME = "cats.db"
    const val FAVORITE_CATS = "favorite_cats"

    const val ERROR = 1
    const val PROGRESS = 0
}