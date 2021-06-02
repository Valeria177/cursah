package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laba.training.persist.Model.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
}
