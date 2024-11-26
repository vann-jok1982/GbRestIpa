package van_jok.RestIpiRickAndMorty.servises;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import van_jok.RestIpiRickAndMorty.models.Characters;

import java.util.List;

@Service
public class ServiceApiImpl implements ServiceApi{
    private RestTemplate template;
    private HttpHeaders headers;

    public ServiceApiImpl() {
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();
    }

    private static final String CHARACTER_API_URL = "https://rickandmortyapi.com/api/character";
    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Characters> response=template.exchange(CHARACTER_API_URL, HttpMethod.GET, entity, Characters.class);
        return response.getBody();
    }
}
