package com.bipal.taskcomplete.query;

import com.bipal.taskcomplete.query.model.DoneTask;
import com.bipal.taskcomplete.query.model.RegisteredTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DoneTasksRepository extends JpaRepository<DoneTask, UUID> {

    @Query("SELECT t FROM done_task t WHERE t.groupId = :groupId AND t.processId = :processId")
    List<DoneTask> findAll(@Param("groupId") UUID groupId, @Param("processId") UUID processId);

}
