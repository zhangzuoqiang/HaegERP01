CREATE OR REPLACE TRIGGER trg_ArticleHistory
AFTER UPDATE
ON ARTICLE
FOR EACH ROW

DECLARE
	v_currentVersion INTEGER;
	v_categoryName VARCHAR2(50);
BEGIN
	SELECT MAX(idArticleHistory)
		INTO v_currentVersion
	FROM ARTICLEHISTORY
	WHERE idArticle = :NEW.idArticle;
	v_currentVersion := v_currentVersion + 1;
	
	SELECT ac.name
		INTO v_categoryName
	FROM ArticleCategory ac JOIN Article a
		ON a.idArticleCategory = ac.idArticleCategory;
	
	INSERT INTO ARTICLEHISTORY (IDARTICLEHISTORY, IDARTICLE, ARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER)
	VALUES (v_currentVersion, :NEW.idArticle, v_categoryName, :NEW.ean, :NEW.name, :NEW.priceVat, :NEW.priceGross, :NEW.priceSupplier);
END;
/