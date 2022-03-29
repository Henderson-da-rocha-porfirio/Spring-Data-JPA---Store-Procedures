-- mysql 1
DELIMITER // 

CREATE PROCEDURE GetAllProducts()
BEGIN
	SELECT *  FROM produto;
END //

-- postgresql 1
CREATE PROCEDURE GetAllProducts()
LANGUAGE plpgsql
as $$
BEGIN
SELECT *  FROM produto;
END; $$


-- mysql 2
DELIMITER //

CREATE PROCEDURE GetAllProductsByPrice(IN price_in decimal)
BEGIN
	SELECT *  FROM product where price>price_in;
END //

-- postgresql 2
CREATE PROCEDURE GetAllProductsByPrice(IN price_in decimal)
LANGUAGE plpgsql
as $$
BEGIN
SELECT *  FROM product where price>price_in;
END; $$


-- mysql 3
DELIMITER //

CREATE PROCEDURE GetAllProductsCountByPrice(IN price_in decimal)
BEGIN
	SELECT count(*)  FROM product where price>price_in;
END //

-- postgresql 3
CREATE PROCEDURE GetAllProductsCountByPrice(IN price_in decimal)
LANGUAGE plpgsql
as $$
BEGIN
SELECT count(*)  FROM product where price>price_in;
END; $$

drop procedure GetAllProducts;
drop procedure GetAllProductsByPrice;
drop procedure GetAllProductsCountByPrice;
