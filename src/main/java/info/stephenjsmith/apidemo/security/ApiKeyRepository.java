package info.stephenjsmith.apidemo.security;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ApiKeyRepository {

    private List<String> API_KEYS = new ArrayList<>(){{
        add("ABC");
        add("123");
    }};

    public Optional<Boolean> findOneByKey(String key) {
        if (API_KEYS.contains(key)) {
            return  Optional.of(true);
        }
        return Optional.empty();
    }
}
