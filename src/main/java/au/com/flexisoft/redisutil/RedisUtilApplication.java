package au.com.flexisoft.redisutil;

import au.com.flexisoft.redisutil.dto.Account;
import au.com.flexisoft.redisutil.dto.AccountSummary;
import au.com.flexisoft.redisutil.processor.JsonProcessor;
import au.com.flexisoft.redisutil.redis.AccountCache;
import au.com.flexisoft.redisutil.redis.AccountSummaryCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RedisUtilApplication implements CommandLineRunner {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JsonProcessor jsonProcessor;

	@Autowired
	private AccountCache accountCache;
	@Autowired
	private AccountSummaryCache accountSummaryCache;

	@Value("${cache.action.populate}")
	private boolean populateCaches;
	@Value("${cache.action.read}")
	private boolean readCaches;

	@Value("${cache.account}")
	private boolean populateAccount;
	@Value("${cache.accountSummary}")
	private boolean populateAccountSummary;


	public static void main(String[] args) {
		SpringApplication.run(RedisUtilApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		testRun();

		System.out.println(populateAccount);
		System.out.println(populateAccountSummary);

		if (populateCaches) {
			populateCache();
		}
		if (readCaches) {
			readCache();
		}
	}

	public void populateCache() throws IOException {
		if (populateAccount) {
			List<Account> accounts = jsonProcessor.retrieveData("src/main/resources/json/accounts.json", Account.class);
			accounts.forEach(a-> {
				accountCache.set(a);
			});
		}

		if (populateAccountSummary) {
			List<AccountSummary> accountSummaries = jsonProcessor.retrieveData("src/main/resources/json/accountSummaries.json", AccountSummary.class);
			accountSummaries.forEach(s -> {
				accountSummaryCache.set(s);
			});
		}
	}

	public void readCache() {
		Set keys = redisTemplate.keys("*");

		keys.forEach(System.out::println);

		if (populateAccount) {
			Account account = accountCache.get("1");
			System.out.println("ACCOUNT: "+account);
		}
		if(populateAccountSummary) {
			AccountSummary accountSummary = accountSummaryCache.get("99");
			System.out.println("ACCOUNT SUMMARY:"+accountSummary);
		}
	}

	private void testRun() throws IOException {
		List<Account> accounts = jsonProcessor.retrieveData("src/main/resources/json/accounts.json", Account.class);

		List<AccountSummary> accountSummaries = jsonProcessor.retrieveData("src/main/resources/json/accountSummaries.json", AccountSummary.class);


		System.out.println("********storing in cache ***********");
		accounts.forEach(a-> {
			accountCache.set(a);
		});
		System.out.println("********End ofstoring in cache ***********");

		System.out.println("*****************Reading from Cache ***********");
		accounts.forEach(acc -> {
			Account account = accountCache.get(String.valueOf(acc.getId()));
			System.out.println(account);
		});
		System.out.println("*****************End of Reading from Cache ***********");
	}
}

