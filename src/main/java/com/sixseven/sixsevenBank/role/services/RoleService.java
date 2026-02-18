package com.sixseven.sixsevenBank.role.services;

import com.sixseven.sixsevenBank.res.Response;
import com.sixseven.sixsevenBank.role.entity.Role;

import java.util.List;

public interface RoleService {

    Response<Role> createRole(Role roleRequest);
    Response<Role> updateRole(Role roleRequest);
    Response<?> deleteRole(Long id);

    Response<List<Role>> getAllRoles();
}
