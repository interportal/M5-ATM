package md.curs.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import md.curs.model.User;
import md.curs.service.UserService;

@ManagedBean
@ViewScoped
public class UserBean {

	@ManagedProperty("#{userService}")
	private UserService userService;
	private User user;
	private Long id;
	
	public void initUser() {
		user = userService.getUser(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
