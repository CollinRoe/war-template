import java.util.List;
import java.util.ArrayList;
/**
 * War game class
 *
 * @author Mr. Jaffe
 * @version 2022-10-19
 */
public class War
{
    /**
     * Constructor for the game
     * Include your initialization here -- card decks, shuffling, etc
     * Run the event loop after you've done the initializations
     */

    private Deck warDeck;
    private Deck player0;
    private Deck player1;

    public War()
    {
        // Initializations here...
        warDeck = new Deck();

        // ...then run the event loop
        this.runEventLoop();
    }

    /**
     * This is the game's event loop. The code in here should come
     * from the War flowchart you created for this game
     */
    public void runEventLoop() {
        // Shuffle Deck
        this.warDeck.shuffle();

        // Deal the Decks - this is bad becuase there are too many cards delt
        //Deck[] deltDecks = this.warDeck.dealDeck();
        // Associate Decks with players
        //player0 = deltDecks[0];
        //player1 = deltDecks[1];

        // Work around for dealDeck() being broken - play with 2 shuffled decks
        player0 = new Deck();
        player0.shuffle();

        player1 = new Deck();
        player1.shuffle();

        // Loop until one player has no cards
        while (player0.getDeckSize() > 0 && player1.getDeckSize() > 0)
        {
            // Flip top cards for each player

            Card topCard0 = player0.dealCardFromDeck();
            Card topCard1 = player1.dealCardFromDeck();

            // Comapres top cards 
            //goes to war or choose a winner - code in method below
            this.CompareCards(topCard0, topCard1);
            System.out.println("player 0 card count "+ player0.getDeckSize());
            System.out.println("player 1 card count "+ player1.getDeckSize());

        }
        if (player0.getDeckSize() == 0)
        {
            System.out.println("GAME OVER - PLAYER 1 WINS! GG");
        }

        if (player1.getDeckSize() == 0)
        {
            System.out.println("GAME OVER - PLAYER 0 WINS! GG");
        }
    }

    private void CompareCards (Card topCard0, Card topCard1)
    {

        // Record the winner in case there is a war in a war
        String warWinner = "";

        if (topCard0.getRank() < topCard1.getRank())
        {
            // Player 1 WINS
            System.out.println("WAR - PLAYER 1 WINS! "+ topCard1.getFace() + topCard1.getSuit() +" > "+ topCard0.getFace() + topCard0.getSuit());
            warWinner = "PLAYER1";
        }
        else if (topCard0.getRank() > topCard1.getRank())
        {
            // Player 0 WINS
            System.out.println("WAR - PLAYER 0 WINS! "+ topCard0.getFace() + topCard0.getSuit() +" > "+ topCard1.getFace() + topCard1.getSuit());
            warWinner = "PLAYER0";
        }
        else if (topCard0.getRank() == topCard1.getRank())
        {
            // THIS IS WAR - code in method below
            System.out.println("WAR " + topCard1.getFace() + topCard1.getSuit() +" = "+ topCard0.getFace() + topCard0.getSuit());
            warWinner = War(topCard0, topCard1);
        }
        
        // Give cards to the winner
        if (warWinner == "PLAYER1") 
        {
            // Player 1 WINS - gets top cards
            this.player1.addCardToDeck(topCard1);
            this.player1.addCardToDeck(topCard0);
        }
        else if (warWinner == "PLAYER0") 
        {
            // Player 0 WINS - gets top cards
            this.player0.addCardToDeck(topCard1);
            this.player0.addCardToDeck(topCard0);
        }
        else
        {
           System.out.println("NO ONE WINS");
        }

    }

    private String War (Card topCard0, Card topCard1)
    {
        try
        {
            // Player 0 does not have enough cards for war = Player 1 wins
            if (player0.getDeckSize() <= 4)
            {
                return "PLAYER1";
            }
            // Player 1 does not have enough cards for war = Player 0 wins
            if (player1.getDeckSize() <= 4)
            {
                return "PLAYER0";
            }
            
            // Get 3 cards from each player
            Card player0War1 = player0.dealCardFromDeck();
            Card player1War1 = player1.dealCardFromDeck();
            Card player0War2 = player0.dealCardFromDeck();
            Card player1War2 = player1.dealCardFromDeck();
            Card player0War3 = player0.dealCardFromDeck();
            Card player1War3 = player1.dealCardFromDeck();

            // Get War Compare Cards
            Card player0War4 = player0.dealCardFromDeck();
            Card player1War4 = player1.dealCardFromDeck();

            // Record the winner in case there is a war in a war
            String warWinner = "";

            if (player0War4.getRank() < player1War4.getRank())
            {
                // Player 1 WINS
                System.out.println("WAR - PLAYER 1 WINS! "+ player1War4.getFace() + player1War4.getSuit() +" > "+ player0War4.getFace() + player0War4.getSuit());
                warWinner = "PLAYER1";
            }
            else if (player0War4.getRank() > player1War4.getRank())
            {
                // Player 0 WINS
                System.out.println("WAR - PLAYER 0 WINS! "+ player0War4.getFace() + player0War4.getSuit() +" > "+ player1War4.getFace() + player1War4.getSuit());
                warWinner = "PLAYER0";
            }

            if (player0War4.getRank() == player1War4.getRank())
            {
                //This is a War in a War
                System.out.println("WAR IN A WAR " + player1War4.getFace() + player1War4.getSuit() +" = "+ player0War4.getFace() + player0War4.getSuit());
                //gets the winner of this war so we can assign the cards from the past war
                warWinner = War(player0War4, player1War4);
            }

            if (warWinner == "PLAYER1") 
            {
                // Player 1 WINS and gets all of the cards in this war
                this.player1.addCardToDeck(player0War1);
                this.player1.addCardToDeck(player1War1);
                this.player1.addCardToDeck(player0War2);
                this.player1.addCardToDeck(player1War2);
                this.player1.addCardToDeck(player0War3);
                this.player1.addCardToDeck(player1War3);
                this.player1.addCardToDeck(player0War4);
                this.player1.addCardToDeck(player1War4);
            }
            else if (warWinner == "PLAYER0") 
            {
                // Player 0 WINS and gets all of the cards in this war
                this.player0.addCardToDeck(player0War1);
                this.player0.addCardToDeck(player1War1);
                this.player0.addCardToDeck(player0War2);
                this.player0.addCardToDeck(player1War2);
                this.player0.addCardToDeck(player0War3);
                this.player0.addCardToDeck(player1War3);
                this.player0.addCardToDeck(player0War4);
                this.player0.addCardToDeck(player1War4);
            }

            // Pass the winner up to the previous War calls
            // This means that all cards in this war will go to the winner
            return warWinner;
        }
        catch(Exception e)
        {
            //returns if player does not have enough cards to finish the war
            return "";
        }
    }

    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }
}
