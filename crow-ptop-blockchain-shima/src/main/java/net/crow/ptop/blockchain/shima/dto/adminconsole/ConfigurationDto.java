package net.crow.ptop.blockchain.shima.dto.adminconsole;

public class ConfigurationDto {

	private String confKey;
    private String confValue;

    public ConfigurationDto() {
    }

    public ConfigurationDto(String confKey, String confValue) {
        this.confKey = confKey;
        this.confValue = confValue;
    }
    
    public String getConfKey() {
        return confKey;
    }

    public void setConfKey(String confKey) {
        this.confKey = confKey;
    }

    public String getConfValue() {
        return confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }
}
