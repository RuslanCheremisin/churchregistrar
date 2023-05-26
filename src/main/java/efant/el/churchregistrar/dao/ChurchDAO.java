package efant.el.churchregistrar.dao;

import efant.el.churchregistrar.model.Church;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChurchDAO extends JpaRepository<Church, Long> {
}
