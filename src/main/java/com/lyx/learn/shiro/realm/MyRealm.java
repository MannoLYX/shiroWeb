package com.lyx.learn.shiro.realm;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.lyx.learn.common.exception.BizException;
import com.lyx.learn.common.util.UserUtil;
import com.lyx.learn.entity.User;
import com.lyx.learn.shiro.token.UserToken;

/**
 * 文件名：MyRealm.java
 * 说明：
 * 作者： 刘育新
 * 创建时间：2016-6-8
 * 版权所有：LYX
 */
public class MyRealm extends AuthorizingRealm
{
	private static final Logger LOGGER = Logger.getLogger(MyRealm.class);
	
	/*
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		LOGGER.info("******* shiro对用户进行登录验证 *******");
		if(null == authcToken)
		{
			try
			{
				throw new BizException("无法获取用户登录的权限令牌！");
			} catch (BizException e)
			{
				e.printStackTrace();
				LOGGER.info("无法获取用户登录的权限令牌！");
			}
		}
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		LOGGER.info("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		
		if(token == null)
		{
			LOGGER.info("当前用户信息为空！");
			return null;
		}
		/*UserToken userToken = (UserToken) authcToken;
		LOGGER.info(UserUtil.printUserInfo(userToken));*/
		
		// 此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
		// 这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
		User user = new User();
		user.setId(9999L);
		user.setUserName("liuyuxin");
		user.setPassword("111111");
		
		LOGGER.info("数据库中用户信息：账号："+user.getUserName()+";密码："+user.getPassword()+";用户ID:"+user.getId());
		
		//shiro在这里对比数据库中取到的账号密码和用户登录的账号密码进行对比
		SimpleAuthenticationInfo info = null;
		if(null != user)
		{
			//也就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)
			info = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),user.getUserName());
			
			UserUtil.setSession("currentUser",user);//设置session的值
		}
		
		return info;
	}
	
	/*
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		LOGGER.info("******* shiro对用户进行权限验证 *******");
		
		if(principals == null)
		{
			LOGGER.info("当前用户信息为空！");
			return null;
		}
		
		
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUserName = (String) super.getAvailablePrincipal(principals);
		
		//通过用户信息去获取当前用户的角色和权限,此处为硬编码
		//设置角色
		List<String> roleList = new ArrayList<String>();
		roleList.add("admin");
		
		//设置权限
		List<String> permissionList = new ArrayList<String>();
		permissionList.add("配置管理");
		permissionList.add("资源管理");
	//	permissionList.add("系统管理");
		
		//为当前用户设置角色和权限 ,即授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roleList);
		info.addStringPermissions(permissionList);
		
		LOGGER.info("当前授权用户名称是：" + currentUserName +",角色是："+roleList.toString()+",用户权限是："+permissionList.toString());
		
		return info;
	}
	
	
	/**
	 * Shiro-1.2.2内置的FilterChain
	 * @see =============================================================================================================================
	 * @see 1)Shiro验证URL时,URL匹配成功便不再继续匹配查找(所以要注意配置文件中的URL顺序,尤其在使用通配符时)
	 * @see   故filterChainDefinitions的配置顺序为自上而下,以最上面的为准
	 * @see 2)当运行一个Web应用程序时,Shiro将会创建一些有用的默认Filter实例,并自动地在[main]项中将它们置为可用
	 * @see   自动地可用的默认的Filter实例是被DefaultFilter枚举类定义的,枚举的名称字段就是可供配置的名称
	 * @see   anon---------------org.apache.shiro.web.filter.authc.AnonymousFilter
	 * @see   authc--------------org.apache.shiro.web.filter.authc.FormAuthenticationFilter
	 * @see   authcBasic---------org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
	 * @see   logout-------------org.apache.shiro.web.filter.authc.LogoutFilter
	 * @see   noSessionCreation--org.apache.shiro.web.filter.session.NoSessionCreationFilter
	 * @see   perms--------------org.apache.shiro.web.filter.authz.PermissionAuthorizationFilter
	 * @see   port---------------org.apache.shiro.web.filter.authz.PortFilter
	 * @see   rest---------------org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
	 * @see   roles--------------org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
	 * @see   ssl----------------org.apache.shiro.web.filter.authz.SslFilter
	 * @see   user---------------org.apache.shiro.web.filter.authz.UserFilter
	 * @see =============================================================================================================================
	 * @see 3)通常可将这些过滤器分为两组
	 * @see   anon,authc,authcBasic,user是第一组认证过滤器
	 * @see   perms,port,rest,roles,ssl是第二组授权过滤器
	 * @see   注意user和authc不同：当应用开启了rememberMe时,用户下次访问时可以是一个user,但绝不会是authc,因为authc是需要重新认证的
	 * @see                      user表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
	 * @see                      说白了,以前的一个用户登录时开启了rememberMe,然后他关闭浏览器,下次再访问时他就是一个user,而不会authc
	 * @see =============================================================================================================================
	 * @see 4)举几个例子
	 * @see   /admin=authc,roles[admin]      表示用户必需已通过认证,并拥有admin角色才可以正常发起'/admin'请求
	 * @see   /edit=authc,perms[admin:edit]  表示用户必需已通过认证,并拥有admin:edit权限才可以正常发起'/edit'请求
	 * @see   /home=user                     表示用户不一定需要已经通过认证,只需要曾经被Shiro记住过登录状态就可以正常发起'/home'请求
	 * @see =============================================================================================================================
	 * @see 5)各默认过滤器常用如下(注意URL Pattern里用到的是两颗星,这样才能实现任意层次的全匹配)
	 * @see   /admins/**=anon             无参,表示可匿名使用,可以理解为匿名用户或游客
	 * @see   /admins/user/**=authc       无参,表示需认证才能使用
	 * @see   /admins/user/**=authcBasic  无参,表示httpBasic认证
	 * @see   /admins/user/**=user        无参,表示必须存在用户,当登入操作时不做检查
	 * @see   /admins/user/**=ssl         无参,表示安全的URL请求,协议为https
	 * @see   /admins/user/**=perms[user:add:*]
	 * @see       参数可写多个,多参时必须加上引号,且参数之间用逗号分割,如/admins/user/**=perms["user:add:*,user:modify:*"]
	 * @see       当有多个参数时必须每个参数都通过才算通过,相当于isPermitedAll()方法
	 * @see   /admins/user/**=port[8081]
	 * @see       当请求的URL端口不是8081时,跳转到schemal://serverName:8081?queryString
	 * @see       其中schmal是协议http或https等,serverName是你访问的Host,8081是Port端口,queryString是你访问的URL里的?后面的参数
	 * @see   /admins/user/**=rest[user]
	 * @see       根据请求的方法,相当于/admins/user/**=perms[user:method],其中method为post,get,delete等
	 * @see   /admins/user/**=roles[admin]
	 * @see       参数可写多个,多个时必须加上引号,且参数之间用逗号分割,如/admins/user/**=roles["admin,guest"]
	 * @see       当有多个参数时必须每个参数都通过才算通过,相当于hasAllRoles()方法
	 * @see =============================================================================================================================
	**/
}
