package bootRest.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by aaron on 3/30/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String userId) {
    super("could not find user '" + userId + "'.");
  }
}
