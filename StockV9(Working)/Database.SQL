DROP SEQUENCE produitIdSeq;
DROP SEQUENCE depotIdSeq;
DROP SEQUENCE stockIdSeq;
DROP SEQUENCE categoryIdSeq;

DROP TABLE mvmtstock;
DROP TABLE stock;
DROP TABLE depot;
DROP TABLE produit;
DROP TABLE StockCategory;
DROP TABLE category;


CREATE SEQUENCE produitIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE depotIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE stockIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE categoryIdSeq START WITH 1 INCREMENT BY 1;

CREATE TABLE category(
    id NUMBER,
    CONSTRAINT categoryPK PRIMARY KEY(id),
    nom VARCHAR(20)
);

CREATE TABLE produit(
    id NUMBER,
    CONSTRAINT produitPK PRIMARY KEY(id),
    nom VARCHAR(20),
    idCategory NUMBER,
    CONSTRAINT produitFK FOREIGN KEY(idCategory) REFERENCES category(id)
);

CREATE TABLE depot(
    id NUMBER,
    CONSTRAINT depotPK PRIMARY KEY(id),
    nom VARCHAR(20)
);

CREATE TABLE mvmtstock(
    id NUMBER,
    CONSTRAINT mvmstockPK PRIMARY KEY(id),
    idDepot NUMBER,
    CONSTRAINT mvmtstockDFK FOREIGN KEY(idDepot) REFERENCES depot(id),
    idProduit NUMBER,
    CONSTRAINT mvmtstockPFK FOREIGN KEY(idProduit) REFERENCES produit(id),
    idCategory NUMBER,
    CONSTRAINT mvmtstockCFK FOREIGN KEY(idCategory) REFERENCES category(id),
    QtMvmt NUMBER,
    money NUMBER(*,2),
    currentStock NUMBER,
    cump NUMBER (*,2),
    dateStock DATE
);

CREATE TABLE stock(
    idDepot NUMBER,
    CONSTRAINT stockDFK FOREIGN KEY(idDepot) REFERENCES depot(id),
    idProduit NUMBER,
    CONSTRAINT stockPFK FOREIGN KEY(idProduit) REFERENCES produit(id),
    idCategory NUMBER,
    CONSTRAINT stockCFK FOREIGN KEY(idCategory) REFERENCES category(id),
    currentStock NUMBER,
    cump NUMBER (*,2)
);

CREATE TABLE StockCategory(
    idcategory NUMBER,
    CONSTRAINT StockCategoryFK FOREIGN KEY(idCategory) REFERENCES category(id),
    pirx NUMBER (*,2)
);


INSERT INTO category VALUES(categoryIdSeq.NEXTVAL,'category 1');
INSERT INTO category VALUES(categoryIdSeq.NEXTVAL,'category 2');

INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Ciptadent',2);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Maxam',2);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Colgate',3);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Formula',3);

INSERT INTO depot VALUES(depotIdSeq.NEXTVAL,'Andohoranofotsy');
INSERT INTO depot VALUES(depotIdSeq.NEXTVAL,'Tanjombato');

INSERT INTO Stock VALUES(2,2,2,0,0);
INSERT INTO Stock VALUES(2,3,2,0,0);
INSERT INTO Stock VALUES(2,4,3,0,0);
INSERT INTO Stock VALUES(2,5,3,0,0);
INSERT INTO Stock VALUES(2,6,4,0,0);
