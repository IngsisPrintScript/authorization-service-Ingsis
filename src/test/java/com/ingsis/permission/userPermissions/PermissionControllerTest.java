package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.CreatePermission;
import com.ingsis.permission.userPermissions.dto.FilterDTO;
import com.ingsis.permission.userPermissions.dto.AuthorizationActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PermissionControllerTest {

    private PermissionService permissionService;
    private PermissionController controller;

    @BeforeEach
    void setup() {
        permissionService = mock(PermissionService.class);
        controller = new PermissionController(permissionService);
    }

    @Test
    void create_callsServiceAndReturnsBody() {
        CreatePermission cp = new CreatePermission("user1", UUID.randomUUID(), AuthorizationActions.READ);
        when(permissionService.createPermissions(cp.userId(), cp.snippetId(), cp.actions())).thenReturn("ok");

        var resp = controller.create(cp);

        assertThat(resp.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(resp.getBody()).isEqualTo("ok");
        verify(permissionService).createPermissions(cp.userId(), cp.snippetId(), cp.actions());
    }

    @Test
    void getSnippetsId_returnsListFromService() {
        var uid = "user2";
        var snippets = List.of(UUID.randomUUID(), UUID.randomUUID());
        var filter = new FilterDTO(AuthorizationActions.ALL);
        when(permissionService.getSnippets(uid, filter.action())).thenReturn(snippets);

        var resp = controller.getSnippetsId(uid, filter);

        assertThat(resp.getBody()).containsExactlyElementsOf(snippets);
        verify(permissionService).getSnippets(uid, filter.action());
    }

    @Test
    void update_delegatesToCreatePermissions() {
        CreatePermission cp = new CreatePermission("u", UUID.randomUUID(), AuthorizationActions.ALL);
        when(permissionService.createPermissions(cp.userId(), cp.snippetId(), cp.actions())).thenReturn("updated");

        var resp = controller.update(cp);

        assertThat(resp.getBody()).isEqualTo("updated");
        verify(permissionService).createPermissions(cp.userId(), cp.snippetId(), cp.actions());
    }

    @Test
    void delete_callsDeletePermission() {
        var uid = "u3";
        var sid = UUID.randomUUID();
        when(permissionService.deletePermission(sid, uid)).thenReturn("deleted");

        var resp = controller.delete(uid, sid);

        assertThat(resp.getBody()).isEqualTo("deleted");
        verify(permissionService).deletePermission(sid, uid);
    }

    @Test
    void deleteSnippet_callsDeleteSnippetPermission() {
        var sid = UUID.randomUUID();
        when(permissionService.deleteSnippetPermission(sid)).thenReturn("sdel");

        var resp = controller.deleteSnippet(sid);

        assertThat(resp.getBody()).isEqualTo("sdel");
        verify(permissionService).deleteSnippetPermission(sid);
    }

    @Test
    void getUserId_returnsUserIdFromService() {
        var sid = UUID.randomUUID();
        var up = new UserPermissions("owner", sid, AuthorizationActions.READ);
        when(permissionService.getUserIdBySnippetId(sid)).thenReturn(up);

        var resp = controller.getUserId(sid);

        assertThat(resp.getBody()).isEqualTo("owner");
        verify(permissionService).getUserIdBySnippetId(sid);
    }
}
