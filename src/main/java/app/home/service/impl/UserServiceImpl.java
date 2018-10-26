package app.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import app.home.dao.UserMapper;
import app.home.model.User;
import app.home.service.UserService;
import app.home.vo.LoginVo;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserMapper thisMapper;
	
	public UserMapper getUserMapper() {
		return thisMapper;
	}
	
	@Autowired
	public void setUserMapper(UserMapper thisMapper){
		this.thisMapper = thisMapper;
	}
	
	public List<User> select() {
		return thisMapper.selectAll();
	}

	public List<User> getAll2() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAll3() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer save(User info) {
		info.setUtime(System.currentTimeMillis());
		return thisMapper.updateByPrimaryKeySelective(info);
	}

	public User find(String uid) {
		return thisMapper.selectByPrimaryKey(uid);
	}
	
	public LoginVo findLoginUser(LoginVo loginVo) {
		User user = thisMapper.selectByUserName(loginVo.getAccount());
		if(null != user) {// 使用用户账号对密码进行第二次加密
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String password = md5.encodePassword(loginVo.getPwd(),loginVo.getAccount());
			if((null != (user.getPwd()))&& (user.getPwd().equals(password))){
				loginVo.setUid(user.getUid());
				return loginVo;
			}
		}
		return null;
	}

}
