<?php

/**
 * @param string $server
 * @param string $user
 * @param string $password
 * @param string $db
 * @return PDO
 */
function connectDB(
	string $server = "127.0.0.1:3306",
	string $user = "root",
	string $password = "",
	string $db = "pwi_tp3_Annuaire"
): PDO{
	return new PDO(
		sprintf("mysql:host=%s;port=%s;dbname=%s",
			explode(":", $server)[0], explode(":", $server)[1], $db
		),
		$user,
		$password,
		[
			PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
			PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
			PDO::MYSQL_ATTR_INIT_COMMAND => "SET CHARACTER SET utf8;"
		]
	);
}