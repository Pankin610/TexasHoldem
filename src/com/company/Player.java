package com.company;

import java.util.Scanner;

public class Player{
    private int coins;
    private String name;
    private boolean pass;
    private boolean in_game;
    private Card first;
    private Card second;
    private int round_bet;

    public boolean Bet(int val) {
        round_bet += val;
        coins -= val;
        return true;
    }

    public void SetFirstCard(Card card) {
        first = card;
    }

    public void SetSecondCard(Card card) {
        second = card;
    }

    public int Win(int val) {
        coins += val;
        return coins;
    }

    public void Pass() {
        pass = true;
        round_bet = 0;
    }

    public boolean IsPass() {
        return pass;
    }

    public int Coins() {
        return coins;
    }

    public String Name() {
        return name;
    }

    public Card FCard() {
        return first;
    }

    public Card SCard() {
        return second;
    }

    public int Lose(int val) {
        coins -= val;
        return coins;
    }

    boolean InGame() {
        return coins > 0;
    }

    public int AllIn() {
        int to_return = coins;
        round_bet += coins;
        coins = 0;
        return to_return;
    }

    public void EndGame() {
        this.in_game = false;
    }

    public boolean GetInGame() {
        return in_game;
    }

    Player(String name, int coins) {
        this.name = name;
        this.coins = coins;
        this.pass = false;
        this.in_game = true;
        this.round_bet = 0;
    }

    public int getRound_bet() {
        return round_bet;
    }

    public int MakeBet(int min_bet) {
        System.out.println();
        System.out.println(name + ", you have to bet at least " + min_bet + " (" + (min_bet + getRound_bet()) + " in pot)");
        System.out.println("Your budget: " +  Coins());
        if (Coins() < min_bet) {
            System.out.println("Unfortunately you have not enough money, the system has passed for you");
            Pass();
            return 0;
        }
        System.out.println("What's your move?");
        System.out.println("C - Check");
        System.out.println("P - Pass");
        System.out.println("A - All in");
        System.out.println("x - Bet - where x is bet's value");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (true) {
            if (input.charAt(0) == 'C') {
                Bet(min_bet);
                return  min_bet;
            } else if (input.charAt(0) == 'P') {
                Pass();
                return 0;
            } else if (input.charAt(0) == 'A') {
                return AllIn();
            } else {
                int bet = Integer.parseInt(input);
                if (bet <= Coins() && bet >= min_bet) {
                    Bet(bet);
                    return bet;
                } else if (bet > Coins()){
                    System.out.println(":) You're poor man! - maybe next time, try again!");
                    input = scanner.nextLine();
                } else {
                    System.out.println("AT LEAST " + min_bet);
                    input = scanner.nextLine();
                }
            }
        }
    }
}