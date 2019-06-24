package cn.zgyt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zgyt.basic.bean.ProductType;


@RepositoryRestResource(path="productType")
@Transactional(propagation=Propagation.REQUIRED)
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>,JpaSpecificationExecutor<ProductType>{
	
	/**
	 * 查询父级菜单列表
	 * @param parentId
	 * @return
	 */
	List<ProductType> findByIsDelOrderByOrderValAsc(Integer isDel);
	
}
