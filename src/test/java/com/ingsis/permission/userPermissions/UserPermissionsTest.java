package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.AuthorizationActions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserPermissionsTest {

    @Test
    void constructorAndGettersWork() {
        var uid = "alice";
        var sid = UUID.randomUUID();
        var up = new UserPermissions(uid, sid, AuthorizationActions.READ);

        assertThat(up.getUserId()).isEqualTo(uid);
        assertThat(up.getSnippetId()).isEqualTo(sid);
        assertThat(up.getAction()).isEqualTo(AuthorizationActions.READ);
    }
}
