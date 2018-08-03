package TKoin;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class TKoin {

	public static int difficulty=5;
	public static ArrayList<Block> blockchain=new ArrayList<Block>();
	
	public static void main(String[] args) {
		blockchain.add(new Block("first block", "0"));
		blockchain.get(0).mineBlock(difficulty);
		blockchain.add(new Block("Second block", blockchain.get(blockchain.size()-1).hash));
		blockchain.get(1).mineBlock(difficulty);
		blockchain.add(new Block("Third block", blockchain.get(blockchain.size()-1).hash));
		blockchain.get(2).mineBlock(difficulty);
		String blockchainJson=new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
	}

	public static Boolean isChainValid(){
		Block currentBlock, previousBlock;
		String hashTarget=new String(new char[difficulty]).replace('\0', '0');
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock=blockchain.get(i);
			previousBlock=blockchain.get(i-1);
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes not equal");
				return false;
			}
			if (!previousBlock.hash.equals(previousBlock.calculateHash())) {
				System.out.println("Previous hashes not equal");
				return false;
			}
			if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
