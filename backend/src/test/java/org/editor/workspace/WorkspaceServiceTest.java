package org.editor.workspace;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkspaceServiceTest {

    @Autowired
    private WorkspaceService workspaceService;

    @MockBean
    private WorkspaceRepository workspaceRepository;

    @Test
    @DisplayName("Test getWorkspaceById")
    public void testGetWorkspaceById() {
        Workspace workspace = new Workspace("1", "", "", "", "admin");
        doReturn(Optional.of(workspace)).when(workspaceRepository).findById("1");

        Optional<Workspace> returnedWorkspace = workspaceService.getWorkspaceById("1");

        Assertions.assertTrue(returnedWorkspace.isPresent(), "NOT FOUND");
        Assertions.assertEquals(returnedWorkspace.get(), workspace, "EQUAL");
    }

    @Test
    @DisplayName("Test saveWorkspace")
    public void testSaveWorkspace() {
        Workspace workspace = new Workspace("1", "", "", "", "admin");
        doReturn(workspace).when(workspaceRepository).save(workspace);

        Workspace savedWorkspace = workspaceService.saveWorkspace(workspace);

        Assertions.assertEquals(savedWorkspace, workspace, "EQUAL");
    }

    @Test
    @DisplayName("Test deleteWorkspaceById")
    public void testDeleteWorkspaceById() {
        Workspace workspace = new Workspace("1", "", "", "", "admin");
        doReturn
    }
}
