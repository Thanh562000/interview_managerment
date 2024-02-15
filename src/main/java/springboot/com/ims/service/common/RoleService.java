package springboot.com.ims.service.common;


import springboot.com.ims.entity.common.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> findAll();

    Roles findByRoleName(String roleName);
}
