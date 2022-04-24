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
	 * coock dengan yang ada pada container
	 */
	public void removeCard(T card) {
		for (int i = 0; i < cards.size(); ++i) {
			if (cards.get(i).getId() == card.getId()) {
				cards.remove(i);
				break;
			}
		}
	}

	public List<T> getCards() {
		return cards;
	}
}