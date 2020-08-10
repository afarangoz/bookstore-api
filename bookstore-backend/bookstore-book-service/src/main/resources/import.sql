INSERT INTO db_bookstore.users (id, username, password, is_enable, full_name, email) VALUES(1, 'admin', '$2a$10$pyB2ais6gYknfv3zn1hQD.t6bjWsYM8mN5PGgaY2EfIq2lDbcSG9e', 1, 'System administrator', 'admin@test.com');
INSERT INTO db_bookstore.users (id, username, password, is_enable, full_name, email) VALUES(2, 'afarangoz', '$2a$10$m7Ng/ODN6Nnr7yNUiItkluuq/m8lsRhE2h1J0eCqptNxcF3uXIYsG', 1, 'Andres Felipe', 'afarangoz@test.com');

INSERT INTO db_bookstore.roles (id, name) VALUES(1, 'ROLE_USER');
INSERT INTO db_bookstore.roles (id, name) VALUES(2, 'ROLE_ADMIN');

INSERT INTO db_bookstore.users_roles(user_id, role_id) VALUES(1, 2);
INSERT INTO db_bookstore.users_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO db_bookstore.users_roles(user_id, role_id) VALUES(2, 1);


INSERT INTO db_bookstore.books (id, name, available, price) VALUES(1, 'Harry Potter y la piedra filosofal', 25, 75000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(2, 'Harry Potter y la cámara secreta', 53, 120000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(3, 'Harry Potter y el prisionero de Azkaban', 28, 99000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(4, 'Harry Potter y el cáliz de fuego', 0, 50000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(5, 'Harry Potter y la Orden del Fénix', 6, 62000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(6, 'Harry Potter y el misterio del príncipe', 10, 85000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(7, 'Harry Potter y las reliquias de la Muerte', 54, 135000);
INSERT INTO db_bookstore.books (id, name, available, price) VALUES(8, 'Harry Potter y el legado maldito', 33, 105000);


INSERT INTO db_bookstore.orders (id, date, user_id) VALUES(1, CURDATE(), 2);
INSERT INTO db_bookstore.orders_books (id, book_id, quantity, order_id) VALUES(1, 2, 3, 1);
INSERT INTO db_bookstore.orders_books (id, book_id, quantity, order_id) VALUES(2, 4, 8, 1);