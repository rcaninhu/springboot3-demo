package net.rcan.demo.domain.board

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

@Entity
@DynamicUpdate
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val active: Boolean? = true,

    @Column(updatable = false)
    var createdDate: LocalDate? = LocalDate.now(),

    @Column(length = 200)
    val title: String,

    @Column(length = 1000)
    val contents: String,
)

interface BoardRepository : JpaRepository<Board, Long> {
    fun findByActiveAndTitleStartsWithOrderByIdDesc(
        pageable: Pageable,
        active: Boolean? = true,
        titleStartWidth: String?
    ): Slice<Board>

    fun findByActiveOrderByIdDesc(
        pageable: Pageable,
        active: Boolean? = true,
    ): Slice<Board>
}
