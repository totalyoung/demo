import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/6/28.
 */
public class test {

    public static void createUpdateSql(int webId,int sid,String agent){
        for(int i=0;i<3;i++){
            String str1= "update `p_role` set yr_web='qq',yr_serverid='"+webId+"',yr_createserverid='"+webId+"',createserverid='"+webId+"',servername='qq_"+webId+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';";
            String str2="update p_user set webId='"+webId+"',yr_web='qq',yr_serverid='"+webId+"',yr_createserverid='"+webId+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';";
            System.out.println(str1);
            System.out.println(str2);
            webId++;
            sid++;
        }
    }

    public static void createUpdateSql2(int toSid,int sid,String toAgent,String agent){
        for(int i=0;i<3;i++){
            String str1= "update `p_role` set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',yr_createserverid='"+toSid+"',createserverid='"+toSid+"',servername='"+toAgent+"_"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_user set webId='"+toSid+"',yr_web='"+toAgent+"',yr_serverid='"+toSid+"',yr_createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_serverparam set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_server_count set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_friend set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',yr_createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_gold_expend set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',yr_createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_goldrecharge set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_guild_member set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',yr_createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_role_gold set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',createserverid='"+toSid+"',yr_createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update p_role_goldrecharge set yr_web='"+toAgent+"',yr_serverid='"+toSid+"',createserverid='"+toSid+"',yr_createserverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_actdata set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_countryking set yr_web='"+toAgent+"',zswebandserverid='"+toAgent+"_"+toSid+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_guild set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_guildlog_data set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_mail set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_nations set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n"
            +"update s_rankchallenge set yr_web='"+toAgent+"',yr_serverid='"+toSid+"' where yr_web='"+agent+"' and yr_serverid='"+sid+"';\n";
            System.out.println(str1);
            toSid++;
            sid++;
        }
    }

    public static void create360CharatorSum(String date,int databaseNum,int day){
        long baseStartTime = TimeUtil.getDayOfStartTime(date);
        long baseEndTime = TimeUtil.getDayOfEndTime(date);
        StringBuilder sb = new StringBuilder("");
        for (int a = 0; a < day; a++) {
            long startTime = baseStartTime+ a* 24*60*60*1000;
            long endTime = baseEndTime+ a* 24*60*60*1000;
            sb.append("select FROM_UNIXTIME(");
            sb.append(startTime/1000).append(") as date,count(*) FROM (");
            for(int i=1,b=0;i<=databaseNum;i++,b++){
                sb.append(" SELECT account FROM game_360_").append(1+b*3).append("_").append(3+b*3).append(".`p_role` where createroletime >")
                        .append(startTime).append(" and  createroletime<= ").append(endTime);
                if(i<databaseNum) {
                    sb.append("\r\n");
                    sb.append("UNION ");
                }
            }
            sb.append(" ) t").append(a).append("\r\n") ;
            if(a!=day-1){
                sb.append("UNION ").append("\r\n");
            }
        }
        System.out.println(sb);
    }

    public static void create360CharatorSum2(String date,int databaseNum,int day){
        long baseStartTime = TimeUtil.getDayOfStartTime(date);
//        if(baseStartTime>1560960000000L){
//            //第一个游戏服时间
//            baseStartTime = 1560960000000L;
//        }
        long baseTime = 1560960000000L;
        long baseEndTime = TimeUtil.getDayOfEndTime(date);
        StringBuilder sb = new StringBuilder("");
        for (int a = 0; a < day; a++) {
            long startTime = baseStartTime+ a* 24*60*60*1000;
            long endTime = baseEndTime+ a* 24*60*60*1000;
            long lastEndTime;
            if(startTime==baseTime&&a==0){
                lastEndTime = 1560960000000L;
            }else{
                lastEndTime = baseEndTime+ (a-1)* 24*60*60*1000;
            }
//            int c =0;
//            if(a>0) c=a-1;
            sb.append("select FROM_UNIXTIME(");
            sb.append(startTime/1000).append(") as date,count(*) FROM (");
            for(int i=1,b=0;i<=databaseNum;i++,b++){
                sb.append(" SELECT account FROM game_360_").append(1+b*3).append("_").append(3+b*3)
                        .append(".`p_role` t").append(i).append(" where createroletime >")
                        .append(startTime).append(" and  createroletime<= ").append(endTime)
                        .append(" and not EXISTS ( select account from game_360_").append(1+b*3).append("_").append(3+b*3).append(".`p_role` t").append(i).append("_").append(i)
                        .append(" where t").append(i).append("_").append(i).append(".account = t").append(i).append(".account and createroletime >").append(baseTime).append(" and  createroletime<=").append( lastEndTime).append(")");
                if(i<databaseNum) {
                    sb.append("\r\n");
                    sb.append("UNION ");
                }
            }
            sb.append(" ) t").append(a).append("\r\n") ;
            if(a!=day-1){
                sb.append("UNION ").append("\r\n");
            }
        }
        System.out.println(sb);
    }


