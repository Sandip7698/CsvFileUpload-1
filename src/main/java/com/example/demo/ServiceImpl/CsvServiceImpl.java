package com.example.demo.ServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CsvRepository;
import com.example.demo.model.CsvFile;
import com.example.demo.service.CsvService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
@Service
public class CsvServiceImpl implements CsvService {
@Autowired
CsvRepository csvRepository;

@Override
public ResponseEntity<String> uploadFile(MultipartFile multipartFile) throws Exception {
	List<CsvFile> list = csvRepository.findAll();
	CsvParserSettings csvParserSettings = new CsvParserSettings();
	csvParserSettings.setHeaderExtractionEnabled(true);
	CsvParser parser = new CsvParser(csvParserSettings);
	List<com.univocity.parsers.common.record.Record> records = parser.parseAllRecords(multipartFile.getInputStream());
	records.forEach(record-> {
		CsvFile csvFile=new CsvFile();
		csvFile.setItemId(record.getString("Item ID"));
		csvFile.setItemName(record.getString("Item Name"));
		csvFile.setBrand(record.getString("Brand"));
		csvFile.setDimensionUnit(record.getString("Dimension Unit"));
		csvFile.setEan(record.getString("ean"));
		csvFile.setInventoryAccount(record.getString("Inventory Account"));
		csvFile.setIsbn(record.getString("ISBN"));
		csvFile.setIsComboProduct(record.getBoolean("Is Combo Product"));
		csvFile.setIsReturnableItem(record.getBoolean("Is Returnable Item"));
		csvFile.setItemType(record.getString("Item Type"));
		csvFile.setLastSyncTime(record.getString("Last Sync Time"));
		csvFile.setManufacturer(record.getString("Manufacturer"));
		csvFile.setOpeningStock(record.getString("Opening Stock"));
		csvFile.setOpeningStockValue(record.getString("Opening Stock Value"));
		csvFile.setPackageHeight(record.getLong("Package Height"));
		csvFile.setPackageLength(record.getLong("Package Length"));
		csvFile.setPackageWeight(record.getLong("Package Weight"));
		csvFile.setPackageWidth(record.getLong("Package Width"));
		csvFile.setPartNumber(record.getLong("Part Number"));
		csvFile.setPreferredVendor(record.getString("Preferred Vendor"));
		csvFile.setProductTypes(record.getString("Product Type"));
		csvFile.setPurchaseAccount(record.getString("Purchase Account"));
		csvFile.setPurchaseDescription(record.getString("Purchase Description"));
		csvFile.setPurchasePrice(record.getString("Purchase Price"));
		csvFile.setRecoderLevel(record.getString("Reorder Level"));
		csvFile.setReferanceId(record.getLong("Reference ID"));
		csvFile.setSalesAccount(record.getString("Sales Account"));
		csvFile.setSalesDescription(record.getString("Sales Description"));
		csvFile.setSellingPrice(record.getString("Selling Price"));
		csvFile.setSku(record.getString("SKU"));
		csvFile.setSource(record.getLong("Source"));
		csvFile.setStatus(record.getString("Status"));
		csvFile.setStockOnHand(record.getLong("Stock On Hand"));
		csvFile.setTaxName(record.getString("Tax Name"));
		csvFile.setTaxPercentage(record.getLong("Tax Percentage"));
		csvFile.setTaxType(record.getString("Tax Type"));
		csvFile.setUnit(record.getString("Unit"));
		csvFile.setUpc(record.getString("UPC"));
		csvFile.setWeightUnit(record.getString("Weight Unit"));
		list.add(csvFile);
		csvRepository.saveAll(list);
	});
	return null;
}

}
