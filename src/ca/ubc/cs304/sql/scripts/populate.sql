INSERT INTO Equipment
 VALUES ('Bodyweight', 0);
INSERT INTO Equipment
 VALUES ('Barbell', 1);
INSERT INTO Equipment
 VALUES ('Bench', 2);
INSERT INTO Equipment
 VALUES ('Treadmill', 3);
INSERT INTO Equipment
 VALUES ('Dumbbells', 4);

INSERT INTO ExerciseWithEquipment
 VALUES ('Squat', 1);
INSERT INTO ExerciseWithEquipment
 VALUES ('Deadlift', 1);
INSERT INTO ExerciseWithEquipment
 VALUES ('BenchPress', 2);
INSERT INTO ExerciseWithEquipment
 VALUES ('Jogging', 3);
INSERT INTO ExerciseWithEquipment
 VALUES ('Pushup', 0);

INSERT INTO ExercisePlan
 VALUES ('Big Quads', 1);
INSERT INTO ExercisePlan
 VALUES ('Death by Deadlifts', 2);
INSERT INTO ExercisePlan
 VALUES ('Monday Chest Day', 3);
INSERT INTO ExercisePlan
 VALUES ('Weight Loss', 4);
INSERT INTO ExercisePlan
 VALUES ('Calisthenics', 5);

INSERT INTO ExercisePlanContainsExercise
 VALUES ('Squat', 1);
INSERT INTO ExercisePlanContainsExercise
 VALUES ('Deadlift', 2);
INSERT INTO ExercisePlanContainsExercise
 VALUES ('BenchPress', 3);
INSERT INTO ExercisePlanContainsExercise
 VALUES ('Jogging', 4);
INSERT INTO ExercisePlanContainsExercise
 VALUES ('Pushup', 5);

INSERT INTO UserFollowsExercisePlan
 VALUES(1, 1111);
INSERT INTO UserFollowsExercisePlan
 VALUES(2, 2222);
INSERT INTO UserFollowsExercisePlan
 VALUES(3, 3333);
INSERT INTO UserFollowsExercisePlan
 VALUES(4, 4444);
INSERT INTO UserFollowsExercisePlan
 VALUES(5, 5555);

INSERT INTO DietPlan
 VALUES (101, 'starvation diet');
INSERT INTO DietPlan
 VALUES (102, 'keto');
INSERT INTO DietPlan
 VALUES (103, 'intermittent fasting');
INSERT INTO DietPlan
 VALUES (104, 'calorie counting');
INSERT INTO DietPlan
 VALUES (105, 'paleo');

INSERT INTO UserWithDietPlan
 VALUES ('Sam', 1111, '778-111-1111', 101);
INSERT INTO UserWithDietPlan
 VALUES ('Frodo', 2222, '778-222-2222', 102);
INSERT INTO UserWithDietPlan
 VALUES ('Merry', 3333, '778-333-3333', 103);
INSERT INTO UserWithDietPlan
 VALUES ('Pippin', 4444, '778-444-4444', 104);
INSERT INTO UserWithDietPlan
 VALUES ('Bilbo', 5555, '778-555-5555', 105);

INSERT INTO Trainer
 VALUES (111, 'John', '111-111-1111');
INSERT INTO Trainer
 VALUES (222, 'Chloe', '222-222-2222');
INSERT INTO Trainer
 VALUES (333, 'Ron', '333-333-3333');
INSERT INTO Trainer
 VALUES (444, 'Joy', '444-444-4444');
INSERT INTO Trainer
 VALUES (555, 'Marcus', '555-555-5555');
INSERT INTO Trainer
 VALUES (666, 'Gandalf', '666-666-6666');

INSERT INTO TrainerTrainsUser
 VALUES (1111, 111);
INSERT INTO TrainerTrainsUser
 VALUES (1111, 222);
INSERT INTO TrainerTrainsUser
 VALUES (2222, 222);
INSERT INTO TrainerTrainsUser
 VALUES (3333, 333);
INSERT INTO TrainerTrainsUser
 VALUES (4444, 444);
INSERT INTO TrainerTrainsUser
 VALUES (5555, 555);
INSERT INTO TrainerTrainsUser
 VALUES (1111, 666);
INSERT INTO TrainerTrainsUser
 VALUES (2222, 666);
INSERT INTO TrainerTrainsUser
 VALUES (3333, 666);
INSERT INTO TrainerTrainsUser
 VALUES (4444, 666);
INSERT INTO TrainerTrainsUser
 VALUES (5555, 666);

INSERT INTO Timeslot
 VALUES (date '2021-05-28', 1111, 111);
INSERT INTO Timeslot
 VALUES (date '2021-06-01', 1111, 222);
INSERT INTO Timeslot
 VALUES (date '2021-06-01', 2222, 222);
INSERT INTO Timeslot
 VALUES (date '2021-06-02', 4444, 444);
INSERT INTO Timeslot
 VALUES (date '2021-06-04', 5555, 555);

INSERT INTO Consumable
 VALUES ('banana', 89);
INSERT INTO Consumable
 VALUES ('apple', 100);
INSERT INTO Consumable
 VALUES ('energy bar', 350);
INSERT INTO Consumable
 VALUES ('burrito', 206);
INSERT INTO Consumable
 VALUES ('cheeseburger', 303);
Insert into Consumable
 VALUES ('soda', 41);
Insert into Consumable
 VALUES ('diet soda', 0);
Insert into Consumable
 VALUES ('water', 0);
Insert into Consumable
 VALUES ('coffee', 28);
Insert into Consumable
 VALUES ('orange juice', 45);

INSERT INTO DietPlanHasConsumable
 VALUES (101, 'banana', 100);
INSERT INTO DietPlanHasConsumable
 VALUES (102, 'apple', 50);
INSERT INTO DietPlanHasConsumable
 VALUES (103, 'energy bar', 24.3);
INSERT INTO DietPlanHasConsumable
 VALUES (104, 'burrito', 67.5);
INSERT INTO DietPlanHasConsumable
 VALUES (105, 'water', 100);
