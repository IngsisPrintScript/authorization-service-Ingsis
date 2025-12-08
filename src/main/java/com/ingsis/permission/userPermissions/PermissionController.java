package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.CreatePermission;
import com.ingsis.permission.userPermissions.dto.FilterDTO;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<String> create(@AuthenticationPrincipal Jwt jwt, @RequestBody CreatePermission permissions) {
        String result = permissionService.createPermissions(permissions.userId(), permissions.snippetId(),
                permissions.actions());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getSnippets")
    public ResponseEntity<List<UUID>> getSnippetsId(@AuthenticationPrincipal Jwt jwt, @RequestParam String userId,
            @RequestBody FilterDTO filterDTO) {
        return ResponseEntity.ok(permissionService.getSnippets(userId, filterDTO.action()));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@AuthenticationPrincipal Jwt jwt, @RequestBody CreatePermission permissions) {
        return ResponseEntity.ok(permissionService.createPermissions(permissions.userId(), permissions.snippetId(),
                permissions.actions()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal Jwt jwt, @RequestParam String userId,
            @RequestBody UUID snippetId) {
        return ResponseEntity.ok(permissionService.deletePermission(snippetId, userId));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteSnippet(@AuthenticationPrincipal Jwt jwt, @RequestBody UUID snippetId) {
        return ResponseEntity.ok(permissionService.deleteSnippetPermission(snippetId));
    }

    @GetMapping()
    public ResponseEntity<String> getUserId(@AuthenticationPrincipal Jwt jwt, @RequestParam UUID snippetId) {
        return ResponseEntity.ok(permissionService.getUserIdBySnippetId(snippetId).getUserId());
    }
}
