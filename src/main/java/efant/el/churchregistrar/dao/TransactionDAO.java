package efant.el.churchregistrar.dao;

import efant.el.churchregistrar.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transaction, Long> {
}
