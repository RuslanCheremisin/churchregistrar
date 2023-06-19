package efant.el.churchregistrar.controller;

import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.service.ChurchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/churches")

public class ChurchController {
    private final ChurchService churchService;

    public ChurchController(ChurchService churchService) {
        this.churchService = churchService;
    }

    @PostMapping("/")
    @Operation(summary = "Добавление новой церкви")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "успешно добавлен",
                    content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<ChurchDTO> addChurch(@RequestBody ChurchDTO churchDTO) {
        churchService.addChurch(churchDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    @Operation(summary = "Внесение в списки членов церкви")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно внесён в списки церкви")
    })
    public ResponseEntity assignMemberToChurch(@RequestParam long churchId,@RequestParam long memberId) {
        churchService.assignMemberToChurch(churchId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary="Получение церкви по её id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Церковь найдена"
            )
    })
    public ResponseEntity<ChurchDTO> getChurchById(@PathVariable("id") long churchId) {
        return ResponseEntity.ok(churchService.getChurchDTOById(churchId));
    }

    @GetMapping("/")
    public ResponseEntity<List<ChurchDTO>> getAllChurchesDTO() {
        return ResponseEntity.ok(churchService.getAllChurchesDTO());
    }


}
