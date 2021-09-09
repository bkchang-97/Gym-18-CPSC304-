DROP TABLE ExercisePlanContainsExercise;
DROP TABLE DietPlanHasConsumable;
DROP TABLE Timeslot;
DROP TABLE TrainerTrainsUser;
DROP TABLE UserWithDietPlan;
DROP TABLE CertificateWithTrainer;
DROP TABLE UserFollowsExercisePlan;
DROP TABLE ExerciseGoal;
DROP TABLE ExerciseWithEquipment;
DROP TABLE DietPlan;
DROP TABLE ExercisePlan;
DROP TABLE Consumable;
DROP TABLE Equipment;
DROP TABLE Trainer;

CREATE TABLE Equipment
(equipName      CHAR(20)        NOT NULL,
 equipID        INTEGER         NOT NULL,
 PRIMARY KEY (equipID));

CREATE TABLE Consumable
(consName       CHAR(20)        NOT NULL,
 calPer100gmL   INTEGER         NOT NULL,
 PRIMARY KEY (consName));

CREATE TABLE ExercisePlan
(eplanName      CHAR(20)        NOT NULL,
 eplanID        INTEGER         NOT NULL,
 PRIMARY KEY (eplanID));

CREATE TABLE DietPlan
(dplanID        INTEGER         NOT NULL,
 dplanName      CHAR(20)        NOT NULL,
 PRIMARY KEY (dplanID));

CREATE TABLE Trainer
(trainerID      INTEGER         NOT NULL,
 trainerName    CHAR(20)        NOT NULL,
 trainerPhone   CHAR(15)        NOT NULL,
 PRIMARY KEY (trainerID));

CREATE TABLE ExerciseWithEquipment
(exrName        CHAR(20)        NOT NULL,
 equipmentID    INTEGER         NOT NULL,
 PRIMARY KEY (exrName),
 FOREIGN KEY (equipmentID)
     REFERENCES Equipment(equipID)
     ON DELETE CASCADE);

CREATE TABLE ExerciseGoal
(eplanName      CHAR(20)        NOT NULL,
 description    CHAR(20)        NOT NULL,
 PRIMARY KEY (eplanName));

CREATE TABLE UserFollowsExercisePlan
(eplanID        INTEGER         NOT NULL,
 userID         INTEGER         NOT NULL,
 PRIMARY KEY (eplanID, userID),
 FOREIGN KEY (eplanID)
     REFERENCES ExercisePlan (eplanID)
     ON DELETE CASCADE);

CREATE TABLE CertificateWithTrainer
(certID         INTEGER         NOT NULL,
 certName       CHAR(20)        NOT NULL,
 trainerID      INTEGER         NOT NULL,
 PRIMARY KEY (certID),
 FOREIGN KEY (trainerID)
     REFERENCES Trainer (trainerID)
     ON DELETE SET NULL);

CREATE TABLE UserWithDietPlan
(userName       CHAR(20)        NOT NULL,
 userID         INTEGER         NOT NULL,
 userPhone      CHAR(15)        NOT NULL,
 dplanID        INTEGER         NOT NULL,
 PRIMARY KEY (userID),
 FOREIGN KEY (dplanID)
     REFERENCES DietPlan(dplanID)
     ON DELETE CASCADE);

CREATE TABLE TrainerTrainsUser
(userID         INTEGER         NOT NULL,
 trainerID      INTEGER         NOT NULL,
 PRIMARY KEY (userID, trainerID),
 FOREIGN KEY (userID)
     REFERENCES UserWithDietPlan (userID)
     ON DELETE CASCADE,
 FOREIGN KEY (trainerID)
     REFERENCES Trainer (trainerID)
     ON DELETE CASCADE);

CREATE TABLE Timeslot
(appointment    DATE            NOT NULL,
 userID         INTEGER         NOT NULL,
 trainerID      INTEGER         NOT NULL,
 PRIMARY KEY (appointment, userID, trainerID),
 FOREIGN KEY (userID)
     REFERENCES UserWithDietPlan (userID)
     ON DELETE CASCADE,
 FOREIGN KEY (trainerID)
     REFERENCES Trainer (trainerID)
     ON DELETE CASCADE);


CREATE TABLE DietPlanHasConsumable
(dplanID        INTEGER         NOT NULL,
 consName       CHAR(20)        NOT NULL,
 portion_g_mL   INTEGER         NOT NULL,
 PRIMARY KEY (dplanID, consName),
 FOREIGN KEY (dplanID)
     REFERENCES DietPlan (dplanID),
 FOREIGN KEY (consName)
     REFERENCES Consumable (consName)
     ON DELETE CASCADE);

CREATE TABLE ExercisePlanContainsExercise
(exerciseName        CHAR(20)        NOT NULL,
 eplanID        INTEGER         NOT NULL,
 PRIMARY KEY (exerciseName, eplanID),
 FOREIGN KEY (exerciseName)
     REFERENCES ExerciseWithEquipment (exrName)
     ON DELETE CASCADE,
 FOREIGN KEY (eplanID)
     REFERENCES ExercisePlan(eplanID)
     ON DELETE CASCADE);

