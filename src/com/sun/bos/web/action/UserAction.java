package com.sun.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.bos.domain.User;
import com.sun.bos.service.IUserService;
import com.sun.bos.utils.MD5Utils;
import com.sun.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	@Resource
	private IUserService userService;
	
	//通过属性验证接受验证码
	private String checkcode;
	public void setCheckcode(String checkcode){
		this.checkcode = checkcode;
	}
	
	public String login(){
		//生成的验证码
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		//判断用户输入的验证码是否正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			//验证码正确
			User user = userService.login(model);
			if(user != null){
				//登录成功，将User放入session域，跳转到系统首页
				ServletActionContext.getRequest().getSession().setAttribute("login", user);
				return "home";
			}else{
				//登录失败，设置错误提示信息，跳转到登陆页面
				this.addActionError(this.getText("loginError"));//用户名或密码错误，请重新输入
				return "login";
			}
		}else{
			//验证码错误，设置错误提示信息，跳转到登陆页面
			this.addActionError(this.getText("validateCodeError"));//验证码错误，请重新输入
			return "login";
		}
	}
	
	/**
	 * 用户退出
	 */
	public String logout(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	/**
	 * 修改当前登陆用户密码
	 * @throws IOException
	 */
	public String editPassword() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String password = model.getPassword();//新密码
		password = MD5Utils.md5(password);
		String flag = "1";
		try {
			userService.editPassword(password,user.getId());
		}catch (Exception e){
			//修改密码失败
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
}
