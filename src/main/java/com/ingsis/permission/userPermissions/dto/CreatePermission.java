package com.ingsis.permission.userPermissions.dto;

import java.util.UUID;

public record CreatePermission(String userId, UUID snippetId, AuthorizationActions actions) {}
