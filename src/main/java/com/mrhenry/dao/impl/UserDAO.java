package com.mrhenry.dao.impl;

import java.util.List;

import com.mrhenry.dao.IUserDAO;
import com.mrhenry.mapper.UserMapper;
import com.mrhenry.model.User;
import com.mrhenry.paging.IPageble;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

	@Override
	public User login(String userName, String password, int status) {
		StringBuilder sql =new StringBuilder("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role AS r ON r.id = u.roleid ");
		sql.append("WHERE username = ? AND password = ? AND status = ?");
		
		List<User> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public List<User> findAll(IPageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM USER AS u INNER JOIN role AS r ON u.roleid = r.id ");
		
		if(pageble.getSorter().getSortName() != null) {
			sql.append("ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy());
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+","+pageble.getLimit());
		}
		
		return query(sql.toString(), new UserMapper());
	}

	@Override
	public User findOne(Long id) {
		String sql = "SELECT * FROM user WHERE id=?";
		return queryFindOne(sql, new UserMapper(), id);
	}

	@Override
	public Long save(User user) {
		String sql = "INSERT INTO user(username, password, fullname, status, roleid, createddate, modifieddate, createdby, modifiedBY) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		Long id = insert(sql, user.getUsername(), user.getPassword(), user.getFullname(), user.getStatus(), user.getRoleid(),
				user.getCreatedDate(), user.getModifiedDate(), user.getCreatedBy(), user.getModifiedBy());
		return id;
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE user SET username= ?, password= ?, fullname= ?, status= ?, roleid= ?, createddate= ?, "
				+ "modifieddate= ?, createdby= ?, modifiedBY= ? WHERE id=?";
		updateAndDelete(sql, user.getUsername(), user.getPassword(), user.getFullname(), user.getStatus(), user.getRoleid(),
				user.getCreatedDate(), user.getModifiedDate(), user.getCreatedBy(), user.getModifiedBy(), user.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "UPDATE user set status=0 WHERE id=?";
		updateAndDelete(sql, id);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM user";
		return count(sql);
	}

}
