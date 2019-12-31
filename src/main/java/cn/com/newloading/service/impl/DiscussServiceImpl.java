package cn.com.newloading.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Discuss;
import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.dto.DiscussDto;
import cn.com.newloading.dao.DiscussDao;
import cn.com.newloading.dao.StudentDao;
import cn.com.newloading.dao.TeacherDao;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.DiscussService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class DiscussServiceImpl implements DiscussService {
	
	@Autowired
	private DiscussDao discussDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public String addDiscuss(Discuss discuss) {
		// TODO Auto-generated method stub
		discuss.setCreateTime(TimeUtil.dateToString(new Date()));
		Integer res = discussDao.addDiscuss(discuss);
		if(res > 0) {
			return "DIS0000";
		}
		return "DIS0001";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<DiscussDto> queryDiscussByCuId(String cuId) {
		// TODO Auto-generated method stub
		List<Discuss> disList = discussDao.queryDiscussByCuId(cuId);
		List<DiscussDto> dtoList = new ArrayList<DiscussDto>();
		for (int i = 0; i < disList.size(); i++) {
			Discuss dis = disList.get(i);
			DiscussDto dto = new DiscussDto();
			if(RoleType.STU.getRole().equals(dis.getForeingType())) {
				Student student = studentDao.queryStuById(dis.getForeignId());
				dto.setRole(student);
			}else if(RoleType.TEA.getRole().equals(dis.getForeingType())) {
				Teacher teacher = teacherDao.queryTeacherById(dis.getForeignId());
				dto.setRole(teacher);
			}
			dto.setDiscuss(dis);
			dtoList.add(dto);
		}
		return dtoList;
	}

}
