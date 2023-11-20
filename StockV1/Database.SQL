DROP SEQUENCE produitIdSeq;
DROP SEQUENCE depotIdSeq;
DROP SEQUENCE stockIdSeq;
DROP TABLE stock;
DROP TABLE depot;
DROP TABLE produit;

CREATE SEQUENCE produitIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE depotIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE stockIdSeq START WITH 1 INCREMENT BY 1;

CREATE TABLE produit(
    id NUMBER,
    CONSTRAINT produitPK PRIMARY KEY(id),
    nom VARCHAR(20)
);

CREATE TABLE depot(
    id NUMBER,
    CONSTRAINT depotPK PRIMARY KEY(id),
    nom VARCHAR(20),
    idProduit NUMBER,
    CONSTRAINT depotFK FOREIGN KEY(idProduit) REFERENCES produit(id),
    price NUMBER(6,0)
);

CREATE TABLE stock(
    id NUMBER,
    CONSTRAINT stockPK PRIMARY KEY(id),
    nom VARCHAR(20),
    idProduit NUMBER,
    CONSTRAINT stockPFK FOREIGN KEY(idProduit) REFERENCES produit(id),
    idDepot NUMBER,
    CONSTRAINT stockDFK FOREIGN KEY(idDepot) REFERENCES depot(id),
    currentStock NUMBER,
    cump NUMBER NOT NULL,
    dateStock DATE
);

INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Ciptadent');
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Maxam');

INSERT INTO depot VALUES(depotIdSeq.NEXTVAL,'Andohoranofotsy',2,2500);
INSERT INTO depot VALUES(depotIdSeq.NEXTVAL,'Andohoranofotsy',3,1300);
INSERT INTO depot VALUES(depotIdSeq.NEXTVAL,'Tanjombato',2,2700);
INSERT INTO depot VALUES(depotIdSeq.NEXTVAL,'Tanjombato',3,1600);