package info.stephenjsmith.apidemo.user;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Repository
public class UserRepo {

    private Integer nextId = 7;
    private Map<Integer, User> users = new TreeMap<>(){{
        put(1, User.builder().id(1).firstName("Ross").lastName("Geller").build());
        put(2,User.builder().id(2).firstName("Phoebe").lastName("Buffay").build());
        put(3,User.builder().id(3).firstName("Joey").lastName("Tribbiani").build());
        put(4,User.builder().id(4).firstName("Monica").lastName("Geller").build());
        put(5,User.builder().id(5).firstName("Chandler").lastName("Bing").build());
        put(6,User.builder().id(6).firstName("Rachel").lastName("Green").build());
    }};

    public List<User> findAll() {
        return users.values().stream().collect(toList());
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public User insert(User user) {
        User newUser = user.toBuilder().id(nextId).build();
        users.put(nextId, newUser);
        nextId++;
        return newUser;
    }

    public User save(User user) {
        return users.replace(user.getId(), user);
    }

    public void deleteById(String id) {
        users.remove(id);
    }
}
