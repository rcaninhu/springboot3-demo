package net.rcan.demo.web

import net.rcan.demo.domain.board.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("")
    fun searchBoard(boardSearchRequest: BoardSearchRequest): ResponseEntity<List<BoardSearchResponse>> =
        boardService.searchBoard(boardSearchRequest).let {
            if (it.isNotEmpty()) {
                ResponseEntity.ok(it)
            } else {
                ResponseEntity.noContent().build()
            }
        }
    @GetMapping("/{boardId}")
    fun detailBoard(
        @PathVariable("boardId") board: Board?
    ): ResponseEntity<BoardDetailResponse> = board?.let {
        ResponseEntity.ok(
            BoardDetailResponse(
                title = it.title,
                contents = it.contents
            )
        )
    } ?: ResponseEntity.noContent().build()

    @PostMapping("")
    fun saveBoard(
        @RequestBody boardSaveRequest: BoardSaveRequest
    ): ResponseEntity<BoardSaveResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(boardService.saveBoard(boardSaveRequest))

    @PutMapping("/{boardId}")
    fun updateBoard(
        @PathVariable("boardId") board: Board?,
        @RequestBody boardUpdateResponse: BoardUpdateResponse
    ): ResponseEntity<Void> =
        board?.let {
            boardService.updateBoard(board, boardUpdateResponse).let {
                ResponseEntity.ok().build()
            }
        } ?: ResponseEntity.noContent().build()

    @DeleteMapping("/{boardId}")
    fun deleteBoard(
        @PathVariable("boardId") board: Board?
    ): ResponseEntity<Void> =
        board?.let {
            boardService.softDelete(board).let {
                ResponseEntity.ok().build()
            }
        } ?: ResponseEntity.noContent().build()
}
