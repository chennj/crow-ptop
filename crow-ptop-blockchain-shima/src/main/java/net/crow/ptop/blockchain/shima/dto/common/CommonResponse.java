package net.crow.ptop.blockchain.shima.dto.common;

public class CommonResponse<T> {

	private T data;
	
	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
