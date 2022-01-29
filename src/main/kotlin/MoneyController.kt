
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@Validated
@RestController
class MoneyController {
    @GetMapping("/food")
    fun getFood(): String {
        return "food"
    }
}