INSERT INTO users(name)
VALUES ('Ines'),
       ('Nico');


INSERT INTO todos (due_date, title, assigned_user_id, status)
VALUES ('2025-07-10', 'Presentatie voorbereiden', 1, 'OPEN'),
       ('2025-07-12', 'Code review', 2, 'IN_PROGRESS'),
       ('2025-07-15', 'Unit tests schrijven', 1, 'DONE');

INSERT INTO recipes (name, difficulty, categorie, url)
VALUES ('Kip met appelmoes', 1, 'DINNER', 'https://www.google.be'),
       ('Ramen', 2, 'DINNER', 'https://www.google.be'),
       ('Quiche', 3, 'DESSERT', 'https://www.google.be'),
       ('Chocomouse', 3, 'DESSERT', 'https://www.google.be'),
       ('Pokebowl', 3, 'DESSERT', 'https://www.google.be');

INSERT INTO weekmenus (start_date)
VALUES ('2025-08-25'),
       ('2025-09-01');

INSERT INTO weekmenu_recipes (menu_day, weekmenu_id, recipe_id)
VALUES (0, 1, 2),
       (1, 1, 2),
       (2, 1, 2),
       (3, 1, 2),
       (4, 1, 2),
       (5, 1, 2),
       (6, 1, 2),
       (0, 2, 1),
       (1, 2, 1),
       (2, 2, 1),
       (3, 2, 1),
       (4, 2, 1),
       (5, 2, 1),
       (6, 2, 1);
