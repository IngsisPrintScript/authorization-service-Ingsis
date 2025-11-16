package com.ingsis.permission.userPermissions.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AuthorizationActionsTest {

  @Test
  void enumContainsExpectedValues() {
    var values = AuthorizationActions.values();
    assertThat(values).contains(AuthorizationActions.ALL, AuthorizationActions.READ);
    assertThat(AuthorizationActions.ALL.name()).isEqualTo("ALL");
  }
}
