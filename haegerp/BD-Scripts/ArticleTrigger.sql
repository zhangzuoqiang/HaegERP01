CREATE OR REPLACE TRIGGER trg_ArticleHistory
AFTER INSERT OR UPDATE
ON ARTICLE
FOR EACH ROW

DECLARE
	v_currentVersion INTEGER;
	v_articleCount INTEGER;
	v_categoryName VARCHAR2(50);
BEGIN
	SELECT COUNT(idArticleHistory)
		INTO v_articleCount
	FROM ARTICLEHISTORY
	WHERE idArticle = :NEW.idArticle;
	
	IF (v_articleCount > 0) THEN
		SELECT MAX(idArticleHistory)
			INTO v_currentVersion
		FROM ARTICLEHISTORY
		WHERE idArticle = :NEW.idArticle;
		v_currentVersion := v_currentVersion + 1;
	ELSE
		v_currentVersion := 1;
	END IF;
	
	SELECT name
		INTO v_categoryName
	FROM ArticleCategory
	WHERE idArticleCategory = :NEW.idArticleCategory;
	
	INSERT INTO ARTICLEHISTORY (IDARTICLEHISTORY, IDARTICLE, ARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
	VALUES (v_currentVersion, :NEW.idArticle, v_categoryName, :NEW.ean, :NEW.name, :NEW.priceVat, :NEW.priceGross, :NEW.priceSupplier, :NEW.lastModifiedDate, :NEW.IdEmployeeModify);
END;
/