package br.com.moipstore.service;


import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import org.springframework.stereotype.Service;

@Service
public class MoipAPIImpl implements MoipAPI {

    @Override
    public API getAPI() {
        Authentication auth = new BasicAuth("01010101010101010101010101010101", "ABABABABABABABABABABABABABABABABABABABAB");

        Client client = new Client(Client.SANDBOX, auth);

        return new API(client);
    }
}
