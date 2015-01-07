package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Banner;

public interface BannerRepository extends CrudRepository<Banner, Long> {

}
