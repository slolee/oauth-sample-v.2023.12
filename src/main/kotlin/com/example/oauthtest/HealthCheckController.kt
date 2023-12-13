package com.example.oauthtest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/ping")
    fun ping() = "pong"
}

/**
 *  Pull Request 를 해보고 싶다!
 *  나 A Branch 에서 B Branch 로 코드를 Merge 할건데 해도돼?
 *  : 다른 개발자가, 음.. 근데 이거 좀 수정해야할 것 같아. 여기는 잘못됐어.
 *  : 다른 개발자가 응 해도돼(Approve)
 *  : 최종적으로 Merge 를 하는 겁니다.
 *   - Git Branch
 *      master(main) - A - C
 *          \____ featureA - B - D
 *          \____ featureB - F - G
 *   - Merge
 *      featureA 에서 개발 다했어 이제 이 코드를 main 에 합쳐야겠다!
 *      featureA 를 main 에 머지할거야!
 *      master(main) - A - C
 *          \____ featureA - B - D
 *      master(main) - A - C - B - D
 *          \____ featureA - B - D
 *      허락을 받을 필요없이 머지할 수 있는거에요
 *   - Add & Commit & Push
 *   - Conflict(충돌)
 */