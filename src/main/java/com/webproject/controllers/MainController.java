package com.webproject.controllers;

import antlr.StringUtils;
import com.webproject.annotations.PassToken;
import com.webproject.annotations.UserLoginToken;
import com.webproject.models.Person;
import com.webproject.models.User;
import com.webproject.repositories.PersonRepository;
import com.webproject.repositories.UserRepository;
import com.webproject.result.Result;
import com.webproject.result.ResultType;
import com.webproject.services.PersonService;
import com.webproject.services.UserService;
import com.webproject.utils.TokenCreator;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @PassToken
    @GetMapping("/register")
    public Result<User> register(@RequestParam(value = "account", required = false) String account,
                                 @RequestParam(value = "pwd", required = false) String pwd) {
        if (account == null || account.length() == 0 || pwd == null || pwd.length() == 0)
            return new Result<>(ResultType.unfill_required_param);
        User one = userService.findUserByAccount(account);
        if (one == null) {
            User user = new User();
            user.setAccount(account);
            user.setPassword(pwd);
            String token = TokenCreator.getToken(user);
            user.setToken(token);
            return new Result<>(ResultType.success, userService.save(user));
        } else {
            return new Result<>(ResultType.duplicate_account);
        }
    }

    @UserLoginToken
    @GetMapping("/userInfo")
    public Result<Person> getUserInfo(@RequestHeader("token") String token) {
        User user = userService.findUserByToken(token);
        if (user!=null){
            Person person = personService.findOnByAccount(user.getAccount());
            if (person!=null){
                return new Result<>(person);
            }
        }
        return new Result<>(ResultType.unkown_error);
    }
}
