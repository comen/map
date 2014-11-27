<%--
Validate if user has login successfully. Should be used in the top of every page.
If login successfully, 
 1) loginState.equals("S") is true;
 2) userName and role will be populated with real data;
Else,
 1) loginState.equals("F") is true;
 2) userName and role will be set "" and 0 as default;
--%>
<%
	String loginState = "F"; 	
	String userName = "";
	int role = 0;
	try {
		loginState = (String)session.getAttribute("loginstate");
		if (loginState.equals("S")) {
			userName = (String)session.getAttribute("username");
			role = Integer.parseInt((String)session.getAttribute("role"));
		}
	} catch (Exception e) {
		System.out.println(e.getClass() + "\t:\t" + e.getMessage());
	}
%>