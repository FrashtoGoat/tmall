package com.xiaoluban.tmallcommon.dao;

import com.xiaoluban.tmallcommon.vo.PmsCommentReplay;

public interface PmsCommentReplayDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsCommentReplay record);

    int insertSelective(PmsCommentReplay record);

    PmsCommentReplay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsCommentReplay record);

    int updateByPrimaryKey(PmsCommentReplay record);
}