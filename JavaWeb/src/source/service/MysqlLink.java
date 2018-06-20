package source.service;

import java.sql.*;
import java.util.Arrays;
/**
 * Created by sophia on 30/07/2017.
 */
public class MysqlLink {
    private static final String URL = "jdbc:mysql://localhost:3306/blog?useSSL=true&useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    protected static final String[] ALL = new String[]{"*"};

    /**
     * 获取数据库连接
     *
     * @return Statement
     */
    private static Statement getLink() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构造查询条件语句
     *
     * @param condition String
     * @return 新字符串
     */
    private static String prepareCondition(String condition) {
        return (condition.equals("")) ? "" : " WHERE " + condition;
    }


    /**
     * 构造查询语句
     *
     * @param table     数据表
     * @param fields    多个字段名
     * @param condition 查询条件
     * @return 新字符串
     */
    private static String prepareSelect(String table, String[] fields, String condition) {
        return "SELECT " + arrayToString(fields) + " FROM " + table + prepareCondition(condition);
    }


    /**
     * 构造插入语句（1 插入全字符串字段）
     *
     * @param table  数据表
     * @param keys   多个字段名
     * @param values 多个字段值
     * @return 新字符串
     */
    private static String prepareInsert(String table, String[] keys, Object[] values) {
        return "INSERT INTO " + table + "(" + arrayToString(keys) + ") VALUES(" + changeToString(values) + ")";
    }

    /**
     * 构造更新语句
     *
     * @param table     数据表
     * @param keys      多个字段名
     * @param newValues 多个字段值
     * @param condition 查询条件
     * @return 新字符串
     */
    private static String prepareUpdate(String table, String[] keys, Object[] newValues, String condition) {
        return "UPDATE " + table + " SET " + arrayToString(equalArray(keys, newValues)) + prepareCondition(condition);
    }


    /**
     * 构造删除语句
     *
     * @param table     数据表
     * @param condition 查询条件
     * @return 新字符串
     */
    private static String prepareDelete(String table, String condition) {
        return "DELETE FROM " + table + prepareCondition(condition);
    }


    /**
     * 将数组中各值两端加上引号
     *
     * @param values 字符串数组
     * @return 新字符串
     */
    private static String changeToString(Object[] values) {
        String[] newValues = new String[values.length];
        for (int item = 0; item < values.length; item++) {
            newValues[item] = addQuotes(values[item]);
        }
        return arrayToString(newValues);
    }


    /**
     * 数组转换为字符串
     *
     * @param values 字符串数组
     * @return 新字符串
     */
    public static String arrayToString(String[] values) {
        return Arrays.toString(values).replace("[", "").replace("]", "");
    }


    /**
     * 字符串两端添加引号
     *
     * @param value 对象
     * @return 新字符串
     */
    public static String addQuotes(Object value) {
        return null == value ? null : (value instanceof String ? "\'" + value.toString() + "\'" : value.toString() + "");
    }


