package efant.el.churchregistrar.service;

import efant.el.churchregistrar.dao.ChurchAccountDAO;
import efant.el.churchregistrar.dao.ChurchDAO;
import efant.el.churchregistrar.dao.MemberDAO;
import efant.el.churchregistrar.dao.TransactionDAO;
import efant.el.churchregistrar.dto.ChurchAccountDTO;
import efant.el.churchregistrar.dto.TransactionDTO;
import efant.el.churchregistrar.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChurchAccountService {
    private final ChurchAccountDAO churchAccountDAO;
    private final ChurchDAO churchDAO;
    private final MemberDAO memberDAO;
    private final TransactionDAO transactionDAO;

    public ChurchAccountService(ChurchAccountDAO churchAccountDAO, ChurchDAO churchDAO, MemberDAO memberDAO, TransactionDAO transactionDAO) {
        this.churchAccountDAO = churchAccountDAO;
        this.churchDAO = churchDAO;
        this.memberDAO = memberDAO;
        this.transactionDAO = transactionDAO;
    }

    public TransactionDTO doTransaction(Long churchId,
                                        Long memberId,
                                        TransactionDirection transactionDirection,
                                        Long amount,
                                        PurposeCategory purposeCategory,
                                        String commentary) {
        Church church = churchDAO.findById(churchId).get();
        ChurchAccount churchAccount = churchAccountDAO.findById(church.getChurchAccount().getChurchAccountId()).orElseThrow();
        Transaction transaction = new Transaction(
                churchAccount,
                memberDAO.findById(memberId).orElseThrow(),
                transactionDirection,
                amount,
                purposeCategory,
                commentary, LocalDateTime.now());
        if (transactionDirection.equals(TransactionDirection.INCOME_DONATION)) {
            churchAccount.setDeposit(churchAccount.getDeposit() + amount);
        } else {
            if (churchAccount.getDeposit()>=amount){
                churchAccount.setDeposit(churchAccount.getDeposit() - amount);
            }else {
                throw new IllegalArgumentException("Spending amount is more than current deposit!");
            }
        }
        churchAccountDAO.save(churchAccount);
        return transactionToDTO(transactionDAO.save(transaction));
    }

    private TransactionDTO transactionToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getChurchAccount().getChurch().getChurchId(),
                transaction.getMember().getMemberId(),
                transaction.getAmount(),
                transaction.getTransactionDirection(),
                transaction.getPurposeCategory(),
                transaction.getPurposeCommentary());
    }

    public ChurchAccountDTO getAccountDetails(Long accountId) {
        return churchAccountToDT0(churchAccountDAO.findById(accountId).orElseThrow());
    }

    public List<ChurchAccountDTO> getAllChurchAccounts(){
        return churchAccountDAO.findAll().stream().map(this::churchAccountToDT0).collect(Collectors.toList());
    }

    public List<TransactionDTO> getTransactions(Long churchAccountId){
        return churchAccountDAO.findById(churchAccountId).get().getTransactions().stream().map(Transaction::transactionToDTO).collect(Collectors.toList());
    }

    private ChurchAccountDTO churchAccountToDT0(ChurchAccount churchAccount) {
        return new ChurchAccountDTO(
                churchAccount.getChurchAccountId(),
                churchAccount.getChurch().getChurchId(),
                churchAccount.getDeposit());
    }
}
