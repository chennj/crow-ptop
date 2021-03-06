package net.crow.ptop.blockchain.core.model.transaction;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class Transaction implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 交易时间戳
     * 这个时间戳是由用户生成的，用户机器的时间戳可能超前或是滞后真正的时间戳，
     * 你无法强制要求用户的机器的时间戳是正确的，因此在使用这个时间戳时必须要考虑这可能并不是个正确的时间戳。
     */
    private long timestamp;
    
    /**
     * 交易的Hash是交易的摘要。交易的哈希确定了，具体的交易也就确定了。
     *
     * 区块链系统不允许同一个[交易的Hash]被使用两次或是两次以上。
     * 这个值的构成应当足够简单去验证这个值是否是唯一的。
     * 当区块数据足够庞大时，用户节点只有最近一部分区块与UTXO数据，这时节点必须也可以校验它的唯一性。
     * 这里建议它的构成是一串字符+时间戳。
     * 最近的区块只包含最近产生的交易，因此只要有最近的区块就可校验它的唯一性。
     *
     * 这个字段也可以用来表示一张独一无二的区块编号
     * 还有另外一个独一无二的编号，区块高度+交易在区块中的编号，这个编号有个缺点，只能在区块完全确定后，才能确定这个编号
     */
    private String transactionHash;
    
    /**
     * 交易类型
     * 冗余
     */
    private TransactionType transactionType;
    
    /**
     * 交易输入
     */
    private List<TransactionInput> inputs;
    
    /**
     * 交易输出
     */
    private List<TransactionOutput> outputs;
    
    /**
     * 附加消息
     * 当是跨链交易时，可以将跨链的地址写在这里
     * 尽量不使用这个字段
     */
    private List<String> messages;
    
    /**
     * 在区块中的交易序列号
     * 冗余
     */
    private BigInteger transactionSequenceNumberInBlock;
    
    /**
     * 在区块链中交易序列号
     * 冗余
     */
    private BigInteger transactionSequenceNumberInBlockChain;
    
    /**
     * 交易所在区块的区块高度
     * 冗余
     */
    private BigInteger blockHeight;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public List<TransactionInput> getInputs() {
		return inputs;
	}

	public void setInputs(List<TransactionInput> inputs) {
		this.inputs = inputs;
	}

	public List<TransactionOutput> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<TransactionOutput> outputs) {
		this.outputs = outputs;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public BigInteger getTransactionSequenceNumberInBlock() {
		return transactionSequenceNumberInBlock;
	}

	public void setTransactionSequenceNumberInBlock(BigInteger transactionSequenceNumberInBlock) {
		this.transactionSequenceNumberInBlock = transactionSequenceNumberInBlock;
	}

	public BigInteger getTransactionSequenceNumberInBlockChain() {
		return transactionSequenceNumberInBlockChain;
	}

	public void setTransactionSequenceNumberInBlockChain(BigInteger transactionSequenceNumberInBlockChain) {
		this.transactionSequenceNumberInBlockChain = transactionSequenceNumberInBlockChain;
	}

	public BigInteger getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(BigInteger blockHeight) {
		this.blockHeight = blockHeight;
	}

    
}
