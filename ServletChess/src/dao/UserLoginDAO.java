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
	//�û���¼
	public boolean dologin(String username,String password) {
		con=DBHelper.getConnection();
		try {
			sql=con.prepareStatement("select * from users");
			res=sql.executeQuery();
			while(res.next()){
				if(username.equals(res.getString(1))&&password.equals(res.getString(2))){
					System.out.println("��¼�ɹ���");
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
		System.out.println("��¼ʧ�ܣ�");
		return false;
		
	}
	
	//�û�ע��
	public boolean register(String username,String password) {
			con=helper.DBHelper.getConnection();
			try {
				sql=con.prepareStatement("select * from users");
				res=sql.executeQuery();
				while(res.next()){
					if(username.equals(res.getString(1))){
						System.out.println("�û����Ѵ��ڣ�");
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
			System.out.println("ע���쳣��");
			return false;
	}
}

