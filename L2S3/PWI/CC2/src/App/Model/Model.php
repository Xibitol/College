<?php

namespace L2S3PWI\App\Model;

use L2S3PWI\App\Config\Database;

class Model{

	private string $table;
	private Database $connexion;

	public function __construct(Database $connexion, string $table){
		$this->connexion = $connexion;
		$this->table = $table;
	}

	// FUNCTIONS
	/**
	 * @param array{
	 *     conditions: array<string, mixed>
	 * } $data
	 * @return array<array<string, mixed>>
	 */
	public function find(array $data = []): array{
		$sql = "SELECT * FROM %s";
		$params = [];

		if(isset($data["conditions"]) && count($data["conditions"]) > 0)
			$sql .= sprintf(" WHERE %s", implode(" AND ",
				array_map(function($cond, $value) use (&$params){
					$name = rtrim($cond, "!~<>=");

					$params[$name] = $value;

					return sprintf("%s:%s", $cond, $name);
				}, array_keys($data["conditions"]), $data["conditions"])
			));

		try{
			$stmt = $this->connexion->getConnexion()->prepare(
				sprintf($sql, $this->table)
			);
			$stmt->execute($params);
			$res = $stmt->fetchAll();

			if(is_array($res)) return $res;
		}catch(\PDOException $_){}

		return [];
	}

	public function update(array $values): bool{
		$sql = "UPDATE %s SET %s WHERE id=:id";

		try{
			$stmt = $this->connexion->getConnexion()->prepare(sprintf($sql,
				$this->table,
				implode(", ", array_map(
					function($p){
						return sprintf("`%1\$s`=:%1\$s", $p);
					},
					array_filter(array_keys($values), function($p){
						return strcmp($p, "id") !== 0;
					}))
				)
			));
			$stmt->execute($values);

			if($stmt->rowCount() === 1) return true;
		}catch(\PDOException $_){}

		return false;
	}

	public function delete(int $identifier): bool{
		$sql = "DELETE FROM %s WHERE id=:id";

		try{
			$stmt = $this->connexion->getConnexion()->prepare(sprintf($sql,
				$this->table
			));
			$stmt->execute([ "id" => $identifier ]);

			if($stmt->rowCount() === 1) return true;
		}catch(\PDOException $_){}

		return false;
	}
}