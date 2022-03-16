package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.jurayev.academy.domain.ResponseToken;

public interface TokenRepository extends JpaRepository<ResponseToken, Integer> {

    @Query(value = "select * from api_token order by id desc limit 1", nativeQuery = true)
    ResponseToken getLastToken();
}
