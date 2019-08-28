package sys.qx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sys.qx.dao.C03TableNewUserDaoMapper;
import sys.qx.dao.C03TableUserDaoMapper;
import sys.qx.dao.C03WuXiaoUserDaoMapper;
import sys.qx.dao.C15TableBeanDaoMapper;
import sys.qx.dao.HKBuFenDaoMapper;
import sys.qx.dao.HKSuoYouDaoMapper;
import sys.qx.dao.JDCQCaoQianUserDaoMapper;
import sys.qx.dao.JDCQNoFuKuanUserDaoMapper;
import sys.qx.dao.JDCQZhengQianUserDaoMapper;
import sys.qx.dao.UserDaoMapper;
import sys.qx.model.UserBean;

/**
 * 声明用户
 * @author DaPeng
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static UserBean user;
	
	//供外界调用
	public static UserBean getUserObj() {
		return user;
	}
	
	@Autowired
	private UserDaoMapper userDaoMapper;
	
	@Autowired
	private C03TableNewUserDaoMapper c03TableNewUserDaoMapper;
	
	@Autowired
	private C03TableUserDaoMapper c03TableUserDaoMapper;
	
	@Autowired
	private C03WuXiaoUserDaoMapper c03WuXiaoUserDaoMapper;
	
	@Autowired
	private C15TableBeanDaoMapper c15TableBeanDaoMapper;
	
	@Autowired
	private HKBuFenDaoMapper hKBuFenDaoMapper;
	
	@Autowired
	private HKSuoYouDaoMapper hKSuoYouDaoMapper;
	
	@Autowired
	private JDCQCaoQianUserDaoMapper jDCQCaoQianUserDaoMapper;
	
	@Autowired
	private JDCQNoFuKuanUserDaoMapper jDCQNoFuKuanUserDaoMapper;
	
	@Autowired
	private JDCQZhengQianUserDaoMapper jDCQZhengQianUserDaoMapper;
	
	
	
	
	@RequestMapping("/getUser")
	public String getUser(ModelMap model,HttpServletRequest request ,RedirectAttributes redirectAttributes) {
		
		//获取密码
		String password = request.getParameter("password");
		//获取用户名
		String userName = request.getParameter("name");
		
		//调用service层的
		user = userDaoMapper.getUser(userName);
		System.out.println(user+"------------------------------------");
		//查看是否有此用户
		if(user != null) {
			//看用户名密码是否正确
			if(user.getMypwd().equals(password)) {
				redirectAttributes.addFlashAttribute("userName",userName);
				//一致则给与登录权限
				//判断是普通用户  还是管理员登录   
				if(user.getAuthority() == 0) {
					UtilsUser.setBiaoshi(user.getAuthority());
					System.out.println("管理员："+UtilsUser.getBiaoshi());
					//新建项目
					return "redirect:/newObjectindex.jsp";
				}else {
					UtilsUser.setBiaoshi(user.getAuthority());
					System.out.println("普通用户："+UtilsUser.getBiaoshi());
					return "redirect:/index.jsp";
				}
			}
		}
		System.out.println(user+"-----------------------");
		//不一致还返回登录界面
		return  "redirect:/login.jsp";
	}
	
	
	//修改用户 and  货品编码
	@RequestMapping("/")
	public String  setUser(ModelMap model,HttpServletRequest request ,RedirectAttributes redirectAttributes) {
		return "";
	}
	
	//查询指定用户
	@RequestMapping("/getOneUser")
	public String getOneUser(ModelMap model,HttpServletRequest request ,RedirectAttributes redirectAttributes) {
		//获取用户名
		String userName = request.getParameter("userName");
		UserBean userBean = userDaoMapper.getUser(userName);
		return "ok";
	}
	
	
	//插入新项目用户
	@RequestMapping("/insertNewObjectUser")
	public String insertNewObjectUser(ModelMap model,HttpServletRequest request) {
		String startdata = request.getParameter("userName");
		String storpData = 	request.getParameter("pwd") ;
		//先查询有没有此用户，不能重复
		UserBean userBean1 = userDaoMapper.getUser(startdata);
		if(userBean1 != null) {
			return "addObejctUserChongFu";
		}
		//获取所有用户的  表示，表示自增
		UserBean GetUserBean = userDaoMapper.getMax();
		int biaoshi = GetUserBean.getAuthority();
		biaoshi+=1;
		//增加一条用户
		UserBean userBean = new UserBean();
		userBean.setUserName(startdata);
		userBean.setMypwd(storpData);
		userBean.setAuthority(biaoshi);
		//diaoyong DAO
		userDaoMapper.addUser(userBean);
		return "addObejctOk";
	}
	
	
	
	//删除项目用户
		@RequestMapping("/deleteObjectUser")
		public String deleteObjectUser(ModelMap model,HttpServletRequest request) {
			String startdata = request.getParameter("userName");
			String storpData = 	request.getParameter("pwd") ;
			//查询有没有当前客户
			UserBean userBean = userDaoMapper.getUser(startdata);
			if(userBean != null) {
				//判断密码一致不一致 ture为一致
				if(userBean.getMypwd().equals(storpData) ) {  
					int biaoshi = userBean.getAuthority();
					//删除草签客户中的数据
					jDCQCaoQianUserDaoMapper.deleteBSUser(biaoshi);
					//删除未付款中的客户数据
					jDCQNoFuKuanUserDaoMapper.deleteBSUser(biaoshi);
					//删除正签客户
					jDCQZhengQianUserDaoMapper.deleteBSUser(biaoshi);
					//删除所有回款客户数据
					hKSuoYouDaoMapper.deleteBSUser(biaoshi);
					//删除部分回款客户数据
					hKBuFenDaoMapper.deleteBSUser(biaoshi);
					//删除15中的数据
					c15TableBeanDaoMapper.deleteBSUser(biaoshi);
					//删除c03无效表中的数据
					c03WuXiaoUserDaoMapper.deleteBSUser(biaoshi);
					//删除c03新客户表中的数据
					c03TableNewUserDaoMapper.deleteBSUser(biaoshi);
					//删除c03表中的客户数据
					c03TableUserDaoMapper.deleteBSUser(biaoshi);
					//删除用户表中 指定用户
					userDaoMapper.delOneUser(biaoshi);
				}else {
					//密码不一致
					return "deleteObjectNoPwd";
				}
			}else {
				//返回没有此用户
				return "deleteObjectNoUser";
			}
			//返回删除成功页面
			return "deleteObjectOk";
		}
}
