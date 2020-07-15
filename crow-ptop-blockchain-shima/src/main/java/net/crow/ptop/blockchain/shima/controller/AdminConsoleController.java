package net.crow.ptop.blockchain.shima.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员控制台的控制器：用于控制本地区块链节点，如激活矿工、停用矿工、同步其它节点数据等。
 * @author chenn
 *
 */
@Controller
@RequestMapping
public class AdminConsoleController {

	private static final Logger logger = LoggerFactory.getLogger(AdminConsoleController.class);
}
