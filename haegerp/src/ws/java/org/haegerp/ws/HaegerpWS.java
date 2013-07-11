package org.haegerp.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.haegerp.entity.Article;

@WebService
public interface HaegerpWS {

	@WebMethod public Article getArticleById(long id);
	
}
