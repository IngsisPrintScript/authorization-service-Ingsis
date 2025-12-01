package com.ingsis.permission.userPermissions.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FilterDTOTest {

    @Test
    void recordHoldsAction() {
        var f = new FilterDTO(AuthorizationActions.ALL);
        assertThat(f.action()).isEqualTo(AuthorizationActions.ALL);
    }
}
