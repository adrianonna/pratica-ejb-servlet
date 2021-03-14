package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet{
    @EJB(lookup="java:module/mensagemService")
    private MensagemService mensagemService;
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String operacao = request.getParameter("operm");
        PrintWriter resposta = response.getWriter();
        switch (operacao) {
            case "1": {
                long id = Integer.parseInt(request.getParameter("id"));
                String texto = request.getParameter("texto");
                mensagemService.inserir(id, texto);
            }
            case "2": {
                for(Mensagem mensagem: mensagemService.listar()){
                    response.setContentType("text/html");
                    resposta.println("<div style=\"display:flex;\">" +
                            "<h2 style=\"margin-right: 10px;\">"+ mensagem.getId() +": </h2>" +
                            "<h4 style=\"display: flex;align-items: center;font-weight: lighter;\">" + mensagem.getTexto() + "</h4>" +
                            "</div>");
                }
                break;
            }
            case "3": {
                long id = Integer.parseInt(request.getParameter("id"));
                Mensagem m = mensagemService.pesquisarPorId(id);
                resposta.println(m.getTexto());
            }
        }
    }
}
