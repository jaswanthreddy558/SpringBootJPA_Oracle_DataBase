package springbootoracledatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springbootoracledatabase.entity.Tutorial;
import springbootoracledatabase.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Tutorial> user = userInfoRepository.findByUsername(username);
        return (UserDetails) user.map(Tutorial::new).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
