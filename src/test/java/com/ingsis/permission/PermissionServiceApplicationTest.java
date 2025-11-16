package com.ingsis.permission;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;

class PermissionServiceApplicationTest {

  @Test
  void applicationClassAnnotatedWithSpringBootApplication() {
    assertThat(PermissionServiceApplication.class.getAnnotation(SpringBootApplication.class)).isNotNull();
  }
}
