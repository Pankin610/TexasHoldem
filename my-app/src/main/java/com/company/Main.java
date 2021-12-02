package com.company;

import java.io.Console;
import java.io.IOException;
import java.util.*;

public class Main {
    public static int num_of_players;
    public static ArrayList<Player> array_of_players = new ArrayList<>();
    public static Stack<Card> deck = Card.getNewDeck();
    public static ArrayList<Card> table = new ArrayList<>();
    public static int pot = 0;

    public static void SetPlayersCards() {
        for (Player player : array_of_players) {
            player.SetFirstCard(deck.pop());
            player.SetSecondCard(deck.pop());
        }
    }

    public static void SetNewCard() {
        table.add(deck.pop());
    }

    public static Boolean InGame() {
        int counter = 0;
        for (Player player : array_of_players) {
            if (player.GetInGame()) {
                if (counter == 1) {
                    return true;
                }
                counter++;
            }
        }
        return false;
    }

    public static void Betting() {
        boolean same_bet = false;
        while (!same_bet) {
            for (Player player : array_of_players) {
                clearConsole();
                if (!player.IsPass() && player.InGame()) {
                    // print Table
                    // print your cards
                    int total_bet = 0;
                    for (Player player1 : array_of_players) {
                        total_bet = Math.max(total_bet, player1.getRound_bet());
                    }
                    pot += player.MakeBet(total_bet - player.getRound_bet());
                }
            }
            HashSet<Integer> counter = new HashSet<Integer>();
            for (Player player : array_of_players) {
                if (!player.IsPass() && player.InGame()) {
                    counter.add(player.getRound_bet());
                }
            }
            same_bet = (counter.size() == 1);
        }
    }

    public static void PrintTable() {
        System.out.println();
        System.out.print("Cards on table: ");
        for (Card card : table) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public static void GiveMoney() {
        // TODO: Giving the pot to winner(s)

        ArrayList<Player> winners = new ArrayList<>();
        int cur_max_comb = 0;
        for (Player player : array_of_players) {
            if (player.IsPass() || player.getRound_bet() == 0) {
                continue;
            }
            ArrayList<Card> player_cards = (ArrayList<Card>)table.clone();
            player_cards.add(player.FCard());
            player_cards.add(player.SCard());

            int best_comb = Combination.getBestCombination(player_cards);
            if (best_comb > cur_max_comb) {
                cur_max_comb = best_comb;
                winners.clear();
            }
            if (best_comb == cur_max_comb) {
                winners.add(player);
            }            
        }

        System.out.println("The round has ended! Here are the winners:");
        for (Player player : winners) {
            System.out.print(player.Name() + " ");
        }
        System.out.println("");
        System.out.println("Each wins " + pot / winners.size() + " coins.");
 
        for (Player player : winners) {
            player.Win(pot / winners.size());
        }

        pot = 0;
        for (Player player : array_of_players) {
            player.setRound_bet(0);
        }
        table.clear();
    }

    public static void AfterCards(Scanner scanner) {
        System.out.println("Enter any key...");
        scanner.nextLine();
        System.out.println();
    }

    public static void clearConsole() {
        System.out.println("Cos poszlo nie tak");
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {
            System.out.println("Cos poszlo nie tak");
        }
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

        Boolean game = true;

        while (InGame()) {
            SetPlayersCards();
            clearConsole();
            Betting(); SetNewCard(); SetNewCard(); SetNewCard(); PrintTable(); AfterCards(scanner);
            clearConsole();
            Betting(); SetNewCard(); PrintTable(); AfterCards(scanner);
            Betting(); SetNewCard(); PrintTable(); AfterCards(scanner);
            Betting();
            GiveMoney();
        }
    }
}
