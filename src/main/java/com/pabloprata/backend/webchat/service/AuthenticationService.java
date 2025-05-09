package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private PsychologistRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByUserEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
