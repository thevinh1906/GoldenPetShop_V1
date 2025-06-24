package com.utc2.petShop.model.services;

import com.utc2.petShop.model.entities.RevenueReport.RevenueReport;
import com.utc2.petShop.model.repository.Select.SelectRevenueReport;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class ExportToEX {
    public static void xuatExcelRevenueReport() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Revenue Report");
        List<RevenueReport> revenueReports = SelectRevenueReport.getAllRevenueReports();


        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("BẢNG DOANH THU THEO THÁNG");

// Gộp các ô từ cột 0 đến 5 lại làm 1 ô tiêu đề
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

// Định dạng cho tiêu đề bảng
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCell.setCellStyle(titleStyle);


        // Header
        String[] headers = {"ID", "Month", "Year", "Total revenue", "Total bill", "Note"};
        Row headerRow = sheet.createRow(2);

        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.DARK_BLUE.getIndex()); // Chữ màu xanh đậm
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // Nền vàng nhạt
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

// Ghi dữ liệu từ danh sách revenueReports

        CellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0; i < revenueReports.size(); i++) {
            RevenueReport r = revenueReports.get(i);
            Row row = sheet.createRow(i + 3); // Bắt đầu từ dòng 1, vì dòng 0 là header

            Cell cell0 = row.createCell(0);
            cell0.setCellValue("RR" + r.getId());
            cell0.setCellStyle(rowStyle);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(r.getMonth());
            cell1.setCellStyle(rowStyle);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(r.getYear());
            cell2.setCellStyle(rowStyle);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(r.getTotalRevenue());
            cell3.setCellStyle(rowStyle);

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(r.getTotalBill());
            cell4.setCellStyle(rowStyle);

            Cell cell5 = row.createCell(5);
            cell5.setCellValue(""); // Note
            cell5.setCellStyle(rowStyle);
        }

// Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

// Ghi file Excel ra ổ đĩa
        try (FileOutputStream out = new FileOutputStream("Revenue_Report.xlsx")) {
            workbook.write(out);
            workbook.close();
            System.out.println("✅ Đã xuất dữ liệu ra Revenue_Report.xlsx");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Xuất file thành công");
            alert.setHeaderText(null);
            alert.setContentText("File Excel đã được lưu\n");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Xuất Excel thành công: " + filePath);
//        if (Desktop.isDesktopSupported()) {
//            Desktop desktop = Desktop.getDesktop();
//            desktop.open(new File(filePath));
//        } else {
//            System.out.println("Hệ thống không hỗ trợ mở file.");
//        }
    }
}

