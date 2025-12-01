package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.CreatePermission;
import com.ingsis.permission.userPermissions.dto.FilterDTO;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

  private final PermissionService permissionService;

  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody CreatePermission permissions) {
    return ResponseEntity.ok(
        permissionService.createPermissions(
            permissions.userId(), permissions.snippetId(), permissions.actions()));
  }

  @PostMapping("/getSnippets")
  public ResponseEntity<List<UUID>> getSnippetsId(
      @RequestParam String userId, @RequestBody FilterDTO filterDTO) {
    return ResponseEntity.ok(permissionService.getSnippets(userId, filterDTO.action()));
  }

  @PostMapping("/update")
  public ResponseEntity<String> update(@RequestBody CreatePermission permissions) {
    return ResponseEntity.ok(
        permissionService.createPermissions(
            permissions.userId(), permissions.snippetId(), permissions.actions()));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> delete(@RequestParam String userId, @RequestBody UUID snippetId) {
    return ResponseEntity.ok(permissionService.deletePermission(snippetId, userId));
  }

  @DeleteMapping()
  public ResponseEntity<String> deleteSnippet(@RequestBody UUID snippetId) {
    return ResponseEntity.ok(permissionService.deleteSnippetPermission(snippetId));
  }

  @GetMapping()
  public ResponseEntity<String> getUserId(@RequestParam UUID snippetId) {
    return ResponseEntity.ok(permissionService.getUserIdBySnippetId(snippetId).getUserId());
  }
}
