<?php

namespace L2S3PWI\App\Config;

class Database{

	private const HOST = "127.0.0.1";
	private const PORT = "3306";
	private const USER = "root";
	private const PASSWORD = "";

	private string $db = "pwi_cc2_yelo";
	private ?\PDO $connection = null;

	public function __construct(string $db){
		$this->db = $db;
	}

	// GETTERS
	public function getConnexion(): \PDO{
		if(!isset($this->connection))
			$this->connection = new \PDO(
				sprintf("mysql:host=%s;port=%s;dbname=%s",
					Database::HOST, Database::PORT, $this->db
				),
				Database::USER,
				Database::PASSWORD,
				[
					\PDO::ATTR_DEFAULT_FETCH_MODE => \PDO::FETCH_ASSOC,
					\PDO::ATTR_ERRMODE => \PDO::ERRMODE_EXCEPTION,
					\PDO::MYSQL_ATTR_INIT_COMMAND => "SET CHARACTER SET utf8;"
				]
			);

		return $this->connection;
	}
}