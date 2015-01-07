package service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.BannerRepository;
import service.BannerService;

import com.google.common.collect.Lists;

import domain.Banner;

@Service("bannerService")
@Repository
@Transactional
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository repository;

	@Transactional(readOnly = true)
	public List<Banner> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Transactional(readOnly = true)
	public Banner findById(Long id) {
		return repository.findOne(id);
	}

	public Banner save(Banner contact) {
		return repository.save(contact);
	}

}
