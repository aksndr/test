DROP TABLE counters IF EXISTS;
DROP TABLE countertypes IF EXISTS;
DROP TABLE flats IF EXISTS;
DROP TABLE houses IF EXISTS;
DROP TABLE records IF EXISTS;
DROP TABLE users IF EXISTS;


CREATE TABLE counters (
  sn INTEGER IDENTITY PRIMARY KEY,
  descr varchar(100) DEFAULT NULL,
  typeid INTEGER NOT NULL,
  flatid INTEGER NOT NULL
  );
CREATE INDEX counters_sn ON counters (sn);


CREATE TABLE countertypes (
  id INTEGER IDENTITY PRIMARY KEY,
  typename VARCHAR(45)
);
CREATE INDEX countertypes_typename ON countertypes (typename);


CREATE TABLE flats (
  id INTEGER IDENTITY PRIMARY KEY,
  houseid INTEGER,
  flatnum VARCHAR(45) NOT NULL
);
CREATE INDEX flats_flatnum ON flats (flatnum);


CREATE TABLE houses (
  id INTEGER IDENTITY PRIMARY KEY,
  address varchar(1024) NOT NULL,
);
CREATE INDEX houses_address ON houses (address);


CREATE TABLE records (
  id INTEGER IDENTITY PRIMARY KEY,
  value INTEGER NOT NULL,
  recdate DATE NOT NULL,
  counterid INTEGER NOT NULL,
);
CREATE INDEX records_counterid ON records (counterid);


CREATE TABLE users (
  id INTEGER IDENTITY PRIMARY KEY,
  login varchar(45) NOT NULL,
  firstname varchar(45),
  lastname varchar(45),
  flatid varchar(45),
);
CREATE INDEX userss_login ON users (login);

INSERT INTO counters VALUES (2342341, 'Холодная вода в ванной', 1, 1);
INSERT INTO counters VALUES (2334243, 'Горячая вода в ванной', 1, 1);
INSERT INTO counters VALUES (56895673, 'Холодная вода на кухне', 1, 1);
INSERT INTO counters VALUES (23476532, 'Горячая вода на кухне', 1, 1);
INSERT INTO counters VALUES (2342341, 'Холодная вода в ванной', 1, 2);
INSERT INTO counters VALUES (2334243, 'Горячая вода в ванной', 1, 2);
INSERT INTO counters VALUES (56895673, 'Холодная вода на кухне', 1, 2);
INSERT INTO counters VALUES (23476532, 'Горячая вода на кухне', 1, 2);

INSERT INTO countertypes VALUES (1, 'литры' );
INSERT INTO countertypes VALUES (2, 'квт' );

INSERT INTO flats VALUES (1, 1, '25' );
INSERT INTO flats VALUES (2, 1, '25а' );

INSERT INTO houses VALUES (1, 'г. Москва, ул. Ленина, дом 1' );

INSERT INTO records VALUES (1, 234, '2010-09-07', 2342341);
INSERT INTO records VALUES (1, 235, '2010-10-07', 2342341);
INSERT INTO records VALUES (1, 236, '2010-11-07', 2342341);
INSERT INTO records VALUES (1, 23, '2010-09-07', 2334243);
INSERT INTO records VALUES (1, 24, '2010-10-07', 2334243);
INSERT INTO records VALUES (1, 27, '2010-11-07', 2334243);
INSERT INTO records VALUES (1, 47, '2010-09-07', 56895673);
INSERT INTO records VALUES (1, 58, '2010-10-07', 56895673);
INSERT INTO records VALUES (1, 68, '2010-11-07', 56895673);
INSERT INTO records VALUES (1, 48, '2010-09-07', 23476532);
INSERT INTO records VALUES (1, 58, '2010-10-07', 23476532);
INSERT INTO records VALUES (1, 69, '2010-11-07', 23476532);

INSERT INTO users VALUES (1, 'James', 'James', 'Carter', 1);
INSERT INTO users VALUES (2, 'Linda', 'Linda', 'Stevens',2);