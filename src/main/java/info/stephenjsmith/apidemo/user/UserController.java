package info.stephenjsmith.apidemo.user;

import info.stephenjsmith.apidemo.rest.IRestController;
import info.stephenjsmith.apidemo.rest.exception.ConflictException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements IRestController<UserDTO, Integer> {

    private final UserService userService;

    @Override
    @ApiModelProperty(name = "Test")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @Override
    public UserDTO getById(Integer id) {
        return userService.getById(id);
    }

    @Override
    public UserDTO updateById(Integer id, UserDTO user) {
        if (id.equals(user.getId())) {
            return userService.updateById(user);
        }
        throw new ConflictException("You cannot update this record");
    }

    @Override
    public void deleteById(Integer id) {
        userService.deleteById(id);
    }
}
