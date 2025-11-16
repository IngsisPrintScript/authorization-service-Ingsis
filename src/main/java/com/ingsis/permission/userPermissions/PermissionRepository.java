package com.ingsis.permission.userPermissions;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<UserPermissions, UUID> {

  @Query(
      """
      SELECT p
      FROM UserPermissions p
      WHERE p.userId = :userId
        AND (
              (:action = 'ALL' AND p.action = 'ALL')
           OR (:action <> 'ALL' AND p.action <> 'ALL')
        )
      """)
  List<UserPermissions> findByUserIdAndAction(
      @Param("userId") String userId, @Param("action") String action);

  @Modifying
  @Transactional
  @Query(
      """
      DELETE FROM UserPermissions p
      WHERE p.snippetId = :snippetId
        AND p.userId = :userId
      """)
  void deleteBySnippetIdAndUserId(
      @Param("snippetId") UUID snippetId, @Param("userId") String userId);

  @Modifying
  @Transactional
  @Query("""
      DELETE FROM UserPermissions p
      WHERE p.snippetId = :snippetId
      """)
  void deleteBySnippetId(UUID snippetId);

  UserPermissions findBySnippetId(UUID snippetId);
}
