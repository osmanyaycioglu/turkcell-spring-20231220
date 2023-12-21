package training.spring.turkcellspring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<UserObj,Long> {

    UserObj findByUsername(String username);

    UserObj findByUsernameAndPasswordOrderByUsername(String username,String password);

    @Query("select u from UserObj u where u.username=?1")
    UserObj searchUsername(String username);

}
