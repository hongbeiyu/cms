
package cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cms {
	// 添加服装
	public void add(cloth cloth) {
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql = "insert into t_cloth(brand, color, size, season) values(?,?,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cloth.getBrand());
				pstmt.setString(2, cloth.getColor());
				pstmt.setDouble(3, cloth.getSize());
				pstmt.setString(4, cloth.getSeason());
				pstmt.execute();
			}finally{                    
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 */
	// 通过id删除服装  
	public void deleteById(long id) {
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql = "delete from t_cloth where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				pstmt.executeUpdate();
			}finally{
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 通过id查询服装 ddl dml
	public cloth queryById(long id) {
		cloth cloth = null;
		try {
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql= "select * from t_cloth where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					String brand = rs.getString("brand");
					double size = rs.getDouble("size");
					String color = rs.getString("color");
					String season = rs.getString("season");
					cloth  = new cloth(id, brand, color, size, season);
				}
			}finally{
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cloth;
	}

	// 查看所有服装信息
	public List<cloth> queryAll() {
		List<cloth> list = new ArrayList<cloth>();
		try {
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql= "select * from t_cloth";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					long id = rs.getLong("id");
					String color = rs.getString("color");
					String brand = rs.getString("brand");
					String season = rs.getString("season");
					double size = rs.getDouble("size");
					cloth cloth = new cloth(id, brand, color, size,season);
					list.add(cloth);
				}
			}finally{
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 修改
	 */
	public void update(cloth cloth) {
		try {
			Connection conn = null;
			PreparedStatement pstmt= null;
			try{
				conn = ConnectionFactory.getConn();
				String sql = "update t_room set code=?,address=?,size=?,price=? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cloth.getBrand());
				pstmt.setString(2, cloth.getColor());
				pstmt.setDouble(3, cloth.getSize());
				pstmt.setString(4, cloth.getSeason());
				pstmt.setLong(5, cloth.getId());
				pstmt.executeUpdate();		
			}finally{
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 菜单
	public void menu() {
		System.out.println("*************************");
		System.out.println("****    服装管理系统          ****");
		System.out.println("**** 1，查看所有服装信息    ****");
		System.out.println("**** 2，添加服装信息           ****");
		System.out.println("**** 3，删除服装信息           ****");
		System.out.println("**** 4，查询服装信息           ****");
		System.out.println("**** 5，修改服装信息           ****");
		System.out.println("**** exit，退出                  ****");
		System.out.println("**** help，帮助                  ****");
		System.out.println("*************************");
	}
	
	public static void main(String[] args) {
		// 创建cms对象
		cms cms = new cms();
		cms.menu(); // 显示主页面
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.print("请输入功能编号：");
				// 等待用户输入功能编号，等用户输入回车的时候获取回车前输入的内容
				String option = scanner.nextLine();
				switch (option) {
				case "1":// 查询所有
					System.out.println("以下是所有服装的信息：");
					List<cloth> stus = cms.queryAll();
					for (cloth stu : stus) {
						System.out.println(stu);
					}
					System.out.println("总计：" + stus.size() + " 件");
					break;
				case "2":// 添加
					while (true) {
						System.out.println("请输入服装信息【brand#color#size#season】或者输入break回到上一级目录");
						String stuStr = scanner.nextLine();
						if (stuStr.equals("break")) {
							break;
						}
						String[] stuArr = stuStr.split("#");
						String brand = stuArr[0];
						String color = stuArr[1];
						double size = Double.parseDouble(stuArr[2]);
						String season = stuArr[3];
						// 封装对象
						cloth stu = new cloth(null, brand, color, size,season);
						cms.add(stu);
						System.out.println("添加成功！");
					}

					break;
				case "3":// 删除
					while (true) {
						System.out.print("请输入您要删除服装的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if (id.equals("break")) {
							break;
						}
						cms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！");
					}
					break;
				case "4":// 查询
					while (true) {
						System.out.print("请输入您要查询服装的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if (id.equals("break")) {
							break;
						}
						cloth stu = cms.queryById(Long.parseLong(id));
						System.out.println("以下是您要查找的服装的信息：");
						System.out.println(stu != null ? stu : "not found!");
					}
					break;
				case "5":// 修改
					while (true) {
						System.out.print("请输入您要修改服装的id或break返回上一级目录:");
						String idStr = scanner.nextLine();
						if (idStr.equals("break")) {
							break;
						}
						//将字符串ID转换为Long
						long id = Long.parseLong(idStr);
						cloth stu = cms.queryById(id);
						if (stu == null) {
							System.out.println("该服装不存在！");
							continue;
						}
						System.out.println("原信息为：" + stu);
						System.out.println("请输入您要修改的信息【code#address#size#price】");
						String stuStr = scanner.nextLine();
						String[] stuArr = stuStr.split("#");

						String color = stuArr[0];
						String brand = stuArr[1];
						double size = Double.parseDouble(stuArr[2]);
						String season = stuArr[3];
						cloth newStu = new cloth(id, brand, color, size,season);
						cms.update(newStu);
						System.out.println("修改成功！");
					}
					break;
				case "help":
					cms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
				}
				
			} catch (Exception e) {
				System.out.println("出错了！"+e.getMessage());
				continue;
			}
		}
	}
}

