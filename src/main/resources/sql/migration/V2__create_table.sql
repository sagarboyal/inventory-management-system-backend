CREATE TABLE IF NOT EXISTS categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            created_on TIMESTAMP NOT NULL DEFAULT NOW(),
                            status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS products (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          name VARCHAR(255) NOT NULL,
                          description VARCHAR(500),
                          category_id BIGINT NOT NULL,
                          brand VARCHAR(80),
                          price NUMERIC(12,2) NOT NULL DEFAULT 0,
                          unit VARCHAR(20),
                          quantity INT NOT NULL DEFAULT 0 CHECK (quantity >= 0),
                          image_url TEXT,

                          CONSTRAINT fk_products_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id)
                                  ON DELETE RESTRICT
                                  ON UPDATE CASCADE
);