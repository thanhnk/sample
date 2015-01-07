package service;

import java.util.List;

import domain.Tab;

public interface TabService {
	public List<Tab> findAll();

	public Tab findById(Long id);

	public Tab save(Tab contact);
}
