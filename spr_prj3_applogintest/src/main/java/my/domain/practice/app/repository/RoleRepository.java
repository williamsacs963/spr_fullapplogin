package my.domain.practice.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.domain.practice.app.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
