INSERT INTO users(id, name)
VALUES (1, 'Ines'),
       (2, 'Nico');


INSERT INTO todos (id, due_date, title, assigned_user_id, status)
VALUES (1, '2025-07-10', 'Presentatie voorbereiden', 1, 'OPEN'),
       (2, '2025-07-12', 'Code review', 2, 'IN_PROGRESS'),
       (3, '2025-07-15', 'Unit tests schrijven', 1, 'DONE');

INSERT INTO recipes (id, name, difficulty, categorie, url)
VALUES (1, 'Kip met appelmoes', 1, 'DINNER', 'https://www.google.be'),
       (2, 'Ramen', 2, 'DINNER', 'https://www.google.be'),
       (3, 'Chocomouse', 3, 'DESSERT', 'https://www.google.be');

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
