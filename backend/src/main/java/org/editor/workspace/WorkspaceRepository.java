package org.editor.workspace;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkspaceRepository extends MongoRepository<Workspace, String> {

    @Query("{username:'?0'}")
    Iterable<Workspace> findAllByUsername(String username);
}
