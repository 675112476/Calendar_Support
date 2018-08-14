package cn.edu.just.utils;

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

import com.mysql.jdbc.Statement;

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
	/**
     * 将结果集转换成实体对象集合
     * @param res 结果集
     * @param c 实体对象映射类
     * @return
    * @throws SQLException
    * @throws IllegalAccessException
    * @throws InstantiationException
     */
	public static List populate(ResultSet rs,Class cc) throws SQLException, InstantiationException, IllegalAccessException{
         
         //结果集 中列的名称和类型的信息
         ResultSetMetaData rsm = rs.getMetaData();
         int colNumber = rsm.getColumnCount();
         List list = new ArrayList();
         Field[] fields = cc.getDeclaredFields();
         
         //遍历每条记录
         while(rs.next()){
             //实例化对象
             Object obj = cc.newInstance();
             //取出每一个字段进行赋值
             for(int i=1;i<=colNumber;i++){
                 Object value = rs.getObject(i);
                 //匹配实体类中对应的属性
                 for(int j = 0;j<fields.length;j++){
                     Field f = fields[j];
                     if(f.getName().equals(rsm.getColumnName(i))){
                         boolean flag = f.isAccessible();
                         f.setAccessible(true);
                         f.set(obj, value);
                         f.setAccessible(flag);
                         break;
                     }
                 }
                  
             }
             list.add(obj);
         }
         return list;
     }
}