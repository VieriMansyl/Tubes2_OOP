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
		 this.cards = new ArrayList<>();
	}

	public void shuffleCards() {
		Collections.shuffle(cards);
	}

	public List<Card> getTop3() {
		List<Card> temp = new ArrayList<>();
		for (int i = 0; i < Math.min(3, cards.size()); ++i) {
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