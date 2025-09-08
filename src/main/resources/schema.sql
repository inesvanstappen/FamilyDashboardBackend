DROP TABLE IF EXISTS weekmenu_recipes;
DROP TABLE IF EXISTS weekmenus;
DROP TABLE IF EXISTS todos;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE todos
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    due_date         DATE         NOT NULL,
    title            VARCHAR(200) NOT NULL,
    assigned_user_id BIGINT       NOT NULL,
    status           VARCHAR(20)  NOT NULL,
    FOREIGN KEY (assigned_user_id) REFERENCES users (id)
);

CREATE TABLE recipes
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    difficulty BIGINT       NOT NULL,
    categorie  VARCHAR(50)  NOT NULL,
    url        VARCHAR(255)
);

CREATE TABLE weekmenus
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE NOT NULL
);

CREATE TABLE weekmenu_recipes
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    menu_day    INT    NOT NULL,
    weekmenu_id BIGINT NOT NULL,
    recipe_id   BIGINT NOT NULL,
    FOREIGN KEY (weekmenu_id) REFERENCES weekmenus (id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (id) ON DELETE SET NULL
);
