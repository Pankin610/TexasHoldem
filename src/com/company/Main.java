package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int num_of_players;
    public static ArrayList<Player> array_of_players = new ArrayList<>();


    public static void SetPlayersCards() {

    }

    public static void main(String[] args) throws InterruptedException, IOException {
	    System.out.println("Welcome to Texas Hold'em Game");
        System.out.println();
        System.out.print("Insert the number of players: ");

        Scanner scanner = new Scanner(System.in);
        num_of_players = Integer.parseInt(scanner.nextLine());
        if (num_of_players == 0) {
            System.out.println("No players no game!");
            System.out.println();
            System.exit(0);
        }
        System.out.println();

        for (int i = 0; i < num_of_players; i++) {
            System.out.print("Please insert player " + (i + 1) + " name: ");
            array_of_players.add(new Player(scanner.nextLine(), 500));
        }
        System.out.println();
    }
}
