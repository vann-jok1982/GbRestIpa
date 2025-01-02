package com.example.Web_Koleso.parser;

import com.example.Web_Koleso.models.Tire;
import com.example.Web_Koleso.models.Warehouse;
import com.example.Web_Koleso.models.WarehouseTire;
import com.example.Web_Koleso.servises.TireService;
import com.example.Web_Koleso.servises.WarehouseService;
import com.example.Web_Koleso.servises.WarehouseTireService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class KolesoParser {
    private final WarehouseService warehouseService;
    private final WarehouseTireService warehouseTireService;
    private final TireService tireService;

    public KolesoParser(WarehouseService warehouseService, WarehouseTireService warehouseTireService, TireService tireService) {
        this.warehouseService = warehouseService;
        this.warehouseTireService = warehouseTireService;
        this.tireService = tireService;
    }

    public void parseExcelFile(InputStream filePath) throws IOException {
        warehouseTireService.deleteAll();
        tireService.deleteAll();
        warehouseService.deleteAll();

        try (
                Workbook workbook = new XSSFWorkbook(filePath)) {

            Sheet sheet = workbook.getSheetAt(0); // это Лист
            //row зто ряд

            // Skip irrelevant rows (first 6 rows in this case)
//            for (int i = 0; i < 6 && rowIterator.hasNext(); i++) {
//                rowIterator.next();
//            }
            Warehouse warehouse = null;
            for (Row row : sheet) {
                try {
                    String stringCellValue = row.getCell(0).getStringCellValue();

                    if (stringCellValue.matches("\\d+")) {
                        long article = Long.parseLong(row.getCell(0).getStringCellValue());
                        String name = row.getCell(2).getStringCellValue();
                        Tire tire = tireService.findByArticle(article)
                                .orElseGet(() -> {
                                    Tire newTire = new Tire();
                                    newTire.setArticle(article);
                                    newTire.setName(name);
                                    tireService.save(newTire);
                                    return newTire;
                                });
                        WarehouseTire warehouseTire = new WarehouseTire();
                        warehouseTire.setTire(tire);
                        warehouseTire.setWarehouse(warehouse);
                        int availableNow = 0;
                        try {
                            availableNow = (int) row.getCell(6).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setAvailableNow(availableNow);
                        warehouseTireService.save(warehouseTire);

                        int expectedFuture = 0;
                        try {
                            expectedFuture = (int) row.getCell(8).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setExpectedFuture(expectedFuture);
                        warehouseTireService.save(warehouseTire);

                        int reservedFuture = 0;
                        try {
                            reservedFuture = (int) row.getCell(9).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setReservedFuture(reservedFuture);
                        warehouseTireService.save(warehouseTire);

                        int availableFuture = 0;
                        try {
                            availableFuture = (int) row.getCell(10).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setAvailableFuture(availableFuture);
                        warehouseTireService.save(warehouseTire);

                        int availableTotal = 0;
                        try {
                            availableTotal = (int) row.getCell(11).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setAvailableTotal(availableTotal);
                        warehouseTireService.save(warehouseTire);

                        int providedTotal = 0;
                        try {
                            providedTotal = (int) row.getCell(12).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setProvidedTotal(providedTotal);
                        warehouseTireService.save(warehouseTire);

                        int deficitTotal = 0;
                        try {
                            deficitTotal = (int) row.getCell(13).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setDeficitTotal(deficitTotal);
                        warehouseTireService.save(warehouseTire);

                        int surplusTotal = 0;
                        try {
                            surplusTotal = (int) row.getCell(14).getNumericCellValue();
                        } catch (Exception e) {
                        }
                        warehouseTire.setSurplusTotal(surplusTotal);
                        warehouseTireService.save(warehouseTire);
                    }

                    if (stringCellValue.startsWith("Склад ООО")) {
                        String name = row.getCell(0).getStringCellValue();
                        warehouse = warehouseService.save(name);

                    }
                } catch (Exception e) {
                }
            }
        }
    }
}