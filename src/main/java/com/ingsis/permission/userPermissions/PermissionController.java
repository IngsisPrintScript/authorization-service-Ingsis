package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.CreatePermission;
import com.ingsis.permission.userPermissions.dto.FilterDTO;
import com.nimbusds.jwt.JWT;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<String> create(@AuthenticationPrincipal JWT jwt, @RequestBody CreatePermission permissions) {
        return ResponseEntity.ok(permissionService.createPermissions(permissions.userId(), permissions.snippetId(),
                permissions.actions()));
    }

    @PostMapping("/getSnippets")
    public ResponseEntity<List<UUID>> getSnippetsId(@AuthenticationPrincipal JWT jwt, @RequestParam String userId,
            @RequestBody FilterDTO filterDTO) {
        return ResponseEntity.ok(permissionService.getSnippets(userId, filterDTO.action()));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@AuthenticationPrincipal JWT jwt, @RequestBody CreatePermission permissions) {
        return ResponseEntity.ok(permissionService.createPermissions(permissions.userId(), permissions.snippetId(),
                permissions.actions()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal JWT jwt, @RequestParam String userId,
            @RequestBody UUID snippetId) {
        return ResponseEntity.ok(permissionService.deletePermission(snippetId, userId));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteSnippet(@AuthenticationPrincipal JWT jwt, @RequestBody UUID snippetId) {
        return ResponseEntity.ok(permissionService.deleteSnippetPermission(snippetId));
    }

    @GetMapping()
    public ResponseEntity<String> getUserId(@AuthenticationPrincipal JWT jwt, @RequestParam UUID snippetId) {
        return ResponseEntity.ok(permissionService.getUserIdBySnippetId(snippetId).getUserId());
    }
}
