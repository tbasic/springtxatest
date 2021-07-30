package com.tech.one.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.spring_tx_board.dao.IDao;
import com.tech.spring_tx_board.vopage.SearchVO;

@Controller
public class BController {
	
	public JdbcTemplate template;
	
//	public void setTemplate(JdbcTemplate template) {
//		this.template = template;
//		Constant.template=this.template;
//	}
	@Autowired
	private SqlSession sqlSession;

	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,
			SearchVO searchVO,Model model) {
		System.out.println("pass by list()");
//		commandInp=new BListService();
//		commandInp.execute(model);
		//search
		String btitle="";
		String bcontent="";
		
		String[] brdtitle=request.getParameterValues("searchType");
		
		if(brdtitle!=null) {
			for (int i = 0; i < brdtitle.length; i++) {
				System.out.println("brdtitle : "+brdtitle[i]);
			}
		}
		//검색유지
		if (brdtitle!=null) {
			for (String val : brdtitle) {
				if (val.equals("btitle")) {
					model.addAttribute("btitle","true");
					btitle="btitle";
				}else if (val.equals("bcontent")) {
					model.addAttribute("bcontent","true");
					bcontent="bcontent";
				}
			}
		}
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
	
		String strPage=request.getParameter("page");
		System.out.println("strPage : "+strPage);
		
		
//		mybatis에서 작업
		IDao dao=sqlSession.getMapper(IDao.class);
		
		if (strPage==null || strPage.equals("")) {
			strPage="1";
		}
		System.out.println("strPage : "+strPage);
		
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//totcnt
		int total=0;
		if (btitle.equals("btitle") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"1");
			System.out.println("list1~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"2");
			System.out.println("list2~~~~~~~~~~~~");
		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			total=dao.selectBoardTotCount(searchKeyword,"3");
			System.out.println("list3~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("")) {
			total=dao.selectBoardTotCount(searchKeyword,"0");
			System.out.println("list0~~~~~~~~~~~~");
		}
		
		model.addAttribute("searchKeyword",searchKeyword);
		
		
		searchVO.pageCalculate(total);
		
		
		System.out.println("total : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		System.out.println("=============================");
		
		if (btitle.equals("btitle") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"1"));
			System.out.println("list1~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"2"));
			System.out.println("list2~~~~~~~~~~~~");
		}else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"3"));
			System.out.println("list3~~~~~~~~~~~~");
		}else if (btitle.equals("") && bcontent.equals("")) {
			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"0"));
			model.addAttribute("totRowCnt",dao.selectBoardTotCount(searchKeyword,"0"));
			System.out.println("list0~~~~~~~~~~~~");
		}
		
		model.addAttribute("searchVo",searchVO);
		return "list";
	}
	
}
