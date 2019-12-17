package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.newloading.bean.Dict;

@Mapper
public interface DictDao {

	/**
	 * 查询字典表
	 * @param dict
	 * @return
	 */
	List<Dict> queryDict(Dict dict);
}
