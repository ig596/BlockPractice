package Blockchain.Block;

import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.hash.Hashing;

/**
 * Hello world!
 *
 */
public class Block extends Object 
{
	private static final Logger logger= LoggerFactory.getLogger(Block.class);
	int index;
	String previousHash;
	Long timestamp;
	Object data;
	String hash;
    Block(int index, String previousHash, long timestamp, Object data, String hash) {
        this.index = index;
        this.previousHash = previousHash.toString();
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash.toString();
        logger.info("New Block Created");
    }

    /** Calculates Sha256 Hash
     * 
     * @param index
     * @param previousHash
     * @param timestamp
     * @param data
     * @return Hash String
     */
    public static String calculateHash(int index, String previousHash, Long timestamp,Object data){
    	final String hashed = Hashing.sha256()
    	        .hashString(index + previousHash + timestamp + data.toString(), StandardCharsets.UTF_8)
    	        .toString();
    	logger.info("Calculated Hash");
    	return hashed;
    }
   /**
    * 
    * @param blockData
    * @return
    */
    
  

}
