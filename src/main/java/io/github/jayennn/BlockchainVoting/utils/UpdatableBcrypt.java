package io.github.jayennn.BlockchainVoting.utils;

import org.mindrot.jbcrypt.BCrypt;

public class UpdatableBcrypt {
    private final int costFactor;

    public UpdatableBcrypt(int costFactor){
        this.costFactor = costFactor;
    }

    public String hash(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(costFactor));
    }

    public boolean verifyHash(String password,String hash){
        return BCrypt.checkpw(password,hash);
    }

}
