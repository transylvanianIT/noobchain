import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class noobchain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 6;
    public static void main(String[] args) {
        //add our blocks to the blockchain arraylist:
        blockchain.add(new Block("HI im the first block", "0"));
        System.out.println("Trying to block mine 1..");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("HI im the second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to block mine 2..");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("HI im the third block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to block mine 3..");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nThe block chain is valid" + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain:");
        System.out.println(blockchainJson);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blochains to check hashes;
        for (int i=1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare reg hash and calc hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current hashes not equal");
                return false;
            }
            //compare reg hash and prev hash:
            if(!previousBlock.hash.equals(currentBlock.previousHash) ){
                System.out.println("Previous hashes not equal");
                return false;
            }

            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("The block hasnt been mined");
                return false;
            }
        }
        return true;
    }
}
