CREATE OR REPLACE TRIGGER trg_ArticleHistory
AFTER INSERT OR UPDATE
ON ARTICLE
FOR EACH ROW

DECLARE
	v_currentVersion INTEGER;
	v_categoryName VARCHAR2(50);
BEGIN
	IF UPDATING THEN
		SELECT MAX(idArticleHistory)
			INTO v_currentVersion
		FROM ARTICLEHISTORY
		WHERE idArticle = :OLD.idArticle;
		v_currentVersion := v_currentVersion + 1;
	ELSE
		v_currentVersion := 1;
	END IF;
	
	SELECT name
		INTO v_categoryName
	FROM ArticleCategory
	WHERE idArticleCategory = :NEW.idArticleCategory;
	
	INSERT INTO ARTICLEHISTORY (IDARTICLEHISTORY, IDARTICLE, ARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER)
	VALUES (v_currentVersion, :NEW.idArticle, v_categoryName, :NEW.ean, :NEW.name, :NEW.priceVat, :NEW.priceGross, :NEW.priceSupplier);
END;
/