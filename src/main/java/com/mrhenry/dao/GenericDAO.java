package com.mrhenry.dao;

import java.util.List;

import com.mrhenry.mapper.RowMapper;

public interface GenericDAO<T> {
	@SuppressWarnings("hiding")
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

	void updateAndDelete(String sql, Object... parameters);

	Long insert(String sql, Object... parameters);

	T queryFindOne(String sql, RowMapper<T> rowMapper, Long id);
	
	int count(String sql, Object... parameters);
}
