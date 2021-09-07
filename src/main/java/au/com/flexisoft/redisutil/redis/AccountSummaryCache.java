package au.com.flexisoft.redisutil.redis;

import org.springframework.stereotype.Repository;

import au.com.flexisoft.redisutil.dto.Account;
import au.com.flexisoft.redisutil.dto.AccountSummary;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AccountSummaryCache extends RedisValueOperationsCache<AccountSummary> {

    private static final String KEY_NAME = "accountsummaryid";

    public AccountSummary get(String key) {
        return super.get(key);
    }

    @Override
    public void set(AccountSummary account) {
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