CREATE TABLE IF NOT EXISTS airport(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    city INT NOT NULL,
    external_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (city) REFERENCES city(id)
);