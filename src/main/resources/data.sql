DROP TABLE IF EXISTS product;

CREATE TABLE product (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              name VARCHAR(250) ,
                              price DOUBLE(250) ,
                              quantity INT(250)


);

INSERT INTO product (name, price, quantity) VALUES
('Phone', 100.99, 20),
('Fridge', 550.90, 10);




