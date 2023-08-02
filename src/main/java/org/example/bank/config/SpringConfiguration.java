package org.example.bank.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration  //声明该类是核心配置类
@ComponentScan({"org.example.bank.service"})    //开启spring注解扫描
@EnableTransactionManagement    //事物扫描
@MapperScan("org.example.bank.mapper")  //MyBatis扫描dao接口
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {
    @Value("${jdbc.Driver}")
    private String driver;
    @Value("${jdbc.url}")
    private  String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    //创建数据源返回数据源，Spring会自动调用该方法，并将该对象交给IOC容器管理
    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /*
    事务管理
     */
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    //创建SqlSessionFactoryBean对象,设置形参，Spring会自动去调用IOC容器中已有的数据源
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:org/example/test/mapper/AccountMapper.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("org.example.bank.pojo");
        return sqlSessionFactoryBean;
    }
}
