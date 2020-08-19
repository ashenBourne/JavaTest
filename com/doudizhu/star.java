package com.doudizhu;

import java.util.*;

/*
*   功能：实现斗地主中的洗牌发牌看牌，拿到手的要排序。
*   思路：
*       1、创建hashMap,键是index，值是牌
*       2、创建ArrayList，存储index
*       3、创建牌面：两个数组，分别为花色和点数
*       4、往hashMap中存入牌，arrayList中存入index
*       5、洗牌
*       6、发牌
*       7、定义地主
*       8、看牌
*
*/
public class star {
    public static void main(String[] args) {
//        所有的牌
        HashMap<Integer,String> allPoker=new HashMap<>();
//        牌的集合
        ArrayList<Integer> allIndex=new ArrayList<>();
//        玩家与底牌
        TreeSet<Integer> playerOne=new TreeSet<Integer>();
        TreeSet<Integer> playerTwo=new TreeSet<Integer>();
        TreeSet<Integer> playerThree=new TreeSet<Integer>();
        TreeSet<Integer> dp=new TreeSet<Integer>();
//        花色点数
        String[] colors={"♠","♥","♣","♦"};
        String[] numbers={"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        int index=0;
        for(String number :numbers){
            for(String color:colors ){
                String x=color+number;
                allPoker.put(index,x);
                allIndex.add(index);
                index++;
            }
        }
        allPoker.put(index,"小王");
        allIndex.add(index);
        index++;
        allPoker.put(index,"大王");
        allIndex.add(index);
//        确定哪个是地主，哪个是农民:在index=8到index=48之间随机选择下标
        int random=(int)(8+Math.random()*(48-8+1));
        System.out.println(random);
//        洗牌
        Collections.shuffle(allIndex);
//        发牌
        for(int i =0;i<allIndex.size();i++){
            Integer x=allIndex.get(i);
            if(allIndex.size()-i<=3){
                dp.add(x);
            }else if(i%3==0){
                playerOne.add(x);
            }else if(i%3==1){
                playerTwo.add(x);
            }else if(i%3==2){
                playerThree.add(x);
            }
        }
//        看牌
        lookPoker("张一鸣",playerOne,allPoker,0,random);
        lookPoker("刘强东",playerTwo,allPoker,1,random);
        lookPoker("马云",playerThree,allPoker,2,random);
        lookPoker("底牌",dp,allPoker,-1,random);

    }
    public static void lookPoker(String name,TreeSet<Integer> ts,HashMap<Integer,String > hm,int role,int random){
        if(random%3==role){
            System.out.print(name+"是地主，牌面是：");
        }else{
            if(role!=-1){
                System.out.print(name+"是农民，牌面是：");
            }else{
                System.out.print("底牌是：");
            }

        }
        for(Integer i:ts){
            String x=hm.get(i);
            System.out.print(x+" ");
        }
        System.out.println();
    }
}
