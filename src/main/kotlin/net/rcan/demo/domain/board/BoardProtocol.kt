package net.rcan.demo.domain.board

data class BoardDetailResponse(
    val title: String,
    val contents: String,
)

data class BoardSaveRequest(
    val title: String,
    val contents: String,
)

data class BoardSaveResponse(
    var id: Long?,
    val title: String,
)

data class BoardUpdateResponse(
    var title:String,
    var contents: String
)

data class BoardSearchRequest(
    val title: String,
)

data class BoardSearchResponse(
    val id: Long?,
    val writeDate: String,
    val title: String,
    val contents: String,
)