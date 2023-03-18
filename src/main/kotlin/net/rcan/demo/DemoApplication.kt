package net.rcan.demo

import jakarta.annotation.PostConstruct
import net.rcan.demo.domain.board.BoardService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication(
    private val boardService: BoardService
){
    @PostConstruct
    fun init(){
        boardService.init();
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
