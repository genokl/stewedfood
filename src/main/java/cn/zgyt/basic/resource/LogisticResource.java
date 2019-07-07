package cn.zgyt.basic.resource;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import cn.zgyt.basic.bean.Logistic;

public class LogisticResource extends Resource<Logistic>{

	public LogisticResource(Logistic content) {
		super(content);
	}

	public LogisticResource(Logistic content, List<Link> links) {
		super(content, links);
	}
}
