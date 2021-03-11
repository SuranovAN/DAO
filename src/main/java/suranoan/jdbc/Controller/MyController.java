package suranoan.jdbc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suranoan.jdbc.repository.BDRepository;

import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class MyController {
    BDRepository bdRepository;

    public MyController(BDRepository bdRepository) {
        this.bdRepository = bdRepository;
    }

    @GetMapping("/products/fetch-product")
    public String getData(@RequestParam("name") String name) throws SQLException {
        return bdRepository.getProductName(name);
    }
}
