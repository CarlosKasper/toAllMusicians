package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
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
