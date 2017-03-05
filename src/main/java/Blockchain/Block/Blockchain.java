package Blockchain.Block;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blockchain {
	private static final Logger logger= LoggerFactory.getLogger(Blockchain.class);
	 List<Block> blockchain= new ArrayList<Block>();
	public Block getGenesisBlock() {
        Block genisis= new Block(0, "0", (long) new Date().getTime(), (Object)"my genesis block!!", "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7");
        return genisis;
    }
	Blockchain(){
		this.blockchain.add(getGenesisBlock());
	}
	public Block getLatestBlock(){
		return blockchain.get(blockchain.size()-1);
	}
	public Block generateNextBlock(Object blockData){
	      Block previousBlock = this.getLatestBlock();
	        int nextIndex = previousBlock.index + 1;
	        Long nextTimestamp = new Date().getTime() / 1000;
	       String nextHash = Block.calculateHash(nextIndex, previousBlock.hash, nextTimestamp, blockData);
	        return new Block(nextIndex, previousBlock.hash, nextTimestamp, blockData, nextHash);
	    }
	 public static  boolean isValidNewBlock(Block newBlock, Block previousBlock){
		    if (previousBlock.index + 1 != newBlock.index) {
		        logger.info("invalid index");
		        return false;
		    } else if (!previousBlock.hash.equals(newBlock.previousHash)) {
		        logger.info("invalid previoushash");
		        return false;
		    } else if (Block.calculateHash(newBlock.index, newBlock.previousHash, newBlock.timestamp, newBlock.data).equals( newBlock.hash)) {
		        logger.info("invalid hash: " +Block.calculateHash(newBlock.index, newBlock.previousHash, newBlock.timestamp, newBlock.data) + " " + newBlock.hash);
		        return false;
		    }
		    return true;
		}
}
