package com.ingsis.permission.userPermissions;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

class PermissionRepositoryTest {

  @Test
  void repositoryInterfaceHasRepositoryAnnotationAndMethods() throws ClassNotFoundException {
    Class<?> repoClass =
        Class.forName("com.ingsis.permission.userPermissions.PermissionRepository");

    assertThat(repoClass.getAnnotation(Repository.class)).isNotNull();

    var methodNames = Arrays.stream(repoClass.getDeclaredMethods()).map(Method::getName).toList();
    assertThat(methodNames)
        .contains(
            "findByUserIdAndAction",
            "deleteBySnippetIdAndUserId",
            "deleteBySnippetId",
            "findBySnippetId");
  }
}
