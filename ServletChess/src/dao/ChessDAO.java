package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import entity.ChessModel;
public class ChessDAO {
	ChessModel chessModel;
	
	//���캯��ʵ����ģ����
	public ChessDAO(){
		chessModel=new ChessModel();
		chessModel.setBlackChess(new ArrayList<HashMap<Integer,Integer>>());
		chessModel.setWhiteChess(new ArrayList<HashMap<Integer,Integer>>());
	}
	
	//��ȡģ�������
	public ChessModel getChessModel(){
		return chessModel;
	}
	
		//��HashMap����ӵ�ǰһ���������
	public	ArrayList<HashMap<Integer,Integer>> AddChess(String chessColor,int row,int col){
		if(chessColor.equals("black")){
			ArrayList<HashMap<Integer,Integer>> PreblackChess=new ArrayList<HashMap<Integer,Integer>>();
			PreblackChess=chessModel.getBlackChess();			//��ȡ�Ѿ����ڵĺ���
			if(row<=chessModel.getChessboardRow()&&col<=chessModel.getChessboardCol()){
				HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
				map.put(row, col);		//��ӵ�ǰ����
				PreblackChess.add(map);
				chessModel.setBlackChess(PreblackChess);			//���º���HashMap��
			}else{
				System.out.println("����Խ�磡");
				return null;
			}
			return PreblackChess;
		}
		else if(chessColor.equals("white")){
			ArrayList<HashMap<Integer,Integer>> PrewhiteChess=new ArrayList<HashMap<Integer,Integer>>();
			PrewhiteChess=chessModel.getWhiteChess();			//��ȡ�Ѿ����ڵİ���
			if(row<=chessModel.getChessboardRow()&&col<=chessModel.getChessboardCol()){
				HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
				map.put(row, col);		//��ӵ�ǰ����
				PrewhiteChess.add(map);
				chessModel.setWhiteChess(PrewhiteChess);			//���°���HashMap��
			}else{
				System.out.println("����Խ�磡");
				return null;
			}
			return PrewhiteChess;
		}
		else{
			System.out.println("������ɫ����");
			return null;
		}
	}
	
			//�ж���Ϸ�Ƿ����
	public boolean isEnd(ArrayList<HashMap<Integer,Integer>> ChessMap,int row,int col){
		int step=chessModel.getChessboardCol()*chessModel.getChessboardRow();
		if(step==ChessMap.size()){
			return true;
		}
		Iterator<HashMap<Integer, Integer>> it=ChessMap.iterator();
		ArrayList<Integer> list_col=new ArrayList<Integer>();		//�������ͬ���к�
		ArrayList<Integer> list_row=new ArrayList<Integer>();		//�������ͬ���к�
		ArrayList<Integer> ENlist=new ArrayList<Integer>();		//��Ŷ���������һ��
		ArrayList<Integer> WSlist=new ArrayList<Integer>();		//������ϳ�����һ��
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
				if(key-row==value-col){				//���������������ͬ����Ϊ��������
					ENlist.add(key);							//ֻ���к�
				}
				if(key-row==col-value){				//��������������෴����Ϊ���ϳ���
					WSlist.add(key);
				}
			}
		}
		Collections.sort(list_col);				//����������
		Collections.sort(list_row);
		Collections.sort(ENlist);
		Collections.sort(WSlist);
		if(isfive(list_row)||isfive(list_col)||isfive(WSlist)||isfive(ENlist)){
			return true;								//ֻҪ����һ�������򷵻�true
		}
		
		return false;
	}
	
				//�ж��б��Ƿ����5��������Ԫ��
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
	
			//�ж���һ����
 public String nextstep(int step){
		if(step%2==0){				//�������֣�����Ϊ˫		
			return "black";
		}else{
			return "white";
		}
	}
	
}
