package com.ingsis.permission.userPermissions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ingsis.permission.userPermissions.dto.AuthorizationActions;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class PermissionServiceTest {

    private PermissionRepository repository;
    private PermissionService service;

    @BeforeEach
    void setup() {
        repository = mock(PermissionRepository.class);
        service = new PermissionService(repository);
    }

    @Test
    void createPermissions_savesEntityAndReturnsMessage() {
        var userId = "u1";
        var sid = UUID.randomUUID();
        var action = AuthorizationActions.ALL;

        var resp = service.createPermissions(userId, sid, action);

        assertThat(resp).isEqualTo("Permission created");

        ArgumentCaptor<UserPermissions> captor = ArgumentCaptor.forClass(UserPermissions.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getUserId()).isEqualTo(userId);
        assertThat(captor.getValue().getSnippetId()).isEqualTo(sid);
        assertThat(captor.getValue().getAction()).isEqualTo(action);
    }

    @Test
    void getSnippets_returnsSnippetIdsFromRepository() {
        var userId = "u2";
        var sid1 = UUID.randomUUID();
        var sid2 = UUID.randomUUID();
        var repoList = List.of(new UserPermissions(userId, sid1, AuthorizationActions.READ),
                new UserPermissions(userId, sid2, AuthorizationActions.READ));
        when(repository.findByUserIdAndAction(userId, AuthorizationActions.READ.name())).thenReturn(repoList);

        var res = service.getSnippets(userId, AuthorizationActions.READ);

        assertThat(res).containsExactly(sid1, sid2);
    }

    @Test
    void deletePermission_delegatesAndReturnsMessage() {
        var sid = UUID.randomUUID();
        var uid = "u3";

        var res = service.deletePermission(sid, uid);

        assertThat(res).isEqualTo("Permission deleted");
        verify(repository).deleteBySnippetIdAndUserId(sid, uid);
    }

    @Test
    void deleteSnippetPermission_delegatesAndReturnsMessage() {
        var sid = UUID.randomUUID();

        var res = service.deleteSnippetPermission(sid);

        assertThat(res).isEqualTo("Snippet deleted");
        verify(repository).deleteBySnippetId(sid);
    }

    @Test
    void getUserIdBySnippetId_returnsEntityFromRepository() {
        var sid = UUID.randomUUID();
        var up = new UserPermissions("owner", sid, AuthorizationActions.ALL);
        when(repository.findBySnippetId(sid)).thenReturn(up);

        var res = service.getUserIdBySnippetId(sid);

        assertThat(res).isSameAs(up);
    }
}
