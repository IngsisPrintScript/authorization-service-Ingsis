package com.ingsis.permission.userPermissions;

import com.ingsis.permission.userPermissions.dto.Actions;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;


import java.util.UUID;

@Entity
public class UserPermissions {
    @Id
    @GeneratedValue()
    private UUID id;

    private String userId;

    private UUID snippetId;

    @Enumerated(EnumType.STRING)
    private Actions action;

    public UserPermissions(String userId, UUID snippetId, Actions action) {
        this.userId = userId;
        this.snippetId = snippetId;
        this.action = action;
    }
    public UserPermissions() {}

    public String getUserId() {
        return userId;
    }

    public UUID getSnippetId() {
        return snippetId;
    }

    public Actions getAction() {
        return action;
    }


}
