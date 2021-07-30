package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.controller.response.InviteResponse;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarConvitesDeRelacionamentoPendentesService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public List<InviteResponse> listar(String email) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        List<Relacionamento> relacionamentoList = relacionamentoRepository.findByMusico2AndStatusEquals(musico, Status.PENDENTE);

        return relacionamentoList.stream()
                .map(InviteResponse::buildFromRelacionamento)
                .collect(Collectors.toList());
    }
}