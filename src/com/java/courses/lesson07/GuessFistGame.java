package com.java.courses.lesson07;

import java.util.Random;
import java.util.Scanner;

public class GuessFistGame {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\"石头剪刀布，电脑已就绪！游戏规则如下：和电脑猜拳\n" + " 1【石头】；2【剪刀】；3【布】\n");
        int computerScore = 0;
        int playerScore = 0;
        Random random = new Random();

        // 当玩家达到5分时游戏结束
        while (playerScore < 5) {
            // random.nextInt(3) 从0开始取3个数，+1后取[1,2,3]
            int computerGuess = random.nextInt(3) + 1;
            System.out.println("请输入数字猜拳: ");
            int playerGuess = scan.nextInt();
            if (playerGuess == 886) {
                System.out.println("下次再见咯~");
                break;
            }
            if (playerGuess > 0 & playerGuess < 4) {
                System.out.println("你出的：" + playerGuess);
                if (computerGuess != playerGuess) {
                    if ((playerGuess - computerGuess == -1) | (playerGuess - computerGuess == 2)) {
                        // 玩家赢的情况：石头1-->剪刀2，剪刀2-->布3，布3-->石头1
                        playerScore += 1;
                        System.out.println("不错不错，竟然真的赢了！本次游戏，电脑出" + computerGuess + "你出" + playerGuess);
                        System.out.println("目前战况为： 电脑：" + computerScore + " 分" + "，玩家 " + playerScore + " 分");
                        System.out.println("退出游戏请输入886，输入其他数字继续");
                        System.out.println("----------------------------------------\n");
                    } else {
                        // 电脑赢
                        computerScore += 1;
                        System.out.println("还好我技高一筹，你败了！本次游戏，电脑出" + computerGuess + "你出" + playerGuess);
                        System.out.println("目前战况为： 电脑：" + computerScore + " 分" + "，玩家 " + playerScore + " 分");
                        System.out.println();
                        System.out.println("退出游戏请输入886，输入其他数字继续");
                        System.out.println("----------------------------------------\n");

                    }
                } else {
                    System.out.println("真是太有默契了，电脑出 " + computerGuess + "你出" + playerGuess);
                    System.out.println("目前战况为： 电脑：" + computerScore + " 分" + "，玩家 " + playerScore + " 分");
                    System.out.println("退出游戏请输入886，输入其他数字继续");
                    System.out.println("----------------------------------------\n");
                }
            } else {
                System.out.println("你输入的数字不合法，重新输入吧~");
                System.out.println();
                System.out.println("----------------------------------------\n");
            }
        }
        System.out.println("电脑分数："+computerScore+"，游戏结束！");
        System.out.println("玩家分数："+playerScore+"，游戏结束！");
    }
}
