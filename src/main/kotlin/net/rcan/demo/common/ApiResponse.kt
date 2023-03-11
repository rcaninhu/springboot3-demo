package net.rcan.demo.common

data class ApiResponse<T>(
    val message: String? = null,
    val success: Boolean? = true,
    val data: T? = null,
)

data class ApiListResponse<T>(
    val message: String? = null,
    val success: Boolean? = true,
    val data: List<T>? = emptyList(),
    val number: Int? = 0,
    val next: Boolean? = false,
    val size: Int = 0,
    val numberOfElements: Int? = 0,
)
