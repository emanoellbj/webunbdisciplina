package br.unb.dominio;

/**
 * @author 
 *
 */
public class Disciplina {
	private int id;
	private String nome;
	private String curso;
	private String turma;
	private String local;

	public Disciplina() {
	}

	public Disciplina(String nome, String curso, String turma, String local) {
		this.nome = nome;
		this.curso = curso;
		this.turma = turma;
		this.local = local;
	}

	
	public Disciplina(int id, String nome, String curso, String turma, String local) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.turma = turma;
		this.local = local;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Disciplina{" + "id=" + id + ", nome='" + nome + '\'' + ", curso='" + curso + '\'' + '}';
	}
}