package io.github.jayennn.BlockchainVoting.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UpdatableBcryptTest {

    @ParameterizedTest
    @ValueSource(strings = {"haymaker-=12","loremipsum"})
    public void hashingProcess(String inputPass){
        String password = "haymaker-=12";
        UpdatableBcrypt UBcrypt = new UpdatableBcrypt(12);

        String hashedPassword = UBcrypt.hash(password);

        boolean result = UBcrypt.verifyHash(inputPass,hashedPassword);
        System.out.println(result);
    }
}
