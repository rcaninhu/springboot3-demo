package net.rcan.demo.web

import net.rcan.demo.domain.study.Study
import net.rcan.demo.domain.study.StudyService
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/study")
@RestController
class StudyController(
    private val studyService: StudyService
) {
    @GetMapping("")
    fun getAll() = studyService.getAll()

    @PostMapping("")
    fun saveStudy(
        @RequestBody
        saveStudy: Study
    ) = studyService.saveStudy(saveStudy)

}