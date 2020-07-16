package net.crow.ptop.blockchain.shima.dto.adminconsole.request;

import net.crow.ptop.blockchain.shima.dto.adminconsole.ConfigurationDto;

public class SetConfigurationRequest {

	private ConfigurationDto configurationDto;
	
	public ConfigurationDto getConfigurationDto() {
        return configurationDto;
    }

    public void setConfigurationDto(ConfigurationDto configurationDto) {
        this.configurationDto = configurationDto;
    }
}
