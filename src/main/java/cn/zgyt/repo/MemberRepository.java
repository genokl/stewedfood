package cn.zgyt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zgyt.basic.bean.Member;


@RepositoryRestResource(path="member")
@Transactional(propagation=Propagation.REQUIRED)
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	/**
	 * 查询父级菜单列表
	 * @param parentId
	 * @return
	 */
	Member findByOpenId(String openId);
	
}
