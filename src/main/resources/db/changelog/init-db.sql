
CREATE TABLE brand (id BIGINT PRIMARY KEY AUTO_INCREMENT not null,
                   name varchar(50) not null unique);
CREATE TABLE tool_type (id BIGINT PRIMARY KEY AUTO_INCREMENT not null,
                        name varchar(50) not null unique,
                        daily_charge NUMERIC(19,4) not null,
                        daily_charge_currency varchar(3) not null,
                        weekday_charge boolean not null default true,
                        weekend_charge boolean not null,
                        holiday_charge boolean not null);

CREATE TABLE tool (id BIGINT PRIMARY KEY AUTO_INCREMENT not null,
                  tool_code varchar(10) not null unique,
                  tool_type_id int not null references tool_type(id),
                  brand_id INT NOT NULL REFERENCES brand(id) );