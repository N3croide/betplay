# Liga BetPlay

LA FEDERACION COLOMBIANA DE FUTBOL DESEA CREAR UN PROGRAMA QUE LE PERMITA
LLEVAR EL CONTROL Y REGISTRO DE TODOS LOS EQUIPOS QUE SE ENCUENTRAN
PARTICIPANDO EN LA LIGA BETPLAY. LA FEDERACION DESEA ORGANIZAR EL TORNEO
TENIENDO EN CUENTA LA SIGUIENTE INFORMACION:

- NOMBRE DEL EQUIPO
- PJ
- PG
- PP
- PE
- GF
- GC
- TP
REQUERIMIENTOS:
1. EL PROGRAMA DEBE PERMITIR REGISTRAR CADA UNO DE LOS EQUIPOS QUE VAN A
    PARTICIPAR EN EL TORNEO, TENGA EN CUENTA QUE AL MOMENTO DE REGISTRAR CADA
    EQUIPO LAS VARIABLES DE PJ,PG,PP,PE,GF,GC,TP DEBEN SER 0
2. REGISTRO DE FECHA. EL REGISTRO DE FECHAS SE DEBE INGRESAR LOS EQUIPOS
    QUE SE ENFRENTARON. EL PROGRAMA DEBE PERMITIR SELECCIONAR QUE EQUIPO JUGO DE
    LOCAL Y QUE EQUIPO JUGO DE VISITANTE. ADEMAS SE DEBE REGISTRAR EL MARCADOR DE
    CADA UNO DE LOS EQUIPOS. EL PROGRAMA DEBE DETERMINAR CUAL FUE EL EQUIPO
    GANADOR Y CUAL ES EL PERDEDOR Y ASIGNAR LOS VALORES CORRESPONDIENTES EN LA
    TABLA DE POSICIONES. RECUERDE QUE CADA PARTIDO GANADO EQUIVALE A 3 PUNTO
    Y LOS EMPATADOS EQUIVALEN A 1 PUNTO.
3. REPORTES
    A. NOMBRE DEL EQUIPO QUE MAS GOLES ANOTO
    B. NOMBRE DEL EQUIPO QUE MAS PUNTOS TIENE
    C. NOMBRE DEL EQUIPO QUE MAS PARTIDOS GANO
    D. TOTAL DE GOLES ANOTADOS POR TODOS LOS EQUIPOS
    E. PROMEDIO DE GOLES ANOTADOS EN EL TORNEOLA FEDERACION COLOMBIANA DE FUTBOL .

------

DATA BASE

DDL

```mysql
-- betplaydb.staff definition

CREATE TABLE `staff` (
  `idStaff` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idStaff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- betplaydb.professional definition

CREATE TABLE `professional` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `lastName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NOT NULL,
  `staffType` enum('Medical','Technical') DEFAULT NULL,
  `idStaff` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_professional_staff` (`idStaff`),
  CONSTRAINT `FK_professional_staff` FOREIGN KEY (`idStaff`) REFERENCES `staff` (`idStaff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- betplaydb.team definition

CREATE TABLE `team` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `playedMatches` int NOT NULL,
  `wonMatches` int NOT NULL,
  `drawMatches` int NOT NULL,
  `totalPoints` int NOT NULL,
  `idStaff` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `idStaff` (`idStaff`),
  CONSTRAINT `FK_team_staff` FOREIGN KEY (`idStaff`) REFERENCES `staff` (`idStaff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- betplaydb.fmatch definition

CREATE TABLE `fmatch` (
  `matchId` int NOT NULL AUTO_INCREMENT,
  `localTeamId` int DEFAULT NULL,
  `visitorTeamId` int DEFAULT NULL,
  `localTeamGoals` int NOT NULL,
  `visitorTeamGoals` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`matchId`),
  KEY `local_team_id` (`localTeamId`),
  KEY `visitor_team_id` (`visitorTeamId`),
  CONSTRAINT `fmatch_ibfk_1` FOREIGN KEY (`localTeamId`) REFERENCES `team` (`id`),
  CONSTRAINT `fmatch_ibfk_2` FOREIGN KEY (`visitorTeamId`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- betplaydb.player definition

CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idTeam` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `dorsal` int NOT NULL,
  `nationality` varchar(100) NOT NULL,
  `goalsScored` int NOT NULL,
  `totalRedCards` int NOT NULL,
  `totalYellowCards` int NOT NULL,
  `lastName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_player_team` (`idTeam`),
  CONSTRAINT `FK_player_team` FOREIGN KEY (`idTeam`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```





Create procedure to update the statistics of the teams

```mysql
DELIMITER //

CREATE PROCEDURE updateTeamStatistics(
    IN pMatchId INT,
    IN pLocalTeamId INT,
    IN pVisitorTeamId INT,
    IN pLocalTeamGoals INT,
    IN pVisitorTeamGoals INT
)
BEGIN
    DECLARE localTeamPoints INT DEFAULT 0;
    DECLARE visitorTeamPoints INT DEFAULT 0;

    IF pLocalTeamGoals > pVisitorTeamGoals THEN
        SET localTeamPoints = 3;
    ELSEIF pLocalTeamGoals < pVisitorTeamGoals THEN
        SET visitorTeamPoints = 3;
    ELSE
        SET localTeamPoints = 1;
        SET visitorTeamPoints = 1;
    END IF;

    INSERT INTO match (
        matchId, localTeamId, visitorTeamId, localTeamGoals, visitorTeamGoals, date
    ) VALUES (
        pMatchId, pLocalTeamId, pVisitorTeamId, pLocalTeamGoals, pVisitorTeamGoals, CURDATE()
    );

    UPDATE team
    SET 
        playedMatches = playedMatches + 1,
        wonMatches = wonMatches + (CASE WHEN localTeamPoints = 3 THEN 1 ELSE 0 END),
        drawMatches = drawMatches + (CASE WHEN localTeamPoints = 1 THEN 1 ELSE 0 END),
        totalPoints = totalPoints + localTeamPoints
    WHERE 
        teamId = pLocalTeamId;

    UPDATE team
    SET 
        playedMatches = playedMatches + 1,
        wonMatches = wonMatches + (CASE WHEN visitorTeamPoints = 3 THEN 1 ELSE 0 END),
        drawMatches = drawMatches + (CASE WHEN visitorTeamPoints = 1 THEN 1 ELSE 0 END),
        totalPoints = totalPoints + visitorTeamPoints
    WHERE 
        teamId = pVisitorTeamId;
END //

DELIMITER ;

```

