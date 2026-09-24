package org.TMS.Repository;

import org.TMS.Entity.Task;
import org.TMS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findByTitle(String title);

    List<Task> findByUser(User user);
}
