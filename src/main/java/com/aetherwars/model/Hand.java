package com.aetherwars.model;

import java.util.*;

public class Hand extends Container<Card> {

	public Hand(Deck deck) {
		cards = new ArrayList<>();
		/*
		 * inisiasi kartu awal pemain sebanyak 3 buah
		 * kartu (sesuai dengan yang dipresentasikan asisten)
		 */
		for (int i = 0; i < Math.min(3, deck.getCards().size()); ++i) {
			cards.add(deck.getTop());
		}
	}

	@Override
	public void addCard(Card card) throws HandOverException {
		if (cards.size() > 5) {
			throw new HandOverException("The hand has more than 5 cards");
		}
		cards.add(card);
	}
}