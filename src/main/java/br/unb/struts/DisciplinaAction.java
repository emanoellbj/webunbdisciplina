package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.unb.dao.DisciplinaDAO;
import br.unb.dominio.Disciplina;

public class DisciplinaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DisciplinaForm disciplinaForm = (DisciplinaForm) form;
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		
		String method = request.getParameter("method");
		if (method != null && !method.isEmpty()) {
			switch (method) {
			case "salvar":
				validaParametros(request, disciplinaForm); 
				Disciplina disciplina = new Disciplina(disciplinaForm.getNome(), disciplinaForm.getCurso(), disciplinaForm.getTurma(), disciplinaForm.getLocal());
				disciplinaDAO.salvar(disciplina);
				break;
			case "editar":
				validaParametros(request, disciplinaForm); 
				int id = Integer.parseInt(request.getParameter("id"));
				disciplina = disciplinaDAO.buscarPorId(id);
				disciplinaForm.setId(disciplina.getId());
				disciplinaForm.setNome(disciplina.getNome());
				disciplinaForm.setCurso(disciplina.getCurso());
				disciplinaForm.setTurma(disciplina.getTurma());
				disciplinaForm.setLocal(disciplina.getLocal());
				break;
			case "atualizar":
				disciplina = new Disciplina(disciplinaForm.getId(),disciplinaForm.getNome(), disciplinaForm.getCurso(), disciplinaForm.getTurma(), disciplinaForm.getLocal());
				disciplinaDAO.atualizar(disciplina);
				disciplinaForm.setId(0);
				break;
			case "excluir":
				id = Integer.parseInt(request.getParameter("id"));
				disciplinaDAO.excluir(id);
				break;
			}
		}

		List<Disciplina> disciplinas = disciplinaDAO.listarTodos();
		request.setAttribute("disciplinas", disciplinas);

		return mapping.findForward("success");
	}

	private boolean validaParametros(HttpServletRequest request, DisciplinaForm disciplinaForm) {
		ActionMessages errors = new ActionErrors();
		if (disciplinaForm.getNome() == null || disciplinaForm.getNome().trim().isEmpty()) {
			errors.add("nome", new ActionMessage("error.nome.required"));
		}
		if (disciplinaForm.getCurso() == null || disciplinaForm.getCurso().trim().isEmpty()) {
			errors.add("curso", new ActionMessage("error.curso.required"));
		}
		if (disciplinaForm.getTurma() == null || disciplinaForm.getTurma().trim().isEmpty()) {
			errors.add("turma", new ActionMessage("error.turma.required"));
		}
		if (disciplinaForm.getLocal() == null || disciplinaForm.getLocal().trim().isEmpty()) {
			errors.add("local", new ActionMessage("error.local.required"));
		}

		if (!errors.isEmpty()) {
			// Se houver erros, adicione-os ao request e retorne a página de entrada (input)
			saveErrors(request, errors);
//            return mapping.findForward("success");
		}
		return errors.isEmpty();
	}
}