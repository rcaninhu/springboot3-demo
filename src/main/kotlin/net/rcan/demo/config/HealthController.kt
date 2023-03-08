package net.rcan.demo.config

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/health")
    fun health() = "OK"

    @GetMapping("/api/alive")
    fun alive() = "OK"
}