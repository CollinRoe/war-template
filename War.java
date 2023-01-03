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
        
        // Deal the Decks - this is bad somehow
        //Deck[] deltDecks = this.warDeck.dealDeck();
        // Associate Decks with players
        //player0 = deltDecks[0];
        //player1 = deltDecks[1];
        
        // Work around for dealDeck() being broken - play with 2 shuffled decks
        player0 = new Deck();
        player0.shuffle();
        
        player1 = new Deck();
        player1.shuffle();
        
        
        while (player0.getDeckSize() >= 1 && player1.getDeckSize() >= 1)
        {

            Card topCard0 = player0.dealCardFromDeck();
            Card topCard1 = player1.dealCardFromDeck();
            
            // Comapre top cords - war or choose a winner
            this.CompareCards(topCard0, topCard1);


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
            if (topCard0.getRank() == topCard1.getRank())
            {
                // THIS IS WAR
                System.out.println("WAR " + topCard1.getFace() + topCard1.getSuit() +" = "+ topCard0.getFace() + topCard0.getSuit());
                War(topCard0, topCard1);
            }
            if (topCard0.getRank() < topCard1.getRank())
            {
                // Player 1 WINS
                System.out.println("PLAYER 1 WINS! " + topCard1.getFace() + topCard1.getSuit() +" > "+ topCard0.getFace() + topCard0.getSuit());
                this.player1.addCardToDeck(topCard1);
                this.player1.addCardToDeck(topCard0);
            }
            else if (topCard0.getRank() > topCard1.getRank())
            {
                // Player 0 WINS
                System.out.println("PLAYER 0 WINS! "+ topCard0.getFace() + topCard0.getSuit() +" > "+ topCard1.getFace() + topCard1.getSuit());
                this.player0.addCardToDeck(topCard1);
                this.player0.addCardToDeck(topCard0);
            }
    }
    
    private void War (Card topCard0, Card topCard1)
    {
        try
        {
        // Get 3 cards from each player
                Card player0War1 = player0.dealCardFromDeck();
                Card player1War1 = player1.dealCardFromDeck();
                Card player0War2 = player0.dealCardFromDeck();
                Card player1War2 = player1.dealCardFromDeck();
                Card player0War3 = player0.dealCardFromDeck();
                Card player1War3 = player1.dealCardFromDeck();
                
                // Get Compare Cards
                Card player0War4 = player0.dealCardFromDeck();
                Card player1War4 = player1.dealCardFromDeck();
                
                if (player0War4.getRank() == player1War4.getRank())
                {
                    //This is a War in a War
                    System.out.println("WAR IN A WAR " + player1War4.getFace() + player1War4.getSuit() +" = "+ player0War4.getFace() + player0War4.getSuit());
                 War(player0War4, player1War4);
                }
            if (player0War4.getRank() < player1War4.getRank())
            {
                // Player 1 WINS
                System.out.println("WAR - PLAYER 1 WINS! "+ player1War4.getFace() + player1War4.getSuit() +" > "+ player0War4.getFace() + player0War4.getSuit());
                this.player1.addCardToDeck(player0War1);
                this.player1.addCardToDeck(player1War1);
                this.player1.addCardToDeck(player0War2);
                this.player1.addCardToDeck(player1War2);
                this.player1.addCardToDeck(player0War3);
                this.player1.addCardToDeck(player1War3);
                this.player1.addCardToDeck(player0War4);
                this.player1.addCardToDeck(player1War4);
            }
            else if (player0War4.getRank() > player1War4.getRank())
            {
                // Player 0 WINS
                System.out.println("WAR - PLAYER 0 WINS! "+ player0War4.getFace() + player0War4.getSuit() +" > "+ player1War4.getFace() + player1War4.getSuit());
                this.player0.addCardToDeck(player0War1);
                this.player0.addCardToDeck(player1War1);
                this.player0.addCardToDeck(player0War2);
                this.player0.addCardToDeck(player1War2);
                this.player0.addCardToDeck(player0War3);
                this.player0.addCardToDeck(player1War3);
                this.player0.addCardToDeck(player0War4);
                this.player0.addCardToDeck(player1War4);
            }
        }
        catch(Exception e)
        {return;}
    }
    
    /**
     * The main method is called when Java starts your program
     */
    public static void main(String[] args) {
        War war = new War();
    }
}
