
INSERT INTO todos (id, due_date, task, assigned_user, status)
VALUES (1, '2025-07-10', 'Presentatie voorbereiden', 'ines', 'OPEN'),
       (2, '2025-07-12', 'Code review', 'tom', 'IN_PROGRESS'),
       (3, '2025-07-15', 'Unit tests schrijven', 'ines', 'DONE');

INSERT INTO recipes (id, name, difficulty, categorie)
VALUES (1, 'Kip met appelmoes', 1, 'DINNER'),
       (2, 'Ramen', 2, 'DINNER'),
       (3, 'Chocomouse', 3, 'DESSERT');

INSERT INTO weekmenus (id)
VALUES (1);

INSERT INTO weekmenu_recipes (menu_day, weekmenu_id, recipe_id)
VALUES (0, 1, 1),
       (1, 1, 2),
       (2, 1, 3),
       (3, 1, 1),
       (4, 1, 2),
       (5, 1, 3),
       (6, 1, 1);
