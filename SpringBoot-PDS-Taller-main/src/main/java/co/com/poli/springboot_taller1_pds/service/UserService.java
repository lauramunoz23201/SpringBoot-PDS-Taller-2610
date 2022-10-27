package co.com.poli.springboot_taller1_pds.service;

import co.com.poli.springboot_taller1_pds.exceptions.UTRException;
import co.com.poli.springboot_taller1_pds.mapper.UserInDTOToUser;
import co.com.poli.springboot_taller1_pds.persistence.entity.User;
import co.com.poli.springboot_taller1_pds.persistence.repository.UserRepository;
import co.com.poli.springboot_taller1_pds.service.dto.UserInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User findById(Integer id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElse(null);
    }

    public User createUser(UserInDTO userInDTO){
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = userInDTO.getBirtDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            throw new UTRException("Minor user", HttpStatus.BAD_REQUEST);
        }
        User user = userInDTOToUser.map(userInDTO);
        return this.userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user, Integer id){
        User user1 = findById(id);
        if(user1 != null){
            user1.setActive(user.getActive());
            user1.setProfile(user.getProfile());
            user1.setDependency(user.getDependency());
            user1.setBirtDate(user.getBirtDate());
            user1.setRows(user.getRows());
            this.userRepository.save(user);
        }
        return user1;
    }

    @Transactional
    public User deleteUser(Integer id){
        User user = findById(id);
        if(user != null){
            this.userRepository.deleteRowByUser(id);
            this.userRepository.deleteById(id);
        }
        return user;
    }
}
