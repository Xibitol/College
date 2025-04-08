<?php

class PersonneManager{

	private Database $database;

	// SETTERS
	public function setDb(Database $database): void{
		$this->database = $database;
	}

	// FUNCTIONS
	public function add(Personne $p): bool{
		$stmt = $this->database->getConnexion()->prepare(
			"INSERT INTO Personne(id, nom, prenom) VALUES (:id, :nom, :prenom);"
		);

		return $stmt->execute($p->toArray());
	}

	/**
	 * @return Personne[]
	 */
	public function getAll(): array{
		$stmt = $this->database->getConnexion()->query(
			"SELECT * FROM Personne;"
		);
		$results = empty($stmt) ? [] : $stmt->fetchAll();

		foreach($results as $k => $v){
			$results[$k] = new Personne($v);
		}

		return $results;
	}

	public function get(int $id): ?Personne{
		$stmt = $this->database->getConnexion()->prepare(
			"SELECT * FROM Personne WHERE id=:id;"
		);
		$stmt->execute(["id" => $id]);
		$result = $stmt->fetch();

		return $result !== false ? new Personne($result) : null;
	}

	public function delete(Personne $p): bool{
		$stmt = $this->database->getConnexion()->prepare(
			"DELETE FROM Personne WHERE id=:id;"
		);

		return $stmt->execute(["id" =>  $p->getID()]);
	}

	public function update(Personne $p): bool{
		$stmt = $this->database->getConnexion()->prepare(
			"UPDATE Personne SET nom=:nom, prenom=:prenom WHERE id=:id;"
		);

		return $stmt->execute($p->toArray());
	}
}