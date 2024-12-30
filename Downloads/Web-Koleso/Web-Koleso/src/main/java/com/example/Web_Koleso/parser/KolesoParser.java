package com.example.Web_Koleso.parser;

import com.example.Web_Koleso.models.Tire;
import com.example.Web_Koleso.models.Warehouse;
import com.example.Web_Koleso.models.WarehouseTire;
import com.example.Web_Koleso.repositories.TireRepository;
import com.example.Web_Koleso.repositories.WarehouseRepository;
import com.example.Web_Koleso.repositories.WarehouseTireRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
@Transactional
@Component
public class KolesoParser {
    private final TireRepository tireRepository;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseTireRepository warehouseTireRepository;

    public KolesoParser(TireRepository tireRepository, WarehouseRepository warehouseRepository, WarehouseTireRepository warehouseTireRepository) {
        this.tireRepository = tireRepository;
        this.warehouseRepository = warehouseRepository;
        this.warehouseTireRepository = warehouseTireRepository;
    }

    public void parseExcelFile(InputStream filePath) throws IOException {

        try (
             Workbook workbook = new XSSFWorkbook(filePath)) {

            Sheet sheet = workbook.getSheetAt(0); // это Лист
            //row зто ряд

            // Skip irrelevant rows (first 6 rows in this case)
//            for (int i = 0; i < 6 && rowIterator.hasNext(); i++) {
//                rowIterator.next();
//            }
            Warehouse warehouse=null;
            for (Row row : sheet) {
                try {
                    String stringCellValue = row.getCell(0).getStringCellValue();

                    if (stringCellValue.matches("\\d+")) {
                        long article = Long.parseLong(row.getCell(0).getStringCellValue());
                        String name = row.getCell(2).getStringCellValue();
                        Tire tire = tireRepository.findByArticle(article)
                                .orElseGet(() -> {
                                    Tire newTire = new Tire();
                                    newTire.setArticle(article);
                                    newTire.setName(name);
                                    return tireRepository.save(newTire);
                                });
                        WarehouseTire warehouseTire = new WarehouseTire();
                        warehouseTire.setTire(tire);
                        warehouseTire.setWarehouse(warehouse);
                        int availableNow = 0;
                        try {
                            availableNow = (int) row.getCell(6).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setAvailableNow(availableNow);
                        warehouseTireRepository.save(warehouseTire);

                        int expectedFuture = 0;
                        try {
                            expectedFuture = (int) row.getCell(8).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setExpectedFuture(expectedFuture);
                        warehouseTireRepository.save(warehouseTire);

                        int reservedFuture = 0;
                        try {
                            reservedFuture = (int) row.getCell(9).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setReservedFuture(reservedFuture);
                        warehouseTireRepository.save(warehouseTire);

                        int availableFuture = 0;
                        try {
                            availableFuture = (int) row.getCell(10).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setAvailableFuture(availableFuture);
                        warehouseTireRepository.save(warehouseTire);

                        int availableTotal = 0;
                        try {
                            availableTotal = (int) row.getCell(11).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setAvailableTotal(availableTotal);
                        warehouseTireRepository.save(warehouseTire);

                        int providedTotal = 0;
                        try {
                            providedTotal = (int) row.getCell(12).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setProvidedTotal(providedTotal);
                        warehouseTireRepository.save(warehouseTire);

                        int deficitTotal = 0;
                        try {
                            deficitTotal = (int) row.getCell(13).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setDeficitTotal(deficitTotal);
                        warehouseTireRepository.save(warehouseTire);

                        int surplusTotal = 0;
                        try {
                            surplusTotal = (int) row.getCell(14).getNumericCellValue();
                        } catch (Exception e) {}
                        warehouseTire.setSurplusTotal(surplusTotal);
                        warehouseTireRepository.save(warehouseTire);
                    }

                    if (stringCellValue.startsWith("Склад ООО")) {
                        String name = row.getCell(0).getStringCellValue();
                       Warehouse newWarehouse = new Warehouse();
                       newWarehouse.setName(name);
                        warehouse = warehouseRepository.save(newWarehouse);

                    }
                } catch (Exception e) {
                }
            }
        }
    }
}