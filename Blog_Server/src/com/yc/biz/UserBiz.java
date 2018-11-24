package com.yc.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.Columns;
import com.yc.bean.User;

import com.yc.dao.DBHelper;

public class UserBiz {

	/**
	 * 登录方法
	 * @param username
	 * @param userpwd
	 * @return  返回登录成功的用户对象
	 */
	
	public User login(String username,String userpwd) throws BizException{
		
		    if(username ==null||username.trim().isEmpty()) {
		          throw new BizException("请填写用户名!");
		          
		    }
		    if(userpwd==null||userpwd.trim().isEmpty()) {
		    	throw new BizException("请填写密码!");
		    }
		    
		    // DBHelper dbhelper=new DBHelper();
		    // List<Object> params=new ArrayList<Object>();
		    // params.add(username);
		    // params.add(userpwd);
		     String sql="select * from user where account = ? and pwd = ?";
		    // List<Map<String,String>> ret=dbhelper.find(sql, params);
		     //Map<String,String> user=dbhelper.findMap(sql, params);
		     return DBHelper.unique(sql, User.class,username,userpwd);
		    // return user;
			
		     
	}

	public List<User> findAll() {
		
		return DBHelper.select("select * from user", User.class);
		
	}

	public void add(User user) throws BizException {
		if(user.getName()==null||user.getName().trim().isEmpty()) {
			throw new BizException("请填写姓名!");
		}
		if(user.getAccount()==null||user.getAccount().trim().isEmpty()) {
			throw new BizException("请填写用户名!");
		}
		String reg1="^[1][3,4,5,7,8][0-9]{9}$";
		if(user.getTel()==null||user.getTel()==reg1) {
			throw new BizException("请填写电话!");
		}
		
		String reg2 = "^[^\\s]{6,16}$";
		if(user.getPwd().length() < 6||user.getPwd()==reg2) {
			throw new BizException("请填写密码!");
		}
		
		String sql="insert into user (name,account,tel,pwd) values(?,?,?,?)";
		DBHelper.insert(sql, user.getName(),user.getAccount(),user.getTel(),user.getPwd());
	}

	public List<Columns> findlm() {
		// TODO Auto-generated method stub
		 return DBHelper.select("select * from columns", Columns.class);
	}

	public void adda(Columns columns) throws BizException {
		// TODO Auto-generated method stub
		if(columns.getColumnName()==null||columns.getColumnName().trim().isEmpty()) {
			throw new BizException("请填写栏目名称!");
		}
		if(columns.getAliasName()==null||columns.getAliasName().trim().isEmpty()) {
			throw new BizException("请填写栏目别名!");
		}
		if(columns.getKeyWords()==null||columns.getKeyWords().trim().isEmpty()) {
			throw new BizException("请填写关键字!");
		}
		if(columns.getDescription()==null||columns.getDescription().trim().isEmpty()) {
			throw new BizException("请填写描述!");
		}
			String sql="insert into columns (columnName,aliasName,parentId,keyWords,description) values(?,?,?,?,?)";
			DBHelper.insert(sql, columns.getColumnName(),columns.getAliasName(),columns.getParentId(),columns.getKeyWords(),columns.getDescription());
		}



	public Object find(User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
				String sql="select * from user where 1=1";
				ArrayList<Object> params=new ArrayList<Object>();
				if(user.getAccount()!=null&&user.getAccount().trim().isEmpty()==false) {
					sql+=" and account like ?";
					params.add("%"+user.getAccount()+"%");
				}
				if(user.getName()!=null&&user.getName().trim().isEmpty()==false) {
					sql+=" and name like ?";
					params.add("%"+user.getName()+"%");
				}
				if(user.getTel()!=null&&user.getTel().trim().isEmpty()==false) {
					sql+=" and tel like ?";
					params.add("%"+user.getTel()+"%");
				}
				return DBHelper.select(sql,params);
	}

	public Object findl(Columns columns) {

		String sql="select * from columns where 1=1";
		ArrayList<Object> params=new ArrayList<Object>();
		if(columns.getColumnName()!=null&&columns.getColumnName().trim().isEmpty()==false) {
			sql+=" and columnName like ?";
			params.add("%"+columns.getColumnName()+"%");
		}
		if(columns.getAliasName()!=null&&columns.getAliasName().trim().isEmpty()==false) {
			sql+=" and aliasName like ?";
			params.add("%"+columns.getAliasName()+"%");
		}
		if(columns.getStatus()!=null&&columns.getStatus().trim().isEmpty()==false) {
			sql+=" and status like ?";
			params.add("%"+columns.getStatus()+"%");
		}
		return DBHelper.select(sql,params);
     }

	public User findById(String id) {
		// TODO Auto-generated method stub
		return DBHelper.unique("select * from user where id=?", User.class,id);
	}

	public void save(User user) throws BizException {
		
		if(user.getAccount()==null||user.getAccount().trim().isEmpty()) {
			throw new BizException("请填写用户名!");
		}
		if(user.getName()==null||user.getName().trim().isEmpty()) {
			throw new BizException("请填写姓名!");
		}
		DBHelper.update("update user set name=?, account=?, tel=? where id=? ", 
				user.getName(),user.getAccount(),user.getTel(),user.getId());
	}

	public Columns findaById(String id) {
		// TODO Auto-generated method stub
		return DBHelper.unique("select * from columns where id=?", Columns.class,id);
	}

	public void savea(Columns columns) throws BizException {
		// TODO Auto-generated method stub
		if(columns.getColumnName()==null||columns.getColumnName().trim().isEmpty()) {
			throw new BizException("请填写栏目名称!");
		}
		if(columns.getAliasName()==null||columns.getAliasName().trim().isEmpty()) {
			throw new BizException("请填写栏目别名!");
		}
		if(columns.getKeyWords()==null||columns.getKeyWords().trim().isEmpty()) {
			throw new BizException("请填写关键字!");
		}
		if(columns.getDescription()==null||columns.getDescription().trim().isEmpty()) {
			throw new BizException("请填写描述!");
		}
		
		DBHelper.update("update columns set columnName=?, aliasName=?, parentId=?, keyWords=?, description=? where id=? ", 
				columns.getColumnName(),columns.getAliasName(),columns.getParentId(),columns.getKeyWords(),columns.getDescription());
	}


	
	}

	

