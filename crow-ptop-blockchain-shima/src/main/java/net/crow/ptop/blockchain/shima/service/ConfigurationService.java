package net.crow.ptop.blockchain.shima.service;

import net.crow.ptop.blockchain.shima.dto.adminconsole.ConfigurationDto;

/**
 * 配置service
 * @author chenn
 *
 */
public interface ConfigurationService {

	/**
     * 根据配置Key获取配置
     */
    ConfigurationDto getConfigurationByConfigurationKey(String confKey);

    /**
     * 设置配置
     */
    void setConfiguration(ConfigurationDto configurationDto);
}
