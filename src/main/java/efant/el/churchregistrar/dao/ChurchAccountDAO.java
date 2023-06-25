package efant.el.churchregistrar.dao;

import efant.el.churchregistrar.model.ChurchAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChurchAccountDAO extends JpaRepository<ChurchAccount, Long> {
}
