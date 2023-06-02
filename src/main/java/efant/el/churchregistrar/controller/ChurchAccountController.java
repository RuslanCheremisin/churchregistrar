package efant.el.churchregistrar.controller;

import efant.el.churchregistrar.dto.ChurchAccountDTO;
import efant.el.churchregistrar.dto.TransactionDTO;
import efant.el.churchregistrar.model.TransactionDirection;
import efant.el.churchregistrar.service.ChurchAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class ChurchAccountController {
    private final ChurchAccountService churchAccountService;

    public ChurchAccountController(ChurchAccountService churchAccountService) {
        this.churchAccountService = churchAccountService;
    }
    @PostMapping
    public ResponseEntity<?> addChurchAccount(@RequestParam Long churchId){
        return ResponseEntity.ok(churchAccountService.addAccountToChurch(churchId));
    }

    @PutMapping("/make-spending")
    public ResponseEntity<TransactionDTO> makeSpending(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok(churchAccountService.doTransaction(
                transactionDTO.churchId(),
                transactionDTO.memberId(),
                TransactionDirection.OUTCOME_SPENDING,
                transactionDTO.amount(),
                transactionDTO.purposeCategory(),
                transactionDTO.purposeCommentary()
        ));
    }

    @PutMapping("/receive-donation")
    public ResponseEntity<TransactionDTO> receiveDonation(@RequestBody TransactionDTO transactionDTO){
        return ResponseEntity.ok(churchAccountService.doTransaction(
                transactionDTO.churchId(),
                transactionDTO.memberId(),
                TransactionDirection.INCOME_DONATION,
                transactionDTO.amount(),
                transactionDTO.purposeCategory(),
                transactionDTO.purposeCommentary()
        ));
    }
}
