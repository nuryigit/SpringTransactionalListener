package com.ny.listener.basic;

import com.ny.listener.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/remove/{id}")
    public ResponseEntity remove(@PathVariable(value = "id") long id)  {
        userService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/removeOld/{id}")
    public ResponseEntity removeOld(@PathVariable(value = "id") long id)  {
        userService.removeWithOld(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/save/{name}")
    public ResponseEntity remove(@PathVariable(value = "name") String name)  {
        userService.save(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity getUser(@PathVariable(value = "name") String name)  {
        userService.getUser(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/old/{name}")
    public ResponseEntity getUserOld(@PathVariable(value = "name") String name)  {
        userService.getUserOld(name);
        return ResponseEntity.ok().build();
    }
}
