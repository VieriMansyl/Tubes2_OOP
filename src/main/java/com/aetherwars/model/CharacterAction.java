package com.aetherwars.model;

public interface CharacterAction {
    double[][] typeEffectiveness =
    /* Atk\Def */  /* Ovw */ /* Net */ /* End */
        /* Ovw */ {{   1d   ,   0.5   ,   2d   },
        /* Net */  {   2d   ,   1d    ,   0.5  },
        /* End */  {   0.5  ,   2d    ,   1d   }};

    void attack(Character target);
}
