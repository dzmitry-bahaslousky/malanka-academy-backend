package by.malanka.academy.api.v1;

import by.malanka.academy.dto.AuthTokenResponseDto;
import by.malanka.academy.dto.register.CreateUserDto;
import by.malanka.academy.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegistrationRestController {
    private final RegisterService registerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthTokenResponseDto register(@Valid @RequestBody CreateUserDto request) {
        return registerService.register(request);
    }

}
