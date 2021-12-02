package com.company;

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

    public static void EmptyScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void PrintPlayerMoves(boolean[] made_move, Player you) {
        int it = -1;
        for (Player player : array_of_players) {
            it++;
            if (player == you) {
                continue;
            }
            System.out.print(player.Name() + ": ");
            if (!made_move[it] && player.getRound_bet() == 0) {
                System.out.println("hasn't moved yet");
            } else if (!made_move[it] && player.getRound_bet() == 10) {
                System.out.println("10 coins SB");
            } else if (!made_move[it] && player.getRound_bet() == 20) {
                System.out.println("20 coins BB");
            } else if (player.IsPass()) {
                System.out.println("Pass");
            } else if (player.Coins() == 0) {
                System.out.println("All-In for " + player.getRound_bet() + " coins");
            } else {
                System.out.println(player.getRound_bet() + " coins");
            }
        }
    }

    public static void Betting() {
        boolean[] made_move = new boolean[array_of_players.size()];
        int index = 0;
        boolean same_bet = false;
        int num_of_player = 1;

        while (!same_bet) {
            for (Player player : array_of_players) {
                //small and big blinds
                if (num_of_player == 1) {
                    if (player.Coins() >= 10) {
                        player.Bet(10);
                        pot += 10;
                    } else {
                        player.Pass();
                    }
                    index++;
                    num_of_player++;
                    continue;
                } else if (num_of_player == 2) {
                    if (player.Coins() >= 20) {
                        player.Bet(20);
                        pot += 20;
                    } else {
                        player.Pass();
                    }
                    index++;
                    num_of_player++;
                    continue;
                }


                if (!player.IsPass() && player.Coins() > 0) {
                    EmptyScreen();
                    System.out.println(player.Name() + ", prepare for your round!");
                    Scanner scanner = new Scanner(System.in);
                    WaitForEnter(scanner);
                    EmptyScreen();
                    PrintPlayerMoves(made_move, player);
                    if (!table.isEmpty()) {
                        PrintTable();
                    }
                    int total_bet = 0;
                    for (Player player1 : array_of_players) {
                        total_bet = Math.max(total_bet, player1.getRound_bet());
                    }
                    pot += player.MakeBet(total_bet - player.getRound_bet());
                }

                if (index == array_of_players.size()) {
                    index = 0;
                }
                made_move[index] = true;
                index++;

                boolean every1_made_move = true;
                HashSet<Integer> counter = new HashSet<Integer>();
                int it = 0;

                for (Player player1 : array_of_players) {
                    if (!made_move[it++]) {
                        every1_made_move = false;
                        break;
                    }
                    if (!player1.IsPass()) {
                        counter.add(player1.getRound_bet());
                    }
                }

                same_bet = (counter.size() == 1 || counter.size() == 0);
                if (!every1_made_move) {
                    same_bet = false;
                }
                if (same_bet) {
                    break;
                }
            }
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
            System.out.println();
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

        System.out.println();
        PrintTable();
        System.out.println();
        System.out.println("Your hands:");
        for (Player player : winners) {
            System.out.print(player.Name() + ": ");
            player.PrintHand();
        }

        pot = 0;
        for (Player player : array_of_players) {
            player.setRound_bet(0);
        }
        table.clear();
    }

    public static void WaitForEnter(Scanner scanner) {
        System.out.println("Enter any key...");
        scanner.nextLine();
        System.out.println();
    }

    public static boolean EnoughPlayers() {
        int counter = 0;
        for (Player player : array_of_players) {
            counter += (!player.IsPass()) ? 1 : 0;
        }
        return counter > 1;
    }

    public static void MovePlayersAroundTable() {
        ArrayList<Player> temp = new ArrayList<>();
        for (Player player : array_of_players) {
            player.Unpass();
            if (player.InGame()) {
                temp.add(player);
            }
        }
        int it = 0;
        ArrayList<Player> temp2 = new ArrayList<>();
        for (Player player : temp) {
            if (it++ > 0) {
                temp2.add(player);
            }
        }
        it = 0;
        for (Player player : temp) {
            if (it++ == 0) {
                temp2.add(player);
            }
        }
        array_of_players = temp2;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        EmptyScreen();
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

        while (array_of_players.size() > 1) {
            SetPlayersCards();
            Betting(); EmptyScreen();
            if (EnoughPlayers()) {
                SetNewCard(); SetNewCard(); SetNewCard(); WaitForEnter(scanner);
            } else {
                SetNewCard(); SetNewCard(); SetNewCard(); SetNewCard(); SetNewCard();
                EmptyScreen();
                GiveMoney();
                WaitForEnter(scanner);
                MovePlayersAroundTable();
                continue;
            }
            Betting(); EmptyScreen();
            if (EnoughPlayers()) {
                SetNewCard(); WaitForEnter(scanner);
            } else {
                SetNewCard(); SetNewCard();
                EmptyScreen();
                GiveMoney();
                WaitForEnter(scanner);
                MovePlayersAroundTable();
                continue;
            }
            Betting(); EmptyScreen();
            if (EnoughPlayers()) {
                SetNewCard(); WaitForEnter(scanner);
            } else {
                SetNewCard();
                EmptyScreen();
                GiveMoney();
                WaitForEnter(scanner);
                MovePlayersAroundTable();
                continue;
            }
            Betting(); EmptyScreen();
            GiveMoney();
            WaitForEnter(scanner);
            MovePlayersAroundTable();
        }
        EmptyScreen();
        String winner = null;
        for (Player player : array_of_players) {
            if (player.InGame()) {
                winner = player.Name();
                break;
            }
        }
        System.out.println("The final winner is " + winner + ", congratulations!");
        System.out.println();
    }
}
