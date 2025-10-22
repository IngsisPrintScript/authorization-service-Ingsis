package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.Actions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<UserPermissions, UUID> {

    @Query("""
        SELECT p
        FROM UserPermissions p
        WHERE p.userId = :userId
          AND (
                (:action = 'ALL' AND p.action = com.ingsis.permission.userPermissions.dto.Actions.ALL)
             OR (:action <> 'ALL' AND p.action <> com.ingsis.permission.userPermissions.dto.Actions.ALL)
          )
    """)
    List<UserPermissions> findByUserIdAndAction(@Param("userId") String userId,
                                                @Param("action") Actions action);

    @Modifying
    @Transactional
    @Query("""
    DELETE FROM UserPermissions p
    WHERE p.snippetId = :snippetId
      AND p.userId = :userId
""")
    void deleteBySnippetIdAndUserId(
            @Param("snippetId") UUID snippetId,
            @Param("userId") String userId
    );
}
