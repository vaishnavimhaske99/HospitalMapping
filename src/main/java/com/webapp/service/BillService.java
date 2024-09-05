// BillService.java
package com.webapp.service;

import com.webapp.dto.BillDTO;
import java.util.List;

public interface BillService {
    List<BillDTO> getAllBills();
    BillDTO getBillById(int id);
    BillDTO createBill(BillDTO billDTO);
    BillDTO updateBill(int id, BillDTO billDTO);
    void deleteBill(int id);
}
