package entity;
import java.util.ArrayList;
import java.util.HashMap;

public class ChessModel {
	private int chessboardRow=15;							//棋盘行数
	private int chessboardCol=15;								//棋盘列数
	private int step=0;															//步数
	private ArrayList<HashMap<Integer,Integer>> blackChess;		//黑棋
	private ArrayList<HashMap<Integer,Integer>> whiteChess;		//白棋
	
	public ChessModel(int chessboardRow, int chessboardCol, ArrayList<HashMap<Integer,Integer>>
	blackChess,ArrayList<HashMap<Integer,Integer>> whiteChess) {
		this.chessboardRow = chessboardRow;
		this.chessboardCol = chessboardCol;
		this.blackChess = blackChess;
		this.whiteChess = whiteChess;
	}
	public ChessModel() {

	}
	
	public int getChessboardRow() {
		return chessboardRow;
	}
	public void setChessboardRow(int chessboardRow) {
		this.chessboardRow = chessboardRow;
	}
	public int getChessboardCol() {
		return chessboardCol;
	}
	public void setChessboardCol(int chessboardCol) {
		this.chessboardCol = chessboardCol;
	}
	public ArrayList<HashMap<Integer,Integer>> getBlackChess() {
		return blackChess;
	}
	public void setBlackChess(ArrayList<HashMap<Integer,Integer>> blackChess) {
		this.blackChess = blackChess;
	}
	public ArrayList<HashMap<Integer,Integer>> getWhiteChess() {
		return whiteChess;
	}
	public void setWhiteChess(ArrayList<HashMap<Integer,Integer>> whiteChess) {
		this.whiteChess = whiteChess;
	}	
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
}
