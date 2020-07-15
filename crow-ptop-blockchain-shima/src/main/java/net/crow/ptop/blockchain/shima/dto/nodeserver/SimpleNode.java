package net.crow.ptop.blockchain.shima.dto.nodeserver;

public class SimpleNode {

	private String ip;
    private int port;

    public SimpleNode() {
    }

    public SimpleNode(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
