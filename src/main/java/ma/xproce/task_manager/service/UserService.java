package ma.xproce.task_manager.service;



import ma.xproce.task_manager.dao.entites.User;
import ma.xproce.task_manager.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

//implements userdetails
@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;




}
