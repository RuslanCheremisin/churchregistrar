package efant.el.churchregistrar.dao;

import efant.el.churchregistrar.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface MemberDAO extends JpaRepository<Member,Long> {
}
