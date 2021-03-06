package app.home.service;

import java.util.List;

import app.home.model.User;
import app.home.vo.LoginVo;

public interface UserService {

	List<User> select();
	
	User find(String uid);
	
	Integer save(User info);

	List<User> getAll2();

	List<User> getAll3();
	
	LoginVo findLoginUser(LoginVo loginVo);

}
