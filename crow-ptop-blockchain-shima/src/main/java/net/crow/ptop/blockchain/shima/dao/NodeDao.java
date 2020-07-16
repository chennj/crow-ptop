package net.crow.ptop.blockchain.shima.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import net.crow.ptop.blockchain.shima.dto.nodeserver.SimpleNode;
import net.crow.ptop.blockchain.shima.entity.NodeEntity;

@Mapper
@Component
public interface NodeDao {

	/**
     * 查询节点
     */
    NodeEntity queryNode(SimpleNode simpleNode);
    /**
     * 获取所有未分叉节点
     */
    List<NodeEntity> queryAllNoForkNodeList();
    /**
     * 获取所有活着的、未分叉节点
     */
    List<NodeEntity> queryAllNoForkAliveNodeList();
    /**
     * 添加节点
     */
    void addNode(NodeEntity node);
    /**
     * 更新节点信息
     */
    int updateNode(NodeEntity node);
    /**
     * 删除节点
     */
    boolean deleteNode(SimpleNode simpleNode);

    /**
     * 查询所有节点
     */
    List<NodeEntity> queryAllNodeList();
}
