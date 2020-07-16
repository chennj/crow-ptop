package net.crow.ptop.blockchain.shima.dto.adminconsole.response;

import net.crow.ptop.blockchain.shima.dto.adminconsole.ConfigurationDto;

public class GetConfigurationByConfigurationKeyResponse {

	private ConfigurationDto configurationDto;
	
	public ConfigurationDto getConfigurationDto() {
        return configurationDto;
    }

    public void setConfigurationDto(ConfigurationDto configurationDto) {
        this.configurationDto = configurationDto;
    }
}
