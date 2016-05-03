package com.ai.slp.route.cache.dao.impl;

import com.ai.slp.route.cache.dao.inter.IRouteDao;
import com.ai.slp.route.common.util.DBQueryTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xin on 16-4-29.
 */
public class RouteDaoImpl implements IRouteDao {
    @Override
    public boolean checkStatusIsValidate(final String routeId) throws SQLException {
        final String sql = "SELECT COUNT(*) AS totalSize FROM ROUTE WHERE ROUTE_ID=? AND STATE LIKE ?";
        return DBQueryTemplate.query(new DBQueryTemplate.Executor<Boolean>() {
            @Override
            public Boolean query(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, routeId);
                ps.setString(2, "2%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("totalSize") > 0 ? true : false;
                }
                return false;
            }
        });
    }
}