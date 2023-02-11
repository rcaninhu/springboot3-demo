package net.rcan.demo.domain.board

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter


@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    @Transactional
    fun saveBoard(
        boardSaveRequest: BoardSaveRequest
    ): BoardSaveResponse = boardSaveRequest.let {
        Board(
            title = it.title,
            contents = it.contents
        ).run {
            boardRepository.save(this)
        }.let {
            BoardSaveResponse(
                id = it.id,
                title = it.title
            )
        }
    }

    @Transactional(readOnly = true)
    fun searchBoard(
        boardSearchRequest: BoardSearchRequest
    ): List<BoardSearchResponse> =
        boardRepository.findByActiveAndTitleStartsWith(titleStartWidth = boardSearchRequest.title)
            .map {
                BoardSearchResponse(
                    id = it.id,
                    writeDate = it.createdDate!!.format(DateTimeFormatter.ISO_DATE),
                    title = it.title,
                    contents = it.contents
                )
            }

    @Transactional
    fun updateBoard(board: Board, boardUpdateResponse: BoardUpdateResponse) {
        board.copy(
            title = boardUpdateResponse.title,
            contents = boardUpdateResponse.contents
        ).run {
            boardRepository.save(this)
        }
    }

    @Transactional
    fun softDelete(board: Board) {
        board.copy(active = false).run {
            boardRepository.save(this)
        }
    }


}