package com.aetherwars.model;

import java.util.*;

/*
 * asumsi tipe kartu nanti dimasukkan ke dalam
 * static variable dari astract class "Card"
 */

abstract public class Container<T extends Card> {
	protected List<T> cards;

	/*
	 * menambah kartu pada container
	 */
	public void addCard(T card) throws HandOverException{
		cards.add(card);
	}

	/*
	 * mengeluarkan kartu apabila id dari kartu tersebut
	 * cocok dengan yang ada pada container
	 */
	public void removeCard(T card) {
		cards.removeIf(c -> (c == card));
	}

	public T getCard(int index) {
		return cards.get(index);
	}

	public List<T> getCards() {
		return cards;
	}
}