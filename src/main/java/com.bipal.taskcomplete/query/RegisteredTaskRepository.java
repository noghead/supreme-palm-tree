package com.bipal.taskcomplete.query;

import com.bipal.taskcomplete.query.model.RegisteredTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public interface RegisteredTaskRepository extends JpaRepository<RegisteredTask, UUID> {

    @Query("SELECT t FROM registered_task t WHERE t.groupId = :groupId")
    List<RegisteredTask> findAllByTaskGroup(@Param("groupId") UUID groupId);
}
