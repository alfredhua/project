package com.generator;



import com.generator.entity.JDBCEntity;
import com.generator.util.MybatisGeneratorUtil;
import static com.generator.util.PropertiesUtil.*;

/**
 * 代码生成类
 */
public class Generator {


	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		JDBCEntity jdbcEntity= JDBCEntity.getInstance().setJdbcDriver(JDBC_DRIVER).
				setJdbcPassword(JDBC_PASSWORD). setJdbcUseName(JDBC_USERNAME).
				setJdbcUrl(JDBC_URL);
		//生成项目结构
		MybatisGeneratorUtil.generator(jdbcEntity);

	}

}
