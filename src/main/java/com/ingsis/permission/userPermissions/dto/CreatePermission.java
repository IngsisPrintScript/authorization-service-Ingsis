package com.ingsis.permission.userPermissions.dto;

import java.security.Permissions;
import java.util.UUID;

public record CreatePermission(String userId, UUID snippetId, Actions actions){
}
