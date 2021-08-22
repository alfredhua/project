package com.generator;



import com.generator.entity.JDBCEntity;
import com.generator.service.*;
import com.generator.sql.QueryUtil;

import java.util.List;
import java.util.Map;

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
		List<Map<String, Object>> tablesList = QueryUtil.getTables(jdbcEntity, DATABASE, TABLE_PREFIX);

		//生成项目结构
//		MybatisGeneratorUtil.generator(jdbcEntity);

		new ApiDtoEntityService().generator(jdbcEntity,tablesList);

		new ServerMapperService().generator(jdbcEntity,tablesList);
		new ServerMapperProviderService().generator(jdbcEntity,tablesList);

		new ServerInterfaceService().generator(jdbcEntity,tablesList);
		new ServerInterfaceImplService().generator(jdbcEntity,tablesList);

		new ServerControllerService().generator(jdbcEntity,tablesList);
		new ServerControllerVoService().generator(jdbcEntity,tablesList);
		new ServerControllerRespVoService().generator(jdbcEntity,tablesList);
	}

}
