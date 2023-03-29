package com.fit.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fit.modular.system.dao.RelationMapper;
import com.fit.modular.system.model.Relation;
import com.fit.modular.system.service.IRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author Aim
 * @since 2018-02-22
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

}
