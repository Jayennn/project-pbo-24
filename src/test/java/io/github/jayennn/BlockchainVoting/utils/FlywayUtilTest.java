package io.github.jayennn.BlockchainVoting.utils;

import org.junit.jupiter.api.Test;

public class FlywayUtilTest {
    @Test
    public void MigrateTest(){
        FlywayUtil.migrate();
    }
}
