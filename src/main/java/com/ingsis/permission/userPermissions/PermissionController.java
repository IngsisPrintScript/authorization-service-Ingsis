package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.CreatePermission;
import com.ingsis.permission.userPermissions.dto.FilterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreatePermission permissions) {
        return permissionService.createPermissions(permissions.userId(),permissions.snippetId(),permissions.actions());
    }

    @PostMapping("/getSnippets")
    public ResponseEntity<List<UUID>> getSnippetsId(@RequestParam String userId, @RequestBody FilterDTO filterDTO) {
        return permissionService.getSnippets(userId,filterDTO.actions());
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody CreatePermission permissions) {
        return permissionService.createPermissions(permissions.userId(),permissions.snippetId(),permissions.actions());
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam String userId, @RequestBody UUID snippetId) {
        return permissionService.deletePermission(snippetId, userId);
    }

}
