import java.util.*;
import java.sql.*;
public class UserLogin {
		public static void main(String args[]) throws SQLException {
			Scanner in = new Scanner(System.in);
			String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/?user=sql12646552";
			String user = "sql12646552";
			String pwd = "gA13G5dLUa";
			Connection con = null;
			try {
			con = DriverManager.getConnection(url,user,pwd);
			}catch(Exception e) {
				System.out.println("Connection Failed");
				return;
			}
			Statement stmt = con.createStatement();
			stmt.execute("use sql12646552");
			try {
			while(true) {
			System.out.println("Enter choice \n1. Login\n2. Sign up\n3. Exit");
			int ch = Integer.parseInt(in.nextLine());
			if(ch==3) {
				break;
			}
			else if(ch==1) {
				System.out.println("Enter user name : ");
				String name = in.nextLine();
				System.out.println("Enter Password : ");
				String pass = in.nextLine();
				ResultSet login = stmt.executeQuery("select name from users where "
						+ "name = \'"+name+"\' and pass = \'"+pass+"\';");
				if(login.next()) {
					System.out.println("Hello ! "+login.getString(1));
				}
				else {
					System.out.println("Invalid User name or password !");
				}
			}
			else if(ch==2) {
				int i=3;
				while(i>=0) {
				System.out.println("creating a New User");
				System.out.println("Enter user name : ");
				String name = in.nextLine();
				System.out.println("Enter Password : ");
				String pass = in.nextLine();
				System.out.println("Confirm Password : ");
				String cpass = in.nextLine();
				if(pass.equals(cpass)) {
					String insert = "INSERT INTO users VALUES(?,?);";
					PreparedStatement prpd = con.prepareStatement(insert);
					prpd.setString(1, name);
					prpd.setString(2, pass);
					try {
					int finish = prpd.executeUpdate();
					if(finish!=0)
					System.out.println("User registered Succesfully !");
					else
					System.out.println("User Registration Failed :( "+finish);
					break;
					}catch(Exception e) {
						System.out.println(e);
					}
				}
				else{
					System.out.println("Passwords don't match !");
					System.out.println("Attempts remaining "+i);
				}
				
				i--;
				}
			}
			
			
			}
			}catch(Exception e) {
				System.out.println(e);
			}
			con.close();
		}
}
