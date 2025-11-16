package com.ingsis.permission.userPermissions.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorizationActionsTest {

    @Test
    void enumContainsExpectedValues() {
        var values = AuthorizationActions.values();
        assertThat(values).contains(AuthorizationActions.ALL, AuthorizationActions.READ);
        assertThat(AuthorizationActions.ALL.name()).isEqualTo("ALL");
    }
}
