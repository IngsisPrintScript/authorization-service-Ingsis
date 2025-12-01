package com.ingsis.permission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

class PermissionServiceApplicationTest {

  @Test
  void applicationClassAnnotatedWithSpringBootApplication() {
    assertThat(PermissionServiceApplication.class.getAnnotation(SpringBootApplication.class))
        .isNotNull();
  }
}
