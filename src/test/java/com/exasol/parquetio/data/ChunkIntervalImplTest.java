package com.exasol.parquetio.data;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class ChunkIntervalImplTest {
    @Test
    void verifyEqualsContract() {
        EqualsVerifier.forClass(ChunkIntervalImpl.class).verify();
    }
}
