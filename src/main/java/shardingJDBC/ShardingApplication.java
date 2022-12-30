package shardingJDBC;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingApplication {

    public static void main(String[] args) throws Exception {
        ShardingApplication application = new ShardingApplication();
        application.testOnlyShardingTable();
//        test();
    }

    public  void test() throws SQLException {
//        String str = "INSERT INTO `u_user_info` (`account`, `phone`, `password`, `lastLoginTime`, `lastLogoutTime`, `createTime`, `modifyTime`) VALUES ('33333', '"+System.currentTimeMillis()/1000+"', 'aaa', NULL, NULL, '2020-12-31 18:59:51', NULL);";
        // 获取数据源对象
        DataSource dataSource = shardingTableAndDataSource();
        System.out.println();
        PreparedStatement preparedStatement ;
//        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(str);
//        preparedStatement.execute();
        String select = "select * from u_user_info where id = 814509691924119552";
        preparedStatement = dataSource.getConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
//        System.out.println(resultSet.isFirst());
        resultSet.next();
        System.out.println(resultSet.getString("account"));
        System.out.println(resultSet.getString(1));
    }

    public void testShardingTableAndDataSource() throws SQLException {
        String str = "INSERT INTO `u_user_info` (`account`, `phone`, `password`, `lastLoginTime`, `lastLogoutTime`, `createTime`, `modifyTime`) VALUES ('33333', '"+System.currentTimeMillis()/1000+"', 'aaa', NULL, NULL, '2020-12-31 18:59:51', NULL);";
        // 获取数据源对象
        DataSource dataSource = shardingTableAndDataSource();
        PreparedStatement preparedStatement ;
        preparedStatement = dataSource.getConnection().prepareStatement(str);
        preparedStatement.execute();
    }

    //单个逻辑表，分库分表
    DataSource shardingTableAndDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        //分库策略
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "usersystem${id % 2}"));
        //分表策略
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new UserPreciseShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    public void testOnlyShardingTable()throws SQLException {
        String str = "INSERT INTO `u_user_info` (`account`, `phone`, `password`, `lastLoginTime`, `lastLogoutTime`, `createTime`, `modifyTime`) VALUES ('33333', '"+System.currentTimeMillis()/1000+"', 'aaa', NULL, NULL, '2020-12-31 18:59:51', NULL);";
        // 获取数据源对象
        DataSource dataSource = onlyShardingTable();
        PreparedStatement preparedStatement ;
        preparedStatement = dataSource.getConnection().prepareStatement(str);
        preparedStatement.execute();
    }

    //单个逻辑表，只分表
    DataSource onlyShardingTable() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration2());
        //分表策略
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new UserPreciseShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createSingleSourceMap(), shardingRuleConfig, new Properties());
    }


    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        //绑定表
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        shardingRuleConfig.getBroadcastTables().add("t_config");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new ModuloShardingTableAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }



    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "id");
        return result;
    }

    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("u_user_info", "usersystem${0..1}.u_user_info${0..1}");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    TableRuleConfiguration getUserTableRuleConfiguration2() {
        TableRuleConfiguration result = new TableRuleConfiguration("u_user_info");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order_item", "ds${0..1}.t_order_item${0..1}");
        return result;
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("usersystem0", createDataSource("usersystem0"));
        result.put("usersystem1", createDataSource("usersystem1"));
        return result;
    }

    Map<String, DataSource> createSingleSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("usersystem0", createDataSource("usersystem0"));
        return result;
    }

    public DataSource createDataSource(String name){
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/"+name);
        dataSource1.setUsername("root");
        dataSource1.setPassword("Totalyoung2@");
        return dataSource1;
    }
}
