package services;

import entity.user.User;
import entity.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.math.BigDecimal;

@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo logista não esta autorizado a realizar a transação");
        }

        if (sender.getBalance().compareTo(amount) < 0 ){
            throw new Exception("Saldo Insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow( () -> new Exception("Usuario não encontrado"));
    }


    public void saveUser(User user){
        this.repository.save(user);
    }
}