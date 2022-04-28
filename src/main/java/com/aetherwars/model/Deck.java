package com.aetherwars.model;

import java.util.*;

class DeckCardAmountException extends Exception {
	public DeckCardAmountException(String str) {
		super(str);
	}
}

public class Deck extends Container<Card> {

	public Deck() {
		/*
		 * akan memanggil DeckCardAmountException apabila
		 * jumlah kartu tidak sesuai dengan requirement setelah
		 * melakukan pembacaan dari CSV
		 */
		 this.cards = new ArrayList<Card>();
	}

	public void shuffleCards() {
		Collections.shuffle(cards);
	}

	public List<Card> getTop3() {
		List<Card> temp = new ArrayList<Card>();
		for(int i = 0; i < 3; ++i) {
			if(i >= cards.size()) {
				break;
			}
			temp.add(cards.get(i));
		}
		return temp;
	}

	public Card getTop() {
		Card temp = cards.get(0);
		cards.remove(0);
		return temp;
	}
}