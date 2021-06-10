package info.stephenjsmith.apidemo.user;

import info.stephenjsmith.apidemo.rest.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityToDtoConverter entityToDtoConverter;
    private final DtoToEntityConverter dtoToEntityConverter;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(entityToDtoConverter::convert)
                .collect(toList());
    }

    public UserDTO create(UserDTO user) {
        return entityToDtoConverter.convert(userRepository.save(dtoToEntityConverter.convert(user)));
    }

    public UserDTO getById(Integer id) {
        return userRepository.findById(id)
                .map(entityToDtoConverter::convert)
                .orElseThrow(() -> new NotFoundException("" + id));
    }

    public UserDTO updateById(UserDTO user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            return entityToDtoConverter.convert(userRepository.save(dtoToEntityConverter.convert(user)));
        }
        throw new NotFoundException("Not found");
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
