package com.ingsis.permission.userPermissions.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePermissionTest {

    @Test
    void recordHoldsValues() {
        var id = UUID.randomUUID();
        var r = new CreatePermission("u", id, AuthorizationActions.READ);

        assertThat(r.userId()).isEqualTo("u");
        assertThat(r.snippetId()).isEqualTo(id);
        assertThat(r.actions()).isEqualTo(AuthorizationActions.READ);
    }
}
