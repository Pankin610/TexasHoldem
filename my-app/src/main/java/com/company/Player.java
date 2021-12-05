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

    public void setRound_bet(int x) {
        round_bet = x;
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
        in_game = (coins > 0);
        return in_game;
    }

    Player(String name, int coins) {
        this.name = name;
        this.coins = coins;
        this.pass = false;
        this.in_game = true;
        this.round_bet = 0;
    }

    public void PrintHand() {
        System.out.println();
        System.out.print("Your hand: ");
        System.out.print(FCard() + " " + SCard());
        System.out.println();
    }

    public int getRound_bet() {
        return round_bet;
    }

    public void Unpass() {
        pass = false;
    }

    public int MakeBet(int min_bet) {
        PrintHand();
        System.out.println();
        System.out.println(name + ", you have to bet at least $" + min_bet + " ($" + Main.pot + " in pot)");
        System.out.println();
        System.out.println("Your budget: $" +  Coins());
        System.out.println();
        if (Coins() < min_bet) {
            System.out.println("Unfortunately you have not enough money, the system has passed for you");
            Main.WaitForEnter(new Scanner(System.in));
            Pass();
        }
        System.out.println("What's your move?");
        if (Coins() > min_bet) {
            if (min_bet > 0) {
                System.out.println("C - Check for $" + min_bet);
            } else {
                System.out.println("C - Wait");
            }
        }
        System.out.println("P - Pass");
        System.out.println("A - All in for $" + Coins());
        if (Coins() > min_bet) {
            System.out.println("x - Raise for $x");
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        if (IsPass()) {
            return 0;
        }
        String input = scanner.nextLine();
        while (true) {
            if (input.isEmpty()) {
                continue;
            }
            if (input.charAt(0) == 'C') {
                Bet(min_bet);
                return  min_bet;
            } else if (input.charAt(0) == 'P') {
                Pass();
                return 0;
            } else if (input.charAt(0) == 'A') {
                return AllIn();
            } else if (input.charAt(0) >= '0' && input.charAt(0) <= '9') {
                int bet = Integer.parseInt(input);
                if (bet <= Coins() && bet >= min_bet) {
                    Bet(bet);
                    return bet;
                } else if (bet > Coins()){
                    System.out.println(":) You're poor man! - maybe next time, try again!");
                    input = scanner.nextLine();
                } else {
                    System.out.println("AT LEAST $" + min_bet + " ;(");
                    input = scanner.nextLine();
                }
            } else {
                input = scanner.nextLine();
            }
        }
    }
}