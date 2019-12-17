package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Dict;

public interface DictService {

	/**
	 * 查询字典表
	 * @param dict
	 * @return
	 */
	List<Dict> queryDict(Dict dict);
	
}
