package com.example.demo.data;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.example.demo.dao.uatDao"}, sqlSessionTemplateRef = "uatSqlSessionTemplate")
public class UatMybatisDataSource {

	@Bean(name = "uatDataSource")
	@ConfigurationProperties(prefix = "uat.datasource")
	public DataSource uatDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public SqlSessionFactory uatSqlSessionFactory(@Qualifier("uatDataSource") DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		
		ResourcePatternResolver  resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath*:mapper/uat/*.xml"));
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate uatSqlSessionTemplate(@Qualifier("uatSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
	}
	
	
	
}
