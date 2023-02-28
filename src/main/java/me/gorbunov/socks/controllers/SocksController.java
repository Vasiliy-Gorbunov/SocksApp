package me.gorbunov.socks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.gorbunov.socks.model.Color;
import me.gorbunov.socks.model.Size;
import me.gorbunov.socks.model.SocksBatch;
import me.gorbunov.socks.model.exceptions.InsufficientQuantityException;
import me.gorbunov.socks.services.impl.SocksServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для носков", description = "CRUD-операции с носками")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат."),
        @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
})
public class SocksController {
    private final SocksServiceImpl socksService;

    public SocksController(SocksServiceImpl socksService) {
        this.socksService = socksService;
    }


    @GetMapping("/")
    @Operation(
            summary = "Получение количества носков на складе",
            description = "Введите параметры носков, чтобы получить их количество на складе"
    )
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа")
    public ResponseEntity<Integer> getSocksCount(@RequestParam Color color,
                                                 @RequestParam Size size,
                                                 @RequestParam(required = false, defaultValue = "0") int minCotton,
                                                 @RequestParam(required = false, defaultValue = "100") int maxCotton) {
        return ResponseEntity.ok().body(socksService.getSocksCount(color, size, minCotton, maxCotton));
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "200", description = "Удалось добавить приход.")
    public ResponseEntity<Void> addSocksToStorage(@RequestBody SocksBatch socks) {
        socksService.addSocks(socks.getSocks().getColor(), socks.getSocks().getSize(), socks.getSocks().getCottonPercent(), socks.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск носков со склада."),
            @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат.")
    })
    public ResponseEntity<Void> releaseSocksFromStorage(@RequestBody SocksBatch socks) {
        try {
            socksService.releaseSocks(socks.getSocks().getColor(), socks.getSocks().getSize(), socks.getSocks().getCottonPercent(), socks.getQuantity());
            return ResponseEntity.ok().build();
        } catch (InsufficientQuantityException e) {
            e.getStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
