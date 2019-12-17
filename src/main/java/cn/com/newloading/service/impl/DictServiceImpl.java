package cn.com.newloading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.Dict;
import cn.com.newloading.dao.DictDao;
import cn.com.newloading.service.DictService;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictDao dictDao;
	
	@Override
	public List<Dict> queryDict(Dict dict) {
		// TODO Auto-generated method stub
		return dictDao.queryDict(dict);
	}

}
