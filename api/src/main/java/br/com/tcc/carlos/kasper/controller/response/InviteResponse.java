package br.com.tcc.carlos.kasper.controller.response;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InviteResponse {

    private Musico musico;
    private Long idFriendship;

    public static InviteResponse buildFromRelacionamento(Relacionamento relacionamento) {
        return InviteResponse.builder()
                .musico(relacionamento.getMusico1())
                .idFriendship(relacionamento.getId())
                .build();
    }
}
