package ru.base.util;


import org.springframework.util.StringUtils;
import ru.base.model.Role;
import ru.base.model.User;
import ru.base.to.UserTo;

    public class UserUtil {

        public static User createNewFromTo(UserTo userTo) {
            return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(),
                    userTo.getPassword(), Role.ROLE_USER);
        }

        public static UserTo asTo(User user) {
            return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        }


        public static User updateFromTo(User user, UserTo userTo) {
            user.setName(userTo.getName());
            user.setEmail(userTo.getEmail().toLowerCase());
            user.setPassword(userTo.getPassword());
            return user;
        }
        
        


}
