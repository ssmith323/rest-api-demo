package info.stephenjsmith.apidemo.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO source) {
        String[] names = source.getName().split(" ");
        return User.builder()
                .id(source.getId())
                .firstName(names[0])
                .lastName(names[1])
                .build();
    }
}