    public static void test(String webname,int start,int end){
        StringBuilder sb = new StringBuilder("");
        for (int a = start; a <=end;a++) {
            sb.append("select yr_web,yr_serverid,yr_createserverid,account,rolename from game_").append(webname).append("_").append(a).append("_");
            a=a+2;
            sb.append(a).append(".p_role t where t.rolename like '%逼%' or t.rolename like '%屌%'").append("\r\n");
            sb.append("union").append("\r\n");

        }
        System.out.println(sb);
    }

    public static void test2(String webname,int start,int end){
        StringBuilder sb = new StringBuilder("");
        for (int a = start; a <=end;a++) {
            sb.append("select yr_web,yr_serverid,yr_createserverid,account,rolename from game_").append(webname).append("_").append(a).append("_");
            a=a+2;
            sb.append(a).append(".p_role t where t.yr_serverid>=100000 ").append("\r\n");
            sb.append("union all").append("\r\n");

        }
        System.out.println(sb);
    }

     static class Pair{
        int fst;
        int snd;

        public Pair(int fst, int snd) {
            this.fst = fst;
            this.snd = snd;
        }

         public Pair() {
         }

         @Override
         public String toString() {
             return "Pair{" +
                     "fir=" + fst +
                     ", sed=" + snd +
                     '}';
         }


     }

     //坐标1相对坐标2的角度 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
     public static int getdir(Pair p,Pair p2){
        if(p.fst==p2.fst&&p.snd<p2.snd){
            return 0;
        }
        if(p.fst>p2.fst&&p.snd<p2.snd){
            return 1;
        }
        if(p.fst>p2.fst&&p.snd==p2.snd){
            return 2;
        }
        if(p.fst>p2.fst&&p.snd>p2.snd){
            return 3;
        }
        if(p.fst==p2.fst&&p.snd>p2.snd){
            return 4;
        }
        if(p.fst<p2.fst&&p.snd>p2.snd){
            return 5;
        }
        if(p.fst<p2.fst&&p.snd==p2.snd){
            return 6;
        }
        if(p.fst<p2.fst&&p.snd<p2.snd){
             return 7;
        }
        return 8;
     }



    public static void shortRold(int[][] block,Pair p,Pair p2){
        List<Pair> list =new ArrayList<>();
        Pair pre = new Pair();
//        int dir = 1;
        boolean bb = false;
        int dir =0;
        int index=0;
        while(!(p.fst ==p2.fst && p.snd ==p2.snd)){
            pre.fst = p.fst;
            pre.snd = p.snd;
            if(index==8){
                break;
            }
            if(!bb){
                dir = getdir(p, p2);
            }
            if(dir==0){
                p.snd = p.snd+1;
            }
            if(dir==1){
                p.fst = p.fst-1;
                p.snd = p.snd+1;
            }
            if(dir==2){
                p.fst = p.fst-1;
            }
            if(dir==3){
                p.fst = p.fst-1;
                p.snd = p.snd-1;
            }
            if(dir==4){
                p.snd = p.snd-1;
            }
            if(dir==5){
                p.fst = p.fst+1;
                p.snd = p.snd-1;
            }
            if(dir==6){
                p.fst = p.fst+1;
            }
            if(dir==7){
                p.fst = p.fst+1;
                p.snd = p.snd+1;
            }
            if(p.snd>=block.length||p.fst>=block[p.fst].length){
                System.out.println("溢出");
                return;
            }
            if(block[p.fst][p.snd]==1){
                p.fst = pre.fst;
                p.snd = pre.snd;
                if(dir>=0&&dir<4){
                    dir--;
                    if(dir<0){
                        dir=dir+8;
                    }
                }else{
                    dir++;
                    if(dir>7){
                        dir =dir-8;
                    }
                }
                bb=true;
                index++;
            }else{
                index=0;
                bb=false;
                System.out.println(p);
            }
        }
    }

    public static String testTry(){
        if(true){
            try{
                return "aa";
            }catch (Exception e){
                System.out.println("e");
            }finally {
                System.out.println("fain");
            }
            return "cc";
        }
        return  "bb";
    }

