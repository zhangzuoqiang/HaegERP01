package org.haegerp.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.haegerp.entity.Article;
import org.springframework.stereotype.Component;

@Component
@WebService
public interface HaegerpWS {

	@WebMethod public Article getArticleById(@WebParam(name = "id") long id);
	
}
