package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request;

import net.crow.ptop.blockchain.shima.dto.common.page.PageCondition;

public class QueryUtxosByAddressRequest {

	private String address;

    private PageCondition pageCondition;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PageCondition getPageCondition() {
        return pageCondition;
    }

    public void setPageCondition(PageCondition pageCondition) {
        this.pageCondition = pageCondition;
    }
}
