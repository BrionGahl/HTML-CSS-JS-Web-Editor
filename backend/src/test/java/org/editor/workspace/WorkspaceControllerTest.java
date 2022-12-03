package org.editor.workspace;

import org.editor.model.ErrorResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkspaceControllerTest {

    @Autowired
    WorkspaceController workspaceController;

    @MockBean
    WorkspaceService workspaceService;

    @Test
    @DisplayName("Test saveWorkspaceSuccess")
    public void testSaveWorkspaceSuccess() {
        Workspace workspace = new Workspace("1", "", "", "", "", "");
        doReturn(workspace).when(workspaceService).saveWorkspace(workspace);

        Assertions.assertEquals(ResponseEntity.ok(workspace), workspaceController.saveWorkspace(workspace));
    }

    @Test
    @DisplayName("Test saveWorkspaceFail")
    public void testSaveWorkspaceFail() {
        Workspace workspace = new Workspace("1", "", "", "", "", "");
        doReturn(null).when(workspaceService).saveWorkspace(workspace);

        Assertions.assertEquals(new ResponseEntity<>(new ErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR), workspaceController.saveWorkspace(workspace));
    }

    @Test
    @DisplayName("Test deleteWorkspace")
    public void testDeleteWorkspace() {
        Assertions.assertEquals(ResponseEntity.ok("1"), workspaceController.deleteWorkspace("1"));
    }

    @Test
    @DisplayName("Test getWorkspaceByIdSuccess")
    public void testGetWorkspaceByIdSuccess() {
        Workspace workspace = new Workspace("1", "", "", "", "", "");
        doReturn(Optional.of(workspace)).when(workspaceService).getWorkspaceById("1");

        Assertions.assertEquals(ResponseEntity.ok(workspace), workspaceController.getWorkspaceById("1"));
    }

    @Test
    @DisplayName("Test getWorkspaceByIdFail")
    public void testGetWorkspaceByIdFail() {
        Assertions.assertEquals(new ResponseEntity<>(new ErrorResponse("No Such Element Exists"), HttpStatus.NOT_FOUND), workspaceController.getWorkspaceById("1"));
    }

    @Test
    @DisplayName("Test getWorkspacesByUsernameSuccess")
    public void testGetWorkspacesByUsernameSuccess() throws Exception {
        Workspace workspace1 = new Workspace("1", "", "", "", "", "admin");
        Workspace workspace2 = new Workspace("2", "", "", "", "", "admin");
        doReturn(Arrays.asList(workspace1, workspace2)).when(workspaceService).getAllByUsername("admin");

        List<Workspace> workspaceList = Arrays.asList(workspace1, workspace2);

        Assertions.assertEquals(ResponseEntity.ok(workspaceList), workspaceController.getWorkspacesByUsername("admin"));
    }

    @Test
    @DisplayName("Test getWorkspacesByUsernameFail")
    public void testGetWorkspacesByUsernameFail() throws Exception {
        List<Workspace> emptyList = new ArrayList<>();
        doThrow(new Exception()).when(workspaceService).getAllByUsername("admin");

        Assertions.assertEquals(new ResponseEntity<>(new ErrorResponse("Bad Request"), HttpStatus.BAD_REQUEST), workspaceController.getWorkspacesByUsername("admin"));
    }
}
