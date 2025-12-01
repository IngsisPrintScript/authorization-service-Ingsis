package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.AuthorizationActions;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

  private final PermissionRepository permissionRepository;
  private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);

  public PermissionService(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  public String createPermissions(
      String userId, UUID snippetId, AuthorizationActions authorizationActions) {
    logger.info(
        "Creating Permission for {} by {} and the action {}",
        snippetId,
        userId,
        authorizationActions);
    permissionRepository.save(new UserPermissions(userId, snippetId, authorizationActions));
    return "Permission created";
  }

  public List<UUID> getSnippets(String userId, AuthorizationActions authorizationActions) {
    logger.info("Getting Permissions for {} by {}", userId, authorizationActions);
    return permissionRepository.findByUserIdAndAction(userId, authorizationActions.name()).stream()
        .map(UserPermissions::getSnippetId)
        .toList();
  }

  public String deletePermission(UUID snippetId, String userId) {
    permissionRepository.deleteBySnippetIdAndUserId(snippetId, userId);
    return "Permission deleted";
  }

  public String deleteSnippetPermission(UUID snippetId) {
    permissionRepository.deleteBySnippetId(snippetId);
    return "Snippet deleted";
  }

  public UserPermissions getUserIdBySnippetId(UUID snippetId) {
    return permissionRepository.findBySnippetId(snippetId);
  }
}
