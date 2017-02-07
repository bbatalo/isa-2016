package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.SystemManager;
import project.repository.SysManRepository;

@Service
public class SysManServiceImpl implements SysManService{
	
	@Autowired
	private SysManRepository sysManRepository;
	
	@Override
	public SystemManager addSystemManager(SystemManager sm) {
		return this.sysManRepository.save(sm);
	}

}
