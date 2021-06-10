package info.stephenjsmith.apidemo.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        return UserDTO.builder()
                .id(source.getId())
                .name(source.getFirstName() + " " + source.getLastName())
                .build();
    }
}
