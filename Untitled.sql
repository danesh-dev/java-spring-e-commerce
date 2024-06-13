CREATE TABLE `users` (
  `id` integer PRIMARY KEY,
  `email` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone_number` integer NOT NULL,
  `address` varchar(200),
  `amount` float DEFAULT 0 COMMENT 'amount >= 0'
);

CREATE TABLE `sellers` (
  `id` integer PRIMARY KEY,
  `user_id` integer
);

CREATE TABLE `customers` (
  `id` integer PRIMARY KEY,
  `user_id` integer
);

CREATE TABLE `products` (
  `id` integer PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `price` float NOT NULL COMMENT 'price > 0',
  `stock` integer NOT NULL COMMENT 'stock >= 0',
  `image_id` integer NOT NULL,
  `category_id` integer NOT NULL,
  `seller_id` integer NOT NULL
);

CREATE TABLE `images` (
  `id` integer PRIMARY KEY,
  `image_path` varchar(100) NOT NULL
);

CREATE TABLE `categories` (
  `id` integer PRIMARY KEY,
  `name` varchar(50) NOT NULL
);

CREATE TABLE `order_items` (
  `id` integer PRIMARY KEY,
  `product_id` integer NOT NULL,
  `quantity` integer NOT NULL COMMENT 'quantity >= products.stock'
);

CREATE TABLE `orders` (
  `id` integer PRIMARY KEY,
  `item_id` integer NOT NULL,
  `created_date` datetime NOT NULL DEFAULT (today()),
  `total_price` float NOT NULL,
  `payment_id` integer NOT NULL,
  `status` varchar(50) NOT NULL
);

CREATE TABLE `carts` (
  `id` integer PRIMARY KEY,
  `user_id` integer NOT NULL,
  `order_id` integer NOT NULL
);

CREATE TABLE `order_payments` (
  `id` integer PRIMARY KEY,
  `customer_id` integer NOT NULL,
  `seller_id` integer NOT NULL
);

CREATE TABLE `whishlists` (
  `id` integer PRIMARY KEY,
  `customer_id` integer NOT NULL,
  `product_id` integer NOT NULL
);

CREATE TABLE `payments` (
  `id` integer PRIMARY KEY,
  `user_id` integer NOT NULL,
  `amount` float NOT NULL COMMENT 'ampunt > 0'
);

ALTER TABLE `sellers` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `customers` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `categories` ADD FOREIGN KEY (`id`) REFERENCES `products` (`category_id`);

ALTER TABLE `products` ADD FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`id`);

ALTER TABLE `images` ADD FOREIGN KEY (`id`) REFERENCES `products` (`image_id`);

CREATE TABLE `products_order_items` (
  `products_id` integer,
  `order_items_product_id` integer,
  PRIMARY KEY (`products_id`, `order_items_product_id`)
);

ALTER TABLE `products_order_items` ADD FOREIGN KEY (`products_id`) REFERENCES `products` (`id`);

ALTER TABLE `products_order_items` ADD FOREIGN KEY (`order_items_product_id`) REFERENCES `order_items` (`product_id`);


ALTER TABLE `order_items` ADD FOREIGN KEY (`id`) REFERENCES `orders` (`item_id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`payment_id`) REFERENCES `order_payments` (`id`);

ALTER TABLE `carts` ADD FOREIGN KEY (`user_id`) REFERENCES `customers` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`id`) REFERENCES `carts` (`order_id`);

ALTER TABLE `order_payments` ADD FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

ALTER TABLE `order_payments` ADD FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`id`);

ALTER TABLE `whishlists` ADD FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

CREATE TABLE `products_whishlists` (
  `products_id` integer,
  `whishlists_product_id` integer,
  PRIMARY KEY (`products_id`, `whishlists_product_id`)
);

ALTER TABLE `products_whishlists` ADD FOREIGN KEY (`products_id`) REFERENCES `products` (`id`);

ALTER TABLE `products_whishlists` ADD FOREIGN KEY (`whishlists_product_id`) REFERENCES `whishlists` (`product_id`);


ALTER TABLE `payments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
