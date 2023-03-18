package net.rcan.demo.domain.board

import net.rcan.demo.common.ApiListResponse
import net.rcan.demo.common.ApiResponse
import org.springframework.data.domain.Pageable
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
    ): ApiResponse<BoardSaveResponse> = boardSaveRequest.let {
        Board(
            title = it.title,
            contents = it.contents
        ).run {
            boardRepository.save(this)
        }.let {
            ApiResponse<BoardSaveResponse>(
                data = BoardSaveResponse(
                    id = it.id,
                    title = it.title
                ),
            )

        }
    }

    @Transactional(readOnly = true)
    fun searchBoard(
        boardSearchRequest: BoardSearchRequest,
        pageable: Pageable
    ): ApiListResponse<BoardSearchResponse> =
        run {
            boardSearchRequest.title?.let {
                boardRepository.findByActiveAndTitleStartsWithOrderByIdDesc(
                    pageable = pageable,
                    titleStartWidth = boardSearchRequest.title
                ).map {
                    BoardSearchResponse(
                        id = it.id,
                        writeDate = it.createdDate!!.format(DateTimeFormatter.ISO_DATE),
                        title = it.title,
                        contents = it.contents
                    )
                }
            } ?: let {
                boardRepository.findByActiveOrderByIdDesc(pageable = pageable)
                    .map {
                        BoardSearchResponse(
                            id = it.id,
                            writeDate = it.createdDate!!.format(DateTimeFormatter.ISO_DATE),
                            title = it.title,
                            contents = it.contents
                        )
                    }
            }
        }.let {
            ApiListResponse(
                data = it.content,
                number = it.number,
                next = it.hasNext(),
                size = it.size,
                numberOfElements = it.numberOfElements
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

    fun init() {
        if( boardRepository.count() < 1){
            Board(title ="안녕하세요", contents = "반갑습니다").also {
                boardRepository.save(it)
            }
        }
    }


}