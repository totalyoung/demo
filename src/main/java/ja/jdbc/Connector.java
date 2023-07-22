package ja.jdbc;

import org.apache.commons.lang3.RandomUtils;

import java.sql.*;

public class Connector {
    public static void main(String[] args) throws Exception {
        testAutoCommit();

    }

    public static void testAutoCommit()throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = "SELECT * FROM `micromall`.`ums_member` WHERE username='aewen' FOR UPDATE;";
                autoCommit(str);
            }
        }).start();
        Thread.sleep(1000L);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String str = "INSERT INTO `micromall`.`ums_member` (`member_level_id`, `username`, `password`, `nickname`, `phone`, `status`, `create_time`, `icon`, `gender`, `birthday`, `city`, `job`, `personalized_signature`, `source_type`, `integration`, `growth`, `luckey_count`, `history_integration`) VALUES ( '4', 'aewen_"+System.currentTimeMillis()+"', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'aewen', '"+System.currentTimeMillis()/1000+"', '1', '2018-11-12 14:22:55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);";
                String str = "INSERT INTO `usersystem`.`u_user_info` (`account`, `phone`, `password`, `lastLoginTime`, `lastLogoutTime`, `createTime`, `modifyTime`) VALUES ('33333', '"+System.currentTimeMillis()/1000+"', 'aaa', NULL, NULL, '2020-12-31 18:59:51', NULL);";
                autoCommit(str);
            }
        }).start();
    }

    public static void autoCommit(String str)  {

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micromall?useUnicode=true&characterEncoding=UTF-8" +
    //                "&roundRobinLoadBalance=true&readFromMasterWhenNoSlaves=true" +
                    "&password=Totalyoung2@&user=root",null);
            if(conn.getAutoCommit()){
                System.out.println(Thread.currentThread().getId()+" 设置AutoCommit");
                conn.setAutoCommit(false);
            }

            int transactionIsolation = conn.getTransactionIsolation();
            System.out.println(Thread.currentThread().getId()+" "+transactionIsolation);
            PreparedStatement preparedStatement = conn.prepareStatement(str);

//            preparedStatement.addBatch(str );
//            int execute = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean execute = preparedStatement.execute();
            System.out.println(Thread.currentThread().getId()+" execute "+execute);
            Thread.sleep(5000L);
            conn.commit();
            System.out.println(Thread.currentThread().getId()+" commit");
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void testDeadLock() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        new Thread(new Runnable() {
            @Override
            public void run() {
                DeadLock(RandomUtils.nextInt(0,100000)+"");
            }
        }).start();
        Thread.sleep(3000L);
        Class.forName("com.mysql.jdbc.Driver");
        new Thread(new Runnable() {
            @Override
            public void run() {
                DeadLock(RandomUtils.nextInt(0,100000)+"");
            }
        }).start();
    }


    /**
     * 发生死锁
     * @param value
     */
    public static void DeadLock(String value){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersystem?useUnicode=true&characterEncoding=UTF-8" +
                    //                "&roundRobinLoadBalance=true&readFromMasterWhenNoSlaves=true" +
                    "&password=Totalyoung2@&user=root",null);
//            if(conn.getAutoCommit()){
//                System.out.println(Thread.currentThread().getId()+" 设置AutoCommit");
//
//            }
            System.out.println(Thread.currentThread().getId()+" start ");
            conn.setAutoCommit(false);
//            conn.setTransactionIsolation();
            int transactionIsolation = conn.getTransactionIsolation();
            long phone = System.currentTimeMillis() / 1000;
            System.out.println(Thread.currentThread().getId()+" "+transactionIsolation);
            String str = "INSERT INTO `usersystem`.`u_user_info` (`account`, `phone`, `password`, `lastLoginTime`, `lastLogoutTime`, `createTime`, `modifyTime`) VALUES ('33333', '"+phone+"', 'aaa', NULL, NULL, '2020-12-31 18:59:51', NULL);";
            Statement statement = conn.createStatement();
            int execute = statement.executeUpdate(str);
            System.out.println(Thread.currentThread().getId()+" INSERT "+execute);
            Thread.sleep(10000L);
            //phone字段类型转换，会导致全表扫描，出现锁升级，锁住全部记录。
            //需要注意字符串与整数之间的强制类型转换，有时候少一个引号，就会使得行锁升级为表锁。
            String update = "update `usersystem`.`u_user_info` set phone="+value+" where phone="+phone;
//            preparedStatement.addBatch(str );
            execute = statement.executeUpdate(update);
//            execute = conn.prepareStatement(update).execute();
            System.out.println(Thread.currentThread().getId()+" update "+execute);
            conn.commit();
            System.out.println(Thread.currentThread().getId()+" commit");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
