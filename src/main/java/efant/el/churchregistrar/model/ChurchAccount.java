package efant.el.churchregistrar.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "church_accounts")
public class ChurchAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long churchAccountId;
    private Long deposit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "church_id")
    private Church Church;
    @OneToMany(mappedBy = "churchAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public ChurchAccount() {
    }

    public ChurchAccount(Long deposit, Church church) {
        this.deposit = deposit;
        Church = church;
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

    public Church getChurch() {
        return Church;
    }

    public List<Transaction> getTransactions(){
        return List.copyOf(transactions);
    }
}
