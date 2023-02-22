package net.rcan.demo.domain.board

import io.swagger.v3.oas.annotations.media.Schema


data class BoardDetailResponse(
    val title: String,
    val contents: String,
)

@Schema(description = "게시판 저장 요청 객체")
data class BoardSaveRequest(

    @field:Schema(
        description = "제목",
        example = "제목입니다",
    )
    val title: String,
    @field:Schema(
        description = "내용",
        example = "내용을 입력해주세요.",
    )
    val contents: String,
)

data class BoardSaveResponse(
    var id: Long?,
    val title: String,
)

data class BoardUpdateResponse(
    var title: String,
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