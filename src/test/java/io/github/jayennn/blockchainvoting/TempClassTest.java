package io.github.jayennn.blockchainvoting;

import io.github.jayennn.BlockchainVoting.blockchainvoting.TempClass;
import org.junit.jupiter.api.Test;

public class TempClassTest {
    @Test
    public void test(){
        TempClass tempClass = new TempClass();
        int result = tempClass.addition(9,5);
        System.out.println(result);
    }


}
