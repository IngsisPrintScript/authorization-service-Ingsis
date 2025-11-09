package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.AuthorizationActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public ResponseEntity<String> createPermissions(String userId, UUID snippetId, AuthorizationActions authorizationActions){
        logger.info("Creating Permission for {} by {} and the action {}", snippetId,userId,authorizationActions);
        permissionRepository.save(new UserPermissions(userId,snippetId, authorizationActions));
        return ResponseEntity.ok().body("Permission created") ;
    }

    public ResponseEntity<List<UUID>> getSnippets(String userId, AuthorizationActions authorizationActions){
        logger.info("Getting Permissions for {} by {}", userId, authorizationActions);
        List<UUID> snippets = permissionRepository
                .findByUserIdAndAction(userId, authorizationActions)
                .stream()
                .map(UserPermissions::getSnippetId)
                .toList();
        return ResponseEntity.ok().body(snippets);
    }

    public ResponseEntity<String> deletePermission(UUID snippetId, String userId) {
        permissionRepository.deleteBySnippetIdAndUserId(snippetId,userId);
        return ResponseEntity.ok().body("Permission deleted") ;
    }

    public ResponseEntity<String> deleteSnippetPermission(UUID snippetId) {
        permissionRepository.deleteBySnippetId(snippetId);
        return ResponseEntity.ok().body("Snippet deleted") ;
    }
}
