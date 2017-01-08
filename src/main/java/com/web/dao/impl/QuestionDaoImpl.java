package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.QuestionDao;
import com.web.entity.Detail;
import com.web.entity.Question;
import com.web.util.PageUtil;

@Repository
public class QuestionDaoImpl extends CommonDaoPageImpl<Question> implements QuestionDao {

	@Override
	public List<Question> findByDetail(Detail detail) {
		String hql="select dq.question from DetailQuestion dq where dq.detail=:detail";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("detail", detail);
		return super.commonSearch.searchHql(hql, params);
	}

	@Override
	public List<Question> searchPaging(Map<String, Object> params,
			PageUtil<Question> paging) {
		String hql=" from Question";
		if(params==null){
		 
		}else if(params.containsKey("detail")){
			hql="select dq.question from DetailQuestion dq where dq.detail=:detail";
			
		}else if(params.containsKey("major")){
			hql+=" where major=:major";
		}
		return super.commonSearch.searchHql(hql, params,paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="select count(*) from Question";
		if(params!=null&&params.containsKey("detail")){
			hql="select count(*) from DetailQuestion dq where dq.detail=:detail";
			
		}
		if(params!=null&&params.containsKey("major")){
			hql+=" where major=:major";
		}
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<Question> findAll() {
		String hql=" from Question";
		return super.commonSearch.searchHql(hql, null);
	}

	

}
