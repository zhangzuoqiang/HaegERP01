package org.haegerp.export.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import org.haegerp.entity.Article;
import org.haegerp.enums.ArticleColumns;
import org.haegerp.export.Export;
import org.springframework.stereotype.Service;

/**
 * Exportation zur CSV-Datei
 * 
 * @author Fabio Codinha
 *
 */
@Service
public class ExportCSV implements Export {

        @Override
	public boolean export(List<Article> articles, String path) throws Exception{
		if (articles.isEmpty())
			return false;
		
		try {
			File file = new File(path);
			file.mkdirs();
			file = new File(path + "\\CSV-Export_" + String.valueOf((new Date()).getTime()) + ".csv");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			
			for (int x = 0; x < ArticleColumns.values().length; x++){
				writer.append(ArticleColumns.values()[x].toString());
				if (x+1 < ArticleColumns.values().length)
					writer.append(',');
			}
			
			writer.append('\n');
			
			for (Article article : articles) {
				writer.append(String.valueOf(article.getIdArticle()));
				writer.append(',');
				writer.append(String.valueOf(article.getArticleCategory().getIdArticleCategory()));
				writer.append(',');
				writer.append(String.valueOf(article.getEan()));
				writer.append(',');
				writer.append(article.getName());
				writer.append(',');
				writer.append(String.valueOf(article.getPriceVat()));
				writer.append(',');
				writer.append(String.valueOf(article.getPriceGross()));
				writer.append(',');
				writer.append(String.valueOf(article.getPriceSupplier()));
				writer.append(',');
				writer.append(String.valueOf(article.getStock()));
				writer.append(',');
				writer.append(article.getColor());
				writer.append(',');
				writer.append(String.valueOf(article.getSizeH()));
				writer.append(',');
				writer.append(String.valueOf(article.getSizeL()));
				writer.append(',');
				writer.append(String.valueOf(article.getSizeW()));
				writer.append(',');
				writer.append(article.getDescription());
				writer.append('\n');
			}
			
			writer.flush();
			writer.close();
			return true;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
