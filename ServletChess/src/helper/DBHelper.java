package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	
	//��ȡ���ݿ����Ӷ���
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("���ݿ��������سɹ�");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		String url="jdbc:mysql://localhost:3306/sakila";
		String name="root";
		String password="510183";
		try {
			con=DriverManager.getConnection(url, name, password);
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return con;
	}
}
