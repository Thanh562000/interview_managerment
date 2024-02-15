package springboot.com.ims.service.impl;

import springboot.com.ims.entity.common.Roles;
import springboot.com.ims.repository.common.RoleRepository;
import springboot.com.ims.service.common.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Roles findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
