package net.crow.ptop.blockchain.shima.dto.blockchainbrowser.request;

import net.crow.ptop.blockchain.shima.dto.common.page.PageCondition;

public class QueryMiningTransactionListRequest {

	private PageCondition pageCondition;
	
	public PageCondition getPageCondition() {
        return pageCondition;
    }

    public void setPageCondition(PageCondition pageCondition) {
        this.pageCondition = pageCondition;
    }
}
