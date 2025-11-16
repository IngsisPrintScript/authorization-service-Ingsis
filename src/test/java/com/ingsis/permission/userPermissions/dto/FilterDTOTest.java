package com.ingsis.permission.userPermissions.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FilterDTOTest {

    @Test
    void recordHoldsAction() {
        var f = new FilterDTO(AuthorizationActions.ALL);
        assertThat(f.action()).isEqualTo(AuthorizationActions.ALL);
    }
}
