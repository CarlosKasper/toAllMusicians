package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnviarSolicitacaoDeRelacionamentoService {

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    @Autowired
    private VerificarSeJaExisteRelacionamentoService verificarSeJaExisteRelacionamentoService;

    @Autowired
    private VerificarSeJaExistePedidoDeRelacionamentoService verificarSeJaExistePedidoDeRelacionamentoService;

    @Autowired
    private VerificarSeUsuarioNaoEstaSeConvidandoService verificarSeUsuarioNaoEstaSeConvidandoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void enviar(String emailSolicitante, String emailSolicitado) {

        Relacionamento relacionamento = new Relacionamento();

        Musico musicoSolicitante = buscarUsuarioPorEmailService.buscar(emailSolicitante);
        Musico musicoSolicitado = buscarUsuarioPorEmailService.buscar(emailSolicitado);

        verificarSeJaExisteRelacionamentoService.verificar(musicoSolicitante, musicoSolicitado);
        verificarSeJaExistePedidoDeRelacionamentoService.verificar(musicoSolicitante, musicoSolicitado);
        verificarSeUsuarioNaoEstaSeConvidandoService.verificar(emailSolicitante, emailSolicitado);

        relacionamento.setMusico1(musicoSolicitante);
        relacionamento.setMusico2(musicoSolicitado);
        relacionamento.setStatus(Status.PENDENTE);

        relacionamentoRepository.save(relacionamento);
    }
}