    public static void main(String[] args) throws IOException {

//        System.out.println(getdir(new Pair(0,1),new Pair(0,0)));
//        System.out.println(getdir(new Pair(1,1),new Pair(0,0)));
//        System.out.println(getdir(new Pair(1,0),new Pair(0,0)));
//        System.out.println(getdir(new Pair(1,-1),new Pair(0,0)));
//        System.out.println(getdir(new Pair(0,-1),new Pair(0,0)));
//        System.out.println(getdir(new Pair(-1,-1),new Pair(0,0)));
//        System.out.println(getdir(new Pair(-1,0),new Pair(0,0)));
//        System.out.println(getdir(new Pair(-1,1),new Pair(0,0)));
//        System.out.println(getdir(new Pair(4,5),new Pair(0,0)));
//        Pair p = new Pair(0,0);
//        Pair p2 = new Pair(2,0);
//        int[][] block = new int[][]{
//                {0,0,0,0},
//                {1,1,1,1},
//                {0,0,0,0},
//                {1,1,1,1}};
//        shortRold(block,p2,p);
//        System.out.println("--------------------");
//        Pair p3 = new Pair(0,0);
//        Pair p4 = new Pair(2,2);
//        shortRold(block,p3,p4);
//        System.out.println("---------------------");
//        System.out.println(getdir(p2,p));
//        System.out.println(getdir(p,p2));
//        System
//        createUpdateSql(1,10001,"37");
//        System.out.println("s1".toUpperCase());
//        create360CharatorSum("2019-06-30",10);
//        create360CharatorSum2("2019-06-21",10,11);

//        createUpdateSql2(1,1,"37","zhenqu");
//        System.out.println("{0}购买BOSS投资计划,计划理财更实惠".replace("\\{[^{}]*\\}","12"));
//        test("2345",1,15);
//        test("liebao",1,15);
//        test("douyu",1,18);
//        test("ku25",1,18);
//        test("360",1,63);
//        test("shunwang",1,30);
//        test("37",1,450);
//        test("37",10001,10087);
//        test("37",20001,20051);
//        test("37",30001,30138);
//        test("qq",1,192);

//        test("1771w",1,6);
//        test("yaodou",1,6);
//        test("zixia",1,9);
//        test("188w",1,18);
//        test("2323w",1,21);
//        test("43u",1,21);
//        test("feihuo",1,45);
//        test("511w",1,33);
//        test("tangchao",1,15);
//        System.out.println(MessageFormat.format("@在血拼秒杀中@",1));
//        System.out.println(String.format("@在血拼秒杀中购买了{$}，全服仅剩%s个! @",1));

//        System.out.println("{\"title\":\"{\\\"timestamp\\\":\\\"1565618017\\\",\\\"seq_id\\\":\\\"1565618017481\\\",\\\"app_id\\\":\\\"1108265248\\\",\\\"retry_tiems\\\":\\\"0\\\",\\\"app_name\\\":\\\"\\\"}\",\"data\":\"[{\\\"log_fields\\\":\\\"iworldid=4&iversion=0&time=1565618017&optype=2&level=140&dtEventTime=2019-08-12+21%3A53%3A37&svrip=-1062731242&domain=0&appid=1105226930&opuid=2897941648162825&opopenid=%23rb%23tzc002&userip=1062731242\\\",\\\"log_nam\\\":\\\"log_common\\\"}]\"}");
//        System.out.println(161 & 31);
//        System.out.println(9&0x8);
//
//        System.out.println(Integer.toBinaryString(161));
//        System.out.println(Integer.toBinaryString(31));

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        System.out.println(list);
//        list.add(0,2);
//        System.out.println(list);
//        test2("37",1,447);
//        test2("37",10001,10081);
//        test2("37",20001,20048);
//        test2("37",30001,30411);
//        test2("37",40001,40324);
//        test2("37",60001,60189);
//        test2("yy",1,72);
//        Integer[][] funcRecords = new Integer[2][6];
//        System.out.println(Arrays.asList(funcRecords));

//        InputStream in = new FileInputStream("C:\\Users\\admin\\Desktop\\txt\\协议.txt");
//        OutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\txt\\协议2.txt");
//        byte[] bytes = new byte[1024];
//        int a=-1;
//        while((a = in.read(bytes))>0){
//            out.write(bytes,0,a);
//            bytes = new byte[1024];
//        }
//        System.out.println(Integer.toHexString(32767*2));
//        System.out.println(124567 & 0xff);
//        System.out.println(124567 & 0x00ff);
//        System.out.println(Integer.parseInt("00ff",16));
//        System.out.println(Integer.parseInt("fff",16));
////        System.out.println(Long.toBinaryString(Long.parseLong("fff",16)));
////        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
////        System.out.println((int)(-1& 0x0FFFFFFFFL) );
////        System.out.println((-1& 0x0FFFFFFFFL) );
////        System.out.println(-123123>>>1);
//        short t = Short.MIN_VALUE;
//        System.out.println((short)(t & 0xffff));
//        System.out.println(TimeUtil.getCurrentSecond() * rand.nextInt(4));
//        BigInteger bigint=new BigInteger(String.valueOf(0xFFFFFFFF), 16);
//        int numb=bigint.intValue();
//        System.out.println(numb);
        System.out.println(testTry());


    }

}
