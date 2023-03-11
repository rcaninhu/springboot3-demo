package net.rcan.demo.web

import net.rcan.demo.common.ApiListResponse
import net.rcan.demo.common.ApiResponse
import net.rcan.demo.domain.board.*
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("")
    fun searchBoard(
        boardSearchRequest: BoardSearchRequest,
        pageable: Pageable
    ): ResponseEntity<ApiListResponse<BoardSearchResponse>> =
        boardService.searchBoard(boardSearchRequest, pageable).let {
            ResponseEntity.ok(it)
        }

    @GetMapping("/{boardId}")
    fun detailBoard(
        @PathVariable("boardId") board: Board?
    ): ResponseEntity<ApiResponse<BoardDetailResponse>> = board?.let {
        ResponseEntity.ok(
            ApiResponse(
                data = BoardDetailResponse(
                    title = it.title,
                    contents = it.contents
                )
            )
        )
    } ?: ResponseEntity.noContent().build()

    @PostMapping("")
    fun saveBoard(
        @RequestBody boardSaveRequest: BoardSaveRequest
    ): ResponseEntity<ApiResponse<BoardSaveResponse>> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(boardService.saveBoard(boardSaveRequest))

    @PutMapping("/{boardId}")
    fun updateBoard(
        @PathVariable("boardId") board: Board?,
        @RequestBody boardUpdateResponse: BoardUpdateResponse
    ): ResponseEntity<ApiResponse<Void>> =
        board?.let {
            boardService.updateBoard(board, boardUpdateResponse).let {
                ResponseEntity.ok().build()
            }
        } ?: ResponseEntity.noContent().build()

    @DeleteMapping("/{boardId}")
    fun deleteBoard(
        @PathVariable("boardId") board: Board?
    ): ResponseEntity<ApiResponse<Void>> =
        board?.let {
            boardService.softDelete(board).let {
                ResponseEntity.ok().build()
            }
        } ?: ResponseEntity.noContent().build()
}
