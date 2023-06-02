package efant.el.churchregistrar.model;

import javax.persistence.*;
import java.util.List;
@Entity
public class ChurchAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long churchAccountId;
    private Long deposit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "church_id")
    private Church Church;
    @OneToMany(mappedBy = "churchAccount", cascade = CascadeType.REMOVE)
    private List<Transaction> transactions;

    public ChurchAccount() {
    }

    public ChurchAccount(Long churchAccountId, Long deposit, List<Transaction> transactions) {
        this.churchAccountId = churchAccountId;
        this.deposit = deposit;
        this.transactions = transactions;
    }

    public Long getChurchAccountId(){
        return this.churchAccountId;
    }

    public Long getDeposit(){
        return this.deposit;
    }

    public void setDeposit(Long value){
        this.deposit = value;
    }

    public efant.el.churchregistrar.model.Church getChurch() {
        return Church;
    }
}
