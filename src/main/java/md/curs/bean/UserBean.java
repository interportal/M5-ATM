package md.curs.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import md.curs.service.UserService;

@ManagedBean
@ViewScoped
public class UserBean {

	UserService userService;
	
	@Autowired
	public UserBean(UserService userService) {
		this.userService = userService;
	}
	
}
