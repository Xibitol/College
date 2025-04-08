<?php

namespace L2S3PWI\Renter\Entity;

class ParcZoe implements \Countable{

	/** @var array<Zoe> */
	private array $mesZoe = [];

	public function __construct(){
		array_push($this->mesZoe,
			new Zoe("verdoyant", "BS-940-BK"),
			new Zoe("ocre", "BT-478-RW"),
			new Zoe("nacré", "AG-615-QF")
		);
	}

	// GETTERS
	/** @return array<Zoe> */
	public function getLesZoe(): array{ return $this->mesZoe; }
	public function count(): int{ return count($this->mesZoe); }
}