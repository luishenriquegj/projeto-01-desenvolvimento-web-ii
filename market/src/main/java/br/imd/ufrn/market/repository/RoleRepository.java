package br.imd.ufrn.market.repository;

import br.imd.ufrn.market.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select role from Role role where role.id = ?1")
    Role findOne(Integer id);
}