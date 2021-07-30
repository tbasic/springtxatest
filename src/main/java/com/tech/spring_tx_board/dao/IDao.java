package com.tech.spring_tx_board.dao;

import java.util.ArrayList;

import com.tech.spring_tx_board.dto.BoardDto;

public interface IDao {
	public ArrayList<BoardDto> list(int start, int end,String searchKeyword,String selNum);
	
	
	public int selectBoardTotCount(String searchKeyword,String selNum);
//	public int selectBoardTotCount2(String searchKeyword);
//	public int selectBoardTotCount3(String searchKeyword);
//	public int selectBoardTotCount0(String searchKeyword);
	
	
	public void write(String bname, 
			String btitle, String bcontent,String fName);
	
	public BoardDto contentView(String sbid);
	public void upHit(String sbid);
	public void modify(String bid, String bName,
			String bTitle, String bContent);
	public void delete(String bid);
	public BoardDto replyView(String sbid);
	public void reply(String bid, String bName, String bTitle,
			String bContent, String bgroup, 
			String bstep,String bindent);
	public void replyShape(String bgroup, String bstep);
	
}
