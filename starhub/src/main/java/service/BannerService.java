package service;

import java.util.List;

import domain.Banner;

public interface BannerService {
	public List<Banner> findAll();

	public Banner findById(Long id);

	public Banner save(Banner contact);
}
