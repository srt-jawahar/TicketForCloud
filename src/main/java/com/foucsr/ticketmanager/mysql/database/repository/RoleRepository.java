package com.foucsr.ticketmanager.mysql.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foucsr.ticketmanager.mysql.database.model.Role;

/**
 * Created by FocusR on 29-Sep-2019.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Optional<Role> findByName(String roleName);
    
    @Query(value="SELECT * FROM USER_ROLES WHERE id=:id",nativeQuery = true)
	Role findRolesById(@Param("id") Long id);
}
