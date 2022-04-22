import java.sql.* ;
import java.util.Scanner;

public class FridayProj {
	public static void main(String[] args) {
		int choice=0;
		Scanner sc=new Scanner(System.in);

		do {
		
			System.out.println("1.Register");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			choice=sc.nextInt();
			if(choice==1) {
				String fname;
				String lname;
				String pass;
				int phno;
				
				System.out.print("\n Enter First Name :");
				fname=sc.next();
				System.out.print("\n Enter Last name :");
				lname=sc.next();
				System.out.print("\n Enter Password  :");
				pass=sc.next();
				System.out.print("\n Enter phno :");
				phno=sc.nextInt();
				
				Connection con=null ;
				try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "Pass@123");
		            PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?)");
		         
		            ps.setString(1, fname);
		            ps.setString(2, lname);
		            ps.setString(3, pass);
		            ps.setInt(4, phno);
		            ps.execute();
	
		        } catch (Exception e) {
		            System.out.println(e);
		        }
				
				
				
			}else if(choice==2) {
				System.out.println("Enter phno : ");
				int phno=sc.nextInt();
				System.out.println("Enter password : ");
				String pass=sc.next();
				
				Connection con=null ;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test","root","Pass@123");
					
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT * FROM  user WHERE phno="+phno+" AND PASS="+"\'"+pass+"\'");
					int usercount=0;
					while(rs.next()) {
						++usercount ;
					}
					if(usercount==1) {
							int choice2=0;
						do {
							System.out.println("1.REGISTER ACCOUNT");
							System.out.println("2.CHECK BALANCE");
							System.out.println("3.tRANSFER MONEY");
							
							choice2=sc.nextInt();
							if(choice2==1) {
								System.out.println("Enter Account No");
								int ano=sc.nextInt();
								System.out.println("Enter IFSC No");
								int ifsc=sc.nextInt();
								System.out.println("Enter Balance No");
								int bal=sc.nextInt();
								System.out.println("Enter Phone No");
								phno=sc.nextInt();
								
								con=null ;
								try {
						            Class.forName("com.mysql.cj.jdbc.Driver");
						            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "Pass@123");
						            PreparedStatement ps = con.prepareStatement("insert into bank2 values(?,?,?,?)");
						         
						            ps.setInt(1, ano);
						            ps.setInt(2, ifsc);
						            ps.setInt(3, bal);
						            ps.setInt(4, phno);
						            ps.execute();
					
						        } catch (Exception e) {
						            System.out.println(e);
						        }
								
								
								
								
							}else if(choice2==2) {
								
								System.out.println("Enter Account no. to check balance");
								int ano=sc.nextInt();
								
								
								con=null ;
								try {
									Class.forName("com.mysql.cj.jdbc.Driver");
									con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test","root","Pass@123");
									
									Statement st2=con.createStatement();
									System.out.println("before");
									ResultSet rset=st2.executeQuery("SELECT * FROM  bank2 WHERE ano="+ano);
									System.out.println("after");
									
									while(rset.next()) {
										System.out.println("balance : "+rset.getInt(1));
									}
									
								}
								catch(Exception e) {
									
								}
								
							}else if(choice2==3) {
								
								
									System.out.println("From Bank Account (ano)");
									int fano=sc.nextInt();
									System.out.println("To Bank Account (ano)");
									int tano=sc.nextInt();
									System.out.println("Enter Amount to be Transferred");
									int amt=sc.nextInt();
									
									con=null ;
									
									try {
										Class.forName("com.mysql.cj.jdbc.Driver");
										con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test","root","Pass@123");
										
										PreparedStatement pstmt3=con.prepareStatement("update bank2 set bal=bal+? where ano=?");
										pstmt3.setInt(1, amt);
										pstmt3.setInt(2, tano);
										pstmt3.execute();
										
										PreparedStatement pstmt4=con.prepareStatement("update bank2 set bal=bal-? where ano=?");
										
										pstmt4.setInt(1, amt);
										pstmt4.setInt(2, fano);
										
										pstmt4.execute();
									}
									catch(Exception e) {
										
									}

									
								
							}
							
						}while(choice2!=4);
						
						
					}
					else {
						System.out.println("NO USER FOUND TRY AGAIN !!!");
					}
					 
				}
				catch(Exception e) {
					
				}
				
			}
			
		}while(choice!=3);	
		
		

	}
}
