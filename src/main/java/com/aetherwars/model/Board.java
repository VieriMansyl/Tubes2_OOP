package com.aetherwars.model;

import java.util.*;

public class Board extends Container<Character> {

	public Board() {
		cards = new ArrayList<>();
		// inisialisasi slot kartu
		for (int i = 0; i < 5; ++i) {
			cards.add(null);
		}
	}

	public boolean isEmpty(){
		boolean flag = true;
		for (int i = 0; i < 5 & flag; ++i) {
			if (cards.get(i) != null) flag = false;
		}
		return  flag;
	}

	// set kartu ke lokasi spesifik pada board
	public void setCard(Character card, int index) throws IllegalCardPlacementException {
		if (getCard(index) != null) {
			throw new IllegalCardPlacementException("Card slot is not empty");
		}

		cards.set(index, card);
	}

	/* menghancurkan kartu pada lokasi i dengan cara menggantinya
	   menjadi null */
	public void destroyCard(int index) {
		cards.set(index, null);
	}

}
