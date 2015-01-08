package service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Tab> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Tab findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Tab save(Tab contact) {
		return repository.save(contact);
	}

	@Override
	public List<Tab> findByParentId(Long parentId) {
		TypedQuery<Tab> query = em.createNamedQuery("Tab.findByParentId", Tab.class);
		query.setParameter("parentId", parentId);
		return query.getResultList();
	}
	// public SessionFactory getSessionFactory() {
	// return sessionFactory;
	// }
	//
	// @Resource(name = "sessionFactory")
	// public void setSessionFactory(SessionFactory sessionFactory) {
	// this.sessionFactory = sessionFactory;
	// }

}
