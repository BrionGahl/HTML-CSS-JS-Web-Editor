package org.editor.workspace;

import org.editor.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class WorkspaceController {

    Logger logger = LoggerFactory.getLogger(WorkspaceController.class);

    @Autowired
    private WorkspaceService workspaceService;

    @RequestMapping(value = "api/v1/workspace/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkspace(@RequestBody Workspace workspace) {
        logger.info(workspace.toString());
        Workspace created_workspace = workspaceService.saveWorkspace(workspace);
        if (created_workspace == null)
            return new ResponseEntity<>(new ErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.ok(created_workspace);
    }

    @RequestMapping(value = "api/v1/workspace/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWorkspace(@PathVariable String id) {
        logger.info(id);
        workspaceService.deleteWorkspaceById(id);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(value = "api/v1/workspace/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWorkspaceById(@PathVariable String id) {
        Optional<Workspace> found_workspace = workspaceService.getWorkspaceById(id);
        Workspace workspace;
        try {
            workspace = found_workspace.get();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new ErrorResponse("No Such Element Exists"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(workspace);
    }

    @RequestMapping(value = "api/v1/workspace/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getWorkspacesByUsername(@PathVariable String username) throws Exception {
        List<Workspace> workspaceList;
        try {
            workspaceList = workspaceService.getAllByUsername(username);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Bad Request"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(workspaceList);

    }
}
