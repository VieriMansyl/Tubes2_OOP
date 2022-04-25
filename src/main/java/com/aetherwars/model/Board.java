package com.aetherwars.model;

import java.util.*;

class IllegalCardPlacementException extends Exception {
	public IllegalCardPlacementException(String str) {
		super(str);
	}
}

/*
 * assumsi punya 2 buah board variabel pada main
 */
public class Board extends Container<Character> {

	public Board() {
		cards = new ArrayList<Character>();

		/*
		 * menggunakan null sebagai inisialisasi variabel card
		 * null digunakan untuk menyatakan bahwa kartu tersebut
		 * kosong
		 */
		for (int i = 0; i < 5; ++i) {
			cards.add(null);
		}
	}

	/*
	 * set kartu ke lokasi spesifik pada board
	 */
	public void setCard(Character card, int index) throws IllegalCardPlacementException {
		/*
		 * apabila kartunya adalah spell, maka akan dilakukan
		 * pengecekan apakah terdapat kartu bertipe karakter pada
		 * board, dan melakukan throw error apabila none
		 */
		if (card.getType() == CardType.SPELL) {
			boolean hasCharacter = false;
			for (int i = 0; i < cards.size(); ++i) {
				if (cards.get(i).getType() == CardType.CHARACTER) {
					hasCharacter = true;
					break;
				}
			}
			if (!hasCharacter) {
				throw new IllegalCardPlacementException("Placing Spell card when there's no character");
			}
		}
		cards.set(index, card);
	}

	/*
	 * menghancurkan kartu pada lokasi i dengan cara menggantinya
	 * menjadi null
	 */
	public void destroyCard(int index) {
		cards.set(index, null);
	}

}
