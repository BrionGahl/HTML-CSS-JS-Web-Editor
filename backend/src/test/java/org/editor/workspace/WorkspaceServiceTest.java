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
        Workspace workspace = new Workspace("635dc73d66472b36fb70d46d", "", "", "", "admin");

        Optional<Workspace> returnedWorkspace = workspaceService.getWorkspaceById("635dc73d66472b36fb70d46d");

        Assertions.assertTrue(returnedWorkspace.isPresent(), "NOT FOUND");
        Assertions.assertEquals(returnedWorkspace.get(), workspace, "EQUAL");
    }
}
