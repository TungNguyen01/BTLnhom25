package pl.gr.manager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gr.manager.Entity.Client;
import pl.gr.manager.Repository.ClientRepository;

@Controller
@RequestMapping("/client")
public class ClientController {
    final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String getClientIndex(Model model){
        model.addAttribute("client", new Client());
        model.addAttribute("clients", clientRepository.findAll());
        return "client";
    }

    @PostMapping
    public String addClient(@ModelAttribute("client") Client client){
        clientRepository.save(client);
        return "redirect:/client";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@ModelAttribute("id") Long id, Model model){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        model.addAttribute("updatingClient", client);
        return "editClient";
    }

    @PostMapping("/edit/{id}")
    public String updateClient(@ModelAttribute("client") Client client){
        clientRepository.save(client);
        return "redirect:/client";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@ModelAttribute("id") Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        clientRepository.delete(client);
        return "redirect:/client";
    }


}
