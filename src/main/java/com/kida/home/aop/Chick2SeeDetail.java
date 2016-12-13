package com.kida.home.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kida.home.service.ArticleService;

/**
 * 点击详情的时候做的切面
 * 
 * @author kida
 *
 */
@Aspect
@Component
public class Chick2SeeDetail {

	public static final String CutPointExec = "execution(* com.kida.home.service.impl.ArticleServiceImpl.queryArticleById(..))";

	@Autowired
	private ArticleService articleService;

	/**
	 * 当详细信息被点击查看的时候就会通过aop自动加1
	 * 
	 * @param joinPoint
	 */
	@After(CutPointExec)
	public void queryArticleAfter(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String articleId = String.valueOf(args[0]);
		articleService.updateArticleReadCount(articleId);
	}

}
