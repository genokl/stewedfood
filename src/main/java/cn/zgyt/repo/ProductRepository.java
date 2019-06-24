package cn.zgyt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.zgyt.basic.bean.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product>{

	
//	List<Product> findByProductid(Integer isDel);
}
