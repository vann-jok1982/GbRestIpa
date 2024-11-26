package van_jok.RestIpiRickAndMorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import van_jok.RestIpiRickAndMorty.models.Characters;
import van_jok.RestIpiRickAndMorty.servises.ServiceApi;

@Controller
public class ControllerApi {
    @Autowired
    private ServiceApi serviceApi;

    @ResponseBody
    @GetMapping("/AllRest")
    public ResponseEntity<Characters> getCharacters() {
        Characters allCharacters = serviceApi.getAllCharacters();
        return new ResponseEntity<>(allCharacters, HttpStatus.OK);
    }

    @GetMapping("AllHttp")
    public String allHttp(Model model) {
        Characters allCharacters = serviceApi.getAllCharacters();
        model.addAttribute("allCharacters", allCharacters);
        return "rikimorty";
    }
}
