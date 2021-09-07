package au.com.flexisoft.redisutil.redis;

import org.springframework.stereotype.Repository;

import au.com.flexisoft.redisutil.dto.Account;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AccountCache extends RedisValueOperationsCache<Account> {

    private static final String KEY_NAME = "accountid";

    public Account get(String key) {
        return super.get(key);
    }

    @Override
    public void set(Account account) {
        super.set(String.valueOf(account.getId()), account);
    }

    protected String getKeyName() {
        return KEY_NAME;
    }

    @Override
    public String getFullKey(String key) {
        return super.getFullKey(key);
    }

    @Override
    protected String getComponent() {
        return "VTM";
    }
}
