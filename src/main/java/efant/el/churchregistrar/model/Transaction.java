package efant.el.churchregistrar.model;

import efant.el.churchregistrar.dto.TransactionDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(targetEntity = ChurchAccount.class)
    @JoinColumn(name = "church_account_id")
    private ChurchAccount churchAccount;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id")
    private Member member;
    @Enumerated(EnumType.STRING)
    private TransactionDirection transactionDirection;

    private Long amount;
    @Enumerated(EnumType.STRING)
    private PurposeCategory purposeCategory;
    private String purposeCommentary;
    @Column(name = "date_time")
    private LocalDateTime transactionDateTime;

    public Transaction() {

    }

    public Transaction(
            ChurchAccount churchAccount,
            Member member,
            TransactionDirection transactionDirection,
            Long amount,
            PurposeCategory purposeCategory,
            String purposeCommentary,
            LocalDateTime transactionDateTime) {
        this.churchAccount = churchAccount;
        this.member = member;
        this.transactionDirection = transactionDirection;
        this.amount = amount;
        this.purposeCategory = purposeCategory;
        this.purposeCommentary = purposeCommentary;
        this.transactionDateTime = transactionDateTime;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public ChurchAccount getChurchAccount() {
        return churchAccount;
    }

    public void setChurchAccount(ChurchAccount churchAccount) {
        this.churchAccount = churchAccount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public TransactionDirection getTransactionDirection() {
        return transactionDirection;
    }

    public void setTransactionDirection(TransactionDirection transactionDirection) {
        this.transactionDirection = transactionDirection;
    }

    public Long getAmount() {
        return amount;
    }


    public PurposeCategory getPurposeCategory() {
        return purposeCategory;
    }

    public void setPurposeCategory(PurposeCategory purposeCategory) {
        this.purposeCategory = purposeCategory;
    }

    public String getPurposeCommentary() {
        return purposeCommentary;
    }

    public void setPurposeCommentary(String purposeCommentary) {
        this.purposeCommentary = purposeCommentary;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public static TransactionDTO transactionToDTO(Transaction transaction){
        return new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getChurchAccount().getChurchAccountId(),
                transaction.getMember().getMemberId(),
                transaction.amount,
                transaction.getTransactionDirection(),
                transaction.getPurposeCategory(),
                transaction.purposeCommentary);
    }
}
