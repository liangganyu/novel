package sutdy1;

import java.util.*;

public class Test4 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<>();

        while (true) {
            System.out.print("请输入一串数字：");
            List<String> listone = Arrays.asList(sc.next().split(","));
            for (String s : listone) {
                list.add(Integer.valueOf(s));
            }
            System.out.println("你输入的集合为：" + list);
            HashMap<String, List<Integer>> map = getMap();
//        System.out.println("============================");
//        System.out.println("生成的5个不同的不包含上面元素的随机集合为：");
            for (int i = 0; i < 10000; i++) {
                lists.add(getList(list));
            }
            System.out.println("===========================");
            //统计lists集合中每个元素的个数并返回最多元素的前5位的list
            List<Integer> integers = doList(lists);
            System.out.println("预测最可能出现的为：");
            Set<Map.Entry<String, List<Integer>>> entries = map.entrySet();
            for (Map.Entry<String, List<Integer>> entry : entries) {
                List<Integer> value = entry.getValue();
                for (Integer integer : integers) {
                    if (value.contains(integer)) {
                        //输入处理数字后的结果
                        System.out.print("  " + entry.getKey()+integer);
                    }
                }
            }
            list.clear();
            lists.clear();
            integers.clear();
            System.out.println();
            System.out.println("=======================");

        }

    }


    public static List<Integer> doList(List<List<Integer>> lists) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //计数
        int count = 0;
        //获取map集合
        ArrayList<Integer> list = new ArrayList<>();
        for (List<Integer> integers : lists) {
            for (Integer integer : integers) {
                for (List<Integer> integerList : lists) {
                    if (integerList.contains(integer)) {
                        count++;
                    }
                }
                map.put(integer, count);
                count = 0;
            }
        }
        //System.out.println(map.toString());
        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //遍历获得排序后的结果
        for (Map.Entry<Integer, Integer> entry : entries) {
            list.add(entry.getKey());
            if (list.size() == 10) {
                break;
            }
        }
        return list;
    }

    public static List<Integer> getList(List<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        while (true) {
            int ceil = (int) Math.ceil((Math.random() * 49));
            if (list.contains(ceil) || newList.contains(ceil)) {
                continue;
            }
            newList.add(ceil);
            if (newList.size() == 7) {
                break;
            }
        }
        return newList;
    }

    public static HashMap<String, List<Integer>> getMap() {
        HashMap<String, List<Integer>> map = new HashMap<>();
        List<Integer> a1 = Arrays.asList(12, 24, 36, 48);
        List<Integer> a2 = Arrays.asList(11, 23, 35, 47);
        List<Integer> a3 = Arrays.asList(10, 22, 34, 46);
        List<Integer> a4 = Arrays.asList(9, 21, 33, 45);
        List<Integer> a5 = Arrays.asList(8, 20, 32, 44);
        List<Integer> a6 = Arrays.asList(7, 19, 31, 43);
        List<Integer> a7 = Arrays.asList(6, 18, 30, 42);
        List<Integer> a8 = Arrays.asList(5, 17, 29, 41);
        List<Integer> a9 = Arrays.asList(4, 16, 28, 40);
        List<Integer> a10 = Arrays.asList(3, 15, 27, 39);
        List<Integer> a11 = Arrays.asList(2, 14, 26, 38);
        List<Integer> a12 = Arrays.asList(1, 13, 25, 37, 49);
        map.put("鼠", a1);
        map.put("牛", a2);
        map.put("虎", a3);
        map.put("兔", a4);
        map.put("龙", a5);
        map.put("蛇", a6);
        map.put("马", a7);
        map.put("羊", a8);
        map.put("猴", a9);
        map.put("鸡", a10);
        map.put("狗", a11);
        map.put("猪", a12);
        return map;
    }


}
