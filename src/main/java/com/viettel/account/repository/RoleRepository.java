package com.viettel.account.repository;

import com.viettel.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    void deleteByAccountId(long savedId);
}
