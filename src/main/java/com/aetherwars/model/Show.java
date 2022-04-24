package com.aetherwars.model;

import java.util.*;

/*
 * asumsikan show cuman di-create dua kali saja
 * satu untuk masing-masing player
 */

class Show extends Container {

	/*
	 * menggunakan deck reference untuk mengakses
	 * apa yang ada di dalam deck
	 */
	private Deck deck;

	/*
	 * menggunakan hand sebagai referensi untuk
	 * mengubah apa yang ada di dalam hand
	 */
	private Hand hand;

	public Show(Deck deck) {
		cards = new ArrayList<Card>();
	}

	/*
	 * fungsi ini akan mengisi kartu pada ArrayList
	 * dengan 3 kartu teratas dari deck
	 */
	public void fillShow() {
		cards = deck.getTop3();
	}

	/*
	 * fungsi ini akan menerima input dari UI yang kemudian
	 * akan menghilangkan kartu yang terpilih oleh player pada UI
	 * dari deck lalu menambahkannya pada hand
	 */
	public void chooseCard(Card card) throws HandOverException {
		deck.removeCard(card);
		hand.addCard(card);
	}
}