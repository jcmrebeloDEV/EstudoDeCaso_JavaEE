package controlller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;

import dao.DaoCmtEJB;
import entity.Boat;
import entity.Sailor;

@ManagedBean
@RequestScoped
public class BeanDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boat boat;
	
	@NotNull(message = "O campo não pode ser nulo")
    private long idBoat;
	
	@NotNull(message = "O campo não pode ser nulo")
	private String query;
	
	private List<Sailor> result;

	/* nav bar */
	private int curPage;
	@SuppressWarnings("unused")
	private int totalPages;
	private int totalResults;
	private final int resultsPerPage = 5;
	
	@EJB
	DaoCmtEJB dm;
/*
	@EJB
	DaoBmtEJB dmAlt;
*/
	
	@PostConstruct
	public void init(){
		/* A variavel boat precisa ser inicializada pois caso contrário,
		 * o JSF não conseguirá utilizar os setters nesse objeto quando da
		 * submissão do formulario (beanDao.boat.id, beanDao.boat.color, etc)
		 */
		this.boat=new Boat();
	}
	
	
	public String removeCards() {
				
		dm.teste();
				
		return "index";
	}
	

	private String buscar(boolean novaBusca) {
	
		String query = this.getQuery();
		
		if (novaBusca)
			this.cleanPaginationParams();
				
		if (this.curPage == 0)
			this.curPage = 1; // se for a primeira busca, seta a primeira pagina

		List<Sailor> ltemp = dm.listSailors(query);
		if (ltemp!=null) this.totalResults = ltemp.size();
		
		int inicio = (this.curPage - 1) * this.resultsPerPage;
		
		List<Sailor> sl = dm.listSailorsPag(query, inicio, this.resultsPerPage);

		this.setResult(sl);
		
		//ERROS>>>
		if(sl==null){
		this.totalResults = 0;
		String message = "Query inválida";
		FacesMessage fmsg = new FacesMessage(message);
		fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
		}

		return "index";

	}

	private void cleanPaginationParams() {
		this.curPage = 0;
		this.totalPages = 0;
		this.totalResults = 0;
	}

	public void deleteObj(ActionEvent event) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		String param = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("delete");

		String message = "Objeto " + param + " deletado";

		System.out.println(message);

		FacesMessage fmsg = new FacesMessage(message);
		fmsg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, fmsg);

		// return "index";
	}

	public String fazerBusca() {

		return this.buscar(true);
	}
	
	public String findBoat(){
		
		this.boat=dm.findBoat(this.idBoat);
		return "index";
	}

	public String createBoat(){
		
	
		dm.createBoat(this.boat);
		this.boat=null; //uma pequena limpeza
		
		return "index";
		
	}
	
	public String updateBoat(){
		
		/* uma observação importante: O objeto boat é criado novo com
		 * todas as propriedades como default/null, e é atualizado apenas com as
		 * variaveis que foram passadas pelo JSF, e assim as outras permanecem 
		 * com os valores default/null. Por exemplo, as propriedades 'mechanics' e 
		 * 'certifications' são criadas com valor null e não são modificadas pelo JSF
		 * que utiliza apenas 'id', 'name', 'color' no formulario. Se esse objeto fosse salvo diretamente
		 * os valores originais de 'mechanics' e 'certifications' no BD seriam substituidas por null.
		 * 
		 * O correto é ler o objeto correspondente do BD e atualizar apenas as propriedades com as quais trabalhamaos
		 * no formulario JSF e então persistir esse objeto, deixando as outras propriedades inalteradas.
		 */
	
		
		long id = this.boat.getId();
		Boat originalBoat = dm.findBoat(id);
	    
		if(originalBoat!=null){
		originalBoat.setName(this.boat.getName());
		originalBoat.setColor(this.boat.getColor());
		dm.updateBoat(originalBoat);
		
		}
		
		return "index";
	}
	
	public String removeBoat(){
		
		dm.removeBoat(this.boat);
		this.boat=null; //uma pequena limpeza
		return "index";
	}
	

	public Boat getBoat() {
	
      return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	
	public long getIdBoat() {
		return idBoat;
	}

	public void setIdBoat(long idBoat) {
		this.idBoat = idBoat;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Sailor> getResult() {
		return result;
	}

	public void setResult(List<Sailor> result) {
		this.result = result;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public int getTotalPages() {

		return (int) Math.ceil((float) this.totalResults
				/ (float) this.resultsPerPage);
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}



	public String nextPage() {

		if (this.curPage < this.getTotalPages())
			this.curPage++;

		return this.buscar(false);
	}

	public String prevPage() {

		if (this.curPage > 1)
			this.curPage--;

		return this.buscar(false);
	}

}
