package net.rcan.demo.domain.study

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@Entity
class Study(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String? = null,
) {


}

interface StudyRepository : JpaRepository<Study, Long>


@Service
class StudyService(
    private val studyRepository: StudyRepository
) {
    @Transactional(readOnly = true)
    fun getAll() = studyRepository.findAll()

    @Transactional
    fun saveStudy(saveStudy: Study) = studyRepository.save(saveStudy)


}

