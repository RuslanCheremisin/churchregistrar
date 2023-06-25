package efant.el.churchregistrar.controller;

import efant.el.churchregistrar.dto.ChurchAccountDTO;
import efant.el.churchregistrar.dto.TransactionDTO;
import efant.el.churchregistrar.model.TransactionDirection;
import efant.el.churchregistrar.service.ChurchAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class ChurchAccountController {
    private final ChurchAccountService churchAccountService;

    public ChurchAccountController(ChurchAccountService churchAccountService) {
        this.churchAccountService = churchAccountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChurchAccountDTO> getAccountDetails(@PathVariable(name = "id") Long accountId) {
        return ResponseEntity.ok(churchAccountService.getAccountDetails(accountId));
    }

    @GetMapping("/")
    public ResponseEntity<List<ChurchAccountDTO>> getAllChurchAccounts() {
        return ResponseEntity.ok(churchAccountService.getAllChurchAccounts());
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByChurchAccountId(@PathVariable(name = "id") Long churchAccountId) {
        return ResponseEntity.ok(churchAccountService.getTransactions(churchAccountId));
    }


    @PutMapping("/make-spending")
    public ResponseEntity<TransactionDTO> makeSpending(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(churchAccountService.doTransaction(
                transactionDTO.churchAccountId(),
                transactionDTO.memberId(),
                TransactionDirection.OUTCOME_SPENDING,
                transactionDTO.amount(),
                transactionDTO.purposeCategory(),
                transactionDTO.purposeCommentary()
        ));
    }

    @PutMapping("/receive-donation")
    public ResponseEntity<TransactionDTO> receiveDonation(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(churchAccountService.doTransaction(
                transactionDTO.churchAccountId(),
                transactionDTO.memberId(),
                TransactionDirection.INCOME_DONATION,
                transactionDTO.amount(),
                transactionDTO.purposeCategory(),
                transactionDTO.purposeCommentary()
        ));
    }
}
