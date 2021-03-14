package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }

    public void inserir(long id, String texto) {
        Mensagem mensagem = new Mensagem(id, texto);
        mensagemDao.inserir(mensagem);
    }

    public Mensagem pesquisarPorId(long id) {
        return mensagemDao.pesquisarPorId(id);
    }
}
