package org.alexv.vet_manager_api.security.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.security.domain.dto.UserAddResponseDTO;
import org.alexv.vet_manager_api.security.domain.dto.UserInfoDTO;
import org.alexv.vet_manager_api.security.domain.dto.UserInfoDetails;
import org.alexv.vet_manager_api.security.domain.entity.UserInfo;
import org.alexv.vet_manager_api.security.domain.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoService implements UserDetailsService {

    UserInfoRepository userInfoRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);

        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserAddResponseDTO addUser(UserInfoDTO userInfoDTO) {

        UserInfo userInfo = modelMapper.map(userInfoDTO, UserInfo.class);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRoles("ROLE_USER");
        UserInfo savedUser = userInfoRepository.save(userInfo);

        return modelMapper.map(savedUser, UserAddResponseDTO.class);
    }

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}



