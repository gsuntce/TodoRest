package backup.auth;

import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins="http://localhost:3200")
@CrossOrigin(origins="*")
@RestController
public class BasicAuthenticationController {

    @GetMapping("/basicauth")
    public AuthenticationBean basicAuth(){
        return new AuthenticationBean("Authentication Success");
    }

}
