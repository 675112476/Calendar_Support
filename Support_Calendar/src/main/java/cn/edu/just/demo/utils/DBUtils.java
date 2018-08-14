package cn.edu.just.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;


public class DBUtils {
	private static String url;
	private static String userName;
	private static String passWord;
	private static String connector;
	//将具体信息写在一个属性文件中，方便修改
	private static ResourceBundle rb = ResourceBundle.getBundle("cn.edu.just.utils.db_config");
	private DBUtils() {
	}
	//静态加载
	static {
		url=rb.getString("jdbc.url");
		userName=rb.getString("jdbc.username");
		passWord=rb.getString("jdbc.password");
		connector=rb.getString("jdbc.driver");
		try {
			Class.forName(connector);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection = DriverManager.getConnection(url,userName,passWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
		return connection;
	}
	public static void close(Connection connection,Statement statement,ResultSet resultSet) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(resultSet!=null) {
				resultSet.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}