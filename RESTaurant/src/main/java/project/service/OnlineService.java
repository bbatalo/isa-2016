package project.service;

import org.springframework.data.domain.Page;

import project.domain.Online;

public interface OnlineService {

	Page<Online> getAll();
	
	Online getOnline(Long id);
	
	Online addOnline(Online online);

}
