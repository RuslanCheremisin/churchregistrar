package efant.el.churchregistrar.dto;

import efant.el.churchregistrar.model.PurposeCategory;
import efant.el.churchregistrar.model.TransactionDirection;

public record TransactionDTO(
        Long transactionId,
        Long churchAccountId,
        Long memberId,
        Long amount,
        TransactionDirection transactionDirection,
        PurposeCategory purposeCategory,
        String purposeCommentary) {
}
