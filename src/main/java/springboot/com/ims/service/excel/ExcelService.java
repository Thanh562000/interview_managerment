package springboot.com.ims.service.excel;
import springboot.com.ims.dto.offerDto.OfferDTO;
import springboot.com.ims.helper.ExcelHelperOffer;
import springboot.com.ims.service.offer.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelService {

    private final OfferService offerService;
    public ByteArrayInputStream load(){

        //TODO:Check Here:
        List<OfferDTO> offerDTOList = offerService.getListOffer();
        ByteArrayInputStream in = ExcelHelperOffer.OfferDTOToExcel(offerDTOList);
        return in;
    }

    public ByteArrayInputStream loadByDate(LocalDate startDate, LocalDate endDate){

        //TODO:Check Here:
        List<OfferDTO> offerDTOList = offerService.findAllByDate(startDate, endDate);
        ByteArrayInputStream in = ExcelHelperOffer.OfferDTOToExcel(offerDTOList);
        return in;
    }
}
