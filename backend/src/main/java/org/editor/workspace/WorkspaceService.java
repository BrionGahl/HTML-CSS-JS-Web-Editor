package org.editor.workspace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {
    Logger logger = LoggerFactory.getLogger(WorkspaceService.class);
    @Autowired
    WorkspaceRepository workspaceRepo;

    public List<Workspace> getAllByUsername(String username) throws Exception {
        Iterable<Workspace> iterWorkspaces = workspaceRepo.findAllByUsername(username);
        List<Workspace> workspaces = new ArrayList<>();
        iterWorkspaces.forEach(workspaces::add);

        if (workspaces.isEmpty()) {
            throw new Exception();
        }

        return workspaces;
    }

    public Workspace saveWorkspace(Workspace workspace) {
        return workspaceRepo.save(workspace);
    }

    public String deleteWorkspaceById(String id) {
        workspaceRepo.deleteById(id);
        return id;
    }

    public Optional<Workspace> getWorkspaceById(String id) {
        return workspaceRepo.findById(id);
    }
}
