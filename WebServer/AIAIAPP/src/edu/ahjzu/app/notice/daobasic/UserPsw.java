package edu.ahjzu.app.notice.daobasic;

public class UserPsw {
	private final String name = "root";
	private final String password = "";
	private final String dbname = "jdbc:mysql://localhost:3306/aiaiapp?useUnicode=true&characterEncoding=UTF-8";

	// jdbc:mysql://localhost:3306/aiaiapp?useUnicode=true&amp;characterEncoding=GBK
	protected String getName() {
		return name;
	}

	protected String getPassword() {
		return password;
	}

	protected String getDbname() {
		return dbname;
	}

}
