DROP SEQUENCE produitIdSeq;
DROP SEQUENCE magasinIdSeq;
DROP SEQUENCE mouvementIdSeq;
DROP SEQUENCE categoryIdSeq;

DROP TABLE mouvement;
DROP TABLE stock;
DROP TABLE magasin;
DROP TABLE produit;
DROP TABLE category;

CREATE SEQUENCE produitIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE magasinIdSeq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE mouvementIdSeq START WITH 1 INCREMENT BY 1;
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

CREATE TABLE magasin(
    id NUMBER,
    CONSTRAINT magasinPK PRIMARY KEY(id),
    nom VARCHAR(20)
);

CREATE TABLE mouvement(
    id NUMBER,
    CONSTRAINT mvmstockPK PRIMARY KEY(id),
    idMagasin NUMBER,
    CONSTRAINT mvmtstockDFK FOREIGN KEY(idMagasin) REFERENCES magasin(id),
    idProduit NUMBER,
    CONSTRAINT mvmtstockPFK FOREIGN KEY(idProduit) REFERENCES produit(id),
    idCategory NUMBER,
    CONSTRAINT mvmtstockCFK FOREIGN KEY(idCategory) REFERENCES category(id),
    entree NUMBER,
    sortie NUMBER,
    money NUMBER,
    datestock DATE
);

/*CREATE TABLE stock(
    idMagasin NUMBER,
    CONSTRAINT stockDFK FOREIGN KEY(idMagasin) REFERENCES magasin(id),
    idProduit NUMBER,
    CONSTRAINT stockPFK FOREIGN KEY(idProduit) REFERENCES produit(id),
    idCategory NUMBER,
    CONSTRAINT stockCFK FOREIGN KEY(idCategory) REFERENCES category(id),
    currentStock NUMBER,
    cump NUMBER (*,2)
);*/

/*CREATE TABLE StockCategory(
    idcategory NUMBER,
    CONSTRAINT StockCategoryFK FOREIGN KEY(idCategory) REFERENCES category(id),
    pirx NUMBER (*,2)
);*/

INSERT INTO magasin VALUES(magasinIdSeq.NEXTVAL,'Andohoranofotsy');
INSERT INTO magasin VALUES(magasinIdSeq.NEXTVAL,'Tanjombato');

INSERT INTO category VALUES(categoryIdSeq.NEXTVAL,'Boisson Hygienique');
INSERT INTO category VALUES(categoryIdSeq.NEXTVAL,'PPN');
INSERT INTO category VALUES(categoryIdSeq.NEXTVAL,'Boisson alcoolisee');

INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Coca',2);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Limonade',2);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Huile',3);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Riz',3);
INSERT INTO produit VALUES(produitIdSeq.NEXTVAL,'Biere',4);

select idProduit,
idMagasin,
(SUM(Entree)-SUM(Sortie)) AS Reste,
(SUM(Entree * Money)/SUM(Entree)) AS Montant 
from Mouvement GROUP BY idProduit,idMagasin;

select idProduit,
idMagasin,
(SUM(Entree)-SUM(Sortie)) AS Reste,
((SUM(Entree * Money)/SUM(Entree))*(SUM(Entree)-SUM(Sortie))) AS Montant 
from Mouvement GROUP BY idProduit,idMagasin;

select Produit.nom,
idProduit,
idMagasin,
(SUM(Entree)-SUM(Sortie)) AS Reste,
(SUM(Entree * Money)/SUM(Entree)) AS CUMP,
((SUM(Entree * Money)/SUM(Entree))*(SUM(Entree)-SUM(Sortie))) AS Montant 
from Mouvement 
INNER JOIN produit ON mouvement.idProduit=produit.id
GROUP BY produit.nom,idProduit,idMagasin
ORDER BY idProduit;
   
select idCategory,idMagasin,((SUM(Entree * Money)/SUM(Entree))*(SUM(Entree)-SUM(Sortie))) AS Montant from Mouvement GROUP BY idCategory,idMagasin;


select 
category.nom,
idCategory,
idMagasin,
(SUM(Entree * Money)/SUM(Entree)) AS CUMP,
((SUM(Entree * Money)/SUM(Entree))*(SUM(Entree)-SUM(Sortie))) AS Montant 
from Mouvement 
INNER JOIN category ON mouvement.idCategory=category.id 
GROUP BY category.nom,idCategory,idMagasin;