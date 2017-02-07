package project.service;

import org.springframework.data.domain.Page;

import project.domain.Token;

public interface TokenService {

Page<Token> getAll();
	
	Token getToken(Long id);
	
	Token getTokenByString(String str);
	
	Token addToken(Token token);
}
