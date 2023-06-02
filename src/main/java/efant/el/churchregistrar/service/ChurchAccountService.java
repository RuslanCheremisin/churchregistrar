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

    public ChurchAccountDTO addAccountToChurch(Long churchId){
        Church church = churchDAO.findById(churchId).get();
        ChurchAccount churchAccount = new ChurchAccount(0L, church);
        church.setChurchAccount(churchAccount);
        churchDAO.save(church);
        return churchAccountToDTO(churchAccountDAO.save(churchAccount));
    }

    private ChurchAccountDTO churchAccountToDTO(ChurchAccount churchAccount) {
        return new ChurchAccountDTO(churchAccount.getChurchAccountId(), churchAccount.getChurch().getChurchId(), churchAccount.getDeposit());
    }

    public TransactionDTO doTransaction(Long churchId,
                                        Long memberId,
                                        TransactionDirection transactionDirection,
                                        Long amount,
                                        PurposeCategory purposeCategory,
                                        String commentary) {
        Church church = churchDAO.findById(churchId).get();
        ChurchAccount churchAccount = churchAccountDAO.findById(church.getChurchAccount().getChurchAccountId()).get();
        Transaction transaction = new Transaction(
                0L,
                churchAccount,
                memberDAO.findById(memberId).get(),
                transactionDirection,
                amount,
                purposeCategory,
                commentary, LocalDateTime.now());
        if (transactionDirection.equals(TransactionDirection.INCOME_DONATION)) {
            churchAccount.setDeposit(churchAccount.getDeposit() + amount);
        } else {
            churchAccount.setDeposit(churchAccount.getDeposit() - amount);
        }
        churchAccountDAO.save(churchAccount);
        return transactionToDTO(transactionDAO.save(transaction));
    }

    public TransactionDTO transactionToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getChurchAccount().getChurch().getChurchId(),
                transaction.getMember().getMemberId(),
                transaction.getAmount(),
                transaction.getTransactionDirection(),
                transaction.getPurposeCategory(),
                transaction.getPurposeCommentary());
    }

//    public ChurchAccountDTO addChurchAccount(ChurchAccountDTO churchAccountDTO) {
//        ChurchAccount churchAccount = new ChurchAccount(churchAccountDTO.churchAccountId(),   churchAccountDTO.deposit());
//
//        churchAccountDAO.save()
//    }
}
