package Code2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUDoperations 
{
	static Connection con=null;
	static PreparedStatement pst=null;
	static Scanner sc=new Scanner(System.in);
	
	void insertData() throws SQLException
	{
		
		String query1="insert into product(name,category,price,brand,rating) values (?, ?, ?, ?, ?)";
		 pst=con.prepareStatement(query1);
		 
		 System.out.println("Enter no of records");
		 int n=sc.nextInt();
		 for(int i=1;i<=n;i++)
		 {
		//System.out.println("Enter slno");
		//Integer slno=sc.nextInt();
		//pst.setInt(1,sc.nextInt());
		
		System.out.println("Enter name");
		//String name=sc.next();
		pst.setString(1,sc.next());
		
		System.out.println("Enter category");
        //String category=sc.nextLine();
		pst.setString(2, sc.next());
		
		System.out.println("Enter price");
		//Integer price=sc.nextInt();
		pst.setInt(3,sc.nextInt());
		
		System.out.println("Enter brand");
		//String brand=sc.next();
		pst.setString(4,sc.next());
		
		System.out.println("Enter rating");
	    //Float rating=sc.nextFloat();
		pst.setFloat(5,sc.nextFloat());
		
		int r=pst.executeUpdate();
        if(r!=0)
		{
			System.out.println("data inserted into table successfully");
		}
		else
		{
			System.out.println("data not inserted into table successfully");
		}
		 }
	}
	void retrieveData()throws SQLException
	{
		System.out.println("Select all products whose price is less than or equal to 1000.");
		System.out.println("---------------------------------------------------------------");
		String query2="select * from product where price <= 1000";
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        System.out.println("slno\tname\tcategory\tprice\tbrand\trating");


		  while(rs.next())
		  {
			   System.out.print(rs.getInt("slno")+"\t");
			   System.out.print(rs.getString("name")+"\t"); 
			   System.out.print(rs.getString("category")+"\t");
			   System.out.print(rs.getInt("price")+"\t");
			   System.out.print(rs.getString("brand")+"\t");
			   System.out.print(rs.getFloat("rating")+"\t");
			   System.out.println();
		  }
	}
	void retrieveData2()throws SQLException
	{
		System.out.println("Print all the products whose rating is greater than 4.5");
		System.out.println("---------------------------------------------------------");
		String query3="select * from product where rating > 4.5";
	    Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query3);
        System.out.println("slno\tname\tcategory\tprice\tbrand\trating");
        while(rs.next())
		  {
			   System.out.print(rs.getInt("slno")+"\t");
			   System.out.print(rs.getString("name")+"\t"); 
			   System.out.print(rs.getString("category")+"\t");
			   System.out.print(rs.getInt("price")+"\t");
			   System.out.print(rs.getString("brand")+"\t");
			   System.out.print(rs.getFloat("rating")+"\t");
			   System.out.println();
		  }
		
	}
	void updateData()throws SQLException
	{
		System.out.println("Update price of SmartWatch to 15000 where no=9");
		System.out.println("------------------------------------------------");
		String query4="update  product SET price=15000 where slno=9";
		PreparedStatement pst=con.prepareStatement(query4);
		int r=pst.executeUpdate();
		if(r>0)
		{
			System.out.println("SmartWatch price updated successfully");
		}
		else
		{
			System.out.println("Not updated");
		}
	}
	void deleteData()throws SQLException
	{
		System.out.println("delete products whose price is less than  100");
		System.out.println("----------------------------------------------");
        String query5="delete  from product where price < 100";
		pst=con.prepareStatement(query5);
		int r=pst.executeUpdate();
		
		if(r >0)
		{
			System.out.println(" product deleted successfully");
		}
		else
		{
			System.out.println("Product not deleted");
		}
	}
	public static void main(String[] args) 
	{
		CRUDoperations co=new CRUDoperations();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			String url="jdbc:mysql://localhost:3306/database1"; 
			String username="root";
			String password="eeshita113";  
			con=DriverManager.getConnection(url, username, password);
			//System.out.println(con);
			int choice;
			do
			{
				System.out.println("Enter your choice");
				System.out.println("1.Insert");
				System.out.println("2.Retrieve");
				System.out.println("3.Retrieve2");
				System.out.println("4.Update");
				System.out.println("5.Delete");
				choice=sc.nextInt();
				switch(choice)
				{
				case 1:
					co.insertData();
					break;
				case 2:
					co.retrieveData();
					break;
				case 3:
					co.retrieveData2();
					break;
				case 4:
					co.updateData();
					break;
				case 5:
					co.deleteData();
					break;
				}
			}
			while(choice!=5);
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.toString();
		}
		finally
		{
			 
			try
			{
				
				con.close(); 
			}
		    catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
	
/*
 * OUTPUT
 * com.mysql.cj.jdbc.ConnectionImpl@72c8e7b
Enter your choice
1.Insert
2.Retrieve
3.Retrieve2
4.Update
5.Delete
1
Enter no of records
14
Enter name
BlueShirt
Enter category
Clothing
Enter price
750
Enter brand
Denim
Enter rating
3.8
data inserted into table successfully
==================================
Enter name
BlueJeans
Enter category
Clothing
Enter price
800
Enter brand
puma
Enter rating
3.6
data inserted into table successfully
=====================================
Enter name
BlackJeans
Enter category
Clothing
Enter price
750
Enter brand
Denim
Enter rating
4.5
data inserted into table successfully
======================================
Enter name
BlueShirt
Enter category
Clothing
Enter price
1000
Enter brand
Puma
Enter rating
4.3
data inserted into table successfully
=====================================
Enter name
ChocolateCake
Enter category
Food
Enter price
25
Enter brand
Britannia
Enter rating
3.7
data inserted into table successfully
=====================================
Enter name
StrawberryCake
Enter category
Food
Enter price
60
Enter brand
Cadbury
Enter rating
4.1
data inserted into table successfully
=====================================
Enter name
ChocolateCake
Enter category
Food
Enter price
60
Enter brand
Cadbury
Enter rating
2.5
data inserted into table successfully
=====================================
Enter name
StrawberryCake
Enter category
Food
Enter price
10
Enter brand
Britannia
Enter rating
4.6
data inserted into table successfully
=====================================
Enter name
SmartWatch
Enter category
Gadgets
Enter price
17000
Enter brand
Apple
Enter rating
4.9
data inserted into table successfully
=====================================
Enter name
SmartCam
Enter category
Gadgets
Enter price
2600
Enter brand
Realme
Enter rating
4.7
data inserted into table successfully
=====================================
Enter name
SmartTv
Enter category
Gadgets
Enter price
40000
Enter brand
Sony
Enter rating
4
data inserted into table successfully
=====================================
Enter name
SmartBand
Enter category
Gadgets
Enter price
3000
Enter brand
Realme
Enter rating
4.6
data inserted into table successfully
=====================================
Enter name
RawCashew
Enter category
Food
Enter price
370
Enter brand
Absa
Enter rating
3.9
data inserted into table successfully
=====================================
Enter name
CashewNuts
Enter category
Food
Enter price
550
Enter brand
Upcrop
Enter rating
4.3
data inserted into table successfully
=====================================
Enter your choice
1.Insert
2.Retrieve
3.Retrieve2
4.Update
5.Delete
2
Select all products whose price is less than or equal to 1000.
---------------------------------------------------------------
slno	name	category	price	brand	rating
1	BlueShirt	Clothing	750	Denim	3.8	
2	BlueShirt	Clothing	750	Denim	3.8	
3	BlueJeans	Clothing	800	puma	3.6	
4	BlackJeans	Clothing	750	Denim	4.5	
5	BlueShirt	Clothing	1000	Puma	4.3	
6	ChocolateCake	Food	25	Britannia	3.7	
7	StrawberryCake	Food	60	Cadbury	4.1	
8	ChocolateCake	Food	60	Cadbury	2.5	
9	StrawberryCake	Food	10	Britannia	4.6	
14	RawCashew	Food	370	Absa	3.9	
15	CashewNuts	Food	550	Upcrop	4.3	
=================================================
Enter your choice
1.Insert
2.Retrieve
3.Retrieve2
4.Update
5.Delete
3
Print all the products whose rating is greater than 4.5
---------------------------------------------------------
slno	name	category	price	brand	rating
9	StrawberryCake	Food	10	Britannia	4.6	
10	SmartWatch	Gadgets	17000	Apple	4.9	
11	SmartCam	Gadgets	2600	Realme	4.7	
13	SmartBand	Gadgets	3000	Realme	4.6
===============================================	
Enter your choice
1.Insert
2.Retrieve
3.Retrieve2
4.Update
5.Delete
4
Update price of SmartWatch to 15000 where no=9
------------------------------------------------
SmartWatch price updated successfully
Enter your choice
1.Insert
2.Retrieve
3.Retrieve2
4.Update
5.Delete
5
delete products whose price is less than  100
----------------------------------------------
 product deleted successfully
*/
 



		
			
	
		
	
		
		
	
		
		
		
		
		
	
	


