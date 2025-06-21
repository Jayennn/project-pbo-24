package io.github.jayennn.BlockchainVoting.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String toUUIDString(String mysqlHex) {
        if (mysqlHex == null || !mysqlHex.startsWith("0x") || mysqlHex.length() != 34) {
            throw new IllegalArgumentException("Invalid MySQL hex UUID format: " + mysqlHex);
        }

        String hex = mysqlHex.substring(2).toLowerCase(); // drop 0x
        return String.format("%s-%s-%s-%s-%s",
                hex.substring(0, 8),
                hex.substring(8, 12),
                hex.substring(12, 16),
                hex.substring(16, 20),
                hex.substring(20, 32));
    }

    public static UUID toUUID(String mysqlHex) {
        return UUID.fromString(toUUIDString(mysqlHex));
    }
}
