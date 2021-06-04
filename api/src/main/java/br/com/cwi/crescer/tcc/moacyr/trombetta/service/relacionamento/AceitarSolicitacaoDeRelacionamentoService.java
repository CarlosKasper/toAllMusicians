package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Status;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceitarSolicitacaoDeRelacionamentoService {

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private BuscarRelacionamentoPorIdService buscarRelacionamentoPorIdService;

    @Autowired
    private VerificarSeRelacionamentoPertenceAoUsuarioService verificarSeRelacionamentoPertenceAoUsuarioService;

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    public void aceitar(Long id, String email) {

        Relacionamento relacionamento = buscarRelacionamentoPorIdService.buscar(id);
        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        verificarSeRelacionamentoPertenceAoUsuarioService.verificar(musico, relacionamento);

        relacionamento.setStatus(Status.ACEITO);

        relacionamentoRepository.save(relacionamento);
    }
}
