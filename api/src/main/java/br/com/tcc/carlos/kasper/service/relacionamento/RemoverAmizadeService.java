package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverAmizadeService {

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private BuscarRelacionamentoPorIdService buscarRelacionamentoPorIdService;

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    @Autowired
    private VerificarSeUsuarioEstaNoRelacionamentoService verificarSeUsuarioEstaNoRelacionamentoService;

    public void remover(String emailUsuario1, String emailUsuario2) {

        Musico musicoLogado = buscarUsuarioPorEmailService.buscar(emailUsuario1);

        Musico musicoARemover = buscarUsuarioPorEmailService.buscar(emailUsuario2);

        Relacionamento relacionamento = verificarSeUsuarioEstaNoRelacionamentoService.verificar(musicoLogado, musicoARemover);

        relacionamentoRepository.delete(relacionamento);
    }
}