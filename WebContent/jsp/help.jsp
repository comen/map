<%@ page language="java" import="java.util.*, java.io.*" 
	pageEncoding="UTF-8"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html> 
<head>
	<title>帮助</title>
</head>
<% 
	out.clear();
	out = pageContext.pushBody();
	try {
		String strPdfPath = new String("C:/eclipse-workspace/Map/help.pdf");
		File file = new File(strPdfPath);
		//判断该路径下的文件是否存在
		if (file.exists()) {
			response.setContentType("application/pdf");
			DataOutputStream temps = new DataOutputStream(response.getOutputStream());
			DataInputStream in = new DataInputStream(new FileInputStream(strPdfPath));
			
			byte[] b = new byte[2048];
			while ((in.read(b)) != -1) {
				temps.write(b);
			}
			in.close();
			temps.flush();
			temps.close();
		} else {
			response.setContentType("text/html; charset=utf-8");
			out.println(strPdfPath + " 文件不存在!");
		}
	} catch (Exception e) {
		out.println(e.getMessage());
	} 
%>
<body>
<br />
</body>
</html>