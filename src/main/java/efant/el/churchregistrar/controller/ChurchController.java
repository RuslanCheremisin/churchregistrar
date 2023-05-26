package efant.el.churchregistrar.controller;

import efant.el.churchregistrar.dto.ChurchDTO;
import efant.el.churchregistrar.service.ChurchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("churches/")

public class ChurchController {
    private final ChurchService churchService;

    public ChurchController(ChurchService churchService) {
        this.churchService = churchService;
    }

    @PostMapping
    public ResponseEntity<ChurchDTO> addChurch(@RequestBody ChurchDTO churchDTO){
        return ResponseEntity.ok(churchService.addChurch(churchDTO));
    }
    @PutMapping
    public ResponseEntity assignMemberToChurch(long churchId, long memberId){
        churchService.assignMemberToChurch(churchId,memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ChurchDTO> getChurchById(long churchId){
        return ResponseEntity.ok(churchService.getChurchDTOById(churchId));
    }
}
