package sutdy1;

import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        ArrayList<List<String>> lists = new ArrayList<>();
        /*出现次数累计*/
        int count1 = 0;
        /*不出现次数累计*/
        int count2 = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入本期生肖数（1-49）：");
        HashMap<String, String> map = new HashMap<>();
        map.put("鼠", "12,24,36,48");
        map.put("牛", "11,23,35,47");
        map.put("虎", "10,22,34,46");
        map.put("兔", "9,21,33,45");
        map.put("龙", "8,20,32,44");
        map.put("蛇", "7,19,31,43");
        map.put("马", "6,18,30,42");
        map.put("羊", "5,17,29,41");
        map.put("猴", "4,16,28,40");
        map.put("鸡", "3,15,27,39");
        map.put("狗", "2,14,26,38");
        map.put("猪", "1,13,25,37,49");
        while (true) {
            StringBuilder sb = new StringBuilder();
            String number = scanner.next();
            List<String> ZWList = new ArrayList<String>();
            //获取输入的生肖
            List<String> numList = Arrays.asList(number.split(","));
            for (String num : numList) {
                Set<Map.Entry<String, String>> entries = map.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    String value = entry.getValue();
                    List<String> values = Arrays.asList(value.split(","));
                    if (values.contains(num)) {
                        //对每个一和生肖进行拼接换算成中文
                        sb.append(entry.getKey() + " ");
                        ZWList.add(entry.getKey());
                        break;
                    }
                }
            }
            //当输入为50时，退出程序
            if (number.equals("50")) {
                System.out.println("结束程序！");
                break;
            }
            System.out.println("当前的期数为：" + sb.toString());
            /*进行与其他期的生肖进行概率换算*/
            sb.delete(0, sb.length());

            for (String zlist : ZWList) {
                for (int i = lists.size() - 1; i > lists.size() - 6; i--) {
                    for (List<String> list : lists) {
                        if (list.contains(zlist)) {
                            count1++;
                            break;
                        } else {
                            count2++;
                            break;
                        }
                    }
                    System.out.println("  "+zlist + "  出现的次数为：" + count1 + " , 不出现的次数为：" + count2);
                }
                count1 = 0;
                count2 = 0;
            }
            lists.add(ZWList);
            System.out.println("lists:"+lists.toString());

            System.out.println("请输入本期生肖数：");
        }
    }
}
