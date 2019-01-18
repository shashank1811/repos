import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Calendar;

public class AdvancedJava 
{
	public static void main(String arg[]) throws IOException 
	{
		Document doc = null;
		try 
		{
			//create connection 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			DOMSource domSource = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element results = doc.createElement("Table");
			doc.appendChild(results);
			try 
			{
				try 
				{
					String username = "sql12274592";
					String password = "BYKrdA3mDn";
					String url = "jdbc:mysql://sql12.freemysqlhosting.net/sql12274592" ;
					
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
				} 
				catch (Exception e) 
				{
					System.out.println(e);
					System.exit(0);
				}
				pstmt = con.prepareStatement("select * from emp");
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();
				Element tableName = doc.createElement("Emp");
				tableName.appendChild(doc.createTextNode(rsmd.getTableName(1)));
				results.appendChild(tableName);
				Element structure = doc.createElement("TableStructure");
				results.appendChild(structure);
				Element col = null;
				for (int i = 1; i <= numCols; i++) {
					col = doc.createElement("Column" + i);
					results.appendChild(col);
					Element columnNode = doc.createElement("ColumnName");
					columnNode.appendChild(doc.createTextNode(rsmd.getColumnName(i)));
					col.appendChild(columnNode);
					Element typeNode = doc.createElement("ColumnType");
					typeNode.appendChild(doc.createTextNode(String.valueOf((rsmd.getColumnTypeName(i)))));
					col.appendChild(typeNode);
					Element lengthNode = doc.createElement("Length");
					lengthNode.appendChild(doc.createTextNode(String.valueOf((rsmd.getPrecision(i)))));
					col.appendChild(lengthNode);
					structure.appendChild(col);
				}
				Element productList = doc.createElement("TableData");
				results.appendChild(productList);
				int l = 0;
				while (rs.next()) 
				{
					Element row = doc.createElement("Product" + (++l));
					results.appendChild(row);
					for (int i = 1; i <= numCols; i++) 
					{
						String columnName = rsmd.getColumnName(i);
						Object value = rs.getObject(i);
						Element node = doc.createElement(columnName);
						node.appendChild(doc.createTextNode((value != null) ? value.toString() : ""));
						row.appendChild(node);
					}
					productList.appendChild(row);
				}
				domSource = new DOMSource(doc);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				
				//insert data of table before editing in first file
				StreamResult sr = new StreamResult(new File("D:\\temp\\file1.xml"));
				transformer.transform(domSource, sr);
			} 
			catch (SQLException sqlExp) 
			{
				System.out.println("SQLExcp:" + sqlExp.toString());
			} 
			finally 
			{
				try 
				{
					if (rs != null) 
					{
						rs.close();
						rs = null;
					}
					if (con != null) 
					{
						con.close();
						con = null;
					}
				} 
				catch (SQLException expSQL) 
				{
					System.out.println("SQLExcp:CLOSING:");
				}
			}
		}
		catch (TransformerException | ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			//create connection for updation
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			DOMSource domSource = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element results = doc.createElement("Table");
			doc.appendChild(results);
			try 
			{
				try 
				{
					String username = "sql12274592";
					String password = "BYKrdA3mDn";
					String url = "jdbc:mysql://sql12.freemysqlhosting.net/sql12274592" ;
					
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
					Calendar calendar = Calendar.getInstance();
					java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
					String query = " insert into emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)"+" values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement insertStmt = con.prepareStatement(query);
				      insertStmt.setInt(1, 300);	
				      insertStmt.setString(2, "Shashank");
				      insertStmt.setString(3, "Intern");
				      insertStmt.setBoolean(4, false);
				      insertStmt.setDate(5, startDate);
				      insertStmt.setInt(6,20000);
				      insertStmt.setInt(7,875);
				      insertStmt.setInt(8,20);
				      insertStmt.execute();
				} 
				catch (Exception e) 
				{
					System.out.println(e);
					System.exit(0);
				}
				pstmt = con.prepareStatement("select * from emp");
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int numCols = rsmd.getColumnCount();
				Element tableName = doc.createElement("Emp");
				tableName.appendChild(doc.createTextNode(rsmd.getTableName(1)));
				results.appendChild(tableName);
				Element structure = doc.createElement("TableStructure");
				results.appendChild(structure);
				Element col = null;
				for (int i = 1; i <= numCols; i++) {
					col = doc.createElement("Column" + i);
					results.appendChild(col);
					Element columnNode = doc.createElement("ColumnName");
					columnNode.appendChild(doc.createTextNode(rsmd.getColumnName(i)));
					col.appendChild(columnNode);
					Element typeNode = doc.createElement("ColumnType");
					typeNode.appendChild(doc.createTextNode(String.valueOf((rsmd.getColumnTypeName(i)))));
					col.appendChild(typeNode);
					Element lengthNode = doc.createElement("Length");
					lengthNode.appendChild(doc.createTextNode(String.valueOf((rsmd.getPrecision(i)))));
					col.appendChild(lengthNode);
					structure.appendChild(col);
				}
				Element productList = doc.createElement("TableData");
				results.appendChild(productList);
				int l = 0;
				while (rs.next()) 
				{
					Element row = doc.createElement("Product" + (++l));
					results.appendChild(row);
					for (int i = 1; i <= numCols; i++) 
					{
						String columnName = rsmd.getColumnName(i);
						Object value = rs.getObject(i);
						Element node = doc.createElement(columnName);
						node.appendChild(doc.createTextNode((value != null) ? value.toString() : ""));
						row.appendChild(node);
					}
					productList.appendChild(row);
				}
				domSource = new DOMSource(doc);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
				//insert data of table emp in 2nd xml file
				StreamResult sr = new StreamResult(new File("D:\\file2.xml"));
				transformer.transform(domSource, sr);
			} 
			catch (SQLException sqlExp) 
			{
				System.out.println("SQLExcp:" + sqlExp.toString());
			} 
			finally 
			{
				try 
				{
					if (rs != null) 
					{
						rs.close();
						rs = null;
					}
					if (con != null) 
					{
						con.close();
						con = null;
					}
				} 
				catch (SQLException expSQL) 
				{
					System.out.println("SQLExcp:CLOSING:");
				}
			}
		}
		catch (TransformerException | ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
        BufferedReader reader1 = new BufferedReader(new FileReader("D:file2.xml"));
        BufferedReader reader2 = new BufferedReader(new FileReader("D:\\temp\\file1.xml"));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        boolean areEqual = true;
        int lineNum = 1;
        while (line1 != null || line2 != null)
        {
        	if(line1 == null || line2 == null)
            {
                areEqual = false;
                System.out.println("Two files have different content. They differ at line "+lineNum);
                System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
            }
            else if(! line1.equalsIgnoreCase(line2))
            {
                areEqual = false;
                System.out.println("Two files have different content. They differ at line "+lineNum);
                System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
            }
        	line1 = reader1.readLine();
            line2 = reader2.readLine();
            lineNum++;
        }
        if(areEqual)
        {
        	System.out.println("Two files have same content.");
        }
        else
        {
            System.out.println("Two files have different content. They differ at line "+lineNum);
            System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
        }
        reader1.close();
        reader2.close();
	}
}