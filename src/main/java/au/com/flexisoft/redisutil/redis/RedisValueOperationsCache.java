package au.com.flexisoft.redisutil.redis;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ValueOperations;

public abstract class RedisValueOperationsCache<T> {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, T> valueOperations;

    protected T get(String key) {
        return valueOperations.get(getFullKey(key));
    }

    protected abstract void set(T value);

    protected void set(String key, T value) {
        valueOperations.set(getFullKey(key), value);
    }

    protected void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit) {
        valueOperations.set(getFullKey(key), value, timeout, unit);
    }

    protected void putValueWithExpireTime(String key, T value, Duration duration) {
        valueOperations.set(getFullKey(key), value, duration);
    }

    protected abstract String getKeyName();

    public String getFullKey(String key) {
        return new StringBuilder(getKeyBase()).append(getKeyName()).append(":").append(key).toString();
    }

    protected abstract String getComponent();

    private String getKeyBase() {
        //key format - <environment>:<service>:<keyType>:<key>
        //eg - boil:nis:balancesbydpanid:DA989809879866
        //eg - dev-VTM:vtm:transitaccountid:12342345
        return new StringBuilder("DEV").append(":").append(getComponent()).append(":").toString();
    }


    public void setValueOperations(ValueOperations<String, T> valueOperations) {
        this.valueOperations = valueOperations;
    }
}
