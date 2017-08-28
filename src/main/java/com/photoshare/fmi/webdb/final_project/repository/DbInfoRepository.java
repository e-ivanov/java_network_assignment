/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.repository;

import com.photoshare.fmi.webdb.final_project.domain.DbColumn;
import com.photoshare.fmi.webdb.final_project.domain.DbTable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

/**
 *
 * @author killer
 */
@Singleton
public class DbInfoRepository extends BaseRepository {

    public List<String> getDatabases() throws SQLException {
        Connection conn = null;
        List<String> dbNames = new ArrayList<String>();
        ResultSet rs = null;
        try {

            conn = ds.getConnection();
            rs = conn.getMetaData().getCatalogs();
            while (rs.next()) {
                dbNames.add(rs.getString("TABLE_CAT"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        if (conn != null) {
            conn.close();
        }

        return dbNames;
    }

    public List<DbTable> getTables(String database) {
        Connection con = null;
        DatabaseMetaData metaData = null;
        List<DbTable> dbTables = new ArrayList<DbTable>();
        try {
            con = ds.getConnection();
            String tableType[] = {"TABLE"};
            metaData = con.getMetaData();
            StringBuilder builder = new StringBuilder();

            ResultSet result = metaData.getTables(database, null, null, tableType);
            while (result.next()) {
                DbTable table = new DbTable();
                String tableName = result.getString(3);
                table.setName(tableName);
                builder.append(tableName + "( ");
                ResultSet columns = metaData.getColumns(database, null, tableName, null);

                while (columns.next()) {
                    DbColumn column = new DbColumn();
                    String columnName = columns.getString(4);
                    String columnDatatype = columns.getString(6);
                    String isAutoincrement = columns.getString(23);
                    String isNullable = columns.getString(18);
                    String defaultValue = columns.getString(13);
                    column.setName(columnName);
                    column.setType(columnDatatype);
                    column.setAutoincrement(isAutoincrement);
                    column.setIsNullable(isNullable);
                    column.setDefaultValue(defaultValue);
                    table.getColumns().add(column);
                }
                dbTables.add(table);
            }
        }catch(Exception e){
            String test = "";
        }
        
        return dbTables;
    }
}
