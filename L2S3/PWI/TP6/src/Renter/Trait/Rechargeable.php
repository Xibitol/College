<?php

namespace L2S3PWI\Renter\Trait;

trait Rechargeable{

    private float $valeurEnergie = 0;

	// GETTERS
	public function getCharge(): float{ return $this->valeurEnergie; }

	// SETTERS
    public function recharger(): void{
        $this->valeurEnergie = 100;
    }
}