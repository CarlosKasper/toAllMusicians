package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarRelacionamentosDoUsuarioService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public List<Musico> listar(String email) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        List<Relacionamento> relacionamentoList = relacionamentoRepository.findByMusico1OrMusico2AndStatusEquals(musico, musico, Status.ACEITO);

        return relacionamentoList.stream()
                .map(
                        relacionamento -> {
                            if (relacionamento.getMusico1().equals(musico)) {
                                return relacionamento.getMusico2();
                            } else {
                                return relacionamento.getMusico1();
                            }
                        }
                ).collect(Collectors.toList());
    }
}