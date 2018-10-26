package app.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.home.dao.ConfigMapper;
import app.home.model.Config;
import app.home.service.ConfigService;

@Service("ConfigService")
public class ConfigServiceImpl implements ConfigService {

	private ConfigMapper thisMapper;
	
	public ConfigMapper getConfigMapper() {
		return thisMapper;
	}
	
	@Autowired
	public void setConfigMapper(ConfigMapper thisMapper){
		this.thisMapper = thisMapper;
	}
	
	public Integer add(Config record) {
		return thisMapper.insertSelective(record);
	}

	public Integer delete(String id) {
		return thisMapper.deleteByPrimaryKey(id);
	}

	public Config find(String id) {
		return thisMapper.selectByPrimaryKey(id);
	}

	public Integer save(Config record) {
		return thisMapper.updateByPrimaryKey(record);
	}

	public List<Config> select() {
		return thisMapper.selectAll();
	}

}
