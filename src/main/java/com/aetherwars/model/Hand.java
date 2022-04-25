package com.aetherwars.model;

import java.util.*;

class HandOverException extends Exception {
	public HandOverException(String str) {
		super(str);
	}
}

class Hand extends Container<Card> {

	public Hand(Deck deck) {
		cards = new ArrayList<Card>();
		/*
		 * inisiasi kartu awal pemain sebanyak 3 buah
		 * kartu (sesuai dengan yang dipresentasikan asisten)
		 */
		for (int i = 0; i < 3; ++i) {
			cards.add(deck.getTop());
		}
	}

	@Override
	public void addCard(Card card) throws HandOverException {
		cards.add(card);
		if (cards.size() > 5) {
			throw new HandOverException("The hand has more than 5 cards");
		}
	}
}