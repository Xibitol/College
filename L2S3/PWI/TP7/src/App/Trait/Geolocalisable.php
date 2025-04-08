<?php

namespace L2S3PWI\App\Trait;

trait Geolocalisable{

    private float $longitude = 0;
	private float $latitude = 0;

	// GETTERS
	public function getLongitude(): float{ return $this->longitude; }
    public function getLatitude(): float{ return $this->latitude; }

	// SETTERS
    public function setLongitude(float $longitude): void{
        $this->longitude = $longitude;
    }
    public function setLatitude(float $latitude): void{
        $this->latitude = $latitude;
    }
}