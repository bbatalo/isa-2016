package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Online;
import project.repository.OnlineRepository;

@Service
public class OnlineServiceImpl implements OnlineService {

	@Autowired
	private OnlineRepository onlineRepository;
	
	@Override
	public Page<Online> getAll() {
		return this.onlineRepository.findAll(new PageRequest(0, 20));
	}

	@Override
	public Online getOnline(Long id) {
		Assert.notNull(id, "ID cannot be null.");
		return this.onlineRepository.findOnline(id);
	}

	@Override
	public Online addOnline(Online online) {
		
		return this.onlineRepository.save(online);
	}


}
