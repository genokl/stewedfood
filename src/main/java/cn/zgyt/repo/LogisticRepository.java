package cn.zgyt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zgyt.basic.bean.Logistic;
import cn.zgyt.basic.bean.Member;

@RepositoryRestResource(path="logistic")
@Transactional(propagation=Propagation.REQUIRED)
public interface LogisticRepository extends JpaRepository<Logistic, Integer>{
	
	Logistic findOneByMemberId(Integer memberid);

}
