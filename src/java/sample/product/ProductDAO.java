/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author linhn
 */
public class ProductDAO {

    private static final String SEARCH = "SELECT productID, productName, price, quantity, categoryID, importDate, usingDate, image FROM tblProduct WHERE productName LIKE ? AND usingDate > getDate() AND quantity > 0 AND status = 1";
    private static final String SEARCH_V2 = "SELECT productID, productName, price, quantity, categoryID, importDate, usingDate, image FROM tblProduct WHERE productName LIKE ? AND status = 1";
    private static final String UPDATE = "UPDATE tblProduct SET productName = ?, image = ?, price = ?, quantity = ?, importDate = ?, usingDate = ? WHERE productID = ?";
    private static final String DELETE = "UPDATE tblProduct SET status = 0 WHERE productID = ?";
    private static final String CREATE = "INSERT INTO tblProduct(productID, productName, image, categoryID, price, quantity, importDate, usingDate, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
    private static final String CHECK_QUANTITY = "SELECT quantity FROM tblProduct WHERE productID = ?";
    private static final String ORDER = "SELECT orderID FROM tblOrder";
    private static final String ADD_ORDERDETAIL = "INSERT INTO tblOrderDetail(detailID, price, quantity, orderID, productID, status) VALUES (?, ?, ? ,?, ?, 1)";
    private static final String ADD_ORDER = "INSERT INTO tblOrder(orderID, orderDate, total, userID, status) VALUES (?, ?, ?, ?, 1)";
    private static final String DETAIL = "SELECT detailID from tblOrderDetail";
    private static final String UPDATE_QUANTITY = "UPDATE tblProduct SET quantity = ? where productID = ?";

    public boolean updateQuantity(String quantity, String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setString(1, quantity);
                ptm.setString(2, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int checkDetail() throws SQLException {
        int detailID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DETAIL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    detailID++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return detailID;
    }

    public boolean addOrderDetail(String detailID, String price, String quantity, String orderID, String productID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDERDETAIL);
                ptm.setString(1, detailID);
                ptm.setString(2, price);
                ptm.setString(3, quantity);
                ptm.setString(4, orderID);
                ptm.setString(5, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public double checkQuantity(String productID) throws SQLException {
        double quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = Double.parseDouble(rs.getString("quantity"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }

    public boolean addOrder(String orderID, String orderDate, String total, String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDER);
                ptm.setString(1, orderID);
                ptm.setString(2, orderDate);
                ptm.setString(3, total);
                ptm.setString(4, userID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int checkOrder() throws SQLException {
        int count = 1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ORDER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public boolean create(ProductDTO product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getImage());
                ptm.setString(4, product.getCategoryID());
                ptm.setString(5, Double.toString(product.getPrice()));
                ptm.setString(6, Double.toString(product.getQuantity()));
                ptm.setString(7, sdf.format(product.getImportDate()));
                ptm.setString(8, sdf.format(product.getUsingDate()));
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean delete(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getImage());
                ptm.setString(3, Double.toString(product.getPrice()));
                ptm.setString(4, Double.toString(product.getQuantity()));
                ptm.setString(5, sdf.format(product.getImportDate()));
                ptm.setString(6, sdf.format(product.getUsingDate()));
                ptm.setString(7, product.getProductID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = Double.parseDouble(rs.getString("price"));
                    double quantity = Double.parseDouble(rs.getString("quantity"));
                    String categoryID = rs.getString("categoryID");
                    Date importDate = DBUtils.getDate(rs.getString("importDate"));
                    Date usingDate = DBUtils.getDate(rs.getString("usingDate"));
                    String image = rs.getString("image");
                    listProduct.add(new ProductDTO(productID, productName, image, categoryID, price, quantity, importDate, usingDate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProduct;
    }

    public List<ProductDTO> getListProductV2(String search) throws SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_V2);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = Double.parseDouble(rs.getString("price"));
                    double quantity = Double.parseDouble(rs.getString("quantity"));
                    String categoryID = rs.getString("categoryID");
                    Date importDate = DBUtils.getDate(rs.getString("importDate"));
                    Date usingDate = DBUtils.getDate(rs.getString("usingDate"));
                    String image = rs.getString("image");
                    listProduct.add(new ProductDTO(productID, productName, image, categoryID, price, quantity, importDate, usingDate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProduct;
    }

}
