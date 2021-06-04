package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Status;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarConvitesDeRelacionamentoPendentesService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public Page<Relacionamento> listar(String email, Pageable pageable) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        return relacionamentoRepository.findByMusico2AndStatusEquals(musico, Status.PENDENTE, pageable);
    }
}