    /**
     * 查询（多个字段）
     *
     * @param table     数据表
     * @param keys      多个字段名
     * @param condition 查询条件
     * @return 结果集
     */
    protected static ResultSet select(String table, String[] keys, String condition) {
        Statement link = MysqlLink.getLink();
        if (null != link) {
            try {
                String sql = MysqlLink.prepareSelect(table, keys, condition);
                return link.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 查询（单个字段）
     *
     * @param table     数据表
     * @param key       字段名
     * @param condition 字段值
     * @return 结果集
     */
    protected static ResultSet select(String table, String key, String condition) {
        return select(table, toArray(key), condition);
    }

    /**
     * 检测存在（处理结果集）
     *
     * @param resultSet 结果集
     * @return 判断结果
     */
    private static boolean exist(ResultSet resultSet) {
        try {
            return resultSet != null && resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 检测存在（单个字段）
     *
     * @param table 数据表
     * @param key   字段名
     * @param value 字段值
     * @return 判断结果
     */
    protected static boolean exist(String table, String key, Object value) {
        ResultSet resultSet = select(table, key, equal(key, value));
        return exist(resultSet);
    }

    /**
     * 检测存在（根据查询条件）
     *
     * @param table     数据表
     * @param key       字段名
     * @param condition 查询条件
     * @return 判断结果
     */
    protected static boolean exist(String table, String key, String condition) {
        ResultSet resultSet = select(table, key, condition);
        return exist(resultSet);
    }


    /**
     * 插入记录（多个字段）
     *
     * @param table  数据表
     * @param keys   多个字段名
     * @param values 多个字段值
     * @return 处理结果
     */
    protected static boolean insert(String table, String[] keys, Object[] values) {
        Statement link = MysqlLink.getLink();
        if (null != link) {
            try {
                String sql = MysqlLink.prepareInsert(table, keys, values);
                return link.executeUpdate(sql) > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 插入记录（单个字段）
     *
     * @param table 数据表
     * @param key   字段名
     * @param value 字段值
     * @return 处理结果
     */
    protected static boolean insert(String table, String key, Object value) {
        return insert(table, toArray(key), toArray(value));
    }

    /**
     * 更新记录（多个字段）
     *
     * @param table     数据表
     * @param keys      多个字段名
     * @param newValues 多个字段值
     * @param condition 查询条件
     * @return 处理结果
     */
    protected static boolean update(String table, String[] keys, Object[] newValues, String condition) {
        Statement link = MysqlLink.getLink();
        if (null != link) {
            try {
                String sql = MysqlLink.prepareUpdate(table, keys, newValues, condition);
                return link.executeUpdate(sql) > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 更新记录（单个字段）
     *
     * @param table     数据表
     * @param key       字段名
     * @param value     字段值
     * @param condition 查询条件
     * @return 处理结果
     */
    protected static boolean update(String table, String key, Object value, String condition) {
        return update(table, toArray(key), toArray(value), condition);
    }

    /**
     * 删除记录
     *
     * @param table     数据表
     * @param condition 查询条件
     * @return 处理结果
     */
    protected static boolean delete(String table, String condition) {
        Statement link = MysqlLink.getLink();
        if (null != link) {
            try {
                String sql = MysqlLink.prepareDelete(table, condition);
                return link.executeUpdate(sql) > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 用 或 连接查询条件
     *
     * @param item 字符串数组
     * @return 新字符串
     */
    protected static String or(String... item) {
        return "(" + arrayToString(item).replace(",", " OR ") + ")";
    }

    /**
     * 用 与 连接查询条件
     *
     * @param item 字符串数组
     * @return 新字符串
     */
    protected static String and(String... item) {
        return "(" + arrayToString(item).replace(",", " AND ") + ")";
    }

    /**
     * 构造升序语句
     *
     * @param order 字段名
     * @return 新字符串
     */
    protected static String orderBy(String order) {
        return " ORDER BY " + order;
    }

    /**
     * 构造等式键值对（字符串值）
     *
     * @param key   字段名
     * @param value 字段值
     * @return 新字符串
     */
    protected static String equal(String key, Object value) {
        return key + " = " + addQuotes(value);
    }

    protected static String bigger(String key, Object value) {
        return key + " > " + addQuotes(value);
    }

    protected static String biggerOrEqual(String key, Object value) {
        return key + " >= " + addQuotes(value);
    }

    protected static String less(String key, Object value) {
        return key + " < " + addQuotes(value);
    }

    protected static String lessOrEqual(String key, Object value) {
        return key + " <= " + addQuotes(value);
    }

    /**
     * 集合多个字符串
     *
     * @param items 字符串组
     * @return 字符串数组
     */
    protected static String[] toArray(String... items) {
        return items;
    }

    protected static Object[] toArray(Object... items) {
        return items;
    }


    protected static String equalsAnd(String[] keys, Object[] values) {
        return and(equalArray(keys, values));
    }

    protected static String equalsOr(String[] keys, Object[] values) {
        return or(equalArray(keys, values));
    }

    private static String[] equalArray(String[] keys, Object[] values) {
        int length = keys.length;
        String[] output = new String[length];
        for (int item = 0; item < length; item++) {
            output[item] = equal(keys[item], values[item]);
        }
        return output;
    }

    protected static String max(String value) {
        return "MAX(" + value + ")";
    }

    protected static ResultSet sortArticle(){
        Statement link = MysqlLink.getLink();
        if (null != link) {
            try {
                String sql = "SELECT * FROM article ORDER BY last_date DESC ";
                return link.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}


