package com.jarida.server.jaridaserver

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@Validated
@RestController
class ClothController {
    @GetMapping("/cloth")
    fun getFood(): String {
        return "cloth"
    }
}
