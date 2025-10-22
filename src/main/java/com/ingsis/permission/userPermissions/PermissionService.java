package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.Actions;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public ResponseEntity<String> createPermissions(String userId, UUID snippetId, Actions actions){
        permissionRepository.save(new UserPermissions(userId,snippetId,actions));
        return ResponseEntity.ok().body("Permission created") ;
    }

    public ResponseEntity<List<UUID>> getSnippets(String userId, Actions actions){
        List<UUID> snippets = permissionRepository
                .findByUserIdAndAction(userId, actions)
                .stream()
                .map(UserPermissions::getSnippetId)
                .toList();
        return ResponseEntity.ok().body(snippets);
    }

    public ResponseEntity<String> deletePermission(UUID snippetId, String userId) {
        permissionRepository.deleteBySnippetIdAndUserId(snippetId,userId);
        return ResponseEntity.ok().body("Permission deleted") ;
    }
}
