package com.sora.pocket.reminder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private static final String INSERT_SQL = "INSERT INTO auth (key, accessToken) values (:key, :accessToken)";

    private static final String DELETE_BY_KEY_SQL = "DELETE FROM file_info WHERE file_id = :fileId";

    private static final String UPDATE_BY_KEY_SQL = "UPDATE file_info SET file_type = :fileType, file_name = :fileName, "
            + "file_path = :filePath, content_type = :contentType, content_length = :contentLength, registered_date = :registeredDate "
            + "WHERE file_id = :fileId";

    private static final String FIND_ONE_SQL = "SELECT id, key, accessToken FROM auth WHERE key = :key ORDER BY id";
    private static final String FIND_ALL_SQL = "SELECT id, key, accessToken FROM auth ORDER BY id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public int insert(String key, String accessToken) {
        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("key", key)
                    .addValue("accessToken", accessToken);
            return jdbcTemplate.update(INSERT_SQL, param);
        } catch (DataAccessException e) {
            log.error("DataAccessError : INSERT_SQL param:{}, error:{}", key, e);
            //throw new DemoSystemException("DataAccessError : FileInfoRepository INSERT_SQL", e);
            return 0;
        }
    }

//    public int deleteByKey(String fileId) {
//        try {
//            SqlParameterSource param = new MapSqlParameterSource().addValue("fileId", fileId);
//            return jdbcTemplate.update(DELETE_BY_KEY_SQL, param);
//        } catch(DataAccessException e) {
//            LOGGER.error("DataAccessError : DELETE_BY_KEY_SQL param:{}, error:{}", fileId, e);
//            throw new DemoSystemException("DataAccessError : FileInfoRepository DELETE_BY_KEY_SQL", e);
//        }
//    }
//
//    public int updateByKey(FileInfo fileInfo) {
//        try {
//            SqlParameterSource param = new BeanPropertySqlParameterSource(fileInfo);
//            return jdbcTemplate.update(UPDATE_BY_KEY_SQL, param);
//        } catch(DataAccessException e) {
//            LOGGER.error("DataAccessError : UPDATE_BY_KEY_SQL param:{}, error:{}", fileInfo, e);
//            throw new DemoSystemException("DataAccessError : FileInfoRepository UPDATE_BY_KEY_SQL", e);
//        }
//    }

    public String fineOne(String key) {
        try {
            SqlParameterSource param = new MapSqlParameterSource().addValue("key", key);
            return jdbcTemplate.queryForObject(FIND_ONE_SQL, param, rowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            log.error("DataAccessError : FIND_ONE_SQL param:{}, error:{}", key, e);
            //throw new DemoSystemException("DataAccessError : FileInfoRepository FIND_ONE_SQL", e);
            return null;
        }
    }

    public List<String> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_SQL, rowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            log.error("DataAccessError : FIND_ALL_SQL error:{}", e);
            //throw new DemoSystemException("DataAccessError : FileInfoRepository FIND_ALL_SQL", e);
            return Collections.emptyList();
        }
    }

    private RowMapper<String> rowMapper() {

        return new RowMapper<>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("accessToken");
            }
        };
    }
}
