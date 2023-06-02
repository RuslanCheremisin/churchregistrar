package efant.el.churchregistrar.model;

public enum TransactionDirection {
    INCOME_DONATION("Приход"), OUTCOME_SPENDING("Расход");
    private String translation;

    TransactionDirection(String translation){
        this.translation = translation;
    }
}
