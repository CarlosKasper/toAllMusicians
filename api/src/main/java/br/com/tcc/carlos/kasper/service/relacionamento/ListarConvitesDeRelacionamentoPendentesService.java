package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarConvitesDeRelacionamentoPendentesService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public List<Relacionamento> listar(String email) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        return relacionamentoRepository.findByMusico2AndStatusEquals(musico, Status.PENDENTE);
    }
}