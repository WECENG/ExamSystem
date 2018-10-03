package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import entity.ChessModel;
public class ChessDAO {
	ChessModel chessModel;
	
	//构造函数实例化模型类
	public ChessDAO(){
		chessModel=new ChessModel();
		chessModel.setBlackChess(new ArrayList<HashMap<Integer,Integer>>());
		chessModel.setWhiteChess(new ArrayList<HashMap<Integer,Integer>>());
	}
	
	//获取模型类对象
	public ChessModel getChessModel(){
		return chessModel;
	}
	
		//向HashMap表添加当前一步棋的棋子
	public	ArrayList<HashMap<Integer,Integer>> AddChess(String chessColor,int row,int col){
		if(chessColor.equals("black")){
			ArrayList<HashMap<Integer,Integer>> PreblackChess=new ArrayList<HashMap<Integer,Integer>>();
			PreblackChess=chessModel.getBlackChess();			//获取已经存在的黑棋
			if(row<=chessModel.getChessboardRow()&&col<=chessModel.getChessboardCol()){
				HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
				map.put(row, col);		//添加当前棋子
				PreblackChess.add(map);
				chessModel.setBlackChess(PreblackChess);			//更新黑棋HashMap表
			}else{
				System.out.println("棋子越界！");
				return null;
			}
			return PreblackChess;
		}
		else if(chessColor.equals("white")){
			ArrayList<HashMap<Integer,Integer>> PrewhiteChess=new ArrayList<HashMap<Integer,Integer>>();
			PrewhiteChess=chessModel.getWhiteChess();			//获取已经存在的白棋
			if(row<=chessModel.getChessboardRow()&&col<=chessModel.getChessboardCol()){
				HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
				map.put(row, col);		//添加当前棋子
				PrewhiteChess.add(map);
				chessModel.setWhiteChess(PrewhiteChess);			//更新白棋HashMap表
			}else{
				System.out.println("棋子越界！");
				return null;
			}
			return PrewhiteChess;
		}
		else{
			System.out.println("棋子颜色有误！");
			return null;
		}
	}
	
			//判断游戏是否结束
	public boolean isEnd(ArrayList<HashMap<Integer,Integer>> ChessMap,int row,int col){
		int step=chessModel.getChessboardCol()*chessModel.getChessboardRow();
		if(step==ChessMap.size()){
			return true;
		}
		Iterator<HashMap<Integer, Integer>> it=ChessMap.iterator();
		ArrayList<Integer> list_col=new ArrayList<Integer>();		//存放行相同的列号
		ArrayList<Integer> list_row=new ArrayList<Integer>();		//存放列相同的行号
		ArrayList<Integer> ENlist=new ArrayList<Integer>();		//存放东北朝向这一列
		ArrayList<Integer> WSlist=new ArrayList<Integer>();		//存放西南朝向这一列
		while(it.hasNext()){
			HashMap<Integer, Integer> map=it.next();
			Iterator<Integer> itr=map.keySet().iterator();
			while(itr.hasNext()){
				int key=itr.next();
				int value=map.get(key);
				if(key==row){
					list_col.add(value);
				}
				if(value==col){
					list_row.add(key);
				}
				if(key-row==value-col){				//纵轴与横轴增量相同，则为东北朝向
					ENlist.add(key);							//只存行号
				}
				if(key-row==col-value){				//纵轴与横轴增量相反，则为西南朝向
					WSlist.add(key);
				}
			}
		}
		Collections.sort(list_col);				//按升序排序
		Collections.sort(list_row);
		Collections.sort(ENlist);
		Collections.sort(WSlist);
		if(isfive(list_row)||isfive(list_col)||isfive(WSlist)||isfive(ENlist)){
			return true;								//只要存在一列满足则返回true
		}
		
		return false;
	}
	
				//判断列表是否存在5个连续的元素
	public boolean isfive(ArrayList<Integer> list){
		int count=0;
		for(int i=1;i<list.size();i++){
			if(list.get(i)-list.get(i-1)==1){
				count++;
				if(count==4){
					return true;
				}
			}else{
				count=0;
			}
		}
		return false;
	}
	
			//判断下一步棋
 public String nextstep(int step){
		if(step%2==0){				//黑棋先手，步数为双		
			return "black";
		}else{
			return "white";
		}
	}
	
}
