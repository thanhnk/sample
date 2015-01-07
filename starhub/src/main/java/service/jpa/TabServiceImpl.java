package service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.TabRepository;
import service.TabService;

import com.google.common.collect.Lists;

import domain.Tab;

@Service("tabService")
@Repository
@Transactional
public class TabServiceImpl implements TabService {
	@Autowired
	private TabRepository repository;

	@Transactional(readOnly = true)
	public List<Tab> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Transactional(readOnly = true)
	public Tab findById(Long id) {
		return repository.findOne(id);
	}

	public Tab save(Tab contact) {
		return repository.save(contact);
	}

}
