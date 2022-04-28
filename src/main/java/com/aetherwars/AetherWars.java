package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.aetherwars.controller.*;
import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import com.aetherwars.util.CSVReader;

public class AetherWars extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String SPELL_LEVEL_CSV_FILE_PATH = "card/data/spell_level.csv";
  private static final String SPELL_MORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
  private static final String SPELL_POTION_CSV_FILE_PATH = "card/data/spell_ptn.csv";
  private static final String SPELL_SWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";


    public List<Card> loadCards() throws IOException, URISyntaxException {
        File CSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader cardReader = new CSVReader(CSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> characterRows = cardReader.read();
        for (String[] row : characterRows) {
            Character c = new Character(Integer.parseInt(row[0]), row[1], CharacterType.valueOf(row[2]), row[3], row[4], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[8]), Integer.parseInt(row[9]));
            Card.availableCard.add(c);
        }

        CSVFile = new File(getClass().getResource(SPELL_LEVEL_CSV_FILE_PATH).toURI());
        cardReader = new CSVReader(CSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> spellLevelRows = cardReader.read();
        for (String[] row : spellLevelRows) {
            int temp = -1;
            if (row[1].equals("Level Up")) temp = 1;
            LevelSpell l = new LevelSpell(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]));
            Card.availableCard.add(l);
        }

        CSVFile = new File(getClass().getResource(SPELL_MORPH_CSV_FILE_PATH).toURI());
        cardReader = new CSVReader(CSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> spellMorphRows = cardReader.read();
        for (String[] row : spellMorphRows) {
            Morph m = new Morph(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[5]), Integer.parseInt(row[4]));
            Card.availableCard.add(m);
        }

        CSVFile = new File(getClass().getResource(SPELL_SWAP_CSV_FILE_PATH).toURI());
        cardReader = new CSVReader(CSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> spellSwapRows = cardReader.read();
        for (String[] row : spellSwapRows) {
            Swap s = new Swap(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), Integer.parseInt(row[5]));
            Card.availableCard.add(s);
        }

        CSVFile = new File(getClass().getResource(SPELL_POTION_CSV_FILE_PATH).toURI());
        cardReader = new CSVReader(CSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> spellPotionRows = cardReader.read();
        for (String[] row : spellPotionRows) {
            Potion p = new Potion(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), Integer.parseInt(row[5]), Integer.parseInt(row[6]) ,Integer.parseInt(row[7]));
            Card.availableCard.add(p);
        }
        return Card.availableCard;
    }

    public Deck loadDeck(String srcDeck) throws IOException, URISyntaxException {

        Deck tes = new Deck();
        File CSVFile = new File(getClass().getResource(srcDeck).toURI());
        CSVReader cardReader = new CSVReader(CSVFile, "\t");
        List<String[]> cardID = cardReader.read();
        for (String[] id : cardID){
            Card current = Card.getNewCard(Integer.parseInt(id[0]));
            try {
                tes.addCard(current);
            } catch (Exception e){
                //do nothing ?
            }
        }
        tes.shuffleCards();
        return tes;
    }

  @Override
  public void start(Stage stage) throws Exception {

//    try {
//      this.loadCards();
//      text.setText("Minecraft: Aether Wars!");
//    } catch (Exception e) {
//      text.setText("Failed to load cards: " + e);
//    }


    Player p1 = null;
    Player p2 = null;

      try {
          Card.availableCard = this.loadCards();
          Deck d1 = this.loadDeck("card/data/deck1.csv");
          Deck d2 = this.loadDeck("card/data/deck1.csv");

          Hand h1 = new Hand(d1);
          Hand h2 = new Hand(d2);

          Board b1 = new Board();
          Board b2 = new Board();

          p1 = new Player("Player1", d1,h1,b1);
          p2 = new Player("Player2", d2,h2,b2);

      } catch (Exception e) {
            System.out.println("Constructor error");
      }

      // set up the scene
      FXMLLoader loader = new FXMLLoader(getClass().getResource("views/board2.fxml"));
      Parent root = loader.load();

      BoardController.setInstance(loader);
      BoardController boardController = BoardController.getInstance();
//      BoardController boardController = BoardController(loader);
//      BoardController boardController = loader.getController();
//      boardController = loader.getController();
      boardController.setupBoardController(p1,p2);
      boardController.refreshBoard();

      Scene scene = new Scene(root);
      // set up the stage
      stage.setTitle("Minecraft: Aether Wars");
      stage.setScene(scene);
      stage.show();
    }

  public static void main(String[] args) {
    launch();
  }
}
