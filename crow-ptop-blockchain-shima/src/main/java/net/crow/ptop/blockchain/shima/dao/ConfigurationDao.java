package net.crow.ptop.blockchain.shima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import net.crow.ptop.blockchain.shima.entity.ConfigurationEntity;

@Mapper
@Component
public interface ConfigurationDao {

	String getConfiguratioValue(@Param("confKey") String confKey);

    void addConfiguration(ConfigurationEntity configurationEntity);

    void updateConfiguration(ConfigurationEntity configurationEntity);
}
