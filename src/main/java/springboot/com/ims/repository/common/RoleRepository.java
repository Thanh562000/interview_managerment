package springboot.com.ims.repository.common;

import springboot.com.ims.entity.common.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(String roleName);


}
