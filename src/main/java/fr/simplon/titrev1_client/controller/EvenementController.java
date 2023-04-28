package fr.simplon.titrev1_client.controller;

import fr.simplon.titrev1_client.entity.Evenement;
import fr.simplon.titrev1_client.entity.Organisme;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class EvenementController {


    private RestTemplate restTemplate;


    @GetMapping("/programmation")
    public String afficherEvenement(Model model){
        this.restTemplate = new RestTemplate();
        String url ="http://localhost:8080/rest/evenements";
        ResponseEntity<List<Evenement>> response=restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Evenement>>(){});
        List<Evenement> evenements = response.getBody();
        model.addAttribute("evenements", evenements);
        return "programmation";
    }


    @GetMapping("/api/evenements")
    @ResponseBody
    public List<Evenement> getAllEventsAPI() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<List<Evenement>> response = restTemplate.exchange("http://localhost:8080/rest/api/evenements", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Evenement>>() {});
        return response.getBody();
    }

}
