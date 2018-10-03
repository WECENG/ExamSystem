package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helper.DBHelper;

public class UserLoginDAO {
	public Connection con = null;
	public PreparedStatement sql = null;
	public ResultSet res = null;
	//用户登录
	public boolean dologin(String username,String password) {
		con=DBHelper.getConnection();
		try {
			sql=con.prepareStatement("select * from users");
			res=sql.executeQuery();
			while(res.next()){
				if(username.equals(res.getString(1))&&password.equals(res.getString(2))){
					System.out.println("登录成功！");
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			if(sql!=null){
				try {
					sql.close();
					sql=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(res!=null){
				try {
					res.close();
					res=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("登录失败！");
		return false;
		
	}
	
	//用户注册
	public boolean register(String username,String password) {
			con=helper.DBHelper.getConnection();
			try {
				sql=con.prepareStatement("select * from users");
				res=sql.executeQuery();
				while(res.next()){
					if(username.equals(res.getString(1))){
						System.out.println("用户名已存在！");
						return false;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(res!=null){
					try {
						res.close();
						res=null;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(sql!=null){
					try {
						sql.close();
						sql=null;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				sql=con.prepareStatement("insert into users values(?,?)");
				sql.setString(1,username);
				sql.setString(2, password);
				sql.executeUpdate();
				sql.close();
				con.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("注册异常！");
			return false;
	}
}

