package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Token;
import project.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Page<Token> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Token getToken(Long id) {
		Assert.notNull(id);
		return tokenRepository.findTokenById(id);
	}

	@Override
	public Token getTokenByString(String str) {
		Assert.notNull(str);
		return tokenRepository.findTokenByString(str);
	}

	@Override
	public Token addToken(Token token) {
		return tokenRepository.save(token);
	}

}
